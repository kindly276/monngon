package com.kindly.monngon.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kindly.monngon.R;
import com.kindly.monngon.activity.MainActivity;
import com.kindly.monngon.model.HomeMenu;
import com.kindly.monngon.model.Mon;
import com.thaond.library.util.SpacesItemDecoration;
import com.thaond.library.util.Utils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_MOST = 1;

    private List<HomeMenu> MessageList;
    private MainActivity activity;

    public HomeAdapter(List<HomeMenu> Messages, MainActivity activity) {
        this.MessageList = Messages;
        this.activity = activity;

    }

    @Override
    public int getItemViewType(int position) {
        if (MessageList.get(position).getType() == 0) {
            return VIEW_HEADER;
        } else{
            return VIEW_MOST;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;

        if (viewType == VIEW_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_home_header, parent, false);

            vh = new HeaderViewHolder(v);
            Utils.logE("thaond","run1");
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_home_list_mon, parent, false);

            vh = new MostestViewViewHolder(v);
            Utils.logE("thaond","run2");

        }


        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            HomeMenu homeMenu = (HomeMenu) MessageList.get(position);
            if (holder instanceof HeaderViewHolder) {
                ((HeaderViewHolder) holder).adapter = new MenuTodayAdapter(activity.getSupportFragmentManager(), (ArrayList<Mon>) homeMenu.getListMon());
                ((HeaderViewHolder) holder).mPager.setAdapter(((HeaderViewHolder) holder).adapter);
                ((HeaderViewHolder) holder).mIndicator.setViewPager(((HeaderViewHolder) holder).mPager);
            } else if (holder instanceof MostestViewViewHolder) {
                List<Mon> listMon = homeMenu.getListMonMost();
                ListMonAdapter adapter = new ListMonAdapter(listMon, activity);
                ((MostestViewViewHolder) holder).recyclerMostView.setAdapter(adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        private ViewPager mPager;
        private MenuTodayAdapter adapter;
        private CirclePageIndicator mIndicator;


        public HeaderViewHolder(View v) {
            super(v);
            mPager = (ViewPager) v.findViewById(R.id.mPager);

            mIndicator = (CirclePageIndicator) v.findViewById(R.id.indicator);

        }
    }  //

    public class MostestViewViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerMostView;


        public MostestViewViewHolder(View v) {
            super(v);
            recyclerMostView = (RecyclerView) v.findViewById(R.id.rv_mon);
            StaggeredGridLayoutManager mLayoutManager = new StaggeredGridLayoutManager(2,1);
            mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            // use a linear layout manager
            recyclerMostView.setLayoutManager(mLayoutManager);
            recyclerMostView.addItemDecoration(new SpacesItemDecoration(Utils.getFixedHeight(activity,10)));;
        }
    }


}