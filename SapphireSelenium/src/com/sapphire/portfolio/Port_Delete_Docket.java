package com.sapphire.portfolio;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.FetchProperties;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Delete_Docket extends Config {
	@Test
	public void TestMethodPortDeleteDocket() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.PORT_DELETE);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.portfolio)).click();
				Thread.sleep(5000);
				String selectMaster = lib1.getExcelData("deleteDocket", i, 0,
						filePath);
				driver.findElement(
						By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/input"))
						.sendKeys(selectMaster);
				driver.findElement(
						By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span[2]/button[1]/span"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//a[contains(text(),'"
								+ selectMaster + "')]")).click();
				driver.findElement(
						By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li[4]/a/span"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView')]//ul/li[2]/a"))
						.click();
				String deleteDocket = lib1.getExcelData("deleteDocket", i, 1,
						filePath);
				driver.findElement(
						By.xpath("//tr[td[2][div[div[a[contains(text(),'"
								+ deleteDocket + "')]]]]]//td[1]//div[2]"))
						.click();
				driver.findElement(
						By.id("tabView:masterArtifactListForm:masterFileGrid:deleteArtifact"))
						.click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("removeConfirmForm:confirmRemove"))
						.click();
				String msgDocket = driver
						.findElement(
								By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p"))
						.getText();
				System.out.println(msgDocket);
				Assert.assertEquals(msgDocket, "Artifact deleted successfully.");
				Thread.sleep(3000);
			}
		} catch (Throwable e) {
			takeScreenShot(e, "Port_Delete_Docket");
			e.printStackTrace();
			Assert.fail("Error in deleting Docket ");
		}
	}
}
