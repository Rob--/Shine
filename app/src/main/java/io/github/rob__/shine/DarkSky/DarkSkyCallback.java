package io.github.rob__.shine.DarkSky;

import io.github.rob__.shine.DarkSky.Models.Forecast;
import retrofit2.Response;

public interface DarkSkyCallback {

    void onResponse(Response<Forecast> forecast);
    void onFailure(Throwable t);

}
