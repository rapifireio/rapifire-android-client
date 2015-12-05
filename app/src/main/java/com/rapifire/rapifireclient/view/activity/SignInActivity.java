package com.rapifire.rapifireclient.view.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.rapifire.rapifireclient.R;
import com.rapifire.rapifireclient.RapidfireApp;
import com.rapifire.rapifireclient.di.components.SignInActivityComponent;
import com.rapifire.rapifireclient.di.module.SignInActivityModule;
import com.rapifire.rapifireclient.mvp.presenter.SignInPresenter;
import com.rapifire.rapifireclient.mvp.view.SigninView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ktomek on 05.12.15.
 */
public class SignInActivity extends AppCompatActivity implements SigninView {

    @Inject
    SignInPresenter signinPresenter;

    @Bind(R.id.username_edit_text)
    EditText userNameEditText;

    @Bind(R.id.password_edit_text)
    EditText passwordEditText;

    @Bind(R.id.signin_button)
    Button signinButton;

    @Bind(R.id.login_progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        signinPresenter.subscribe(this);
    }


    @Override
    public void onDestroy() {
        signinPresenter.unsubscribe(this);
        super.onDestroy();
    }

    protected void setupActivityComponent() {
        final SignInActivityComponent signInActivityComponent = RapidfireApp.get(this)
                .getAppComponent()
                .plus(new SignInActivityModule(this));
        signInActivityComponent.inject(this);
    }

    @Override
    public String getUsername() {
        return userNameEditText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void navigateToThings() {
        startActivity(new Intent(this, ThingsActivity.class));
    }

    @Override
    public void showProgress(boolean show) {
        progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setTitle(getString(R.string.warning));
        final AlertDialog dialog = builder.create();
        dialog.show();
    }

    @OnClick(R.id.signin_button)
    public void signin() {
        signinPresenter.signin();
    }
}

