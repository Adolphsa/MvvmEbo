package com.lucas.ebo.ui.country;

import com.chad.library.adapter.base.entity.MultiItemEntity;

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

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect;


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
