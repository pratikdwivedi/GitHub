package com.ms.sapphire.admin;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.FetchProperties;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Admin_Create_EventGroups extends Config {
	@Test
	public void create_event_group() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				driver.findElement(By.linkText(fetchProp.admin)).click();
				driver.findElement(By.linkText("Event groups")).click();
				driver.findElement(
						By.id("eventGroupform:eventGroupGrid:createEventGroup"))
						.click();// create clicked
				String eventGroupName = lib1.getExcelData("eventgroups", i, 0,
						filePath);
				driver.findElement(By.id("eventGroupDetailForm:eventGroupName"))
						.sendKeys(eventGroupName);
				String status = lib1
						.getExcelData("eventgroups", i, 1, filePath);
				driver.findElement(By.id("eventGroupDetailForm:statusId_label"))
						.click();// status clicked
				driver.findElement(
						By.xpath("//div[contains(@id,'eventGroupDetailForm:statusId_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ status + "')]")).click();// status selected
				String type = lib1.getExcelData("eventgroups", i, 2, filePath);
				driver.findElement(By.id("eventGroupDetailForm:grpType_label"))
						.click();// type clicked
				driver.findElement(
						By.xpath("//div[contains(@id,'eventGroupDetailForm:grpType_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ type + "')]")).click();// type selected
				String description = lib1.getExcelData("eventgroups", i, 3,
						filePath);
				if (description != null) {
					driver.findElement(
							By.id("eventGroupDetailForm:description"))
							.sendKeys(description);
				}
				/*
				 * Event group
				 */
				String eventFile = lib1.getExcelData("eventgroups", i, 4,
						filePath);
				if (eventFile != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'eventGroupDetailForm:prodEventGrid:fileUploadBtn')]//div[1]//label"))
							.click();// upload clicked
					Thread.sleep(3000);
					setClipboardData(eventFile);
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					Thread.sleep(2000);
					robot.keyPress(KeyEvent.VK_ENTER);
					robot.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(5000);
				}
				// driver.findElement(By.id("eventGroupDetailForm:saveAndCloseEventGroup")).click();
				driver.findElement(By.id("eventGroupDetailForm:saveEventGroup"))
						.click();
				// driver.findElement(By.id("eventGroupDetailForm:cancle")).click();
				System.out.println("Event group creation complete............"
						+ eventGroupName);
				Reporter.log("Event group creation complete" + eventGroupName);
				Thread.sleep(5000);
			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_EventGroups");
			e.printStackTrace();
			Assert.fail("Error in creation of event groups");
		}
		finally{
			Reporter.log("This test case is to verify if creation of event groups");
			}
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}
}
