package com.howell.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.howell.ecamcompany.R;
import com.howell.ksoap.VideoSourceGroup;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class VideoGroupListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<VideoSourceGroup> videoGroupsList;
//	private Drawable group1,group2,group3,group4,group5,group6,group7;
	
	public VideoGroupListAdapter(Context context,ArrayList<VideoSourceGroup> videoGroupsList) {
        this.mContext = context;
        this.videoGroupsList = videoGroupsList;
//        group1= mContext.getResources().getDrawable(R.drawable.icon_group_1);
//        group2= mContext.getResources().getDrawable(R.drawable.icon_group_2);
//        group3= mContext.getResources().getDrawable(R.drawable.icon_group_3);
//        group4= mContext.getResources().getDrawable(R.drawable.icon_group_4);
//        group5= mContext.getResources().getDrawable(R.drawable.icon_group_5);
//        group6= mContext.getResources().getDrawable(R.drawable.icon_group_6);
//        group7= mContext.getResources().getDrawable(R.drawable.icon_group_7);
    }
	
	public void setVideoGroupsList(ArrayList<VideoSourceGroup> videoGroupsList) {
		this.videoGroupsList = videoGroupsList;
	}

	@Override
    public int getCount() {
        // TODO Auto-generated method stub
        return videoGroupsList.size() ;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return videoGroupsList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
    	System.out.println("getView:"+position);
    	ViewHolder holder = null;
    	if (convertView == null) {
    		LayoutInflater layoutInflater = LayoutInflater.from(mContext);
    		convertView = layoutInflater.inflate(R.layout.video_group_list_item, null);
			holder = new ViewHolder();
				
			//holder.mPlayBtn = (LinearLayout)convertView.findViewById(R.id.ll_video_source_play);
			//holder.mPlayBtn.setOnClickListener(VideoGroupListActivity.this);
			holder.videoGroupName = (TextView)convertView.findViewById(R.id.tv_video_group_name);
            convertView.setTag(holder);
    	}else{
         	holder = (ViewHolder)convertView.getTag();
        }
        //holder.mPlayBtn.setTag(position);
//    	if((position + 1) % 7 == 1){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group1,null,null,null);
//    	}else if ((position + 1) % 7 == 2){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group2,null,null,null);
//    	}else if ((position + 1) % 7 == 3){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group3,null,null,null);
//    	}else if ((position + 1) % 7 == 4){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group4,null,null,null);
//    	}else if ((position + 1) % 7 == 5){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group5,null,null,null);
//    	}else if ((position + 1) % 7 == 6){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group6,null,null,null);
//    	}else if ((position + 1) % 7 == 0){
//    		holder.videoGroupName.setCompoundDrawablesWithIntrinsicBounds (group7,null,null,null);
//    	}
    	holder.videoGroupName.setText(videoGroupsList.get(position).getName());
		return convertView;
    }
    
    class ViewHolder {
        public TextView videoGroupName;
//        public LinearLayout mPlayBtn;
//        public ImageButton mSetBtn;
    }
}


