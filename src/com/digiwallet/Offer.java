package com.digiwallet;

import java.util.Map;


public class Offer {

	String Category;
	String Store;
	double Id;
	String Offers;
	String OfferSummary;
	int OfferCount;
	String Location;

	public Offer()
	{
		
	}
	
	public Offer(Map<String, Object> dataMap)
	{
		this.Store = (String) dataMap.get("Store");
		this.Id = (Double)dataMap.get("Id");
		this.Offers = (String)dataMap.get("Offers");
		
		setOfferCount(Offers);
		this.OfferSummary = (String)dataMap.get("OfferSummary");
		this.Location = (String)dataMap.get("Location");
		
	}
	
	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public double getId() {
		return Id;
	}

	public void setId(double id) {
		Id = id;
	}

	public int getOfferCount() {
		return OfferCount;
	}

	public void setOfferCount(String Offers) {
		String[] offers = Offers.split(";");
		this.OfferCount = offers.length;
;
	}

	public String getCategory() {
		return Category;
	}
	public String getSummary() {
		return OfferSummary;
	}

	public void setOfferSummary(String summary) {
		this.OfferSummary = summary;
	}

	public void setCategory(String category) {
		this.Category = category;
	}
	public String getStoreName() {
		return Store;
	}
	public void setStoreName(String storeName) {
		this.Store = storeName;
	}
	public double getShopId() {
		return Id;
	}
	public void setShopId(double shopId) {
		this.Id = shopId;
	}
	
	public String getOffers() {
		return Offers;
	}
	public void setOffers(String offers) {
		this.Offers = offers;
	}
	
	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}
	
	
}
