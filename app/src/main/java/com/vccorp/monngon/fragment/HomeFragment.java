package com.vccorp.monngon.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.thaond.library.util.GsonRequest;
import com.thaond.library.util.Utils;
import com.vccorp.monngon.R;
import com.vccorp.monngon.model.ListMon;
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
        activity = getActivity();
        Utils.logE("thaond","abc");
        RequestQueue queue = Utils.getQueue(activity);

        GsonRequest<ListMon> postRequest = new GsonRequest<ListMon>(
                Request.Method.GET, UrlHelper.UrlCommon, ListMon.class, null, null,
                new Response.Listener<ListMon>() {
                    public void onResponse(ListMon response) {
                        Utils.logE("thaond","ListMon"+response.toString());

                    }
                }, new Response.ErrorListener(

        ) {
            public void onErrorResponse(VolleyError error) {
                Utils.logE("thaond","Exception"+error.toString());

            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

        return mView;
    }
}
