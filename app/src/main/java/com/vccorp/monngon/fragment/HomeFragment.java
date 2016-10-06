package com.vccorp.monngon.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.thaond.library.util.GsonRequest;
import com.thaond.library.util.Utils;
import com.vccorp.monngon.R;
import com.vccorp.monngon.model.ListMenu;
import com.vccorp.monngon.util.UrlHelper;

/**
 * Created by PC0353 on 10/5/2016.
 */

public class HomeFragment extends Fragment {
    private View mView;
    private Activity activity;
    public static HomeFragment newInstance() {
        HomeFragment myFragment = new HomeFragment();
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        activity=getActivity();
        RequestQueue queue = Utils.getQueue(activity);

        GsonRequest<ListMenu> postRequest = new GsonRequest<ListMenu>(
                Request.Method.GET, UrlHelper.UrlCommon, ListMenu.class, null, null,
                new Response.Listener<ListMenu>() {
                    public void onResponse(ListMenu response) {
                        
                    }
                }, new Response.ErrorListener(

        ) {
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(postRequest);
        return mView;
    }
}
