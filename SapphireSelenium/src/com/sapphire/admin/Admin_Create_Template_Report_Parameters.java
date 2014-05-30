package com.sapphire.admin;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
public class Admin_Create_Template_Report_Parameters extends Config{
	@Test
	public void TestMethodCreateTemplateReportParameters() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.ADMIN);
		try{
		ExcelLibrary lib1=new ExcelLibrary();
		for(int i=1;i<=lib1.rowCount;i++)
		{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		//Create Report Parameter
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Report parameters")).click();
		String parameterName=lib1.getExcelData("reportparameters", i, 0,filePath);
		driver.findElement(By.id("reportTab:reportParamform:reportParamGrid:createReportParam")).click();
		driver.findElement(By.id("reportParamDetailForm:nameid")).sendKeys(parameterName);
		String datasource=lib1.getExcelData("reportparameters", i, 1,filePath);
		driver.findElement(By.id("reportParamDetailForm:reportParamDSId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamDSId_panel')]//div[1]/ul[1]/li[text()='"+datasource+"']")).click();//datasource
		String types=lib1.getExcelData("reportparameters", i, 2,filePath);
		driver.findElement(By.id("reportParamDetailForm:reportParamTypeId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamTypeId_panel')]//div[1]/ul[1]/li[text()='"+types+"']")).click();//types
		Thread.sleep(3000);
		String codeListID=lib1.getExcelData("reportparameters", i, 3,filePath);
		if(codeListID!=null)
		{
		int codeListIDInt=new Double(codeListID).intValue();
		if (types.equals("Dropdown"))
		{
			System.out.println(codeListIDInt);
		
		driver.findElement(By.id("reportParamDetailForm:paramAssociatedId")).sendKeys(""+codeListIDInt);
		}
		}
		System.out.println("**********************");
		Thread.sleep(3000);
	//	driver.findElement(By.id("reportParamDetailForm:saveAndCloseUser")).click();//save&Close
		driver.findElement(By.id("reportParamDetailForm:saveProduct")).click();//save
	//	driver.findElement(By.id("reportParamDetailForm:cancle"));//cancel
		System.out.println("Template parameter creation complete............"+parameterName);
		Reporter.log("Template parameter creation complete"+parameterName);
		Thread.sleep(3000);
		}//for close
	}
	catch(Exception e)
	{
		takeScreenShot(e, "Admin_Create_Template_Report_Parameters");
		Assert.fail("Error in creating template parameter");
		e.printStackTrace();
	}
	}
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
}
