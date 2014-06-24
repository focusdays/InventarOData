package com.focusdays2014.webfilter;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class UTF8HttpServletRequestWrapper extends HttpServletRequestWrapper {

	public UTF8HttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		return EscapeUtils.decodeURIComponent(
				super.getParameter(name));
	}

	@Override
	public Map getParameterMap() {
		Map parameterMap = super.getParameterMap();
		Set<Map.Entry<String, String[]>> set = parameterMap.entrySet();
		for (Map.Entry<String, String[]> entry : set) {
			String[] arr = entry.getValue();
			if (arr != null) {
				for (int i = 0; i < arr.length; i++) {
					arr[i] = EscapeUtils.decodeURIComponent(arr[i]);
				}
			}
		}
		return parameterMap;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] arr = super.getParameterValues(name);
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				arr[i] = EscapeUtils.decodeURIComponent(arr[i]);
			}
		}
		return arr;
	}
	
	
	

}
