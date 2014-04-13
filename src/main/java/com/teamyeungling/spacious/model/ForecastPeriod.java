package com.teamyeungling.spacious.model;

import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;

public class ForecastPeriod {
	
	private static final int MOVING_AVERAGE = 1;
	private static final int SIMPLE_EXP_SMOOTHING = 2;
	private static final int HOLTS_MODEL = 3;
	private static final int WINTERS_MODEL = 15;
	
	private String monthYear;
	private String actualDemand;
	private String deseasonalizedDemand;
	private String level;
	private String trend;
	private String seasonalFactor;
	private String forecast;
	private String error;
	private String absoluteError;
	private String squaredError;
	private String meanAbsoluteDeviation;
	private String percentageError;
	private String meanAbsolutePercentageError;
	private String trackingSignal;
	private String bias;
	
	private static SimpleDateFormat monthYearDateFormat;
	
	public ForecastPeriod() {
		
	}
	
	public ForecastPeriod(String[] forecastMetrics) {
		
		
		this.setMonthYear(forecastMetrics[0]);
		
		if(!StringUtils.isBlank(forecastMetrics[1])) {
			this.setActualDemand(forecastMetrics[1]);
		}
		
		switch(forecastMetrics.length) {
			case MOVING_AVERAGE:
				break;
				
			case SIMPLE_EXP_SMOOTHING:
				break;
			
			case HOLTS_MODEL:
				break;
				
			case WINTERS_MODEL:
				
				this.setDeseasonalizedDemand(forecastMetrics[2]);
				this.setLevel(forecastMetrics[3]);
				this.setTrend(forecastMetrics[4]);
				this.setSeasonalFactor(forecastMetrics[5]);
				this.setForecast(forecastMetrics[6]);
				this.setError(forecastMetrics[7]);
				this.setAbsoluteError(forecastMetrics[8]);
				this.setSquaredError(forecastMetrics[9]);
				this.setMeanAbsoluteDeviation(forecastMetrics[10]);
				this.setPercentageError(forecastMetrics[11]);
				this.setMeanAbsolutePercentageError(forecastMetrics[12]);
				this.setTrackingSignal(forecastMetrics[13]);
				this.setBias(forecastMetrics[14]);
				
				break;
				
			default:
				break;
		}
	}
	
	public String getMonthYear() {
		return monthYear;
	}
	
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public String getActualDemand() {
		return actualDemand;
	}

	public void setActualDemand(String actualDemand) {
		this.actualDemand = actualDemand;
	}

	public String getDeseasonalizedDemand() {
		return deseasonalizedDemand;
	}

	public void setDeseasonalizedDemand(String deseasonalizedDemand) {
		this.deseasonalizedDemand = deseasonalizedDemand;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTrend() {
		return trend;
	}

	public void setTrend(String trend) {
		this.trend = trend;
	}

	public String getSeasonalFactor() {
		return seasonalFactor;
	}

	public void setSeasonalFactor(String seasonalFactor) {
		this.seasonalFactor = seasonalFactor;
	}

	public String getForecast() {
		return forecast;
	}

	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getAbsoluteError() {
		return absoluteError;
	}

	public void setAbsoluteError(String absoluteError) {
		this.absoluteError = absoluteError;
	}

	public String getSquaredError() {
		return squaredError;
	}

	public void setSquaredError(String squaredError) {
		this.squaredError = squaredError;
	}

	public String getMeanAbsoluteDeviation() {
		return meanAbsoluteDeviation;
	}

	public void setMeanAbsoluteDeviation(String meanAbsoluteDeviation) {
		this.meanAbsoluteDeviation = meanAbsoluteDeviation;
	}

	public String getPercentageError() {
		return percentageError;
	}

	public void setPercentageError(String percentageError) {
		this.percentageError = percentageError;
	}

	public String getMeanAbsolutePercentageError() {
		return meanAbsolutePercentageError;
	}

	public void setMeanAbsolutePercentageError(String meanAbsolutePercentageError) {
		this.meanAbsolutePercentageError = meanAbsolutePercentageError;
	}

	public String getTrackingSignal() {
		return trackingSignal;
	}

	public void setTrackingSignal(String trackingSignal) {
		this.trackingSignal = trackingSignal;
	}

	public String getBias() {
		return bias;
	}

	public void setBias(String bias) {
		this.bias = bias;
	}
	
	public SimpleDateFormat getMonthYearDateFormat() {
		if(this.monthYearDateFormat == null) {
			this.monthYearDateFormat = new SimpleDateFormat("MMM-yyyy");
		}
		
		return this.monthYearDateFormat;
	}
}
