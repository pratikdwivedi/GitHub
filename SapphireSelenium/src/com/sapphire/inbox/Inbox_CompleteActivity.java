package com.sapphire.inbox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.jna.Library;
import com.test.Assertions;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Inbox_CompleteActivity extends Inbox_logout{
	@Test
	public void InboxCompleteActivity() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.INBOX);
		try {
			ExcelLibrary inboxLib=new ExcelLibrary();
			for(int i=1;i<=inboxLib.rowCount;i++)
			{
				List list1 = new ArrayList();
				String user=inboxLib.getExcelData("workflowrbac", i, 0,filePath);
				if(user!=null){
				String pwd=inboxLib.getExcelData("workflowrbac", i, 1,filePath);
				String browser=inboxLib.getExcelData("browsers",1,0,"SapphireFiles\\AutomationTestData.xls");
				String url=inboxLib.getExcelData("browsers",1,1,"SapphireFiles\\AutomationTestData.xls");
				System.out.println("Start scenario");
			if(browser.equals("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(browser.equals("ie"))
			{
				System.setProperty("webdriver.ie.driver","jar files/IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else if(browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","jar files/chromedriver.exe");
					driver=new ChromeDriver();
			}
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				driver.manage().window().maximize();
				WebElement element=driver.findElement(By.id("loginUserId"));
				element.sendKeys(user);
				driver.findElement(By.name("j_password")).sendKeys(pwd);
				Thread.sleep(3000);
				driver.findElement(By.name("submit")).click();
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'INBOX')]")).click();
				System.out.println(user);
				WebElement weUserName=driver.findElement(By.xpath("//div[contains(@class,'userNavBox')]//table/tbody/tr/td[2]/div/ul/li[1]/a/span[1]"));
				String uName=weUserName.getText();
		//		String wfActivity=inboxLib.getExcelData(user, i, 10, filePath);
		//		driver.findElement(By.xpath("//div[contains(@id,'itemform:itemGrid:activitiesList_panel')]//div/ul/li[contains(text(),'"+wfActivity+"')]")).click();
				String completeAlert=inboxLib.getExcelData(user, i, 11, filePath);
				if(completeAlert!=null)
				{
					Thread.sleep(2000);
					driver.findElement(By.id("itemform:itemGrid:gblSrc1")).clear();
					driver.findElement(By.id("itemform:itemGrid:gblSrc1")).sendKeys(completeAlert);
					Thread.sleep(2000);
					driver.findElement(By.id("itemform:itemGrid:gblSrcBtn")).click();
					Thread.sleep(3000);
				WebElement completeAlert2=driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a[contains(text(),'"+completeAlert+"')]"));
				String completeAlert3=completeAlert2.getText();
				Thread.sleep(3000);
				completeAlert2.click();
				Thread.sleep(10000);
				driver.findElement(By.id("itemDetailForm:completeActivity")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.id("completeActiviryForm:nextActivity_label")).click();
				String activity1=inboxLib.getExcelData(user, i, 12, filePath);
				driver.findElement(By.xpath("//div[contains(@id,'completeActiviryForm:nextActivity_panel')]//div/ul/li[contains(text(),'"+activity1+"')]")).click();
				String remarks1=inboxLib.getExcelData(user, i, 13, filePath);
				if(remarks1!=null)
					{
					driver.findElement(By.id("completeActiviryForm:activityRemark")).sendKeys(remarks1);
					}
				driver.findElement(By.id("completeActiviryForm:completeItem")).click();
				Thread.sleep(10000);
				driver.findElement(By.id("itemform:itemGrid:activitiesList_label")).click();
				}
				doLogout();
				}
			}
		}
		catch(Throwable e)
		{
		//	Assert.fail("Error in inbox complete activity ...");
		e.printStackTrace();	
		}
	}//for close
		
}
