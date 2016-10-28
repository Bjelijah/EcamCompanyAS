package com.howell.ksoap;

public class TURNServer {
	private String iPv4Address;
	private String iPv6Address;
	private int port;
	private String username;
	private String password;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "TURNServer [iPv4Address=" + iPv4Address + ", iPv6Address="
				+ iPv6Address + ", port=" + port + ", username=" + username
				+ ", password=" + password + "]";
	}

}
