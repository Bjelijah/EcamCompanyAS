package com.howell.utils;

public class DetectIfHasFrameUtils {
	
	private int currentFrameCount;
	private int lastMonmentFrameCount;
	

    public DetectIfHasFrameUtils() {
		super();
		this.currentFrameCount = 0;
		this.lastMonmentFrameCount = 0;
	}

	public synchronized int getCurrentFrames(){
    	return currentFrameCount;
    }
    
    public synchronized void addFrames(){
    	currentFrameCount += 1;
    //	System.out.println("currentFrameCount:"+currentFrameCount);
    }

	public synchronized int getLastMonmentFrameCount() {
		return lastMonmentFrameCount;
	}

	public synchronized void setLastMonmentFrameCount() {
		this.lastMonmentFrameCount = currentFrameCount;
	} 
    
	public boolean hasFrame(){
		return lastMonmentFrameCount == currentFrameCount ?  false : true;
	}
    
	public synchronized void clearCount(){
		currentFrameCount = 0;
	}
}
