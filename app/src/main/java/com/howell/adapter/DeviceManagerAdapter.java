package com.howell.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.howell.webcamforcompany.EventTypeActivity;
import com.howell.ecamcompany.R;
import com.howell.ehlib.GroupRefreshListView;
import com.howell.ksoap.Device;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DeviceManagerAdapter extends BaseAdapter implements OnScrollListener{
	
	private Context mContext;
	private ArrayList<Device> deviceList;
	private int phoneWidth;			//手机屏幕宽度
	private int tanslationDistance;	//设置按钮移动距离（手机屏幕宽度-按钮宽度）
	private boolean[] devicesClickable;	
	private View lastClickedView;
	private GroupRefreshListView listview;
	
	public DeviceManagerAdapter(Context mContext, ArrayList<Device> deviceList,GroupRefreshListView listview) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.deviceList = deviceList;
		this.phoneWidth = getPhoneWidth(mContext);
		this.listview = listview;
	}

	public void setDeviceList(ArrayList<Device> deviceList) {
		this.deviceList = deviceList;
		devicesClickable = new boolean[deviceList.size()];
	}

	public View getLastClickedView() {
		return lastClickedView;
	}

	public void setLastClickedView(View lastClickedView) {
		this.lastClickedView = lastClickedView;
	}
	
	public void clearLastClickedView(){
		this.lastClickedView = null;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return deviceList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return deviceList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
    	if (convertView == null) {
    		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
    		convertView = layoutInflater.inflate(R.layout.device_manager_item, null);
			holder = new ViewHolder();
			
			holder.deviceName = (TextView)convertView.findViewById(R.id.tv_device_manager_item_name);
			holder.showBtns = (ImageButton)convertView.findViewById(R.id.btn_device_manager_show_all_btns);
			holder.btn1 = (LinearLayout)convertView.findViewById(R.id.btn_device_manager_btn_1);
			holder.btn2 = (LinearLayout)convertView.findViewById(R.id.btn_device_manager_btn_2);
			holder.btnLayout = (LinearLayout)convertView.findViewById(R.id.layout_device_manager_btns);
			
			int btnWidth = mesureViewWidth(holder.showBtns);
			tanslationDistance = phoneWidth - btnWidth;  
			System.out.println("phoneWidth:"+phoneWidth+" btnWidth:"+btnWidth);
			FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) holder.btnLayout.getLayoutParams(); 
			linearParams.width = tanslationDistance;       
			holder.btnLayout.setLayoutParams(linearParams);
			
			holder.btnLayout.setTranslationX(-tanslationDistance);
			convertView.setTag(holder);
    	}else{
         	holder = (ViewHolder)convertView.getTag();
        }
    	setbtnPlayTanslation(devicesClickable[position],holder.btnLayout);
    	MyVideoSourceOnClickListener listener = new MyVideoSourceOnClickListener(position,tanslationDistance, holder.btnLayout);
    	holder.deviceName.setText(deviceList.get(position).getName());
    	holder.btn1.setOnClickListener(listener);
    	holder.btn2.setOnClickListener(listener);
    	holder.showBtns.setOnClickListener(listener);
		return convertView;
	}
	
	@SuppressWarnings("deprecation")
	public int getPhoneWidth(Context context){
		WindowManager wm;
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}
	
	private int mesureViewWidth(View layout){
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		layout.measure(w, h); 
		//int height = btnLayout.getMeasuredHeight(); 
		int width = layout.getMeasuredWidth(); //获取控件宽度
		System.out.println("width:"+width);
//		MarginLayoutParams lp = (MarginLayoutParams) layout.getLayoutParams();
//		int leftMargin = lp.leftMargin;			//获取控件左边距
//		int rightMargin = lp.rightMargin;		//获取控件右边距
//		System.out.println("leftMargin:"+leftMargin+" rightMargin:"+rightMargin);
		return width ;//+ leftMargin + rightMargin;
	}
	
	private void setbtnPlayTanslation(boolean isClicked,View view){
		if(isClicked){
			view.setTranslationX(0);
		}else{
			view.setTranslationX(-tanslationDistance);
		}
	}
	
	class ViewHolder {
		//group_item:videoGroupName,video_source_item:videoSourceLayout
	    public TextView deviceName;
	    public ImageButton showBtns;
	    public LinearLayout btn1,btn2;
	    public LinearLayout btnLayout;
	}
	
	class MyVideoSourceOnClickListener implements OnClickListener{

		private int offset,position;
		private boolean isClick;
		private View btnLayout,lastClickedLayout;
		
		public  MyVideoSourceOnClickListener(int position,int offset,View btnLayout) {
			// TODO Auto-generated constructor stub
			this.position = position;
			this.offset = offset;
			this.btnLayout = btnLayout;
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_device_manager_show_all_btns:
				System.out.println("click position:"+position);
				lastClickedLayout = getLastClickedView();
				if(lastClickedLayout != null && lastClickedLayout != btnLayout){
					lastClickedLayout.setTranslationX(-tanslationDistance);
				}
				int videoPositon = position;
				for(int i = 0 ; i < devicesClickable.length ; i++){
					if(i != videoPositon){
						devicesClickable[i] = false;
						System.out.println(i+":"+devicesClickable[i]);
					}
				}
				isClick = devicesClickable[videoPositon];
				if(isClick){
					ObjectAnimator oa = ObjectAnimator.ofFloat(btnLayout,"translationX", -offset);
					oa.setDuration(200);
					oa.start();
					isClick = false;
				}else{
					ObjectAnimator oa = ObjectAnimator.ofFloat(btnLayout,"translationX", 0);
					oa.setDuration(200);
					oa.start();
					isClick = true;
				}
				devicesClickable[videoPositon] = !devicesClickable[videoPositon];
				setLastClickedView(btnLayout);
				break;

			case R.id.btn_device_manager_btn_1:
				Log.i("", "position:"+(position)+",button1");
				Intent intent = new Intent(mContext ,EventTypeActivity.class);
				intent.putExtra("deviceId", deviceList.get((int)position).getId());
				mContext.startActivity(intent);
				break;
			case R.id.btn_device_manager_btn_2:
				Log.i("", "position:"+(position)+",button2");
				break;
			default:
				break;
			}
		}	
	}

	@Override
	public void onScroll(AbsListView view, int firstVisiableItem, int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
	//	Log.e("", "onScroll totalItemCount:"+totalItemCount + " visibleItemCount:"+visibleItemCount);
		if(listview != null){
			listview.setFirstItemIndex(firstVisiableItem);
		}
		View v = getLastClickedView();
		if(v != null){
			v.setTranslationX(-tanslationDistance);
			for(int i = 0 ; i < devicesClickable.length ; i++){
				devicesClickable[i] = false;
			}
			clearLastClickedView();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
