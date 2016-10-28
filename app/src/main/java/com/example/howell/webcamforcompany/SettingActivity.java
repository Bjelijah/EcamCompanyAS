package com.example.howell.webcamforcompany;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.howell.ecamcompany.R;

import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends Activity implements OnClickListener {
//    private SoapManager mSoapManager;

    private View mAccount;
//    private View mSysMessage;

    private Button mButton;
	private PopupWindow popupWindow;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Log.e("Settings", "onCreate");
    	
        mAccount = findViewById(R.id.account);
        mButton = (Button) findViewById(R.id.exit);
        
        mAccount.setOnClickListener(this);
//        mSysMessage.setOnClickListener(this);
        mButton.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        int id = v.getId();
        switch (id) {
        case R.id.account:
//            Intent intent = new Intent(this, InformationActivity.class);
//            startActivity(intent);
            break;
        case R.id.exit:
        	showPopupWindow();
            break;
        case R.id.bt_exit:
			popupWindow.dismiss();
			break;
		case R.id.bt_remove:
			popupWindow.dismiss();
			SharedPreferences sharedPreferences = getSharedPreferences("set",
                    Context.MODE_PRIVATE);
        	Editor editor = sharedPreferences.edit();
            editor.putString("account", "");
            editor.putString("password", "");
            editor.commit();
            System.out.println(JPushInterface.isPushStopped(getApplicationContext()));
            if(!JPushInterface.isPushStopped(getApplicationContext()))
            	JPushInterface.stopPush(getApplicationContext());
            System.out.println(JPushInterface.isPushStopped(getApplicationContext()));
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        default:
            break;
        }
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
		case 1:
			if(data != null){
				String result = data.getStringExtra("result");
				System.out.println("result:"+result);
				if(result != null){
					Uri uri = Uri.parse(result);  
					Intent it = new Intent(Intent.ACTION_VIEW, uri);  
					startActivity(it);
				}
			}
			break;

		default:
			break;
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void showPopupWindow() {

		View view = (LinearLayout) LayoutInflater.from(SettingActivity.this).inflate(R.layout.popmenu, null);
				/*.inflate(R.layout.popmenu, null);*/

		LinearLayout bt_clear = (LinearLayout) view.findViewById(R.id.bt_remove);
		LinearLayout bt_exit = (LinearLayout) view.findViewById(R.id.bt_exit);
		
		TextView tv_clear = (TextView) view.findViewById(R.id.tv_remove);
		//TextView tv_exit = (TextView) view.findViewById(R.id.tv_exit);
		tv_clear.setText(getResources().getString(R.string.remove_device_popmenu_logout));
//		TextPaint tp = tv_clear.getPaint();
//        tp.setFakeBoldText(true);
//        tp = tv_exit.getPaint();
//        tp.setFakeBoldText(true);

		bt_clear.setOnClickListener(this);
		bt_exit.setOnClickListener(this);

		if (popupWindow == null) {

			popupWindow = new PopupWindow(SettingActivity.this);

//			popupWindow.setFocusable(true); // 设置PopupWindow可获得焦点
			popupWindow.setTouchable(true); // 设置PopupWindow可触摸
			popupWindow.setOutsideTouchable(true); // 设置非PopupWindow区域可触摸

			popupWindow.setContentView(view);
			
			popupWindow.setWidth(LayoutParams.MATCH_PARENT);
			popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
			
			popupWindow.setAnimationStyle(R.style.popuStyle);	//设置 popupWindow 动画样式
		}

		popupWindow.showAtLocation(mButton, Gravity.BOTTOM, 0, 0);

		popupWindow.update();

	}
    
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
        Log.e("Setting","onPause");
    }
    
    @Override
    protected void onRestart() {
    	// TODO Auto-generated method stub
    	super.onRestart();
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    }
}
