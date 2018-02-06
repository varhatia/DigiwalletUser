package com.digiwallet;


import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String phoneNumber;
	private String password;
	private long customerId;
	private long digiCash;
	
	public User() {
	}

	public User(String phoneNumber, String profession) {
		this.phoneNumber = phoneNumber;
		this.password = profession;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@XmlElement
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	@XmlElement
	public void setPassword(String password) {
		this.password = password;
	}

	public void setCustomerId(long custId) {
		this.customerId = custId;
	}

	public long getCustomerId() {
		return customerId;
	}

	public long getDigiCash() {
		return digiCash;
	}

	public void setDigiCash(long digiCash) {
		this.digiCash = digiCash;
	}


	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof User)) {
			return false;
		} else {
			User user = (User) object;
			if (phoneNumber.equals(user.getPhoneNumber())
					&& password.equals(user.getPassword())) {
				return true;
			}
		}
		return false;
	}


}
