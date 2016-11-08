package com.vccorp.monngon.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vccorp.monngon.fragment.ExampleFragment;
import com.vccorp.monngon.fragment.HomeFragment;

/**
 * Created by PC0353 on 4/29/2016.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    private String[] mTitles;
    private Context mContext;

    public MainPagerAdapter(Context context, FragmentManager fm, String[] listTitle) {
        super(fm);
        this.mTitles = listTitle;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mTitles != null) {
            return mTitles.length;
        }
        return 0;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = HomeFragment.newInstance();
        } else if (position == 1) {
            fragment = ExampleFragment.newInstance();
        } else if (position == 2) {
            fragment = ExampleFragment.newInstance();
        } else if (position == 3) {
            fragment = ExampleFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String sb = "";
        try {
            sb = mTitles[position];
        } catch (Exception e) {
            e.printStackTrace();
            sb = "";
        }
        return sb;
    }
}


