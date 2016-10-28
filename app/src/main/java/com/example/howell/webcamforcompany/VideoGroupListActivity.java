package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.howell.adapter.VideoGroupListAdapter;
import com.howell.ecamcompany.R;
import com.howell.ehlib.MyListView;
import com.howell.ehlib.MyListView.OnRefreshListener;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.VideoSourceGroup;
import com.howell.ksoap.VideoSourceGroupCatalogReq;
import com.howell.ksoap.VideoSourceGroupCatalogRes;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class VideoGroupListActivity extends Activity implements OnRefreshListener,OnItemClickListener{
	
    private MyListView videoGroupsListView;
	private ArrayList<VideoSourceGroup> videoGroupsList;
    private VideoGroupListAdapter adapter;
    private SoapManager mSoapManager;
    private VideoSourceGroupCatalogRes videoSourceGroupCatalogRes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_group_list);
		videoGroupsList = new ArrayList<VideoSourceGroup>();
		videoGroupsListView = (MyListView)findViewById(R.id.video_group_listview);
		mSoapManager = SoapManager.getInstance();
		adapter = new VideoGroupListAdapter(this,videoGroupsList);
		videoGroupsListView.setAdapter(adapter);
		videoGroupsListView.setonRefreshListener(this);
		videoGroupsListView.setOnItemClickListener(this);
		
	}

	private void connectReq(){
		System.out.println("group onFirstRefresh");
		VideoSourceGroupCatalogReq videoSourceGroupCatalogReq = new VideoSourceGroupCatalogReq();
		videoSourceGroupCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
		videoSourceGroupCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
		videoSourceGroupCatalogReq.setId("Root");
		videoSourceGroupCatalogRes = mSoapManager.getVideoSourceGroupCatalogRes(videoSourceGroupCatalogReq);
	}
	
	private void connectRes(){
		videoGroupsList = videoSourceGroupCatalogRes.getVideoSourceGroups();
//		videoGroupsList.add(new VideoSourceGroup("Root","test"));
		adapter.setVideoGroupsList(videoGroupsList);
		adapter.notifyDataSetChanged();
		videoGroupsListView.onRefreshComplete();
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Void, Void>() {
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String id = videoSourceGroupCatalogRes.getVideoSourceGroups().get((int)arg3).getId();
		Intent intent = new Intent(VideoGroupListActivity.this,SecondGroupActivity.class);
		intent.putExtra("id", id);
		intent.putExtra("stringtitle", videoGroupsList.get((int)arg3).getName());
		startActivity(intent);
		//设置切换动画，从右边进入，左边退出
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);     
	}


}
