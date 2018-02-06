package com.digiwallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAO {

	public List<Place> getPlaces(Connection connection, String type) {
		List<Place> placeList = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM SHOP_DETAILS where CATEGORY = '"+type+"' ORDER BY SHOP_ID DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Place place = new Place();
				place.setShopId(rs.getLong("SHOP_ID"));
				place.setStoreName(rs.getString("STORE"));
				place.setCategory(rs.getString("CATEGORY"));
				place.setAddress(rs.getString("ADDRESS"));
				place.setMethod(rs.getString("METHOD"));
				place.setLocation(rs.getString("LOCATION"));
				place.setOffers(rs.getString("OFFERS"));
				place.setOfferSummary(rs.getString("OFFERSUMMARY"));
				place.setOfferCount(rs.getString("OFFERS"));
				
				placeList.add(place);
			}
			
			return placeList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return placeList;
	}

	public List<Place> getPlaces(Connection connection, String type, String shopId) {
		List<Place> placeList = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM SHOP_DETAILS where CATEGORY = '"+type+"' and SHOP_ID ="+shopId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Place place = new Place();
				place.setShopId(rs.getLong("SHOP_ID"));
				place.setStoreName(rs.getString("STORE"));
				place.setCategory(rs.getString("CATEGORY"));
				place.setAddress(rs.getString("ADDRESS"));
				place.setMethod(rs.getString("METHOD"));
				place.setLocation(rs.getString("LOCATION"));
				place.setOffers(rs.getString("OFFERS"));
				place.setOfferSummary(rs.getString("OFFERSUMMARY"));
				place.setOfferCount(rs.getString("OFFERS"));
				
				placeList.add(place);
			}
			
			return placeList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return placeList;
	}

}