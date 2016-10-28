package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.howell.ecamcompany.R;
import com.howell.ksoap.DeviceIOInputChannelCatalogReq;
import com.howell.ksoap.DeviceIOInputChannelCatalogRes;
import com.howell.ksoap.DeviceIOOutputChannelCatalogReq;
import com.howell.ksoap.DeviceIOOutputChannelCatalogRes;
import com.howell.ksoap.DeviceNetworkInterfaceCatalogReq;
import com.howell.ksoap.DeviceNetworkInterfaceCatalogRes;
import com.howell.ksoap.DeviceStorageMediumCatalogReq;
import com.howell.ksoap.DeviceStorageMediumCatalogRes;
import com.howell.ksoap.DeviceVideoInputChannelCatalogReq;
import com.howell.ksoap.DeviceVideoInputChannelCatalogRes;
import com.howell.ksoap.EventSubscription;
import com.howell.ksoap.EventSubscriptionCatalogReq;
import com.howell.ksoap.EventSubscriptionCatalogRes;
import com.howell.ksoap.IOInputChannel;
import com.howell.ksoap.IOOutputChannel;
import com.howell.ksoap.NetworkInterface;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.StorageMedium;
import com.howell.ksoap.SubscribeEventReq;
import com.howell.ksoap.SubscribeEventRes;
import com.howell.ksoap.UnsubscribeEventReq;
import com.howell.ksoap.UnsubscribeEventRes;
import com.howell.ksoap.VideoInputChannel;
import com.howell.utils.DialogUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 霍之昊 
 *
 * 类说明：订阅设备模块界面
 */
public class SetDeviceNotificationActivity extends Activity implements OnClickListener{
	
	private GetDeviceDifferentCatalogtTask task;
	private ListView setNotificationListView;

	private ImageButton back;
	private ArrayList<Object> modeList;
	private SetNotificationAdapter adapter;
	private Dialog waitDialog;
	private int whichMode;
	
