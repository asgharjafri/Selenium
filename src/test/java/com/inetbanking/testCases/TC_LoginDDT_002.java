package com.inetbanking.testCases;

import java.io.IOException;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	@Test(dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException
	{
		
		System.out.println("In loginDDT");
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("user name provided: " + user);
		lp.setPassword(pwd);
		logger.info("password provided: " + pwd );
		lp.clickSubmit();
		System.out.println("with " + user +" and " + pwd);
		Thread.sleep(6000);
		
		// Checking for login-failed alert
		if(isAlertPresent()==true)
		{
			System.out.println("Alert present");
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			Thread.sleep(3000);
			logger.info("Login failed");
			System.out.println("login failed");
		}
		else
		{
			System.out.println("Alert not present");
			Assert.assertTrue(true);
			System.out.println("logging out");
			lp.clickLogout();
			driver.switchTo().alert().accept(); // close logout alert
			driver.switchTo().defaultContent();
			Thread.sleep(3000);
			logger.info("login passed");
			System.out.println("login passed");
		}
		
	 }
	
		public boolean isAlertPresent()
		{
			try
			{
				driver.switchTo().alert();
				return true;
				
			}
			
			catch(NoAlertPresentException e)
			{
				return false;
			}
			
		}  // is alert present
			
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);
				System.out.println("Got xl data: for " + i + "," + j + " : " + logindata[i-1][j]);
			}
		}
		
		return logindata;
		
	}
	
}
