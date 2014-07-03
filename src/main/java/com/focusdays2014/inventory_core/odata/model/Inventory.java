package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private InventoryPK id;

	private int currency;

	private String inventoryTitle;

	private BigDecimal inventoryTotalPrice;

	private int inventoryType;

	private int language;

	@Temporal(TemporalType.TIMESTAMP)
	private Date mutationTimestamp;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	//bi-directional many-to-many association to Commodity
	@ManyToMany(mappedBy="inventories")
	private List<Commodity> commodities;

	public Inventory() {
	}

	public InventoryPK getId() {
		return this.id;
	}

	public void setId(InventoryPK id) {
		this.id = id;
	}

	public int getCurrency() {
		return this.currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public String getInventoryTitle() {
		return this.inventoryTitle;
	}

	public void setInventoryTitle(String inventoryTitle) {
		this.inventoryTitle = inventoryTitle;
	}

	public BigDecimal getInventoryTotalPrice() {
		return this.inventoryTotalPrice;
	}

	public void setInventoryTotalPrice(BigDecimal inventoryTotalPrice) {
		this.inventoryTotalPrice = inventoryTotalPrice;
	}

	public int getInventoryType() {
		return this.inventoryType;
	}

	public void setInventoryType(int inventoryType) {
		this.inventoryType = inventoryType;
	}

	public int getLanguage() {
		return this.language;
	}

	public void setLanguage(int language) {
		this.language = language;
	}

	public Date getMutationTimestamp() {
		return this.mutationTimestamp;
	}

	public void setMutationTimestamp(Date mutationTimestamp) {
		this.mutationTimestamp = mutationTimestamp;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public List<Commodity> getCommodities() {
		return this.commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

}