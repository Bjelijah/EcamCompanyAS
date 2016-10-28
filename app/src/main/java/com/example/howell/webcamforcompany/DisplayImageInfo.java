package com.example.howell.webcamforcompany;

import com.example.howell.webcamforcompany.NoticeCatalogActivity.GetNoticeCatalogAdapter.ViewHolder;

import java.util.ArrayList;

/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class DisplayImageInfo {
	private int index;
	private ArrayList<String> pictureID;
	private int pictureWidth,pictureHeight;
	private ViewHolder holder;
	
	public DisplayImageInfo(int index, ArrayList<String> pictureID,int pictureWidth,int pictureHeight,ViewHolder holder) {
		super();
		this.index = index;
		this.pictureID = pictureID;
		this.pictureWidth = pictureWidth;
		this.pictureHeight = pictureHeight;
		this.holder = holder;
	}

	public ArrayList<String> getPictureID() {
		return pictureID;
	}

	public void setPictureID(ArrayList<String> pictureID) {
		this.pictureID = pictureID;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getPictureWidth() {
		return pictureWidth;
	}

	public void setPictureWidth(int pictureWidth) {
		this.pictureWidth = pictureWidth;
	}

	public int getPictureHeight() {
		return pictureHeight;
	}

	public void setPictureHeight(int pictureHeight) {
		this.pictureHeight = pictureHeight;
	}

	public ViewHolder getHolder() {
		return holder;
	}

	public void setHolder(ViewHolder holder) {
		this.holder = holder;
	}
	
	
}
