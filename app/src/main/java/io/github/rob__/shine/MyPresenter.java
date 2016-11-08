package io.github.rob__.shine;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.util.Log;

import io.github.rob__.shine.DarkSky.DarkSkyAPI;
import io.github.rob__.shine.DarkSky.DarkSkyCallback;
import io.github.rob__.shine.DarkSky.Models.Forecast;
import io.github.rob__.shine.Model.MyLocation;
import io.github.rob__.shine.Service.MyService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;

public class MyPresenter {

    private final String TAG = "Presenter";

    private MyView mView;
    private MyService mService;
    private Context mContext;
    private MyLocation mLocation;
    private DarkSkyAPI darkSkyApi;

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

        this.darkSkyApi = new DarkSkyAPI();


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
     * Calls getForecast, which will invoke an api call to Dark Sky to get a forecast.
     */
    public void getForecast(){
        /* if we have a location, make the api call to get the forecast */
        if(this.mLocation.getLocation() != null){
            Log.d("Making api call", "making api call");

            this.darkSkyApi.makeCall(
                    this.mLocation.getLocation().getLatitude(),
                    this.mLocation.getLocation().getLongitude(),
                    new DarkSkyCallback() {
                        @Override
                        public void onResponse(Response<Forecast> forecast) {
                            mView.showForecastInfo(forecast.body());
                        }
                    });
        }
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
