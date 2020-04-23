package com.example.ac_twitterclone;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmailLogIn, edtPasswordLogIn;
    private Button btnSignUpLogIn, btnLogInLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        setTitle("Log In");

        edtEmailLogIn = findViewById(R.id.edtEmailLogIn);
        edtPasswordLogIn = findViewById(R.id.edtPasswordLogIn);

        btnLogInLogIn = findViewById(R.id.btnLogInLogIn);
        btnSignUpLogIn = findViewById(R.id.btnSignUpLogIn);

        btnSignUpLogIn.setOnClickListener(this);
        btnLogInLogIn.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();

            transitionToTweeterUsers();
        }

        }


    private void transitionToTweeterUsers() {
        Intent intent = new Intent(LogInActivity.this, TwitterUsers.class);
        startActivity(intent);

        finish();
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogInLogIn:
                if (edtEmailLogIn.getText().toString().equals("") || edtPasswordLogIn.getText().toString().equals("")) {
                    FancyToast.makeText
                            (LogInActivity.this, "Email, Password is requierd!",
                                    FancyToast.LENGTH_SHORT, FancyToast.INFO, true).show();
                }
                ParseUser.logInInBackground(edtEmailLogIn.getText().toString(), edtPasswordLogIn.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e ==  null) {
                            FancyToast.makeText(LogInActivity.this, user.getUsername()
                                    + " is loged in", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                            transitionToTweeterUsers();

                        } else {
                            FancyToast.makeText(LogInActivity.this, e.getMessage(), FancyToast.LENGTH_SHORT, FancyToast.ERROR, true).show();

                        }

                    }
                });

                break;
                case R.id.btnSignUpLogIn:
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);

                break;

        }

    }
}