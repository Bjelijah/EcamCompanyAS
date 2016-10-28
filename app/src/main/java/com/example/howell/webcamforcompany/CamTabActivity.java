package com.example.howell.webcamforcompany;


import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.howell.ecamcompany.R;

@SuppressWarnings("deprecation")
public class CamTabActivity extends TabActivity implements
OnCheckedChangeListener {

	private TabHost mHost;
	private RadioGroup mGroup;
	private RadioButton mCameraList,mDeviceManager,mNoticeManager,mHealthDegree,mSettings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cam_tab);
		Log.e("CamTabActivity", "onCreate");
		mGroup = (RadioGroup) findViewById(R.id.radio_group);
		mGroup.setOnCheckedChangeListener(this);
		mCameraList = (RadioButton)findViewById(R.id.rb_camera_list);
		mDeviceManager = (RadioButton)findViewById(R.id.rb_device_manager);
		mHealthDegree = (RadioButton)findViewById(R.id.rb_health_degree);
		//        mLocalFiles = (RadioButton)findViewById(R.id.rb_local_files);
		mNoticeManager = (RadioButton)findViewById(R.id.rb_notice_manager);
		mSettings = (RadioButton)findViewById(R.id.rb_settings);

		mHost = getTabHost();
		mHost.addTab(mHost
				.newTabSpec("cameralist")
				.setIndicator(getResources().getString(R.string.camera_list),
						getResources().getDrawable(R.drawable.tab_camera_selector))
				.setContent(new Intent(this, VideoListTabActivity.class)));
		mHost.addTab(mHost
				.newTabSpec("devicemanager")
				.setIndicator(getResources().getString(R.string.device),
						getResources().getDrawable(R.drawable.tab_camera_selector))
				.setContent(new Intent(this, DeviceManagerActivity.class)));
		mHost.addTab(mHost
				.newTabSpec("healthdegree")
				.setIndicator(getResources().getString(R.string.health_degree),
						getResources().getDrawable(R.drawable.tab_camera_selector))
				.setContent(new Intent(this, CheckDeviceActivity.class)));
		mHost.addTab(mHost
				.newTabSpec("noticecatalog")
				.setIndicator(getResources().getString(R.string.notice_catalog),
						getResources().getDrawable(R.drawable.tab_camera_selector))
				.setContent(new Intent(this, NoticeCatalogActivity.class)));

		//        mHost.addTab(mHost
		//                .newTabSpec("localfiles")
		//                .setIndicator(getResources().getString(R.string.media_lib),
		//                        getResources().getDrawable(R.drawable.tab_files_selector))
		//                .setContent(new Intent(this, LocalFilesActivity.class)));

		mHost.addTab(mHost
				.newTabSpec("settings")
				.setIndicator(getResources().getString(R.string.settings),
						getResources().getDrawable(R.mipmap.setting))
				.setContent(new Intent(this, SettingActivity.class)));
		mHost.setCurrentTab(0);  
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
		case R.id.rb_camera_list:
			mHost.setCurrentTabByTag("cameralist");
			mCameraList.setTextColor(getResources().getColor(R.color.blue));
			mDeviceManager.setTextColor(getResources().getColor(R.color.light_gray));
			//            mLocalFiles.setTextColor(getResources().getColor(R.color.light_gray));
			mNoticeManager.setTextColor(getResources().getColor(R.color.light_gray));
			mSettings.setTextColor(getResources().getColor(R.color.light_gray));
			mHealthDegree.setTextColor(getResources().getColor(R.color.light_gray));
			break;
		case R.id.rb_device_manager:
			mHost.setCurrentTabByTag("devicemanager");
			mDeviceManager.setTextColor(getResources().getColor(R.color.blue));
			mCameraList.setTextColor(getResources().getColor(R.color.light_gray));
			//            mLocalFiles.setTextColor(getResources().getColor(R.color.light_gray));
			mNoticeManager.setTextColor(getResources().getColor(R.color.light_gray));
			mSettings.setTextColor(getResources().getColor(R.color.light_gray));
			mHealthDegree.setTextColor(getResources().getColor(R.color.light_gray));
			break;  
		case R.id.rb_health_degree:
			mHost.setCurrentTabByTag("healthdegree");
			mHealthDegree.setTextColor(getResources().getColor(R.color.blue));
			mCameraList.setTextColor(getResources().getColor(R.color.light_gray));
			mDeviceManager.setTextColor(getResources().getColor(R.color.light_gray));
			//            mLocalFiles.setTextColor(getResources().getColor(R.color.light_gray));
			mNoticeManager.setTextColor(getResources().getColor(R.color.light_gray));
			mSettings.setTextColor(getResources().getColor(R.color.light_gray));
			break;

		case R.id.rb_notice_manager:
			mHost.setCurrentTabByTag("noticecatalog");
			mNoticeManager.setTextColor(getResources().getColor(R.color.blue));
			mDeviceManager.setTextColor(getResources().getColor(R.color.light_gray));
			mCameraList.setTextColor(getResources().getColor(R.color.light_gray));
			//            mLocalFiles.setTextColor(getResources().getColor(R.color.light_gray));
			mSettings.setTextColor(getResources().getColor(R.color.light_gray));
			mHealthDegree.setTextColor(getResources().getColor(R.color.light_gray));
			break;  
			//        case R.id.rb_local_files:
			//            mHost.setCurrentTabByTag("localfiles");
			//            mLocalFiles.setTextColor(getResources().getColor(R.color.blue));
			//            mCameraList.setTextColor(getResources().getColor(R.color.light_gray));
			//            mDeviceManager.setTextColor(getResources().getColor(R.color.light_gray));
			//            mSettings.setTextColor(getResources().getColor(R.color.light_gray));
			//            mHealthDegree.setTextColor(getResources().getColor(R.color.light_gray));
			//            break;
		case R.id.rb_settings:
			mHost.setCurrentTabByTag("settings");
			mSettings.setTextColor(getResources().getColor(R.color.blue));
			//            mLocalFiles.setTextColor(getResources().getColor(R.color.light_gray));
			mNoticeManager.setTextColor(getResources().getColor(R.color.light_gray));
			mCameraList.setTextColor(getResources().getColor(R.color.light_gray));
			mHealthDegree.setTextColor(getResources().getColor(R.color.light_gray));
			break;
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.i("log123", "cam tab on resume");
		SharedPreferences preferences = getSharedPreferences("relogin", MODE_PRIVATE);   
		boolean needRelogin =  preferences.getBoolean("needReLogin", false);  
		if (needRelogin) {
			Editor editor = preferences.edit();   
			editor.putBoolean("needReLogin", false);   
			editor.commit();  	
			Intent intent = new Intent(this, LoginActivity.class);
	        startActivity(intent);
	        finish();
		}
		// preferences.getString("string_key", "default_value");   

		super.onResume();
	}
}
