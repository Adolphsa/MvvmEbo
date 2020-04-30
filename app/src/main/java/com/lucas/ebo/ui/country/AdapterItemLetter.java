package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by lucas
 * Date: 2020/4/30 15:54
 */
public class AdapterItemLetter implements MultiItemEntity{

    private String letter;

    public AdapterItemLetter()
    {

    }

    public AdapterItemLetter(String letter)
    {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public int getItemType() {
        return QuickMultipleEntity.LETTER;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AdapterItemLetter that = (AdapterItemLetter) obj;

        return letter.toLowerCase().equals(that.letter.toLowerCase());
    }

    @Override
    public int hashCode() { return letter.toLowerCase().hashCode(); }

}
