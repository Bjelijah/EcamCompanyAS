package com.howell.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "howell.db";
	private static final int DATABASE_VERSION = 1;
	
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("DBHelper onCreate");
		db.execSQL("CREATE TABLE IF NOT EXISTS videosource" +
				"(_id VARCHAR PRIMARY KEY , name VARCHAR, pseudocode INTEGER, videoinputchannelid VARCHAR" +
				",cameratype VARCHAR , ptz INTEGER , infrared INTEGER ,online INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("ALTER TABLE person ADD COLUMN other STRING");
	}
}
