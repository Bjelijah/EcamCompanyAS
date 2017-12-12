package com.howell.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.howell.webcamforcompany.Const;
import com.example.howell.webcamforcompany.PlayerActivity;
import com.howell.db.DBManager;
import com.howell.ecamcompany.R;
import com.howell.ehlib.GroupRefreshListView;
import com.howell.ksoap.VideoSource;
import com.howell.ksoap.VideoSourceGroup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class VideoSourceListAdapter extends BaseAdapter implements Const,OnScrollListener{
	
	private Context mContext;
//	private Drawable videoSource1,videoSource2,videoSource3,videoSource4,videoSource5,videoSource6,videoSource7;
	private ArrayList<VideoSource> videoSourcesList;	//视频源列表
	private ArrayList<VideoSourceGroup> videoGroupsList;//分组列表
	private boolean[] videoSourceClickable;	//每个视频按钮是否按过的标志位
	private boolean noScroll;
	private int phoneWidth;			//手机屏幕宽度
	private int tanslationDistance;	//设置按钮移动距离（手机屏幕宽度-按钮宽度）
	private View lastClickedView;
	private GroupRefreshListView listview;
	
    private DBManager mDBManager; 
    private int whichActivity;
	
    
	public VideoSourceListAdapter(Context context,ArrayList<VideoSourceGroup> videoGroupsList,ArrayList<VideoSource> videoSourcesList
			,GroupRefreshListView listview,int whichActivity) {
		this.whichActivity = whichActivity;
        this.mContext = context;
        this.videoSourcesList = videoSourcesList;
        this.videoGroupsList = videoGroupsList;
        this.phoneWidth = getPhoneWidth(mContext);
        this.listview = listview;
		mDBManager = new DBManager(mContext);
//        videoSource1= mContext.getResources().getDrawable(R.drawable.icon_video_souce_1);
//        videoSource2= mContext.getResources().getDrawable(R.drawable.icon_video_souce_2);
//        videoSource3= mContext.getResources().getDrawable(R.drawable.icon_video_souce_3);
//        videoSource4= mContext.getResources().getDrawable(R.drawable.icon_video_souce_4);
//        videoSource5= mContext.getResources().getDrawable(R.drawable.icon_video_souce_5);
//        videoSource6= mContext.getResources().getDrawable(R.drawable.icon_video_souce_6);
//        videoSource7= mContext.getResources().getDrawable(R.drawable.icon_video_souce_7);
    }
	
	public void setVideoSourcesList(ArrayList<VideoSource> videoSourcesList) {
		this.videoSourcesList = videoSourcesList;
		if(whichActivity == VIDEOSOURCEACTIVITY){
			ArrayList<VideoSource> sqlList = mDBManager.query();
			for(VideoSource vs : videoSourcesList){
				vs.setFavourite(false);
				for(VideoSource sqlvs : sqlList){
					if(sqlvs.getId().equals(vs.getId())){
						vs.setFavourite(true);
					}
				}
			}
		}else if (whichActivity == FAVOURITEACTIVITY){
			for(VideoSource vs : videoSourcesList){
				vs.setFavourite(true);
			}
		}
		//设置完视频源列表以后才知道标志位数组长度
		videoSourceClickable = new boolean[videoSourcesList.size()];
		//初始化标志位数组
		for(int i = 0 ; i < videoSourceClickable.length ; i++){
			videoSourceClickable[i] = false;
		}
	}
	
	public void setVideoGroupsList(ArrayList<VideoSourceGroup> videoGroupsList) {
		this.videoGroupsList = videoGroupsList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return videoGroupsList.size() + videoSourcesList.size();
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
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position > videoGroupsList.size() - 1 ? videoSourcesList.get(position - videoGroupsList.size()) : videoGroupsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position > videoGroupsList.size() - 1 ? position  - videoGroupsList.size() : position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
    	if (convertView == null) {
    		System.out.println("111:"+position);
    		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
    		convertView = layoutInflater.inflate(R.layout.video_device_list_item, null);
			holder = new ViewHolder();
			
			//holder.btnFavourite = (ImageButton)convertView.findViewById(R.id.video_source_item_favourite);
			holder.btnPlay = (LinearLayout)convertView.findViewById(R.id.ll_play_btn);
			holder.videoGroupName = (TextView)convertView.findViewById(R.id.tv_video_group_item);
			holder.videoSourceLayout = (FrameLayout)convertView.findViewById(R.id.fl_video_source_item);
			holder.videoSourceName = (TextView)convertView.findViewById(R.id.tv_video_source_name);
			holder.showAllBtns = (ImageButton)convertView.findViewById(R.id.btn_video_source_show_all_btns);
			holder.btn1 = (ImageButton)convertView.findViewById(R.id.btn_video_source_btn_1);
			holder.btn2 = (ImageButton)convertView.findViewById(R.id.btn_video_source_btn_2);
			holder.btn3 = (ImageButton)convertView.findViewById(R.id.btn_video_source_btn_3);
			holder.btn4 = (ImageButton)convertView.findViewById(R.id.btn_video_source_btn_4);
			holder.btnLayout = (LinearLayout)convertView.findViewById(R.id.layout_video_source_btns);
			
			int btnWidth = mesureViewWidth(holder.showAllBtns);
			tanslationDistance = phoneWidth - btnWidth;  
			System.out.println("phoneWidth:"+phoneWidth+" btnWidth:"+btnWidth);
			FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) holder.btnLayout.getLayoutParams(); 
			linearParams.width = tanslationDistance;       
			holder.btnLayout.setLayoutParams(linearParams);
			
			holder.btnLayout.setTranslationX(-tanslationDistance);
			convertView.setTag(holder);
    	}else{
    		System.out.println("222:"+position);
         	holder = (ViewHolder)convertView.getTag();
        }
    	
    	if(position > videoGroupsList.size() - 1){
    		MyVideoSourceOnClickListener listener = new MyVideoSourceOnClickListener(position,tanslationDistance, holder.btnLayout , holder.btn4);
    		//System.out.println("tanslationDistance:"+holder.btnLayout.getTranslationX()+" tanslationDistance:"+tanslationDistance);
    		if(holder.videoGroupName.getVisibility() == View.VISIBLE)
    			holder.videoGroupName.setVisibility(View.GONE);
    		if(holder.videoSourceLayout.getVisibility() == View.GONE)
    			holder.videoSourceLayout.setVisibility(View.VISIBLE);
//    		if((position - videoGroupsList.size() + 1) % 7 == 1){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource1,null,null,null);
//        	}else if ((position - videoGroupsList.size() + 1) % 7 == 2){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource2,null,null,null);
//        	}else if ((position - videoGroupsList.size() + 1) % 7 == 3){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource3,null,null,null);
//        	}else if ((position - videoGroupsList.size() + 1) % 7 == 4){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource4,null,null,null);
//        	}else if ((position - videoGroupsList.size() + 1) % 7 == 5){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource5,null,null,null);
//        	}else if ((position - videoGroupsList.size() + 1) % 7 == 6){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource6,null,null,null);
//        	}else if ((position - videoGroupsList.size() + 1) % 7 == 0){
//        		holder.videoSourceName.setCompoundDrawablesWithIntrinsicBounds (videoSource7,null,null,null);
//        	}
    		
    		if(videoSourcesList.get(position - videoGroupsList.size()).isFavourite()){
    			holder.btn4.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_favourite_pressed));
    		}else{
    			holder.btn4.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.icon_favourite));
    		}
    		setbtnPlayTanslation(videoSourceClickable[position - videoGroupsList.size()],holder.btnLayout);
