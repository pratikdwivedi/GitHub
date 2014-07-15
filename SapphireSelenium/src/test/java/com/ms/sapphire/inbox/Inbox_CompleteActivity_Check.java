package com.ms.sapphire.inbox;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Inbox_CompleteActivity_Check extends Inbox_logout{
	@Test
	public void inbox_completed_activity_check() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.INBOX);
		try {
			ExcelLibrary inboxLib=new ExcelLibrary();
			for(int i=1;i<=inboxLib.rowCount;i++)
			{
				List list1 = new ArrayList();
			
				String user=inboxLib.getExcelData("activityCheck", i, 0,filePath);
				String pwd=inboxLib.getExcelData("activityCheck", i, 1,filePath);
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
				driver.findElement(By.id("itemform:itemGrid:activitiesList_label")).click();
				String wfActivity=inboxLib.getExcelData("activityCheck", i, 2, filePath); 
				driver.findElement(By.xpath("//div[contains(@id,'itemform:itemGrid:activitiesList_panel')]//div/ul/li[contains(text(),'"+wfActivity+"')]")).click();
				Thread.sleep(3000);
				String checkAlert=inboxLib.getExcelData("activityCheck", i, 3, filePath);
				driver.findElement(By.id("itemform:itemGrid:gblSrc1")).sendKeys(checkAlert);
				driver.findElement(By.id("itemform:itemGrid:gblSrcBtn")).click();
				WebElement checkAlertList=driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a[contains(text(),'"+checkAlert+"')]"));
				String listedAlert=checkAlertList.getText();
				if(checkAlert.equals(listedAlert))
				{
					System.out.println("Pass");
				}
				else{
					System.out.println("Fail");
				}
				String completeAlert=inboxLib.getExcelData("activityCheck", i, 4, filePath);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a[contains(text(),'"+completeAlert+"')]")).click();
				Thread.sleep(4000);
				driver.findElement(By.id("itemDetailForm:completeActivity")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.id("completeActiviryForm:nextActivity_label")).click();
				String completeAlertActivity=inboxLib.getExcelData("activityCheck", i, 5, filePath);
				driver.findElement(By.xpath("//div[contains(@id,'completeActiviryForm:nextActivity_panel')]//div/ul/li[contains(text(),'"+completeAlertActivity+"')]")).click();
				String remarks=inboxLib.getExcelData("activityCheck", i,6, filePath);
				driver.findElement(By.id("completeActiviryForm:activityRemark")).sendKeys(remarks);
				driver.findElement(By.id("completeActiviryForm:completeItem")).click();
				Thread.sleep(10000);
				doLogout();
			}//for close
		}
		catch(Throwable e)
		{
		//	Assert.fail("Error in inbox complete activity check...");
		e.printStackTrace();	
		}
	}
		
	
}
