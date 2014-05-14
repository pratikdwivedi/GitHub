package com.sapphire.portfolio;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Create_ActionsPlan extends Config{

	@Test
	public void TestMethodPortCreateActionPlan() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				Thread.sleep(10000);
				
				driver.findElement(By.linkText("MASTERS")).click();
				Thread.sleep(3000);
				List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String masterName=lib1.getExcelData("actionsplan", i, 0, filePath);
				for( WebElement we:listMaster)
	          	{
	        	  System.out.println(we.getText());
	        	  if(we.getText().contentEquals(masterName))
	        	  	{
	        		  	String link = we.getText(); 
						System.out.println("Clicked LInk"+link);
						we.click();
				        System.out.println("clicked");
				        break;
	        	  	}
	          	}
	            Thread.sleep(4000);
	            driver.findElement(By.linkText("Actions")).click();
				driver.findElement(By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//span/button[1]/span[contains(text(),'Create')]")).click();
				String planName=lib1.getExcelData("actionsplan", i, 1, filePath);	
				driver.findElement(By.id("actionPlanDetailForm:actionPlanName")).sendKeys(planName);
				
				String planStartDate=lib1.getExcelData("actionsplan", i, 2, filePath);
				driver.findElement(By.id("actionPlanDetailForm:planStartDate_input")).clear();
				driver.findElement(By.id("actionPlanDetailForm:planStartDate_input")).sendKeys(planStartDate);
				
				String planEndDate=lib1.getExcelData("actionsplan", i, 3, filePath);
				driver.findElement(By.id("actionPlanDetailForm:planEndDate_input")).clear();
				driver.findElement(By.id("actionPlanDetailForm:planEndDate_input")).sendKeys(planEndDate);
				
				String actualStartDate=lib1.getExcelData("actionsplan", i, 4, filePath);
				driver.findElement(By.id("actionPlanDetailForm:actualStartDate_input")).clear();
				driver.findElement(By.id("actionPlanDetailForm:actualStartDate_input")).sendKeys(actualStartDate);
				
				String actualEndDate=lib1.getExcelData("actionsplan", i, 5, filePath);
				driver.findElement(By.id("actionPlanDetailForm:actualEndDate_input")).clear();
				driver.findElement(By.id("actionPlanDetailForm:actualEndDate_input")).sendKeys(actualEndDate);
				
				String owner=lib1.getExcelData("actionsplan", i, 6, filePath);
				driver.findElement(By.id("actionPlanDetailForm:lookupUser")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:userLookupGrid')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/input")).sendKeys(owner);
				driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:userLookupGrid')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tbody[contains(@id,'userLookupForm:userLookupGrid_data')]//tr/td/div/div/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("userLookupForm:selectUser")).click();
				String desc=lib1.getExcelData("actionsplan", i, 7, filePath);
				driver.findElement(By.id("actionPlanDetailForm:actionPlanDescription")).sendKeys(desc);
				driver.findElement(By.id("actionPlanDetailForm:savePlan")).click();
				
				Thread.sleep(5000);
			}//for close
		}//try close
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("Error in creating Actions ");
		}
	}//method close	
}
