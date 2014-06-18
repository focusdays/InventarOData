package com.focusdays2014.inventory_core;

import java.io.IOException;

public interface EANTitlePlugin {

	public String getEAN();
	public void setEAN(String code);

	public String getFormat();
	public void setFormat(String format);
	
	public String getEANTitle() throws IOException;
	
}
