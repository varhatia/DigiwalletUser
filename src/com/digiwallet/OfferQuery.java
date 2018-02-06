package com.digiwallet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/OfferQuery")
public class OfferQuery {

	ConnectionManager connectionManager = ConnectionManager.getInstance();

	@GET
	@Path("/offers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Offer> getAllOffers() {
		Connection connection = connectionManager.getConnection();
		List<Offer> offers = connectionManager.getOffers(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return offers;
	}
}
