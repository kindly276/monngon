package com.vccorp.monngon.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thaond.library.util.Utils;
import com.vccorp.monngon.MainActivity;
import com.vccorp.monngon.R;
import com.vccorp.monngon.model.HomeMenu;
import com.vccorp.monngon.model.Material;
import com.vccorp.monngon.model.Mon;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_MATERIAL = 1;

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
        return VIEW_MATERIAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        Utils.logE("thaond", "onCreateViewHolder ");

        if (viewType == VIEW_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_home_header, parent, false);

            vh = new HeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_home_list_material, parent, false);

            vh = new MaterialViewHolder(v);
        }


        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond", "onBindViewHolder ");
        HomeMenu homeMenu = (HomeMenu) MessageList.get(position);
        if (holder instanceof HeaderViewHolder) {
            Utils.logE("thaond", "HeaderViewHolder");
            ((HeaderViewHolder) holder).adapter = new MenuTodayAdapter(activity.getSupportFragmentManager(), (ArrayList<Mon>) homeMenu.getListMon());
            ((HeaderViewHolder) holder).mPager.setAdapter(((HeaderViewHolder) holder).adapter);
            ((HeaderViewHolder) holder).mIndicator.setViewPager(((HeaderViewHolder) holder).mPager);
        } else if (holder instanceof MaterialViewHolder) {
            List<Material> listMaterial = homeMenu.getListMaterial();
            ListMaterialAdapter adapter = new ListMaterialAdapter(listMaterial, activity);
            ((MaterialViewHolder) holder).recyclerMaterial.setAdapter(adapter);
        }
    }


    @Override
    public int getItemCount() {
        Utils.logE("thaond", "ItemCount");
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

    public class MaterialViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerMaterial;


        public MaterialViewHolder(View v) {
            super(v);
            recyclerMaterial = (RecyclerView) v.findViewById(R.id.recycler_material);
            recyclerMaterial.setLayoutManager(new GridLayoutManager(activity,2));
        }
    }

}