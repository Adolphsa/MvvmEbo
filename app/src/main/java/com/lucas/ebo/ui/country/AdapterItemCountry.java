package com.lucas.ebo.ui.country;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lucas.architecture.utils.PinyinUtil;

/**
 * Created by lucas
 * Date: 2020/4/29 15:19
 */
public class AdapterItemCountry implements MultiItemEntity{

    /**
     * 国家名称  中国 美国
     */
    private String countryName;

    /**
     * 国家区号  +86
     */
    private int countryNumber;

    /**
     * 国家编码 CN
     */
    private String countrySimpleName;

    public String pinyin;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryNumber() {
        return countryNumber;
    }

    public void setCountryNumber(int countryNumber) {
        this.countryNumber = countryNumber;
    }

    public String getCountrySimpleName() {
        return countrySimpleName;
    }

    public void setCountrySimpleName(String countrySimpleName) {
        this.countrySimpleName = countrySimpleName;
    }

    @Override
    public int getItemType() {
        return QuickMultipleEntity.COUNTRY_TEXT;
    }

}
