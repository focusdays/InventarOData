package com.focusdays2014.inventory_core.odata.firstdb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 

@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c") 
 */
public class CustomerFirstDb implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=200)
	private String email;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="customerBean")
	private List<InventoryFirstDb> inventories;

	public CustomerFirstDb() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<InventoryFirstDb> getInventories() {
		return this.inventories;
	}

	public void setInventories(List<InventoryFirstDb> inventories) {
		this.inventories = inventories;
	}

	public InventoryFirstDb addInventory(InventoryFirstDb inventory) {
		getInventories().add(inventory);
		inventory.setCustomerBean(this);

		return inventory;
	}

	public InventoryFirstDb removeInventory(InventoryFirstDb inventory) {
		getInventories().remove(inventory);
		inventory.setCustomerBean(null);

		return inventory;
	}

}