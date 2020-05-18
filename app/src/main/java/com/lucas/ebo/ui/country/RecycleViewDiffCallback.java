package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by lucas
 * Date: 2020/5/16 12:20
 */
public class RecycleViewDiffCallback extends DiffUtil.ItemCallback<QuickMultipleEntity> {

    /**
     * 判断是否是同一个item
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areItemsTheSame(@NonNull QuickMultipleEntity oldItem, @NonNull QuickMultipleEntity newItem) {
        return oldItem.getPinyin().equals(newItem.getPinyin());
    }

    /**
     * 当是同一个item时，再判断内容是否发生改变
     *
     * @param oldItem New data
     * @param newItem old Data
     * @return
     */
    @Override
    public boolean areContentsTheSame(@NonNull QuickMultipleEntity oldItem, @NonNull QuickMultipleEntity newItem) {
        return false;
    }
}
