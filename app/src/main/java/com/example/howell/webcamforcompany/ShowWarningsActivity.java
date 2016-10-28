package com.example.howell.webcamforcompany;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.howell.ecamcompany.R;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.SystemWarningReport;
import com.howell.ksoap.SystemWarningReportCatalogReq;
import com.howell.ksoap.SystemWarningReportCatalogRes;
import com.howell.utils.DialogUtils;
import com.howell.utils.SystemReportStorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
@SuppressLint("UseSparseArrays")
public class ShowWarningsActivity extends Activity implements OnGroupExpandListener{
	
	private ExpandableListView listview;
	private String[] generalsTypes = new String[] { "CPU过高设备", "内存过高设备", "网口使用率过高设备" 
			, "过热设备", "电压不稳设备", "设备视频负载过高", "视频网络状态不稳定", "异常上下线设备","系统视频连接失败率过高"};	//父视图
	private Map<Integer,ArrayList<SystemWarningReport>> generals = new HashMap<Integer, ArrayList<SystemWarningReport>>();
	private MyAdapter adapter;
	private SoapManager mSoapManager;
	private static final int STARTWAITING = 1;
	private GetSystemWarningReportTask getCPUUsageReportTask,getMemoryUsageReportTask,getNetworkUsageReportTask,getSuperHeatReportTask
			,getVoltageInstabilityReportTask,getVideoHighLoadReportTask,getVideoNetworkInstabilityReportTask,getTeardownReportTask,getVideoConnectionFailureReportTask;
	private SystemWarningReportCatalogRes systemWarningReportCatalogRes;
	private Dialog waitDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_warnings);
		Log.v("ShowWarningsActivity", "onCreate");
		listview = (ExpandableListView) findViewById(R.id.expandableListView_show_warnings);
		listview.requestFocusFromTouch();
		generals.put(0,new ArrayList<SystemWarningReport>());
		generals.put(1,new ArrayList<SystemWarningReport>());
		generals.put(2,new ArrayList<SystemWarningReport>());
		generals.put(3,new ArrayList<SystemWarningReport>());
		generals.put(4,new ArrayList<SystemWarningReport>());
		generals.put(5,new ArrayList<SystemWarningReport>());
		generals.put(6,new ArrayList<SystemWarningReport>());
		generals.put(7,new ArrayList<SystemWarningReport>());
		generals.put(8,new ArrayList<SystemWarningReport>());
		mSoapManager = SoapManager.getInstance();
		adapter = new MyAdapter();
		listview.setAdapter(adapter);
		listview.setOnGroupExpandListener(this);
	}
	
	//获取子视图数据
	private void getGeneralsDatas(int key,ArrayList<SystemWarningReport> list){
		generals.get(key).clear();
		generals.put(key, list);
	}
	
	private void startWaitingAnimation(){
		waitDialog = DialogUtils.postWaitDialog(ShowWarningsActivity.this);
		waitDialog.show();
	}
	
	private void finishWaitingAnimation(){
		waitDialog.dismiss();
	}
	
	//获取故障信息
	class GetSystemWarningReportTask extends AsyncTask<Void, Integer, Void> {
		private int key;
		private SystemWarningReportCatalogReq systemWarningReportCatalogReq;
		public GetSystemWarningReportTask(int key,SystemWarningReportCatalogReq systemWarningReportCatalogReq) {
			// TODO Auto-generated constructor stub
			this.systemWarningReportCatalogReq = systemWarningReportCatalogReq;
			this.key = key;
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//开始等待动画
			handler.sendEmptyMessage(STARTWAITING);
	        //请求查询系统故障报告
			systemWarningReportCatalogRes = mSoapManager.getSystemWarningReportCatalogRes(systemWarningReportCatalogReq);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(systemWarningReportCatalogRes.getResult() != null){//获取成功
				getGeneralsDatas(key,systemWarningReportCatalogRes.getSystemWarningReports());
				adapter.notifyDataSetChanged();
			}else{												//获取失败
				DialogUtils.postDialogWithPositiveButton(ShowWarningsActivity.this,"","网络繁忙，请稍候再试",R.drawable.expander_ic_minimized,"确定",
						new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								
							}
						}).show();
			}
			//结束等待动画
			finishWaitingAnimation();
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
			case STARTWAITING:
				startWaitingAnimation();
				break;
			}
		}
	};
	
	//ExpandableListView的适配器
	class MyAdapter extends BaseExpandableListAdapter{
		
		@Override
		public Object getChild(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return generals.get(groupPosition).get(childPosition);
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			// TODO Auto-generated method stub
			return childPosition;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View childView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			System.out.println("getChildrenCount:"+getChildrenCount(groupPosition)+" ,groupPosition:"+groupPosition);
			ViewChildHolder childHolder = null;
            if (childView == null) {
            	LayoutInflater layoutInflater = LayoutInflater.from(ShowWarningsActivity.this);
            	childView = layoutInflater.inflate(R.layout.checkdetail_childview_item, null);
            	childHolder = new ViewChildHolder();
            	childHolder.childName = (TextView)childView.findViewById(R.id.checkdetail_childname);
                
                //holder.iv.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight));
				childView.setTag(childHolder);
            }else{
            	childHolder = (ViewChildHolder)childView.getTag();
            }
            SystemWarningReport item = generals.get(groupPosition).get(childPosition);
            String time = item.getCreationTime();
            String id = item.getComponentId();
            childHolder.childName.setText(time.substring(0, 10)+" "+time.substring(11)+"\n设备 id:\n"+id);
            
            return childView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			// TODO Auto-generated method stub
			return generals.get(groupPosition).size();
		}

		@Override
		public Object getGroup(int groupPosition) {
			// TODO Auto-generated method stub
			return generalsTypes[groupPosition];
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return generalsTypes.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			// TODO Auto-generated method stub
			return groupPosition;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded, View groupView,
				ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewGroupHolder groupHolder = null;
            if (groupView == null) {
            	LayoutInflater layoutInflater = LayoutInflater.from(ShowWarningsActivity.this);
            	groupView = layoutInflater.inflate(R.layout.checkdetail_groupview_item, null);
            	groupHolder = new ViewGroupHolder();
            	groupHolder.groupIcon = (ImageView)groupView.findViewById(R.id.checkdetail_groupicon);
            	groupHolder.groupName = (TextView)groupView.findViewById(R.id.checkdetail_groupname);
                groupHolder.icon = (ImageView)groupView.findViewById(R.id.checkdetail_group_down_icon);
                groupHolder.faultCount = (TextView)groupView.findViewById(R.id.checkdetail_faultcount);
                //holder.iv.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight));
				groupView.setTag(groupHolder);
            }else{
            	groupHolder = (ViewGroupHolder)groupView.getTag();
            }
            
            if(isExpanded){
            	groupHolder.icon.setImageResource(R.mipmap.qz_icon_down_normal);
            }else{
            	groupHolder.icon.setImageResource(R.mipmap.qz_icon_forward_normal);
            }
            groupHolder.groupName.setText(generalsTypes[groupPosition]);
            
            switch(groupPosition){
            case 0:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_1);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getcPUUsageNumber()+"");
		           break;
            case 1:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_2);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getMemoryUsageNumber()+"");
		           break;
            case 2:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_3);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getNetworkUsageNumber()+"");
		           break;
            case 3:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_4);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getSuperHeatNumber()+"");
		           break;
            case 4:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_5);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getVoltageInstabilityNumber()+"");
		           break;
            case 5:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_6);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getVideoHighLoadNumber()+"");
		           break;
            case 6:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_7);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getVideoNetworkInstabilityNumber()+"");
		           break;
            case 7:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_8);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getTeardownNumber()+"");
		           break;
            case 8:groupHolder.groupIcon.setImageResource(R.mipmap.icon_warning_9);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemWarningStatistics().getVideoConnectionFailureNumber()+"");
		           break;
            default:break;
            }
            return groupView;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}
	}
	
	public static class ViewGroupHolder {
		public TextView groupName,faultCount;
		public ImageView groupIcon,icon;
	}
	
	public static class ViewChildHolder {
		public TextView childName;
	}

	@Override
	public void onGroupExpand(int groupPosition) {
		// TODO Auto-generated method stub
		SystemWarningReportCatalogReq systemWarningReportCatalogReq = null;
		switch(groupPosition){
		case 0:
			if(getCPUUsageReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"CPUUsage");
				getCPUUsageReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getCPUUsageReportTask.execute();
			}
			break;
		case 1:
			if(getMemoryUsageReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"MemoryUsage");
				getMemoryUsageReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getMemoryUsageReportTask.execute();
			}
			break;
		case 2:
			if(getNetworkUsageReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"NetworkUsage");
				getNetworkUsageReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getNetworkUsageReportTask.execute();
			}
			break;
		case 3:
			if(getSuperHeatReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"SuperHeat");
				getSuperHeatReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getSuperHeatReportTask.execute();
			}
			break;
		case 4:
			if(getVoltageInstabilityReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"VoltageInstability");
				getVoltageInstabilityReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getVoltageInstabilityReportTask.execute();
			}
			break;
		case 5:
			if(getVideoHighLoadReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"VideoHighLoad");
				getVideoHighLoadReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getVideoHighLoadReportTask.execute();
			}
			break;
		case 6:
			if(getVideoNetworkInstabilityReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"VideoNetworkInstability");
				getVideoNetworkInstabilityReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getVideoNetworkInstabilityReportTask.execute();
			}
			break;
		case 7:
			if(getTeardownReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"Teardown");
				getTeardownReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getTeardownReportTask.execute();
			}
			break;
		case 8:
			if(getVideoConnectionFailureReportTask == null){
				systemWarningReportCatalogReq = new SystemWarningReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"VideoConnectionFailure");
				getVideoConnectionFailureReportTask = new GetSystemWarningReportTask(groupPosition,systemWarningReportCatalogReq);
				getVideoConnectionFailureReportTask.execute();
			}
			break;
		default:break;
		}
	}

}
