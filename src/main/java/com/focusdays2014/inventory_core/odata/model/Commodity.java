package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the commodity database table.
 * 
 */
@Entity
@NamedQuery(name="Commodity.findAll", query="SELECT d FROM Commodity d")
public class Commodity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int commodityId;
	
	private String commodityTitle;
	private Integer commodityType;
	private Integer roomType;
	private BigDecimal commodityPrice;
	private String commodityPicture;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date mutationTimestamp;
	
	//bi-directional many-to-one association to Person
	@ManyToOne
	private Inventory inventory;

	public Commodity() {
	}
	
	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityTitle() {
		return commodityTitle;
	}

	public void setCommodityTitle(String commodityTitle) {
		this.commodityTitle = commodityTitle;
	}

	public Integer getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(Integer commodityType) {
		this.commodityType = commodityType;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public BigDecimal getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(BigDecimal commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public String getCommodityPicture() {
		return commodityPicture;
	}

	public void setCommodityPicture(String commodityPicture) {
		this.commodityPicture = commodityPicture;
	}

	public Date getMutationTimestamp() {
		return mutationTimestamp;
	}

	public void setMutationTimestamp(Date mutationTimestamp) {
		this.mutationTimestamp = mutationTimestamp;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
		
	
	
}
