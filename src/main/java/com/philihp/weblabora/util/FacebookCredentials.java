package com.philihp.weblabora.util;

public class FacebookCredentials {

	private String facebookId;
	private String name;
	private String firstName;
	private String link;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String toString() {
		return "FacebookCredentials{facebookId=" + facebookId + ", name=\"" + name + "\", firstName=\"" + firstName + "\", link=\"" + link
				+ "\"}";
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
}
