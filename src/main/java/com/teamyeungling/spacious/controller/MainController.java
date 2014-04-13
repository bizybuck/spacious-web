package com.teamyeungling.spacious.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.TabChangeEvent;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.teamyeungling.spacious.model.EntryItem;
import com.teamyeungling.spacious.model.ForecastItem;
import com.teamyeungling.spacious.model.ForecastPeriod;
import com.teamyeungling.spacious.model.ForecastSearchItem;
import com.teamyeungling.spacious.model.SearchItem;

@ManagedBean
@SessionScoped
public class MainController implements Serializable {
	
	private static final String spaciousMockDataRoot =
			"C:\\Users\\Aaron\\collegeClasses\\EIN 6905\\Forecasting\\SPACIOUS\\spacious_mock_data\\";
	
	private static final String itemListFile = spaciousMockDataRoot + "item_numbers.csv";
	private static final String viewInventoryDataFile = spaciousMockDataRoot + "item_historical_data.csv";
	
	private static final String movingAverageDataFile = "";
	private static final String exponentialSmoothingDataFile = "";
	private static final String holtsModelDataFile = "";
	private static final String wintersModelDataFile = spaciousMockDataRoot + "forecast_data.csv";
	
	private static final long serialVersionUID = 8484431810671303335L;
	
	private boolean CSVEntrySelected;
	
	private boolean renderSearchResultsTable = false;
	
	private boolean renderForecastResultsTable = false;
	
	private boolean renderForecastItemDescription = true;
	private boolean renderDeseasonalizedDemand = false;
	private boolean renderLevel = false;
	private boolean renderTrend = false;
	private boolean renderSeasonalFactor = false;
	private boolean renderForecastError = false;
	private boolean renderPercentageError = true;
	private boolean renderMAD = false;
	private boolean renderMAPE = false;
	private boolean renderTrackingSignal = false;
	private boolean renderBias = false;
	
	private boolean isSearchItemNumberEmpty = true;
	private boolean isSearchItemDescEmpty = true;
	
	private boolean isForecastItemNumberEmpty = true;
	private boolean isForecastItemDescEmpty = true;
	/*private EntryItemTO entryItem;
	private SearchItemTO searchItem;*/
	
	private EntryItem entryItem;
	private SearchItem searchItem;
	private ForecastSearchItem forecastSearchItem;
	
	private BiMap<String, String> itemNumberDescHash;
	private String todaysDate;
	
	//private List<EntryItemTO> viewInventoryData;
	private List<EntryItem> viewInventoryData;
	private ForecastItem viewForecastItem;

	public boolean isCSVEntrySelected() {
		return CSVEntrySelected;
	}
	
	public void setCSVEntrySelected(boolean newCSVEntrySelected) {
		CSVEntrySelected = newCSVEntrySelected;
	}
	
	public void changeCSVEntrySelected() {
		this.setCSVEntrySelected(!CSVEntrySelected);
	}
	
	public void changeRenderForecastItemDescription() {
		this.setRenderForecastItemDescription(!renderForecastItemDescription);
	}

	public void changeRenderDeseasonalizedDemand() {
		this.setRenderDeseasonalizedDemand(!renderDeseasonalizedDemand);
	}
	
	public void changeRenderLevel() {
		this.setRenderLevel(!renderLevel);
	}
	
	public void changeRenderTrend() {
		this.setRenderTrend(!renderTrend);
	}

	public void changeSeasonalFactor() {
		this.setRenderSeasonalFactor(!renderSeasonalFactor);
	}
	
	public void changeRenderForecastError() {
		this.setRenderForecastError(!renderForecastError);
	}
	
	public void changeRenderPercentageError() {
		this.setRenderPercentageError(!renderPercentageError);
	}
	
	public void changeRenderMAD() {
		this.setRenderMAD(!renderMAD);
	}
	
	public void changeRenderMAPE() {
		this.setRenderMAPE(!renderMAPE);
	}
	
	public void changeRenderTrackingSignal() {
		this.setRenderTrackingSignal(!renderTrackingSignal);
	}
	
	public void changeRenderBias() {
		this.setRenderBias(!renderBias);
	}
	
