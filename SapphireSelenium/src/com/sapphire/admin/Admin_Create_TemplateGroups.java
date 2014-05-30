package com.sapphire.admin;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_Create_TemplateGroups extends Config {
	@Test
	public void TestMethodCreateTemplateGroup() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");

				// create Template Group
				driver.findElement(By.linkText("ADMIN")).click();
				driver.findElement(By.linkText("Templates")).click();
				driver.findElement(By.linkText("Template group")).click();
				String groupName = lib1.getExcelData("templategroup", i, 0,
						filePath);
				driver.findElement(
						By.id("reportTab:templateGroupform:templateGroupGrid:createTemplateGroup"))
						.click();
				driver.findElement(By.id("templateGroupDetailForm:groupnameid"))
						.sendKeys(groupName);
				String datasource = lib1.getExcelData("templategroup", i, 1,
						filePath);
				driver.findElement(
						By.id("templateGroupDetailForm:datasourceId_label"))
						.click();
				Thread.sleep(2000);
				driver.findElement(
						By.xpath("//div[contains(@id,'templateGroupDetailForm:datasourceId_panel')]/div[1]/ul[1]/li[text()='"
								+ datasource + "']")).click();
				Thread.sleep(2000);
				String description = lib1.getExcelData("templategroup", i, 2,
						filePath);
				driver.findElement(
						By.id("templateGroupDetailForm:descriptionId"))
						.sendKeys(description);
				// driver.findElement(By.id("templateGroupDetailForm:cancle")).click();//cancel
				driver.findElement(
						By.id("templateGroupDetailForm:saveTemplateGroupId"))
						.click();// save
				Thread.sleep(3000);
				// driver.findElement(By.id("templateGroupDetailForm:saveCloseTemplateGroupId")).click();//save
				// & close
				System.out
						.println("Template group creation complete............"
								+ groupName);
				Reporter.log("Template group creation complete" + groupName);

			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_TemplateGroups");
			e.printStackTrace();
			Assert.fail("Error in creating template groups");
		}
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}

}
