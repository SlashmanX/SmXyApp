package com.slashmanx.smxyApp;

import android.app.Activity;
import android.os.Bundle;
import com.phonegap.*;
import com.slashmanx.smxyApp.R; 

public class App extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/index.html");
    }
}