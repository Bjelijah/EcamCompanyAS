package com.howell.utils;

import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.Log;

import com.howell.ksoap.NATServerRes;
import com.howell.ksoap.SoapManager;
import com.howell.ksoap.VideoSourceInviteReq;
import com.howell.ksoap.VideoSourceInviteRes;
import com.howell.ksoap.VideoSourceTeardownReq;
import com.howell.ksoap.VideoSourceTeardownRes;

import java.util.Random;

@SuppressWarnings("JniMissingFunction")
@SuppressLint("NewApi")
public class VideoInviteUtils {
	private native long createHandle(String account,int is_palyback);
	private native String prepareSDP(long handle,StreamReqContext streamReqContext);
	private native String prepareSDPtest(long handle,StreamReqContext streamReqContext);
	private native int handleRemoteSDP(long handle,StreamReqContext streamReqContext, String dialog_id,
			String remote_sdp);
	private native int start(long handle, StreamReqContext streamReqContext,int timeout_ms);
	public native int getSdpTime(long handle);
	public native int getBegSdpTime(long handle);
	public native int getEndSdpTime(long handle);
	public native void freeHandle(long handle);
	public native void joinThread(long handle);
	public native void freeAllHandle();
	
	public native long testCreatHandle(String account,int is_playback);
	public native void testFreehandle(long handle);
	
	
	public void getStreamLen(int streamLen){
		//PlayerActivity.showStreamLen(streamLen/1024*8);
		//	Log.e("getStreamLen", (streamLen/1024*8)+"");
	}

	public static int REQ_TIMEOUT = 3000;
	private long handle;
	private String local_sdp, remote_sdp;
	private String account, dialogID,oldDialogID,loginSession ,SDPMessage;
	private String internetDeviceId, id ,oldId;
	private int streamingChannel;
	private long beg,end;
	private Random random;
	private SoapManager mSoapManager;
	private boolean isStartFinish;
	private VideoSourceInviteRes inviteRes,oldInviteRes;

	private StreamReqContext context;
	private boolean bNeedReLogin = false;
	
	public boolean getNeedReLogin(){
		return bNeedReLogin;
	}
	
