package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lucas.architecture.utils.PinyinUtil;

/**
 * Created by lucas
 * Date: 2020/4/30 15:39
 */
public class QuickMultipleEntity implements MultiItemEntity, PyEntity {

    public static final int LETTER = 1;
    public static final int COUNTRY_TEXT = 2;

    private int itemType;
    public String pinyin;

    private AdapterItemLetter itemLetter;
    private AdapterItemCountry itemCountry;


    public QuickMultipleEntity() {

    }

    public QuickMultipleEntity(int itemType, AdapterItemLetter adapterItemLetter, String pinyin) {
        this.itemType = itemType;
        this.itemLetter = adapterItemLetter;
        this.pinyin = pinyin;
    }

    public QuickMultipleEntity(int itemType, AdapterItemCountry adapterItemCountry, String pinyin) {
        this.itemType = itemType;
        this.itemCountry = adapterItemCountry;
        this.pinyin = pinyin;
    }

    public QuickMultipleEntity(int itemType, AdapterItemLetter adapterItemLetter, AdapterItemCountry adapterItemCountry) {
        this.itemType = itemType;
        this.itemLetter = adapterItemLetter;
        this.itemCountry = adapterItemCountry;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public AdapterItemLetter getItemLetter() {
        return itemLetter;
    }

    public void setItemLetter(AdapterItemLetter itemLetter) {
        this.itemLetter = itemLetter;
    }

    public AdapterItemCountry getItemCountry() {
        return itemCountry;
    }

    public void setItemCountry(AdapterItemCountry itemCountry) {
        this.itemCountry = itemCountry;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    @NonNull
    @Override
    public String getPinyin() {
        if (itemLetter != null) {
            return itemLetter.getLetter();
        }
        if (itemCountry != null) {
            return PinyinUtil.getPingYin(itemCountry.getCountryName());
        }
        return null;
    }

//    @Override
//    public boolean equals(@Nullable Object obj) {
//        return itemLetter.equals(obj);
//    }
//
//    @Override
//    public int hashCode() {
//        return itemLetter.hashCode();
//    }
}
