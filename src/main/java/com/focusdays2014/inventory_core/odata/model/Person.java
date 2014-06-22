package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the person database table.
 * 
 */
@Entity
@NamedQuery(name="Person.findAll", query="SELECT p FROM Person p")
public class Person implements Serializable {
	private static final long serialVersionUID = 6207944485315990919L;

	@Id
	private String personID;

	private String personName;

	private BigDecimal totalPriceInventories;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="person")
	private List<Device> devices;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="person")
	private List<Inventory> inventories;

	//bi-directional many-to-many association to Location
	@ManyToMany(mappedBy="persons")
	private List<Location> locations;

	public Person() {
	}

	public String getPersonID() {
		return this.personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public BigDecimal getTotalPriceInventories() {
		return this.totalPriceInventories;
	}

	public void setTotalPriceInventories(BigDecimal totalPriceInventories) {
		this.totalPriceInventories = totalPriceInventories;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setPerson(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setPerson(null);

		return device;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public Inventory addInventory(Inventory inventory) {
		getInventories().add(inventory);
		inventory.setPerson(this);

		return inventory;
	}

	public Inventory removeInventory(Inventory inventory) {
		getInventories().remove(inventory);
		inventory.setPerson(null);

		return inventory;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

}