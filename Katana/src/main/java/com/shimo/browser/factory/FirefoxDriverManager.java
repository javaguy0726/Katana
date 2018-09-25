package com.shimo.browser.factory;

import java.io.File;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

public class FirefoxDriverManager extends DriverManager {

	private GeckoDriverService ffService;
	
	@Override
	protected void startService() {
		if (null == ffService) {
			try {
				ffService = new GeckoDriverService.Builder()
						.usingDriverExecutable(new File("path/to/my/chromedriver.exe")).usingAnyFreePort().build();
				ffService.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public void stopService() {
		if (null != ffService && ffService.isRunning())
			ffService.stop();
	}
	

	@Override
	protected void createDriver() {
		FirefoxOptions options = new FirefoxOptions();
		driver = new FirefoxDriver(ffService, options);
	}

}
