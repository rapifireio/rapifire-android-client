package com.rapifire.rapifireclient.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ktomek on 20.10.2015.
 */
public class ViewWrapper<T, V extends View & ViewWrapper.Binder<T>>
        extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }

    public interface Binder<T> {
        void bind(T data);
    }
}
