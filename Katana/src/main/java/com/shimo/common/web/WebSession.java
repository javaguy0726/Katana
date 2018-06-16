package com.shimo.common.web;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.service.DriverService;

import com.shimo.common.IConfig;
import com.shimo.common.IService;
import com.shimo.common.ISession;

public abstract class WebSession implements ISession {
	
	IConfig config;
	IService service;
	
	
	public DriverService createService() {
		try {
			service.getService().start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return service.getService();
	};
	
	public void destroyService() {
		service.getService().stop();
	}
	
	public WebDriver createDriver() {
		
	}
	
	public void destroyDriver() {
		
	}
	
	public void setService(IService service) {
		this.service = service;
	}
}
