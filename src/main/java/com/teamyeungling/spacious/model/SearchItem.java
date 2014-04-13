package com.teamyeungling.spacious.model;

import java.util.Date;

public class SearchItem extends BaseItem {

	private Date startDate, endDate;
	private int forecastRange;
	
	public SearchItem() {
	}
	
	public SearchItem(String number, String description, String status,
			Date startDate, Date endDate, int forecastRange) {
		
		super(number, description, status);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getForecastRange() {
		return forecastRange;
	}

	public void setForecastRange(int forecastRange) {
		this.forecastRange = forecastRange;
	}

	public String toString() {
		return super.toString()
				+ "\nSearch start date:  " + this.getStartDate().toString()
				+ "\n Search end date:  " + this.getEndDate().toString();
	}
}
