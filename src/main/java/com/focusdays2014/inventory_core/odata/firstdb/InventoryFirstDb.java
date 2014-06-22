package com.focusdays2014.inventory_core.odata.firstdb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the inventory database table.
 * 
 
@Entity
@Table(name="inventory")
@NamedQuery(name="InventoryFirstDb.findAll", query="SELECT i FROM Inventory i")
*/
public class InventoryFirstDb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="location_address", length=200)
	private String locationAddress;

	@Column(name="location_city", length=200)
	private String locationCity;

	@Column(name="location_country", length=200)
	private String locationCountry;

	@Column(name="location_latitude")
	private double locationLatitude;

	@Column(name="location_longitude")
	private double locationLongitude;

	@Column(name="location_postalcode", length=10)
	private String locationPostalcode;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer", nullable=false)
	private CustomerFirstDb customerBean;

	public InventoryFirstDb() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocationAddress() {
		return this.locationAddress;
	}

	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	public String getLocationCity() {
		return this.locationCity;
	}

	public void setLocationCity(String locationCity) {
		this.locationCity = locationCity;
	}

	public String getLocationCountry() {
		return this.locationCountry;
	}

	public void setLocationCountry(String locationCountry) {
		this.locationCountry = locationCountry;
	}

	public double getLocationLatitude() {
		return this.locationLatitude;
	}

	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}

	public double getLocationLongitude() {
		return this.locationLongitude;
	}

	public void setLocationLongitude(double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}

	public String getLocationPostalcode() {
		return this.locationPostalcode;
	}

	public void setLocationPostalcode(String locationPostalcode) {
		this.locationPostalcode = locationPostalcode;
	}

	public CustomerFirstDb getCustomerBean() {
		return this.customerBean;
	}

	public void setCustomerBean(CustomerFirstDb customerBean) {
		this.customerBean = customerBean;
	}

}