//	    	holder.btnPlay.setTag(position);
	    	holder.btnPlay.setOnClickListener(listener);
	    	holder.btn1.setOnClickListener(listener);
	    	holder.btn2.setOnClickListener(listener);
	    	holder.btn3.setOnClickListener(listener);
	    	holder.btn4.setOnClickListener(listener);
	    	holder.showAllBtns.setOnClickListener(listener);
			holder.videoSourceName.setText(videoSourcesList.get(position - videoGroupsList.size()).getName());

    	}else{
    		if(holder.videoGroupName.getVisibility() == View.GONE)
    			holder.videoGroupName.setVisibility(View.VISIBLE);
    		if(holder.videoSourceLayout.getVisibility() == View.VISIBLE)
    			holder.videoSourceLayout.setVisibility(View.GONE);
    		
    		holder.videoGroupName.setText(videoGroupsList.get(position).getName());
    	}
		return convertView;
	}
	
	private void setbtnPlayTanslation(boolean isClicked,View view){
		if(isClicked){
			view.setTranslationX(0);
		}else{
			view.setTranslationX(-tanslationDistance);
		}
	}
	
	private int mesureViewWidth(View layout){
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		layout.measure(w, h); 
		//int height = btnLayout.getMeasuredHeight(); 
		int width = layout.getMeasuredWidth(); //获取控件宽度
		System.out.println("width:"+width);
//		MarginLayoutParams lp = (MarginLayoutParams) layout.getLayoutParams();
		//int leftMargin = lp.leftMargin;			//获取控件左边距
		//int rightMargin = lp.rightMargin;		//获取控件右边距
		//System.out.println("leftMargin:"+leftMargin+" rightMargin:"+rightMargin);
		return width ;//+ leftMargin + rightMargin;
	}
	
	@SuppressWarnings("deprecation")
	public int getPhoneWidth(Context context){
		WindowManager wm;
		wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}
	
	class ViewHolder {
		//group_item:videoGroupName,video_source_item:videoSourceLayout
	    public TextView videoSourceName,videoGroupName;
	    public ImageButton showAllBtns;
	    public ImageButton btn1,btn2,btn3,btn4;
	    public LinearLayout btnLayout,btnPlay;
	    public FrameLayout videoSourceLayout;
	}
	
	class MyVideoSourceOnClickListener implements OnClickListener{

		private int offset,position;
		private boolean isClick;
		private View btnLayout,lastClickedLayout,favouriteView;
		

		
		public  MyVideoSourceOnClickListener(int position,int offset,View btnLayout,View favouriteView) {
			// TODO Auto-generated constructor stub
			this.position = position;
			this.offset = offset;
			this.btnLayout = btnLayout;
			this.favouriteView = favouriteView;
		
		}
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_video_source_show_all_btns:
//				System.out.println("click position:"+position);
				Log.i("log123", "click position:"+position);
				lastClickedLayout = getLastClickedView();
				if(lastClickedLayout != null && lastClickedLayout != btnLayout){
					lastClickedLayout.setTranslationX(-tanslationDistance);
				}
				int videoPositon = position - videoGroupsList.size();
				for(int i = 0 ; i < videoSourceClickable.length ; i++){
					if(i != videoPositon){
						videoSourceClickable[i] = false;
						System.out.println(i+":"+videoSourceClickable[i]);
					}
				}
				isClick = videoSourceClickable[videoPositon];
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
				videoSourceClickable[videoPositon] = !videoSourceClickable[videoPositon];
				System.out.println(videoPositon +" end:"+videoSourceClickable[videoPositon]);
				setLastClickedView(btnLayout);
				System.out.println(getLastClickedView().getId());
				break;

			case R.id.ll_play_btn:
				System.out.println("fl_video_source_item");
				
				
				//判断网络若断网 finish	
//				if (!NetWorkUtil.isNetConnect(mContext)) {
//					//链接失败
//					Log.i("log123", "onadapt net disconnect");
////					DialogUtils.postDialogWithPositiveButton(mContext, "连接视频失败", "网络信号差，请稍后重新登录", R.drawable.expander_ic_minimized, "确定", new DialogInterface.OnClickListener() {
////						@Override
////						public void onClick(DialogInterface dialog, int which) {
////							// TODO Auto-generated method stub
////
////						}
////					}).show();
//				//	onDestroy();
//					Toast.makeText(mContext, "网络链接失败", Toast.LENGTH_SHORT).show();
//					Log.i("log123", "adapt return");
//					return;
//				}
				
				
				
				Intent intent = new Intent(mContext ,PlayerActivity.class);
				intent.putExtra("videosource", videoSourcesList.get(position - videoGroupsList.size()));
				intent.putExtra("position", position - videoGroupsList.size());
				Bundle bundle = new Bundle();
				bundle.putSerializable("videoSourceList", (Serializable) videoSourcesList);

				intent.putExtras(bundle);
				intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
				mContext.startActivity(intent);
				System.out.println("intent ok");
				
				break;
				
			case R.id.btn_video_source_btn_1:
				Log.i("123", "position:"+(position - videoGroupsList.size())+",button1");
				break;
			case R.id.btn_video_source_btn_2:
				Log.i("", "position:"+(position - videoGroupsList.size())+",button2");
				break;
			case R.id.btn_video_source_btn_3:
				Log.i("", "position:"+(position - videoGroupsList.size())+",button3");
				break;
			case R.id.btn_video_source_btn_4:
				Log.i("", "position:"+(position - videoGroupsList.size())+",button4");
				noScroll = true;
				if(!videoSourcesList.get(position - videoGroupsList.size()).isFavourite()){
					mDBManager.add(videoSourcesList.get(position - videoGroupsList.size()));
					videoSourcesList.get(position - videoGroupsList.size()).setFavourite(true);
					((ImageButton)favouriteView).setImageResource(R.mipmap.icon_favourite_pressed);
				}else{
					mDBManager.deleteOldCamera(videoSourcesList.get(position - videoGroupsList.size()).getName());
					videoSourcesList.get(position - videoGroupsList.size()).setFavourite(false);
					((ImageButton)favouriteView).setImageResource(R.mipmap.icon_favourite);
					if(whichActivity == FAVOURITEACTIVITY){
						setVideoSourcesList(mDBManager.query());
						notifyDataSetChanged();
					}
				}
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
		if(noScroll){
			noScroll = false;
			return;
		}
		if(listview != null){
			listview.setFirstItemIndex(firstVisiableItem);
		}
		View v = getLastClickedView();
		if(v != null){
			v.setTranslationX(-tanslationDistance);
			for(int i = 0 ; i < videoSourceClickable.length ; i++){
				videoSourceClickable[i] = false;
			}
			clearLastClickedView();
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
	//	Log.e("", "onScrollStateChanged:"+scrollState);
	}

}


