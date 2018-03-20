package com.dsshopping.view;

import com.dsshopping.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class SettingsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.activity_settings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }
}
