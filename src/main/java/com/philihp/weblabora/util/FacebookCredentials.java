package com.philihp.weblabora.util;

public class FacebookCredentials {

	private int id;
	private String name;
	private String firstName;
	private String link;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "FacebookCredentials{id=" + id + ", name=\"" + name + "\", firstName=\"" + firstName + "\", link=\"" + link
				+ "\"}";
	}
}
