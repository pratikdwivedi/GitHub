package com.sapphire.portfolio;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Delete_Dossier extends Config{
	@Test
	public void TestMethodPortDeleteDossier() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORT_DELETE);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("PORTFOLIO")).click();
			Thread.sleep(5000);
			String selectMaster=lib1.getExcelData("deleteDossier", i, 0, filePath);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/input")).sendKeys(selectMaster);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//a[contains(text(),'"+selectMaster+"')]")).click();
			driver.findElement(By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li[3]/a/span")).click();
			String deleteDossier=lib1.getExcelData("deleteDossier", i, 1, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'dossierform:dossierGrid')]//input[contains(@type,'text')]")).sendKeys(deleteDossier);
			driver.findElement(By.xpath("//div[contains(@id,'dossierform:dossierGrid')]//button[1]/span[contains(text(),'Search')]")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tr[td[3][div[a[text()='"+deleteDossier+"']]]]//td[1]//div[2]")).click();
			Thread.sleep(3000);
			driver.findElement(By.id("dossierform:dossierGrid:removeDossier")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
			String msgDossier=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p")).getText();
			System.out.println(msgDossier);
			Assert.assertEquals(msgDossier, "Selected record(s) are deleted successfully");
			Thread.sleep(3000);
			}//for
			}//try
			catch(Throwable e)
			{
				takeScreenShot(e, "Port_Delete_Dossier");
				e.printStackTrace();
				Assert.fail("Error in deleting Dossier");
			}
		}
}
