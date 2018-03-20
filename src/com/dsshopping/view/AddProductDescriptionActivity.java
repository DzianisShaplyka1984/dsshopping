package com.dsshopping.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dsshopping.R;
import com.dsshopping.adapter.CategorySpinnerAdapter;
import com.dsshopping.managers.CategoriesManager;
import com.dsshopping.managers.DBManager;
import com.dsshopping.managers.ProductDescriptionsManager;
import com.dsshopping.model.CategoryVO;

public class AddProductDescriptionActivity extends Activity implements
		OnClickListener {

	private ProductDescriptionsManager productDescriptionsManager;

	private CategoriesManager categoriesManager;

	private CategorySpinnerAdapter adapter;

	private Spinner productDescriptionSpinner;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Initialize content view
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_add_product_description);

		productDescriptionsManager = DBManager.getInstance(this)
				.getProductDescriptionsManager();

		categoriesManager = DBManager.getInstance(this).getCategoriesManager();

		Button btSaveProductDescription = (Button) findViewById(R.id.btSaveProductDescription);
		btSaveProductDescription.setOnClickListener(this);

		Button btCancelProductDescription = (Button) findViewById(R.id.btCancelProductDescription);
		btCancelProductDescription.setOnClickListener(this);

		adapter = new CategorySpinnerAdapter(this);

		List<CategoryVO> categoryList = categoriesManager.getAll();

		adapter.setData(categoryList);

		productDescriptionSpinner = (Spinner) findViewById(R.id.productDescriptionSpinner);

		productDescriptionSpinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btSaveProductDescription:
			EditText txName = (EditText) findViewById(R.id.addProductDescriptionText);
			String productName = txName.getText().toString();
			
			if (!productName.trim().equals("")) {
				productDescriptionsManager.insert(productName, "",
						((CategoryVO) productDescriptionSpinner.getSelectedItem())
								.getId());
			} else {
				Toast.makeText(
						v.getContext(),
						getString(R.string.message_add_product),
						Toast.LENGTH_SHORT).show();
				break;
			}
		case R.id.btCancelProductDescription:
			finish();
			break;
		default:
			break;
		}

	}
}
