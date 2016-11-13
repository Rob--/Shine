package io.github.rob__.shine;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import io.github.rob__.shine.DarkSky.DarkSkyAPI;
import io.github.rob__.shine.DarkSky.DarkSkyCallback;
import io.github.rob__.shine.DarkSky.Models.Forecast;
import io.github.rob__.shine.Model.MyLocation;
import io.github.rob__.shine.Model.TimePreview;
import io.github.rob__.shine.Service.MyService;
import retrofit2.Response;

public class MyPresenter {

    private final String TAG = "Presenter";

    private MyView mView;
    private MyService mService;
    private Context mContext;
    private MyLocation mLocation;
    private DarkSkyAPI mDarkSkyApi;
    private TimePreview mTimePreview;
    private Forecast mForecast;

    /**
     * Consturctor for MyPresenter, sets up location retrieval by calling getLocation()
     * @param view - custom MyView class.
     * @param service - custom MyService class.
     * @param context - Activity context.
     */
    public MyPresenter(MyView view, MyService service, Context context){
        this.mView = view;
        this.mService = service;
        this.mContext = context;
        this.mLocation = service.getLocation(this.mContext);
        this.mLocation.setPresenter(this);

        this.mDarkSkyApi = new DarkSkyAPI();
    }


    /**
     * Callback for the onTouchListener, used mainly for the TimePreview class
     * in order to update the view based on the user dragging upwards
     * @param v - View from onTouch callback argument
     * @param motionEvent - MotionEvent from the onTouch callback argument
     */
    public void onTouchEvent(View v, MotionEvent motionEvent){
        int action = motionEvent.getAction();

        if(action == MotionEvent.ACTION_MOVE){
        }

        if(action == MotionEvent.ACTION_DOWN){
            /* when the user presses down on the screen, we save the Y value to be
               able to calculate delta Y as they drag their finger up */
            mTimePreview.setStartY(motionEvent.getY());
        }

        if(action == MotionEvent.ACTION_UP){
            /* when the user lifts their finger up off the screen, we default the value
               so we know when startY is -1.0f, no finger is pressed on the screen */
            mTimePreview.resetStartY();
        }

        mView.reactToTouch(action);

        /* if the value is not default, i.e. finger is down on the screen we need
           to calculate delta Y and preview various information as delta Y corresponds
           to time elapsed */
        if(mTimePreview.fingerDragged()){
            mTimePreview.calculateDeltaY(motionEvent.getY());

            mView.showTimePreview(
                    this.mForecast, mTimePreview.getFormattedTime(),
                    mTimePreview.getHours(), mTimePreview.getMinutes()
            );
        }
    }

    /**
     * Sets the location, is called from `MyLocation`
     */
    public void setLocation(Location location){
        this.mLocation.setLocation(location);
        mView.showLocation(this.mLocation.getLocation());
    }

    /**
     * Calls showLocation, will which call showLocation wherever MyView is implemented.
     */
    public void getLocation(){
        mView.showLocation(this.mLocation.getLocation());
    }

    /**
     * Calls getForecast, which will invoke an api call to Dark Sky to get a mForecast.
     */
    public void getForecast(){
        /* if we have a location, make the api call to get the mForecast */
        if(this.mLocation.getLocation() != null){
            Log.d("Making api call", "making api call");

            this.mDarkSkyApi.makeCall(
                    this.mLocation.getLocation().getLatitude(),
                    this.mLocation.getLocation().getLongitude(),
                    new DarkSkyCallback() {
                        @Override
                        public void onResponse(Response<Forecast> response) {
                            mForecast = response.body();
                            showForecast();
                        }

                        @Override
                        public void onFailure(Throwable t){
                            t.printStackTrace();
                        }
                    });
        }
    }

    /**
     * Calls upon the view to display the mForecast info, we used this to reset any time
     * previews.
     */
    public void showForecast(){
        mView.showForecastInfo(mForecast);
    }

    /**
     * Proxy for onActivityResult through the main activity to classes that invoke the callback
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case MyLocation.CODE_LOCATION_RESOLUTION:
                mLocation.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

}
