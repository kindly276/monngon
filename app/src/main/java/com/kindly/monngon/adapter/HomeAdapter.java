package com.kindly.monngon.adapter;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thaond.library.util.Utils;
import com.kindly.monngon.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.model.Cooking;
import com.kindly.monngon.model.HomeMenu;
import com.kindly.monngon.model.Material;
import com.kindly.monngon.model.Mon;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    public static final int VIEW_HEADER = 0;
    public static final int VIEW_MATERIAL = 1;
    public static final int VIEW_COOKING_TYPE = 2;

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
        } else if (MessageList.get(position).getType() == 1) {
            return VIEW_HEADER;
        }
        return VIEW_COOKING_TYPE;
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
        } else if (viewType == VIEW_COOKING_TYPE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_home_list_cooking_type, parent, false);

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
        } else if (holder instanceof TypeCookingViewHolder) {
            List<Cooking> cookings = homeMenu.getCachnaus();
            ListCookingTypeAdapter adapter = new ListCookingTypeAdapter(cookings, activity);
            ((TypeCookingViewHolder) holder).recyclerCooking.setAdapter(adapter);
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
            recyclerMaterial.setLayoutManager(new GridLayoutManager(activity, 2));
        }
    }

    public class TypeCookingViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerCooking;


        public TypeCookingViewHolder(View v) {
            super(v);
            recyclerCooking = (RecyclerView) v.findViewById(R.id.recycler_cooking);
            recyclerCooking.setLayoutManager(new GridLayoutManager(activity, 2));
        }
    }

}