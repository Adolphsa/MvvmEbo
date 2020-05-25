package com.lucas.ebo.ui.base.binding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lucas.architecture.ui.adapter.BaseBindingAdapter;

import java.util.List;

/**
 * Created by lucas
 * Date: 2020/4/29 14:25
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"setSpanCount"})
    public static void setSpanCount(RecyclerView recyclerView, int spanCount) {
        if (recyclerView != null) {
            if (recyclerView.getLayoutManager() == null || !(recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), spanCount));
            } else {
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanCount(spanCount);
                }
            }
        }
    }

    @BindingAdapter(value = {"adapter", "refreshList"}, requireAll = false)
    public static void bindList(RecyclerView recyclerView, RecyclerView.Adapter adapter, List list) {
        if (recyclerView != null && list != null) {
            if (recyclerView.getAdapter() == null) {
                recyclerView.setAdapter(adapter);
            }

            ((BaseBindingAdapter) recyclerView.getAdapter()).setList(list);

            //TODO 此处可通过 diffUtil 进一步优化用户体验
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

}
