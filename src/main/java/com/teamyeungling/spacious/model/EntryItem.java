package com.teamyeungling.spacious.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntryItem extends BaseItem {
	
	private Date entryDate;
	private int startQuantity;
	private int receivedQuantity;

	public EntryItem() {
	}

	public EntryItem(String number, String description, String status, Date entryDate, int startQuantity,
			int receivedQuantity) {

		super(number, description, status);

		this.entryDate = entryDate;
		this.startQuantity = startQuantity;
		this.receivedQuantity = receivedQuantity;
	}
	
	public EntryItem(String inventoryMetrics[]) {
		this.setNumber(inventoryMetrics[0]);
		this.setDesc(inventoryMetrics[1]);
		this.setstatus(inventoryMetrics[5]);
		this.setStartQuantity(Integer.parseInt(inventoryMetrics[2]));
		this.setReceivedQuantity(Integer.parseInt(inventoryMetrics[3]));
		
		try {
			this.setEntryDate(new SimpleDateFormat("MM/dd/yyyy").parse(inventoryMetrics[6]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date itemEntryDate) {
		this.entryDate = itemEntryDate;
	}

	public int getStartQuantity() {
		return startQuantity;
	}

	public void setStartQuantity(int itemStartQuantity) {
		this.startQuantity = itemStartQuantity;
	}

	public int getReceivedQuantity() {
		return receivedQuantity;
	}

	public void setReceivedQuantity(int receivedQuantity) {
		this.receivedQuantity = receivedQuantity;
	}

	public String toString() {
		return super.toString() + "\nItem Entry Date:  "
				+ this.getEntryDate().toString() + "\nItem Start Quantity:  "
				+ this.getStartQuantity() + "\nItem Incoming Stock:  "
				+ this.getReceivedQuantity();
	}
}
