package io.github.rob__.shine.DarkSky;

import io.github.rob__.shine.DarkSky.Models.Forecast;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyInterface {
    void onResponse(Forecast forecast);

    @GET("forecast/{key}/{latitude},{longitude}?units=si")
    Call<Forecast> getForecast(@Path("key") String key, @Path("latitude") double latitude, @Path("longitude") double longitude);
}
