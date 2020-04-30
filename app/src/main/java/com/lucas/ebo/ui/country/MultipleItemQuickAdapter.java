package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.lucas.ebo.R;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/4/30 15:28
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<QuickMultipleEntity, BaseViewHolder> {

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
                break;
            default:
                break;
        }
    }
}
