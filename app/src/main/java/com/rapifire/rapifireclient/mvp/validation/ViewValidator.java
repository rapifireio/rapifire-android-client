package com.rapifire.rapifireclient.mvp.validation;


import com.rapifire.rapifireclient.mvp.view.MVPView;

/**
 * Created by ktomek on 05.12.15.
 */
public interface ViewValidator<T extends MVPView> {

    boolean validate(T view);
    String validationMessage(T view);
}
