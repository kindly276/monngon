package com.kindly.monngon.adapter;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kindly.monngon.R;
import com.kindly.monngon.handler.Hander;
import com.kindly.monngon.model.Mon;
import com.thaond.library.util.ProgressViewHolder;
import com.thaond.library.util.Utils;

import java.util.List;

/**
 * Created by PC0353 on 11/18/2016.
 */

public class ListMonAdapter extends RecyclerView.Adapter {

    private List<Mon> MessageList;
    private Activity activity;
    public static final int TYPE_PROGRESS = 0;
    public static final int TYPE_ITEM = 1;
    public static final int TYPE_BOTTOM = 2;

    @Override
    public int getItemViewType(int position) {
        if (MessageList.get(position).getType() == TYPE_PROGRESS) {
            return TYPE_PROGRESS;
        }else if (MessageList.get(position).getType() == TYPE_BOTTOM) {
            return TYPE_BOTTOM;
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

        if (holder instanceof HeaderViewHolder) {
            Mon mon = MessageList.get(position);
            Utils.logE("thaond", "image"+mon.getImage());

            ((HeaderViewHolder) holder).getBinding().setVariable(com.kindly.monngon.BR.mon, mon);
            ((HeaderViewHolder) holder).getBinding().executePendingBindings();

        }else{
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            layoutParams.setFullSpan(true);
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
        ViewDataBinding binding;

        public HeaderViewHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
            binding.setVariable(com.kindly.monngon.BR.hanlder,new Hander());
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }
    }

}