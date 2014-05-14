package com.sapphire.portfolio;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Delete_Action extends Config{
	@Test
	public void TestMethodPortDeleteAction() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORT_DELETE);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("PORTFOLIO")).click();
			Thread.sleep(5000);
			String selectMaster=lib1.getExcelData("deleteAction", i, 0, filePath);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/input")).sendKeys(selectMaster);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//a[contains(text(),'"+selectMaster+"')]")).click();
			driver.findElement(By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li/a/span[contains(text(),'Action')]")).click();
			String selectActionPlan=lib1.getExcelData("deleteAction", i, 1, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//input[contains(@type,'text')]")).sendKeys(selectActionPlan);
			driver.findElement(By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//button[1]/span[contains(text(),'Search')]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody[contains(@id,'actionPlanListForm:actionPlanGrid_data')]//a[contains(text(),'"+selectActionPlan+"')]")).click();
			Thread.sleep(3000);
			String selectAction=lib1.getExcelData("deleteAction", i, 2, filePath);
			driver.findElement(By.xpath("//tbody[contains(@id,'actionPlanDetailForm:itemActionGrid_data')]//a[contains(text(),'"+selectAction+"')]")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			String classCheckbox=driver.findElement(By.xpath("//div[contains(@id,'actionDetailForm:tabView:publishtoteam')]//div[2]")).getAttribute("class");
			if(classCheckbox.toString().equals("ui-chkbox-box ui-widget ui-corner-all ui-state-default ui-state-active"))
			{
			driver.findElement(By.id("actionDetailForm:close")).click();
			driver.findElement(By.xpath("//tr[td[3][div[a[contains(text(),'"+selectAction+"')]]]]//td[1]//div[2]")).click();
			driver.findElement(By.id("actionPlanDetailForm:itemActionGrid:removeAction")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
			String msgAction=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p")).getText();
			System.out.println(msgAction);
			Assert.assertEquals(msgAction, "Published actions cannot be removed.");
			Thread.sleep(3000);
			}
			else
			{
				driver.findElement(By.id("actionDetailForm:close")).click();
				driver.findElement(By.xpath("//tr[td[3][div[a[contains(text(),'"+selectAction+"')]]]]//td[1]//div[2]")).click();
				driver.findElement(By.id("actionPlanDetailForm:itemActionGrid:removeAction")).click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
				String msgAction=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p")).getText();
				System.out.println(msgAction);
				Assert.assertEquals(msgAction, "Selected action(s) removed successfully.");
				Thread.sleep(3000);
			}
			}//for
			}//try
			catch(Throwable e)
			{
				takeScreenShot(e, "Port_Delete_Action");
				e.printStackTrace();
				Assert.fail("Error in deleting Action");
			}
		}
}
