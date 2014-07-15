package com.ms.sapphire.workspace;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Work_Create_ReportGroup extends Config {
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void create_report_group() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("REPORTS")).click();
		Thread.sleep(5000);
		String reportGroupName=lib1.getExcelData("WF-reportGroup", i, 0, filePath);
		if(reportGroupName!=null)
		{
		driver.findElement(By.id("reportsListForm:reportGrid:manageGroup")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//div[contains(@id,'editReportGrpForm:reportGroupGrid')]//span/button[1]/span[contains(text(),'Add')]")).click();	
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("groupDetailForm:tabView:name")).sendKeys(reportGroupName);
		String reportGroupType=lib1.getExcelData("WF-reportGroup", i, 1, filePath);
		driver.findElement(By.id("groupDetailForm:tabView:groupType_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'groupDetailForm:tabView:groupType_panel')]//div/ul/li[contains(text(),'"+reportGroupType+"')]")).click();
		String reportGroupDesc=lib1.getExcelData("WF-reportGroup", i, 2, filePath);
		driver.findElement(By.id("groupDetailForm:tabView:description")).sendKeys(reportGroupDesc);
		Thread.sleep(2000);
		String reportGroupSharingUser=lib1.getExcelData("WF-reportGroup", i, 3, filePath);
		if(reportGroupSharingUser!=null)
			{
				driver.findElement(By.xpath("//div[contains(@id,'groupDetailForm:tabView')]//ul/li/a[contains(text(),'Group privileges')]")).click();
				driver.findElement(By.xpath("//div[contains(@id,'groupDetailForm:tabView:groupUserGroupGrid')]//button[1]/span[contains(text(),'Add')]")).click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'groupUserLookupForm:tabView:userLookupGrid')]//span/input[1]")).sendKeys(reportGroupSharingUser);
				driver.findElement(By.xpath("//div[contains(@id,'groupUserLookupForm:tabView:userLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+reportGroupSharingUser+"')]]]//td[1]/div/div/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("groupUserLookupForm:selectUser")).click();
			}
		String reportGroupSharingUserGroup=lib1.getExcelData("WF-reportGroup", i, 4, filePath);
		if(reportGroupSharingUserGroup!=null)
		{
			if(reportGroupSharingUser==null)
			{
				driver.findElement(By.xpath("//div[contains(@id,'groupDetailForm:tabView')]//ul/li/a[contains(text(),'Group privileges')]")).click();
			}
			driver.findElement(By.xpath("//div[contains(@id,'groupDetailForm:tabView:groupUserGroupGrid')]//button[1]/span[contains(text(),'Add')]")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'groupUserLookupForm:tabView')]//ul/li/a[contains(text(),'User group')]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'groupUserLookupForm:tabView:userGroupLookupGrid')]//span/input[1]")).sendKeys(reportGroupSharingUserGroup);	
			driver.findElement(By.xpath("//div[contains(@id,'groupUserLookupForm:tabView:userGroupLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+reportGroupSharingUserGroup+"')]]]//td[1]/div/div/div[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("groupUserLookupForm:selectUser")).click();
			}
		Thread.sleep(2000);
		driver.findElement(By.id("groupDetailForm:saveGroup")).click();//save
		Thread.sleep(2000);
		driver.findElement(By.id("editReportGrpForm:close")).click();//close
		Thread.sleep(2000);
		}
			}//for
		}//try
		catch (Exception e) 
		{
			Assert.fail("Error in Report group creation..");
			e.printStackTrace();
		}
	}//method	
}
