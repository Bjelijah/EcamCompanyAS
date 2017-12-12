package com.howell.ksoap;

import java.io.Serializable;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

@SuppressWarnings("serial")
public class SoapManager implements Serializable {

    private static String sNameSpace = "http://www.howell.net.cn/InternetService/MCU/";

    private static String sEndPoint = "http://www.haoweis.com:8800/InternetService/InternetService.svc?wsdl";

    //private static String sSoapAction = null;

    private static SoapManager sInstance = new SoapManager();
    
    private UserLoginRes userLoginRes;
    private UserLoginReq userLoginReq;
    
    private String internetDeviceId;
    
	public UserLoginRes getUserLoginRes() {
    	return userLoginRes == null ? null : userLoginRes;
	}
	
	public String getInternetDeviceId(){
    	return internetDeviceId;
    }
    
    public void setInternetDeviceId(String internetDeviceId){
    	Log.i("log123", "set internet device id:"+internetDeviceId);
    	this.internetDeviceId = internetDeviceId;
    }
    
	private SoapManager() {

    }

    public static SoapManager getInstance() {
        return sInstance;
    }


	public SoapObject initEnvelopAndTransport(SoapObject rpc , String sSoapAction) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER10);
        envelope.bodyOut = rpc;
        envelope.dotNet = true;
        envelope.encodingStyle = "UTF-8";
        envelope.setOutputSoapObject(rpc);
//        FakeX509TrustManager.allowAllSSL();
        HttpTransportSE transport;
		transport = new HttpTransportSE(sEndPoint);
		transport.debug = true;
		try {
		    transport.call(sSoapAction, envelope);
		} catch (SocketTimeoutException e) {
			Log.e("", "SocketTimeoutException");
		    e.printStackTrace();
		} catch (Exception e) {
			Log.e("", "Exception");
		    e.printStackTrace();
		}

        SoapObject soapObject = (SoapObject) envelope.bodyIn;
        return soapObject;
    }
	
	//登录
    public UserLoginRes getUserLoginRes(UserLoginReq req) {
    	Log.e("SoapManager", "UserLoginRes");
    	this.internetDeviceId = req.getInternetDeviceId();
    	this.userLoginReq = req;
    	userLoginRes = new UserLoginRes();
        SoapObject rpc = new SoapObject(sNameSpace, "UserLoginReq");
        rpc.addProperty("Username", req.getUsername());
        System.out.println("Username:"+req.getUsername());
        rpc.addProperty("UserType", req.getUserType());
        rpc.addProperty("PasswordType", req.getPasswordType());
        rpc.addProperty("Password", req.getPassword());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("ApplicationId", req.getApplicationId());
        rpc.addProperty("ApplicationVersion", req.getApplicationVersion());
        rpc.addProperty("NetworkOperator", req.getNetworkOperator());
        System.out.println("NetworkOperator:"+req.getNetworkOperator());
        rpc.addProperty("NetworkType", req.getNetworkType());
        System.out.println("NetworkType:"+req.getNetworkType());
        SoapObject userAgent = new SoapObject(sNameSpace, "UserAgent");
        userAgent.addProperty("UUID",req.getUserAgent().getuUID());
        System.out.println("UUID:"+req.getUserAgent().getuUID());
        userAgent.addProperty("Model",req.getUserAgent().getModel());
        System.out.println("Model:"+req.getUserAgent().getModel());
        userAgent.addProperty("Name",req.getUserAgent().getName());
        System.out.println("Name:"+req.getUserAgent().getName());
        userAgent.addProperty("AgentType",req.getUserAgent().getAgentType());
        System.out.println("AgentType:"+req.getUserAgent().getAgentType());
        userAgent.addProperty("AgentOSType",req.getUserAgent().getAgentOSType());
        System.out.println("AgentOSType:"+req.getUserAgent().getAgentOSType());
        userAgent.addProperty("Manufactory",req.getUserAgent().getManufactory());
        System.out.println("Manufactory:"+req.getUserAgent().getManufactory());
        userAgent.addProperty("OSVersion",req.getUserAgent().getoSVersion());
        System.out.println("OSVersion:"+req.getUserAgent().getoSVersion());
        userAgent.addProperty("IMEI",req.getUserAgent().getiMEI());
        System.out.println("IMEI:"+req.getUserAgent().getiMEI());
        rpc.addProperty("UserAgent",userAgent);
        
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/UserLogin");
		Log.e("123","obj="+object.toString());
        try{
	        Object result = object.getProperty("Result");
	        userLoginRes.setResult(result.toString());
	        Object userName = object.getProperty("Username");
	        userLoginRes.setUsername(userName.toString());
	        Object session = object.getProperty("Session");
	        userLoginRes.setSession(session.toString());
	        System.out.println("session:"+session);
	        Object nickname = object.getProperty("Nickname");
	        userLoginRes.setNickname(nickname.toString());
	        System.out.println("userLoginRes:"+userLoginRes.getResult().toString());
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("getUserLoginRes crash");
		}
        return userLoginRes;
    }
    
    //查询设备目录
    public DeviceCatalogRes getDeviceCatalogRes(DeviceCatalogReq req) {
    	Log.e("SoapManager", "deviceCatalogRes");
    	DeviceCatalogRes deviceCatalogRes = new DeviceCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "DeviceCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/DeviceCatalog");
        try{
	        Object result = object.getProperty("Result");
	        deviceCatalogRes.setResult(result.toString());
	        System.out.println("deviceCatalogRes:"+result.toString());
	        SoapObject page = (SoapObject)object.getProperty("Page");
	        Object pageIndex = page.getProperty("PageIndex");
	        Object pageSize = page.getProperty("PageSize");
	        Object pageCount = page.getProperty("PageCount");
	        Object recordCount = page.getProperty("RecordCount");
	        Object totalRecordCount = page.getProperty("TotalRecordCount");
	        deviceCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
	        		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
	        System.out.println("deviceCatalogRes page:"+deviceCatalogRes.getPage().toString());
	        SoapObject devices = (SoapObject)object.getProperty("Devices");
	        System.out.println(devices.toString());
	        ArrayList<Device> deviceList = new ArrayList<Device>();
	        for(int i = 0 ;i<devices.getPropertyCount();i++){
	        	System.out.println(i);
	        	SoapObject d = (SoapObject) devices.getProperty(i);
	        	Device device = new Device();
	        	String id = d.getProperty("Id").toString();
	        	device.setId(id);
	        	String name = d.getProperty("Name").toString();
	        	device.setName(name);
	        	
	        	
	        	deviceList.add(device);
	        }
	        deviceCatalogRes.setDevices(deviceList);
	        System.out.println("deviceCatalogRes:"+deviceCatalogRes.getResult().toString());
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("getDeviceCatalogRes crash");
		}
        return deviceCatalogRes;
    }
    
    //查询视频源目录
    public VideoSourceCatalogRes getVideoSourceCatalogRes(VideoSourceCatalogReq req) {
    	Log.e("SoapManager", "VideoSourceCatalogRes");
    	VideoSourceCatalogRes videoSourceCatalogRes = new VideoSourceCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "VideoSourceCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        if(req.getId() != null){
        	rpc.addProperty("Id", req.getId());
        }
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        System.out.println("VideoSourceCatalogReq"+req.toString());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/VideoSourceCatalog");
        try{
//Result
	        Object result = object.getProperty("Result");
	        videoSourceCatalogRes.setResult(result.toString());
	        System.out.println("videoSourceCatalogRes res:"+result);
	        System.out.println("videoSourceCatalogRes:"+result.toString());
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getVideoSourceCatalogRes(req);
	        }
//Page	        
	        SoapObject page = (SoapObject)object.getProperty("Page");
	        Object pageIndex = page.getProperty("PageIndex");
	        Object pageSize = page.getProperty("PageSize");
	        Object pageCount = page.getProperty("PageCount");
	        Object recordCount = page.getProperty("RecordCount");
	        Object totalRecordCount = page.getProperty("TotalRecordCount");
	        videoSourceCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
	        		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
	        System.out.println("VideoSourceCatalogRes page:"+videoSourceCatalogRes.getPage().toString());
        }catch (Exception e) {
				// TODO: handle exception
	    }
