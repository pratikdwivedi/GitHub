package com.ms.sapphire.admin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Admin_WorkflowPrivileges extends Config {

	@Test
	public void workflow_privileges() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText("ADMIN")).click();
				System.out.println("Admin clicked ");
				String userid = lib1.getExcelData("workflowPrivileges", i, 0,
						filePath);
				driver.findElement(
						By.xpath("//tbody[contains(@id,'form:userGrid_data')]//tr/td[3]/div/a[contains(text(),'"
								+ userid + "')]")).click();
				driver.findElement(By.linkText("Privileges")).click();
				Thread.sleep(2000);
				// Workflow
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview')]//ul/li/a[contains(text(),'Workflow')]"))
						.click();
				String wfActivity = lib1.getExcelData("workflowPrivileges", i,
						2, filePath);
				driver.findElement(
						By.xpath("//tr[td[1][div[contains(text(),'"
								+ wfActivity + "')]]]//td[2]/div/div/div[2]"))
						.click();
				// driver.findElement(By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:activityPrivilegeTree')]//table/tbody/tr[1]/td[2]/div/div/div[2]")).click();//all
				// privileges of workflow
				Thread.sleep(2000);
				driver.findElement(By.id("userDetailForm:saveUser")).click();
				Thread.sleep(5000);
				// Dashboard
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview')]//ul/li/a[contains(text(),'Dashboard')]"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:dashboardPrivilegeTree')]//table/tbody/tr[1]/td[2]/div/div/div[2]"))
						.click();
				Thread.sleep(2000);
				// Data Source
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview')]//ul/li/a[contains(text(),'Data Sources')]"))
						.click();
				String dataSources = lib1.getExcelData("workflowPrivileges", i,
						3, filePath);
				driver.findElement(
						By.xpath("//tr[td[1][div[text()='" + dataSources
								+ "']]]//td[2]/div/div/div[2]")).click();
				Thread.sleep(2000);
				// Reports
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview')]//ul/li/a[contains(text(),'Reports')]"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:templatePrivilegeTree')]//table/tbody/tr[1]/td[2]/div/div/div[2]"))
						.click();
				driver.findElement(By.id("userDetailForm:saveAndCloseUser"))
						.click();
				Thread.sleep(5000);
			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_WorkflowPrivileges");
			e.printStackTrace();
			Assert.fail("Error in workflow privileges  ");

		}
	}
}
