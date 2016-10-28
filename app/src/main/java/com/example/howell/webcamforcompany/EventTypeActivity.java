package com.example.howell.webcamforcompany;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.howell.adapter.EventTypeAdapter;
import com.howell.ecamcompany.R;

import java.util.Arrays;

/**
 * @author 霍之昊 
 *
 * 类说明:报警类型选择界面
 */
public class EventTypeActivity extends Activity implements OnItemClickListener,OnClickListener{
	
	private ListView eventTypeListView;
	private ImageButton back;
	private String[] eventTypeList;
	private EventTypeAdapter adapter;
	private String deviceId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_type_activity);
		Intent intent = getIntent();
		deviceId = intent.getStringExtra("deviceId");
		back = (ImageButton)findViewById(R.id.event_type_back);
		back.setOnClickListener(this);
		eventTypeListView = (ListView)findViewById(R.id.event_type_listview);
		eventTypeList = getResources (). getStringArray (R.array.event_type);
//		ArrayAdapter<String> adapter = 
//				new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,eventTypeList);
		adapter = new EventTypeAdapter(this,Arrays.asList(eventTypeList));
		eventTypeListView.setAdapter(adapter);
		eventTypeListView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long position) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,SetDeviceNotificationActivity.class);
		intent.putExtra("deviceId", deviceId);
		switch ((int)position) {
		case 0:
			intent.putExtra("whichMode", 2);
			intent.putExtra("eventType", "IO");
			break;
		case 1:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "VMD");
			break;
		case 2:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "Videoloss");
			break;
		case 3:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "IRCut");
			break;
		case 4:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "DayNight");
			break;
		case 5:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "RecordState");
			break;
		case 6:
			intent.putExtra("whichMode", 4);
			intent.putExtra("eventType", "StorageMediumFailure");
			break;
		case 7:
			intent.putExtra("whichMode", 4);
			intent.putExtra("eventType", "RAIDFailure");
			break;
		case 8:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "RecordingFailure");
			break;
		case 9:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "BadVideo");
			break;
		case 10:
			intent.putExtra("whichMode", 0);
			intent.putExtra("eventType", "CpuUsage");
			break;
		case 11:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "MaximumConnections");
			break;
		case 12:
			intent.putExtra("whichMode", 5);
			intent.putExtra("eventType", "NetworkBitrate");
			break;
		case 13:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "VideoBitrate");
			break;
		case 14:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "Squint");
			break;
		case 15:
			intent.putExtra("whichMode", 1);
			intent.putExtra("eventType", "VideoTurned");
			break;
		default:
			break;
		}
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.event_type_back:
			finish();
			break;

		default:
			break;
		}
	}
}
