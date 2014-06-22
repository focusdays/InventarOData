package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the device database table.
 * 
 */
@Embeddable
public class DevicePK implements Serializable {
	private static final long serialVersionUID = 6949600034975301465L;
	
	private int deviceID;

	@Column(insertable=false, updatable=false)
	private String person_personID;

	public DevicePK() {
	}
	public int getDeviceID() {
		return this.deviceID;
	}
	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}
	public String getPerson_personID() {
		return this.person_personID;
	}
	public void setPerson_personID(String person_personID) {
		this.person_personID = person_personID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DevicePK)) {
			return false;
		}
		DevicePK castOther = (DevicePK)other;
		return 
			(this.deviceID == castOther.deviceID)
			&& this.person_personID.equals(castOther.person_personID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.deviceID;
		hash = hash * prime + this.person_personID.hashCode();
		
		return hash;
	}
}