package com.thaond.library.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.thaond.library.R;


public class PopupConfirm extends Dialog {

    private String des;

    private Context context;
    private String titlePopup;
    // Declare listener
    private OnPopupConfirmListener listener;

    public PopupConfirm(Context context, String des,String title, OnPopupConfirmListener listener) {
        super(context);
        this.context = context;
        this.des = des;
        this.listener = listener;
        this.titlePopup =title;
    }

    public PopupConfirm(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // No Window Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.MyAnimation_Window;

        // Set Layout
        this.setContentView(R.layout.popup_confirm);
        //
        TextView title = (TextView) findViewById(R.id.tv_dialog_title);
        title.setText(titlePopup);
        //
        TextView text = (TextView) findViewById(R.id.tv_dialog_content);
        text.setText(des);

        TextView ok = (TextView) findViewById(R.id.btn_dialog_note_ok);
        TextView cancel = (TextView) findViewById(R.id.btn_dialog_note_cancel);

        // Set on clicked
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onConfirmOK();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onConfirmCancel();
            }
        });

    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public interface OnPopupConfirmListener {
        void onConfirmOK();

        void onConfirmCancel();
    }
}