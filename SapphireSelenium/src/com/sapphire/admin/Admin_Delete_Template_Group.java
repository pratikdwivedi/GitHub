package com.sapphire.admin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_Delete_Template_Group extends Config {
	@Test
	public void deleteTemplateGroup() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN_DELETE);
		try {
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(
						By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'ADMIN')]"))
						.click();
				System.out.println("Admin clicked ");
				driver.findElement(By.linkText("Templates")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'reportTab')]//ul/li[3]/a"))
						.click();
				String groupName = lib1.getExcelData("deleteTemplateGroup", i,
						0, filePath);
				driver.findElement(
						By.xpath("//tr[td[2][div[a[contains(text(),'"
								+ groupName + "')]]]]//td[1]//div[2]")).click();
				driver.findElement(
						By.id("reportTab:templateGroupform:templateGroupGrid:deleteGroup"))
						.click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("removeConfirmForm:confirmRemove"))
						.click();
				Thread.sleep(5000);
			}
		}// try
		catch (Throwable e) {
			takeScreenShot(e, "Admin_Delete_template_Group");
			e.printStackTrace();
			Assert.fail("Error in deleteing template group ");

		}
	}
}
