package com.vccorp.monngon.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thaond.library.util.Utils;
import com.vccorp.monngon.MainActivity;
import com.vccorp.monngon.R;
import com.vccorp.monngon.model.HomeMenu;
import com.vccorp.monngon.model.Mon;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final int VIEW_HEADER = 0;

    private List<HomeMenu> MessageList;
    private MainActivity activity;

    public HomeAdapter(List<HomeMenu> Messages, MainActivity activity) {
        this.MessageList = Messages;
        this.activity = activity;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        }
        return VIEW_HEADER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        Utils.logE("thaond","onCreateViewHolder ");


        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_home_header, parent, false);

        vh = new HeaderViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond","onBindViewHolder ");

        if (holder instanceof HeaderViewHolder) {
            Utils.logE("thaond","HeaderViewHolder");
            HomeMenu singleMessage = (HomeMenu) MessageList.get(position);
            ((HeaderViewHolder) holder).adapter = new MenuTodayAdapter(activity.getSupportFragmentManager(), (ArrayList<Mon>) singleMessage.getListMon());
            ((HeaderViewHolder) holder).mPager.setAdapter(((HeaderViewHolder) holder).adapter);
            ((HeaderViewHolder) holder).mIndicator.setViewPager( ((HeaderViewHolder) holder).mPager);
        }
    }


    @Override
    public int getItemCount() {
        if (MessageList != null) {
            return MessageList.size();
        }
        return 0;
    }

    //
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager mPager;
        private MenuTodayAdapter adapter;
        private CirclePageIndicator mIndicator;


        public HeaderViewHolder(View v) {
            super(v);
            mPager = (ViewPager) v.findViewById(R.id.mPager);

            mIndicator = (CirclePageIndicator) v.findViewById(R.id.indicator);

        }
    }

}