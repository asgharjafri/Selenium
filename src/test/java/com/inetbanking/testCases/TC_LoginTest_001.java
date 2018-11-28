package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException {
		
		LoginPage lp = new LoginPage(driver);
		
		driver.get(baseURL);
		logger.info("entered url " + baseURL);
		
		System.out.println(driver.getTitle());
		
		lp.setUserName(username);
		logger.info("entered username " + username);
		
		lp.setPassword(password);
		logger.info("entered password " + password);
		
		lp.clickSubmit();
		
		if (driver.getTitle().equals("Guru99 Bank Manager 76HomePage")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		
		} 
		else {
			System.out.println("Title not right");
			captureScreen(driver, "logintest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		
		}

	}

}
