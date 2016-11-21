package com.kindly.monngon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thaond.library.util.Utils;
import com.kindly.monngon.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.model.DayCooking;

import java.util.List;

/**
 * Created by rau muong on 15/11/2016.
 */
public class ListDayCookingAdapter extends RecyclerView.Adapter {

    private List<DayCooking> MessageList;
    private MainActivity activity;

    public ListDayCookingAdapter(List<DayCooking> Messages, MainActivity activity) {
        this.MessageList = Messages;
        this.activity = activity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        Utils.logE("thaond", "onCreateViewHolder ");


        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list_common, parent, false);

        vh = new HeaderViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond", "onBindViewHolder ");

        if (holder instanceof HeaderViewHolder) {
            Utils.logE("thaond", "HeaderViewHolder");
            DayCooking singleMessage = MessageList.get(position);
            ((HeaderViewHolder) holder).txt_name_category.setText(singleMessage.getName_dipnau());
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
        private TextView txt_name_category;

        public HeaderViewHolder(View v) {
            super(v);
            txt_name_category = (TextView) v.findViewById(R.id.txt_name_category);


        }
    }

}