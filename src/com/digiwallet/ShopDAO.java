package com.digiwallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopDAO {

	public List<Shop> getShops(Connection connection, String custId) {
		List<Shop> shopList = new ArrayList<>();
		try {
			
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM CUSTOMER_DETAILS INNER JOIN SHOP_DETAILS ON CUSTOMER_DETAILS.SHOP_ID = SHOP_DETAILS.SHOP_ID where CUSTOMER_DETAILS.CUSTOMER_ID ="+custId);
			System.out.println("Shop query"+ps.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Shop shop = new Shop();
				shop.setShopId(rs.getLong("SHOP_ID"));
				shop.setStoreName(rs.getString("STORE"));
				shop.setCategory(rs.getString("CATEGORY"));
				shop.setAddress(rs.getString("ADDRESS"));
				shop.setMethod(rs.getString("METHOD"));
				shop.setLocation(rs.getString("LOCATION"));
				shop.setOffers(rs.getString("OFFERS"));
				shop.setCash(rs.getDouble("CASH"));
				
				shopList.add(shop);
			}
			
			return shopList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return shopList;
	}
}