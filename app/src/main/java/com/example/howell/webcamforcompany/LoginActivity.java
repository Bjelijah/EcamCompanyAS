package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.howell.ecamcompany.R;
import com.howell.ksoap.InternetDeviceCatalogReq;
import com.howell.ksoap.InternetDeviceCatalogRes;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.UserAgent;
import com.howell.ksoap.UserLoginReq;
import com.howell.ksoap.UserLoginRes;
import com.howell.utils.DialogUtils;
import com.howell.utils.EncryptPassword;
import com.howell.utils.UserInformation;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends Activity implements OnClickListener,TagAliasCallback,Const{
	
	
	private final static int MSG_GET_LOGIN_ID = 0x00;
	
	private Button btnLogin;
	private EditText etAccount,etPassword/*,etNetdeviceId*/;
	private SoapManager mSoapManager;
	private Dialog waitDialog;
	private SharedPreferences sharedPreferences;
	
	
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			switch (msg.what) {
			case MSG_GET_LOGIN_ID:
				if (msg.obj instanceof UserLoginRes) {
					foo((UserLoginRes)msg.obj);
				}else{
					Log.e("123", "msg obj error");
				}
				
			
				break;

			default:
				break;
			}
			
			
			super.handleMessage(msg);
		}
		
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	//	initJPush();
		
		String account = sharedPreferences.getString("account", "");
		String password = sharedPreferences.getString("password", "");
		//String netdeviceId = sharedPreferences.getString("netdeviceId", "");
		
		if(!account.equals("") && !password.equals("") /*&& !netdeviceId.equals("")*/){
			etAccount.setText(account);
			etPassword.setText(password);
//			etNetdeviceId.setText(netdeviceId);
			waitDialog = DialogUtils.postWaitDialog(LoginActivity.this);
			waitDialog.show();
			LoginTask task = new LoginTask();
			task.execute();
		}
		
	}
	
	private void init(){
		mSoapManager = SoapManager.getInstance();
		btnLogin = (Button)findViewById(R.id.btn_login);
		etAccount = (EditText)findViewById(R.id.et_account);
		etPassword = (EditText)findViewById(R.id.et_password);
//		etNetdeviceId = (EditText)findViewById(R.id.et_netdevice_id);
		btnLogin.setOnClickListener(this);
		sharedPreferences = getSharedPreferences("set",Context.MODE_PRIVATE);
	}
	
	private void initJPush(){
		JPushInterface.init(getApplicationContext());
		setAlias();
		System.out.println("jpush:"+JPushInterface.isPushStopped(getApplicationContext()));
		if(JPushInterface.isPushStopped(getApplicationContext())) 
			JPushInterface.resumePush(getApplicationContext());
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		JPushInterface.onResume(getApplicationContext());
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		JPushInterface.onPause(getApplicationContext());
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//设置JPush别名
	private void setAlias(){
		String alias = UserInformation.getAndroidPhoneIMEI(LoginActivity.this);
		JPushInterface.setAliasAndTags(getApplicationContext(), alias, null, this);
	}
	
	//登录线程
	public class LoginTask extends AsyncTask<Void, Integer, Void> {
		//UserLoginRes res = null;
		private UserLoginRes res = null;
		private UserLoginReq userLoginReq = null;
		String account,password;
		public LoginTask() {
			// TODO Auto-generated constructor stub
			
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			account = etAccount.getText().toString();
			password = etPassword.getText().toString();
		}

		@Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
        	try {
        		//用户登录协议

//    			System.out.println("kkkkkkkkkkk, user:"+account+",password:"+password+"gatewayId:"+etNetdeviceId.getText().toString());
    			userLoginReq = new UserLoginReq();
    			userLoginReq.setUsername(account);
    			userLoginReq.setUserType("InternetUser");//登录平台
    			userLoginReq.setPasswordType("Common");
				Log.i("123","passwod="+password);
    			userLoginReq.setPassword(EncryptPassword.getEncryptPassword(password));
//    			userLoginReq.setInternetDeviceId(/*"10000"*/etNetdeviceId.getText().toString());
//    			System.out.println("InternetDeviceId"+etNetdeviceId.getText().toString());
    			userLoginReq.setApplicationId("yancao");
    			userLoginReq.setApplicationVersion("1.0.0");
    			String networkOperator = UserInformation.getProvidersName(LoginActivity.this);
    			System.out.println("networkOperator:"+networkOperator);
    			if(networkOperator == null){
    				userLoginReq.setNetworkOperator("ChinaMobile");
    			}else {
	    			if(networkOperator.equals("中国移动")){
	    				userLoginReq.setNetworkOperator("ChinaMobile");
	    			}else if(networkOperator.equals("中国电信")){
	    				userLoginReq.setNetworkOperator("ChinaTelecom");
	    			}else if(networkOperator.equals("中国联通")){
	    				userLoginReq.setNetworkOperator("ChinaUnicom");
	    			}else{
	    				userLoginReq.setNetworkOperator("ChinaNetcom");
	    			}
    			}
	    		int networkType = UserInformation.getNetType(LoginActivity.this);
	    		System.out.println("networkType:"+networkType);
	    		if(networkType == 1){
	    			userLoginReq.setNetworkType("WiFi");//WIFI
	    		}else if(networkType == 3){
	    			userLoginReq.setNetworkType("3G");//3G
	    		}else{
	    			userLoginReq.setNetworkType("Other");//OTHER
	    		}
    			
    			UserAgent userAgent = new UserAgent();
    			userAgent.setuUID(UserInformation.getAndroidPhoneIMEI(LoginActivity.this));
    			System.out.println("UUID:"+UserInformation.getAndroidPhoneIMEI(LoginActivity.this));
    			userAgent.setModel(UserInformation.getDeviceModel());
    			System.out.println("Manufactory:"+UserInformation.checkMake()+"model:"+UserInformation.getDeviceModel()+" version:"+UserInformation.getDeviceVersion());
    			userAgent.setName("e看企业版");
    			userAgent.setAgentType("CellPhone");
    			userAgent.setAgentOSType("Android");
    			if(UserInformation.checkMake() != null)
    				userAgent.setManufactory(UserInformation.checkMake());
    			else
    				userAgent.setManufactory("NULL");
    			userAgent.setoSVersion(UserInformation.getDeviceVersion());
    			userAgent.setiMEI(UserInformation.getAndroidPhoneIMEI(LoginActivity.this));
    			System.out.println("imei:"+UserInformation.getAndroidPhoneIMEI(LoginActivity.this));
    			userLoginReq.setUserAgent(userAgent);
				Log.i("123","userLoginReq="+userLoginReq.toString());
    			res = mSoapManager.getUserLoginRes(userLoginReq);
    			
        	} catch (Exception e) {
				// TODO: handle exception
        		System.out.println("login crash");
			}
            return null;
        }
        
        @Override
        protected void onPostExecute(Void result) {
        	// TODO Auto-generated method stub
        	super.onPostExecute(result);
        	waitDialog.dismiss();
        	if(res.getResult() != null){
	        	if(res.getResult().toString().equals("OK")){
	        		//用户名 密码 id存于配置文件中
	        		sharedPreferences = getSharedPreferences("set", Context.MODE_PRIVATE);
                    Editor editor = sharedPreferences.edit();
                    editor.putString("account", etAccount.getText().toString());
                    editor.putString("password", etPassword.getText().toString());
//                    editor.putString("netdeviceId", etNetdeviceId.getText().toString());
                    editor.commit();
                    
                    //如果登录平台 先获取internetid
                    System.out.println("userLoginReq.getInternetDeviceId():"+userLoginReq.getInternetDeviceId());
                    if(userLoginReq.getUserType().equals("InternetUser")){
	                  
	                    Message msg = new Message();
	                    msg.what = MSG_GET_LOGIN_ID;
	                    msg.obj = res;
                    	
	                    handler.sendMessage(msg);
                    
                    }
                    
					Intent intent = new Intent (LoginActivity.this,CamTabActivity.class);
					startActivity(intent);
				}else if(res.getResult().toString().equals("Offline")){
	        		Dialog alertDialog = new AlertDialog.Builder(LoginActivity.this).   
	        	            setTitle("登录失败").   
	        	            setMessage("设备不在线，请稍后重新登录").   
	        	            setIcon(R.drawable.expander_ic_minimized).   
	        	            setPositiveButton("确定", new DialogInterface.OnClickListener() {   
	        	                @Override   
	        	                public void onClick(DialogInterface dialog, int which) {   
	        	                    // TODO Auto-generated method stub  
	        	                	//finish();
	        	                }   
	        	            }).   
	        	    create();   
	        		alertDialog.show();   
	        	}else {
	        		Dialog alertDialog = new AlertDialog.Builder(LoginActivity.this).   
	        	            setTitle("登录失败").   
	        	            setMessage("用户名或密码错误").   
	        	            setIcon(R.drawable.expander_ic_minimized).   
	        	            setPositiveButton("确定", new DialogInterface.OnClickListener() {   
	        	                @Override   
	        	                public void onClick(DialogInterface dialog, int which) {   
	        	                    // TODO Auto-generated method stub  
	        	                	//finish();
	        	                }   
	        	            }).   
	        	    create();   
	        		alertDialog.show();   
	        	}
        	}else{
        		Dialog alertDialog = new AlertDialog.Builder(LoginActivity.this).   
        	            setTitle("登录失败").   
        	            setMessage("网络信号差，请稍后重新登录").   
        	            setIcon(R.drawable.expander_ic_minimized).   
        	            setPositiveButton("确定", new DialogInterface.OnClickListener() {   
        	                @Override   
        	                public void onClick(DialogInterface dialog, int which) {   
        	                    // TODO Auto-generated method stub  
        	                	//finish();
        	                }   
        	            }).   
        	    create();   
        		alertDialog.show();   
        	}
        }
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			waitDialog = DialogUtils.postWaitDialog(LoginActivity.this);
			waitDialog.show();
			LoginTask task = new LoginTask();
			task.execute();
			break;

		default:
			break;
		}
	}

	//JPush回调，用于判断别名设置是否成功
	@Override
	public void gotResult(int code, String alias, Set<String> tags) {
		// TODO Auto-generated method stub
		String logs ;
		switch (code) {
		case 0:
			logs = "Set tag and alias success, alias = " + alias + "; tags = " + tags;
			Log.i("", logs);
			break;
		
		default:
			logs = "Failed with errorCode = " + code + " alias = " + alias + "; tags = " + tags;
			Log.e("", logs);
		}
		//ExampleUtil.showToast(logs, getApplicationContext());
	}

	private void foo(final UserLoginRes res){//add by cbj because httpClient used to run in main thread.. 
		Log.i("123", "foo");
		new Thread(){
			public void run() {
				 InternetDeviceCatalogReq internetDeviceCatalogReq = new InternetDeviceCatalogReq();
		          internetDeviceCatalogReq.setSession(res.getSession());
		          InternetDeviceCatalogRes internetDeviceCatalogRes =mSoapManager.getInternetDeviceCatalogRes(internetDeviceCatalogReq);
		          mSoapManager.setInternetDeviceId(internetDeviceCatalogRes.getInternetDevices().get(0).getId());
			};
		}.start();
	}
	
	
}
