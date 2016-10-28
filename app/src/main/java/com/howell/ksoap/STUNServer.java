package com.howell.ksoap;

public class STUNServer {
	private String iPv4Address;
	private String iPv6Address;
	private int port;
	public String getiPv4Address() {
		return iPv4Address;
	}
	public void setiPv4Address(String iPv4Address) {
		this.iPv4Address = iPv4Address;
	}
	public String getiPv6Address() {
		return iPv6Address;
	}
	public void setiPv6Address(String iPv6Address) {
		this.iPv6Address = iPv6Address;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "STUNServer [iPv4Address=" + iPv4Address + ", iPv6Address="
				+ iPv6Address + ", port=" + port + "]";
	}
	
	

}
