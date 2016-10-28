package com.example.howell.webcamforcompany;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.howell.adapter.VideoSourceListAdapter;
import com.howell.ecamcompany.R;
import com.howell.ehlib.GroupRefreshListView;
import com.howell.ehlib.GroupRefreshListView.OnRefreshListener;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.VideoSource;
import com.howell.ksoap.VideoSourceCatalogReq;
import com.howell.ksoap.VideoSourceCatalogRes;
import com.howell.ksoap.VideoSourceGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class VideoSourcesListActivity extends ListActivity implements Const,OnRefreshListener{
	private static final String TAG = "VideoSourcesListActivity";
	private ArrayList<VideoSource> videoSourcesList;
    private GroupRefreshListView videoSourcesListView;
    private VideoSourceListAdapter adapter;
    private SoapManager mSoapManager;
    private VideoSourceCatalogRes videoSourceCatalogRes;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("123",TAG+"   oncreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_sources_list);
		Log.e("123",TAG+"   0000000000");
		mSoapManager = SoapManager.getInstance();
		videoSourcesList = new ArrayList<VideoSource>();
		Log.e("123",TAG+"   1111111");
		videoSourcesListView = (GroupRefreshListView)findViewById(android.R.id.list);
		Log.e("123",TAG+"   22222222");
		adapter = new VideoSourceListAdapter(this,new ArrayList<VideoSourceGroup>(),videoSourcesList,videoSourcesListView,VIDEOSOURCEACTIVITY);
		Log.e("123",TAG+"   33333333");
		setListAdapter(adapter);
		Log.e("123",TAG+"   44444444444");
		videoSourcesListView.setonRefreshListener(this);
		videoSourcesListView.setOnScrollListener(adapter);
		
	}
	
	private void connectReq(){
		//获取设备列表
		System.out.println("VideoSourceCatalogReq");
		VideoSourceCatalogReq videoSourceCatalogReq = new VideoSourceCatalogReq();
		videoSourceCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
		videoSourceCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
				//videoSourceCatalogReq.setId("test");
//				videoSourceCatalogReq.setPageIndex(1);
//				videoSourceCatalogReq.setPageSize(10);
		videoSourceCatalogRes = mSoapManager.getVideoSourceCatalogRes(videoSourceCatalogReq);
	}
	
	@SuppressWarnings("unchecked")
	private void connectRes(){
		if(videoSourceCatalogRes.getResult() != null){
			videoSourcesList = videoSourceCatalogRes.getVideoSources();
			Collections.sort(videoSourcesList, new SortByName());
			adapter.setVideoSourcesList(videoSourcesList);
		}
		adapter.notifyDataSetChanged();
		videoSourcesListView.onRefreshComplete();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		adapter.setVideoSourcesList(videoSourcesList);
		adapter.notifyDataSetChanged();
		Log.i("log123", "listview on resum");
		
		SharedPreferences preferences = getSharedPreferences("VideoSourceList", MODE_PRIVATE);   
		int pos = preferences.getInt("position", 0);
		if (pos != 0) {
			Editor editor = preferences.edit();   
			editor.putInt("position", 0); 
			editor.commit();  	
			videoSourcesListView.setSelection(pos);
		}
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Integer, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				connectReq();
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				connectRes();
				super.onPostExecute(result);
			}
			
		}.execute();
	}

	@Override
	public void onFirstRefresh() {
		// TODO Auto-generated method stub
		connectReq();
	}
	
	@Override
	public void onFirstRefreshDown() {
		// TODO Auto-generated method stub
		connectRes();
	}
	
}

@SuppressWarnings("rawtypes")
class SortByName implements Comparator {
	public int compare(Object o1, Object o2) {
		VideoSource s1 = (VideoSource) o1;
		VideoSource s2 = (VideoSource) o2;
		return s1.getName().compareTo(s2.getName());
	}
}
