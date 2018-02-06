package com.digiwallet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/ShopQuery")
public class ShopQuery {

	ConnectionManager connectionManager = ConnectionManager.getInstance();

	@GET
	@Path("/shops/{custId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shop> getShops(@PathParam("custId") String custId) {
		Connection connection = connectionManager.getConnection();
		System.out.println("Cust id = "+custId);
		List<Shop> shops = connectionManager.getShops(connection, custId);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shops;
	}

}
