package com.ms.sapphire.dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;
public class Dashboard_SetPreference extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.DASHBOARD);
	@Test
	public void set_preference() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
		WebElement user=driver.findElement(By.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
		act.moveToElement(user).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Preferences")).click();
		Thread.sleep(5000);
	/*	for(int i=1;i<=lib1.rowCount;i++)
		{
		String masterName=lib1.getExcelData("PreferenceMaster", i, 0, filePath);
		Thread.sleep(2000);
		driver.findElement(By.id("preferenceDetailForm:parameterTabView:addMaster")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//div[contains(@id,'masterLookupForm:masterGrid')]//span/input")).sendKeys(masterName);
		driver.findElement(By.xpath("//div[contains(@id,'masterLookupForm:masterGrid')]//span/button[1]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(" //tr[td[2][div[contains(text(),'"+masterName+"')]]]//td[1]/div/div/div[2]")).click();
		driver.findElement(By.xpath(" //div[contains(@id,'masterLookupForm:masterLookupDlg')]//div[3]/span/button[1]/span")).click();
		}//for (master name)		
	
		for(int j=1;j<=lib1.rowCount;j++)
		{
			String dict=lib1.getExcelData("PreferenceProduct", j, 0, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'preferenceDetailForm:parameterTabView:dashTab')]//table[2]/tbody[1]/tr[1]/td[2]//button[1]/span[contains(text(),'Add')]")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:searchLevel_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"+dict+"')]")).click();
			String productType=lib1.getExcelData("PreferenceProduct", j, 1, filePath);
			if(productType!=null)
				{
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:drugLevelId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:drugLevelId_panel')]//div/ul/li[contains(text(),'"+productType+"')]")).click();
				}
			String product=lib1.getExcelData("PreferenceProduct", j, 2, filePath);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//span/input")).sendKeys(product);
			driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(" //tr[td[2][div[contains(text(),'"+product+"')]]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.xpath(" //div[contains(@id,'productLookupForm:poductLookupDlg')]//div[3]/span/button[1]/span")).click();
			Thread.sleep(2000);	
		}	//for product
*/
		int columnCount = 0;
		ExcelLibrary libProductCount=new ExcelLibrary();
		for(int productCount=0;productCount<=libProductCount.rowCount;productCount++)
		{
		String productTotal=libProductCount.getExcelData("PreferenceProduct", productCount, 2, filePath);
		}
		for(int k=1;k<=libProductCount.rowCount;)
		{
			String product=lib1.getExcelData("PreferenceProduct", k, 2, filePath);
		driver.findElement(By.id("preferenceDetailForm:parameterTabView:ProdForEventListDropDown_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'preferenceDetailForm:parameterTabView:ProdForEventListDropDown_panel')]//div/ul/li[contains(text(),'"+product+"')]")).click();
			
				for(int rowEventCount=1;rowEventCount<=lib1.rowCount;rowEventCount++)
				{
			String event=lib1.getExcelData("PreferenceEvents", rowEventCount, columnCount, filePath);
			if(event!=null)
			{
			driver.findElement(By.id("preferenceDetailForm:parameterTabView:addEventForProdBtnLkp")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId")).sendKeys(event);
			driver.findElement(By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:prodTab')]//button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+event+"')]]]//td[1]/div/div")).click();
			driver.findElement(By.id("eventLookupForm:selectEvent")).click();
			Thread.sleep(2000);
			}
				}
				k++;
				columnCount++;
			}
		Thread.sleep(2000);
		String algo=lib1.getExcelData("Preference", 1, 0, filePath);
		if(algo!=null)
		{
		driver.findElement(By.id("preferenceDetailForm:parameterTabView:algoDropDown_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'preferenceDetailForm:parameterTabView:algoDropDown_panel')]//div/ul/li[text()='"+algo+"']")).click();
		}
		
		Thread.sleep(2000);
		driver.findElement(By.id("preferenceDetailForm:saveSettings")).click();
		Thread.sleep(4000);
		driver.findElement(By.linkText("HOME")).click();
		Thread.sleep(4000);
		}
		catch (Exception e) 
		{
			takeScreenShot(e, "Dashboard_SetPreference");
			e.printStackTrace();
			Assert.fail("Error in set preference ..");
		}
	}
}
