package com.teamyeungling.spacious.model;

public class ForecastSearchItem extends BaseItem {

	private int range;
	
	public ForecastSearchItem() {
		// TODO Auto-generated constructor stub
	}
	
	public ForecastSearchItem(String number, String description, String status, int range) {
		super(number, description, status);
		this.setRange(range);
	}

	public int getRange() {
		return range;
	}

	public void setRange(int forecastRange) {
		this.range = forecastRange;
	}

}
