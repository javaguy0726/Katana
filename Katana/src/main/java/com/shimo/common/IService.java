package com.shimo.common;

import org.openqa.selenium.remote.service.DriverService;

public interface IService {

	void setService();
	
	DriverService getService(); 
}
