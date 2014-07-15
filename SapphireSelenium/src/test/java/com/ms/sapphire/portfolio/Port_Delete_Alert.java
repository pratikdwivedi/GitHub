package com.ms.sapphire.portfolio;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.FetchProperties;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Port_Delete_Alert extends Config {
	@Test
	public void delete_topic_alert() throws Exception {
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
				String selectMaster = lib1.getExcelData("deletePortAlert", i,
						0, filePath);
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
						By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li/a/span[contains(text(),'Setup')]"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView')]//ul/li[3]/a"))
						.click();
				String selectTopic = lib1.getExcelData("deletePortAlert", i, 1,
						filePath);
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView:masterTopictListForm:topicGrid')]//tr[1]//input[contains(@type,'text')]"))
						.sendKeys(selectTopic);
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView:masterTopictListForm:topicGrid')]//tr[1]//button[1]/span[contains(text(),'Search')]"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'tabView:masterTopictListForm:topicGrid_data')]//a[contains(text(),'"
								+ selectTopic + "')]")).click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView:topicDetailForm:tabView')]//ul/li[2]/a[contains(text(),'Alerts')]"))
						.click();
				String deleteAlert = lib1.getExcelData("deletePortAlert", i, 2,
						filePath);
				driver.findElement(
						By.id("tabView:topicDetailForm:tabView:analysisGrid:gblSrc1"))
						.sendKeys(deleteAlert);
				driver.findElement(
						By.id("tabView:topicDetailForm:tabView:analysisGrid:gblSrcBtn"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tr[td[4][div[a[contains(text(),'"
								+ deleteAlert + "')]]]]//td[1]//div[2]"))
						.click();
				driver.findElement(
						By.id("tabView:topicDetailForm:tabView:analysisGrid:deleteAnalysis"))
						.click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("removeConfirmForm:confirmRemove"))
						.click();
				String msgTopicAlert = driver
						.findElement(
								By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p"))
						.getText();
				System.out.println(msgTopicAlert);
				Assert.assertEquals(
						msgTopicAlert,
						"Could not delete analysis since some of the analysis has routed or scheduled to inbox. Deselect those analysis and try again");
				Thread.sleep(3000);
			}
		} catch (Throwable e) {
			takeScreenShot(e, "Port_Delete_TopicAlert");
			e.printStackTrace();
			Assert.fail("Error in deleting Topic Alert ");
		}
	}
}
