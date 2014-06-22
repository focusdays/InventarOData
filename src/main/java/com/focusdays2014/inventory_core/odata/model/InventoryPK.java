package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the inventory database table.
 * 
 */
@Embeddable
public class InventoryPK implements Serializable {
	private static final long serialVersionUID = 2434718109916344805L;

	private int inventoryID;

	@Column(insertable=false, updatable=false)
	private String person_personID;

	@Column(insertable=false, updatable=false)
	private int location_locationID;

	public InventoryPK() {
	}
	public int getInventoryID() {
		return this.inventoryID;
	}
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}
	public String getPerson_personID() {
		return this.person_personID;
	}
	public void setPerson_personID(String person_personID) {
		this.person_personID = person_personID;
	}
	public int getLocation_locationID() {
		return this.location_locationID;
	}
	public void setLocation_locationID(int location_locationID) {
		this.location_locationID = location_locationID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof InventoryPK)) {
			return false;
		}
		InventoryPK castOther = (InventoryPK)other;
		return 
			(this.inventoryID == castOther.inventoryID)
			&& this.person_personID.equals(castOther.person_personID)
			&& (this.location_locationID == castOther.location_locationID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.inventoryID;
		hash = hash * prime + this.person_personID.hashCode();
		hash = hash * prime + this.location_locationID;
		
		return hash;
	}
}