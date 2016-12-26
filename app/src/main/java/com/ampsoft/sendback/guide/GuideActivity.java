package com.ampsoft.sendback.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ampsoft.sendback.R;
import com.ampsoft.sendback.lib.BackPressed;

/**
 * Created by saki on 16. 11. 23..
 */
public class GuideActivity extends FragmentActivity {
    Context mContext;
    private int NUM_PAGES = 3;

    public final static int FRAGMENT_PAGE1 = 0;
    public final static int FRAGMENT_PAGE2 = 1;
    public final static int FRAGMENT_PAGE3 = 2;

    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mViewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        mViewPager.setCurrentItem(FRAGMENT_PAGE1);
    }



    private class pagerAdapter extends FragmentPagerAdapter {

        public pagerAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case FRAGMENT_PAGE1:
                    return new Guide1Fragment();
                case FRAGMENT_PAGE2:
                    return new Guide2Fragment();
                case FRAGMENT_PAGE3:
                    return new Guide3Fragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GuideActivity.this, BackPressed.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (String.valueOf(resultCode).equals("-1")) {
            finish();
        }
    }
}
