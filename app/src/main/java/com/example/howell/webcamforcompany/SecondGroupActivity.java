package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.howell.adapter.VideoSourceListAdapter;
import com.howell.ecamcompany.R;
import com.howell.ehlib.GroupRefreshListView;
import com.howell.ehlib.GroupRefreshListView.OnRefreshListener;
import com.howell.ksoap.GroupVideoSourceCatalogReq;
import com.howell.ksoap.GroupVideoSourceCatalogRes;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.VideoSource;
import com.howell.ksoap.VideoSourceGroup;
import com.howell.ksoap.VideoSourceGroupCatalogReq;
import com.howell.ksoap.VideoSourceGroupCatalogRes;
import com.howell.utils.DialogUtils;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class SecondGroupActivity extends Activity implements Const,OnItemClickListener,OnClickListener,OnRefreshListener{
	
	private GroupRefreshListView /*secondGroupListView,*/secondDeviceListView;
	private ImageButton btnBack;
	private HorizontalScrollView scroll;
	private TextView tvGroupTitle;
	private ArrayList<VideoSourceGroup> videoGroupsList;
	private ArrayList<VideoSource> videoSourcesList;
	private VideoSourceListAdapter deviceAdapter;
    private SoapManager mSoapManager;
    private String stringTitle;
    private String id;
    private VideoSourceGroupCatalogRes videoSourceGroupCatalogRes;
	private GroupVideoSourceCatalogRes groupVideoSourceCatalogRes;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_group);
		init();
//		startWaitingAnimation();
//		GetInfoTask getInfoTask = new GetInfoTask();
//		getInfoTask.execute();
	}
	
	private void init(){
		Intent intent = getIntent();
		stringTitle = intent.getStringExtra("stringtitle");
		id = intent.getStringExtra("id");
		mSoapManager = SoapManager.getInstance();
		btnBack = (ImageButton)findViewById(R.id.group_title_back_btn);
		btnBack.setOnClickListener(this);
		tvGroupTitle = (TextView)findViewById(R.id.group_title_textview);
		tvGroupTitle.setText(stringTitle);
		scroll = (HorizontalScrollView)findViewById(R.id.group_title_scrollview);
		scroll.post(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = mesureViewWidth(tvGroupTitle);
				scroll.scrollTo(i, 0);
			}
		});
		
		videoGroupsList = new ArrayList<VideoSourceGroup>();
		videoSourcesList = new ArrayList<VideoSource>();
		secondDeviceListView = (GroupRefreshListView)findViewById(R.id.second_device_listview);
		deviceAdapter = new VideoSourceListAdapter(this,videoGroupsList ,videoSourcesList,secondDeviceListView,VIDEOSOURCEACTIVITY);
		secondDeviceListView.setAdapter(deviceAdapter);
		secondDeviceListView.setOnItemClickListener(this);
		secondDeviceListView.setonRefreshListener(this);
		secondDeviceListView.setOnScrollListener(deviceAdapter);
	}
	
	private int mesureViewWidth(View layout){
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		layout.measure(w, h); 
		//int height = btnLayout.getMeasuredHeight(); 
		int width = layout.getMeasuredWidth(); //获取控件宽度
		return width;
	}
	
