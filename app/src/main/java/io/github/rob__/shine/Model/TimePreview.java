package io.github.rob__.shine.Model;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimePreview {

    /* default value, used to state whether the user is currently dragging or not */
    private static float DEFAULT_Y = -1.0f;
    /* starting Y position when user touches screen initially */
    private static float mStartY;
    /* change in start Y from all updated Y positions during drag */
    private static float mDeltaY;
    /* delta Y's percentage of the entire screen */
    private static float mScreenPercentage;
    /* minutes to preview info for, calculated from delta Y size */
    private static int mMinutes;
    /* mScreenPercentage threshold before we go into calculating hours to preview for */
    private static float mMinutesThreshold = 0.15f;
    /* hours to preview info for */
    private static int mHours;

    /**
     * Set the starting Y position in order to calculate delta Y.
     * @param y - the y position on the screen
     */
    public static void setStartY(float y){
        mStartY = y;
    }

    /**
     * Reset the starting Y value to default (when user lifts finger off screen)
     */
    public static void resetStartY(){
        mStartY = DEFAULT_Y;
    }

    /**
     * Returns the number of minutes to display forecast information for in the future.
     * @return int - minutes from current time
     */
    public static int getMinutes(){
        return mMinutes;
    }

    /**
     * Returns the number of hours to display forecast information for in the future.
     * @return int - hours from current time
     */
    public static int getHours(){
        return mHours;
    }

    /**
     * Checks if the user is still dragging the finger by checking if start Y is defaulted
     * @return boolean, true if finger is dragging on the screen
     */
    public static boolean fingerDragged(){
        return mStartY != DEFAULT_Y;
    }

    /**
     * Calculates delta Y, delta Y's screen percentage, and the minutes/hours preview times,
     * @param y - new y value to calculate delta Y from
     */
    public static void calculateDeltaY(float y){
        mDeltaY = mStartY - y;
        mScreenPercentage = mDeltaY / Gradients.mScreenSize.y;
        if(mScreenPercentage < 0) mScreenPercentage = 0;

        if(inMinuteThreshold()){
            float pctOfThreshold = mScreenPercentage / mMinutesThreshold;
            mMinutes = Math.round(pctOfThreshold * 60.0f);
        } else {
            float pctOfThreshold = mScreenPercentage - mMinutesThreshold;
            mHours = Math.round(pctOfThreshold * 47.0f) + 1;
            Log.d("Hours", String.valueOf(mHours));
        }
    }

    /**
     * Determines if we're below the minute threshold and should therefore still return
     * minutely data to preview.
     * @return boolean - below minute threshold
     */
    private static boolean inMinuteThreshold(){
        return mScreenPercentage < mMinutesThreshold;
    }

    /**
     * Returns a string of the formatted preview time.
     * @return String - formatted preview time
     */
    public static String getFormattedTime(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(inMinuteThreshold() ? Calendar.MINUTE : Calendar.HOUR, inMinuteThreshold() ? mMinutes : mHours);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(c.getTime());
    }
}
