package com.lucas.ebo.ui.country;

import android.content.Context;
import android.graphics.Color;

import androidx.recyclerview.widget.RecyclerView;

import com.lucas.architecture.ui.adapter.SimpleBaseBindingAdapter;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.R;
import com.lucas.ebo.databinding.AdapterItemCountryBinding;
import com.lucas.ebo.generated.callback.OnClickListener;

/**
 * Created by lucas
 * Date: 2020/4/29 15:00
 */
public class CountryListAdapter extends SimpleBaseBindingAdapter<AdapterItemCountry, AdapterItemCountryBinding> {
    private static final String TAG = "CountryListAdapter";

    public CountryListAdapter(Context context) {
        super(context, R.layout.adapter_item_country);
    }

    @Override
    protected void onSimpleBindItem(AdapterItemCountryBinding binding, AdapterItemCountry item, RecyclerView.ViewHolder holder) {
        binding.setItemCountry(item);
        binding.getRoot().setOnClickListener(v -> {
            LogUtils.d(TAG, "点击了" + holder.getAdapterPosition());
            binding.itemTextCountryName.setTextColor(Color.BLUE);
//            notifyItemChanged();
        });
    }
}

