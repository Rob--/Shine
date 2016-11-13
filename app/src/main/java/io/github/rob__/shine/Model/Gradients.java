package io.github.rob__.shine.Model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.util.Log;
import android.support.v4.graphics.ColorUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Gradients {

    public static class Mornings {
        public static int[] warmClear = new int[] { Color.parseColor("#67B26F"), Color.parseColor("#4ca2cd") }; // Mild
        //public static int[] coldDark = new int[] { Color.parseColor("#3B4371"), Color.parseColor("#F3904F") }; // Dawn
        public static int[] brightColourful = new int[] { Color.parseColor("#F3904F"), Color.parseColor("#3B4371") }; // Brady Brady Fun Fun
        public static int[] greenMix = new int[] { Color.parseColor("#F3904F"), Color.parseColor("#3B4371") }; // Lush
        //public static int[] paleWhite = new int[] { Color.parseColor("#FF5F6D"), Color.parseColor("#FFC371") }; // Sweet Morning
        public static int[] paleGreen = new int[] { Color.parseColor("#c2e59c"), Color.parseColor("#64b3f4") }; // Green and Blue
        public static int[] grassGreen = new int[] { Color.parseColor("#c2e59c"), Color.parseColor("#64b3f4") }; // Little Leaf
        public static int[] springMorning = new int[] { Color.parseColor("#00C9FF"), Color.parseColor("#92FE9D") }; // Back to Earth
        public static int[] calmMorning = new int[] { Color.parseColor("#43cea2"), Color.parseColor("#185a9d") }; // Endless River
        public static int[] emeraldWater = new int[] { Color.parseColor("#43cea2"), Color.parseColor("#185a9d") }; // Emerald Water
        public static int[] warmWater = new int[] { Color.parseColor("#02AAB0"), Color.parseColor("#00CDAC") }; // Green Beach
        //public static int[] hazeMix = new int[] { Color.parseColor("#02AAB0"), Color.parseColor("#00CDAC") }; // Harmonic Energy
        public static int[] reef = new int[] { Color.parseColor("#3a7bd5"), Color.parseColor("#00d2ff") }; // Reef
        public static int[] seaweed = new int[] { Color.parseColor("#4CB8C4"), Color.parseColor("#3CD3AD") }; // Sea Weed

        public static int[][] gradients = new int[][] {
                warmClear, brightColourful, greenMix, paleGreen, seaweed,
                grassGreen, springMorning, calmMorning, emeraldWater, warmWater, reef
        };

        public static int[] randomGradient(){
            int r = (int) Math.floor(Math.random() * gradients.length);
            return gradients[r];
        }
    }

    public static class Evenings {
        public static int[] dawn = new int[] { Color.parseColor("#F3904F"), Color.parseColor("#3B4371")}; // Dawn
        public static int[] ibizaSunset = new int[] { Color.parseColor("#ee0979"), Color.parseColor("#ff6a00")}; // Ibiza Sunset
        public static int[] warmEvening = new int[] { Color.parseColor("#f79d00"), Color.parseColor("#64f38c")}; // Sherbert
        public static int[] sunset = new int[] { Color.parseColor("#0B486B"), Color.parseColor("#F56217")}; // Sunset
        public static int[] purpleSunset = new int[] { Color.parseColor("#e96443"), Color.parseColor("#904e95")}; // Grapefruit Sunset
        public static int[] redSunset = new int[] { Color.parseColor("#A43931"), Color.parseColor("#1D4350")}; // Red Ocean
        public static int[] frost = new int[] { Color.parseColor("#0B486B"), Color.parseColor("#F56217")}; // Frost
        public static int[] blush = new int[] { Color.parseColor("#B24592"), Color.parseColor("#F15F79")}; // Blush
        public static int[] clearEvening = new int[] { Color.parseColor("#005C97"), Color.parseColor("#363795")}; // Clear Sky
        public static int[] brightToDark = new int[] { Color.parseColor("#005C97"), Color.parseColor("#363795")}; // Back to the Sky
        public static int[] purpleBliss = new int[] { Color.parseColor("#0b8793"), Color.parseColor("#360033")}; // Purple Bliss
        public static int[] darkRed = new int[] { Color.parseColor("#e74c3c"), Color.parseColor("#000000")}; // Red Mist
        public static int[] clearNight = new int[] { Color.parseColor("#26D0CE"), Color.parseColor("#1A2980")}; // Aqua Marine

        public static int[][] gradients = new int[][] {
                dawn, ibizaSunset, warmEvening, sunset, purpleSunset, redSunset, frost,
                blush, clearEvening, brightToDark, purpleBliss, darkRed, clearNight
        };

        public static int[] randomGradient(){
            int r = (int) Math.floor(Math.random() * gradients.length);
            return gradients[r];
        }
    }

    public static class Midnight {
        public static int[] deepSpace = new int[] { Color.parseColor("#000000"), Color.parseColor("#282828")}; // Deep Space
        public static int[] spaceGray = new int[] { Color.parseColor("#232526"), Color.parseColor("#414345")}; // Midnight City

        public static int[][] gradients = new int[][] {
                deepSpace, spaceGray
        };

        public static int[] randomGradient(){
            int r = (int) Math.floor(Math.random() * gradients.length);
            return gradients[r];
        }
    }

    private Context mContext;
    public static Point mScreenSize;

    private Handler mHandler;
    private boolean mHandlerRunning = false;
    private Runnable mRunnable;
    private int mDuration = 500;
    private int mDelay = 250;

    /* transition states to identify the direction of the gradient's transition */
    Transition currentState = Transition.Start;
    private enum Transition {
        Start, HalfwayToStart, HalfwayToEnd, End
    }

    public TransitionDrawable mTransition;
    public GradientDrawable mStartGradient ;
    public GradientDrawable mHalfwayGradient;
    public GradientDrawable mEndGradient;
    public GradientDrawable mLocalStart;
    public GradientDrawable mLocalEnd;

    /**
     * Constructor will find the screen size and initialise the Handler that animates
     * the background gradient transitions
     * @param context - activity context
     * @param layout - the layout that the gradient will animate on
     */
    public Gradients(Context context, final LinearLayout layout){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        this.mScreenSize = new Point(dm.widthPixels, dm.heightPixels);
        this.mContext = context;

        /* default transition */
        transitionBetween(Mornings.springMorning, Evenings.clearEvening);

        this.mHandler = new Handler();

        mRunnable = new Runnable() {
            @Override
            public void run() {

                /* takes the current transition state into account to determine which gradients
                   should next occur */
                if(currentState == Transition.Start){

                    mLocalStart = mStartGradient;
                    mLocalEnd = mHalfwayGradient;
                    currentState = Transition.HalfwayToEnd;

                } else if(currentState == Transition.HalfwayToEnd){

                    mLocalStart = mHalfwayGradient;
                    mLocalEnd = mEndGradient;
                    currentState = Transition.End;

                } else if(currentState == Transition.HalfwayToStart){

                    mLocalStart = mHalfwayGradient;
                    mLocalEnd = mStartGradient;
                    currentState = Transition.Start;

                } else {

                    mLocalStart = mEndGradient;
                    mLocalEnd = mHalfwayGradient;
                    currentState = Transition.HalfwayToStart;

                }

                /* creates a new TransitionDrawable, this is the next gradient
                   the background will transition to */
                mTransition = new TransitionDrawable(new GradientDrawable[]{
                        mLocalStart,
                        mLocalEnd
                });

                transitionTo(Mornings.randomGradient());


                layout.setBackground(mTransition);

                /* transition will take mInterval duration */
                mTransition.startTransition(mDuration);

                /* reverse the animation after it finished + the delay */
                mHandler.postDelayed(mRunnable, mDuration + mDelay);
            }
        };
    }

    /**
     * Should only be used to initialise an animation, after this has been called initially
     * the transitionTo method should be used for an eased gradient transition.
     * @param colors1 - the first set of gradient colours
     * @param colors2 - the second set of gradient colours
     */
    public void transitionBetween(int[] colors1, int[] colors2){
        this.mStartGradient = gradientFromColours(colors1);
        this.mHalfwayGradient = gradientFromColours(getHalfwayColors(colors1, colors2));
        this.mEndGradient = gradientFromColours(colors2);
    }

    /**
     * Will create a new colour gradient array with the halfway colours between two given
     * gradients. This is used to smoothly transition between two gradients.s
     * @param colors1 - the first set of gradient colours
     * @param colors2 - the second set of gradient colours
     * @return
     */
    public int[] getHalfwayColors(int[] colors1, int[] colors2){
        return new int[] {
                colors1[1], colors2[0]
        };
    }

    /**
     * Sets a new gradient colour scheme to transition to. Using this method allows a smooth
     * gradient transition as it takes the current gradient transition direction into account.
     * @param colors - the set of colours to transition tos
     */
    public void transitionTo(int[] colors){
        if(currentState == Transition.Start || currentState == Transition.HalfwayToStart){
            mEndGradient = gradientFromColours(colors);
        } else {
            mStartGradient = gradientFromColours(colors);
        }
    }

    /**
     * Runs the handler and begins the gradient animations.
     */
    public void runHandler(){
        mHandler.post(mRunnable);
        mHandlerRunning = true;
    }

    /**
     * Returns whether the handler has been run or not. Is used to determine if the background
     * is currently being transitioned with gradients.s
     * @return boolean - handler running
     */
    public boolean isHanderRunning(){
        return mHandlerRunning;
    }

    /**
     * Creates a gradient drawable from a given color array.
     * @param colours - the set of colours for the gradient
     * @return GradientDrawable
     */
    private GradientDrawable gradientFromColours(int[] colours){
        GradientDrawable gradient = new GradientDrawable();
        gradient.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
        gradient.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gradient.setSize(mScreenSize.x, mScreenSize.y);
        gradient.setColors(colours);

        return gradient;
    }
}
