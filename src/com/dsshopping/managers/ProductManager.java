package com.dsshopping.managers;

import java.util.ArrayList;
import java.util.List;

import com.dsshopping.model.ProductVO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ProductManager {
	private static String TABLE_NAME = "product";

	private SQLiteDatabase db;

	public ProductManager(SQLiteDatabase db) {
		this.db = db;
	}

	public List<ProductVO> getProducts(long listIndex) {
		List<ProductVO> result = new ArrayList<ProductVO>();

		Cursor c = db.query(TABLE_NAME, null, "list_id = " + listIndex, null, null,
				null, "name");

		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex("id");
			int nameIndex = c.getColumnIndex("name");
			int descriptionIndex = c.getColumnIndex("description");
			int priceIndex = c.getColumnIndex("price");
			int amountIndex = c.getColumnIndex("amount");
			int purchasedIndex = c.getColumnIndex("purchased");
			int listIdIndex = c.getColumnIndex("list_id");

			do {

				ProductVO productVO = new ProductVO(c.getLong(idIndex),
						c.getFloat(priceIndex), c.getFloat(amountIndex),
						c.getInt(purchasedIndex),
						c.getString(descriptionIndex), c.getLong(listIdIndex),
						c.getString(nameIndex));

				result.add(productVO);
			} while (c.moveToNext());
		}

		return result;
	}

	public long insert(String name, long listID, String description) {
		ContentValues cv = new ContentValues();

		cv.put("name", name);
		cv.put("list_id", listID);
		cv.put("description", description);

		return db.insert(TABLE_NAME, null, cv);
	}

	public void delete(long id) {
		db.delete(TABLE_NAME, "id = " + id, null);
	}

	public void purchase(long id, int purchase) {
		ContentValues cv = new ContentValues();

		cv.put("purchased", purchase);

		db.update(TABLE_NAME, cv, "id = " + id, null);
	}

	public void deleteProducts(long id) {
		db.delete(TABLE_NAME, "list_id = " + id, null);
	}
}
