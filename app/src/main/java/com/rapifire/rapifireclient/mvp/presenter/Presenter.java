package com.rapifire.rapifireclient.mvp.presenter;


import com.rapifire.rapifireclient.mvp.view.MVPView;

/**
 * Created by ktomek on 05.12.15.
 */
public interface Presenter<T extends MVPView> {
    void subscribe(T view);
    void unsubscribe(T view);
}
