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

public class Port_Delete_Action_References extends Config {
	@Test
	public void delete_action_references() throws Exception {
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
				String selectMaster = lib1.getExcelData(
						"deleteActionReferences", i, 0, filePath);
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
						By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li/a/span[contains(text(),'Action')]"))
						.click();
				String selectActionPlan = lib1.getExcelData(
						"deleteActionReferences", i, 1, filePath);
				driver.findElement(
						By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//input[contains(@type,'text')]"))
						.sendKeys(selectActionPlan);
				driver.findElement(
						By.xpath("//div[contains(@id,'actionPlanListForm:actionPlanGrid')]//button[1]/span[contains(text(),'Search')]"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'actionPlanListForm:actionPlanGrid_data')]//a[contains(text(),'"
								+ selectActionPlan + "')]")).click();
				Thread.sleep(3000);
				String selectAction = lib1.getExcelData(
						"deleteActionReferences", i, 2, filePath);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'actionPlanDetailForm:itemActionGrid_data')]//a[contains(text(),'"
								+ selectAction + "')]")).click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(
						By.xpath("//div[contains(@id,'actionDetailForm:tabView')]//li[2]/a[contains(text(),'References')]"))
						.click();
				// topics
				String deleteTopic = lib1.getExcelData(
						"deleteActionReferences", i, 3, filePath);
				if (deleteTopic != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab')]//li/a[contains(text(),'Topics')]"))
							.click();
					driver.findElement(
							By.xpath("//tr[td[2][div[contains(text(),'"
									+ deleteTopic + "')]]]//td[1]//div[2]"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:refTopicTab')]//button[2]/span[contains(text(),'Remove')]"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(By.id("removeConfirmForm:confirmRemove"))
							.click();
				}
				// Alert items
				String deleteAlertItems = lib1.getExcelData(
						"deleteActionReferences", i, 4, filePath);
				if (deleteAlertItems != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab')]//li/a[contains(text(),'Alert Items')]"))
							.click();
					driver.findElement(
							By.xpath("//tr[td[2][div[contains(text(),'"
									+ deleteAlertItems + "')]]]//td[1]//div[2]"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:refItemsTab')]//button[2]/span[contains(text(),'Remove')]"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(By.id("removeConfirmForm:confirmRemove"))
							.click();
				}
				// Artifacts
				String deleteArtifacts = lib1.getExcelData(
						"deleteActionReferences", i, 5, filePath);
				if (deleteArtifacts != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab')]//li/a[contains(text(),'Artifacts')]"))
							.click();
					driver.findElement(
							By.xpath("//tr[td[4][div[contains(text(),'"
									+ deleteArtifacts + "')]]]//td[1]//div[2]"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:artifactReferenceGrid')]//button[2]/span[contains(text(),'Remove')]"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(By.id("removeConfirmForm:confirmRemove"))
							.click();
				}
				// Minutes
				String deleteMinutes = lib1.getExcelData(
						"deleteActionReferences", i, 6, filePath);
				if (deleteMinutes != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab')]//li/a[contains(text(),'Minutes')]"))
							.click();
					driver.findElement(
							By.xpath("//tr[td[2][div[contains(text(),'"
									+ deleteMinutes + "')]]]//td[1]//div[2]"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:minutesReferenceGrid')]//button[2]/span[contains(text(),'Remove')]"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(By.id("removeConfirmForm:confirmRemove"))
							.click();
				}
				// Documents
				String deleteDoc = lib1.getExcelData("deleteActionReferences",
						i, 7, filePath);
				if (deleteDoc != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab')]//li/a[contains(text(),'Documents')]"))
							.click();
					driver.findElement(
							By.xpath("//tr[td[3][div[contains(text(),'"
									+ deleteDoc + "')]]]//td[1]//div[2]"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:refSupDocTab')]//button[2]/span[contains(text(),'Remove')]"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(By.id("removeConfirmForm:confirmRemove"))
							.click();
				}
				Thread.sleep(3000);
				driver.findElement(By.id("actionDetailForm:saveAction"))
						.click();
			}// for
		}// try
		catch (Throwable e) {
			takeScreenShot(e, "Port_Delete_Action_Reference");
			e.printStackTrace();
			Assert.fail("Error in deleting Action Reference");
		}
	}
}