	/*public boolean isActive() {
		if(this.getEntryItem() == null) {
			this.setEntryItem(new EntryItemTO());
		}
		
		return this.getEntryItem().getStatus().equalsIgnoreCase("active");
	}*/
	
	public boolean isActive() {
		if(this.getEntryItem() == null) {
			this.setEntryItem(new EntryItem());
		}
		
		return this.getEntryItem().getStatus().equalsIgnoreCase("active");
	}

	/*public EntryItemTO getSingleEntryItem() {
		if(this.singleEntryItem == null) {
			this.singleEntryItem = new EntryItemTO();
		}
		
		return singleEntryItem;
	}*/
	
	public EntryItem getEntryItem() {
		if(this.entryItem == null) {
			this.entryItem = new EntryItem();
		}
		
		return entryItem;
	}

	/*public void setEntryItem(EntryItemTO entryItemTO) {
		this.entryItem = entryItemTO;
	}*/
	
	public void setEntryItem(EntryItem entryItem) {
		this.entryItem = entryItem;
	}
	
	/*public void saveEntryItem() {
		this.setEntryItem(new EntryItemTO());
	}*/
	
	public void saveEntryItem() {
		this.setEntryItem(new EntryItem());
	}

	/*public SearchItemTO getSearchItem() {
		if(this.searchItem == null) {
			this.searchItem = new SearchItemTO();
		}
		
		return searchItem;
	}*/
	
	public SearchItem getSearchItem() {
		if(this.searchItem == null) {
			this.setSearchItem(new SearchItem());
		}
		
		return searchItem;
	}

	/*public void setSearchItem(SearchItemTO searchItem) {
		this.searchItem = searchItem;
	}*/
	
	public void setSearchItem(SearchItem searchItem) {
		this.searchItem = searchItem;
	}
	
	public ForecastSearchItem getForecastSearchItem() {
		if(this.forecastSearchItem == null) {
			this.setForecastSearchItem(new ForecastSearchItem());
		}
		return forecastSearchItem;
	}

	public void setForecastSearchItem(ForecastSearchItem forecastSearchItem) {
		this.forecastSearchItem = forecastSearchItem;
	}

	public List<String> completeSearchItemNumber(String query) {
		ArrayList<String> results = new ArrayList<String>();
		
		Set<String> itemNumberSet = this.getItemNumberDescHash().keySet();
		
		for(String itemNumber : itemNumberSet) {
			if(itemNumber.contains(query)) {
				results.add(itemNumber);
			}
		}
		
		return results;
	}
	
	public List<String> completeForecastItemDesc(String query) {
		return new ArrayList<String>(this.getItemNumberDescHash().values());
	}
	
	/*public void changeSearchItemNumber() {
		SearchItemTO searchItem = this.getSearchItem();		
		searchItem.setDesc(this.getItemNumberDescHash().get(searchItem.getNumber()));
	}*/
	
	public void changeSearchItemNumber() {
		SearchItem searchItem = this.getSearchItem();
		searchItem.setDesc(this.getItemNumberDescHash().get(searchItem.getNumber()));
	}
	
	public void changeForecastSearchItemNumber() {
		ForecastSearchItem forecastSearchItem = this.getForecastSearchItem();
		forecastSearchItem.setDesc(this.getItemNumberDescHash().get(forecastSearchItem.getNumber()));
	}
	
	public void changeForecastItemDesc() {
		SearchItem searchItem = this.getSearchItem();
		searchItem.setNumber(this.getItemNumberDescHash().inverse().get(searchItem.getDesc()));
	}
	
	public void onTabChange(TabChangeEvent event) {
		this.setEntryItem(new EntryItem());
		this.setSearchItem(new SearchItem());
	}
	
	/*public void submitSearchItem() {
		this.setSearchItem(new SearchItemTO());
		this.setRenderSearchResultsPanel(true);
	}*/
	
