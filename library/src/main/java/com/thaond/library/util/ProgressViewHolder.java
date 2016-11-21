package com.thaond.library.util;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.thaond.library.R;


/**
 * Created by Nam on 12/16/2015.
 */
public class ProgressViewHolder extends RecyclerView.ViewHolder{

    public ProgressBar progressBar;

    public ProgressViewHolder(View v) {
        super(v);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
    }


    public static void bind(ProgressViewHolder viewHolder){
        viewHolder.progressBar.setIndeterminate(true);
    }

}
