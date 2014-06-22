package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commodity database table.
 * 
 */
@Entity
@NamedQuery(name="Commodity.findAll", query="SELECT c FROM Commodity c")
public class Commodity implements Serializable {
	private static final long serialVersionUID = 4381530028393292397L;

	@Id
	private int commodityID;

	private String commodityPicture;

	private BigDecimal commodityPrice;

	private String commodityTitle;

	private int commodityType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date mutationTimestamp;

	private int roomType;

	//bi-directional many-to-one association to Commodity
	@ManyToOne
	private Commodity commodity;

	//bi-directional many-to-one association to Commodity
	@OneToMany(mappedBy="commodity")
	private List<Commodity> commodities;

	//bi-directional many-to-many association to Inventory
	@ManyToMany
	@JoinTable(
		name="inventory_has_commodity"
		, joinColumns={
			@JoinColumn(name="commodity_commodityID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="inventory_inventoryID", referencedColumnName="inventoryID"),
			@JoinColumn(name="inventory_location_locationID", referencedColumnName="location_locationID"),
			@JoinColumn(name="inventory_person_personID", referencedColumnName="person_personID")
			}
		)
	private List<Inventory> inventories;

	public Commodity() {
	}

	public int getCommodityID() {
		return this.commodityID;
	}

	public void setCommodityID(int commodityID) {
		this.commodityID = commodityID;
	}

	public String getCommodityPicture() {
		return this.commodityPicture;
	}

	public void setCommodityPicture(String commodityPicture) {
		this.commodityPicture = commodityPicture;
	}

	public BigDecimal getCommodityPrice() {
		return this.commodityPrice;
	}

	public void setCommodityPrice(BigDecimal commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public String getCommodityTitle() {
		return this.commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public int getCommodityType() {
		return this.commodityType;
	}

	public void setCommodityType(int commodityType) {
		this.commodityType = commodityType;
	}

	public Date getMutationTimestamp() {
		return this.mutationTimestamp;
	}

	public void setMutationTimestamp(Date mutationTimestamp) {
		this.mutationTimestamp = mutationTimestamp;
	}

	public int getRoomType() {
		return this.roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public List<Commodity> getCommodities() {
		return this.commodities;
	}

	public void setCommodities(List<Commodity> commodities) {
		this.commodities = commodities;
	}

	public Commodity addCommodity(Commodity commodity) {
		getCommodities().add(commodity);
		commodity.setCommodity(this);

		return commodity;
	}

	public Commodity removeCommodity(Commodity commodity) {
		getCommodities().remove(commodity);
		commodity.setCommodity(null);

		return commodity;
	}

	public List<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

}