package com.dsshopping.managers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dsshopping.model.ProductDescriptionVO;

public class ProductDescriptionsManager {
	private static String TABLE_NAME = "productdescription";

	private SQLiteDatabase db;

	public ProductDescriptionsManager(SQLiteDatabase db) {
		this.db = db;
	}

	public List<ProductDescriptionVO> getAll() {
		List<ProductDescriptionVO> result = new ArrayList<ProductDescriptionVO>();

		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, "name");

		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex("id");
			int nameIndex = c.getColumnIndex("name");
			int descriptionIndex = c.getColumnIndex("description");
			int categoryIndex = c.getColumnIndex("category_id");

			do {

				ProductDescriptionVO productDescriptionVO = new ProductDescriptionVO(
						c.getLong(idIndex), c.getString(nameIndex),
						c.getString(descriptionIndex), c.getLong(categoryIndex));

				result.add(productDescriptionVO);
			} while (c.moveToNext());
		}

		return result;
	}

	public long insert(String name, String description, long categoryId) {
		ContentValues cv = new ContentValues();

		cv.put("name", name);
		cv.put("description", description);
		cv.put("category_id", categoryId);

		return db.insert(TABLE_NAME, null, cv);
	}

	public void delete(long id) {
		db.delete(TABLE_NAME, "id = " + id, null);
	}

	public void deleteProducts(long id) {
		db.delete(TABLE_NAME, "category_id = " + id, null);
	}
}
