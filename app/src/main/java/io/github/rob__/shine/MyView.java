package io.github.rob__.shine;

import android.location.Location;

import io.github.rob__.shine.DarkSky.Models.Forecast;

public interface MyView {

    void showLocation(Location loc);
    void showForecastInfo(Forecast forecast);

}
