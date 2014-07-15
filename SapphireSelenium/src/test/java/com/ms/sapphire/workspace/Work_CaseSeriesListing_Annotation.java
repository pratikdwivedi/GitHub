package com.ms.sapphire.workspace;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Work_CaseSeriesListing_Annotation extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void case_series_annotation() throws Exception{
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
		String caseSeriesAnnotation=lib1.getExcelData("caseseries-share-annotate", i, 4, filePath);
		if(caseSeriesAnnotation!=null)
		{
			driver.findElement(By.xpath("//div[contains(@id,'caseSeriesform:caseSeriesGrid')]//span/button[4]/span")).click();//annotate
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("appCSAnnotateForm:listAnnotateType_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'appCSAnnotateForm:listAnnotateType_panel')]//ul/li[contains(text(),'"+caseSeriesAnnotation+"')]")).click();
			String priority=lib1.getExcelData("caseseries-share-annotate", i, 5, filePath);
			driver.findElement(By.id("appCSAnnotateForm:priority_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'appCSAnnotateForm:priority_panel')]//li[contains(text(),'"+priority+"')]")).click();
			String remarks=lib1.getExcelData("caseseries-share-annotate", i, 6, filePath);
			driver.findElement(By.id("appCSAnnotateForm:listAnnotateRem")).sendKeys(remarks);
			String annotationFile=lib1.getExcelData("caseseries-share-annotate", i, 7, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'appCSAnnotateForm:appCSAnnoateDlg')]//div[2]/div[contains(@id,'appCSAnnotateForm:lstfileUploadId')]")).click();
			Thread.sleep(3000);	
			setClipboardData(annotationFile);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			driver.findElement(By.id("appCSAnnotateForm:lstappAnnoateCS")).click();
		}
		Thread.sleep(10000);
		
		
			}//for
		}//try
		catch (Exception e) 
		{
			Assert.fail("Error in case series annotation..");
			e.printStackTrace();
		}
	}		
}
