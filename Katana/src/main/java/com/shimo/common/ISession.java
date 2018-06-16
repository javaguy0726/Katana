package com.shimo.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

public interface ISession {
	
	DriverService createService();
	
	void destroyService();
	
	WebDriver createDriver();
	
	void destroyDriver();
}
