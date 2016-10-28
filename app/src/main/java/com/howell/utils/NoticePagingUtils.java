package com.howell.utils;

import com.howell.ksoap.NoticeCatalogReq;
import com.howell.ksoap.NoticeCatalogRes;
import com.howell.ksoap.SoapManager;


/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class NoticePagingUtils {
	private int curPageNo;		//当前页数
	private int totalPageNo;	//总共页数
	
	private int pageSize = 20;	//每页获取个数
	
	private SoapManager mSoapManager ;
	
	public NoticePagingUtils() {
		// TODO Auto-generated constructor stub
		curPageNo = 1;
		totalPageNo = 1;
		mSoapManager = SoapManager.getInstance();
		
	}
	
	public void clearResource(){
		curPageNo = 1;
		totalPageNo = 1;
	}
	
	public NoticeCatalogRes getNoticeCatalog(){
		if(curPageNo > totalPageNo){
			return null;
		}
		NoticeCatalogRes res = mSoapManager.getNoticeCatalogRes(new NoticeCatalogReq(mSoapManager.getUserLoginRes().getSession(),curPageNo,pageSize));
		if(curPageNo == 1 && res != null && res.getResult() != null && res.getResult().equals("OK")){
			totalPageNo = res.getPage().getPageCount();
			System.out.println("totalPageNo:"+totalPageNo);
		}
		curPageNo++;
		return res;
	}
	
	
}
