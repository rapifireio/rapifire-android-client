package com.rapifire.rapifireclient.mvp.view;

/**
 * Created by ktomek on 05.12.15.
 */
public interface SigninView extends MVPView {
    String getUsername();

    String getPassword();

    void navigateToThings();
}
