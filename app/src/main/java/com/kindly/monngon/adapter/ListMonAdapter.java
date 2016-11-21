package com.kindly.monngon.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kindly.monngon.R;
import com.kindly.monngon.model.Mon;
import com.thaond.library.util.ProgressViewHolder;
import com.thaond.library.util.Utils;

import java.util.List;

import static android.databinding.tool.util.GenerationalClassUtil.ExtensionFilter.BR;

/**
 * Created by PC0353 on 11/18/2016.
 */

public class ListMonAdapter extends RecyclerView.Adapter {

    private List<Mon> MessageList;
    private Activity activity;
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    @Override
    public int getItemViewType(int position) {
        if (MessageList.get(position).getType() == TYPE_PROGRESS) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public ListMonAdapter(List<Mon> Messages, Activity activity) {
        this.MessageList = Messages;
        this.activity = activity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        Utils.logE("thaond", "onCreateViewHolder ");
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_list_mon, parent, false);

            vh = new HeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progress_item, parent, false);

            vh = new ProgressViewHolder(v);
        }


        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond", "onBindViewHolder ");

        if (holder instanceof HeaderViewHolder) {
            Utils.logE("thaond", "HeaderViewHolder");
            Mon mon = MessageList.get(position);
            ((HeaderViewHolder) holder).getBinding().setVariable(BR.mon, mon);
            ((HeaderViewHolder) holder).getBinding().executePendingBindings();

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
        ViewDataBinding binding;

        public HeaderViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

}