package com.example.howell.webcamforcompany;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.howell.ecamcompany.R;

/**
 * @author 霍之昊 
 *
 * 类说明：摄像机，设备分组界面
 */
@SuppressWarnings("deprecation")
public class VideoListTabActivity extends TabActivity implements OnCheckedChangeListener,OnClickListener {
	
	private TabHost mHost;
    private RadioGroup mGroup;
    private RadioButton mCameraGroup,mCameraAll,mFavorites;
    //private ImageButton back;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera_tab);
		init();
	}
	
	private void init(){
		mGroup = (RadioGroup)findViewById(R.id.camera_radio_group);
		mGroup.setOnCheckedChangeListener(this);
		mCameraGroup = (RadioButton)findViewById(R.id.rb_camera_group);
		mCameraAll = (RadioButton)findViewById(R.id.rb_camera_all);
		mFavorites = (RadioButton)findViewById(R.id.rb_favorites);
//		back = (ImageButton)findViewById(R.id.camera_group_back);
//		back.setOnClickListener(this);
//		mFavorites = (RadioButton)findViewById(R.id.rb_favorites);
		mHost = getTabHost();
		mHost.addTab(mHost
                .newTabSpec("cameraall")
                .setIndicator(getResources().getString(R.string.camera_all),
                        getResources().getDrawable(R.drawable.camera_tab_left_selector))
                .setContent(new Intent(this, VideoSourcesListActivity.class)));
		mHost.addTab(mHost
                .newTabSpec("cameragroup")
                .setIndicator(getResources().getString(R.string.camera_group),
                        getResources().getDrawable(R.drawable.camera_tab_middle_selector))
                .setContent(new Intent(this, VideoGroupListActivity.class)));
		mHost.addTab(mHost
                .newTabSpec("favorites")
                .setIndicator(getResources().getString(R.string.favorites),
                        getResources().getDrawable(R.drawable.camera_tab_right_selector))
                .setContent(new Intent(this, MyFavouriteActivity.class)));
        
        mCameraAll.setChecked(true); 
        mHost.setCurrentTab(0);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
        case R.id.rb_camera_group:
            mHost.setCurrentTabByTag("cameragroup");
            mCameraGroup.setTextColor(getResources().getColor(R.color.white));
            mCameraAll.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            mFavorites.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            break;
        case R.id.rb_camera_all:
            mHost.setCurrentTabByTag("cameraall");
            mCameraGroup.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            mCameraAll.setTextColor(getResources().getColor(R.color.white));
            mFavorites.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            break;
        case R.id.rb_favorites:
            mHost.setCurrentTabByTag("favorites");
            mFavorites.setTextColor(getResources().getColor(R.color.white));
            mCameraGroup.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            mCameraAll.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            break;
        default:
            break;
        }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.camera_group_back:
//			finish();
//			break;

		default:
			break;
		}
	}
}
