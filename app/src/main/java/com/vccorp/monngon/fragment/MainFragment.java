package com.vccorp.monngon.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vccorp.monngon.R;
import com.vccorp.monngon.adapter.MainPagerAdapter;

/**
 * Created by PC0353 on 10/6/2016.
 */

public class MainFragment extends Fragment {
    private Activity activity;
    private View mView;

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private String[] listTitle;
    private Activity mContext;

    public static MainFragment newInstance() {
        MainFragment myFragment = new MainFragment();
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        activity = getActivity();
        initContent();
        return mView;
    }


    private void initContent() {
        mViewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) mView.findViewById(R.id.tabs);
        listTitle = getResources().getStringArray(R.array.tab_main);
        mViewPager.setAdapter(new MainPagerAdapter(mContext, getChildFragmentManager(), listTitle));
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
