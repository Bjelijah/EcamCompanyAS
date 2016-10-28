package com.example.howell.webcamforcompany;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.howell.ksoap.SystemFaultReport;
import com.howell.ksoap.SystemFaultReportCatalogReq;
import com.howell.ksoap.SystemFaultReportCatalogRes;
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
public class ShowFaultsActivity extends Activity implements OnGroupExpandListener{
	private ExpandableListView listview;
	private String[] generalsTypes = new String[] { "离线设备", "出错的存储媒介", "视频丢失通道" };	//父视图
	private Map<Integer,ArrayList<SystemFaultReport>> generals = new HashMap<Integer, ArrayList<SystemFaultReport>>();
	private MyAdapter adapter;
	private SoapManager mSoapManager;
	private SystemFaultReportCatalogRes systemFaultReportCatalogRes;
	private GetSystemFaultReportTask getOfflineReportTak,getStorageMediumAbnormalReportTask,getVideolossReportTask;
	private static final int STARTWAITING = 1;
	
	private Dialog waitDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_faults);
		init();
	}
	
	private void init(){
		listview = (ExpandableListView) findViewById(R.id.expandableListView_show_fauls);
		listview.requestFocusFromTouch();
		generals.put(0,new ArrayList<SystemFaultReport>());
		generals.put(1,new ArrayList<SystemFaultReport>());
		generals.put(2,new ArrayList<SystemFaultReport>());
		mSoapManager = SoapManager.getInstance();
		adapter = new MyAdapter();
		listview.setAdapter(adapter);
		listview.setOnGroupExpandListener(this);
	}
	
	//获取子视图数据
	private void getGeneralsDatas(int key,ArrayList<SystemFaultReport> list){
		generals.get(key).clear();
		generals.put(key, list);
	}
	
	private void startWaitingAnimation(){
		waitDialog = DialogUtils.postWaitDialog(ShowFaultsActivity.this);
		waitDialog.show();
	}
	
	private void finishWaitingAnimation(){
		waitDialog.dismiss();
	}
	
	//获取故障信息
	class GetSystemFaultReportTask extends AsyncTask<Void, Integer, Void> {
		private int key;
		private SystemFaultReportCatalogReq systemFaultReportCatalogReq;
		public GetSystemFaultReportTask(int key,SystemFaultReportCatalogReq systemFaultReportCatalogReq) {
			// TODO Auto-generated constructor stub
			this.systemFaultReportCatalogReq = systemFaultReportCatalogReq;
			this.key = key;
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			//开始等待动画
			handler.sendEmptyMessage(STARTWAITING);
	        //请求查询系统故障报告
	        systemFaultReportCatalogRes = mSoapManager.getSystemFaultReportCatalogRes(systemFaultReportCatalogReq);
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			if(systemFaultReportCatalogRes.getResult() != null){//获取成功
				getGeneralsDatas(key,systemFaultReportCatalogRes.getSystemFaultReports());
				adapter.notifyDataSetChanged();
			}else{												//获取失败
				DialogUtils.postDialogWithPositiveButton(ShowFaultsActivity.this,"","网络繁忙，请稍候再试",R.drawable.expander_ic_minimized,"确定",
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
            	LayoutInflater layoutInflater = LayoutInflater.from(ShowFaultsActivity.this);
            	childView = layoutInflater.inflate(R.layout.checkdetail_childview_item, null);
            	childHolder = new ViewChildHolder();
            	childHolder.childName = (TextView)childView.findViewById(R.id.checkdetail_childname);
                
                //holder.iv.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight));
				childView.setTag(childHolder);
            }else{
            	childHolder = (ViewChildHolder)childView.getTag();
            }
            SystemFaultReport item = generals.get(groupPosition).get(childPosition);
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
            	LayoutInflater layoutInflater = LayoutInflater.from(ShowFaultsActivity.this);
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
            case 0:groupHolder.groupIcon.setImageResource(R.mipmap.icon_fault_1);
            	   groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemFaultStatistics().getOfflineNumber()+"");
            	   break;
            case 1:groupHolder.groupIcon.setImageResource(R.mipmap.icon_fault_2);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemFaultStatistics().getStorageMediumAbnormalNumber()+"");
		           break;
            case 2:groupHolder.groupIcon.setImageResource(R.mipmap.icon_fault_3);
		           groupHolder.faultCount.setText(SystemReportStorage.getInstance().getSystemFaultStatistics().getVideolossNumber()+"");
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
		SystemFaultReportCatalogReq systemFaultReportCatalogReq = null;
		switch(groupPosition){
		case 0://Offline
			if(getOfflineReportTak == null){
				systemFaultReportCatalogReq = new SystemFaultReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"Offline");
				getOfflineReportTak = new GetSystemFaultReportTask(groupPosition,systemFaultReportCatalogReq);
				getOfflineReportTak.execute();
			}
			break;
		case 1://StorageMediumAbnormal
			if(getStorageMediumAbnormalReportTask == null){
				systemFaultReportCatalogReq = new SystemFaultReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"StorageMediumAbnormal");
				getStorageMediumAbnormalReportTask = new GetSystemFaultReportTask(groupPosition,systemFaultReportCatalogReq);
				getStorageMediumAbnormalReportTask.execute();
			}
			break;
		case 2://Videoloss
			if(getVideolossReportTask == null){
				systemFaultReportCatalogReq = new SystemFaultReportCatalogReq(mSoapManager.getUserLoginRes().getSession()
		        		,mSoapManager.getInternetDeviceId(),1,10,"Videoloss");
				getVideolossReportTask = new GetSystemFaultReportTask(groupPosition,systemFaultReportCatalogReq);
				getVideolossReportTask.execute();
			}
			break;
		default:break;
		}
	}

}
