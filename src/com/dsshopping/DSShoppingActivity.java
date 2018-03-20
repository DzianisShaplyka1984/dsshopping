package com.dsshopping;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.dsshopping.adapter.ShoppingListAdapter;
import com.dsshopping.managers.DBManager;
import com.dsshopping.managers.ListManager;
import com.dsshopping.managers.ProductManager;
import com.dsshopping.model.ListVO;
import com.dsshopping.view.AboutActivity;
import com.dsshopping.view.HelpActivity;
import com.dsshopping.view.SettingsActivity;
import com.dsshopping.view.ShowListActivity;

public class DSShoppingActivity extends Activity implements OnClickListener {

	private ListManager listManager;
	private ProductManager productManager;
	private ListView shoppingList;
	private ShoppingListAdapter adapter;
	private Button btAddList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_dsshopping);

		listManager = DBManager.getInstance(this).getListManager();
		productManager = DBManager.getInstance(this).getProductManager();

		adapter = new ShoppingListAdapter(this);

		shoppingList = (ListView) findViewById(R.id.shoppingListView);

		shoppingList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {

						Intent intent = new Intent(view.getContext(),
								ShowListActivity.class);
						intent.putExtra(ListVO.class.getName(),
								adapter.getItem(position));
						startActivity(intent);
					}
				});

		shoppingList
				.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
					public boolean onItemLongClick(AdapterView<?> parent,
							View view, int position, long id) {

						ListVO listVO = adapter.getItem(position);

						productManager.deleteProducts(listVO.getId());
						listManager.delete(listVO.getId());

						refreshListView();

						return true;
					}
				});

		btAddList = (Button) findViewById(R.id.btAddList);
		btAddList.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		refreshListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_dsshopping, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent settingsIntent = new Intent(this, SettingsActivity.class);
			startActivity(settingsIntent);
			break;
		case R.id.menu_about:
			Intent aboutIntent = new Intent(this, AboutActivity.class);
			startActivity(aboutIntent);
			break;
		case R.id.menu_help:
			Intent helpIntent = new Intent(this, HelpActivity.class);
			startActivity(helpIntent);
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btAddList:
			Intent showIntent = new Intent(this, ShowListActivity.class);
			startActivity(showIntent);
			break;
		default:
			break;
		}
	}

	private void refreshListView() {
		List<ListVO> shoppingListData = listManager.getAll();

		adapter.setData(shoppingListData);

		shoppingList.setAdapter(adapter);
	}
}
