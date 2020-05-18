package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lucas.architecture.utils.LogUtils;
import com.lucas.ebo.R;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/4/30 15:28
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<QuickMultipleEntity, BaseViewHolder> {
    private static final String TAG = "MultipleItemQuickAdapte";

    public MultipleItemQuickAdapter(List<QuickMultipleEntity> data) {
        super(data);
        // 绑定 layout 对应的 type
        addItemType(QuickMultipleEntity.LETTER, R.layout.adapter_item_letter);
        addItemType(QuickMultipleEntity.COUNTRY_TEXT, R.layout.adapter_item_country);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, QuickMultipleEntity item) {
        // 根据返回的 type 分别设置数据
        switch (helper.getItemViewType()) {
            case QuickMultipleEntity.LETTER:
                helper.setText(R.id.item_text_letter, item.getItemLetter().getLetter());
                break;
            case QuickMultipleEntity.COUNTRY_TEXT:
                helper.setText(R.id.item_text_country_name, item.getItemCountry().getCountryName());
                if (item.getItemCountry().isSelect())
                {
                    //如果被选择
                    helper.setTextColorRes(R.id.item_text_country_name, R.color.country_select_color);
                }else {
                    helper.setTextColorRes(R.id.item_text_country_name, R.color.country_default_color);
                }
                break;
            default:
                break;
        }
    }
}
