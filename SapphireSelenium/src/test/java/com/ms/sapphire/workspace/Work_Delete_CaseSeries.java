package com.ms.sapphire.workspace;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Work_Delete_CaseSeries extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE_DELETE);
	@Test
	public void delete_case_series() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("CASE SERIES")).click();
		Thread.sleep(3000);
		String caseSeriesName=lib1.getExcelData("deleteCaseSeries", i, 0, filePath);
		driver.findElement(By.xpath("//div[contains(@id,'caseSeriesform:caseSeriesGrid')]//input[@type='text']")).sendKeys(caseSeriesName);
		driver.findElement(By.xpath("//div[contains(@id,'caseSeriesform:caseSeriesGrid')]//button[1]/span[contains(text(),'Search')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[td[3][div[div[a[contains(text(),'Delete')]]]]]//td[1]//div[2]")).click();
		driver.findElement(By.id("caseSeriesform:caseSeriesGrid:removeCaseSeries")).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
		Thread.sleep(5000);
			}
		}
		catch (Throwable e) 
		{
			takeScreenShot(e, "CaseSeriesDelete");
			Assert.fail("Error in case series delete..");
			e.printStackTrace();
		}
	}
}
