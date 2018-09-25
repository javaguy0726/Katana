package com.shimo.browser.factory;

import java.io.File;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends DriverManager {

	private ChromeDriverService chromeService;

	@Override
	public void startService() {
		if (null == chromeService) {
			try {
				chromeService = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File("path/to/my/chromedriver.exe")).usingAnyFreePort().build();
				chromeService.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void stopService() {
		if (null != chromeService && chromeService.isRunning())
			chromeService.stop();
	}

	@Override
	public void createDriver() {
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(chromeService, options);
	}

}
