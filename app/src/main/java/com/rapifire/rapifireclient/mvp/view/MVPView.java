package com.rapifire.rapifireclient.mvp.view;

/**
 * Created by ktomek on 05.12.15.
 */
public interface MVPView {
    void showProgress(boolean show);

    void showMessage(String message);

    void showMessage(int stringId);
}
