package com.howell.ksoap;

import java.util.ArrayList;

public class NATServerRes {
	private String result;
	ArrayList<STUNServer> sTUNServers;
	ArrayList<TURNServer> tURNServers;
	public NATServerRes(String result, ArrayList<STUNServer> sTUNServers,
			ArrayList<TURNServer> tURNServers) {
		super();
		this.result = result;
		this.sTUNServers = sTUNServers;
		this.tURNServers = tURNServers;
	}
	public NATServerRes() {
		super();
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public ArrayList<STUNServer> getsTUNServers() {
		return sTUNServers;
	}
	public void setsTUNServers(ArrayList<STUNServer> sTUNServers) {
		this.sTUNServers = sTUNServers;
	}
	public ArrayList<TURNServer> gettURNServers() {
		return tURNServers;
	}
	public void settURNServers(ArrayList<TURNServer> tURNServers) {
		this.tURNServers = tURNServers;
	}

}
