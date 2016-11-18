package com.kindly.monngon.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thaond.library.util.Utils;
import com.kindly.monngon.MainActivity;
import com.kindly.monngon.R;
import com.kindly.monngon.activity.DetailMonActivity;
import com.kindly.monngon.model.Mon;
import com.kindly.monngon.util.Constants;


/**
 * Created by PC0353 on 10/5/2016.
 */

public class TodayMenuFragment extends Fragment {
    private View mView;
    private TextView txtTitle;
    private ImageView imageMenu;
    private MainActivity activity;
    private Mon mon;

    public static TodayMenuFragment newInstance(Mon mon) {
        TodayMenuFragment myFragment = new TodayMenuFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.DATA, mon);
        myFragment.setArguments(bundle);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_today_menu, container, false);
        activity = (MainActivity) getActivity();
        txtTitle = (TextView) mView.findViewById(R.id.txt_title_menu);
        imageMenu = (ImageView) mView.findViewById(R.id.image_today_menu);
        try {
            mon = getArguments().getParcelable(Constants.DATA);
            if (mon != null) {
                txtTitle.setText(mon.getTitle());
                Utils.loadImage(activity, imageMenu, mon.getImage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, DetailMonActivity.class);
                intent.putExtra(Constants.DATA,mon);
//                startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent,
                            ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
                }else{
                    startActivity(intent);

                }

            }
        });
        return mView;
    }

}
