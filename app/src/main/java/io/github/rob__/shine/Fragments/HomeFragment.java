package io.github.rob__.shine.Fragments;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.location.Location;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Handler;

import io.github.rob__.shine.DarkSky.Models.Forecast;
import io.github.rob__.shine.Gradients;
import io.github.rob__.shine.MyPresenter;
import io.github.rob__.shine.MyView;
import io.github.rob__.shine.R;
import io.github.rob__.shine.Service.ConcreteService;

public class HomeFragment extends Fragment implements MyView {

    private LinearLayout llHome;
    private TextView tvCurrentTemp;
    private TextView tvCurrentSummary;
    private MyPresenter presenter;
    private Gradients gradient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);


        llHome = (LinearLayout) rootView.findViewById(R.id.llHome);
        tvCurrentTemp = (TextView) rootView.findViewById(R.id.tvCurrentTemp);
        tvCurrentSummary = (TextView) rootView.findViewById(R.id.tvCurrentSummary);

        presenter = new MyPresenter(this, new ConcreteService(getActivity()), getActivity().getApplicationContext());

        presenter.getLocation();

        gradient = new Gradients(getActivity().getApplicationContext());

        // Initialize a new Handler
        mHandler = new Handler();


        // Set the RelativeLayout background
        llHome.setBackground(mStartGradient);

        return rootView;
    }

    private Handler mHandler;
    private Runnable mRunnable;
    private int mInterval = 5000;

    private TransitionDrawable drawable;
    private GradientDrawable mStartGradient;
    private GradientDrawable mEndGradient;

    private boolean retrievedInfo = false;

    @Override
    public void showForecastInfo(Forecast forecast){
        if(forecast == null || retrievedInfo) return;
        retrievedInfo = true;
        tvCurrentTemp.setText(forecast.getCurrently().getTemperature() + "° C");
        tvCurrentSummary.setText(forecast.getCurrently().getSummary());

        mStartGradient = gradient.getGradient(1);
        mEndGradient = gradient.getGradient(2);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                Log.d("Starting", "gradient");
                GradientDrawable mTempStart = mStartGradient;
                GradientDrawable mTempEnd = mEndGradient;
                mStartGradient = mTempEnd;
                mEndGradient = mTempStart;

                GradientDrawable[] mGradientDrawableArray = new GradientDrawable[]{
                        mStartGradient,
                        mEndGradient
                };

                drawable = new TransitionDrawable(mGradientDrawableArray);

                drawable.startTransition(mInterval);
                llHome.setBackground(drawable);

                mHandler.postDelayed(mRunnable, mInterval + 1000);
            }
        };
        // Play animation immediately after button click
        mHandler.postDelayed(mRunnable, 100);
    }

    @Override
    public void showLocation(Location loc){
        /* loc may be null if no location permission is given */

        Log.d("Fragment", "showLocation called!");
        if(loc != null){
            presenter.getForecast();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }
}
