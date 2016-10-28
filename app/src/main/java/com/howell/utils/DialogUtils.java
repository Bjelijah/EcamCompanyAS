package com.howell.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;

import com.howell.ecamcompany.R;

public class DialogUtils {
	public static Dialog postWaitDialog(Context context){
		final Dialog lDialog = new Dialog(context,android.R.style.Theme_Translucent_NoTitleBar);
		lDialog.setContentView(R.layout.wait_dialog);
		return lDialog;
	}
	
	public static Dialog postDialogWithPositiveButton(Context context,String title ,String message,int icon,String btnName,OnClickListener listenter){
		Dialog alertDialog = new AlertDialog.Builder(context).   
	            setTitle(title).   
	            setMessage(message).   
	            setIcon(icon).   
	            setPositiveButton(btnName, listenter).   
	    create();   
		return alertDialog;
	}
	
}
