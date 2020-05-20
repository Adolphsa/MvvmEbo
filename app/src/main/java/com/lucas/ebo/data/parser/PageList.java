package com.lucas.ebo.data.parser;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/5/18 17:34
 */
public class PageList<T> {

    private int     curPage; //当前页数
    private int     pageCount; //总页数
    private int     total; //总条数
    private List<T> datas;

    public int getCurPage() {
        return curPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotal() {
        return total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
