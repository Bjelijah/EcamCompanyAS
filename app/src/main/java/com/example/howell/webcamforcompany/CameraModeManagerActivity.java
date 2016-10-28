package com.example.howell.webcamforcompany;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.howell.ecamcompany.R;

/**
 * @author 霍之昊 
 *
 * 类说明:主页界面
 */
public class CameraModeManagerActivity extends Activity implements OnClickListener{
	private FrameLayout videoSource,deviceManager,myFavourite;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_mode_manager_activity);
		videoSource = (FrameLayout)findViewById(R.id.camera_mode_videosource);
		deviceManager = (FrameLayout)findViewById(R.id.camera_mode_devicemanager);
		myFavourite = (FrameLayout)findViewById(R.id.camera_mode_favourite);
		videoSource.setOnClickListener(this);
		deviceManager.setOnClickListener(this);
		myFavourite.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.camera_mode_videosource:
			Intent intent = new Intent(this,VideoListTabActivity.class);
			startActivity(intent);
			break;
		case R.id.camera_mode_devicemanager:
			intent = new Intent(this,DeviceManagerActivity.class);
			startActivity(intent);
			break;
		case R.id.camera_mode_favourite:
			intent = new Intent(this,MyFavouriteActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