	public boolean isStartFinish() {
		return isStartFinish;
	}
	public void setStartFinish(boolean isStartFinish) {
		this.isStartFinish = isStartFinish;
	}
	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.oldId = this.id;
		this.oldInviteRes = this.inviteRes;
		this.id = id;
	}
	public VideoInviteUtils(String id){
		mSoapManager = SoapManager.getInstance();
		internetDeviceId = mSoapManager.getInternetDeviceId();
		if (internetDeviceId==null) {
			Log.e("log123", "internetDeviceId = null");
		}
		
		this.id = id;
		account = mSoapManager.getUserLoginRes().getUsername();
		loginSession = mSoapManager.getUserLoginRes().getSession();
		streamingChannel = 1;
		random = new Random();
		dialogID = String.valueOf(random.nextInt());
		isStartFinish = false;
	}

	public String getInternetDeviceId(){
		return this.internetDeviceId;
	}
	
	public void setInternetDeviceId(String internetDeviceID){
		this.internetDeviceId = internetDeviceID;
	}
	
	public long getHandle() {
		return handle;
	}

	public void InviteTeardown(){
		if(inviteRes != null){
			dialogID = inviteRes.getDialogId();
		}
		System.out.println("InviteTeardown loginSession:"+loginSession + " internetDeviceId:"+internetDeviceId+" id:"+id+" dialogID:"+dialogID);
		VideoSourceTeardownReq req = new VideoSourceTeardownReq (loginSession,internetDeviceId,id,dialogID);

		VideoSourceTeardownRes res = mSoapManager.getVideoSourceTeardownRes(req);
		System.out.println("VideoSourceTeardownRes :"+res.getResult());
	}

	public void inviteTearOldDown(){
		if (oldInviteRes!=null) {
			oldDialogID = oldInviteRes.getDialogId();
		}
		VideoSourceTeardownReq req = new VideoSourceTeardownReq (loginSession,internetDeviceId,oldId,oldDialogID);
		VideoSourceTeardownRes res = mSoapManager.getVideoSourceTeardownRes(req);


	}

	public boolean changeVideo(String newId){
		InviteTeardown();
		this.id = newId;
		
		
		
		StreamReqContext context = fillStreamReqContext(0, 0, 0, 0);
		
		if (!invite(context,false)) {
			Log.e("false", "postMessage333");
			System.out.println("invite fail");
			isStartFinish = true;
			return false;
		}
		System.out.println("start live start");
		int ret = start(handle, context, REQ_TIMEOUT);

		if(ret != 0) {
			Log.e("false", "postMessage555");
			return false; 
		}
		isStartFinish = true;
		System.out.println("finish start");
		return true;
	}



	public boolean InviteLive(int stream) {
		System.out.println("start invitelive");
		streamingChannel = stream;
		handle = createHandle(account, 0);
		if(handle == -1){
			return false;
		}
		isStartFinish = true;
		System.out.println("Invite handle success!!");
		StreamReqContext context = fillStreamReqContext(0, 0, 0, 0);
		if(context == null ){
			Log.e("false", "postMessage111");
			isStartFinish = true;
			return false;
		}
		//this.context = context;
		if (!invite(context,false)) {
			Log.e("false", "postMessage333");
			System.out.println("invite fail");
			isStartFinish = true;
			return false;
		}
		System.out.println("start live start");
		int ret = start(handle, context, REQ_TIMEOUT);

		if(ret != 0) {
			Log.e("false", "postMessage555");
			return false; 
		}
	
		System.out.println("finish start");
		return true;
	}
	
	@SuppressWarnings("unused")
	private boolean invite(StreamReqContext streamReqContext ,boolean isPlayback) {
		System.out.println("start invite");
		Log.e("------------>>>>", "start invite");
		Log.e("1", streamReqContext.toString());
		if (btest) {
			local_sdp = prepareSDPtest(handle, streamReqContext);
		}else{
			local_sdp = prepareSDP(handle, streamReqContext);
		}
		
		
		
		if(local_sdp.compareTo("")==0 || btest){// test
			Log.e("log123", "local_sdp = null");
			
			return false;
		}
		
		System.out.println("local_sdp:"+local_sdp);
		SDPMessage = Base64.encodeToString(local_sdp.getBytes(), Base64.DEFAULT);
		// System.out.println(account+"\n"+loginSession+"\n"+devID+"\n"+channelNo+"\n"+streamType+"\n"+dialogID+"\n"+SDPMessage);
		try{
			Log.e("---------->>>", "00000000000");
			System.out.println(loginSession+"\n"+internetDeviceId+"\n"+id+"\n"+streamingChannel+"\n"+dialogID+"\n"+SDPMessage);
			inviteRes = mSoapManager.getVideoSourceInviteRes(new VideoSourceInviteReq(loginSession,
					internetDeviceId, id, streamingChannel, SDPMessage));
			Log.e("---------->>>", "aaaaaaaaaaaaaa");
			if(!inviteRes.getResult().equals("OK")){
				Log.e("log123","result=" +inviteRes.getResult());
				if(inviteRes.getResult().equals("NotFound")){
					//TODO 
					bNeedReLogin = true;
				}
				return false;
			}
			// System.out.println(inviteRes.getResult());
			remote_sdp = new String(Base64.decode(inviteRes.getsDP(),  Base64.DEFAULT));
			System.out.println("remote_sdp:"+remote_sdp);
			Log.e("---------->>>", "ccccccccccccc");
			System.out.println(remote_sdp);
			int ret = handleRemoteSDP(handle, streamReqContext, dialogID, remote_sdp);
			if(ret == -1) {
				Log.i("log123", "handle remote sdp ret = -1");
				return false;
			}
			if(isPlayback){
				getSdpTime(handle);
				beg = getBegSdpTime(handle);
				end = getEndSdpTime(handle);
				Log.e("---------->>>", beg+","+end);
			}
			Log.e("---------->>>", "ddddddddddddd ");
		}catch (Exception e) {
			
			Log.e("invite", "invite catch");
			return false;
		}
		// destoryLocalSDP(local_sdp);
		return true;
	}

	private StreamReqContext fillStreamReqContext(int isPlayBack, long beg,
			long end, int re_invite ) {
		//        StreamReqIceOpt opt = new StreamReqIceOpt(1, "180.166.7.214", 34780,
		//                "180.166.7.214", 34780, 0, "100", "100");
		String UpnpIP = "";
		int UpnpPort = 0;
		System.out.println("fillStreamReqContext11111111111");
		StreamReqContext streamReqContext = null;
		NATServerRes res = mSoapManager.getNATServerRes();
		try{
			StreamReqIceOpt opt = new StreamReqIceOpt(1, res.getsTUNServers().get(0).getiPv4Address()/*"42.96.151.28"*/,res.getsTUNServers().get(0).getPort(),
					res.gettURNServers().get(0).getiPv4Address(), res.gettURNServers().get(0).getPort(), 
					0, res.gettURNServers().get(0).getUsername(), res.gettURNServers().get(0).getPassword());
			Log.e("Client", "1");
			//    		StreamReqIceOpt opt = new StreamReqIceOpt(1, "222.191.251.186", 34780,
			//                  "222.191.251.186", 34780, 0, "100", "100");

			Log.e("Client", "3");
			streamReqContext = new StreamReqContext(isPlayBack,
					beg, end, re_invite, 1 << 1 | 1 << 2 ,UpnpIP , UpnpPort, opt);
			Log.e("streamReqContext", "UpnpIP:"+UpnpIP+"UpnpPort:"+UpnpPort);

		}catch (Exception e) {
			
			Log.e("", "fillStreamReqContext fail");
		}
		System.out.println("fillStreamReqContext2222222222222");
		return streamReqContext;
	}
	
	//test
	public long h=-1;
	public boolean btest = false;
	public void testNewResource(){
		btest = true;
		Log.i("log123", "start creat handle");
		h = testCreatHandle(account,0);
		StreamReqContext context = fillStreamReqContext(0, 0, 0, 0);
		if (!invite(context,false)) {
			Log.e("false", "postMessage333");
			System.out.println("invite fail");
			isStartFinish = true;
		//	return false;
		}
		
//		h = createHandle(account, 0);
	}
	public void testFreeResource(){
		if (h==-1){
			return;
		}
		Log.i("log123", " free handle");
		testFreehandle(h);
//		freeHandle(h);
	}
	
	
}
