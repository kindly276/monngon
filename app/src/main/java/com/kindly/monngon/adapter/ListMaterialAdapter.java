package com.kindly.monngon.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kindly.monngon.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.handler.Hander;
import com.kindly.monngon.model.Material;
import com.thaond.library.util.Utils;

import java.util.List;

/**
 * Created by PC0353 on 11/14/2016.
 */

public class ListMaterialAdapter extends RecyclerView.Adapter {

    private List<Material> MessageList;
    private MainActivity activity;

    public ListMaterialAdapter(List<Material> Messages, MainActivity activity) {
        this.MessageList = Messages;
        this.activity = activity;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        RecyclerView.ViewHolder vh;
        Utils.logE("thaond", "onCreateViewHolder ");


        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_list_material, parent, false);

        vh = new HeaderViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Utils.logE("thaond", "onBindViewHolder ");

        if (holder instanceof HeaderViewHolder) {
            Utils.logE("thaond", "HeaderViewHolder");
            Material singleMessage = MessageList.get(position);
            ((HeaderViewHolder) holder).getDataBinding().setVariable(com.kindly.monngon.BR.material,singleMessage);
            ((HeaderViewHolder) holder).getDataBinding().executePendingBindings();

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
        private ViewDataBinding dataBinding;

        
        public HeaderViewHolder(View v) {
            super(v);
            dataBinding = DataBindingUtil.bind(v);
            dataBinding.setVariable(com.kindly.monngon.BR.hander,new Hander());

//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(activity, ListMonActivity.class);
//                    activity.startActivity(intent);
//                }
//            });


        }

        public ViewDataBinding getDataBinding() {
            return dataBinding;
        }

    }
}