	private String deviceId;
//	private ArrayList<EventSubscription> eventSubscriptionList;
	private boolean[] isSubscribedList;
	private String eventType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_notification_activity);
		modeList = new ArrayList<Object>();
		back = (ImageButton)findViewById(R.id.set_notification_back);
		back.setOnClickListener(this);
		setNotificationListView = (ListView)findViewById(R.id.set_notification_listview);
		adapter = new SetNotificationAdapter(this);
		setNotificationListView.setAdapter(adapter);
		Intent intent = getIntent();
		deviceId = intent.getStringExtra("deviceId");
		eventType = intent.getStringExtra("eventType");
		System.out.println("eventType"+eventType);
		whichMode = intent.getIntExtra("whichMode", 0);
		System.out.println("whichMode:"+whichMode);
		switch (whichMode) {
		case 0:
			//self mode
			startWaitingAnimation();
			task = new GetDeviceDifferentCatalogtTask(null);
			task.execute();
			break;
		case 1:
			startWaitingAnimation();
			task = new GetDeviceDifferentCatalogtTask(new DeviceVideoInputChannelCatalogReq(SoapManager.getInstance().getUserLoginRes().getSession(),SoapManager.getInstance().getInternetDeviceId(),deviceId));
			task.execute();
			break;
		case 2:
			startWaitingAnimation();
			task = new GetDeviceDifferentCatalogtTask(new DeviceIOInputChannelCatalogReq(SoapManager.getInstance().getUserLoginRes().getSession(),SoapManager.getInstance().getInternetDeviceId(),deviceId));
			task.execute();
			break;
		case 3:
			startWaitingAnimation();
			task = new GetDeviceDifferentCatalogtTask(new DeviceIOOutputChannelCatalogReq(SoapManager.getInstance().getUserLoginRes().getSession(),SoapManager.getInstance().getInternetDeviceId(),deviceId));
			task.execute();
			break;
		case 4:
			startWaitingAnimation();
			task = new GetDeviceDifferentCatalogtTask(new DeviceStorageMediumCatalogReq(SoapManager.getInstance().getUserLoginRes().getSession(),SoapManager.getInstance().getInternetDeviceId(),deviceId));
			task.execute();
			break;
		case 5:
			startWaitingAnimation();
			task = new GetDeviceDifferentCatalogtTask(new DeviceNetworkInterfaceCatalogReq(SoapManager.getInstance().getUserLoginRes().getSession(),SoapManager.getInstance().getInternetDeviceId(),deviceId));
			task.execute();
			break;
		default:
			break;
		}
	}
	
	class SetNotificationAdapter extends BaseAdapter {

		private Context mContext;
		
		public SetNotificationAdapter(Context context) {
	        this.mContext = context;
	    }
		
		@Override
	    public int getCount() {
	        // TODO Auto-generated method stub
	        return modeList.size() ;
	    }

	    @Override
	    public Object getItem(int position) {
	        // TODO Auto-generated method stub
	        return modeList.get(position) ;
	    }

	    @Override
	    public long getItemId(int position) {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        // TODO Auto-generated method stub
	    	ViewHolder holder = null;
	    	if (convertView == null) {
	    		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
	    		convertView = layoutInflater.inflate(R.layout.set_notification_item, null);
				holder = new ViewHolder();
					
				holder.modeName = (TextView)convertView.findViewById(R.id.set_notification_textview);
	            holder.imageBtn = (ImageButton)convertView.findViewById(R.id.set_notification_imagebutton);
				convertView.setTag(holder);
	    	}else{
	         	holder = (ViewHolder)convertView.getTag();
	        }
	        //holder.mPlayBtn.setTag(position);
	    	if(whichMode == 1){
	    		VideoInputChannel videoInputChannel = (VideoInputChannel)(modeList.get(position));
	    		holder.modeName.setText(videoInputChannel.getName());
	    	}else if(whichMode == 2){
	    		IOInputChannel iOInputChannel = (IOInputChannel)(modeList.get(position));
	    		holder.modeName.setText(iOInputChannel.getName());
	    	}else if(whichMode == 3){
	    		IOOutputChannel iOOutputChannel = (IOOutputChannel)(modeList.get(position));
    			holder.modeName.setText(iOOutputChannel.getName());
	    	}else if(whichMode == 4){
	    		StorageMedium storageMedium = (StorageMedium)(modeList.get(position));
    			holder.modeName.setText(storageMedium.getManufacturer() + " " + storageMedium.getModel() );
	    	}else if(whichMode == 5){
	    		NetworkInterface networkInterface = (NetworkInterface)(modeList.get(position));
    			holder.modeName.setText(networkInterface.getiPAddress());
	    	}else if(whichMode == 0){
	    		holder.modeName.setText(modeList.get(position).toString());
	    	}
	    	if(isSubscribedList[position]){
	    		holder.imageBtn.setImageDrawable(getResources().getDrawable(R.mipmap.set_notification_item_added));
	    	}else{
	    		holder.imageBtn.setImageDrawable(getResources().getDrawable(R.mipmap.set_notification_item_add));
	    	}
	    	holder.imageBtn.setOnClickListener(SetDeviceNotificationActivity.this);
	    	holder.imageBtn.setTag(position);
			return convertView;
	    }
	    
	    class ViewHolder {
	        public TextView modeName;
	        public ImageButton imageBtn;
	    }
	}
	
	class GetDeviceDifferentCatalogtTask extends AsyncTask<Void, Integer, Void> {
		private Object deviceCatalogReq;
		private SoapManager mSoapManager;
		private DeviceVideoInputChannelCatalogRes deviceVideoInputChannelCatalogRes;
		private DeviceIOInputChannelCatalogRes deviceIOInputChannelCatalogRes;
		private DeviceIOOutputChannelCatalogRes deviceIOOutputChannelCatalogRes;
		private DeviceStorageMediumCatalogRes deviceStorageMediumCatalogRes;
		private DeviceNetworkInterfaceCatalogRes deviceNetworkInterfaceCatalogRes;
		
		private EventSubscriptionCatalogRes eventSubscriptionCatalogRes;
		
		public GetDeviceDifferentCatalogtTask(Object deviceCatalog) {
			// TODO Auto-generated constructor stub
			this.deviceCatalogReq = deviceCatalog;
			mSoapManager = SoapManager.getInstance();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			if(deviceCatalogReq == null){
				eventSubscriptionCatalogRes = mSoapManager.getEventSubscriptionCatalogRes(new EventSubscriptionCatalogReq(mSoapManager.getUserLoginRes().getSession(),mSoapManager.getInternetDeviceId(),eventType));
			}else if(deviceCatalogReq instanceof DeviceVideoInputChannelCatalogReq){//查询设备下的视频输入通道目录
				deviceVideoInputChannelCatalogRes = mSoapManager.getDeviceVideoInputChannelCatalogRes((DeviceVideoInputChannelCatalogReq)deviceCatalogReq);
				eventSubscriptionCatalogRes = mSoapManager.getEventSubscriptionCatalogRes(new EventSubscriptionCatalogReq(mSoapManager.getUserLoginRes().getSession(),mSoapManager.getInternetDeviceId(),eventType));
			}else if(deviceCatalogReq instanceof DeviceIOInputChannelCatalogReq){//查询设备下的报警输入通道目录 
				deviceIOInputChannelCatalogRes = mSoapManager.getDeviceIOInputChannelCatalogRes((DeviceIOInputChannelCatalogReq)deviceCatalogReq);
				eventSubscriptionCatalogRes = mSoapManager.getEventSubscriptionCatalogRes(new EventSubscriptionCatalogReq(mSoapManager.getUserLoginRes().getSession(),mSoapManager.getInternetDeviceId(),eventType));
			}else if(deviceCatalogReq instanceof DeviceIOOutputChannelCatalogReq){//查询设备下的报警输出通道目录 
				deviceIOOutputChannelCatalogRes = mSoapManager.getDeviceIOOutputChannelCatalogRes((DeviceIOOutputChannelCatalogReq)deviceCatalogReq);
				eventSubscriptionCatalogRes = mSoapManager.getEventSubscriptionCatalogRes(new EventSubscriptionCatalogReq(mSoapManager.getUserLoginRes().getSession(),mSoapManager.getInternetDeviceId(),eventType));
			}else if(deviceCatalogReq instanceof DeviceStorageMediumCatalogReq){//查询设备下的存储媒介
				deviceStorageMediumCatalogRes = mSoapManager.getDeviceStorageMediumCatalogRes((DeviceStorageMediumCatalogReq)deviceCatalogReq);
				eventSubscriptionCatalogRes = mSoapManager.getEventSubscriptionCatalogRes(new EventSubscriptionCatalogReq(mSoapManager.getUserLoginRes().getSession(),mSoapManager.getInternetDeviceId(),eventType));
			}else if(deviceCatalogReq instanceof DeviceNetworkInterfaceCatalogReq){//查询设备下的网口信息
				deviceNetworkInterfaceCatalogRes = mSoapManager.getDeviceNetworkInterfaceCatalogRes((DeviceNetworkInterfaceCatalogReq)deviceCatalogReq);
				eventSubscriptionCatalogRes = mSoapManager.getEventSubscriptionCatalogRes(new EventSubscriptionCatalogReq(mSoapManager.getUserLoginRes().getSession(),mSoapManager.getInternetDeviceId(),eventType));
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(deviceVideoInputChannelCatalogRes != null){
				if(deviceVideoInputChannelCatalogRes.getResult() != null){//获取成功
					modeList.addAll(deviceVideoInputChannelCatalogRes.getVideoInputChannels()) ;
//					adapter.notifyDataSetChanged();
				}else{												//获取失败
					showFailureDialog();
				}
			}else if(deviceIOInputChannelCatalogRes != null){
				if(deviceIOInputChannelCatalogRes.getResult() != null){//获取成功
					modeList.addAll(deviceIOInputChannelCatalogRes.getiOInputChannels());
//					adapter.notifyDataSetChanged();
				}else{												//获取失败
					showFailureDialog();
				}
			}else if(deviceIOOutputChannelCatalogRes != null){
				if(deviceIOOutputChannelCatalogRes.getResult() != null){//获取成功
					modeList.addAll(deviceIOOutputChannelCatalogRes.getiOOutputChannels());
//					adapter.notifyDataSetChanged();
				}else{												//获取失败
					showFailureDialog();
				}
			}else if(deviceStorageMediumCatalogRes != null){
				if(deviceStorageMediumCatalogRes.getResult() != null){//获取成功
					modeList.addAll(deviceStorageMediumCatalogRes.getStorageMediums());
//					eventSubscriptionList = eventSubscriptionCatalogRes.getEventSubscriptions();
//					adapter.notifyDataSetChanged();
				}else{												//获取失败
					showFailureDialog();
				}
			}else if(deviceNetworkInterfaceCatalogRes != null){
				if(deviceNetworkInterfaceCatalogRes.getResult() != null){//获取成功
					modeList.addAll(deviceNetworkInterfaceCatalogRes.getNetworkInterfaces());
//					adapter.notifyDataSetChanged();
				}else{												//获取失败
					showFailureDialog();
				}
			}else{
				String failure[] = getResources().getStringArray(R.array.device_failure);
				modeList.addAll(Arrays.asList(failure));
			}
			setSubscribedList(modeList,eventSubscriptionCatalogRes.getEventSubscriptions());
			adapter.notifyDataSetChanged();
			//结束等待动画
			finishWaitingAnimation();
			super.onPostExecute(result);
		}
	}
	
	private void setSubscribedList(ArrayList<Object> modeList, ArrayList<EventSubscription> eventSubscriptionList){
		isSubscribedList = new boolean[modeList.size()];
		if(modeList.size() > 0 && modeList.get(0) instanceof String){
			System.out.println("setSubscribedList");
			for(int i = 0 ; i < eventSubscriptionList.size() ; i++){
				if(eventSubscriptionList.get(i).getComponentId().equals(deviceId)) {
					if(eventSubscriptionList.get(i).getEventType().equals("POS")){
						isSubscribedList[0] = true;
					}else if(eventSubscriptionList.get(i).getEventType().equals("FanFailure")){
						isSubscribedList[1] = true;
					}else if(eventSubscriptionList.get(i).getEventType().equals("CpuUsage")){
						isSubscribedList[2] = true;
					}else if(eventSubscriptionList.get(i).getEventType().equals("MemoryUsage")){
						isSubscribedList[3] = true;
					}else if(eventSubscriptionList.get(i).getEventType().equals("Temperature")){
						isSubscribedList[4] = true;
					}else if(eventSubscriptionList.get(i).getEventType().equals("Pressure")){
						isSubscribedList[5] = true;
					}else if(eventSubscriptionList.get(i).getEventType().equals("Voltage")){
						isSubscribedList[6] = true;
					}
					
				}
			}
		}else{
			for(int i = 0 ; i < modeList.size() ; i++){
				for(int j = 0 ; j < eventSubscriptionList.size() ; j++){
					if(modeList.get(i) instanceof VideoInputChannel){
						VideoInputChannel obj = (VideoInputChannel)modeList.get(i);
						if(obj.getId().equals(eventSubscriptionList.get(j).getComponentId()) && eventSubscriptionList.get(j).getEventType().equals(eventType)){
							isSubscribedList[i] = true;
						}
					}else if(modeList.get(i) instanceof IOInputChannel){
						IOInputChannel obj = (IOInputChannel)modeList.get(i);
						if(obj.getId().equals(eventSubscriptionList.get(j).getComponentId()) && eventSubscriptionList.get(j).getEventType().equals(eventType)){
							isSubscribedList[i] = true;
						}
					}else if(modeList.get(i) instanceof IOOutputChannel){
						IOOutputChannel obj = (IOOutputChannel)modeList.get(i);
						if(obj.getId().equals(eventSubscriptionList.get(j).getComponentId()) && eventSubscriptionList.get(j).getEventType().equals(eventType)){
							isSubscribedList[i] = true;
						}
					}else if(modeList.get(i) instanceof StorageMedium){
						StorageMedium obj = (StorageMedium)modeList.get(i);
						if(obj.getId().equals(eventSubscriptionList.get(j).getComponentId()) && eventSubscriptionList.get(j).getEventType().equals(eventType)){
							isSubscribedList[i] = true;
						}
					}else if(modeList.get(i) instanceof NetworkInterface){
						NetworkInterface obj = (NetworkInterface)modeList.get(i);
						if(obj.getId().equals(eventSubscriptionList.get(j).getComponentId()) && eventSubscriptionList.get(j).getEventType().equals(eventType)){
							isSubscribedList[i] = true;
						}
					}
				}
			}
		}
	}
	
	private void showFailureDialog(){
		DialogUtils.postDialogWithPositiveButton(SetDeviceNotificationActivity.this,"","网络繁忙，请稍候再试",R.drawable.expander_ic_minimized,"确定",
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						
					}
				}).show();
	}
	
	private void startWaitingAnimation(){
		waitDialog = DialogUtils.postWaitDialog(SetDeviceNotificationActivity.this);
		waitDialog.show();
	}
	
	private void finishWaitingAnimation(){
		waitDialog.dismiss();
	}
	
	private void startRotateAnimation(View v){
		RotateAnimation animation = new RotateAnimation(0f,360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		LinearInterpolator lir = new LinearInterpolator();
		animation.setInterpolator(lir);
		animation.setDuration(1000);
		animation.setFillAfter(true);
		animation.setRepeatCount(-1);
		v.startAnimation(animation);
	}
	
	private void finishRotateAnimation(View v){
		v.clearAnimation();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.set_notification_imagebutton:
			final ImageButton ib = (ImageButton)v; 
			ib.setImageDrawable(getResources().getDrawable(R.mipmap.progress_blue));
			startRotateAnimation(ib);
			new AsyncTask<Void, Integer, Void>(){
				int position = Integer.valueOf(ib.getTag().toString());
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
					if(isSubscribedList[position]){
						UnsubscribeEventReq req = new UnsubscribeEventReq();
						req.setSession(SoapManager.getInstance().getUserLoginRes().getSession());
						req.setInternetDeviceId(SoapManager.getInstance().getInternetDeviceId());
						req.setEventType(eventType);
						if(modeList.get(position) instanceof VideoInputChannel){
							VideoInputChannel obj = (VideoInputChannel)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof IOInputChannel){
							IOInputChannel obj = (IOInputChannel)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof IOOutputChannel){
							IOOutputChannel obj = (IOOutputChannel)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof StorageMedium){
							StorageMedium obj = (StorageMedium)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof NetworkInterface){
							NetworkInterface obj = (NetworkInterface)modeList.get(position);
							req.setComponentId(obj.getId());
						}else{
							System.out.println("self");
							req.setComponentId(deviceId);
							switch (position) {
							case 0:
								req.setEventType("POS");
								break;
							case 1:
								req.setEventType("FanFailure");
								break;
							case 2:
								req.setEventType("CpuUsage");
								break;
							case 3:
								req.setEventType("MemoryUsage");
								break;
							case 4:
								req.setEventType("Temperature  ");
								break;
							case 5:
								req.setEventType("Pressure");
								break;
							case 6:
								req.setEventType("Voltage");
								break;
							default:
								break;
							}
						}
						
						UnsubscribeEventRes res = SoapManager.getInstance().getUnsubscribeEventRes(req);
					}else{
						SubscribeEventReq req = new SubscribeEventReq();
						req.setSession(SoapManager.getInstance().getUserLoginRes().getSession());
						req.setInternetDeviceId(SoapManager.getInstance().getInternetDeviceId());
						req.setEventType(eventType);
						if(modeList.get(position) instanceof VideoInputChannel){
							VideoInputChannel obj = (VideoInputChannel)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof IOInputChannel){
							IOInputChannel obj = (IOInputChannel)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof IOOutputChannel){
							IOOutputChannel obj = (IOOutputChannel)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof StorageMedium){
							StorageMedium obj = (StorageMedium)modeList.get(position);
							req.setComponentId(obj.getId());
						}else if(modeList.get(position) instanceof NetworkInterface){
							NetworkInterface obj = (NetworkInterface)modeList.get(position);
							req.setComponentId(obj.getId());
						}else{
							System.out.println("self");
							req.setComponentId(deviceId);
							switch (position) {
							case 0:
								req.setEventType("POS");
								break;
							case 1:
								req.setEventType("FanFailure");
								break;
							case 2:
								req.setEventType("CpuUsage");
								break;
							case 3:
								req.setEventType("MemoryUsage");
								break;
							case 4:
								req.setEventType("Temperature  ");
								break;
							case 5:
								req.setEventType("Pressure");
								break;
							case 6:
								req.setEventType("Voltage");
								break;
							default:
								break;
							}
						}
						
						req.setTriggerState("Active");
						req.setNotificationMethod("Push");
						SubscribeEventRes res = SoapManager.getInstance().getSubscribeEventRes(req);
					}
					return null;
				}
				
				protected void onPostExecute(Void result) {
					super.onPostExecute(result);
					finishRotateAnimation(ib);
					if(isSubscribedList[position]){
						ib.setImageDrawable(getResources().getDrawable(R.mipmap.set_notification_item_add));
					}else{
						ib.setImageDrawable(getResources().getDrawable(R.mipmap.set_notification_item_added));
					}
					isSubscribedList[position] = !isSubscribedList[position];
				};
				
			}.execute();
			
			break;
		case R.id.set_notification_back:
			finish();
			break;
		default:
			break;
		}
	}
}
