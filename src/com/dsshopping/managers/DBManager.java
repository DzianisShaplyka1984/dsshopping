package com.dsshopping.managers;

import com.dsshopping.helpers.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
	private static DBManager instance;
	private ListManager listManager;
	private CategoriesManager categoriesManager;
	private ProductDescriptionsManager productDescriptionsManager;
	private ProductManager productManager;
	
	public static DBManager getInstance(Context context) {
		
		return instance != null ? instance : context != null ? new DBManager(context) : null;
	}
	
	private DBManager(Context context) {
		DBHelper dbHelper = new DBHelper(context);
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		listManager = new ListManager(db);
		categoriesManager = new CategoriesManager(db);
		productDescriptionsManager = new ProductDescriptionsManager(db);
		productManager = new ProductManager(db);
	}
	
	public ListManager getListManager() {
		return listManager;
	}
	
	public CategoriesManager getCategoriesManager() {
		return categoriesManager;
	}
	
	public ProductDescriptionsManager getProductDescriptionsManager() {
		return productDescriptionsManager;
	}
	
	public ProductManager getProductManager() {
		return productManager;
	}
}
