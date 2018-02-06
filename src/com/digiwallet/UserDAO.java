package com.digiwallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	// public List<User> getAllUsers() {
	// List<User> userList = null;
	// try {
	// File file = new File("Users.dat");
	// if (!file.exists()) {
	// User user = new User("Mahesh", "Teacher");
	// userList = new ArrayList<User>();
	// userList.add(user);
	// saveUserList(userList);
	// } else {
	// FileInputStream fis = new FileInputStream(file);
	// ObjectInputStream ois = new ObjectInputStream(fis);
	// userList = (List<User>) ois.readObject();
	// ois.close();
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// return userList;
	// }

	public List<User> getAllUsers(Connection connection) {
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement ps = connection
					.prepareStatement("SELECT CUSTOMER_ID,PHONE_NUMBER,PASSWORD,DIGICASH FROM CUSTOMER ORDER BY CUSTOMER_ID DESC");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setCustomerId(rs.getLong("CUSTOMER_ID"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setDigiCash(rs.getLong("DIGICASH"));
				userList.add(user);
			}
			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public User getUser(Connection connection, String phoneNumber, String password) {
		List<User> users = getAllUsers(connection);
		System.out.println("Required pho " + phoneNumber);
		System.out.println("Users " + users.get(0).getPhoneNumber());
		for (User user : users) {
			if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)) {
				System.out.println("Got one");
				return user;
			}
		}
		return null;
	}

	public int addUser(Connection connection, User pUser) {
		List<User> userList = getAllUsers(connection);
		boolean userExists = false;
		for (User user : userList) {
			if (user.getPhoneNumber().equals(pUser.getPhoneNumber())) {
				userExists = true;
				break;
			}
		}
		if (!userExists) {
			String query = " insert into CUSTOMER (CUSTOMER_ID, PHONE_NUMBER, PASSWORD, DIGICASH)"
					+ " values (?, ?, ?, ?)";

			// get the latest id;
			long id = userList.get(0).getCustomerId();
			System.out.println("Id to insert"+id);
			
			try {
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setLong(1, ++id);
				ps.setString(2, pUser.getPhoneNumber());
				ps.setString(3, pUser.getPassword());
				ps.setLong(4, 100);
				
				System.out.println("PS query"+ps.toString());
				
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//saveUserList(userList);
			return 1;
		}
		return 0;
	}

//	public int updateUser(Connection connection, User pUser) {
//		List<User> userList = getAllUsers(connection);
//		for (User user : userList) {
//			if (user.getPhoneNumber() == pUser.getPhoneNumber()) {
//				int index = userList.indexOf(user);
//				userList.set(index, pUser);
//				saveUserList(userList);
//				return 1;
//			}
//		}
//		return 0;
//	}

//	public int deleteUser(Connection connection, String phoneNumber) {
//		List<User> userList = getAllUsers(connection);
//
//		for (User user : userList) {
//			if (user.getPhoneNumber() == phoneNumber) {
//				int index = userList.indexOf(user);
//				userList.remove(index);
//				saveUserList(userList);
//				return 1;
//			}
//		}
//		return 0;
//	}

////	private void saveUserList(List<User> userList) {
////		try {
////			File file = new File("Users.dat");
////			FileOutputStream fos;
////
////			fos = new FileOutputStream(file);
////
////			ObjectOutputStream oos = new ObjectOutputStream(fos);
////			oos.writeObject(userList);
////			oos.close();
////		} catch (FileNotFoundException e) {
////			e.printStackTrace();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//	}
}