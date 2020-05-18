package com.lucas.ebo.ui.base.binding;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;

/**
 * Created by lucas
 * Date: 2020/5/15 14:17
 */
public class CommonBindingAdapter {

    @BindingAdapter(value = {"visible"}, requireAll = false)
    public static void visible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter(value = "textColor", requireAll = false)
    public static void setTextColor(TextView textView, int textColorRes)
    {
        textView.setTextColor(textView.getResources().getColor(textColorRes));
    }

    @BindingAdapter(value = "textChange", requireAll = false)
    public static void addTextChangedListener(EditText editText, OnTextChangedListener listener)
    {
        if (listener != null)
        {
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    listener.onTextChanged(s.toString());
                }
            });


        }

    }

}
