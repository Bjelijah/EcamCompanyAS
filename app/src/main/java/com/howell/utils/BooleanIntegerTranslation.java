package com.howell.utils;

import com.example.howell.webcamforcompany.Const;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class BooleanIntegerTranslation implements Const{
	public static int b2i(boolean b){
		return b ? TRUE : FALSE;
	}
	
	public static boolean i2b(int i){
		return i == 1 ? true : false;
	}
}
