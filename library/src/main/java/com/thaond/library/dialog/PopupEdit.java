package com.thaond.library.dialog;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.thaond.library.R;


public class PopupEdit extends Dialog {

    private String title;
    private String hint;
    private boolean isPasswordMode;
    private EditText editText;

    // Declare listner
    private OnPopupEditListener listener;

    public PopupEdit(Context context, String title, String hint,
                     boolean isPasswordMode, OnPopupEditListener listener) {
        super(context);
        this.title = title;
        this.hint = hint;
        this.isPasswordMode = isPasswordMode;

        this.listener = listener;
    }

    public PopupEdit(Context context, int theme) {
        super(context, theme);
    }

    public String getText() {
        return editText.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // No Window Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.MyAnimation_Window;

        // Set Layout
        this.setContentView(R.layout.popup_edit);
        editText = (EditText) findViewById(R.id.edit_note);
        editText.setHint(this.hint);
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        editText.requestFocus();
        if (isPasswordMode)
            editText.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        TextView titleTv = (TextView) findViewById(R.id.tv_dialog_title);
        titleTv.setText(title);

        TextView ok = (TextView) findViewById(R.id.btn_dialog_note_ok);
        TextView cancel = (TextView) findViewById(R.id.btn_dialog_note_cancel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditOK();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditCancel();
            }
        });

    }

    public interface OnPopupEditListener {
        void onEditOK();

        void onEditCancel();
    }
}