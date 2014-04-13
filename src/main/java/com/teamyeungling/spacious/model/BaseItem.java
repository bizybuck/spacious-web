package com.teamyeungling.spacious.model;

public class BaseItem {
	
	private String number;
	private String desc;
	private String status;
	
	public BaseItem() {
		this.status = "Active";
	}
	
	public BaseItem(String number, String desc, String status) {
		this.number = number;
		this.desc = desc;
		this.status = status;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setstatus(String status) {
		this.status = status;
	}
	
	public String toString() {
		return "ItemNumber: " + this.getNumber()
				+ "\nItem Description:  " + this.getDesc()
				+ "\nItem Status:  " + this.getStatus();
	}
}
