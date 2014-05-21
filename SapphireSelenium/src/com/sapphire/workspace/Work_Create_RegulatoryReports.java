package com.sapphire.workspace;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
import com.test.Save_Analysis;
import com.test.Work_Save_Report;

public class Work_Create_RegulatoryReports extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void TestMethodCreateRegulatoryReport() throws Exception{
		try{
			Work_Save_Report wsr=new Work_Save_Report();
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("REPORTS")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(@id,'workspaceReportForm:leftRepTabView')]//ul/li/a[contains(text(),'Template')]")).click();
		String dataType=lib1.getExcelData("RegulatoryReport", i, 0, filePath);
		driver.findElement(By.xpath("//span[span[3][table[tbody[tr[td[text()='"+dataType+"']]]]]]//span[1]")).click();
		Thread.sleep(2000);
		String templateType=lib1.getExcelData("RegulatoryReport", i, 1, filePath);
		WebElement ws=driver.findElement(By.xpath("//span[span[table[tbody[tr[td[contains(text(),'Signal score reports')]]]]]]//span[1]"));
		ws.click();
		Thread.sleep(2000);
		String templateName=lib1.getExcelData("RegulatoryReport", i, 2, filePath);
		driver.findElement(By.xpath("//div[contains(@id,'workspaceReportForm:leftRepTabView:repprtTemplateTree')]//td[contains(text(),'"+templateName+"')]")).click();	
		Thread.sleep(2000);
		String productLevel=lib1.getExcelData("RegulatoryReport", i, 3, filePath);
		driver.findElement(By.xpath("//div[contains(@id,'reportfieldsform:fieldsList_content')]//div//label[1]")).click();
		driver.findElement(By.xpath("//div/ul/li[contains(text(),'"+productLevel+"')]")).click();
		String productName=lib1.getExcelData("RegulatoryReport", i, 4, filePath);
		driver.findElement(By.xpath("//td[label[contains(text(),'Drug Name')]]//div/input")).sendKeys(productName);
		String PTName=lib1.getExcelData("RegulatoryReport", i, 6, filePath);
		if(PTName!=null)
		{
		driver.findElement(By.xpath("//td[label[contains(text(),'PT')]]//div/input")).sendKeys(PTName);
		}
		String indication=lib1.getExcelData("RegulatoryReport", i, 8, filePath);
		if(indication!=null)
		{
		driver.findElement(By.xpath("//td[label[contains(text(),'Indication List')]]//div/input")).sendKeys(indication);
		}
		String eventType=lib1.getExcelData("RegulatoryReport", i, 7, filePath);
		if(eventType!=null)
		{
		driver.findElement(By.xpath("//td[label[contains(text(),'Event type')]]//div/label[1]")).click();
		driver.findElement(By.xpath("//div/ul/li[contains(text(),'"+eventType+"')]")).click();
		Thread.sleep(2000);
		}
		
		String eventsName=lib1.getExcelData("RegulatoryReport", i, 11, filePath);
		if(eventsName!=null)
		{
		driver.findElement(By.xpath("//td[label[contains(text(),'Event')]]//div/input")).sendKeys(eventsName);
		driver.findElement(By.xpath("//div[contains(@id,'reportfieldsform:fieldsList_content')]//button[1]/span[contains(text(),'Add')]")).click();
		}
		driver.findElement(By.id("reportfieldsform:generateRep_id")).click();
		Thread.sleep(10000);
		wsr.scheduleCheck=lib1.getExcelData("RegulatoryReport", i, 12, filePath);
		wsr.reportGroup=lib1.getExcelData("RegulatoryReport", i, 13, filePath);
		wsr.reportScheduler=lib1.getExcelData("RegulatoryReport", i, 14, filePath);
		wsr.reportName=lib1.getExcelData("RegulatoryReport", i, 15, filePath);
		wsr.reportDesc=lib1.getExcelData("RegulatoryReport", i, 16, filePath);
		wsr.reportNotification=lib1.getExcelData("RegulatoryReport", i, 17, filePath);
		wsr.reportUserNotify=lib1.getExcelData("RegulatoryReport", i, 18, filePath);
		wsr.reportShareNotify=lib1.getExcelData("RegulatoryReport", i, 19, filePath);
		wsr.reportShareUser=lib1.getExcelData("RegulatoryReport", i, 20, filePath);
		wsr.reportShareUserGroup=lib1.getExcelData("RegulatoryReport", i, 21, filePath);
		wsr.save_Report();
		Thread.sleep(5000);
			}//for
		}//try
		catch (Exception e) 
		{
			takeScreenShot(e, "Regulatory_Report_Generation");
		//	Assert.fail("Error in Report group creation..");
			e.printStackTrace();
			
		}
	}//method
		
}
