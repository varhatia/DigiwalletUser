package com.digiwallet;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/PlaceQuery")
public class PlaceQuery {

	ConnectionManager connectionManager = ConnectionManager.getInstance();

	@GET
	@Path("/places/{type}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Place> getPlaces(@PathParam("type") String type) {
		Connection connection = connectionManager.getConnection();
		List<Place> places = connectionManager.getPlaces(connection, type);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return places;
	}

	@GET
	@Path("/place/{type}/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Place> getPlace(@PathParam("type") String type,
			@PathParam("id") String shopId) {

		Connection connection = connectionManager.getConnection();
		List<Place> places = connectionManager.getPlaces(connection, type, shopId);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return places;
	}

	@OPTIONS
	@Path("/places")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
