package com.dsshopping.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dsshopping.R;
import com.dsshopping.managers.CategoriesManager;
import com.dsshopping.managers.DBManager;

public class AddCategoryActivity extends Activity implements OnClickListener {

	private CategoriesManager categoriesManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_add_category);

		categoriesManager = DBManager.getInstance(this).getCategoriesManager();

		Button btSaveCategory = (Button) findViewById(R.id.btSaveCategory);
		btSaveCategory.setOnClickListener(this);

		Button btCancelCategory = (Button) findViewById(R.id.btCancelCategory);
		btCancelCategory.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSaveCategory:
			EditText txName = (EditText) findViewById(R.id.addCategoryText);
			String categoryName = txName.getText().toString();
			
			if (!categoryName.trim().equals("")) {
				categoriesManager.insert(categoryName, "");
			} else {
				Toast.makeText(
						v.getContext(),
						getString(R.string.message_add_category),
						Toast.LENGTH_SHORT).show();
				break;
			}
		case R.id.btCancelCategory:
			finish();
			break;
		default:
			break;
		}

	}
}
