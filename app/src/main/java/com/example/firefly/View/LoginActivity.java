package com.example.firefly.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firefly.R;
import com.example.firefly.ViewModel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginButton)
    Button button;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.userNameEditText)
    EditText userNameEditText;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpButton();
        loginViewModel = new LoginViewModel(getApplicationContext());
    }

    private void setUpButton() {
        button.setOnClickListener(v -> {
            String userName = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            loginViewModel.login(userName, password, this);
        });
    }

    public SingleObserver<Boolean> handleLoginResult() {
        return new SingleObserver<Boolean>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Boolean result) {
                handleLoginSuccess(result);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                handleLoginFailure(e);
            }
        };
    }

    public void handleLoginSuccess(Boolean loginSuccess) {
        if (loginSuccess) {
            Intent intent = new Intent(LoginActivity.this, GameActivity.class);
            LoginActivity.this.startActivity(intent);
        } else {
            String errorMsg = "Incorrect Credentials";
            Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_LONG).show();
        }
    }

    public void handleLoginFailure(Throwable e) {
        String errorMsg = "Failed To Login : " + e.getMessage();
        Toast.makeText(LoginActivity.this, errorMsg, Toast.LENGTH_LONG).show();
    }

}
