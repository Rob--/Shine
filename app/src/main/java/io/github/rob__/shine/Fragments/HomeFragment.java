package io.github.rob__.shine.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import io.github.rob__.shine.DarkSky.Models.Datum_;
import io.github.rob__.shine.DarkSky.Models.Forecast;
import io.github.rob__.shine.Model.Gradients;
import io.github.rob__.shine.Model.TimePreview;
import io.github.rob__.shine.MyPresenter;
import io.github.rob__.shine.MyView;
import io.github.rob__.shine.R;
import io.github.rob__.shine.Service.ConcreteService;

public class HomeFragment extends Fragment implements MyView {

    private LinearLayout llHome;
    private TextView tvCurrentTemp;
    private TextSwitcher tvCurrentSummary;
    private ImageView ivCurrentIcon;
    private TextView tvPreviewTime;

    private MyPresenter mPresenter;
    private Gradients mGradients;
    private TimePreview mTimePreview;

    private String lastSummary = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);


        /* intialise elements */
        llHome = (LinearLayout) rootView.findViewById(R.id.llHome);
        tvCurrentTemp = (TextView) rootView.findViewById(R.id.tvCurrentTemp);
        tvCurrentSummary = (TextSwitcher) rootView.findViewById(R.id.tvCurrentSummary);
        tvCurrentSummary.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView () {
                /* TextView returned that displays the text */
                TextView tv = new TextView(getActivity().getApplicationContext());
                tv.setTextColor(Color.parseColor("#f2ffffff"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                tv.setShadowLayer(15, 5, 5, Color.parseColor("#4d000000"));
                return tv;
            }
        });

        ivCurrentIcon = (ImageView) rootView.findViewById(R.id.ivCurrentIcon);
        tvPreviewTime = (TextView) rootView.findViewById(R.id.tvTimePreview);

        mPresenter = new MyPresenter(this, new ConcreteService(getActivity()), getActivity().getApplicationContext());
        /* will start requesting location updates, continuously invokes the showLocation callback */
        mPresenter.getLocation();

        /* gradient class is used for the various transition animations */
        mGradients = new Gradients(getActivity().getApplicationContext(), llHome);

        ivCurrentIcon.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        llHome.setBackground(mGradients.mStartGradient);

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mPresenter.onTouchEvent(view, motionEvent);
                return true;
            }
        });

        return rootView;
    }

    public Spanned formatTemperature(double celsius){
        return parseHtml(String.valueOf(celsius) + "<sup><small><small><small>°C</small></small><small></small></sup>");
    }

    @SuppressWarnings("deprecation")
    public static Spanned parseHtml(String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }

    @Override
    public void reactToTouch(int action){
        if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_UP){
            tvPreviewTime.animate().setDuration(500).alpha(
                    action == MotionEvent.ACTION_DOWN ? 1.f : 0.f
            );
        }

        if(action == MotionEvent.ACTION_UP){
            /* when the user lifts their finger, we want to reset the data to default,
               so we use the presenter to invoke the showForecastInfo method */
            mPresenter.showForecast();
        }
    }

    @Override
    public void showTimePreview(Forecast forecast, String formattedTime, int hours, int minutes){
        tvPreviewTime.setText(formattedTime);

        /* if the forecast data is null return because we can't preview anything */
        if(forecast == null) return;

        /* get the data object for the hour we want to preview */
        Datum_ hourlyData = forecast.getHourly().getData().get(hours);

        tvCurrentTemp.setText(formatTemperature(hourlyData.getTemperature()));

        /* we need to check if the text has differed at all as animations run, setting the text
           even though the content hasn't changed disrupts animations */
        if(!lastSummary.equals(hourlyData.getSummary())){
            tvCurrentSummary.setText(lastSummary = hourlyData.getSummary());
        }
    }

    @Override
    public void showForecastInfo(Forecast forecast){
        if(forecast == null) return;

        /* if the handler isn't running yet, we begin the animations */
        if(!mGradients.isHanderRunning()){
            mGradients.transitionBetween(
                    Gradients.Mornings.randomGradient(),
                    Gradients.Mornings.randomGradient()
            );
            mGradients.runHandler();
        }

        tvCurrentTemp.setText(formatTemperature(forecast.getCurrently().getTemperature()));
        tvCurrentSummary.setText(lastSummary = forecast.getCurrently().getSummary());

        boolean isNight = ((forecast.getDaily().getData().get(0).getSunsetTime() * 1000) - System.currentTimeMillis()) > 3600000;

        int icon;
        switch(forecast.getCurrently().getIcon()){
            case "clear-day":
                icon = R.drawable.ic_clear_day;
                break;
            case "clear-night":
                icon = R.drawable.ic_clear_night;
                break;
            case "rain":
                icon = isNight ? R.drawable.ic_rain_night : R.drawable.ic_rain_day;
                break;
            case "snow":
                icon = isNight ? R.drawable.ic_snow_night : R.drawable.ic_snow_day;
                break;
            case "sleet":
                icon = isNight ? R.drawable.ic_hail_night : R.drawable.ic_hail_day;
                break;
            case "wind":
                icon = isNight ? R.drawable.ic_wind_night : R.drawable.ic_wind_day;
                break;
            case "fog":
                icon = isNight ? R.drawable.ic_fog_night : R.drawable.ic_fog_day;
                break;
            case "cloudy":
                icon = isNight ? R.drawable.ic_cloud_night : R.drawable.ic_cloud_day;
                break;
            case "partly-cloudy-day":
                icon = R.drawable.ic_cloud_partly_day;
                break;
            case "partly-cloudy-night":
                icon = R.drawable.ic_cloud_partly_night;
                break;
            default:
                icon = R.drawable.ic_cloud_day;
        }

        ivCurrentIcon.setImageResource(icon);
    }

    @Override
    public void showLocation(Location loc){
        /* loc may be null if no location permission is given */
        Log.d("Fragment", "showLocation called!");
        if(loc != null){
            mPresenter.getForecast();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }
}
