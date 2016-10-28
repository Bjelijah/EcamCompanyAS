package com.howell.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;

public class UserInformation {
 
    /**
     * Role:获取当前设置的电话号码
     * <BR>Date:2012-3-12
     * <BR>@author CODYY)peijiangping
     */
    public static String getNativePhoneNumber(Context context) {
    	TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String NativePhoneNumber=null;
        NativePhoneNumber=telephonyManager.getLine1Number();
        return NativePhoneNumber;
    }
 
    /**
     * Role:Telecom service providers获取手机服务商信息 <BR>
     * 需要加入权限<uses-permission
     * android:name="android.permission.READ_PHONE_STATE"/> <BR>
     * Date:2012-3-12 <BR>
     *
     * @author CODYY)peijiangping
     */
    public static String getProvidersName(Context context) {
    	TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String ProvidersName = null;
        // 返回唯一的用户ID;就是这张卡的编号神马的
        String IMSI = telephonyManager.getSubscriberId();
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        System.out.println("IMSI:"+IMSI);
        if(IMSI != null){
	        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
	            ProvidersName = "中国移动";
	        } else if (IMSI.startsWith("46001")) {
	            ProvidersName = "中国联通";
	        } else if (IMSI.startsWith("46003")) {
	            ProvidersName = "中国电信";
	        } else{
	        	ProvidersName = "中国网通";
	        }
        }
        return ProvidersName;
    }
    
    //获取手机IMEI码
    public static String getAndroidPhoneIMEI(Context context){
    	TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    	return telephonyManager.getDeviceId();
    }
    
    //获取手机网络类型
    public static int getNetType(Context context){
    	int NO_NETWORK_CONNECT = -1;
    	int NETWORK_WIFI = 1;
    	int NETWORK_2G = 2;
    	int NETWORK_3G = 3;
    	int UNKONWN = 0;
    	ConnectivityManager connectMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo info = connectMgr.getActiveNetworkInfo();
    	if(info == null){
    		return NO_NETWORK_CONNECT;
    	}else {
    		if(info.getType() ==  ConnectivityManager.TYPE_MOBILE){
        		if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA 
        				|| info.getSubtype() ==  TelephonyManager.NETWORK_TYPE_GPRS
        				|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE){
        			return NETWORK_2G;
        		}else if(info.getSubtype() == TelephonyManager.NETWORK_TYPE_UMTS
        				|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_HSDPA
        				|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_0
        				|| info.getSubtype() == TelephonyManager.NETWORK_TYPE_EVDO_A){
        			return NETWORK_3G;
        		}else{
        			return UNKONWN;
        		}
        	}else if(info.getType() == ConnectivityManager.TYPE_WIFI){
        		return NETWORK_WIFI;
        	}
    	}
    	return UNKONWN;
    	
    }
    
    //获取设备型号
    public static String getDeviceModel(){
    	return Build.MODEL;
    }
    
    //获取设备版本号
    public static String getDeviceVersion(){
    	return Build.VERSION.RELEASE;
    }
    
    public static String checkMake(){
    	String sMake = Build.MANUFACTURER.toLowerCase();
    	return sMake == null ? null : sMake;
    } 
}
