package com.kindly.monngon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thaond.library.util.Utils;
import com.kindly.monngon.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.model.Mon;

import java.util.List;

/**
 * Created by PC0353 on 11/18/2016.
 */

public class ListMonAdapter extends RecyclerView.Adapter {

    private List<Mon> MessageList;
    private MainActivity activity;

    public ListMonAdapter(List<Mon> Messages, MainActivity activity) {
        this.MessageList = Messages;
        this.activity = activity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        Utils.logE("thaond", "onCreateViewHolder ");


        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list_mon, parent, false);

        vh = new HeaderViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond", "onBindViewHolder ");

        if (holder instanceof ListMaterialAdapter.HeaderViewHolder) {
            Utils.logE("thaond", "HeaderViewHolder");
            Mon singleMessage = MessageList.get(position);
            ((HeaderViewHolder) holder).txt_title.setText(singleMessage.getTitle());
            ((HeaderViewHolder) holder).txt_des.setText(singleMessage.getDescription());
            Utils.loadImage(activity, ((HeaderViewHolder) holder).image_mon, singleMessage.getImage());
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
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_title, txt_des;
        private ImageView image_mon;

        public HeaderViewHolder(View v) {
            super(v);
            txt_title = (TextView) v.findViewById(R.id.txt_title);
            txt_des = (TextView) v.findViewById(R.id.txt_des);
            image_mon = (ImageView) v.findViewById(R.id.image_mon);

        }
    }

}