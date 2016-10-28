package com.howell.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.howell.ksoap.VideoSource;
import com.howell.utils.BooleanIntegerTranslation;

import java.util.ArrayList;

public class DBManager {
	private DBHelper helper;
	private SQLiteDatabase db;
	
	public DBManager(Context context) {
		helper = new DBHelper(context);
		db = helper.getReadableDatabase();
	}
	
	/**
	 * add persons
	 * @param vs
	 */
	public void add(VideoSource vs) {
//		ArrayList<VideoSource> videosources = query();
//		for(VideoSource videosource : videosources ){
//			if(vs.getId().equals(videosource.getId())){
//				Log.e("", "视频源已存在于数据库中");
//				videosources = null;
//				return;
//			}
//		}
        db.beginTransaction();	//��ʼ����
        try {
        	//for (Person person : persons) {
        	db.execSQL("INSERT INTO videosource VALUES(?, ?, ?, ? ,?, ?, ?, ?)", new Object[]{vs.getId()
        			,vs.getName(),vs.getPseudoCode(),vs.getVideoInputChannelId(),vs.getCameraType()
        			,BooleanIntegerTranslation.b2i(vs.ispTZ()),BooleanIntegerTranslation.b2i(vs.isInfrared())
        			,BooleanIntegerTranslation.b2i(vs.isOnline())});
        	//}
        	db.setTransactionSuccessful();	//��������ɹ����
        } finally {
//        	videosources = null;
        	db.endTransaction();	//��������
        }
	}
	
	/**
	 * update person's age
	 * @param person
	 */
//	public void updateName(String oldName,String newName) {
//		ContentValues cv = new ContentValues();
//		cv.put("name", oldName);
//		db.update("camera", cv, "name = ?", new String[]{newName});
//	}
	
	/**
	 * delete old person
	 * @param name
	 */
	public void deleteOldCamera(String name) {
		db.delete("videosource", "name = ?", new String[]{name});
	}
	
	/**
	 * query all persons, return list
	 * @return List<Person>
	 */
	public ArrayList<VideoSource> query() {
		ArrayList<VideoSource> cameras = new ArrayList<VideoSource>();
		Cursor c = queryTheCursor();
        while (c.moveToNext()) {
        	VideoSource camera = new VideoSource();
        	camera.setId(c.getString(c.getColumnIndex("_id")));
        	camera.setName(c.getString(c.getColumnIndex("name")));
        	camera.setPseudoCode(c.getInt(c.getColumnIndex("pseudocode")));
        	camera.setVideoInputChannelId(c.getString(c.getColumnIndex("videoinputchannelid")));
        	camera.setCameraType(c.getString(c.getColumnIndex("cameratype")));
        	camera.setpTZ(BooleanIntegerTranslation.i2b(c.getInt(c.getColumnIndex("ptz"))));
        	camera.setInfrared(BooleanIntegerTranslation.i2b(c.getInt(c.getColumnIndex("infrared"))));
        	camera.setOnline(BooleanIntegerTranslation.i2b(c.getInt(c.getColumnIndex("online"))));
        	cameras.add(camera);
        }
        c.close();
        return cameras;
	}
	
	/**
	 * query all persons, return cursor
	 * @return	Cursor
	 */
	public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM videosource", null);
        return c;
	}
	
	/**
	 * close database
	 */
	public void closeDB() {
		db.close();
	}
}