//VideoSources   
	    SoapObject videoSources = (SoapObject)object.getProperty("VideoSources");
	    ArrayList<VideoSource> vsList = new ArrayList<VideoSource>();
	    for(int i = 0 ;i<videoSources.getPropertyCount();i++){
	    	VideoSource vs = new VideoSource (); 
	        SoapObject videoSource = (SoapObject) videoSources.getProperty(i);
	        //id
	        String id = videoSource.getProperty("Id").toString();
	        vs.setId(id);
	        //name
	        String name = videoSource.getProperty("Name").toString();
	        vs.setName(name);
	        //pseudoCode
	        try{
	        	int pseudoCode = Integer.valueOf(videoSource.getProperty("PseudoCode").toString());
	        	vs.setPseudoCode(pseudoCode);
	        }catch (Exception e) {
				// TODO: handle exception
	        	System.out.println("pseudoCode crash");
	        }
	        //videoInputChannelId
	        String videoInputChannelId = videoSource.getProperty("VideoInputChannelId").toString();
	        vs.setVideoInputChannelId(videoInputChannelId);
	        //cameraType
	        String cameraType = videoSource.getProperty("CameraType").toString();
	        vs.setCameraType(cameraType);
	        //PTZ
	        try{
	        	boolean ptz = Boolean.valueOf(videoSource.getProperty("PTZ").toString());
	        	vs.setpTZ(ptz);
	        }catch (Exception e) {
				// TODO: handle exception
	        	System.out.println("ptz crash");
	        }
	        try{
	        	boolean infrared = Boolean.valueOf(videoSource.getProperty("Infrared").toString());
	        	vs.setInfrared(infrared);
	        }catch (Exception e) {
				// TODO: handle exception
	        	System.out.println("infrared chrash");
	        }
	        boolean online = Boolean.valueOf(videoSource.getProperty("Online").toString());
	        vs.setOnline(online);
	        try{
	        	String groupPath = videoSource.getProperty("GroupPath").toString();
	        	vs.setGroupPath(groupPath);
	        }catch (Exception e) {
				// TODO: handle exception
	        	System.out.println("groupPath crash");
	        }
	        System.out.println("videosource:"+vs.toString());
	        vsList.add(vs);
	    }
	    videoSourceCatalogRes.setVideoSources(vsList);
        return videoSourceCatalogRes;
    }

    //请求视频源数据
    public VideoSourceInviteRes getVideoSourceInviteRes(VideoSourceInviteReq req) {
    	Log.e("SoapManager", "VideoSourceInviteReq");
    	VideoSourceInviteRes videoSourceInviteRes = new VideoSourceInviteRes();
        SoapObject rpc = new SoapObject(sNameSpace, "VideoSourceInviteReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("StreamChannel", req.getStreamingChannel());
        rpc.addProperty("SDP", req.getsDP());
        
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/VideoSourceInvite");
        try{
	        Object result = object.getProperty("Result");
	        videoSourceInviteRes.setResult(result.toString());
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getVideoSourceInviteRes(req);
	        }
	        Object dialogId = object.getProperty("DialogId");
	        videoSourceInviteRes.setDialogId(dialogId.toString());
	        Object sdp = object.getProperty("SDP");
	        Log.i("log123", "sdp="+sdp.toString());
	        videoSourceInviteRes.setsDP(sdp.toString());
	        System.out.println("VideoSourceInviteRes:"+videoSourceInviteRes.getResult().toString());
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("VideoSourceInviteRes crash");
		}
        return videoSourceInviteRes;
    }
    
    //注销视频源数据 
    public VideoSourceTeardownRes getVideoSourceTeardownRes(VideoSourceTeardownReq req) {
    	Log.e("SoapManager", "VideoSourceInviteReq");
    	VideoSourceTeardownRes videoSourceTeardownRes = new VideoSourceTeardownRes();
        SoapObject rpc = new SoapObject(sNameSpace, "VideoSourceTeardownReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        //rpc.addProperty("Id", req.getId());
        rpc.addProperty("DialogId", req.getDialogId());
      
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/VideoSourceTeardown");
        Object result = object.getProperty("Result");
        videoSourceTeardownRes.setResult(result.toString());
        if(result.toString().equals("SessionExpired")){
        	userLoginRes = getUserLoginRes(userLoginReq);
        	req.setSession(userLoginRes.getSession());
        	return getVideoSourceTeardownRes(req);
        }
     
        return videoSourceTeardownRes;
    }
    
    //获取NAT服务器连接信息
    public NATServerRes getNATServerRes() {
    	Log.e("SoapManager", "NATServerRes");
    	NATServerRes nATServerRes = new NATServerRes();
        SoapObject rpc = new SoapObject(sNameSpace, "NATServerReq");
        
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/NATServer");
	    Object result = object.getProperty("Result");
	    nATServerRes.setResult(result.toString());
	    System.out.println("nATServerRes:"+nATServerRes.getResult().toString());
	    if(result.toString().equals("SessionExpired")){
        	userLoginRes = getUserLoginRes(userLoginReq);
        	return getNATServerRes();
        }
	    try{
		    SoapObject sTUNServers = (SoapObject)object.getProperty("STUNServers");
		    System.out.println("1111");
			ArrayList<STUNServer> sTUNServersList = new ArrayList<STUNServer>();
			for(int i = 0 ;i<sTUNServers.getPropertyCount();i++){
				STUNServer s = new STUNServer();
				SoapObject sTUNServer = (SoapObject) sTUNServers.getProperty(i);
				String iPv4Address = sTUNServer.getProperty("IPv4Address").toString();
				s.setiPv4Address(iPv4Address);
				System.out.println("STUNServer iPv4Address:"+iPv4Address);
				String iPv6Address = sTUNServer.getProperty("IPv6Address").toString();
				s.setiPv6Address(iPv6Address);
				System.out.println("STUNServer iPv6Address:"+iPv6Address);
				int port = Integer.valueOf(sTUNServer.getProperty("Port").toString());
				s.setPort(port);
				System.out.println("STUNServer "+port);
				s.toString();
				sTUNServersList.add(s);
			}
			nATServerRes.setsTUNServers(sTUNServersList);
	    }catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("STUNServer crash");
		}
	    
	    try{
	    	SoapObject tURNServers = (SoapObject)object.getProperty("TURNServers");
			ArrayList<TURNServer> tURNServersList = new ArrayList<TURNServer>();
			for(int i = 0 ;i<tURNServers.getPropertyCount();i++){
				TURNServer t = new TURNServer();
				SoapObject sTUNServer = (SoapObject) tURNServers.getProperty(i);
				String iPv4Address = sTUNServer.getProperty("IPv4Address").toString();
				t.setiPv4Address(iPv4Address);
				System.out.println("TURNServer iPv6Address:"+iPv4Address);
				String iPv6Address = sTUNServer.getProperty("IPv6Address").toString();
				t.setiPv6Address(iPv6Address);
				System.out.println("TURNServer iPv6Address:"+iPv6Address);
				int port = Integer.valueOf(sTUNServer.getProperty("Port").toString());
				t.setPort(port);
				System.out.println("TURNServer"+port);
				String username = sTUNServer.getProperty("Username").toString();
				t.setUsername(username);
				System.out.println("TURNServer"+username);
				String password = sTUNServer.getProperty("Password").toString();
				t.setPassword(password);
				System.out.println("TURNServer"+password);
				t.toString();
				tURNServersList.add(t);
			}
			nATServerRes.settURNServers(tURNServersList);
	    }catch (Exception e) {
			// TODO: handle exception
	    	System.out.println("TURNServer crash");
		}
        return nATServerRes;
    }
    
    //获取视频录像列表
    public VideoRecordCatalogRes getVideoRecordCatalogRes(VideoRecordCatalogReq req) {
    	Log.e("SoapManager", "VideoSourceInviteReq");
    	VideoRecordCatalogRes videoRecordCatalogRes = new VideoRecordCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "VideoRecordCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("StreamingChannel", req.getStreamingChannel());
        
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/VideoRecordCatalog");
        Object result = object.getProperty("Result");
        videoRecordCatalogRes.setResult(result.toString());
        if(result.toString().equals("SessionExpired")){
        	userLoginRes = getUserLoginRes(userLoginReq);
        	req.setSession(userLoginRes.getSession());
        	return getVideoRecordCatalogRes(req);
        }
        return videoRecordCatalogRes;
    }
    
    //订阅或修改事件通知
    public SubscribeEventRes getSubscribeEventRes(SubscribeEventReq req) {
    	Log.e("SoapManager", "SubscribeEventRes");
    	SubscribeEventRes subscribeEventRes = new SubscribeEventRes();
        SoapObject rpc = new SoapObject(sNameSpace, "SubscribeEventReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("ComponentId", req.getComponentId());
        rpc.addProperty("EventType", req.getEventType());
        rpc.addProperty("TriggerState", req.getTriggerState());
        rpc.addProperty("NotificationMethod", req.getNotificationMethod());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/SubscribeEvent");
        try{
	        Object result = object.getProperty("Result");
	        subscribeEventRes.setResult(result.toString());
	        System.out.println("SubscribeEventRes:"+result.toString());
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getSubscribeEventRes(req);
	        }
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("SubscribeEventRes crash");
		}
        return subscribeEventRes;
    }
    
    //退订事件通知
    public UnsubscribeEventRes getUnsubscribeEventRes(UnsubscribeEventReq req) {
    	Log.e("SoapManager", "UnsubscribeEventRes");
    	UnsubscribeEventRes unsubscribeEventRes = new UnsubscribeEventRes();
        SoapObject rpc = new SoapObject(sNameSpace, "UnsubscribeEventReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("ComponentId", req.getComponentId());
        rpc.addProperty("EventType", req.getEventType());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/UnsubscribeEvent");
        try{
	        Object result = object.getProperty("Result");
	        unsubscribeEventRes.setResult(result.toString());
	        System.out.println("UnsubscribeEventRes:"+result);
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getUnsubscribeEventRes(req);
	        }
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("UnsubscribeEventRes crash");
		}
        return unsubscribeEventRes;
    }
    
    //查询系统健康度信息
	public SystemHealthStatisticsRes getSystemHealthStatisticsRes(SystemHealthStatisticsReq req) {
    	Log.e("SoapManager", "SystemHealthStatisticsReq");
    	SystemHealthStatisticsRes systemHealthStatisticsRes = new SystemHealthStatisticsRes();
        SoapObject rpc = new SoapObject(sNameSpace, "SystemHealthStatisticsReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/SystemHealthStatistics");
        try{
	        Object result = object.getProperty("Result");
	        systemHealthStatisticsRes.setResult(result.toString());
	        System.out.println("SystemHealthStatisticsRes:"+result);
	        
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getSystemHealthStatisticsRes(req);
	        }
	        SoapObject systemHealthStatistics = (SoapObject)object.getProperty("SystemHealthStatistics");
	        System.out.println(systemHealthStatistics.toString());
	        
	        SystemHealthStatistics shs = new SystemHealthStatistics();
	        
		        Object id = systemHealthStatistics.getProperty("Id");
		        System.out.println("id:"+id);
		        shs.setId(id.toString());
		        
		        Object percentage = systemHealthStatistics.getProperty("Percentage");
		        System.out.println("percentage:"+percentage);
		        shs.setPercentage(Double.valueOf(percentage.toString()));
	        
		        SystemFaultStatistics sfs = new SystemFaultStatistics();
		        SystemWarningStatistics sws = new SystemWarningStatistics();
		        
		        SoapObject faults = (SoapObject)systemHealthStatistics.getProperty("Faults");
		        System.out.println("faults:"+faults);
		        	Object faultNumber = faults.getProperty("FaultNumber");
		        	System.out.println("faultNumber:"+faultNumber);
		        	sfs.setFaultNumber(Integer.valueOf(faultNumber.toString()));
		        	
		        	Object offlineNumber = faults.getProperty("OfflineNumber");
		        	System.out.println("offlineNumber:"+offlineNumber);
		        	sfs.setOfflineNumber(Integer.valueOf(offlineNumber.toString()));
		        	
		        	Object storageMediumAbnormalNumber = faults.getProperty("StorageMediumAbnormalNumber");
		        	System.out.println("storageMediumAbnormalNumber:"+storageMediumAbnormalNumber);
		        	sfs.setStorageMediumAbnormalNumber(Integer.valueOf(storageMediumAbnormalNumber.toString()));
		        	
		        	Object videolossNumber = faults.getProperty("VideolossNumber");
		        	System.out.println("VideolossNumber:"+videolossNumber);
		        	sfs.setVideolossNumber(Integer.valueOf(videolossNumber.toString()));
		        shs.setFaults(sfs);
		        
		        SoapObject warnings = (SoapObject)systemHealthStatistics.getProperty("Warnings");
		        System.out.println("warnings:"+warnings);
		        
			        Object warningNumber = warnings.getProperty("WarningNumber");
		        	System.out.println("WarningNumber:"+warningNumber);
		        	sws.setWarningNumber(Integer.valueOf(warningNumber.toString()));
		        	
		        	Object cPUUsageNumber = warnings.getProperty("CPUUsageNumber");
		        	System.out.println("CPUUsageNumber:"+cPUUsageNumber);
		        	sws.setcPUUsageNumber(Integer.valueOf(cPUUsageNumber.toString()));
		        	
		        	Object memoryUsageNumber = warnings.getProperty("MemoryUsageNumber");
		        	System.out.println("MemoryUsageNumber:"+memoryUsageNumber);
		        	sws.setMemoryUsageNumber(Integer.valueOf(memoryUsageNumber.toString()));
		        	
		        	Object networkUsageNumber = warnings.getProperty("NetworkUsageNumber");
		        	System.out.println("NetworkUsageNumber:"+networkUsageNumber);
		        	sws.setNetworkUsageNumber(Integer.valueOf(networkUsageNumber.toString()));
		        	
		        	Object superHeatNumber = warnings.getProperty("SuperHeatNumber");
		        	System.out.println("SuperHeatNumber:"+superHeatNumber);
		        	sws.setSuperHeatNumber(Integer.valueOf(superHeatNumber.toString()));
		        	
		        	Object voltageInstabilityNumber = warnings.getProperty("VoltageInstabilityNumber");
		        	System.out.println("VoltageInstabilityNumber:"+voltageInstabilityNumber);
		        	sws.setVoltageInstabilityNumber(Integer.valueOf(voltageInstabilityNumber.toString()));
		        	
		        	Object videoHighLoadNumber = warnings.getProperty("VideoHighLoadNumber");
		        	System.out.println("VideoHighLoadNumber:"+videoHighLoadNumber);
		        	sws.setVideoHighLoadNumber(Integer.valueOf(videoHighLoadNumber.toString()));
		        	
		        	Object videoNetworkInstabilityNumber = warnings.getProperty("VideoNetworkInstabilityNumber");
		        	System.out.println("VideoNetworkInstabilityNumber:"+videoNetworkInstabilityNumber);
		        	sws.setVideoNetworkInstabilityNumber(Integer.valueOf(videoNetworkInstabilityNumber.toString()));
		        	
		        	Object teardownNumber = warnings.getProperty("TeardownNumber");
		        	System.out.println("TeardownNumber:"+teardownNumber);
		        	sws.setTeardownNumber(Integer.valueOf(teardownNumber.toString()));
		        	
		        	Object videoConnectionFailureNumber = warnings.getProperty("VideoConnectionFailureNumber");
		        	System.out.println("VideoConnectionFailureNumber:"+videoConnectionFailureNumber);
		        	sws.setVideoConnectionFailureNumber(Integer.valueOf(videoConnectionFailureNumber.toString()));
		        
		        shs.setWarnings(sws);
	        systemHealthStatisticsRes.setSystemHealthStatistics(shs);
        }catch (Exception e) {
			// TODO: handle exception
        	Log.e("Exception", "systemHealthStatisticsRes crash");
        	SystemHealthStatistics systemHealthStatistics = new SystemHealthStatistics();
        	systemHealthStatistics.setFaults(new SystemFaultStatistics());
        	systemHealthStatistics.setWarnings(new SystemWarningStatistics());
        	systemHealthStatisticsRes.setSystemHealthStatistics(systemHealthStatistics);
		}
        return systemHealthStatisticsRes;
    }
    
    //获取网络接入设备列表
    public InternetDeviceCatalogRes getInternetDeviceCatalogRes(InternetDeviceCatalogReq req) {
    	Log.e("SoapManager", "InternetDeviceCatalogReq");
    	InternetDeviceCatalogRes internetDeviceCatalogRes = new InternetDeviceCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "InternetDeviceCatalogReq");
        rpc.addProperty("Session", req.getSession());
        if(req.getPageSize() != 0)
        	rpc.addProperty("PageIndex", req.getPageIndex());
        if(req.getPageSize() != 0)
        	rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/InternetDeviceCatalog");
        try{
	        Object result = object.getProperty("Result");
	        internetDeviceCatalogRes.setResult(result.toString());
	        System.out.println("internetDeviceCatalogRes:"+result);
	        
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getInternetDeviceCatalogRes(req);
	        }
	        
	        SoapObject objectPage = (SoapObject)object.getProperty("Page");
	        Page page = new Page(); 
	        String pageIndex = objectPage.getProperty("PageIndex").toString();
	        page.setPageIndex(Integer.valueOf(pageIndex));
	        String pageSize = objectPage.getProperty("PageSize").toString();
	        page.setPageSize(Integer.valueOf(pageSize));
	        String pageCount = objectPage.getProperty("PageCount").toString();
	        page.setPageCount(Integer.valueOf(pageCount));
	        String totalRecordCount = objectPage.getProperty("TotalRecordCount").toString();
	        page.setTotalRecordCount(Integer.valueOf(totalRecordCount));
	          
	        internetDeviceCatalogRes.setPage(page);
	        System.out.println("internetDeviceCatalog page:"+page);
	        
	        SoapObject internetDevices = (SoapObject)object.getProperty("InternetDevices");
	        System.out.println(internetDevices.toString());
	        ArrayList<InternetDevice> internetDeviceList = new ArrayList<InternetDevice>();
			for(int i = 0 ;i<internetDevices.getPropertyCount();i++){
				InternetDevice internetDevice = new InternetDevice();
				SoapObject objectInternetDevice = (SoapObject) internetDevices.getProperty(i);
				String id = objectInternetDevice.getProperty("Id").toString();
				internetDevice.setId(id);
				System.out.println(id);
				String name = objectInternetDevice.getProperty("Name").toString();
				internetDevice.setName(name);
				System.out.println(name);
				String manufacturer = objectInternetDevice.getProperty("Manufacturer").toString();
				internetDevice.setManufacturer(manufacturer);
				System.out.println(manufacturer);
				String model = objectInternetDevice.getProperty("Model").toString();
				internetDevice.setModel(model);
				System.out.println(model);
				String firmware = objectInternetDevice.getProperty("Firmware").toString();
				internetDevice.setFirmware(firmware);
				System.out.println(firmware);
				String serialNumber = objectInternetDevice.getProperty("SerialNumber").toString();
				internetDevice.setSerialNumber(serialNumber);
				System.out.println(serialNumber);
				boolean isOnline = Boolean.valueOf(objectInternetDevice.getProperty("IsOnline").toString());
				internetDevice.setOnline(isOnline);
				System.out.println(isOnline);
				String lastOnlineTime = objectInternetDevice.getProperty("LastOnlineTime").toString();
				internetDevice.setLastOnlineTime(lastOnlineTime);
				System.out.println(lastOnlineTime);
				Long systemUpTime = Long.valueOf(objectInternetDevice.getProperty("SystemUpTime").toString());
				internetDevice.setSystemUpTime(systemUpTime);
				System.out.println(systemUpTime);
				System.out.println(internetDevice.toString());
				internetDeviceList.add(internetDevice);
			}
			internetDeviceCatalogRes.setInternetDevices(internetDeviceList);
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("internetDeviceCatalogRes crash");
		}
        return internetDeviceCatalogRes;
    }
    
    //查询系统故障报告
    public SystemFaultReportCatalogRes getSystemFaultReportCatalogRes(SystemFaultReportCatalogReq req) {
    	Log.e("SoapManager", "systemFaultReportCatalogRes");
    	SystemFaultReportCatalogRes systemFaultReportCatalogRes = new SystemFaultReportCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "SystemFaultReportCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        if(req.getPageSize() != 0)
        	rpc.addProperty("PageIndex", req.getPageIndex());
        if(req.getPageSize() != 0)
        	rpc.addProperty("PageSize", req.getPageSize());
        rpc.addProperty("FaultType", req.getFaultType());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/SystemFaultReportCatalog");
        try{
	        Object result = object.getProperty("Result");
	        systemFaultReportCatalogRes.setResult(result.toString());
	        System.out.println("systemFaultReportCatalogRes:"+result);
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getSystemFaultReportCatalogRes(req);
	        }
	        Object page = object.getProperty("Page");
	        systemFaultReportCatalogRes.setResult(page.toString());
	        System.out.println("page:"+page);
	        SoapObject systemFaultReports = (SoapObject)object.getProperty("SystemFaultReports");
	        System.out.println(systemFaultReports.toString());
	        ArrayList<SystemFaultReport> systemFaultReportList = new ArrayList<SystemFaultReport>();
			
	        for(int i = 0 ;i<systemFaultReports.getPropertyCount();i++){
	        	
	        	SoapObject wsdlSystemFaultReport = (SoapObject) systemFaultReports.getProperty(i);
				System.out.println("test:"+wsdlSystemFaultReport);
	        	
	        	SystemFaultReport systemFaultReport = new SystemFaultReport();
				String id = wsdlSystemFaultReport.getProperty("Id").toString();
				systemFaultReport.setId(id);
				System.out.println("id:"+id);
				
				String creationTime = wsdlSystemFaultReport.getProperty("CreationTime").toString();
				systemFaultReport.setCreationTime(creationTime);
				System.out.println("creationTime:"+creationTime);
				
				String faultType = wsdlSystemFaultReport.getProperty("FaultType").toString();
				systemFaultReport.setFaultType(faultType);
				System.out.println("faultType:"+faultType);
				
				String componentId = wsdlSystemFaultReport.getProperty("ComponentId").toString();
				systemFaultReport.setComponentId(componentId);
				System.out.println("componentId:"+componentId);
				
				String recovered = wsdlSystemFaultReport.getProperty("Recovered").toString();
				systemFaultReport.setRecovered(Boolean.valueOf(recovered));
				System.out.println("recovered:"+recovered);
				try{
					String recoveryTime = wsdlSystemFaultReport.getProperty("RecoveryTime").toString();
					systemFaultReport.setRecoveryTime(recoveryTime);
					System.out.println("recoveryTime:"+recoveryTime);
				}catch(Exception e){
					
				}
				
				String description = wsdlSystemFaultReport.getProperty("Description").toString();
				systemFaultReport.setDescription(description);
				System.out.println("description:"+description);
				
				System.out.println(systemFaultReport.toString());
				systemFaultReportList.add(systemFaultReport);
			}
			systemFaultReportCatalogRes.setSystemFaultReports(systemFaultReportList);
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("systemFaultReportRes crash");
        	ArrayList<SystemFaultReport> systemFaultReportList = new ArrayList<SystemFaultReport>();
        	systemFaultReportCatalogRes.setSystemFaultReports(systemFaultReportList);
		}
        return systemFaultReportCatalogRes;
    }

    //查询系统警告报告
    public SystemWarningReportCatalogRes getSystemWarningReportCatalogRes(SystemWarningReportCatalogReq req) {
    	Log.e("SoapManager", "SystemWarningReportCatalog");
    	SystemWarningReportCatalogRes systemWarningReportCatalogRes = new SystemWarningReportCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "SystemWarningReportCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        if(req.getPageSize() != 0)
        	rpc.addProperty("PageIndex", req.getPageIndex());
        if(req.getPageSize() != 0)
        	rpc.addProperty("PageSize", req.getPageSize());
        rpc.addProperty("WarningType", req.getWarningType());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/SystemWarningReportCatalog");
        try{
	        Object result = object.getProperty("Result");
	        systemWarningReportCatalogRes.setResult(result.toString());
	        System.out.println("systemWarningReportCatalogRes:"+result);
	        
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getSystemWarningReportCatalogRes(req);
	        }
	        
	        Object page = object.getProperty("Page");
	        systemWarningReportCatalogRes.setResult(page.toString());
	        
	        SoapObject systemWarningReports = (SoapObject)object.getProperty("SystemWarningReports");
	        System.out.println(systemWarningReports.toString());
	        
	        ArrayList<SystemWarningReport> systemWarningReportList = new ArrayList<SystemWarningReport>();
			
	        for(int i = 0 ;i<systemWarningReports.getPropertyCount();i++){
	        	SoapObject wsdlSystemWarningReport = (SoapObject) systemWarningReports.getProperty(i);
				System.out.println("test:"+wsdlSystemWarningReport);
	        	
	        	SystemWarningReport systemWarningReport = new SystemWarningReport();
				String id = wsdlSystemWarningReport.getProperty("Id").toString();
				systemWarningReport.setId(id);
				System.out.println("id:"+id);
				
				String creationTime = wsdlSystemWarningReport.getProperty("CreationTime").toString();
				systemWarningReport.setCreationTime(creationTime);
				System.out.println("creattime:"+creationTime);
				
				String warningType = wsdlSystemWarningReport.getProperty("WarningType").toString();
				systemWarningReport.setWarningType(warningType);
				System.out.println("warningType:"+warningType);
				
				String componentId = wsdlSystemWarningReport.getProperty("ComponentId").toString();
				systemWarningReport.setComponentId(componentId);
				System.out.println("componentId:"+componentId);
				
				String description = wsdlSystemWarningReport.getProperty("Description").toString();
				systemWarningReport.setDescription(description);
				System.out.println("description:"+description);
				
				System.out.println(systemWarningReport.toString());
				systemWarningReportList.add(systemWarningReport);
			}
	        systemWarningReportCatalogRes.setSystemWarningReports(systemWarningReportList);
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("systemFaultReportRes crash");
        	ArrayList<SystemWarningReport> systemWarningReportList = new ArrayList<SystemWarningReport>();
        	systemWarningReportCatalogRes.setSystemWarningReports(systemWarningReportList);
		}
        return systemWarningReportCatalogRes;
    }
    
    //查询视频源分组 
    public VideoSourceGroupCatalogRes getVideoSourceGroupCatalogRes(VideoSourceGroupCatalogReq req) {
    	Log.e("SoapManager", "VideoSourceGroupCatalogReq");
    	VideoSourceGroupCatalogRes videoSourceGroupCatalogRes = new VideoSourceGroupCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "VideoSourceGroupCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/VideoSourceGroupCatalog");
        try{
        	//Result
	        Object result = object.getProperty("Result");
	        videoSourceGroupCatalogRes.setResult(result.toString());
	        System.out.println("result:"+result);
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getVideoSourceGroupCatalogRes(req);
	        }
	        //Page
	        SoapObject page = (SoapObject)object.getProperty("Page");
	        Object pageIndex = page.getProperty("PageIndex");
	        Object pageSize = page.getProperty("PageSize");
	        Object pageCount = page.getProperty("PageCount");
	        Object recordCount = page.getProperty("RecordCount");
	        Object totalRecordCount = page.getProperty("TotalRecordCount");
	        videoSourceGroupCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
	        		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
	        System.out.println("VideoSourceGroupCatalogRes page:"+videoSourceGroupCatalogRes.getPage().toString());
	        //VideoSourceGroups
	        ArrayList<VideoSourceGroup> list = new ArrayList<VideoSourceGroup>();
	        SoapObject videoSourceGroups = (SoapObject)object.getProperty("VideoSourceGroups");
	        for(int i = 0 ;i<videoSourceGroups.getPropertyCount();i++){
	        	SoapObject realVideoSourceGroups = (SoapObject) videoSourceGroups.getProperty(i);
	        	VideoSourceGroup listItem = new VideoSourceGroup();
	        	Object id = realVideoSourceGroups.getProperty("Id");
	        	listItem.setId(id.toString());
	        	System.out.println("id"+id);
	        	Object name = realVideoSourceGroups.getProperty("Name");
	        	listItem.setName(name.toString());
	        	System.out.println("name"+name);
	        	Object comment = realVideoSourceGroups.getProperty("Comment");
	        	listItem.setComment(comment.toString());
	        	System.out.println("comment"+comment);
	        	Object parentId = realVideoSourceGroups.getProperty("ParentId");
	        	listItem.setParentId(parentId.toString());
	        	System.out.println("parentId"+parentId);
	        	list.add(listItem);
	        }
	        videoSourceGroupCatalogRes.setVideoSourceGroups(list);
	        
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("videoSourceGroupCatalogRes crash");
        	videoSourceGroupCatalogRes.setVideoSourceGroups(new ArrayList<VideoSourceGroup>());
		}
        return videoSourceGroupCatalogRes;
    }
    
    //查询视频源分组下的视频源列表 
    public GroupVideoSourceCatalogRes getGroupVideoSourceCatalogRes(GroupVideoSourceCatalogReq req) {
    	Log.e("SoapManager", "GroupVideoSourceCatalogReq");
    	GroupVideoSourceCatalogRes groupVideoSourceCatalogRes = new GroupVideoSourceCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "GroupVideoSourceCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/GroupVideoSourceCatalog");
        try{
        	//Result
	        Object result = object.getProperty("Result");
	        groupVideoSourceCatalogRes.setResult(result.toString());
	        System.out.println("result:"+result);
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getGroupVideoSourceCatalogRes(req);
	        }
	        //Page
	        SoapObject page = (SoapObject)object.getProperty("Page");
	        Object pageIndex = page.getProperty("PageIndex");
	        Object pageSize = page.getProperty("PageSize");
	        Object pageCount = page.getProperty("PageCount");
	        Object recordCount = page.getProperty("RecordCount");
	        Object totalRecordCount = page.getProperty("TotalRecordCount");
	        groupVideoSourceCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
	        		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
	        System.out.println("page:"+groupVideoSourceCatalogRes.getPage().toString());
	        //VideoSources
	        ArrayList<VideoSource> list = new ArrayList<VideoSource>();
	        SoapObject videoSources = (SoapObject)object.getProperty("VideoSources");
	        for(int i = 0 ;i<videoSources.getPropertyCount();i++){
	        	SoapObject realVideoSources = (SoapObject) videoSources.getProperty(i);
	        	VideoSource listItem = new VideoSource();
	        	Object id = realVideoSources.getProperty("Id");
	        	listItem.setId(id.toString());
	        	System.out.println("id:"+id);
	        	Object name = realVideoSources.getProperty("Name");
	        	listItem.setName(name.toString());
	        	System.out.println("name:"+name);
	        	Object pseudoCode = realVideoSources.getProperty("PseudoCode");
	        	listItem.setPseudoCode(Integer.valueOf(pseudoCode.toString()));
	        	System.out.println("pseudoCode:"+pseudoCode);
	        	Object videoInputChannelId = realVideoSources.getProperty("VideoInputChannelId");
	        	listItem.setVideoInputChannelId(videoInputChannelId.toString());
	        	System.out.println("videoInputChannelId:"+videoInputChannelId);
	        	Object cameraType = realVideoSources.getProperty("CameraType");
	        	listItem.setCameraType(cameraType.toString());
	        	System.out.println("cameraType:"+cameraType);
	        	Object ptz = realVideoSources.getProperty("PTZ");
	        	listItem.setpTZ(Boolean.valueOf(ptz.toString()));
	        	System.out.println("ptz:"+ptz);
	        	Object infrared = realVideoSources.getProperty("Infrared");
	        	listItem.setInfrared(Boolean.valueOf(infrared.toString()));
	        	System.out.println("infrared:"+infrared);
	        	Object online = realVideoSources.getProperty("Online");
	        	listItem.setOnline(Boolean.valueOf(online.toString()));
	        	System.out.println("online:"+online);
	        	list.add(listItem);
	        }
	        groupVideoSourceCatalogRes.setVideoSources(list);
	        
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("videoSourceGroupCatalogRes crash");
        	groupVideoSourceCatalogRes.setVideoSources(new ArrayList<VideoSource>());
		}
        return groupVideoSourceCatalogRes;
    }
    
    //查询设备下的视频输入通道目录
    public DeviceVideoInputChannelCatalogRes getDeviceVideoInputChannelCatalogRes(DeviceVideoInputChannelCatalogReq req) {
    	Log.e("SoapManager", "DeviceVideoInputChannelCatalogReq");
    	DeviceVideoInputChannelCatalogRes deviceVideoInputChannelCatalogRes = new DeviceVideoInputChannelCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "DeviceVideoInputChannelCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/DeviceVideoInputChannelCatalog");
        try{
        	//Result
	        Object result = object.getProperty("Result");
	        deviceVideoInputChannelCatalogRes.setResult(result.toString());
	        System.out.println("result:"+result);
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getDeviceVideoInputChannelCatalogRes(req);
	        }
	        //Page
	        SoapObject page = (SoapObject)object.getProperty("Page");
	        Object pageIndex = page.getProperty("PageIndex");
	        Object pageSize = page.getProperty("PageSize");
	        Object pageCount = page.getProperty("PageCount");
	        Object recordCount = page.getProperty("RecordCount");
	        Object totalRecordCount = page.getProperty("TotalRecordCount");
	        deviceVideoInputChannelCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
	        		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
	        System.out.println("page:"+deviceVideoInputChannelCatalogRes.getPage().toString());
	        //VideoInputChannels
	        ArrayList<VideoInputChannel> list = new ArrayList<VideoInputChannel>();
	        SoapObject VideoInputChannel = (SoapObject)object.getProperty("VideoInputChannels");
	        for(int i = 0 ;i<VideoInputChannel.getPropertyCount();i++){
	        	SoapObject realVideoInputChannel = (SoapObject) VideoInputChannel.getProperty(i);
	        	VideoInputChannel listItem = new VideoInputChannel();
	        	Object id = realVideoInputChannel.getProperty("Id");
	        	listItem.setId(id.toString());
	        	System.out.println("id:"+id);
	        	Object name = realVideoInputChannel.getProperty("Name");
	        	listItem.setName(name.toString());
	        	System.out.println("name:"+name);
	        	Object ptz = realVideoInputChannel.getProperty("PTZ");
	        	listItem.setpTZ(Boolean.valueOf(ptz.toString()));
	        	System.out.println("ptz:"+ptz);
	        	Object infrared = realVideoInputChannel.getProperty("Infrared");
	        	listItem.setInfrared(Boolean.valueOf(infrared.toString()));
	        	System.out.println("infrared:"+infrared);
	        	Object cameraType = realVideoInputChannel.getProperty("CameraType");
	        	listItem.setCameraType(cameraType.toString());
	        	System.out.println("cameraType:"+cameraType);
	        	Object terminal = realVideoInputChannel.getProperty("Terminal");
	        	listItem.setTerminal(Boolean.valueOf(terminal.toString()));
	        	System.out.println("terminal:"+terminal);
	        	Object networked = realVideoInputChannel.getProperty("Networked");
	        	listItem.setNetworked(Boolean.valueOf(networked.toString()));
	        	System.out.println("networked:"+networked);
	        	Object videoInterfaceType = realVideoInputChannel.getProperty("VideoInterfaceType");
	        	listItem.setVideoInterfaceType(videoInterfaceType.toString());
	        	System.out.println("videoInterfaceType:"+videoInterfaceType);
	        	list.add(listItem);
	        }
	        deviceVideoInputChannelCatalogRes.setVideoInputChannels(list);
	        
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("DeviceVideoInputChannelCatalogRes crash");
        	deviceVideoInputChannelCatalogRes.setVideoInputChannels(new ArrayList<VideoInputChannel>());
		}
        return deviceVideoInputChannelCatalogRes;
    }
    
    //查询设备下的报警输入通道目录
    public DeviceIOInputChannelCatalogRes getDeviceIOInputChannelCatalogRes(DeviceIOInputChannelCatalogReq req) {
    	Log.e("SoapManager", "DeviceIOInputChannelCatalogReq");
    	DeviceIOInputChannelCatalogRes deviceIOInputChannelCatalogRes = new DeviceIOInputChannelCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "DeviceIOInputChannelCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/DeviceIOInputChannelCatalog");
        try{
        	//Result
	        Object result = object.getProperty("Result");
	        deviceIOInputChannelCatalogRes.setResult(result.toString());
	        System.out.println("result:"+result);
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getDeviceIOInputChannelCatalogRes(req);
	        }
	        //Page
	        SoapObject page = (SoapObject)object.getProperty("Page");
	        Object pageIndex = page.getProperty("PageIndex");
	        Object pageSize = page.getProperty("PageSize");
	        Object pageCount = page.getProperty("PageCount");
	        Object recordCount = page.getProperty("RecordCount");
	        Object totalRecordCount = page.getProperty("TotalRecordCount");
	        deviceIOInputChannelCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
	        		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
	        System.out.println("page:"+deviceIOInputChannelCatalogRes.getPage().toString());
	        //VideoInputChannels
	        ArrayList<IOInputChannel> list = new ArrayList<IOInputChannel>();
	        SoapObject iOInputChannel = (SoapObject)object.getProperty("IOInputChannels");
	        for(int i = 0 ;i<iOInputChannel.getPropertyCount();i++){
	        	SoapObject realIOInputChannel = (SoapObject) iOInputChannel.getProperty(i);
	        	IOInputChannel listItem = new IOInputChannel();
	        	Object id = realIOInputChannel.getProperty("Id");
	        	listItem.setId(id.toString());
	        	System.out.println("id:"+id);
	        	Object name = realIOInputChannel.getProperty("Name");
	        	listItem.setName(name.toString());
	        	System.out.println("name:"+name);
	        	Object probeType = realIOInputChannel.getProperty("ProbeType");
	        	listItem.setProbeType(probeType.toString());
	        	System.out.println("probeType:"+probeType);
	        	Object triggeringType = realIOInputChannel.getProperty("TriggeringType");
	        	listItem.setTriggeringType(triggeringType.toString());
	        	System.out.println("triggeringType:"+triggeringType);
	        	Object defenceZoneId = realIOInputChannel.getProperty("DefenceZoneId");
	        	listItem.setDefenceZoneId(defenceZoneId.toString());
	        	System.out.println("defenceZoneId:"+defenceZoneId);
	        	list.add(listItem);
	        }
	        deviceIOInputChannelCatalogRes.setiOInputChannels(list);
	        
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("DeviceIOInputChannelCatalogRes crash");
        	deviceIOInputChannelCatalogRes.setiOInputChannels(new ArrayList<IOInputChannel>());
		}
        return deviceIOInputChannelCatalogRes;
    }
    
    //查询设备下的报警输出通道目录
    public DeviceIOOutputChannelCatalogRes getDeviceIOOutputChannelCatalogRes(DeviceIOOutputChannelCatalogReq req) {
    	Log.e("SoapManager", "DeviceIOOutputChannelCatalogReq");
    	DeviceIOOutputChannelCatalogRes deviceIOOutputChannelCatalogRes = new DeviceIOOutputChannelCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "DeviceIOOutputChannelCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/DeviceIOOutputChannelCatalog");
        try{
        	//Result
            Object result = object.getProperty("Result");
            deviceIOOutputChannelCatalogRes.setResult(result.toString());
            System.out.println("result:"+result);
            if(result.toString().equals("SessionExpired")){
            	userLoginRes = getUserLoginRes(userLoginReq);
            	req.setSession(userLoginRes.getSession());
            	return getDeviceIOOutputChannelCatalogRes(req);
            }
            //Page
            SoapObject page = (SoapObject)object.getProperty("Page");
            Object pageIndex = page.getProperty("PageIndex");
            Object pageSize = page.getProperty("PageSize");
            Object pageCount = page.getProperty("PageCount");
            Object recordCount = page.getProperty("RecordCount");
            Object totalRecordCount = page.getProperty("TotalRecordCount");
            deviceIOOutputChannelCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
            		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
            System.out.println("page:"+deviceIOOutputChannelCatalogRes.getPage().toString());
            //VideoInputChannels
            ArrayList<IOOutputChannel> list = new ArrayList<IOOutputChannel>();
            SoapObject iOOutputChannel = (SoapObject)object.getProperty("IOOutputChannels");
            for(int i = 0 ;i<iOOutputChannel.getPropertyCount();i++){
            	SoapObject realIOOutputChannel = (SoapObject) iOOutputChannel.getProperty(i);
            	IOOutputChannel listItem = new IOOutputChannel();
            	Object id = realIOOutputChannel.getProperty("Id");
            	listItem.setId(id.toString());
            	System.out.println("id:"+id);
            	Object name = realIOOutputChannel.getProperty("Name");
            	listItem.setName(name.toString());
            	System.out.println("name:"+name);
            	Object triggeringType = realIOOutputChannel.getProperty("TriggeringType");
            	listItem.setTriggeringType(triggeringType.toString());
            	System.out.println("triggeringType:"+triggeringType);
            	list.add(listItem);
            }
            deviceIOOutputChannelCatalogRes.setiOOutputChannels(list);
            
        }catch (Exception e) {
    		// TODO: handle exception
        	System.out.println("DeviceIOInputChannelCatalogRes crash");
        	deviceIOOutputChannelCatalogRes.setiOOutputChannels(new ArrayList<IOOutputChannel>());
    	}
        return deviceIOOutputChannelCatalogRes;
    }
    
    //查询设备下的存储媒介信息
    public DeviceStorageMediumCatalogRes getDeviceStorageMediumCatalogRes(DeviceStorageMediumCatalogReq req) {
    	Log.e("SoapManager", "DeviceStorageMediumCatalogReq");
    	DeviceStorageMediumCatalogRes deviceStorageMediumCatalogRes = new DeviceStorageMediumCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "DeviceStorageMediumCatalogReq");
        rpc.addProperty("Session", req.getSession());
        System.out.println(req.getId()+"---------");
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
//        rpc.addProperty("PageIndex", req.getPageIndex());
//        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/DeviceStorageMediumCatalog");
        try{
        	//Result
            Object result = object.getProperty("Result");
            deviceStorageMediumCatalogRes.setResult(result.toString());
            System.out.println("result:"+result);
            if(result.toString().equals("SessionExpired")){
            	userLoginRes = getUserLoginRes(userLoginReq);
            	req.setSession(userLoginRes.getSession());
            	return getDeviceStorageMediumCatalogRes(req);
            }
            //Page
            SoapObject page = (SoapObject)object.getProperty("Page");
            Object pageIndex = page.getProperty("PageIndex");
            Object pageSize = page.getProperty("PageSize");
            Object pageCount = page.getProperty("PageCount");
            Object recordCount = page.getProperty("RecordCount");
            Object totalRecordCount = page.getProperty("TotalRecordCount");
            deviceStorageMediumCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
            		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
            System.out.println("page:"+deviceStorageMediumCatalogRes.getPage().toString());
            //VideoInputChannels
            ArrayList<StorageMedium> list = new ArrayList<StorageMedium>();
            SoapObject storageMedium = (SoapObject)object.getProperty("StorageMediums");
            System.out.println(storageMedium.toString());
            for(int i = 0 ;i<storageMedium.getPropertyCount();i++){
            	SoapObject realStorageMedium = (SoapObject) storageMedium.getProperty(i);
            	StorageMedium listItem = new StorageMedium();
            	try{
	            	Object id = realStorageMedium.getProperty("Id");
	            	listItem.setId(id.toString());
	            	System.out.println("id:"+id);
            	}catch(Exception e){
            		
            	}
            	try{
	            	Object storagePort = realStorageMedium.getProperty("StoragePort");
	            	listItem.setStoragePort(Integer.valueOf(storagePort.toString()));
	            	System.out.println("storagePort:"+storagePort);
            	}catch(Exception e){
            		
            	}
//            	Object mediumType = realStorageMedium.getProperty("MediumType");
//            	listItem.setMediumType(mediumType.toString());
//            	System.out.println("mediumType:"+mediumType);
            	try{
	            	Object manufacturer = realStorageMedium.getProperty("Manufacturer");
	            	listItem.setManufacturer(manufacturer.toString());
	            	System.out.println("manufacturer:"+manufacturer);
            	}catch(Exception e){
            		
            	}
            	try{
	            	Object model = realStorageMedium.getProperty("Model");
	            	listItem.setModel(model.toString());
	            	System.out.println("model:"+model);
            	}catch(Exception e){
            		
            	}
            	try{
	            	Object capacity = realStorageMedium.getProperty("Capacity");
	            	listItem.setCapacity(Integer.valueOf(capacity.toString()));
	            	System.out.println("capacity:"+capacity);
            	}catch(Exception e){
            		
            	}
            	try{
	            	Object freespace = realStorageMedium.getProperty("Freespace");
	            	listItem.setFreespace(Integer.valueOf(freespace.toString()));
	            	System.out.println("freespace:"+freespace);
            	}catch(Exception e){
            		
            	}
            	try{
	            	Object storageType = realStorageMedium.getProperty("StorageType");
	            	listItem.setStorageType(storageType.toString());
	            	System.out.println("storageType:"+storageType);
            	}catch(Exception e){
            		
            	}
            	list.add(listItem);
            }
            deviceStorageMediumCatalogRes.setStorageMediums(list);
            
        }catch (Exception e) {
    		// TODO: handle exception
        	System.out.println("getDeviceStorageMediumCatalogRes crash");
        	deviceStorageMediumCatalogRes.setStorageMediums(new ArrayList<StorageMedium>());
    	}
        return deviceStorageMediumCatalogRes;
    }
    
    //查询设备下的网口信息 
    public DeviceNetworkInterfaceCatalogRes getDeviceNetworkInterfaceCatalogRes(DeviceNetworkInterfaceCatalogReq req) {
    	Log.e("SoapManager", "DeviceNetworkInterfaceCatalogReq");
    	DeviceNetworkInterfaceCatalogRes deviceNetworkInterfaceCatalogRes = new DeviceNetworkInterfaceCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "DeviceNetworkInterfaceCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        rpc.addProperty("Id", req.getId());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/DeviceNetworkInterfaceCatalog");
        try{
        	//Result
            Object result = object.getProperty("Result");
            deviceNetworkInterfaceCatalogRes.setResult(result.toString());
            System.out.println("result:"+result);
            if(result.toString().equals("SessionExpired")){
            	userLoginRes = getUserLoginRes(userLoginReq);
            	req.setSession(userLoginRes.getSession());
            	return getDeviceNetworkInterfaceCatalogRes(req);
            }
            //Page
            SoapObject page = (SoapObject)object.getProperty("Page");
            Object pageIndex = page.getProperty("PageIndex");
            Object pageSize = page.getProperty("PageSize");
            Object pageCount = page.getProperty("PageCount");
            Object recordCount = page.getProperty("RecordCount");
            Object totalRecordCount = page.getProperty("TotalRecordCount");
            deviceNetworkInterfaceCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
            		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
            System.out.println("page:"+deviceNetworkInterfaceCatalogRes.getPage().toString());
            //VideoInputChannels
            ArrayList<NetworkInterface> list = new ArrayList<NetworkInterface>();
            SoapObject networkInterface = (SoapObject)object.getProperty("NetworkInterfaces");
            for(int i = 0 ;i<networkInterface.getPropertyCount();i++){
            	SoapObject realNetworkInterface = (SoapObject) networkInterface.getProperty(i);
            	NetworkInterface listItem = new NetworkInterface();
            	Object id = realNetworkInterface.getProperty("Id");
            	listItem.setId(id.toString());
            	System.out.println("id:"+id);
            	Object interfacePort = realNetworkInterface.getProperty("InterfacePort");
            	listItem.setInterfacePort(Integer.valueOf(interfacePort.toString()));
            	System.out.println("interfacePort:"+interfacePort);
            	Object iPVersion = realNetworkInterface.getProperty("IPVersion");
            	listItem.setiPVersion(iPVersion.toString());
            	System.out.println("iPVersion:"+iPVersion);
            	Object addressingType = realNetworkInterface.getProperty("AddressingType");
            	listItem.setAddressingType(addressingType.toString());
            	System.out.println("addressingType:"+addressingType);
            	Object iPAddress = realNetworkInterface.getProperty("IPAddress");
            	listItem.setiPAddress(iPAddress.toString());
            	System.out.println("iPAddress:"+iPAddress);
            	Object physcialAddress = realNetworkInterface.getProperty("PhyscialAddress");
            	listItem.setPhyscialAddress(physcialAddress.toString());
            	System.out.println("physcialAddress:"+physcialAddress);
            	Object cableType = realNetworkInterface.getProperty("CableType");
            	listItem.setCableType(cableType.toString());
            	System.out.println("cableType:"+cableType);
            	Object speedDuplex = realNetworkInterface.getProperty("SpeedDuplex");
            	listItem.setSpeedDuplex(speedDuplex.toString());
            	System.out.println("speedDuplex:"+speedDuplex);
            	Object workMode = realNetworkInterface.getProperty("WorkMode");
            	listItem.setWorkMode(workMode.toString());
            	System.out.println("workMode:"+workMode);
            	Object wireless = realNetworkInterface.getProperty("Wireless");
            	listItem.setWireless(wireless.toString());
            	System.out.println("wireless:"+wireless);
            	Object mTU = realNetworkInterface.getProperty("MTU");
            	listItem.setmTU(Integer.valueOf(mTU.toString()));
            	System.out.println("mTU:"+mTU);
            	list.add(listItem);
            }
            deviceNetworkInterfaceCatalogRes.setNetworkInterfaces(list);
            
        }catch (Exception e) {
    		// TODO: handle exception
        	System.out.println("getDeviceNetworkInterfaceCatalogRes crash");
        	deviceNetworkInterfaceCatalogRes.setNetworkInterfaces(new ArrayList<NetworkInterface>());
    	}
        return deviceNetworkInterfaceCatalogRes;
    }
    
    //查询系统通知信息
    public NoticeCatalogRes getNoticeCatalogRes(NoticeCatalogReq req) {
    	Log.e("SoapManager", "NoticeCatalogReq");
    	NoticeCatalogRes noticeCatalogRes = new NoticeCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "NoticeCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/NoticeCatalog");
        try{
        	//Result
            Object result = object.getProperty("Result");
            noticeCatalogRes.setResult(result.toString());
            System.out.println("result:"+result);
            if(result.toString().equals("SessionExpired")){
            	userLoginRes = getUserLoginRes(userLoginReq);
            	req.setSession(userLoginRes.getSession());
            	return getNoticeCatalogRes(req);
            }
            //Page
            SoapObject page = (SoapObject)object.getProperty("Page");
            Object pageIndex = page.getProperty("PageIndex");
            Object pageSize = page.getProperty("PageSize");
            Object pageCount = page.getProperty("PageCount");
            Object recordCount = page.getProperty("RecordCount");
            Object totalRecordCount = page.getProperty("TotalRecordCount");
            noticeCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
            		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
            System.out.println("page:"+noticeCatalogRes.getPage().toString());
            //VideoInputChannels
            ArrayList<Notice> list = new ArrayList<Notice>();
            SoapObject notice = (SoapObject)object.getProperty("Notices");
            Log.e("notice", notice.toString());
            for(int i = 0 ;i<notice.getPropertyCount();i++){
            	SoapObject realNotice = (SoapObject) notice.getProperty(i);
            	Notice listItem = new Notice();
            	Object id = realNotice.getProperty("Id");
            	listItem.setId(id.toString());
            	System.out.println("id:"+id);
            	Object message = realNotice.getProperty("Message");
            	listItem.setMessage(message.toString());
            	System.out.println("message:"+message);
            	Object classification = realNotice.getProperty("Classification");
            	listItem.setClassification(classification.toString());
            	System.out.println("classification:"+classification);
            	Object time = realNotice.getProperty("Time");
            	listItem.setTime(time.toString());
            	System.out.println("time:"+time);
            	Object status = realNotice.getProperty("Status");
            	listItem.setStatus(status.toString());
            	System.out.println("status:"+status);
            	Object sender = realNotice.getProperty("Sender");
            	listItem.setSender(sender.toString());
            	System.out.println("sender:"+sender);
            	Object componentId = realNotice.getProperty("ComponentId");
            	listItem.setComponentId(componentId.toString());
            	System.out.println("componentId:"+componentId);
            	Object componentName = realNotice.getProperty("ComponentName");
            	listItem.setComponentName(componentName.toString());
            	System.out.println("ComponentName:"+componentName);
            	
//            	Object picture = realNotice.getProperty("PictureID");
//            	listItem.addPictureID(picture.toString());
//            	System.out.println("picture:"+picture);
            	SoapObject pictureIDList = (SoapObject)realNotice.getProperty("PictureID");
	        	//System.out.println("pictureIDList:"+pictureIDList);
	        	ArrayList<String> pictureIdList = new ArrayList<String>();
	        	for(int j = 0 ; j < pictureIDList.getPropertyCount() ; j++){
	        		Object pictureID = pictureIDList.getProperty(j); 
	        		//System.out.println("pictureID:"+pictureID);
	        		pictureIdList.add(pictureID.toString());
	        	}
	        	listItem.setPictureID(pictureIdList);
            	list.add(listItem);
            }
            noticeCatalogRes.setNotices(list);
            
        }catch (Exception e) {
    		// TODO: handle exception
        	System.out.println("NoticeCatalogRes crash");
        	noticeCatalogRes.setNotices(new ArrayList<Notice>());
    	}
        return noticeCatalogRes;
    }
    
    //获取图片
    public GetPictureRes getGetPictureRes(GetPictureReq req) {
    	Log.e("SoapManager", "GetPictureReq");
    	GetPictureRes getPictureRes = new GetPictureRes();
        SoapObject rpc = new SoapObject(sNameSpace, "GetPictureReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("PictureID", req.getPictureID());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/GetPicture");
        try{
	        Object result = object.getProperty("Result");
	        getPictureRes.setResult(result.toString());
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getGetPictureRes(req);
	        }
	        Object pictureID = object.getProperty("PictureID");
	        getPictureRes.setPictureID(pictureID.toString());
	        
	        Object picture = object.getProperty("Picture");
	        getPictureRes.setPicture(picture.toString());
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("SubscribeEventRes crash");
		}
        return getPictureRes;
    }
    
    //标记通知状态
    public FlaggedNoticeStatusRes getFlaggedNoticeStatusRes(FlaggedNoticeStatusReq req) {
    	Log.e("SoapManager", "FlaggedNoticeStatusReq");
    	FlaggedNoticeStatusRes flaggedNoticeStatusRes = new FlaggedNoticeStatusRes();
        SoapObject rpc = new SoapObject(sNameSpace, "SubscribeEventReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("Status", req.getStatus());
        rpc.addProperty("NoticeId", req.getNoticeId());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/FlaggedNoticeStatus");
        try{
	        Object result = object.getProperty("Result");
	        flaggedNoticeStatusRes.setResult(result.toString());
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getFlaggedNoticeStatusRes(req);
	        }
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("SubscribeEventRes crash");
		}
        return flaggedNoticeStatusRes;
    }
    
    //查询事件订阅信息列表
    public EventSubscriptionCatalogRes getEventSubscriptionCatalogRes(EventSubscriptionCatalogReq req) {
    	Log.e("SoapManager", "EventSubscriptionCatalogReq");
    	System.out.println(req.toString());
    	EventSubscriptionCatalogRes eventSubscriptionCatalogRes = new EventSubscriptionCatalogRes();
        SoapObject rpc = new SoapObject(sNameSpace, "EventSubscriptionCatalogReq");
        rpc.addProperty("Session", req.getSession());
        rpc.addProperty("InternetDeviceId", req.getInternetDeviceId());
        if(req.getComponentId() != null)
        	rpc.addProperty("ComponentId", req.getComponentId());
        rpc.addProperty("EventType", req.getEventType());
        rpc.addProperty("PageIndex", req.getPageIndex());
        rpc.addProperty("PageSize", req.getPageSize());
        SoapObject object = initEnvelopAndTransport(rpc,"http://www.howell.net.cn/InternetService/MCU/EventSubscriptionCatalog");
        try{
	        Object result = object.getProperty("Result");
	        System.out.println("EventSubscriptionCatalogRes:"+result);
	        eventSubscriptionCatalogRes.setResult(result.toString());
	        if(result.toString().equals("SessionExpired")){
	        	userLoginRes = getUserLoginRes(userLoginReq);
	        	req.setSession(userLoginRes.getSession());
	        	return getEventSubscriptionCatalogRes(req);
	        }
	        
	        //Page
            SoapObject page = (SoapObject)object.getProperty("Page");
            Object pageIndex = page.getProperty("PageIndex");
            Object pageSize = page.getProperty("PageSize");
            Object pageCount = page.getProperty("PageCount");
            Object recordCount = page.getProperty("RecordCount");
            Object totalRecordCount = page.getProperty("TotalRecordCount");
            eventSubscriptionCatalogRes.setPage(new Page(Integer.valueOf(pageIndex.toString()),Integer.valueOf(pageSize.toString()),Integer.valueOf(pageCount.toString())
            		,Integer.valueOf(recordCount.toString()),Integer.valueOf(totalRecordCount.toString())));
//            System.out.println("page:"+eventSubscriptionCatalogRes.getPage().toString());
            
            //VideoInputChannels
            ArrayList<EventSubscription> list = new ArrayList<EventSubscription>();
            SoapObject eventSubscription = (SoapObject)object.getProperty("EventSubscriptions");
            for(int i = 0 ;i<eventSubscription.getPropertyCount();i++){
            	SoapObject realNotice = (SoapObject) eventSubscription.getProperty(i);
            	EventSubscription listItem = new EventSubscription();
            	Object internetDeviceId = realNotice.getProperty("InternetDeviceId");
            	listItem.setInternetDeviceId(internetDeviceId.toString());
//            	System.out.println("internetDeviceId:"+internetDeviceId);
            	Object componentId = realNotice.getProperty("ComponentId");
            	listItem.setComponentId(componentId.toString());
//            	System.out.println("componentId:"+componentId);
            	Object eventType = realNotice.getProperty("EventType");
            	listItem.setEventType(eventType.toString());
//            	System.out.println("eventType:"+eventType);
            	Object triggerState = realNotice.getProperty("TriggerState");
            	listItem.setTriggerState(triggerState.toString());
//            	System.out.println("triggerState:"+triggerState);
            	Object notificationMethod = realNotice.getProperty("NotificationMethod");
            	listItem.setNotificationMethod(notificationMethod.toString());
//            	System.out.println("notificationMethod:"+notificationMethod);
            	Object lastModifiedTime = realNotice.getProperty("LastModifiedTime");
            	listItem.setLastModifiedTime(lastModifiedTime.toString());
//            	System.out.println("lastModifiedTime:"+lastModifiedTime);
            	list.add(listItem);
            	System.out.println(listItem.toString());
            }
            eventSubscriptionCatalogRes.setEventSubscriptions(list);
        }catch (Exception e) {
			// TODO: handle exception
        	System.out.println("EventSubscriptionCatalogRes crash");
        	eventSubscriptionCatalogRes.setEventSubscriptions(new ArrayList<EventSubscription>());
		}
        return eventSubscriptionCatalogRes;
    }
}


