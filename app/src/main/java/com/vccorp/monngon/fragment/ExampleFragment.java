package com.vccorp.monngon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vccorp.monngon.R;

/**
 * Created by PC0353 on 11/8/2016.
 */

public class ExampleFragment extends Fragment {
    private View mView;
    public static ExampleFragment newInstance() {
        ExampleFragment myFragment = new ExampleFragment();

        return myFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_example, container, false);
        return mView;
    }
}