	public void submitSearchItem() {
		
		this.setSearchItem(new SearchItem());
		this.setViewInventoryData(new ArrayList<EntryItem>());
		
		List<String> historicalInventoryLines = null;
		
		try {
			historicalInventoryLines = FileUtils.readLines(FileUtils.getFile(viewInventoryDataFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(historicalInventoryLines != null && !historicalInventoryLines.isEmpty()) {
			String[] historicalInventoryMetrics = null;
			
			for(String historicalInventoryLine : historicalInventoryLines) {
				historicalInventoryMetrics = historicalInventoryLine.split(",");
				
				if(historicalInventoryMetrics != null && !ArrayUtils.isEmpty(historicalInventoryMetrics)) {
					this.getViewInventoryData().add(new EntryItem(historicalInventoryMetrics));
				}
			}
		}
		
		
		this.setRenderSearchResultsTable(true);
	}
	
	public void submitForecastItem() {
		this.setViewForecastItem(new ForecastItem());
		
		List<String> forecastLines = null;
		ForecastItem forecastItem = null;
		
		try {
			forecastLines = FileUtils.readLines(FileUtils.getFile(wintersModelDataFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(forecastLines != null && !forecastLines.isEmpty()) {
			String[] forecastMetrics = null;
			
			ForecastSearchItem fsi = this.getForecastSearchItem();
			forecastItem = new ForecastItem(fsi.getNumber(), fsi.getDesc(), fsi.getStatus());
			
			for(String forecastLine : forecastLines) {
				forecastMetrics = forecastLine.split(",");
				
				if(forecastMetrics != null && !ArrayUtils.isEmpty(forecastMetrics)) {
					forecastItem.getForecastPeriods().add(new ForecastPeriod(forecastMetrics));
				}
			}
		}
		
		this.setViewForecastItem(forecastItem);
		
		this.setSearchItem(new SearchItem());
		this.setRenderForecastResultsTable(true);
	}

	public BiMap<String, String> getItemNumberDescHash() {
		
		if(this.itemNumberDescHash == null) {
			this.setItemNumberDescHash(HashBiMap.create());
		}
		
		List<String> itemNumbers = null;
		
		try {
			itemNumbers = FileUtils.readLines(FileUtils.getFile(itemListFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(itemNumbers != null && !itemNumbers.isEmpty()) {
			String[] itemNumber = null;
			
			for(String itemNumberLine : itemNumbers) {
				itemNumber = itemNumberLine.split(",");
				
				if(itemNumber != null && !ArrayUtils.isEmpty(itemNumber)) {
					this.itemNumberDescHash.put(itemNumber[0], itemNumber[1]);
				}
			}
		}

		return itemNumberDescHash;
	}

	public void setItemNumberDescHash(BiMap itemNumberDescHash) {
		this.itemNumberDescHash = itemNumberDescHash;
	}

	public boolean isItemNumberRequired() {
		if(null == searchItem.getEndDate()) {
			return true;
		}

		return false;
	}

	public boolean isEndDateRequired() {
		if(StringUtils.isBlank(searchItem.getNumber())) {
			return true;
		}
		
		return false;
	}
	
	public List<String> getStatuses() {
		List<String> statuses = new ArrayList<String>();
		
		statuses.add("Active");
		statuses.add("Inactive");
		
		return statuses;
	}
	
	public List<Integer> getRanges() {
		List<Integer> ranges = new ArrayList<Integer>();
		
		ranges.add(3);
		ranges.add(6);
		
		return ranges;
	}

	/*public List<EntryItemTO> getViewInventoryData() {
		List<EntryItemTO> results = new ArrayList<EntryItemTO>();
		
		EntryItemTO singleEntryItem = new EntryItemTO("1234", "description", "Active", new Date(), 1, 1);
		results.add(singleEntryItem);
		
		return results;
	}*/
	
	public List<EntryItem> getViewInventoryData() {
		return viewInventoryData;
	}

	/*public void setViewInventoryData(List<EntryItemTO> viewInventoryData) {
		this.viewInventoryData = viewInventoryData;
	}*/
	
	public void setViewInventoryData(List<EntryItem> viewInventoryData) {
		this.viewInventoryData = viewInventoryData;
	}

	public boolean isRenderSearchResultsTable() {
		return renderSearchResultsTable;
	}

	public void setRenderSearchResultsTable(boolean renderSearchResultsTable) {
		this.renderSearchResultsTable = renderSearchResultsTable;
	}

	public boolean isRenderForecastResultsTable() {
		return renderForecastResultsTable;
	}

	public void setRenderForecastResultsTable(boolean renderForecastResultsTable) {
		this.renderForecastResultsTable = renderForecastResultsTable;
	}

	public boolean isRenderDeseasonalizedDemand() {
		return renderDeseasonalizedDemand;
	}

	public boolean isRenderForecastItemDescription() {
		return renderForecastItemDescription;
	}

	public void setRenderForecastItemDescription(
			boolean renderForecastItemDescription) {
		this.renderForecastItemDescription = renderForecastItemDescription;
	}

	public void setRenderDeseasonalizedDemand(boolean renderDeseasonalizedDemand) {
		this.renderDeseasonalizedDemand = renderDeseasonalizedDemand;
	}

	public boolean isRenderLevel() {
		return renderLevel;
	}

	public void setRenderLevel(boolean renderLevel) {
		this.renderLevel = renderLevel;
	}

	public boolean isRenderTrend() {
		return renderTrend;
	}

	public void setRenderTrend(boolean renderTrend) {
		this.renderTrend = renderTrend;
	}

	public boolean isRenderSeasonalFactor() {
		return renderSeasonalFactor;
	}

	public void setRenderSeasonalFactor(boolean renderSeasonalFactor) {
		this.renderSeasonalFactor = renderSeasonalFactor;
	}

	public boolean isRenderForecastError() {
		return renderForecastError;
	}

	public void setRenderForecastError(boolean renderForecastError) {
		this.renderForecastError = renderForecastError;
	}

	public boolean isRenderMAD() {
		return renderMAD;
	}

	public boolean isRenderPercentageError() {
		return renderPercentageError;
	}

	public void setRenderPercentageError(boolean renderPercentageError) {
		this.renderPercentageError = renderPercentageError;
	}

	public void setRenderMAD(boolean renderMAD) {
		this.renderMAD = renderMAD;
	}

	public boolean isRenderMAPE() {
		return renderMAPE;
	}

	public void setRenderMAPE(boolean renderMAPE) {
		this.renderMAPE = renderMAPE;
	}

	public boolean isRenderTrackingSignal() {
		return renderTrackingSignal;
	}

	public void setRenderTrackingSignal(boolean renderTrackingSignal) {
		this.renderTrackingSignal = renderTrackingSignal;
	}

	public boolean isRenderBias() {
		return renderBias;
	}

	public void setRenderBias(boolean renderBias) {
		this.renderBias = renderBias;
	}

	public boolean isSearchItemNumberEmpty() {
		return isSearchItemNumberEmpty;
	}

	public void setSearchItemNumberEmpty(boolean isSearchItemNumberEmpty) {
		this.isSearchItemNumberEmpty = isSearchItemNumberEmpty;
	}

	public boolean isSearchItemDescEmpty() {
		return isSearchItemDescEmpty;
	}

	public void setSearchItemDescEmpty(boolean isSearchItemDescEmpty) {
		this.isSearchItemDescEmpty = isSearchItemDescEmpty;
	}

	public boolean isForecastItemNumberEmpty() {
		return isForecastItemNumberEmpty;
	}

	public void setForecastItemNumberEmpty(boolean isForecastItemNumberEmpty) {
		this.isForecastItemNumberEmpty = isForecastItemNumberEmpty;
	}

	public boolean isForecastItemDescEmpty() {
		return isForecastItemDescEmpty;
	}

	public void setForecastItemDescEmpty(boolean isForecastItemDescEmpty) {
		this.isForecastItemDescEmpty = isForecastItemDescEmpty;
	}

	public ForecastItem getViewForecastItem() {
		return viewForecastItem;
	}

	public void setViewForecastItem(ForecastItem viewForecastItem) {
		this.viewForecastItem = viewForecastItem;
	}

	public String getTodaysDate() {
		if(todaysDate == null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			todaysDate = format.format(new Date());
		}
		return todaysDate;
	}

	public void setTodaysDate(String todaysDate) {
		this.todaysDate = todaysDate;
	}

}