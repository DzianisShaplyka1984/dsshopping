package com.dsshopping.managers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dsshopping.model.ListVO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ListManager {
	private static String TABLE_NAME = "list";

	private static String DATE_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

	private SQLiteDatabase db;

	public ListManager(SQLiteDatabase db) {
		this.db = db;
	}

	public List<ListVO> getAll() {
		List<ListVO> result = new ArrayList<ListVO>();

		Cursor c = db.query(TABLE_NAME, null, null, null, null, null, "name");

		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex("id");
			int nameIndex = c.getColumnIndex("name");
			int descriptionIndex = c.getColumnIndex("description");
			int createdIndex = c.getColumnIndex("created");
			int totalIndex = c.getColumnIndex("total");
			int purchasedIndex = c.getColumnIndex("purchased");

			do {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						DATE_FORMAT_STRING);

				Date date = new Date();

				try {

					date = dateFormat.parse(c.getString(createdIndex));
				} catch (ParseException e) {

					e.printStackTrace();
				}

				ListVO listVO = new ListVO(c.getLong(idIndex),
						c.getString(nameIndex), c.getString(descriptionIndex),
						date, c.getInt(totalIndex), c.getInt(purchasedIndex));

				result.add(listVO);
			} while (c.moveToNext());
		}

		return result;
	}

	public ListVO getList(long id) {
		Cursor c = db.query(TABLE_NAME, null, "id = " + id, null, null, null,
				"name");

		if (c.moveToFirst()) {
			int idIndex = c.getColumnIndex("id");
			int nameIndex = c.getColumnIndex("name");
			int descriptionIndex = c.getColumnIndex("description");
			int createdIndex = c.getColumnIndex("created");
			int totalIndex = c.getColumnIndex("total");
			int purchasedIndex = c.getColumnIndex("purchased");

			SimpleDateFormat dateFormat = new SimpleDateFormat(
					DATE_FORMAT_STRING);

			Date date = new Date();

			try {

				date = dateFormat.parse(c.getString(createdIndex));
			} catch (ParseException e) {

				e.printStackTrace();
			}

			ListVO listVO = new ListVO(c.getLong(idIndex),
					c.getString(nameIndex), c.getString(descriptionIndex),
					date, c.getInt(totalIndex), c.getInt(purchasedIndex));

			return listVO;
		}

		return null;
	}

	public long insert(String name, String description) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_STRING);

		Date created = new Date();

		ContentValues cv = new ContentValues();

		cv.put("name", name);
		cv.put("description", description);
		cv.put("created", dateFormat.format(created));

		return db.insert(TABLE_NAME, null, cv);
	}

	public void delete(long id) {
		db.delete(TABLE_NAME, "id = " + id, null);
	}

	public void rename(long id, String name) {
		ContentValues cv = new ContentValues();

		cv.put("name", name);

		db.update(TABLE_NAME, cv, "id = " + id, null);
	}

	public void statistic(long id, int total, int purchased) {
		ContentValues cv = new ContentValues();

		cv.put("total", total);
		cv.put("purchased", purchased);

		db.update(TABLE_NAME, cv, "id = " + id, null);
	}
}
