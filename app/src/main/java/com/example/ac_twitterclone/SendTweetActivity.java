package com.example.ac_twitterclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.media.MediaSync;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SendTweetActivity extends AppCompatActivity {

    private EditText edtSendTweet;
    private Button btnSendTweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_tweet);

        setTitle("Send Tweet!");

        edtSendTweet = findViewById(R.id.edtSendTweet);
        btnSendTweet = findViewById(R.id.btnSendTweet);

    }

    public void sendtweet (View view) {
        ParseObject parseObject = new ParseObject("MyTweet");
        parseObject.put("tweet",edtSendTweet.getText().toString());
        parseObject.put("user", ParseUser.getCurrentUser().toString());


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        parseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText(SendTweetActivity.this,
                            ParseUser.getCurrentUser().getUsername() +
                                    " 's tweet" + "(" + edtSendTweet.getText().toString() + ")" + " is saved !!!",
                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,true).show();
                }else {
                    FancyToast.makeText(SendTweetActivity.this,
                            e.getMessage(), Toast.LENGTH_SHORT,
                            FancyToast.ERROR,true).show();
                }progressDialog.dismiss();
            }
        });
    }
}

