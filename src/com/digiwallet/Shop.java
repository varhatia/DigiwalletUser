package com.digiwallet;

import java.util.Map;


public class Shop {

	String Category;
	String Store;
	String Address;
	String Location;
	double Cash;
	double Id;
	String Method;
	String Offers;
	
	public Shop()
	{
		
	}
	
	public Shop(Map<String, Object> dataMap)
	{
		this.Category = (String) dataMap.get("Category");
		this.Store = (String) dataMap.get("Store");
		this.Cash = (Double) dataMap.get("Cash");
		this.Id = (Double)dataMap.get("Id");
		this.Address = (String)dataMap.get("Address");
		this.Location = (String)dataMap.get("Location");
		this.Method = (String)dataMap.get("Method");
		this.Offers = (String)dataMap.get("Offers");
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		this.Category = category;
	}
	public String getStoreName() {
		return Store;
	}
	public String getStore() {
		return Store;
	}
	public void setStore(String store) {
		Store = store;
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
	public double getId() {
		return Id;
	}
	public void setId(double id) {
		Id = id;
	}
	public void setStoreName(String storeName) {
		this.Store = storeName;
	}
	public Double getCash() {
		return Cash;
	}
	public void setCash(double cash) {
		this.Cash = cash;
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
	
	
	
}
