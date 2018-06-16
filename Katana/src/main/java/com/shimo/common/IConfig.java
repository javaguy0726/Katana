package com.shimo.common;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface IConfig {

	DesiredCapabilities getCapabilities();
	
	String getRemoteAddress();
}
