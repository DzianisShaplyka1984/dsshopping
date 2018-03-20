package com.dsshopping.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static String DB_NAME = "DSShoppingDB";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	      db.execSQL("create table list ("
	              + "id integer primary key autoincrement," 
	              + "name text,"
	              + "description text,"
	              + "total integer,"
	              + "purchased integer,"
	              + "created smalldatetime" + ");");
	      
	      db.execSQL("create table category ("
	              + "id integer primary key autoincrement," 
	              + "name text,"
	              + "description text" + ");");
	      
	      db.execSQL("create table productdescription ("
	              + "id integer primary key autoincrement," 
	              + "name text,"
	              + "description text,"
	              + "category_id int" + ");");
	      
	      db.execSQL("create table product ("
	              + "id integer primary key autoincrement," 
	              + "price real,"
	              + "amount real,"
	              + "purchased integer,"
	              + "description text,"
	              + "list_id integer,"
	              + "name text" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
