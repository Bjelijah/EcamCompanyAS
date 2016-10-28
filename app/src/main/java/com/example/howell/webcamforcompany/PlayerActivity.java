package com.example.howell.webcamforcompany;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.howell.ecamcompany.R;
import com.howell.ksoap.VideoSource;
import com.howell.render.YV12Renderer;
import com.howell.utils.DetectIfHasFrameUtils;
import com.howell.utils.PhoneConfig;
import com.howell.utils.VideoInviteUtils;

import java.util.ArrayList;


public class PlayerActivity extends Activity implements Callback,OnClickListener,OnGestureListener{

	private VideoSource videoSource;
	private VideoInviteUtils inviteUtils;
	private GLSurfaceView mGlView;
	private ProgressBar progressBar;
	private DetectIfHasFrameUtils hasFrameUtils;
	private LinearLayout settingLayout;

	//private ImageButton mPauseBtn,mVideoListBtn,mTakePhotoBtn,mAdjustVoiceBtn;

	private PlayerHandler handler;
	private static final int IS_FIRST_FRAME_ARRIVE 		= 1;
	private static final int DETECT_IF_NO_FRAME_ARRIVE 	= 2;
	private static final int REINVITE_VIDEO 			= 3;
	private static final int INVITE_NEXT_VIDEO 			= 4;

	private static final int TEST_NEW					= 5;
	private static final int TEST_FREE					= 6;

	private GestureDetector mGestureDetector;
	private ArrayList<VideoSource> videoSourcesList;
	private boolean isReinvite; 
	private int position=0;

	private String mNextId = "";
	private boolean mIsNext = false;
	private boolean isChanging = false;

	private int testNum = 0;

	private String internetDeviceId;
	static {
		System.loadLibrary("hwplay");
		System.loadLibrary("player_jni");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.player);
		//		Log.i("log123", "oncreat");
		mGestureDetector = new GestureDetector(this, this);

		init();

