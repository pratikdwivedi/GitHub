package com.sapphire.portfolio;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Delete_ActionPlan extends Config{
	@Test
	public void TestMethodPortDeleteActionPlan() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORT_DELETE);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("PORTFOLIO")).click();
			Thread.sleep(5000);
			String selectMaster=lib1.getExcelData("deleteActionPlan", i, 0, filePath);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/input")).sendKeys(selectMaster);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//a[contains(text(),'"+selectMaster+"')]")).click();
			driver.findElement(By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li/a/span[contains(text(),'Action')]")).click();
			String deleteActionPlan=lib1.getExcelData("deleteActionPlan", i, 1, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//input[contains(@type,'text')]")).sendKeys(deleteActionPlan);
			driver.findElement(By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//button[1]/span[contains(text(),'Search')]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tr[td[2][div[a[contains(text(),'"+deleteActionPlan+"')]]]]//td[1]//div[2]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//button/span[contains(text(),'Remove')]")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
			String msgActionPlan=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p")).getText();
			System.out.println(msgActionPlan);
			Assert.assertEquals(msgActionPlan, "Action plan removed successfully.");
			Thread.sleep(3000);
			}
			}
			catch(Throwable e)
			{
				takeScreenShot(e, "Port_Delete_Action Plan");
				e.printStackTrace();
				Assert.fail("Error in deleting Action Plan ");
			}
		}
}
