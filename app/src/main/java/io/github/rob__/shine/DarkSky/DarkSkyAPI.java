package io.github.rob__.shine.DarkSky;

import android.util.Log;

import io.github.rob__.shine.DarkSky.Models.Forecast;
import okhttp3.internal.http.RequestException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DarkSkyAPI {

    private static final String TAG = "DarkSkyAPI";
    public static final String BASE_URL = "https://api.darksky.net/";
    private static final String API_KEY = "03bf1f782e9cc5a448f602e2d921f78c";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    DarkSkyInterface mInterface = retrofit.create(DarkSkyInterface.class);

    public DarkSkyAPI(){

    }

    public void makeCall(double latitude, double longitude, final DarkSkyCallback callback){
        Call<Forecast> request = mInterface.getForecast(API_KEY, latitude, longitude);

        request.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Log.d(TAG, "Successfully made API request through Dark Sky API");
                int statusCode = response.code();
                Log.d(TAG, "Status code for call: " + String.valueOf(statusCode));

                callback.onResponse(response);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.d(TAG, "Error making API call through Dark Sky API");
                t.printStackTrace();
            }
        });
    }
}
