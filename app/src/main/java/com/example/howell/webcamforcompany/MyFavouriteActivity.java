package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.howell.adapter.VideoSourceListAdapter;
import com.howell.db.DBManager;
import com.howell.ecamcompany.R;
import com.howell.ksoap.VideoSource;
import com.howell.ksoap.VideoSourceGroup;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明：我的收藏界面
 */
public class MyFavouriteActivity extends Activity implements Const,OnClickListener{
	private VideoSourceListAdapter adapter;
	private ListView favouriteListView;
//	private ImageButton back;
	private ArrayList<VideoSource> videoSourcesList;
	private DBManager dbmanager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_favourite);
		dbmanager = new DBManager(this);
//		back = (ImageButton)findViewById(R.id.my_favourite_back);
//		back.setOnClickListener(this);
		videoSourcesList = dbmanager.query();
		favouriteListView = (ListView)findViewById(R.id.my_favourite_listview);
		adapter = new VideoSourceListAdapter(this, new ArrayList<VideoSourceGroup>(), new ArrayList<VideoSource>(), null,FAVOURITEACTIVITY);
		favouriteListView.setAdapter(adapter);
		adapter.setVideoSourcesList(videoSourcesList);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(dbmanager != null) dbmanager.closeDB();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e("","onResume");
		videoSourcesList = dbmanager.query();
		adapter.setVideoSourcesList(videoSourcesList);
		adapter.notifyDataSetChanged();
	}	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.my_favourite_back:
//			finish();
//			break;

		default:
			break;
		}
	}
}
