package com.ms.sapphire.admin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Admin_Delete_User_Groups extends Config {
	@Test
	public void delete_user_group() throws Exception {
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
				driver.findElement(By.linkText("User groups")).click();
				String userGroupdelete = lib1.getExcelData("userGroupDelete",
						i, 0, filePath);
				driver.findElement(
						By.xpath("//tr[td[3][div[a[text()='" + userGroupdelete
								+ "']]]]//td[1]//div[2]")).click();
				driver.findElement(
						By.id("userGroupform:userGroupGrid:deleteGroup"))
						.click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("removeConfirmForm:confirmRemove"))
						.click();
				Thread.sleep(5000);
			}
		}// try
		catch (Throwable e) {
			takeScreenShot(e, "Admin_Delete_User_Group");
			e.printStackTrace();
			Assert.fail("Error in deleteing user Group ");

		}
	}
}
