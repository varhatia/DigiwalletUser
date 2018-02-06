package com.digiwallet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/UserService")
public class UserService {

	ConnectionManager connectionManager = ConnectionManager.getInstance();
	private static final String SUCCESS_RESULT = "success";
	private static final String FAILURE_RESULT = "failure";

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		Connection connection = connectionManager.getConnection();
		List<User> userList = connectionManager.getAllUsers(connection);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@GET
	@Path("/users/{phoneNumber}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam("phoneNumber") String phoneNumber,
			@PathParam("password") String password) {
		Connection connection = connectionManager.getConnection();
		User user = connectionManager
				.getUser(connection, phoneNumber, password);
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@POST
	@Path("/users/{phoneNumber}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String createUser(@PathParam("phoneNumber") String phoneNumber,
			@PathParam("password") String password) throws IOException {
		System.out.println("phoneNum " + phoneNumber);
		System.out.println("password " + password);

		User newUser = new User(phoneNumber, password);
		Connection connection = connectionManager.getConnection();

		int result = connectionManager.addUser(connection, newUser);
		JSONObject obj = new JSONObject();

		try {
			if (result == 1) {
				obj.put("result", SUCCESS_RESULT);
			} else {
				obj.put("result", FAILURE_RESULT);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return obj.toString();
	}

	// @POST
	// @Path("/users")
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// public String createUser(@FormParam("phoneNumber") String phoneNumber,
	// @FormParam("password") String password,
	// @Context HttpServletResponse servletResponse) throws IOException {
	// User user = new User(phoneNumber, password);
	// System.out.println("phoneNum " + phoneNumber);
	// System.out.println("pass " + password);
	// int result = connectionManager.addUser(user);
	// JSONObject obj = new JSONObject();
	//
	// if (result == 1) {
	// try {
	// obj.put("result", SUCCESS_RESULT);
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	//
	// } else {
	// try {
	// obj.put("result", FAILURE_RESULT);
	// } catch (JSONException e) {
	// e.printStackTrace();
	// }
	// }
	// return obj.toString();
	// }

	// @POST
	// @Path("/users")
	// @Produces(MediaType.APPLICATION_JSON)
	// @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	// public String updateUser(@QueryParam("phoneNumber") String phoneNumber,
	// @QueryParam("password") String password,
	// @Context HttpServletResponse servletResponse) throws IOException{
	// User user = new User(phoneNumber, password);
	// int result = connectionManager.updateUser(user);
	// if(result == 1){
	// return SUCCESS_RESULT;
	// }
	// return FAILURE_RESULT;
	// }

	// @DELETE
	// @Path("/users/{phoneNumber}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public String deleteUser(@PathParam("phoneNumber") String phoneNumber){
	// int result = connectionManager.deleteUser(phoneNumber);
	// if(result == 1){
	// return SUCCESS_RESULT;
	// }
	// return FAILURE_RESULT;
	// }

	@OPTIONS
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
