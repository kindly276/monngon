package com.kindly.monngon.handler;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;

import com.kindly.monngon.R;
import com.kindly.monngon.activity.DetailMonActivity;
import com.kindly.monngon.activity.ListMonActivity;
import com.kindly.monngon.model.Mon;
import com.kindly.monngon.util.Constants;

import static android.R.attr.id;

/**
 * Created by PC0353 on 11/21/2016.
 */

public class Hander {
    /**
     * Kich tu item danh sach nguyen lieu,dipnau
     * @param view
     * @param id
     * @param name
     */
    public void onCategoryClick(View view, String id, String name){
        Log.e("thaond","task");

        Context context = view.getContext();
        Intent intent = new Intent(context, ListMonActivity.class);
        intent.putExtra(Constants.NAME, name);
        intent.putExtra(Constants.ID, id);



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            context.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation( ((Activity) context)).toBundle());
        }else{
            ((Activity) context).startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.anim_in, R.anim.anim_out);


        }
    }
    public void onDetailMonClick(View view, Mon mon){
        Context context = view.getContext();
        Intent intent = new Intent(context, DetailMonActivity.class);
        intent.putExtra(Constants.DATA, mon);
        intent.putExtra(Constants.ID, id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            context.startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation( ((Activity) context)).toBundle());
        }else{
            ((Activity) context).startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.anim_in, R.anim.anim_out);


        }
    }
}
