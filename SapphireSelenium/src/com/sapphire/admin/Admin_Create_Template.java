package com.sapphire.admin;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.common.FetchProperties;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_Create_Template extends Config {
	@Test
	public void TestMethodCreateTemplate() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				// Create Template
				driver.findElement(By.linkText(fetchProp.admin)).click();
				driver.findElement(By.linkText("Templates")).click();
				String templateName = lib1.getExcelData("templates", i, 0,
						filePath);
				driver.findElement(
						By.id("reportTab:reportTemplateform:reportTemplateGrid:createReportTemplate"))
						.click();
				driver.findElement(By.id("reportTemplateDetailForm:nameid"))
						.sendKeys(templateName);
				String datasource = lib1.getExcelData("templates", i, 1,
						filePath);
				driver.findElement(
						By.id("reportTemplateDetailForm:datasourceId_label"))
						.click();// datasource dropdown clicked
				driver.findElement(
						By.xpath("//div[contains(@id,'reportTemplateDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[text()='"
								+ datasource + "']")).click();// datasourcess
																// clicked
				String templateGroup = lib1.getExcelData("templates", i, 2,
						filePath);
				if (templateGroup == null) {
					System.out.println("Template Group is not available");
				} else {
					Thread.sleep(3000);
					driver.findElement(
							By.id("reportTemplateDetailForm:reportGroupListId_label"))
							.click();
					driver.findElement(
							By.xpath(" //div[contains(@id,'reportTemplateDetailForm:reportGroupListId_panel')]//div[1]/ul[1]/li[text()='"
									+ templateGroup + "']")).click();
				}
				String type = lib1.getExcelData("templates", i, 3, filePath);// types
																				// checkboxes
				driver.findElement(
						By.xpath(" //table[contains(@id,'reportTemplateDetailForm:typesId')]//tbody[1]/tr/td/label[contains(text(),'"
								+ type + "')]")).click();
				String description = lib1.getExcelData("templates", i, 4,
						filePath);
				driver.findElement(
						By.id("reportTemplateDetailForm:descriptionId"))
						.sendKeys(description);
				String templateRPT = lib1.getExcelData("templates", i, 6,
						filePath);// template rpt upload
				driver.findElement(
						By.xpath("//form[contains(@id,'reportTemplateDetailForm')]//div[2][contains(@id,'reportTemplateDetailForm:simpleid')]"))
						.click();
				Thread.sleep(3000);
				setClipboardData(templateRPT);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				String MappingProductLevel = lib1.getExcelData("templates", i,
						7, filePath);
				if (MappingProductLevel != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ MappingProductLevel
									+ "')]]]//td[5]/div//select/option[contains(text(),'Product Level')]"))
							.click();
					Thread.sleep(2000);
				}
				String MappingDrug = lib1.getExcelData("templates", i, 8,
						filePath);
				if (MappingDrug != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ MappingDrug
									+ "')]]]//td[5]/div//select/option[contains(text(),'Products')]"))
							.click();
					Thread.sleep(2000);
				}
				String Event = lib1.getExcelData("templates", i, 9, filePath);
				if (Event != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ Event
									+ "')]]]//td[5]/div//select/option[contains(text(),'Events')]"))
							.click();
					Thread.sleep(2000);
				}
				String StartDate = lib1.getExcelData("templates", i, 10,
						filePath);
				if (StartDate != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ StartDate
									+ "')]]]//td[5]/div//select/option[contains(text(),'Date')]"))
							.click();
					Thread.sleep(2000);
				}
				String EventType = lib1.getExcelData("templates", i, 11,
						filePath);
				if (EventType != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ EventType
									+ "')]]]//td[5]/div//select/option[contains(text(),'Event Type')]"))
							.click();
					Thread.sleep(2000);
				}
				String Event2 = lib1.getExcelData("templates", i, 13, filePath);
				if (Event2 != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ Event2
									+ "')]]]//td[5]/div//select/option[contains(text(),'Events')]"))
							.click();
					Thread.sleep(2000);
				}
				String EndDate = lib1
						.getExcelData("templates", i, 14, filePath);
				if (EndDate != null) {
					driver.findElement(
							By.xpath("//tr[td[1][div[contains(text(),'"
									+ EndDate
									+ "')]]]//td[5]/div//select/option[contains(text(),'Date')]"))
							.click();
					Thread.sleep(2000);
				}
				driver.findElement(
						By.id("reportTemplateDetailForm:saveTemplate")).click();// save
				Thread.sleep(3000);
				String Rule = lib1.getExcelData("templates", i, 15, filePath);
				if (Rule != null && Rule.equals("Y")) {
					String RuleFor = lib1.getExcelData("templates", i, 16,
							filePath);
					String mandatoryField = lib1.getExcelData("templates", i,
							17, filePath);
					if (mandatoryField != null) {
						driver.findElement(
								By.xpath("//tr[td[1][div[contains(text(),'"
										+ RuleFor
										+ "')]]]//td[6]/div/button[1]/span[1]"))
								.click();
						Thread.sleep(2000);
						driver.switchTo().activeElement();
						Thread.sleep(2000);
						driver.findElement(
								By.id("paramRuleForm:mandatoryId_label"))
								.click();
						driver.findElement(
								By.xpath("//div[contains(@id,'paramRuleForm:mandatoryId_panel')]//div/ul/li[contains(text(),'"
										+ mandatoryField + "')]")).click();
						driver.findElement(By.id("paramRuleForm:close"))
								.click();
						Thread.sleep(2000);
					}
					String multiple = lib1.getExcelData("templates", i, 18,
							filePath);
					if (multiple != null) {
						driver.findElement(
								By.xpath("//tr[td[1][div[contains(text(),'"
										+ RuleFor
										+ "')]]]//td[6]/div/button[1]/span[1]"))
								.click();
						Thread.sleep(2000);
						driver.switchTo().activeElement();
						Thread.sleep(2000);
						driver.findElement(
								By.id("paramRuleForm:multiFieldId_label"))
								.click();
						driver.findElement(
								By.xpath("//div[contains(@id,'paramRuleForm:multiFieldId_panel')]//div/ul/li[contains(text(),'"
										+ multiple + "')]")).click();
						driver.findElement(By.id("paramRuleForm:close"))
								.click();
						Thread.sleep(2000);
					}
					String linkedParameter = lib1.getExcelData("templates", i,
							19, filePath);
					if (linkedParameter != null) {
						driver.findElement(
								By.xpath("//tr[td[1][div[contains(text(),'"
										+ RuleFor
										+ "')]]]//td[6]/div/button[1]/span[1]"))
								.click();
						Thread.sleep(2000);
						driver.switchTo().activeElement();
						Thread.sleep(2000);
						driver.findElement(
								By.id("paramRuleForm:linkedParamId_label"))
								.click();
						driver.findElement(
								By.xpath("//div[contains(@id,'paramRuleForm:linkedParamId_panel')]//div/ul/li[contains(text(),'"
										+ linkedParameter + "')]")).click();
						driver.findElement(By.id("paramRuleForm:close"))
								.click();
						Thread.sleep(2000);
					}
					driver.findElement(
							By.id("reportTemplateDetailForm:saveTemplate"))
							.click();// save
					Thread.sleep(2000);
				}
				// driver.findElement(By.id("reportTemplateDetailForm:saveAndCloseTemplate")).click();//save
				// & close
				// driver.findElement(By.id("reportTemplateDetailForm:cancle"));//cancel
				System.out.println("**********************");
				System.out.println("complete template created" + templateName);
				Reporter.log("complete template created" + templateName);
				Thread.sleep(3000);
			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_Template");
			e.printStackTrace();
			Assert.fail("Error in creating template");
		}
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}
}
