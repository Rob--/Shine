package io.github.rob__.shine;

import android.location.Location;
import android.view.MotionEvent;

import io.github.rob__.shine.DarkSky.Models.Forecast;

public interface MyView {

    void showLocation(Location loc);
    void showForecastInfo(Forecast forecast);
    void reactToTouch(int action);
    void showTimePreview(Forecast forecast, String formattedTime, int hours, int minutes);

}
