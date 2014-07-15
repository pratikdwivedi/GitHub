package com.ms.sapphire.admin;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.FetchProperties;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Admin_Create_ProductGroups extends Config {
	@Test
	public void create_product_groups() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.admin)).click();
				System.out.println("Admin clicked ");
				driver.findElement(By.linkText("Products")).click();
				Thread.sleep(2000);
				driver.findElement(By.linkText("Product groups")).click();
				Thread.sleep(5000);
				System.out.println("Product Group clicked");
				driver.findElement(
						By.id("productGroupform:productGroupGrid:addPrgGroupField"))
						.click();// create
				String producGrouptName = lib1.getExcelData("productgroups", i,
						0, filePath);// group
				driver.findElement(
						By.id("productGroupDetailForm:productGroupName"))
						.sendKeys(producGrouptName);
				String dictionary = lib1.getExcelData("productgroups", i, 1,
						filePath);// dict
				driver.findElement(
						By.id("productGroupDetailForm:dictTypeId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'productGroupDetailForm:dictTypeId_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ dictionary + "')]")).click();
				String level = lib1.getExcelData("productgroups", i, 2,
						filePath);// level
				driver.findElement(
						By.id("productGroupDetailForm:levelId_label")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'productGroupDetailForm:levelId_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ level + "')]")).click();
				String status = lib1.getExcelData("productgroups", i, 3,
						filePath);// status
				driver.findElement(
						By.id("productGroupDetailForm:statusId_label")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'productGroupDetailForm:statusId_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ status + "')]")).click();// status selected
				String type = lib1
						.getExcelData("productgroups", i, 4, filePath);// type
				driver.findElement(
						By.id("productGroupDetailForm:grpType_label")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'productGroupDetailForm:grpType_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ type + "')]")).click();// type selected
				String description = lib1.getExcelData("productgroups", i, 5,
						filePath);
				driver.findElement(By.id("productGroupDetailForm:description"))
						.sendKeys(description);
				/*
				 * product group file 
				 */
				String productGroupFile = lib1.getExcelData("productgroups", i,
						6, filePath);
				driver.findElement(
						By.xpath("//div[contains(@id,'productGroupDetailForm:prodProductGrid:fileUploadBtn')]//div[1]//label"))
						.click();
				Thread.sleep(3000);
				setClipboardData(productGroupFile);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				System.out.println("Complete");
				Thread.sleep(5000);
				// driver.findElement(By.id("productGroupDetailForm:saveAndCloseProductGroup")).click();//save
				// & close
				driver.findElement(
						By.id("productGroupDetailForm:saveProductGroup"))
						.click();// save
				// driver.findElement(By.id("productGroupDetailForm:cancle")).click();//cancel
				System.out
						.println("Product Group creation complete............"
								+ producGrouptName);
				Reporter.log("Product Group created " + producGrouptName);
				Thread.sleep(5000);
			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_ProductGroups");
			e.printStackTrace();
			Assert.fail("Error in creation of product group");
		}finally{
			Reporter.log("This test case is to verify if creation of product group");
			}
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}
}
