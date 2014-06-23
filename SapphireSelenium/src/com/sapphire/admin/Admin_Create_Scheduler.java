package com.sapphire.admin;

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

public class Admin_Create_Scheduler extends Config {
	@Test(description = "Creating Scheduler")
	public void TestMethodCreateScheduler() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.admin)).click();
				driver.findElement(By.linkText("Schedulers")).click();
				String schedulerName = lib1.getExcelData("schedulers", i, 0,
						filePath);
				driver.findElement(
						By.id("schedulerform:schedulerGrid:createScheduler"))
						.click();// create
				driver.findElement(By.id("schedulerDetailForm:nameid"))
						.sendKeys(schedulerName);
				String schedulerType = lib1.getExcelData("schedulers", i, 1,
						filePath);
				driver.findElement(
						By.id("schedulerDetailForm:schedulerTypeId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'schedulerDetailForm:schedulerTypeId_panel')]//div[1]/ul[1]/li[contains(text(),'"
								+ schedulerType + "')]")).click();// scheduler
																	// type
				String schedulerFrequency = lib1.getExcelData("schedulers", i,
						2, filePath);
				driver.findElement(
						By.id("schedulerDetailForm:freqTypeId_label")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'schedulerDetailForm:freqTypeId_panel')]//div[1]/ul[1]/li[contains(text(),'"
								+ schedulerFrequency + "')]")).click();// select
																		// frequency
				if (schedulerFrequency.equals("In minutes")) {
					String minuteDuration = lib1.getExcelData("schedulers", i,
							3, filePath);
					driver.findElement(
							By.id("schedulerDetailForm:everyMinute_input"))
							.clear();
					driver.findElement(
							By.id("schedulerDetailForm:everyMinute_input"))
							.sendKeys(minuteDuration);
				} else if (schedulerFrequency.equals("Daily")) {
					String dailyDailyfrequency = lib1.getExcelData(
							"schedulers", i, 4, filePath);
					if (dailyDailyfrequency.equals("Daily")) {
						driver.findElement(
								By.id("schedulerDetailForm:customRadio:0"))
								.click();
					}
					String weekDaysFrequency = lib1.getExcelData("schedulers",
							i, 5, filePath);
					if (weekDaysFrequency.equals(" Weekdays only")) {
						driver.findElement(
								By.id("schedulerDetailForm:customRadio:1"))
								.click();

					}

					String everyDayDuration = lib1.getExcelData("schedulers",
							i, 7, filePath);
					if (everyDayDuration.equals("Every")) {
						driver.findElement(
								By.id("schedulerDetailForm:customRadio:2"))
								.click();
						driver.findElement(
								By.id("schedulerDetailForm:repNameId")).clear();
						driver.findElement(
								By.id("schedulerDetailForm:repNameId"))
								.sendKeys(everyDayDuration);
					}
				}

				driver.findElement(
						By.id("schedulerDetailForm:startDateId_input")).clear();
				String startTime = lib1.getExcelData("schedulers", i, 13,
						filePath);
				driver.findElement(
						By.id("schedulerDetailForm:startDateId_input"))
						.sendKeys(startTime);

				// driver.findElement(By.id("ui_tpicker_time_label_schedulerDetailFormstartDateId_input")).click();
				// driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-buttonpane ui-widget-content')]//button[1]")).click();//Time
				// now
				// driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-buttonpane ui-widget-content')]//button[2]")).click();//Done
				// Thread.sleep(3000);

				driver.findElement(By.id("schedulerDetailForm:endDateId_input"))
						.clear();
				String endtTime = lib1.getExcelData("schedulers", i, 14,
						filePath);
				driver.findElement(By.id("schedulerDetailForm:endDateId_input"))
						.sendKeys(endtTime);
				// driver.findElement(By.id("ui_tpicker_time_label_schedulerDetailFormendDateId_input")).click();
				// driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-buttonpane ui-widget-content')]//button[1]")).click();//Time
				// now
				// driver.findElement(By.xpath("//div[contains(@class,'ui-datepicker-buttonpane ui-widget-content')]//button[2]")).click();//Done
				Thread.sleep(3000);
				String description = lib1.getExcelData("schedulers", i, 15,
						filePath);
				driver.findElement(By.id("schedulerDetailForm:descriptionId"))
						.sendKeys(description);
				// driver.findElement(By.id("schedulerDetailForm:saveAndCloseUser")).click();//save&close
				Thread.sleep(3000);
				driver.findElement(By.id("schedulerDetailForm:saveProduct"))
						.click();// save
				Thread.sleep(3000);
				// driver.findElement(By.id("schedulerDetailForm:cancle")).click();//cancel
				/*
				 * driver.findElement(By.id("schedulerDetailForm:startSchedulerBtn"
				 * )).click();//start Thread.sleep(4000);
				 * driver.switchTo().activeElement();
				 * driver.findElement(By.id("confirmStartForm:confirmStart"
				 * )).click(); Thread.sleep(7000);
				 */
				driver.findElement(
						By.id("schedulerDetailForm:saveAndCloseUser")).click();// save&close
				System.out.println("Scheduler creation complete............"
						+ schedulerName);
				Reporter.log("Scheduler creation complete... " + schedulerName);
				Thread.sleep(5000);
			}// for close
		} catch (Throwable e) {
			takeScreenShot(e, "Admin_Create_Scheduler");
			e.printStackTrace();
			Assert.fail("Error in creating scheduler..");

		}
	}
}
