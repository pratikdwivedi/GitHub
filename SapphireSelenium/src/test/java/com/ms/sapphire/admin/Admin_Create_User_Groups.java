package com.ms.sapphire.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.FetchProperties;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Admin_Create_User_Groups extends Config {
	@Test
	public void create_user_group() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				act = new Actions(driver);
				driver.findElement(By.linkText(fetchProp.admin)).click();
				System.out.println("Admin clicked ");
				driver.findElement(By.linkText("User groups")).click();
				String groupName = lib1.getExcelData("usergroups", i, 0,
						filePath);// group name
				driver.findElement(
						By.id("userGroupform:userGroupGrid:addGroup")).click();
				driver.findElement(
						By.id("userDetailForm:userGroupDetailView:groupName"))
						.sendKeys(groupName);
				String status = lib1.getExcelData("usergroups", i, 1, filePath);// status
				driver.findElement(
						By.id("userDetailForm:userGroupDetailView:statusDropDown_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'userDetailForm:userGroupDetailView:statusDropDown_panel')]/div[1]/ul[1]/li[contains(text(),'"
								+ status + "')]")).click();
				String description = lib1.getExcelData("usergroups", i, 2,
						filePath);// description
				driver.findElement(
						By.id("userDetailForm:userGroupDetailView:descriptionArea"))
						.sendKeys(description);
				String avUser = lib1.getExcelData("usergroups", i, 3, filePath);// available
																				// user
																				// selecting
				driver.findElement(
						By.xpath(" //table[contains(@id,'userDetailForm:userGroupDetailView:userPickList')]/tbody/tr/td[1]/ul/li[contains(text(),'"
								+ avUser + "')]")).click();
				driver.findElement(
						By.xpath("//table[contains(@id,'userDetailForm:userGroupDetailView:userPickList')]/tbody/tr/td[2]/button[1]"))
						.click();
				/*
				 * String avRole =lib1.getExcelData("usergroups", i,
				 * 4,filePath); driver.findElement(By.xpath(
				 * " //table[contains(@id,'userDetailForm:userGroupDetailView:rolePickList')]/tbody/tr/td[1]/ul/li[contains(text(),'"
				 * +avRole+"')]")).click(); driver.findElement(By.xpath(
				 * "//table[contains(@id,'userDetailForm:userGroupDetailView:rolePickList')]/tbody/tr/td[2]/button[1]"
				 * )).click();
				 */

				/*
				 * String productAccess=lib1.getExcelData("usergroups", i,
				 * 5,filePath);//productAccess level driver.findElement(By.id(
				 * "userDetailForm:userGroupDetailView:productLevel_label"
				 * )).click(); driver.findElement(By.xpath(
				 * "//div[contains(@id,'userDetailForm:userGroupDetailView:productLevel_panel')]/div[1]/ul[1]/li[contains(text(),'"
				 * +productAccess+"')]")).click(); driver.findElement(By.id(
				 * "userDetailForm:userGroupDetailView:productGrid:j_idt140"
				 * )).click();//add product driver.switchTo().activeElement();
				 * String addProduct=lib1.getExcelData("usergroups", i,
				 * 6,filePath); driver.findElement(By.id(
				 * "productLookupForm:productLookupGrid:srcTxtId"
				 * )).sendKeys(addProduct); driver.findElement(By.id(
				 * "productLookupForm:productLookupGrid:j_idt190")).click();
				 * Thread.sleep(5000); driver.findElement(By.xpath(
				 * "//th[contains(@id,'productLookupForm:productLookupGrid:j_idt198')]//div[1]/div[1]/div[2]"
				 * )).click();
				 * driver.findElement(By.id("productLookupForm:selectProduct"
				 * )).click();
				 */
				// driver.findElement(By.id("userDetailForm:saveCloseGroup")).click();//save&close
				driver.findElement(By.id("userDetailForm:saveGroup")).click();// save
				Thread.sleep(5000);
				driver.findElement(By.linkText("Privileges")).click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'userDetailForm:userGroupDetailView:privilegeTabview:formPrivilegeTree_node_0')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'userDetailForm:userGroupDetailView:privilegeTabview:formPrivilegeTree_node_1')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				driver.findElement(
						By.xpath("//tr[contains(@id,'userDetailForm:userGroupDetailView:privilegeTabview:formPrivilegeTree_node_2')]/td[2]/div[1]/div[1]/div[2]"))
						.click();
				Thread.sleep(5000);
				System.out.println("Privileges has been selected");
				driver.findElement(
						By.xpath("//form[contains(@id,'userDetailForm')]/div[2]/button[2]"))
						.click();// save
				driver.findElement(
						By.xpath("//form[contains(@id,'userDetailForm')]/div[2]/button[1]"))
						.click();// save&close
				// WebElement
				// text=driver.findElement(By.xpath("//div[contains(@class,'ui-growl ui-widget')]/div[1]/div[1]/div[2]/p[1]"));
				// System.out.println(text.getText());//text of alert message
				// driver.findElement(By.id("userDetailForm:j_idt173")).click();//cancel
				// driver.findElement(By.id("userDetailForm:saveCloseGroup")).click();//save&close
				driver.findElement(By.id("userDetailForm:saveGroup")).click();// save
				Reporter.log("User Group created " + groupName);
				Thread.sleep(10000);

			}// for close
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_User_Groups");
			e.printStackTrace();
			Assert.fail("Error in creation of user group ");

		}
		finally{
		Reporter.log("This test case is to verify if creation of user group");
		}
	}
}
