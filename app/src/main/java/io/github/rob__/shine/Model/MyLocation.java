package io.github.rob__.shine.Model;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import io.github.rob__.shine.MyPresenter;

public class MyLocation extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private Location mLocation;
    private Context mContext;
    private GoogleApiClient mClient;
    private LocationRequest mLocationReq;
    private MyPresenter mPresenter;

    private final static int CODE_LOCATION_PERMISSION = 1;
    public final static int CODE_LOCATION_RESOLUTION = 2;

    private final static String TAG = "MyLocation";

    /**
     * Constructor, takes context and proceeds to set up location services and retrieve
     * the location in order to return it whenever requested.
     * @param context - activity context
     */
    public MyLocation(Context context) {
        this.mContext = context;

        /* if we don't have location permissions, attempt to obtain them,
           if we do, go ahead and attempt to get a location */
        if (ContextCompat.checkSelfPermission(this.mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "No location permissions, requesting them...");
            ActivityCompat.requestPermissions((Activity) this.mContext, new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, CODE_LOCATION_PERMISSION);
        } else {
            retrieveLocation();
        }
    }

    /**
     * Sets the presenter so we can call it to update the location on the `requestLocationUpdates`
     * callback, this is probably bad practice - not sure how else to accomplish this though
     */
    public void setPresenter(MyPresenter presenter){
        this.mPresenter = presenter;
    }

    /**
     * Simply returns the last known location, may be null.
     * @return android.location - last known location
     */
    public Location getLocation(){
        return mLocation;
    }

    /**
     * Simply sets the location
     */
    public void setLocation(Location location) {
        this.mLocation = location;
    }

    /**
     * Creates a Google API Client and proceeds to attempt to retrieve the last known
     * location. Creates a location request and connects to the Google API Client
     * which will update this class' location.
     *
     * Will create a prompt to enable location services if necessary.
     */
    private void retrieveLocation(){
        Log.d(TAG, "About to retrieve the location...");

        this.mClient = new GoogleApiClient
                .Builder(this.mContext, this, this)
                .addApi(LocationServices.API).build();

        mLocationReq = new LocationRequest()
                .setInterval(20000)
                .setFastestInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        this.mClient.connect();
    }

    /**
     * Callback for when the Google API Client is connected.
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "Connected to the Google API client, building LocationSettingsRequest...");

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest
                .Builder()
                .addLocationRequest(mLocationReq);

        PendingResult<LocationSettingsResult> result = LocationServices
                .SettingsApi
                .checkLocationSettings(mClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                switch(result.getStatus().getStatusCode()){
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            result.getStatus().startResolutionForResult((Activity) mContext, CODE_LOCATION_RESOLUTION);
                        } catch(IntentSender.SendIntentException e){
                            Log.d(TAG, "Unable to ask the user to enable Location Services");
                            e.printStackTrace();
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.d(TAG, "Unable to change Location Services settings");
                }
            }
        });

        try{
            if(mLocation == null) {
                Log.d(TAG, "Getting last location via Fused Location API");
                mLocation = LocationServices.FusedLocationApi.getLastLocation(mClient);
            }

            LocationServices.FusedLocationApi.requestLocationUpdates(mClient, mLocationReq, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    mPresenter.setLocation(mLocation = location);
                    Log.d(TAG, "Location changed, longitude: " + String.valueOf(mLocation.getLongitude()));
                    Log.d(TAG, "Location changed: latitude: " + String.valueOf(mLocation.getLatitude()));
                }
            });

        } catch(SecurityException e){
            Log.d(TAG, "Unable to request location, SecurityException occurred");
            e.printStackTrace();
        }
    }

    /**
     * Callback for when the Google API Client connection is suspended.
     */
    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Connection to Google API Client suspended");
    }

    /**
     * Callback for when the Google API Client fails to connect.
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "Failed to connect to Google API Client");
    }

    /**
     * Callback for when we request permission to access the user's location.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(requestCode != CODE_LOCATION_PERMISSION) return;

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Location permissions granted");
            retrieveLocation();
        } else {
            Log.d("Perms", "Location permissions denied");
        }
    }

    /**
     * Callback for when we request to enable Location Services.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /* No need to check if requestCode matches, we proxy this callback through
           the presenter and checks are conducted there */

        switch (resultCode) {
            case Activity.RESULT_OK:
                Log.d(TAG, "Location Services enabled");
                break;
            case Activity.RESULT_CANCELED:
                Log.d(TAG, "Location Services request dialog cancelled");
                break;
            default:
                break;
        }
    }
}