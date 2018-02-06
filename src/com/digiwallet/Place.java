package com.digiwallet;

import java.util.Map;


public class Place {

	String Store;
	String Category;
	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public double getId() {
		return Id;
	}

	public void setId(double id) {
		Id = id;
	}

	double Id;
	String Method;
	String Offers;
	String Address;
	String Location;
	private int OfferCount;
	private String OfferSummary;
	
	public Place()
	{
		
	}
	
	public Place(Map<String, Object> dataMap)
	{
		this.Store = (String) dataMap.get("Store");
		this.Id = (Double)dataMap.get("Id");
		this.Method = (String)dataMap.get("Method");
		this.OfferSummary = (String)dataMap.get("OfferSummary");
		this.Offers = (String)dataMap.get("Offers");
		this.Address = (String)dataMap.get("Address");
		this.Location = (String)dataMap.get("Location");
		this.Category = (String)dataMap.get("Category");
		
		setOfferCount(Offers);
	}

	public String getOfferSummary() {
		return OfferSummary;
	}

	public void setOfferSummary(String offerSummary) {
		OfferSummary = offerSummary;
	}

	public int getOfferCount() {
		return OfferCount;
	}

	public void setOfferCount(String Offers) {
		String[] offers = Offers.split(";");
		this.OfferCount = offers.length;
		
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
	public String getMethod() {
		return Method;
	}
	public void setMethod(String method) {
		this.Method = method;
	}
	public String getOffers() {
		return Offers;
	}
	public void setOffers(String offers) {
		this.Offers = offers;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}


	
	
}
