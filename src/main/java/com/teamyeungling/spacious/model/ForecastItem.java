package com.teamyeungling.spacious.model;

import java.util.ArrayList;

public class ForecastItem extends BaseItem {

	private ArrayList<ForecastPeriod> forecastPeriods;
	
	public ForecastItem() {
		// TODO Auto-generated constructor stub
	}

	public ForecastItem(String number, String desc, String status) {
		super(number, desc, status);
		// TODO Auto-generated constructor stub
	}

	public ArrayList<ForecastPeriod> getForecastPeriods() {
		if(forecastPeriods == null) {
			forecastPeriods = new ArrayList<ForecastPeriod>();
		}
		
		return forecastPeriods;
	}

	public void setForecastPeriods(ArrayList<ForecastPeriod> forecastPeriods) {
		this.forecastPeriods = forecastPeriods;
	}
}
