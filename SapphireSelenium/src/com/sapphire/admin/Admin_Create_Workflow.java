package com.sapphire.admin;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.*;

import com.test.*;

public class Admin_Create_Workflow extends Config {

	@Test
	public void TestMethodCreateWorkflow() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText("ADMIN")).click();
				driver.findElement(By.linkText("Workflows")).click();
				driver.findElement(
						By.id("workflowform:workflowGrid:createWorkflow"))
						.click();
				String workflowName = lib1.getExcelData("workflow", i, 0,
						filePath);
				driver.findElement(By.id("workflowDetailForm:nameid"))
						.sendKeys(workflowName);
				String workflowType = lib1.getExcelData("workflow", i, 1,
						filePath);
				driver.findElement(
						By.id("workflowDetailForm:workflowTypeId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'workflowDetailForm:workflowTypeId_panel')]//div[1]/ul[1]/li[contains(text(),'"
								+ workflowType + "')]")).click();
				String workflowDesc = lib1.getExcelData("workflow", i, 2,
						filePath);
				driver.findElement(By.id("workflowDetailForm:descriptionId"))
						.sendKeys(workflowDesc);
				driver.findElement(
						By.xpath("//div[contains(@id,'workflowDetailForm:savepnl')]//div[1][contains(@id,'workflowDetailForm:simpleid')]"))
						.click();
				String workflowFile = lib1.getExcelData("workflow", i, 3,
						filePath);
				Thread.sleep(3000);
				setClipboardData(workflowFile);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(2000);
				// driver.findElement(By.id("workflowDetailForm:saveAndCloseWorkflow")).click();//save&close
				driver.findElement(By.id("workflowDetailForm:saveWorkflow"))
						.click();// save
				// driver.findElement(By.id("workflowDetailForm:cancle")).click();//cancel
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//div[contains(@id,'workflowDetailForm:startPauseId')]//div[1]/span[1]"))
						.click();// start
				// driver.findElement(By.xpath("//div[contains(@id,'workflowDetailForm:startPauseId')]//div[2]/span[1]")).click();//stop
				Thread.sleep(4000);
				driver.switchTo().activeElement();
				// driver.findElement(By.id("operateConfirmForm:cancelRemove")).click();//cancel
				// popup
				driver.findElement(By.id("operateConfirmForm:confirmRemove"))
						.click();// confirm popup
				Thread.sleep(4000);
				// driver.findElement(By.id("workflowDetailForm:cancle")).click();//cancel
				// workflow create page
				System.out.println("complete workflow created");
				Reporter.log("complete workflow created");
				Thread.sleep(5000);

			}// for close
		}// try close
		catch (Exception e) {
			takeScreenShot(e, "Admin_Create_Workflow");
			e.printStackTrace();
			Assert.fail("Error in creating workflow");

		}
	}
}