package com.kindly.monngon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.kindly.monngon.fragment.TodayMenuFragment;
import com.kindly.monngon.model.Mon;

import java.util.ArrayList;

/**
 * Created by PC0353 on 11/8/2016.
 */

public class MenuTodayAdapter extends FragmentStatePagerAdapter {
    private ArrayList<Mon> listItem;

    public MenuTodayAdapter(FragmentManager fm, ArrayList<Mon> listItem) {
        super(fm);
        this.listItem = listItem;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = TodayMenuFragment.newInstance(listItem.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}