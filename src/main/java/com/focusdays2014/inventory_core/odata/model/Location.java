package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 3000233443320358499L;

	@Id
	private int locationID;

	private BigDecimal geoLocation_accuracy;

	private BigDecimal geoLocation_latitude;

	private BigDecimal geoLocation_longitude;

	private String locationAddress_land;

	private String locationAddress_postalCodeCity;

	private String locationAddress_streetHouseNr;

	private String locationTitle;

	private int locationType;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="location")
	private List<Inventory> inventories;

	//bi-directional many-to-many association to Person
	@ManyToMany
	@JoinTable(
		name="person_has_location"
		, joinColumns={
			@JoinColumn(name="location_locationID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="person_personID")
			}
		)
	private List<Person> persons;

	public Location() {
	}

	public int getLocationID() {
		return this.locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public BigDecimal getGeoLocation_accuracy() {
		return this.geoLocation_accuracy;
	}

	public void setGeoLocation_accuracy(BigDecimal geoLocation_accuracy) {
		this.geoLocation_accuracy = geoLocation_accuracy;
	}

	public BigDecimal getGeoLocation_latitude() {
		return this.geoLocation_latitude;
	}

	public void setGeoLocation_latitude(BigDecimal geoLocation_latitude) {
		this.geoLocation_latitude = geoLocation_latitude;
	}

	public BigDecimal getGeoLocation_longitude() {
		return this.geoLocation_longitude;
	}

	public void setGeoLocation_longitude(BigDecimal geoLocation_longitude) {
		this.geoLocation_longitude = geoLocation_longitude;
	}

	public String getLocationAddress_land() {
		return this.locationAddress_land;
	}

	public void setLocationAddress_land(String locationAddress_land) {
		this.locationAddress_land = locationAddress_land;
	}

	public String getLocationAddress_postalCodeCity() {
		return this.locationAddress_postalCodeCity;
	}

	public void setLocationAddress_postalCodeCity(String locationAddress_postalCodeCity) {
		this.locationAddress_postalCodeCity = locationAddress_postalCodeCity;
	}

	public String getLocationAddress_streetHouseNr() {
		return this.locationAddress_streetHouseNr;
	}

	public void setLocationAddress_streetHouseNr(String locationAddress_streetHouseNr) {
		this.locationAddress_streetHouseNr = locationAddress_streetHouseNr;
	}

	public String getLocationTitle() {
		return this.locationTitle;
	}

	public void setLocationTitle(String locationTitle) {
		this.locationTitle = locationTitle;
	}

	public int getLocationType() {
		return this.locationType;
	}

	public void setLocationType(int locationType) {
		this.locationType = locationType;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setLocation(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setLocation(null);

		return inventory;
	}

	public List<Person> getPersons() {
		return this.persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}