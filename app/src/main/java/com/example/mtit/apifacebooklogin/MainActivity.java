package com.example.mtit.apifacebooklogin;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class MainActivity extends FragmentActivity {

    LoginButton loginButton;
    TextView txtNotify;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);


        loginButton = (LoginButton) findViewById(R.id.login_button);
        txtNotify = (TextView) findViewById(R.id.textViewNotify);
        callbackManager = CallbackManager.Factory.create();


        loginButton.setReadPermissions("email");

        // If using in a fragment
//        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                txtNotify.setText("Login successful.\nUser ID: " + loginResult.getAccessToken().getUserId() + "\n" +
                        "LoginToken: " + loginResult.getAccessToken().getToken());

                Toast.makeText(MainActivity.this, txtNotify.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                // App code
                txtNotify.setText("Login cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                txtNotify.setText("Login error: " + exception);
            }
        });


//        LoginManager.getInstance().registerCallback(callbackManager,
//                new FacebookCallback<LoginResult>() {
//                    @Override
//                    public void onSuccess(LoginResult loginResult) {
//                        // App code
//                        txtNotify.setText("Login successful.\nUser ID: " + loginResult.getAccessToken().getUserId() + "\n" +
//                                "LoginToken: " + loginResult.getAccessToken().getToken());
//
//                    }
//
//                    @Override
//                    public void onCancel() {
//                        // App code
//                        txtNotify.setText("Login cancelled");
//
//                    }
//
//                    @Override
//                    public void onError(FacebookException exception) {
//                        // App code
//                        txtNotify.setText("Login error: " + exception);
//
//                    }
//                }
//        );
//
//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);
    }


}
