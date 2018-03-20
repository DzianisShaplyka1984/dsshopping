package com.dsshopping.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;

import com.dsshopping.R;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_about);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
}
