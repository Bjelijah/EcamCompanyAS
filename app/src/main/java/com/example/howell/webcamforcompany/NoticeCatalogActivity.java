package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.howell.ecamcompany.R;
import com.howell.ehlib.MyListViewWithFoot;
import com.howell.ehlib.MyListViewWithFoot.OnRefreshListener;
import com.howell.ksoap.Notice;
import com.howell.ksoap.NoticeCatalogRes;
import com.howell.utils.DisplayImageManager;
import com.howell.utils.NoticePagingUtils;
import com.howell.utils.PhoneConfig;
import com.howell.utils.SDCardUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明：系统通知界面
 */
public class NoticeCatalogActivity extends Activity implements OnRefreshListener{
	private MyListViewWithFoot listView;
	private ArrayList<Notice> noticesList;
	private GetNoticeCatalogAdapter adapter;
	private NoticeCatalogRes res;
	private NoticePagingUtils noticePagingUtils;
	DisplayImageOptions options;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notice_catalog_activity);
		noticesList = new ArrayList<Notice>();
		noticePagingUtils = new NoticePagingUtils();
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.mipmap.empty_photo)
		.showImageForEmptyUri(R.mipmap.empty_photo)
		.showImageOnFail(R.mipmap.empty_photo)
		.cacheInMemory(true)
		.cacheOnDisc(false)
		.bitmapConfig(Bitmap.Config.RGB_565)	 
		.build();
		listView = (MyListViewWithFoot)findViewById(R.id.noticecatalog_listview);
		listView.setonRefreshListener(this);
		adapter = new GetNoticeCatalogAdapter(this);
		listView.setAdapter(adapter);
	}
	
	class GetNoticeCatalogAdapter extends BaseAdapter implements OnClickListener{

		private Context mContext;
		private DisplayImageManager displayImageManager;
		private int imageWidth,imageHeight;
	   	private LinearLayout.LayoutParams lp;
		
		public GetNoticeCatalogAdapter(Context context) {
	        this.mContext = context;
	        this.displayImageManager = DisplayImageManager.getInstance();
			this.displayImageManager.setHandler(mHandler);
			imageWidth = (PhoneConfig.getPhoneWidth(NoticeCatalogActivity.this) - 10 ) / 3;
			imageHeight = imageWidth;
			lp = new LinearLayout.LayoutParams(imageWidth, imageHeight);
    		lp.setMargins(5, 5, 0, 0);
	    }
		
		@Override
	    public int getCount() {
	        // TODO Auto-generated method stub
	        return noticesList.size() ;
	    }

	    @Override
	    public Object getItem(int position) {
	        // TODO Auto-generated method stub
	        return noticesList.get(position) ;
	    }

	    @Override
	    public long getItemId(int position) {
	        // TODO Auto-generated method stub
	        return position;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        // TODO Auto-generated method stub
 	    	Log.e("", "getView:"+position);
	    	ViewHolder holder = null;
	    	if (convertView == null) {
	    		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
	    		convertView = layoutInflater.inflate(R.layout.notice_item, null);
				holder = new ViewHolder();
					
				holder.message = (TextView)convertView.findViewById(R.id.notice_item_message);
				holder.picture1 = (ImageView)convertView.findViewById(R.id.notice_item_imageView1);
				holder.picture2 = (ImageView)convertView.findViewById(R.id.notice_item_imageView2);
				holder.picture3 = (ImageView)convertView.findViewById(R.id.notice_item_imageView3);
				holder.picture4 = (ImageView)convertView.findViewById(R.id.notice_item_imageView4);
				
				holder.picture1.setLayoutParams(lp);
				holder.picture2.setLayoutParams(lp);
				holder.picture3.setLayoutParams(lp);
				holder.picture4.setLayoutParams(lp);
				
				holder.picture1.setOnClickListener(this);
				holder.picture2.setOnClickListener(this);
				holder.picture3.setOnClickListener(this);
				holder.picture4.setOnClickListener(this);
				
	            convertView.setTag(holder);
	    	}else{
	         	holder = (ViewHolder)convertView.getTag();
	        }
	    	Notice notice = noticesList.get(position);
	    	holder.picture1.setTag(position);
            holder.picture2.setTag(position);
            holder.picture3.setTag(position);
            holder.picture4.setTag(position);
	    	holder.message.setText(notice.getTime().substring(0, 10)+" "+notice.getTime().substring(11)+" "+notice.getMessage());
	    	switch(notice.getPictureID().size()){
			case 0:	
				holder.picture1.setVisibility(View.GONE);
				holder.picture2.setVisibility(View.GONE);
				holder.picture3.setVisibility(View.GONE);
				holder.picture4.setVisibility(View.GONE);
				break;
			case 1:
				holder.picture1.setVisibility(View.VISIBLE);
				holder.picture1.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				Log.e("", "set image empty position:"+position);
				holder.picture2.setVisibility(View.GONE);
				holder.picture3.setVisibility(View.GONE);
				holder.picture4.setVisibility(View.GONE);
				break;
			case 2:
				holder.picture1.setVisibility(View.VISIBLE);
				holder.picture1.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture2.setVisibility(View.VISIBLE);
				holder.picture2.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture3.setVisibility(View.GONE);
				holder.picture4.setVisibility(View.GONE);
				break;
			
			case 3:
				holder.picture1.setVisibility(View.VISIBLE);
				holder.picture1.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture2.setVisibility(View.VISIBLE);
				holder.picture2.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture3.setVisibility(View.VISIBLE);
				holder.picture3.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture4.setVisibility(View.GONE);
				break;
			case 4:
				holder.picture1.setVisibility(View.VISIBLE);
				holder.picture1.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture2.setVisibility(View.VISIBLE);
				holder.picture2.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture3.setVisibility(View.VISIBLE);
				holder.picture3.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				holder.picture4.setVisibility(View.VISIBLE);
				holder.picture4.setImageDrawable(getResources().getDrawable(R.mipmap.empty_photo));
				break;
			}
	    	//imageLoader.displayImage(imageUrls[position], holder.picture1, options);
	    	DisplayImageInfo info = new DisplayImageInfo(position,notice.getPictureID(),imageWidth,imageHeight,holder);
	    	displayImageManager.startDownload(info);
			return convertView;
	    }
	    
	    class ViewHolder {
	        public TextView message;
	        public ImageView picture1,picture2,picture3,picture4;
	    }
	    
		private Handler mHandler = new Handler() {
			
			public void handleMessage(Message msg)
			{
				// notifyDataSetChanged会执行getView函数，更新所有可视item的数据
				//notifyDataSetChanged();
				// 只更新指定item的数据，提高了性能
				DisplayImageInfo info = (DisplayImageInfo)msg.obj;
				updateView(info);
			}
		};
	    
	    // 更新指定item的数据
		private void updateView(DisplayImageInfo info)
		{
//			int visiblePos = listView.getFirstVisiblePosition();
//			int offset = index - visiblePos + 1;
//			Log.e("", "index="+index+",visiblePos="+visiblePos+",offset="+offset);
//			// 只有在可见区域才更新
//			if(offset < 0) return;
//			
//			View view = listView.getChildAt(offset);
			Notice notice = noticesList.get(info.getIndex());
			ViewHolder holder = info.getHolder();
			for(int i = 0 ; i < notice.getPictureID().size() ; i++){
				ImageView imageView = null;
				switch (i) {
				case 0:
					imageView = holder.picture1;
					break;
				case 1:
					imageView = holder.picture2;
					break;
				case 2:
					imageView = holder.picture3;
					break;
				case 3:
					imageView = holder.picture4;
					break;
				default:
					break;
				}
				Log.e("", "set image :"+info.getIndex());
				imageLoader.displayImage("file://"+SDCardUtils.getBitmapCachePath()+notice.getPictureID().get(i), imageView, options);
			}
		}
		
		private ArrayList<String> getPictures(int position){
			ArrayList<String> pathList = new ArrayList<String>();
			for(int i = 0 ; i < noticesList.get(position).getPictureID().size() ; i++){
				if(SDCardUtils.isBitmapExist(noticesList.get(position).getPictureID().get(i)))
					pathList.add(SDCardUtils.getBitmapCachePath()+noticesList.get(position).getPictureID().get(i));
			}
			return pathList;
		}
		

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int which = 0;
			if(v.getId() == R.id.notice_item_imageView1){
				which = 0;
			}else if(v.getId() == R.id.notice_item_imageView2){
				which = 1;
			}else if(v.getId() == R.id.notice_item_imageView3){
				which = 2;
			}else if(v.getId() == R.id.notice_item_imageView4){
				which = 3;
			}
			Intent intent = new Intent(NoticeCatalogActivity.this, BigImages.class);
			intent.putExtra("position", which);
			intent.putStringArrayListExtra("arrayList", getPictures(Integer.valueOf(v.getTag().toString())));
	        startActivity(intent);  
		}
	}
	
	private void connectReq(){
		res = noticePagingUtils.getNoticeCatalog();
		//NoticeCatalogReq req = new NoticeCatalogReq(SoapManager.getInstance().getUserLoginRes().getSession());
		//res = SoapManager.getInstance().getNoticeCatalogRes(req);
	}
	
	private void connectRes(){
		if(res != null && res.getResult() != null && res.getResult().equals("OK")){
			noticesList = res.getNotices();
			adapter.notifyDataSetChanged();
		}
		listView.onRefreshComplete();
	}
	
	private void connectRes(int position){
		if(res != null && res.getResult() != null && res.getResult().equals("OK")){
			noticesList.addAll(res.getNotices());
			adapter.notifyDataSetChanged();
		}
		listView.onFootRefreshComplete();
		listView.setSelection(position);
	}
	
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Integer, Void>() {

			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				noticePagingUtils.clearResource();
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
	public void onFootRefresh() {
		// TODO Auto-generated method stub
		new AsyncTask<Void, Integer, Void>() {
			int position = 0;
			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				position = noticesList.size();
				connectReq();
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				connectRes(position);
				super.onPostExecute(result);
			}
			
		}.execute();
	}
	
}
