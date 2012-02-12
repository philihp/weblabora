package com.philihp.weblabora.jpa;

import static javax.persistence.AccessType.FIELD;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
@Access(FIELD)
abstract class BasicEntity implements Serializable{

	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	private Date dateCreated;

	@Column(name = "date_updated")
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	private Date dateUpdated;

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		Date now = new Date();
		setDateUpdated(now);
		if (getDateCreated() == null) {
			setDateCreated(now);
		}
	}

}
