package io.github.rob__.shine;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.ColorUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Gradients {

    private int[] blues;
    private int[] frost = new int[] { Color.parseColor("#000428"), Color.parseColor("#004e92")};
    private int[] royal = new int[] { Color.parseColor("#141E30"), Color.parseColor("#243B55")};
    private int[] sunsetBright = new int[] { Color.parseColor("#ee0979"), Color.parseColor("#ff6a00")};
    private int[] sunsetNight = new int[] { Color.parseColor("#0B486B"), Color.parseColor("#F56217")};
    private int[] clearDayToDark = new int[] { Color.parseColor("#1A2980"), Color.parseColor("#26D0CE")};
    private int[] rainy = new int[] { Color.parseColor("#16222A"), Color.parseColor("#3A6073")};

    private Context mContext;
    private Point mScreenSize;

    public Gradients(Context context){
        this.mContext = context;

        this.blues = new int[] {
                Color.parseColor("#0D47A1"),
                Color.parseColor("#1565C0"),
                Color.parseColor("#1976D2"),
                Color.parseColor("#1E88E5"),
                Color.parseColor("#2196F3"),
                Color.parseColor("#42A5F5"),
                Color.parseColor("#64B5F6"),
                Color.parseColor("#90CAF9"),
                Color.parseColor("#BBDEFB"),
                Color.parseColor("#E3F2FD")

        };


        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        this.mScreenSize = new Point(dm.widthPixels, dm.heightPixels);
    }

    public GradientDrawable getGradient(int a){
        GradientDrawable gradient = new GradientDrawable();
        gradient.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        gradient.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gradient.setSize(mScreenSize.x, mScreenSize.y);
        gradient.setColors(a == 1 ? rainy : frost);

        return gradient;
    }
}
