package com.howell.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.howell.ecamcompany.R;

import java.util.List;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class EventTypeAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<String> list;
	private Drawable icon1,icon2,icon3,icon4,icon5,icon6,icon7,icon8,icon9,icon10,icon11,icon12,icon13,icon14,icon15,icon16,iconRight;

	public EventTypeAdapter(Context mContext ,List<String> list) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		this.list = list;
		icon1= mContext.getResources().getDrawable(R.mipmap.event_type_io);
		icon2= mContext.getResources().getDrawable(R.mipmap.event_type_vmd);
		icon3= mContext.getResources().getDrawable(R.mipmap.event_type_videoloss);
		icon4= mContext.getResources().getDrawable(R.mipmap.event_type_ircut);
		icon5= mContext.getResources().getDrawable(R.mipmap.event_type_daynight);
		icon6= mContext.getResources().getDrawable(R.mipmap.event_type_recordstate);
		icon7= mContext.getResources().getDrawable(R.mipmap.event_type_storagemediumfail);
		icon8= mContext.getResources().getDrawable(R.mipmap.event_type_radifailure);
		icon9= mContext.getResources().getDrawable(R.mipmap.event_type_recordingfailure);
		icon10= mContext.getResources().getDrawable(R.mipmap.event_type_badvideo);
		icon11= mContext.getResources().getDrawable(R.mipmap.event_type_deviceself);
		icon12= mContext.getResources().getDrawable(R.mipmap.event_type_maxconnections);
		icon13= mContext.getResources().getDrawable(R.mipmap.event_type_networkbitrate);
		icon14= mContext.getResources().getDrawable(R.mipmap.event_type_videobitrate);
		icon15= mContext.getResources().getDrawable(R.mipmap.event_type_squint);
		icon16= mContext.getResources().getDrawable(R.mipmap.event_type_videoturned);
		iconRight = mContext.getResources().getDrawable(R.mipmap.qz_icon_forward_normal);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
    		convertView = layoutInflater.inflate(R.layout.event_type_item, null);
			holder = new ViewHolder();
				
			holder.title = (TextView)convertView.findViewById(R.id.event_type_item_title);
            convertView.setTag(holder);
    	}else{
         	holder = (ViewHolder)convertView.getTag();
        }
    	changeIcon(holder.title,position);
    	holder.title.setText(list.get(position));
		return convertView;
	}
	
	private void changeIcon(TextView v,int position){
    	switch (position) {
		case 0:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon1,null,iconRight,null);
			break;
		case 1:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon2,null,iconRight,null);	
			break;
		case 2:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon3,null,iconRight,null);
			break;
		case 3:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon4,null,iconRight,null);
			break;
		case 4:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon5,null,iconRight,null);
			break;
		case 5:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon6,null,iconRight,null);
			break;
		case 6:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon7,null,iconRight,null);
			break;
		case 7:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon8,null,iconRight,null);
			break;
		case 8:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon9,null,iconRight,null);
			break;
		case 9:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon10,null,iconRight,null);
			break;
		case 10:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon11,null,iconRight,null);
			break;
		case 11:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon12,null,iconRight,null);
			break;
		case 12:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon13,null,iconRight,null);
			break;
		case 13:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon14,null,iconRight,null);
			break;
		case 14:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon15,null,iconRight,null);
			break;
		case 15:
			 v.setCompoundDrawablesWithIntrinsicBounds (icon16,null,iconRight,null);
			break;
		default:
			break;
		}
	}
	
	class ViewHolder{
		public TextView title;
	}

}
