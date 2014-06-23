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

public class Port_Delete_Topic extends Config {

	@Test
	public void TestMethodPortDeleteTopic() throws Exception {
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
				String selectMaster = lib1.getExcelData("deleteTopic", i, 0,
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
						By.xpath("//div[contains(@id,'tabView')]//ul/li[3]/a"))
						.click();
				String deleteTopic = lib1.getExcelData("deleteTopic", i, 1,
						filePath);
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView:masterTopictListForm:topicGrid')]//tr[1]//input[contains(@type,'text')]"))
						.sendKeys(deleteTopic);
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView:masterTopictListForm:topicGrid')]//tr[1]//button[1]/span[contains(text(),'Search')]"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tr[td[2][div[div[a[contains(text(),'"
								+ deleteTopic + "')]]]]]//td[1]//div[2]"))
						.click();
				driver.findElement(
						By.id("tabView:masterTopictListForm:topicGrid:deleteTopic"))
						.click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("removeConfirmForm:confirmRemove"))
						.click();
				String alertCheck = driver.findElement(
						By.xpath("//tr[td[2][div[div[a[contains(text(),'"
								+ deleteTopic + "')]]]]]//td[9]/div/div[1]"))
						.getText();
				if (alertCheck.isEmpty()) {
					String msgTopic = driver
							.findElement(
									By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p"))
							.getText();
					System.out.println(msgTopic);
					Assert.assertEquals(msgTopic, "Topic deleted successfully.");
					Thread.sleep(3000);
				} else {
					System.out.println(" Alerts  avalialble");
					String topicAlertText = driver
							.findElement(
									By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p"))
							.getText();
					System.out.println(topicAlertText);
					Assert.assertEquals(
							topicAlertText,
							"Could not delete Topic since some of the alerts are attached to this Topic . Deselect those Topic and try again");
					Thread.sleep(3000);
				}
			}// for
		}// try
		catch (Throwable e) {
			takeScreenShot(e, "Port_Delete_Topic");
			e.printStackTrace();
			Assert.fail("Error in deleting Topic ");
		}
	}
}
