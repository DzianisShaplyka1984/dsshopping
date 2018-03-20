package com.dsshopping.view;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dsshopping.R;
import com.dsshopping.adapter.ProductListItemAdapter;
import com.dsshopping.managers.DBManager;
import com.dsshopping.managers.ListManager;
import com.dsshopping.managers.ProductManager;
import com.dsshopping.model.ListVO;
import com.dsshopping.model.ProductVO;

public class ShowListActivity extends Activity implements OnClickListener {

	private ListManager listManager;
	private ProductManager productManager;
	private Button btAddProduct;
	private ListVO listItem;
	private ListView productList;
	private ProductListItemAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		this.getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		setContentView(R.layout.activity_show_list);

		listManager = DBManager.getInstance(this).getListManager();
		productManager = DBManager.getInstance(this).getProductManager();

		String listName = getString(R.string.label_new_list);

		Bundle extra = getIntent().getExtras();

		if (extra != null) {
			listItem = (ListVO) extra.get(ListVO.class.getName());

			listName = listItem.getName();
		} else {
			listItem = new ListVO();

			long id = listManager.insert(listName, "");

			listItem.setId(id);
			listItem.setName(listName);

		}

		EditText tv = (EditText) findViewById(R.id.showListActivityListName);
		tv.setText(listName);

		tv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId,
					KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_DONE) {
					listItem.setName(v.getText().toString());

					listManager.rename(listItem.getId(), listItem.getName());

				}

				return false;
			}

		});

		btAddProduct = (Button) findViewById(R.id.btAddProduct);
		btAddProduct.setOnClickListener(this);

		productList = (ListView) findViewById(R.id.showListActivityListView);

		adapter = new ProductListItemAdapter(this);

		productList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						ProductVO product = adapter.getItem(position);

						if (!product.isPurchased()) {
							product.setPurchased(true);

							productManager.purchase(product.getId(), 1);

							listItem.setPurchased(listItem.getPurchased() + 1);

							view.setBackgroundColor(Color.GRAY);
						} else {
							product.setPurchased(false);

							productManager.purchase(product.getId(), 0);

							listItem.setPurchased(listItem.getPurchased() - 1);

							view.setBackgroundColor(Color.WHITE);
						}

						listManager.statistic(listItem.getId(),
								listItem.getTotal(), listItem.getPurchased());
					}
				});

		productList
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						ProductVO product = adapter.getItem(position);

						listItem.setTotal(listItem.getTotal() - 1);
						listItem.setPurchased(product.isPurchased() ? listItem
								.getPurchased() - 1 : listItem.getPurchased());

						listManager.statistic(listItem.getId(),
								listItem.getTotal(), listItem.getPurchased());

						productManager.delete(product.getId());

						refreshListView();

						return true;
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

	@Override
	protected void onStart() {
		super.onStart();

		refreshListView();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddProduct:
			Intent showIntent = new Intent(this, ShowProductsActivity.class);
			showIntent.putExtra(ListVO.class.getName(), listItem);
			startActivity(showIntent);
			break;
		default:
			break;
		}

	}

	private void refreshListView() {
		listItem = listManager.getList(listItem.getId());
		
		List<ProductVO> data = productManager.getProducts(listItem.getId());

		adapter.setData(data);

		productList.setAdapter(adapter);
	}
}
