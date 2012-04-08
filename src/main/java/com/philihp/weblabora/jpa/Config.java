package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity(name = "Config")
@Access(FIELD)
@Table(name = "weblabora_config")
@NamedQuery(name = "getAllConfigs", query = "SELECT c FROM Config c")
public class Config implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "config_id")
	private int configId;

	@Basic
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Basic
	@Column(name = "value", nullable = true, unique = false)
	private String value;

	public Config() {
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
