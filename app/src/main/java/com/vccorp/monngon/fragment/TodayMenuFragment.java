package com.vccorp.monngon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vccorp.monngon.R;


/**
 * Created by PC0353 on 10/5/2016.
 */

public class TodayMenuFragment extends Fragment {
    private View mView;
    private TextView txtTitle;
    private ImageView imageMenu;
    public static TodayMenuFragment newInstance() {
        TodayMenuFragment myFragment = new TodayMenuFragment();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_today_menu,container,false);
        txtTitle=(TextView)mView.findViewById(R.id.txt_title_menu);
        imageMenu=(ImageView)mView.findViewById(R.id.image_today_menu);
        return mView;
    }
}
