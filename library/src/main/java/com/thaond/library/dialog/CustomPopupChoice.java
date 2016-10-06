package com.thaond.library.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.thaond.library.R;


public class CustomPopupChoice extends Dialog {

    private Context context;
    private ListView listview;
    private String title;
    private String[] items;
    private OnItemClickListener listener;

    public CustomPopupChoice(Context context, String title, String[] items, OnItemClickListener listener) {
        super(context);
        this.context = context;
        this.title = title;
        this.items = items;
        this.listener = listener;
    }


    public CustomPopupChoice(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // No Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.MyAnimation_Window;
        // Set Layout
        this.setContentView(R.layout.popup_choice);
        // Set title
        TextView titleHeader = (TextView) findViewById(R.id.popup_choice_title);
        titleHeader.setText(title);
        // Load ListView
        listview = (ListView) findViewById(R.id.popup_choice_list);

        List<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < items.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("item", items[i]);
            listData.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(context, listData,
                R.layout.popup_choice_ele, new String[]{"item"},
                new int[]{R.id.popup_choice_ele_text});

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemClickListener(position);
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClickListener(int position);
    }
}