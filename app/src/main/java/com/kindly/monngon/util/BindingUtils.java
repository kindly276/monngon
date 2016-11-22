package com.kindly.monngon.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.kindly.monngon.R;
import com.squareup.picasso.Picasso;
import com.thaond.library.util.Utils;

/**
 * Created by PC0353 on 11/22/2016.
 */

public class BindingUtils {
    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        Utils.logE("thaond","loadimage");
        Picasso.with(view.getContext())
                .load(url).error(R.mipmap.ic_launcher)
                .into(view);
    }
}
