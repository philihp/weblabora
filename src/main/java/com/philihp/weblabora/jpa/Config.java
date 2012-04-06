package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AssociationOverride;
import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.philihp.weblabora.model.WeblaboraException;

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
