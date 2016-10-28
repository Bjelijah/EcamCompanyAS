package com.example.howell.webcamforcompany;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.howell.ecamcompany.R;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.SystemHealthStatisticsReq;
import com.howell.ksoap.SystemHealthStatisticsRes;
import com.howell.utils.SystemReportStorage;

/**
 * @author 霍之昊 
 *
 * 类说明:健康度界面
 */
public class CheckDeviceActivity extends Activity implements OnClickListener{
	
	private TextView tvWarningCount,tvErrorCount,tvClick,tvScore,tvScoreUint;					
	private ImageView pointer,background;
	private LinearLayout alarmBtn,errorBtn;		
//	private FrameLayout healthBtn;
	
	private SoapManager mSoapManager;
	
	private CheckDeviceTask task;
	private static final int STARTCHECKDEVICE = 1;
	private static final int SHOWSCORE = 2;
	private SystemHealthStatisticsRes systemHealthStatisticsRes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_device);
		init();
		//开始获取健康度
		if(task == null){
			task = new CheckDeviceTask();
			task.execute();
		}
	}
	
	//初始化
	private void init(){
//		healthRefreshBtn = (ImageView)findViewById(R.id.health_refresh_btn);
//		healthRefreshBtn.setOnClickListener(this);
		tvScore = (TextView)findViewById(R.id.check_device_score);
		tvScore.setOnClickListener(this);
		tvScoreUint = (TextView)findViewById(R.id.check_device_score_unit);
		tvClick = (TextView)findViewById(R.id.check_device_click_to_check);
		pointer = (ImageView)findViewById(R.id.check_device_pointer);
		background = (ImageView)findViewById(R.id.check_device_bg);
//		backgroundRoll = (ImageView)findViewById(R.id.check_device_background);
		alarmBtn = (LinearLayout)findViewById(R.id.ll_alarm);
		alarmBtn.setOnClickListener(this);
		errorBtn = (LinearLayout)findViewById(R.id.ll_error);
		errorBtn.setOnClickListener(this);
//		healthBtn = (FrameLayout)findViewById(R.id.fl_health);
//		healthBtn.setOnClickListener(this);
		tvWarningCount = (TextView)findViewById(R.id.check_device_warning_count);
		tvErrorCount = (TextView)findViewById(R.id.check_device_error_count);
		mSoapManager = SoapManager.getInstance();
	}
	
	//开始动画
	private void startCheckDeviceAnimation(){
		background.setVisibility(View.VISIBLE);
		pointer.setVisibility(View.VISIBLE);
		tvScore.setVisibility(View.GONE);
		tvScoreUint.setVisibility(View.GONE);
		tvClick.setVisibility(View.GONE);
		RotateAnimation animation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		LinearInterpolator lir = new LinearInterpolator();
		animation.setInterpolator(lir);
		animation.setDuration(1000);
		animation.setFillAfter(true);
		animation.setRepeatCount(-1);
		pointer.startAnimation(animation);
	}
	
	//结束动画
	private void finishCheckDeviceAnimation(double endPosition){
		pointer.clearAnimation();
		pointer.setVisibility(View.GONE);
		background.setVisibility(View.GONE);
		tvScore.setVisibility(View.VISIBLE);
		final int score = (int)endPosition;
		new Thread(){
			public void run() {
				for(int i = 0 ; i <= score ; i++){
					Message msg = new Message();
					msg.arg1 = i;
					msg.what = SHOWSCORE;
					handler.sendMessage(msg);
					try {
						sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					tvScore.setText(i+"");
				}
			};
		}.start();
		
		
		tvScoreUint.setVisibility(View.VISIBLE);
		tvClick.setVisibility(View.VISIBLE);
//		RotateAnimation animation =new RotateAnimation(0f,endPosition,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
//		animation.setDuration(1000);
//		animation.setFillAfter(true);
//		pointer.startAnimation(animation);
	}
	
	//计算指针最后的位置
	/*private float findPointerPosition(double percentage){
		if(percentage == 0){
			return 0f;
		}else if(percentage > 0 && percentage <= 11){
			return 25f;
		}else if(percentage >= 12 && percentage <= 23){
			return 50f;
		}else if(percentage >= 24 && percentage <= 35){
			return 75f;
		}else if(percentage >= 36 && percentage <= 47){
			return 100f;
		}else if(percentage >= 48 && percentage <= 59){
			return 125f;
		}else if(percentage >= 60 && percentage <= 71){
			return 150f;
		}else if(percentage >= 72 && percentage <= 83){
			return 175f;
		}else if(percentage >= 84 && percentage <= 95){
			return 200f;
		}else {
			return 225f;
		}
	}*/
	
	//获取健康度时UI的显示
	class CheckDeviceTask extends AsyncTask<Void, Integer, Void> {
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//开始检查健康度动画UI
			handler.sendEmptyMessage(STARTCHECKDEVICE);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//请求健康度协议
			SystemHealthStatisticsReq systemHealthStatisticsReq = new SystemHealthStatisticsReq();
	        systemHealthStatisticsReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
	        systemHealthStatisticsReq.setSession(mSoapManager.getUserLoginRes().getSession());
	        systemHealthStatisticsRes = mSoapManager.getSystemHealthStatisticsRes(systemHealthStatisticsReq);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			double percantage = 0;
			if(systemHealthStatisticsRes.getResult() != null){
				//把故障 警告数存入内存
				SystemReportStorage.getInstance().setSystemFaultStatistics(systemHealthStatisticsRes.getSystemHealthStatistics().getFaults());
				SystemReportStorage.getInstance().setSystemWarningStatistics(systemHealthStatisticsRes.getSystemHealthStatistics().getWarnings());
				
				percantage = systemHealthStatisticsRes.getSystemHealthStatistics().getPercentage();
				//显示健康度
//				tvScore.setText(String.valueOf(Math.round(percantage)));
//				tvScore.setTextSize(40);
				//显示警告数，故障数
				tvWarningCount.setText(systemHealthStatisticsRes.getSystemHealthStatistics().getWarnings().getWarningNumber()+"");
				tvErrorCount.setText(systemHealthStatisticsRes.getSystemHealthStatistics().getFaults().getFaultNumber()+"");
			}
			//结束检查健康度动画UI
			finishCheckDeviceAnimation(percantage);
			super.onPostExecute(result);
		}
	}
	
	//处理线程中需要的UI更新
	@SuppressLint("HandlerLeak")
	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch(msg.what){
			//健康度开始动画
			case STARTCHECKDEVICE:
				startCheckDeviceAnimation();
				break;
			case SHOWSCORE:
				tvScore.setText(msg.arg1+"");
				break;
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		//健康度警告按钮点击事件
		case R.id.ll_alarm:
			Intent intent = new Intent(CheckDeviceActivity.this,HealthTabActivity.class);
			intent.putExtra("whichtab", 1);
			startActivity(intent);
			break;
		//健康度故障按钮点击事件
		case R.id.ll_error:
			intent = new Intent(CheckDeviceActivity.this,HealthTabActivity.class);
			intent.putExtra("whichtab", 0);
			startActivity(intent);
			break;
		//检查健康度按钮点击事件
		/*case R.id.fl_health:
			if(task == null){
				System.out.println("11");
				task = new CheckDeviceTask();
				task.execute();
			}else{
				System.out.println("22");
				System.out.println(task.getStatus());
				if(task.getStatus().equals(AsyncTask.Status.FINISHED)){
					System.out.println("33");
					task = null;
					task = new CheckDeviceTask();
					task.execute();
				}
			}
			break;*/
		//检查健康度按钮点击事件
		case R.id.check_device_score:
			if(task.getStatus().equals(AsyncTask.Status.FINISHED)){
				task = null;
				task = new CheckDeviceTask();
				task.execute();
			}
			break;
		default:
			break;
		}
	}
}
