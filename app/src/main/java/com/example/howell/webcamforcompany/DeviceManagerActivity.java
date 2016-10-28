package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.howell.adapter.DeviceManagerAdapter;
import com.howell.ecamcompany.R;
import com.howell.ehlib.GroupRefreshListView;
import com.howell.ehlib.GroupRefreshListView.OnRefreshListener;
import com.howell.ksoap.Device;
import com.howell.ksoap.DeviceCatalogReq;
import com.howell.ksoap.DeviceCatalogRes;
import com.howell.ksoap.SoapManager;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明：设备管理界面
 */
public class DeviceManagerActivity extends Activity implements OnRefreshListener,OnClickListener{
	private GroupRefreshListView deviceManagerListView;
	//private ImageButton back;
	private ArrayList<Device> devices ;
	private DeviceManagerAdapter adapter;
	private SoapManager mSoapManager;
	private DeviceCatalogRes deviceCatalogRes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_manager);
		mSoapManager = SoapManager.getInstance();
//		back = (ImageButton)findViewById(R.id.device_manager_back);
//		back.setOnClickListener(this);
		devices = new ArrayList<Device>();
		deviceManagerListView = (GroupRefreshListView)findViewById(R.id.device_manager_listview);
		
		deviceManagerListView.setonRefreshListener(this);
//		devices.add(new Device("test"));
		adapter = new DeviceManagerAdapter(this,devices,deviceManagerListView);
		deviceManagerListView.setAdapter(adapter);
		deviceManagerListView.setOnScrollListener(adapter);
	}
	
	private void connectReq(){
		DeviceCatalogReq deviceCatalogReq = new DeviceCatalogReq();
		deviceCatalogReq.setSession(mSoapManager.getUserLoginRes().getSession());
		deviceCatalogReq.setInternetDeviceId(mSoapManager.getInternetDeviceId());
		deviceCatalogRes = mSoapManager.getDeviceCatalogRes(deviceCatalogReq);
	}
	
	private void connectRes(){
		if(deviceCatalogRes.getResult() != null && deviceCatalogRes.getResult().equals("OK")){
			devices = deviceCatalogRes.getDevices();
		}
		deviceManagerListView.onRefreshComplete();
		adapter.setDeviceList(devices);
		adapter.notifyDataSetChanged();
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Integer, Void>() {

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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.device_manager_back:
//			finish();
//			break;

		default:
			break;
		}
	}



}
