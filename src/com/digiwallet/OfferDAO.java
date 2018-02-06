package com.digiwallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OfferDAO {

	public List<Offer> getOffers(Connection connection) {
		List<Offer> offerList = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT * FROM SHOP_DETAILS");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Offer offer = new Offer();
				offer.setShopId(rs.getLong("SHOP_ID"));
				offer.setStoreName(rs.getString("STORE"));
				offer.setCategory(rs.getString("CATEGORY"));
				offer.setLocation(rs.getString("LOCATION"));
				offer.setOfferSummary(rs.getString("OFFERSUMMARY"));
				offer.setOfferCount(rs.getString("OFFERS"));
				
				offerList.add(offer);
			}
			
			return offerList;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return offerList;
	}
}