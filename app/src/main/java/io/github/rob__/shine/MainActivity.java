package io.github.rob__.shine;

import android.graphics.PixelFormat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import io.github.rob__.shine.Fragments.HomeFragment;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new CustomViewPagerAdapter(getSupportFragmentManager()));
    }

    private class CustomViewPagerAdapter extends FragmentStatePagerAdapter {
        private final static int PAGE_COUNT = 1;

        public CustomViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new HomeFragment();
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
