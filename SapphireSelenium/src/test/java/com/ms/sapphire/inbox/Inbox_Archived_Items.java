package com.ms.sapphire.inbox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Inbox_Archived_Items extends Inbox_logout{
	@Test
	public void inbox_archived_items() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.INBOX);
		try {
			ExcelLibrary inboxLib=new ExcelLibrary();
			for(int i=1;i<=inboxLib.rowCount;i++)
			{
				List list1 = new ArrayList();
			
				String user=inboxLib.getExcelData("archivedItems", i, 0,filePath);
				String pwd=inboxLib.getExcelData("archivedItems", i, 1,filePath);
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
				Thread.sleep(3000);
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'INBOX')]")).click();
				Thread.sleep(3000);
				System.out.println(user);
				WebElement weUserName=driver.findElement(By.xpath("//div[contains(@class,'userNavBox')]//table/tbody/tr/td[2]/div/ul/li[1]/a/span[1]"));
				String uName=weUserName.getText();
				driver.findElement(By.xpath("//div[contains(@id,'itemform:itemGrid')]//div/button[2]/span")).click();
				Thread.sleep(5000);
				String checkArchivedAlert=inboxLib.getExcelData("archivedItems", i, 2, filePath);
				WebElement checkArchived=driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a"));
	//			String listedArchivedAlert=checkArchived.getText();
				driver.findElement(By.id("itemform:itemGrid:gblSrc1")).sendKeys(checkArchivedAlert);
				driver.findElement(By.id("itemform:itemGrid:gblSrcBtn")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a[contains(text(),'"+checkArchivedAlert+"')]")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li/a[contains(text(),'Activity logs')]")).click();
				Thread.sleep(2000);
				if(browser.equals("firefox"))
				{
					File srcfile1=((FirefoxDriver)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcfile1,new File("D:\\ScreenShots\\ArchivedItems\\"+checkArchivedAlert+".png"));
				}
				else if(browser.equals("ie"))
				{
					File srcfile2=((InternetExplorerDriver)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcfile2,new File("D:\\ScreenShots\\ArchivedItems\\"+checkArchivedAlert+".png"));
				}
				else if(browser.equals("chrome"))
				{
					File srcfile3=((ChromeDriver)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcfile3,new File("D:\\ScreenShots\\ArchivedItems\\"+checkArchivedAlert+".png"));
				}
				
			
				
				Thread.sleep(10000);
				doLogout();
			}//for close
			
		}	//try close
		
		catch(Throwable e)
		{
			Assert.fail("Error in inbox archived items..");
		e.printStackTrace();	
		}
	}	
			
}
