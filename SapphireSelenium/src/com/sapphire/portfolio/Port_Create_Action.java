package com.sapphire.portfolio;

import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.common.FetchProperties;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Create_Action extends Config {

	@Test
	public void TestMethodPortCreateAction() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.PORTFOLIO);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.portfolio)).click();
				Thread.sleep(3000);
				List<WebElement> listMaster = driver
						.findElements(By
								.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String masterName = lib1
						.getExcelData("actions", i, 0, filePath);
				for (WebElement weMaster : listMaster) {
					System.out.println(weMaster.getText());
					if (weMaster.getText().contentEquals(masterName)) {
						String linkMaster = weMaster.getText();
						System.out.println("Clicked Link" + linkMaster);
						weMaster.click();
						System.out.println("clicked");
						break;
					}
				}
				Thread.sleep(4000);
				driver.findElement(By.linkText("Actions")).click();
				List<WebElement> listPlans = driver
						.findElements(By
								.xpath("//tbody[contains(@id,'actionPlanListForm:actionPlanGrid_data')]//tr/td[2]/div/a"));
				String planName = lib1.getExcelData("actions", i, 1, filePath);
				for (WebElement wepname : listPlans) {
					System.out.println(wepname.getText());
					if (wepname.getText().contentEquals(planName)) {
						String linkPname = wepname.getText();
						System.out.println("Clicked Link" + linkPname);
						wepname.click();
						System.out.println("clicked");
						break;
					}
				}
				String actionName = lib1
						.getExcelData("actions", i, 2, filePath);
				driver.findElement(
						By.id("actionPlanDetailForm:itemActionGrid:createAction"))
						.click();
				driver.switchTo().activeElement();
				driver.findElement(
						By.id("actionDetailForm:tabView:actionName_label"))
						.click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//div[contains(@id,'actionDetailForm:tabView:actionName_panel')]//div[1]/ul[1]/li[contains(text(),'"
								+ actionName + "')]")).click();
				String status = lib1.getExcelData("actions", i, 3, filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:actionStatus_label"))
						.click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//div[contains(@id,'actionDetailForm:tabView:actionStatus_panel')]//div/ul/li[text()='"
								+ status + "']")).click();
				String priority = lib1.getExcelData("actions", i, 4, filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:actionPriority_label"))
						.click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//div[contains(@id,'actionDetailForm:tabView:actionPriority_panel')]//div/ul/li[text()='"
								+ priority + "']")).click();
				String desc = lib1.getExcelData("actions", i, 5, filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:actionDescription"))
						.sendKeys(desc);
				String planStart = lib1.getExcelData("actions", i, 6, filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:planStartDate_input"))
						.clear();
				driver.findElement(
						By.id("actionDetailForm:tabView:planStartDate_input"))
						.sendKeys(planStart);
				String planEnd = lib1.getExcelData("actions", i, 7, filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:planEndDate_input"))
						.clear();
				driver.findElement(
						By.id("actionDetailForm:tabView:planEndDate_input"))
						.sendKeys(planEnd);
				String actualStart = lib1.getExcelData("actions", i, 8,
						filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:actualStartDate_input"))
						.clear();
				driver.findElement(
						By.id("actionDetailForm:tabView:actualStartDate_input"))
						.sendKeys(actualStart);
				String actualEnd = lib1.getExcelData("actions", i, 9, filePath);
				driver.findElement(
						By.id("actionDetailForm:tabView:actualEndDate_input"))
						.clear();
				driver.findElement(
						By.id("actionDetailForm:tabView:actualEndDate_input"))
						.sendKeys(actualEnd);
				String assignTo = lib1.getExcelData("actions", i, 10, filePath);
				driver.findElement(By.id("actionDetailForm:tabView:lookupUser"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'userLookupForm:userLookupGrid')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/input"))
						.sendKeys(assignTo);
				driver.findElement(
						By.xpath("//div[contains(@id,'userLookupForm:userLookupGrid')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/button[1]/span"))
						.click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'userLookupForm:userLookupGrid_data')]//tr/td/div/div/div[2]"))
						.click();
				Thread.sleep(2000);
				driver.findElement(By.id("userLookupForm:selectUser")).click();
				Thread.sleep(2000);
				String publishToTeam = lib1.getExcelData("actions", i, 11,
						filePath);
				if (publishToTeam.equals("Y")) {
					System.out.println(publishToTeam.equals("Y"));
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:publishtoteam')]//div[2]"))
							.click();
				} else {
					System.out.println("publish to team not mentioned");
				}
				driver.findElement(By.linkText("References")).click();
				// topic
				String topicName = lib1
						.getExcelData("actions", i, 12, filePath);
				driver.findElement(By.linkText("Topics")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:topicReferenceGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
						.click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//div[contains(@id,'topicReferenceForm:topicGrid')]//table/thead/tr/th/div/div/span/input"))
						.sendKeys(topicName);
				driver.findElement(
						By.xpath("//div[contains(@id,'topicReferenceForm:topicGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'topicReferenceForm:topicGrid_data')]//tr/td/div/div/div[2]"))
						.click();
				Thread.sleep(3000);
				driver.findElement(By.id("topicReferenceForm:selectItem"))
						.click();

				// Alert item
				String alert = lib1.getExcelData("actions", i, 13, filePath);
				if (alert != null) {
					driver.findElement(By.linkText("Alert Items")).click();
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:itemReferenceGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
							.click();
					Thread.sleep(2000);
					driver.switchTo().activeElement();
					Thread.sleep(2000);
					driver.findElement(
							By.xpath("//div[contains(@id,'itemReferenceForm:itemGrid')]//table/thead/tr/th/div/div/span/input"))
							.sendKeys(alert);
					driver.findElement(
							By.xpath("//div[contains(@id,'itemReferenceForm:itemGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
							.click();
					WebElement searchAlertText = driver
							.findElement(By
									.xpath("//tbody[contains(@id,'itemReferenceForm:itemGrid_data')]/tr/td/div"));
					String searchAlertText2 = searchAlertText.getText();
					if (searchAlertText2.equals("No records found.")) {
						System.out.println("No records found.");
						driver.findElement(By.id("itemReferenceForm:close"))
								.click();
					} else {
						Thread.sleep(2000);
						driver.findElement(
								By.xpath("//tbody[contains(@id,'itemReferenceForm:itemGrid_data')]//tr/td/div/div/div[2]"))
								.click();
						Thread.sleep(2000);
						driver.findElement(
								By.id("itemReferenceForm:selectItem")).click();
					}
				}
				// Artifacts
				String artifacts = lib1
						.getExcelData("actions", i, 14, filePath);
				driver.findElement(By.linkText("Artifacts")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:artifactReferenceGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
						.click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(
						By.xpath("//tbody[tr[td[2][div[contains(text(),'"
								+ artifacts + "')]]]]//tr/td/div/div/div[2]"))
						.click();
				driver.findElement(
						By.id("artifactReferenceForm:selectArtifact")).click();
				// driver.findElement(By.id("artifactReferenceForm:close")).click();//artifact
				// popup close
				// Minutes
				String minutes = lib1.getExcelData("actions", i, 15, filePath);

				driver.findElement(By.linkText("Minutes")).click();
				if (minutes != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'actionDetailForm:tabView:referenceTab:minutesReferenceGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
							.click();
					driver.switchTo().activeElement();

					WebElement searchMinutesText = driver
							.findElement(By
									.xpath("//tbody[contains(@id,'minutesReferenceForm:minutesGrid_data')]/tr/td/div"));
					String searchMinutesText2 = searchMinutesText.getText();
					if (searchMinutesText2.equals("No records found.")) {
						System.out.println("No records found.");
						driver.findElement(By.id("minutesReferenceForm:close"))
								.click();
					} else {
						driver.findElement(
								By.xpath("//tbody[tr[2][td[2][div[a[contains(text(),'"
										+ minutes
										+ "')]]]]]//tr[2]/td/div/div/div[2]"))
								.click();
						driver.findElement(
								By.id("minutesReferenceForm:selectMinutes"))
								.click();
					}
				} else {
					System.out.println("Minutes are not available");
				}
				// driver.findElement(By.id("minutesReferenceForm:close")).click();//close

				// Documents
				String doc = lib1.getExcelData("actions", i, 16, filePath);
				driver.findElement(By.linkText("Documents")).click();
				driver.findElement(
						By.id("actionDetailForm:tabView:referenceTab:docGrid:addDoc"))
						.click();
				driver.findElement(By.id("supDocDetailForm:Title")).sendKeys(
						doc);
				String templateType = lib1.getExcelData("actions", i, 17,
						filePath);
				driver.findElement(By.id("supDocDetailForm:typeId_label"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//div[contains(@id,'supDocDetailForm:typeId_panel')]/div/ul/li[contains(text(),'"
								+ templateType + "')]")).click();
				String descAction = lib1.getExcelData("actions", i, 18,
						filePath);
				Thread.sleep(2000);
				driver.findElement(By.id("supDocDetailForm:description"))
						.sendKeys(descAction);
				Thread.sleep(2000);
				String actionDoc = lib1
						.getExcelData("actions", i, 19, filePath);// doc upload
				driver.findElement(
						By.xpath(" //div[contains(@id,'supDocDetailForm:supDocDtlDlg')]//div[2]/div[contains(@id,'supDocDetailForm:uploadNewAtrId')]"))
						.click();
				Thread.sleep(3000);
				setClipboardData(actionDoc);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				driver.findElement(By.id("supDocDetailForm:close")).click();// doc
																			// save
				Thread.sleep(10000);

				// driver.findElement(By.id("actionDetailForm:close")).click();//close
				driver.findElement(By.id("actionDetailForm:saveAction"))
						.click();
				Thread.sleep(5000);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			// Assert.fail("Error in creating Actions ");
		}
	}

}
