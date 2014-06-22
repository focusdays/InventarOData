package com.focusdays2014.inventory_core.odata.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the device database table.
 * 
 */
@Entity
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
public class Device implements Serializable {
	private static final long serialVersionUID = 1461073131484869929L;

//	@EmbeddedId
//	private DevicePK id;
	
	// TODO: Workaround
	@Id
	private int deviceID;
	public int getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}
	
	
	private String deviceName;

	private String deviceType;

	//bi-directional many-to-one association to Person
	@ManyToOne
	private Person person;

	public Device() {
	}
	
	

//	public DevicePK getId() {
//		return this.id;
//	}
//
//	public void setId(DevicePK id) {
//		this.id = id;
//	}
	
	
	
	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}