package com.kindly.monngon.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kindly.monngon.BR;
import com.kindly.monngon.R;
import com.kindly.monngon.activity.MainActivity;
import com.kindly.monngon.handler.Hander;
import com.kindly.monngon.model.DayCooking;
import com.thaond.library.util.Utils;

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
                R.layout.item_list_daycooking, parent, false);

        vh = new HeaderViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond", "onBindViewHolder ");

        if (holder instanceof HeaderViewHolder) {
            Utils.logE("thaond", "HeaderViewHolder");
            DayCooking singleMessage = MessageList.get(position);
            ((HeaderViewHolder) holder).getBinding().setVariable(BR.daycooking, singleMessage);
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