		//请求视频流
		InviteTask inviteTask = new InviteTask(inviteUtils);
		inviteTask.execute();
		//判断waitProgress是否显示
		handler.sendEmptyMessage(IS_FIRST_FRAME_ARRIVE);
	}




	//初始化

	@SuppressWarnings("unchecked")
	private void init(){
		//		System.out.println("play activity init");
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);//屏幕常亮
		hasFrameUtils = new DetectIfHasFrameUtils();
		handler = new PlayerHandler();
		mGlView = (GLSurfaceView)findViewById(R.id.glsurface_view);
		mGlView.setEGLContextClientVersion(2);
		mGlView.setRenderer(new YV12Renderer(this,mGlView,hasFrameUtils));
		mGlView.getHolder().addCallback((Callback) this);
		mGlView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		//		mGlView.setOnClickListener(this);
		progressBar = (ProgressBar)findViewById(R.id.player_progressbar);
		settingLayout = (LinearLayout)findViewById(R.id.surface_icons);
		Intent intent = getIntent();
		videoSource = (VideoSource)intent.getSerializableExtra("videosource");
		inviteUtils = new VideoInviteUtils(videoSource.getId());

		videoSourcesList = (ArrayList<VideoSource>) intent.getSerializableExtra("videoSourceList");
		position = (Integer) intent.getSerializableExtra("position");
		//		Log.i("log123", "pos ="+position + "name="+videoSourcesList.get(position).getName());
		//test
	}

	class PlayerHandler extends Handler{

		private boolean destoryPlayer ;

		public PlayerHandler() {
			super();
			this.destoryPlayer = false;
		}
		public void setDestoryPlayer(boolean destoryPlayer) {
			this.destoryPlayer = destoryPlayer;
		}
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case IS_FIRST_FRAME_ARRIVE:
				if(!handler.destoryPlayer){
					if(hasFrameUtils.getCurrentFrames() <= 2){
						handler.sendEmptyMessageDelayed(IS_FIRST_FRAME_ARRIVE, 100);
					}else if(hasFrameUtils.getCurrentFrames() >= 3){
						progressBar.setVisibility(View.GONE);
						handler.sendEmptyMessage(DETECT_IF_NO_FRAME_ARRIVE);
					}
				}
				break;
			case DETECT_IF_NO_FRAME_ARRIVE:
				//	System.out.println("currentFrameCount:"+hasFrameUtils.getCurrentFrames()+" lastMonmentFrameCount:"+hasFrameUtils.getLastMonmentFrameCount());
				if(!handler.destoryPlayer){
					if(hasFrameUtils.hasFrame()){
						progressBar.setVisibility(View.GONE);
					}else{
						progressBar.setVisibility(View.VISIBLE);
					}
					hasFrameUtils.setLastMonmentFrameCount();
					if(hasFrameUtils.getCurrentFrames() >= 2000000000/*1000*/){
						hasFrameUtils.clearCount();
					}
					handler.sendEmptyMessageDelayed(DETECT_IF_NO_FRAME_ARRIVE, 1000);
				}
				break;
			case REINVITE_VIDEO:
			{
				isReinvite = true;
				inviteUtils = new VideoInviteUtils(videoSource.getId());
				InviteTask inviteTask = new InviteTask(inviteUtils);
				inviteTask.execute();

			}
			break;

			case INVITE_NEXT_VIDEO:
			{
				inviteUtils = new VideoInviteUtils(mNextId);
				//				String bar = inviteUtils.getInternetDeviceId();
				//				if (bar==null) {
				//					inviteUtils.setInternetDeviceId(internetDeviceId);
				//				}else{
				//					internetDeviceId = bar;
				//				}
				ChangeVideoTask cvTask = new ChangeVideoTask(inviteUtils, mNextId, mIsNext);
				cvTask.execute();
				//				InviteTask inviteTask = new InviteTask(inviteUtils);
				//				inviteTask.execute();

			}
			break;
			case TEST_NEW:
				testNum++;
				Log.i("log123", "test num = "+testNum);
				TestNewTask testNewTask = new TestNewTask();
				testNewTask.execute();
				break;
			case TEST_FREE:
				TestFreeTask testFreeTask = new TestFreeTask();
				testFreeTask.execute();
				break;

			default:
				break;
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("log123", "onDestroty");

		super.onDestroy();
		handler.setDestoryPlayer(true);

		while(true){
			if(inviteUtils != null){
				inviteUtils.joinThread(inviteUtils.getHandle());
				break;
			}
		}


		//		if(inviteUtils != null && inviteUtils.getHandle()!=-1){
		//			Log.i("log123", "join thread");
		//			inviteUtils.joinThread(inviteUtils.getHandle());
		//			Log.i("log123", "join thread ok");
		//		}

		System.out.println("stop audio");
		YV12Renderer.nativeDeinit();
		finish();
		MyQuitTask mTask = new MyQuitTask(inviteUtils);
		mTask.execute();
		Log.i("log123", "ondestroy ok");

		//test
		handler.removeMessages(TEST_NEW);
		handler.removeMessages(TEST_FREE);

	}

	//请求视频协议
	public class InviteTask extends AsyncTask<Void, Integer, Void> {
		private VideoInviteUtils inviteUtils;
		private boolean inviteRet;
		public InviteTask(VideoInviteUtils inviteUtils){
			this.inviteUtils = inviteUtils;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			System.out.println("start invite live");

			try {
				inviteRet = inviteUtils.InviteLive(2);
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("log123", "invite live error");
				inviteRet = false;
			}

			//	System.out.println("finish invite live");
			Log.i("log123", "finish invite live inviteRet =:"+inviteRet);
			return null;
		}

		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			//super.onPreExecute();
			super.onPostExecute(result);
			if(!inviteRet && !isDestroyed()){
				if(isReinvite){
					isReinvite = false;
					finish();
					return;
				}
				System.out.println("inviteRet:"+inviteRet);
				Dialog alertDialog = new AlertDialog.Builder(PlayerActivity.this).   
						setTitle("连接视频失败").   
						setMessage("网络信号差，请稍后重新登录").   
						setIcon(R.drawable.expander_ic_minimized).   
						setPositiveButton("确定", new DialogInterface.OnClickListener() {   
							@Override   
							public void onClick(DialogInterface dialog, int which) {   
								// TODO Auto-generated method stub  
								Log.i("log123", "请求视频协议  链接失败");
								finish();
							}   
						}).   
						create();   
				alertDialog.show();   
			}
		}
	}

	public class MyQuitTask extends AsyncTask<Void, Integer, Void> {
		private VideoInviteUtils inviteUtils;
		public MyQuitTask(VideoInviteUtils inviteUtils){
			this.inviteUtils = inviteUtils;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(inviteUtils != null && inviteUtils.getHandle() != -1){
				System.out.println("isStartFinish:"+inviteUtils.isStartFinish()+","+inviteUtils.toString());
				while(true){
					if(inviteUtils.isStartFinish()){
						System.out.println("free handle");

						inviteUtils.freeHandle(inviteUtils.getHandle());
						//						inviteUtils.freeAllHandle();
						break;
					}
				}
			}
			//System.out.println("release audio");
			//audioRelease();
			if(inviteUtils != null){
				if(inviteUtils.getHandle()>=0){

					try {
						inviteUtils.InviteTeardown();
					} catch (Exception e) {
						Log.e("log123", "invite tear down");
					}

				}
			}
			System.out.println("finish activity");
			return null;
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.glsurface_view:
			if(PhoneConfig.getPhoneHeight(this) < PhoneConfig.getPhoneWidth(this)){
				if(settingLayout.isShown()){
					settingLayout.setVisibility(View.GONE);
				}else{
					settingLayout.setVisibility(View.VISIBLE);
				}

			}
			break;

		default:
			break;
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
			settingLayout.setVisibility(View.GONE);
		}else{
			settingLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		if(PhoneConfig.getPhoneHeight(this) < PhoneConfig.getPhoneWidth(this)){
			if(settingLayout.isShown()){
				settingLayout.setVisibility(View.GONE);
			}else{
				settingLayout.setVisibility(View.VISIBLE);
			}
		}
		return false;
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}



	@SuppressWarnings("unused")
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		System.out.println("onFling");
		final int FLING_MIN_DISTANCE = 50, FLING_MIN_VELOCITY = 100;   
		if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {   
			if (isChanging) {
				return false;
			}
			isChanging = true;
			// Fling left   
			Log.e("log123", "Fling right "+"x:"+Math.abs(e1.getX() - e2.getX())+"y:"+Math.abs(e1.getY() - e2.getY()));   
			//判断网络状况
			//			if (!NetWorkUtil.isNetConnect(this)) {
			//				//链接失败
			//				DialogUtils.postDialogWithPositiveButton(this, "连接视频失败", "网络信号差，请稍后重新登录", R.drawable.expander_ic_minimized, "确定", new DialogInterface.OnClickListener() {
			//					@Override
			//					public void onClick(DialogInterface dialog, int which) {
			//						// TODO Auto-generated method stub
			//
			//					}
			//				}).show();
			//				return false;
			//			}

			//上一个视频
			Log.i("log123", "pos = "+position);
			int pos = position+1>videoSourcesList.size()-1?0:position+1;
			String id = videoSourcesList.get(pos).getId();

			mNextId = id;
			mIsNext = true;
			Log.i("log123", "next video="+videoSourcesList.get(pos).getName());
			//			ChangeVideoTask cvTask = new ChangeVideoTask(inviteUtils,id,true);
			//			cvTask.execute();
			StopVideoTask stopVideoTask = new StopVideoTask(inviteUtils);
			stopVideoTask.execute();



		} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {   

			if (isChanging) {
				return false;
			}
			isChanging = true;
			// Fling right  
			Log.e("log123", "Fling left "+"x:"+Math.abs(e1.getX() - e2.getX())+"y:"+Math.abs(e1.getY() - e2.getY()));  
			//判断网络状况TODO
			//			if (!NetWorkUtil.isNetConnect(this)) {
			//				//链接失败
			//				DialogUtils.postDialogWithPositiveButton(this, "连接视频失败", "网络信号差，请稍后重新登录", R.drawable.expander_ic_minimized, "确定", new DialogInterface.OnClickListener() {
			//					@Override
			//					public void onClick(DialogInterface dialog, int which) {
			//						// TODO Auto-generated method stub
			//
			//					}
			//				}).show();
			//				return false;
			//			}



			//下一个视频
			Log.i("log123", "pos = "+position);
			int pos = position-1<0?videoSourcesList.size()-1:position-1;
			String id = videoSourcesList.get(pos).getId();
			mNextId = id;
			mIsNext = false;
			Log.i("log123", "last video="+videoSourcesList.get(pos).getName());
			//			ChangeVideoTask cvTask = new ChangeVideoTask(inviteUtils,id,false);
			//			cvTask.execute();
			StopVideoTask stopVideoTask = new StopVideoTask(inviteUtils);
			stopVideoTask.execute();
		}  else if (e2.getY() - e1.getY() > FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {   
			// Fling Down   

			Log.e("log123", "Fling Down "+"y:"+Math.abs(e1.getY() - e2.getY())+"x:"+Math.abs(e1.getX() - e2.getX()));   


			//handler.sendEmptyMessage(TEST_NEW);



		}   else if (e1.getY() - e2.getY() > FLING_MIN_DISTANCE && Math.abs(velocityY) > FLING_MIN_VELOCITY) {   
			// Fling Up   
			Log.e("log123", "Fling Up "+"y:"+Math.abs(e1.getY() - e2.getY())+"x:"+Math.abs(e1.getX() - e2.getX()));   

		}   else{
			return true;
		}



		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(event);
	}

	public class StopVideoTask extends AsyncTask<Void, Void, Void>{
		private VideoInviteUtils inviteUtils=null;
		public StopVideoTask(VideoInviteUtils inviteUtils) {
			this.inviteUtils = inviteUtils;
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(inviteUtils!=null&&inviteUtils.getHandle()>-1){

				if(inviteUtils != null && inviteUtils.getHandle()>-1){
					Log.i("log123", "changetask joinThread");
					inviteUtils.joinThread(inviteUtils.getHandle());
				}


				if(inviteUtils.isStartFinish() && inviteUtils.getHandle()>-1){
					Log.i("log123", "changetask free handle");
					inviteUtils.freeHandle(inviteUtils.getHandle());

				}
				try {
					inviteUtils.InviteTeardown();
				} catch (Exception e) {
					Log.e("log123", "invite teardown error");		
				}

				Log.i("log123", "flip stop ok");
			}
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			//			handler.sendEmptyMessage(INVITE_NEXT_VIDEO);
			handler.sendEmptyMessageDelayed(INVITE_NEXT_VIDEO, 1000);
			super.onPostExecute(result);
		}
	}

	public class ChangeVideoTask extends AsyncTask<Void, Void, Void>{

		private VideoInviteUtils inviteUtils=null;
		String newVideoId;
		private boolean inviteRet = false;
		private boolean isNextVideo;
		private boolean bNeedReLogin = false;
		public ChangeVideoTask(VideoInviteUtils inviteUtils,String newId,boolean isNext) {
			// TODO Auto-generated constructor stub
			this.inviteUtils = inviteUtils;
			this.newVideoId = newId;
			this.isNextVideo = isNext;
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			//wait dialog FIXME
			//stop 
			if(inviteUtils!=null&&inviteUtils.getHandle()>-1){
				//				while(true){
				//					if(inviteUtils != null){
				//						Log.i("log123", "changetask joinThread");
				//						inviteUtils.joinThread(inviteUtils.getHandle());
				//						break;
				//					}
				//				}
				//
				//				while(true){
				//					if(inviteUtils.isStartFinish()){
				//						Log.i("log123", "changetask free handle");
				//						inviteUtils.freeHandle(inviteUtils.getHandle());
				//						break;
				//					}
				//				}
				//				inviteUtils.InviteTeardown();
				//				Log.i("log123", "flip stop ok");
			}
			//play
			//			inviteUtils.setId(newVideoId);

			try {
				inviteRet = inviteUtils.InviteLive(2);	
			} catch (Exception e) {
				Log.e("log123", "inviteLive error");
				inviteRet = false;
			}
//			inviteRet = false;//test
//			bNeedReLogin = true;

			//			inviteRet = inviteUtils.changeVideo(newVideoId);
			return null;
		}
		@SuppressLint("NewApi")
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(!inviteRet && !isDestroyed()){
				System.out.println("inviteRet:"+inviteRet);
				if (inviteUtils.getNeedReLogin()) {
					this.bNeedReLogin = true;
				}
				Dialog alertDialog = new AlertDialog.Builder(PlayerActivity.this).   
						setTitle("连接视频失败").   
						setMessage("网络信号差，请稍后重新登录").   
						setIcon(R.drawable.expander_ic_minimized).   
						setPositiveButton("确定", new DialogInterface.OnClickListener() {   
							@Override   
							public void onClick(DialogInterface dialog, int which) {   
								// TODO Auto-generated method stub  
								//								finish();
								//重新链接当前video
								Log.i("log123", "重新链接当前video");
								if (bNeedReLogin) {
									Log.i("log123", "relogin...");
									SharedPreferences preferences = getSharedPreferences("relogin", MODE_PRIVATE);   
									Editor editor = preferences.edit();   
									editor.putBoolean("needReLogin", true);   
									editor.commit();  					
								}
								SharedPreferences preferences = getSharedPreferences("VideoSourceList", MODE_PRIVATE);   
								Editor editor = preferences.edit();   
								
								editor.putInt("position", position);
								editor.commit();  																							
								finish();
								//handler.sendEmptyMessage(REINVITE_VIDEO);
							}   
						}).   
						create();   
				alertDialog.show();   
			}else{
				if (isNextVideo) {
					position = position+1>videoSourcesList.size()-1?0:position+1;
				}else{
					position =  position-1<0?videoSourcesList.size()-1:position-1;
				}
				Log.i("log123", "onPostExecute pos = "+position+"num="+num);
				//				inviteUtils.inviteTearOldDown();

//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				test();
			}
			isChanging = false;
		}
	}
	private int num = 0;
	public void test(){
		num++;
		if(num == 200){
			Log.i("log123", "test over congi");
			return;
		}

		Log.i("log123", "start test");
		int pos = position-1<0?videoSourcesList.size()-1:position-1;
		String id = videoSourcesList.get(pos).getId();
		mNextId = id;
		mIsNext = false;
		Log.i("log123", "last video="+videoSourcesList.get(pos).getName());
		//		ChangeVideoTask cvTask = new ChangeVideoTask(inviteUtils,id,false);
		//		cvTask.execute();
		StopVideoTask stopVideoTask = new StopVideoTask(inviteUtils);
		stopVideoTask.execute();
	}


	public class TestNewTask extends AsyncTask<Void, Void, Void>{
		int res = 0;


		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			inviteUtils.testNewResource();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			handler.sendEmptyMessageDelayed(TEST_FREE, 1000);
			super.onPostExecute(result);
		}

	}

	public class TestFreeTask extends AsyncTask<Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			inviteUtils.testFreeResource();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			handler.sendEmptyMessageAtTime(TEST_NEW, 1000);
			super.onPostExecute(result);
		}
	}
}
