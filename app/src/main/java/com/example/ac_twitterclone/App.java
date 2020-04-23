package com.example.ac_twitterclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("h0TyyRLz28A41y1R37k8esCYX6CtW4C5loz7NA3H")
                // if defined
                .clientKey("q1erGAUzf3wZ8rpK05C5s8Lq2Nb1VFdyu0PfBnqg")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}