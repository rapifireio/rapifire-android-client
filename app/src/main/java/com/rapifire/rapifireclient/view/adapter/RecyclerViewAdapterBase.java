package com.rapifire.rapifireclient.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by ktomek on 20.10.2015.
 */
public abstract class RecyclerViewAdapterBase<T, V extends View & ViewWrapper.Binder<T>>
        extends RecyclerView.Adapter<ViewWrapper<T, V>> {

    protected List<T> items = new ArrayList<>();

    @Override
    public final ViewWrapper<T, V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);

    @Override
    public final void onBindViewHolder(ViewWrapper<T, V> viewHolder, int position) {
        V view = viewHolder.getView();
        T data = items.get(position);
        view.bind(data);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(Collection<T> collection) {
        items.clear();
        items.addAll(collection);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
    }
}
