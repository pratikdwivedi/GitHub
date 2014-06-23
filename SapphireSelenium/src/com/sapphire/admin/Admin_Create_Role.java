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

public class Admin_Create_Role extends Config {
	@Test
	public void TestMethodCreateRole() throws Exception {
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
				driver.findElement(By.linkText("Roles")).click();
				Thread.sleep(2000);
				String roleName = lib1.getExcelData("roles", i, 0, filePath);
				driver.findElement(By.id("roleform:roleGrid:createRole"))
						.click();
				driver.findElement(By.id("roleDetailForm:roleTabView:nameid"))
						.sendKeys(roleName);
				String description = lib1.getExcelData("roles", i, 1, filePath);
				driver.findElement(
						By.id("roleDetailForm:roleTabView:descriptionId"))
						.sendKeys(description);
				String avUser = lib1.getExcelData("roles", i, 2, filePath);
				driver.findElement(
						By.xpath("//table[contains(@id,'roleDetailForm:roleTabView:pickList')]/tbody/tr[1]/td[1]/ul/li[contains(text(),'"
								+ avUser + "')]")).click();
				driver.findElement(
						By.xpath("//table[contains(@id,'roleDetailForm:roleTabView:pickList')]/tbody/tr[1]/td[2]/button[1]"))
						.click();
				String avGroup = lib1.getExcelData("roles", i, 3, filePath);
				driver.findElement(
						By.xpath("//table[contains(@id,'roleDetailForm:roleTabView:userGroupPickList')]/tbody/tr[1]/td[1]/ul/li[contains(text(),'"
								+ avGroup + "')]")).click();
				driver.findElement(
						By.xpath("//table[contains(@id,'roleDetailForm:roleTabView:userGroupPickList')]/tbody/tr[1]/td[2]/button[1]"))
						.click();
				Thread.sleep(3000);
				// driver.findElement(By.id("roleDetailForm:saveAndCloseUser")).click();//save&close
				driver.findElement(By.id("roleDetailForm:saveProduct")).click();// save
				// driver.findElement(By.id("roleDetailForm:cancle")).click();//cancel
				// Privileges
				driver.findElement(By.linkText("Privileges")).click();
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_0')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_1')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_2')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_3')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_4')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_5')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'roleDetailForm:roleTabView:privilegeTabview:formPrivilegeTree_node_6')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);

				// driver.findElement(By.id("roleDetailForm:saveAndCloseUser")).click();//save&close
				driver.findElement(By.id("roleDetailForm:saveProduct")).click();// save
				// driver.findElement(By.id("roleDetailForm:cancle")).click();
				System.out.println("Role created successfully");
				Reporter.log("Role created : " + roleName);
				Thread.sleep(10000);

			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_Role");
			e.printStackTrace();
			Assert.fail("Error in creating role");

		}

	}
}
