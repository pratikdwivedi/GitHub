package com.ms.sapphire.workspace;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Work_CaseSeriesListing_Share extends Config {
	

	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void case_series_share() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("CASE SERIES")).click();
		Thread.sleep(5000);
		
		String caseSeriesName=lib1.getExcelData("caseseries-share-annotate", i, 0, filePath);
		driver.findElement(By.xpath("//div[contains(@id,'caseSeriesform:caseSeriesGrid')]//span/input")).sendKeys(caseSeriesName);
		driver.findElement(By.xpath("//div[contains(@id,'caseSeriesform:caseSeriesGrid')]//span/button[1]/span[contains(text(),'Search')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[td[3][div[div[a[contains(text(),'"+caseSeriesName+"')]]]]]//td[1]/div/div/div[2]")).click();
		String caseSeriesShareUser=lib1.getExcelData("caseseries-share-annotate", i, 1, filePath);
		if(caseSeriesShareUser!=null)
		{
		driver.findElement(By.xpath("//div[contains(@id,'caseSeriesform:caseSeriesGrid')]//span/button[3]/span")).click();//share
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		String caseSeriesUserGroup=lib1.getExcelData("caseseries-share-annotate", i, 2, filePath);
		String caseSeriesNotify=lib1.getExcelData("caseseries-share-annotate", i, 3, filePath);
		driver.findElement(By.id("sharedAccessListForm:userGroupGrid:createNew")).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//div[contains(@id,'listUserLookupForm:tabView:userLookupGrid')]//span/input")).sendKeys(caseSeriesShareUser);
		driver.findElement(By.xpath("//div[contains(@id,'listUserLookupForm:tabView:userLookupGrid')]//span/button[1]/span")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tbody[contains(@id,'listUserLookupForm:tabView:userLookupGrid_data')]//div[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("listUserLookupForm:selectUserForList")).click();
		Thread.sleep(3000);
		if(caseSeriesUserGroup!=null)
		{
			driver.findElement(By.id("sharedAccessListForm:userGroupGrid:createNew")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'listUserLookupForm:tabView')]//ul/li/a[contains(text(),'User group')]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'listUserLookupForm:tabView:userGroupLookupGrid')]//span/input")).sendKeys(caseSeriesUserGroup); 
			driver.findElement(By.xpath("//div[contains(@id,'listUserLookupForm:tabView:userGroupLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody[contains(@id,'listUserLookupForm:tabView:userGroupLookupGrid_data')]//div[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("listUserLookupForm:selectUserForList")).click();
			Thread.sleep(3000);		
		}
		if(caseSeriesNotify!=null && caseSeriesNotify.equals("Y"))
		{
			driver.findElement(By.xpath("//div[contains(@id,'sharedAccessListForm:userGroupGrid:notifyId')]/div[2]")).click();
		}
		driver.findElement(By.id("sharedAccessListForm:save")).click();
		}
		Thread.sleep(10000);
			
			}//for
		}//try
		catch (Exception e) 
		{
			Assert.fail("Error in case series sharing..");
			e.printStackTrace();
		}
	}	
}
