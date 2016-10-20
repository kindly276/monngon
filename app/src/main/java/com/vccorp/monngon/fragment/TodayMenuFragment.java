package com.vccorp.monngon.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thaond.library.util.Utils;
import com.vccorp.monngon.R;
import com.vccorp.monngon.model.Mon;
import com.vccorp.monngon.util.Constants;


/**
 * Created by PC0353 on 10/5/2016.
 */

public class TodayMenuFragment extends Fragment {
    private View mView;
    private TextView txtTitle;
    private ImageView imageMenu;
    private Activity activity;
    public static TodayMenuFragment newInstance(Mon mon) {
        TodayMenuFragment myFragment = new TodayMenuFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable(Constants.DATA,mon);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_today_menu,container,false);
        activity=getActivity();
        txtTitle=(TextView)mView.findViewById(R.id.txt_title_menu);
        imageMenu=(ImageView)mView.findViewById(R.id.image_today_menu);
        try {
            Mon mon=getArguments().getParcelable(Constants.DATA);
            if(mon!=null){
                txtTitle.setText(mon.getTitle());
                Utils.loadImage(activity,imageMenu,mon.getImage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mView;
    }
}
