package com.howell.ksoap;
/**
 * @author 霍之昊 
 *
 * 类说明
 */
public class SystemHealthStatistics {
	private String id;
    private double percentage;
    private SystemFaultStatistics faults;
    private SystemWarningStatistics warnings;
	public SystemHealthStatistics(String id, double percentage,
			SystemFaultStatistics faults, SystemWarningStatistics warnings) {
		super();
		this.id = id;
		this.percentage = percentage;
		this.faults = faults;
		this.warnings = warnings;
	}
	public SystemHealthStatistics() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public SystemFaultStatistics getFaults() {
		return faults;
	}
	public void setFaults(SystemFaultStatistics faults) {
		this.faults = faults;
	}
	public SystemWarningStatistics getWarnings() {
		return warnings;
	}
	public void setWarnings(SystemWarningStatistics warnings) {
		this.warnings = warnings;
	}
    
    
}