//	//获取下一级信息
//	class GetInfoTask extends AsyncTask<Void, Integer, Void> {
//		private VideoSourceGroupCatalogRes videoSourceGroupCatalogRes;
//		private GroupVideoSourceCatalogRes groupVideoSourceCatalogRes;
//		@Override
//		protected Void doInBackground(Void... arg0) {
//			// TODO Auto-generated method stub
//			//获取层数
//			VideoSourceGroupCatalogReq videoSourceGroupCatalogReq = new VideoSourceGroupCatalogReq();
//			videoSourceGroupCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
//			videoSourceGroupCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
//			videoSourceGroupCatalogReq.setId(id);
//			videoSourceGroupCatalogRes = mSoapManager.getVideoSourceGroupCatalogRes(videoSourceGroupCatalogReq);
//			//获取视频
//			GroupVideoSourceCatalogReq groupVideoSourceCatalogReq = new GroupVideoSourceCatalogReq();
//			groupVideoSourceCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
//			groupVideoSourceCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
//			groupVideoSourceCatalogReq.setId(id);
//			groupVideoSourceCatalogRes = mSoapManager.getGroupVideoSourceCatalogRes(groupVideoSourceCatalogReq);
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(Void result) {
//			// TODO Auto-generated method stub
//			if(videoSourceGroupCatalogRes.getResult() != null && groupVideoSourceCatalogRes.getResult() != null){//获取成功
//				videoGroupsList = videoSourceGroupCatalogRes.getVideoSourceGroups();
//				videoSourcesList = groupVideoSourceCatalogRes.getVideoSources();
//				//test
//				videoSourcesList.add(new VideoSource("1"));
//				videoSourcesList.add(new VideoSource("2"));
//				videoSourcesList.add(new VideoSource("3"));
//				videoSourcesList.add(new VideoSource("4"));
//				videoSourcesList.add(new VideoSource("5"));
//				videoSourcesList.add(new VideoSource("6"));
//				videoSourcesList.add(new VideoSource("7"));
//				videoSourcesList.add(new VideoSource("8"));
//				videoSourcesList.add(new VideoSource("9"));
//				videoSourcesList.add(new VideoSource("10"));
//				videoSourcesList.add(new VideoSource("11"));
//				videoSourcesList.add(new VideoSource("12"));
//				videoSourcesList.add(new VideoSource("13"));
//				videoSourcesList.add(new VideoSource("14"));
//				videoSourcesList.add(new VideoSource("15"));
//				videoSourcesList.add(new VideoSource("16"));
//				videoSourcesList.add(new VideoSource("17"));
//				videoSourcesList.add(new VideoSource("18"));
//				videoSourcesList.add(new VideoSource("19"));
//				videoSourcesList.add(new VideoSource("20"));
//				
////				adapter.setVideoGroupsList(videoGroupsList);
//				deviceAdapter.setVideoSourcesList(videoSourcesList);
//				deviceAdapter.setVideoGroupsList(videoGroupsList);
////				adapter.notifyDataSetChanged();
//				deviceAdapter.notifyDataSetChanged();
//			}else{												//获取失败
//				DialogUtils.postDialogWithPositiveButton(SecondGroupActivity.this,"","网络繁忙，请稍候再试",R.drawable.expander_ic_minimized,"确定",
//						new DialogInterface.OnClickListener() {
//							
//							@Override
//							public void onClick(DialogInterface arg0, int arg1) {
//								// TODO Auto-generated method stub
//								
//							}
//						}).show();
//			}
//			//结束等待动画
//			finishWaitingAnimation();
//			super.onPostExecute(result);
//		}
//	}
	
//	private void startWaitingAnimation(){
//		waitDialog = DialogUtils.postWaitDialog(SecondGroupActivity.this);
//		waitDialog.show();
//	}
//	
//	private void finishWaitingAnimation(){
//		waitDialog.dismiss();
//	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int arg2, long position) {
		// TODO Auto-generated method stub
		if(position <= videoGroupsList.size() -1 ){
			String id = videoGroupsList.get((int)position).getId();
			Intent intent = new Intent(SecondGroupActivity.this,SecondGroupActivity.class);
			intent.putExtra("id", id);
			intent.putExtra("stringtitle", stringTitle + " > " + videoGroupsList.get((int)position).getName());
			startActivity(intent);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.group_title_back_btn:
			finish();
			break;

		default:
			break;
		}
	}
	
	private void connectReq(){
		//获取层数
				VideoSourceGroupCatalogReq videoSourceGroupCatalogReq = new VideoSourceGroupCatalogReq();
				videoSourceGroupCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
				videoSourceGroupCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
				videoSourceGroupCatalogReq.setId(id);
				videoSourceGroupCatalogRes = mSoapManager.getVideoSourceGroupCatalogRes(videoSourceGroupCatalogReq);
				//获取视频
				GroupVideoSourceCatalogReq groupVideoSourceCatalogReq = new GroupVideoSourceCatalogReq();
				groupVideoSourceCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
				groupVideoSourceCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
				groupVideoSourceCatalogReq.setId(id);
				groupVideoSourceCatalogRes = mSoapManager.getGroupVideoSourceCatalogRes(groupVideoSourceCatalogReq);
	}
	
	private void connectRes(){
		if(videoSourceGroupCatalogRes.getResult() != null && groupVideoSourceCatalogRes.getResult() != null){//获取成功
			videoGroupsList = videoSourceGroupCatalogRes.getVideoSourceGroups();
			videoSourcesList = groupVideoSourceCatalogRes.getVideoSources();
			//test
//			videoSourcesList.add(new VideoSource("1","00001"));
//			videoSourcesList.add(new VideoSource("2","00002"));
//			videoSourcesList.add(new VideoSource("3","00003"));
//			videoSourcesList.add(new VideoSource("4","00004"));
//			videoSourcesList.add(new VideoSource("5","00005"));
//			videoSourcesList.add(new VideoSource("6","00006"));
//			videoSourcesList.add(new VideoSource("7","00007"));
//			videoSourcesList.add(new VideoSource("8","00008"));
//			videoSourcesList.add(new VideoSource("9","00009"));
//			videoSourcesList.add(new VideoSource("10","00010"));
//			videoSourcesList.add(new VideoSource("11","00011"));
//			videoSourcesList.add(new VideoSource("12","00012"));
//			videoSourcesList.add(new VideoSource("13","00013"));
//			videoSourcesList.add(new VideoSource("14","00014"));
//			videoSourcesList.add(new VideoSource("15","00015"));
//			videoSourcesList.add(new VideoSource("16","00016"));
//			videoSourcesList.add(new VideoSource("17","00017"));
//			videoSourcesList.add(new VideoSource("18","00018"));
//			videoSourcesList.add(new VideoSource("19","00019"));
//			videoSourcesList.add(new VideoSource("20","00020"));
			
//			adapter.setVideoGroupsList(videoGroupsList);
			deviceAdapter.setVideoSourcesList(videoSourcesList);
			deviceAdapter.setVideoGroupsList(videoGroupsList);
//			adapter.notifyDataSetChanged();
			deviceAdapter.notifyDataSetChanged();
			secondDeviceListView.onRefreshComplete();
		}else{												//获取失败
			DialogUtils.postDialogWithPositiveButton(SecondGroupActivity.this,"","网络繁忙，请稍候再试",R.drawable.expander_ic_minimized,"确定",
					new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}
					}).show();
		}
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
	
}
