package com.vccorp.monngon.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vccorp.monngon.R;
import com.vccorp.monngon.model.HomeMenu;

import java.util.List;

/**
 * Created by PC0353 on 10/21/2016.
 */

public class HomeAdapter extends RecyclerView.Adapter {
    private final int VIEW_HEADER = 0;

    private List<HomeMenu> MessageList;

    public HomeAdapter(List<HomeMenu> Messages, RecyclerView recyclerView) {
        MessageList = Messages;
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

        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_home_header, parent, false);

        vh = new HeaderViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

            HomeMenu singleMessage = (HomeMenu) MessageList.get(position);

            ((HeaderViewHolder) holder).tvName.setText(singleMessage.getName());

            ((HeaderViewHolder) holder).tvEmailId.setText(singleMessage.getName());

            ((HeaderViewHolder) holder).Message = singleMessage;

        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
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
        public TextView tvName;

        public TextView tvEmailId;

        public Student Message;

        public HeaderViewHolder(View v) {
            super(v);
            tvName = (TextView) v.findViewById(R.id.tvName);

            tvEmailId = (TextView) v.findViewById(R.id.tvEmailId);

            v.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),
                            "OnClick :" + Message.getName() + " \n " + Message.getName(),
                            Toast.LENGTH_SHORT).show();

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(),
                            "OnLongClick :" + Message.getName() + " \n " + Message.getName(),
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }
}