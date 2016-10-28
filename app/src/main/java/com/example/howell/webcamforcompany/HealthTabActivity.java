package com.example.howell.webcamforcompany;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import com.howell.ecamcompany.R;

/**
 * @author 霍之昊 
 *
 * 类说明:
 */
@SuppressWarnings("deprecation")
public class HealthTabActivity extends TabActivity implements OnCheckedChangeListener,OnClickListener {
	private TabHost mHost;
    private RadioGroup mGroup;
    private RadioButton mFault,mWarning;
    private ImageButton mBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_tab);
		init();
		Intent intent = getIntent();
		int which = intent.getIntExtra("whichtab", 0);
		switch(which){
		case 0:mFault.setChecked(true);break;
		case 1:mWarning.setChecked(true);break;
		default:break;
		}
	}
	
	//初始化
	private void init(){
		mGroup = (RadioGroup)findViewById(R.id.health_radio_group);
		mGroup.setOnCheckedChangeListener(this);
		mFault = (RadioButton)findViewById(R.id.rb_health_error);
		mWarning = (RadioButton)findViewById(R.id.rb_health_warning);
		mBack = (ImageButton)findViewById(R.id.health_tab_back);
		mBack.setOnClickListener(this);
		mHost = getTabHost();
		mHost.addTab(mHost
                .newTabSpec("healthfault")
                .setIndicator(getResources().getString(R.string.health_fault),
                        getResources().getDrawable(R.drawable.tab_fault_selector))
                .setContent(new Intent(this, ShowFaultsActivity.class)));
        mHost.addTab(mHost
                .newTabSpec("healthwarning")
                .setIndicator(getResources().getString(R.string.health_warning),
                        getResources().getDrawable(R.drawable.tab_warning_selector))
                .setContent(new Intent(this, ShowWarningsActivity.class)));
        
        //mHost.setCurrentTab(which);  
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch (checkedId) {
        case R.id.rb_health_error:
            mHost.setCurrentTabByTag("healthfault");
            mFault.setTextColor(getResources().getColor(R.color.white));
            mWarning.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            break;
        case R.id.rb_health_warning:
            mHost.setCurrentTabByTag("healthwarning");
            mFault.setTextColor(getResources().getColor(R.color.camera_tab_blue));
            mWarning.setTextColor(getResources().getColor(R.color.white));
            break;
        default:
            break;
        }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.health_tab_back:
        	finish();
        	break;

		default:
			break;
		}
	}
}
