package com.example.ac_twitterclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtEmail,edtUserName,edtPassword;
    Button btnSignUp,btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sign Up");

        edtEmail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){
                    onClick(btnSignUp); }
                return false;
            }
        });

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(MainActivity.this);
        btnLogIn.setOnClickListener(MainActivity.this);

        if (ParseUser.getCurrentUser() != null){
            //ParseUser.getCurrentUser().logOut();
            transitionToTweeterUsers();
        }

        }

    private void transitionToTweeterUsers() {
        Intent intent = new Intent(MainActivity.this, TwitterUsers.class);
        startActivity(intent);

        finish();
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUp:
                if (edtEmail.getText().toString().equals("") ||
                        edtUserName.getText().toString().equals("")
                        || edtPassword.getText().toString().equals("")){
                    FancyToast.makeText
                        (MainActivity.this,"Email, UserName, Password is requierd!",
                                FancyToast.LENGTH_SHORT,FancyToast.INFO,true).show();

                }
                else {


                    final ParseUser appUser = new ParseUser();
                    appUser.setUsername(edtUserName.getText().toString());
                    appUser.setPassword(edtPassword.getText().toString());
                    appUser.setEmail(edtEmail.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("Signing up" + edtUserName.getText().toString());
                    progressDialog.show();

                    appUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            {
                                if (e == null) {
                                    FancyToast.makeText(MainActivity.this, appUser.getUsername()
                                            + " is signed up", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                                    transitionToTweeterUsers();

                                } else {
                                    FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();
                                }
                                progressDialog.dismiss();

                            }

                        }
                    });
                }




                break;

            case R.id.btnLogIn:
                Intent intent = new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);

                break;
        }

    }
}
