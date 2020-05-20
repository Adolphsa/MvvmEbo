package com.lucas.ebo.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.lucas.ebo.R;


public class CustomProgressDialog extends Dialog {

    private TextView tv_msg;

    public CustomProgressDialog(Context context, String content) {
        this(context, content, R.style.CustomProgressDialog);
    }

    private CustomProgressDialog(Context context, String content, int theme) {
        super(context, theme);
        this.setContentView(R.layout.layout_dialog_progress);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
        this.setCanceledOnTouchOutside(false);

//        tv_msg = (TextView) this.findViewById(R.id.tv_msg);
//        if(tv_msg != null){
//            tv_msg.setText(content);
//        }
    }

    public void setMessage(String content){
        if(tv_msg != null){
            tv_msg.setText(content);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if(!hasFocus){
            dismiss();
        }
    }
}
