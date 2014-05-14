package com.sapphire.workspace;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Work_Create_CaseSeries extends Config{
	
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
		@Test
		public void TestMethodCreateCaseSeries() throws Exception{
			try{
				ExcelLibrary lib1=new ExcelLibrary();
				for(int i=1;i<=lib1.rowCount;i++)
				{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("CASE SERIES")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("caseSeriesform:caseSeriesGrid:createCaseSeries")).click();
			Thread.sleep(3000);
			String caseSeriesName=lib1.getExcelData("WF-caseseries", i, 0, filePath);
			driver.findElement(By.id("caseSeriesDetailForm:nameid")).sendKeys(caseSeriesName);
			String caseSeriesType=lib1.getExcelData("WF-caseseries", i, 1, filePath);
			if(caseSeriesType!=null)
			{
			driver.findElement(By.id("caseSeriesDetailForm:caseseriesId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'caseSeriesDetailForm:caseseriesId_panel')]//div/ul/li[contains(text(),'"+caseSeriesType+"')]")).click();
			}
			String caseSeriesOwner=lib1.getExcelData("WF-caseseries", i, 2, filePath);
			if(caseSeriesOwner!=null)
			{
				driver.findElement(By.id("caseSeriesDetailForm:lookupUser")).click();
				driver.switchTo().activeElement();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[contains(@id,'ownerLookupForm:userLookupGrid')]//span/input")).sendKeys(caseSeriesOwner);
				driver.findElement(By.xpath("//div[contains(@id,'ownerLookupForm:userLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+caseSeriesOwner+"')]]]//td[1]/div/div/div[2]")).click();
				driver.findElement(By.id("ownerLookupForm:selectUser")).click();
				
			}
			String topic=lib1.getExcelData("WF-caseseries", i, 6, filePath);
			driver.findElement(By.id("caseSeriesDetailForm:lookupTopic")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicHeaderGridSave')]//span/input[1]")).sendKeys(topic);
			driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicHeaderGridSave')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+topic+"')]]]//td[1]//div/div/div[2]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicLookupDlg')]//div[3]//button[1]/span")).click();
			String caseSeriesDatasource=lib1.getExcelData("WF-caseseries", i, 3, filePath);
			driver.findElement(By.id("caseSeriesDetailForm:datasourceId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'caseSeriesDetailForm:datasourceId_panel')]//li[text()='"+caseSeriesDatasource+"']")).click();
			String caseSeriesDescription=lib1.getExcelData("WF-caseseries", i, 4, filePath);
			driver.findElement(By.id("caseSeriesDetailForm:descriptionId")).sendKeys(caseSeriesDescription);
			driver.findElement(By.id("caseSeriesDetailForm:saveProduct")).click();
			Thread.sleep(5000);
			String caseSeriesFile=lib1.getExcelData("WF-caseseries", i, 5, filePath);
			if(caseSeriesFile!=null)
			{
				driver.findElement(By.xpath("//span[contains(@id,'caseSeriesDetailForm:savepnl')]/div[contains(@id,'caseSeriesDetailForm:fileUploadBtn')]")).click();
				Thread.sleep(3000);	
				setClipboardData(caseSeriesFile);
				Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				driver.findElement(By.id("caseSeriesDetailForm:saveProduct")).click();
				Thread.sleep(5000);
			}
			driver.findElement(By.id("caseSeriesDetailForm:saveAndCloseUser")).click();
			Thread.sleep(5000);
				}
			}
			catch (Exception e) 
			{
				Assert.fail("Error in case series creation..");
				e.printStackTrace();
			}
		}
}
