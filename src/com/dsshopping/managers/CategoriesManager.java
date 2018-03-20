package com.dsshopping.managers;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dsshopping.model.CategoryVO;

public class CategoriesManager {
	private static String TABLE_NAME = "category";

	private SQLiteDatabase db;

	public CategoriesManager(SQLiteDatabase db) {
		this.db = db;
	}

	public List<CategoryVO> getAll() {
		List<CategoryVO> result = new ArrayList<CategoryVO>();

		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, "name");

		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex("id");
			int nameIndex = c.getColumnIndex("name");
			int descriptionIndex = c.getColumnIndex("description");

			do {

				CategoryVO categoryVO = new CategoryVO(c.getLong(idIndex),
						c.getString(nameIndex), c.getString(descriptionIndex));

				result.add(categoryVO);
			} while (c.moveToNext());
		}

		return result;
	}

	public long insert(String name, String description) {
		ContentValues cv = new ContentValues();

		cv.put("name", name);
		cv.put("description", description);

		return db.insert(TABLE_NAME, null, cv);
	}

	public void delete(long id) {
		db.delete(TABLE_NAME, "id = " + id, null);
	}
}
