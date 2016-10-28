package com.howell.utils;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kobjects.base64.Base64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import com.example.howell.webcamforcompany.DisplayImageInfo;
import com.howell.ksoap.GetPictureReq;
import com.howell.ksoap.GetPictureRes;
import com.howell.ksoap.SoapManager;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DisplayImageManager {
	private Handler mHandler;
	private final static Object syncObj = new Object();
	private static DisplayImageManager instance;
	private ExecutorService executorService;
	
	private DisplayImageManager()
	{
		// 最多只能同时下载3个任务，其余的任务排队等待
		executorService = Executors.newFixedThreadPool(3);
	}
	
	public static DisplayImageManager getInstance()
	{
		if(null == instance)
		{
			synchronized(syncObj) {
				instance = new DisplayImageManager();
			}
			return instance;
		}
		return instance;
	}
	
	public void setHandler(Handler handler) {
		this.mHandler =  handler;
	}
	
	public void freeResource(){
		// 会停止正在进行的任务和拒绝接受新的任务
		executorService.shutdownNow();
	}
	
	// 开始下载，创建一个下载线程
	public void startDownload(DisplayImageInfo info) {
		//downloadFiles.put(file.downloadID, file);
		DisplayImageTask task = new DisplayImageTask(info);
		//taskList.add(task);
		executorService.submit(task);
	}
	
	// 更新listview中对应的item
	public void update(DisplayImageInfo info)
	{
		Message msg = mHandler.obtainMessage();
		msg.obj = info;
		msg.sendToTarget();
				
	}
	
	class DisplayImageTask implements Runnable {
		private DisplayImageInfo info;
		private SoapManager mSoapManager;
		private Bitmap bm;
		
		public DisplayImageTask(DisplayImageInfo info) {
			// TODO Auto-generated constructor stub
			this.info = info;
			this.mSoapManager = SoapManager.getInstance();
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i = 0 ; i < info.getPictureID().size() ; i++){
				if(!new File(SDCardUtils.getBitmapCachePath()+info.getPictureID().get(i)).exists()){
					GetPictureReq req = new GetPictureReq(mSoapManager.getUserLoginRes().getSession(),info.getPictureID().get(i));
					GetPictureRes res = mSoapManager.getGetPictureRes(req);
					if(res!= null && res.getResult() != null){
						if(res.getResult().equals("OK")){
							bm = BitmapFactory.decodeByteArray(Base64.decode(res.getPicture()), 0, Base64.decode(res.getPicture()).length);
									//ScaleImageUtils.decodeByteArray(info.getPictureWidth(), info.getPictureHeight(), Base64.decode(res.getPicture()));
							SDCardUtils.saveBmpToSd(bm,info.getPictureID().get(i));
						}
					}
				}
			}
			update(info);
		}
	}
}
