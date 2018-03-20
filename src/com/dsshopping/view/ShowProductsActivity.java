package com.dsshopping.view;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.dsshopping.R;
import com.dsshopping.adapter.ProductsExpandableListAdapter;
import com.dsshopping.managers.CategoriesManager;
import com.dsshopping.managers.DBManager;
import com.dsshopping.managers.ListManager;
import com.dsshopping.managers.ProductDescriptionsManager;
import com.dsshopping.managers.ProductManager;
import com.dsshopping.model.CategoryVO;
import com.dsshopping.model.ListVO;
import com.dsshopping.model.ProductDescriptionVO;

public class ShowProductsActivity extends Activity implements OnClickListener {

	private ListManager listManager;
	private ListVO listItem;
	private CategoriesManager categoriesManager;
	private ProductDescriptionsManager productDescriptionsManager;
	private ProductManager productManager;
	private ExpandableListView productList;
	private ProductsExpandableListAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_show_products);

		categoriesManager = DBManager.getInstance(this).getCategoriesManager();
		productDescriptionsManager = DBManager.getInstance(this)
				.getProductDescriptionsManager();
		productManager = DBManager.getInstance(this).getProductManager();
		listManager = DBManager.getInstance(this).getListManager();

		Button btAddCategory = (Button) findViewById(R.id.btAddCategory);
		btAddCategory.setOnClickListener(this);

		Button btAddProductDescription = (Button) findViewById(R.id.btAddProductDescription);
		btAddProductDescription.setOnClickListener(this);

		productList = (ExpandableListView) findViewById(R.id.productsExpListView);

		adjustGroupIndicator();

		adapter = new ProductsExpandableListAdapter(this);

		productList
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {

						ProductDescriptionVO productDescriptionVO = adapter
								.getChild(groupPosition, childPosition);

						productManager.insert(productDescriptionVO.getName(),
								listItem.getId(), "");

						listItem.setTotal(listItem.getTotal() + 1);

						listManager.statistic(listItem.getId(),
								listItem.getTotal(), listItem.getPurchased());

						Toast.makeText(
								parent.getContext(),
								productDescriptionVO.getName()
										+ " "
										+ getString(R.string.message_add_product_list)
										+ " " + listItem.getName(),
								Toast.LENGTH_SHORT).show();

						return true;
					}
				});

		productList
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {
						int itemType = ExpandableListView
								.getPackedPositionType(id);

						if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
							int groupPosition = ExpandableListView
									.getPackedPositionGroup(id);
							int childPosition = ExpandableListView
									.getPackedPositionChild(id);

							ProductDescriptionVO productDescription = adapter
									.getChild(groupPosition, childPosition);

							productDescriptionsManager
									.delete(productDescription.getId());

							refreshListView();

							return true;
						} else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
							int groupPosition = ExpandableListView
									.getPackedPositionGroup(id);

							CategoryVO category = adapter
									.getGroup(groupPosition);

							productDescriptionsManager.deleteProducts(category
									.getId());
							categoriesManager.delete(category.getId());

							refreshListView();

							return true;
						}

						return false;
					}
				});

		Bundle extra = getIntent().getExtras();

		if (extra != null) {
			listItem = (ListVO) extra.get(ListVO.class.getName());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddCategory:
			Intent addCategoryIntent = new Intent(this,
					AddCategoryActivity.class);
			startActivity(addCategoryIntent);
			break;
		case R.id.btAddProductDescription:
			Intent addProductDescriptionIntent = new Intent(this,
					AddProductDescriptionActivity.class);
			startActivity(addProductDescriptionIntent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		refreshListView();
	}

	private List<CategoryVO> prepareData() {
		List<CategoryVO> categories = categoriesManager.getAll();

		List<ProductDescriptionVO> products = productDescriptionsManager
				.getAll();

		for (ProductDescriptionVO product : products) {
			for (CategoryVO category : categories) {
				if (product.getCategoryId() == category.getId()) {
					category.addProductDescription(product);
				}

			}
		}

		return categories;
	}

	private void adjustGroupIndicator() {
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		productList.setIndicatorBounds(width - GetDipsFromPixel(50), width
				- GetDipsFromPixel(10));
	}

	private int GetDipsFromPixel(float pixels) {
		float scale = getResources().getDisplayMetrics().density;
		return (int) (pixels * scale + 0.5f);
	}

	private void refreshListView() {
		List<CategoryVO> data = prepareData();

		adapter.setData(data);

		productList.setAdapter(adapter);
	}
}
