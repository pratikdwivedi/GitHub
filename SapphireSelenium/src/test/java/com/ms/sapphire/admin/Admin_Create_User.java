package com.ms.sapphire.admin;

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

public class Admin_Create_User extends Config {
	@Test
	public void create_user() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(
						By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'"+fetchProp.admin+"')]"))
						.click();
				System.out.println("Admin clicked ");
				String userID = lib1.getExcelData("users", i, 0, filePath);
				if (userID != null) {
					driver.findElement(By.id("form:userGrid:createUser"))
							.click();
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:userId"))
							.sendKeys(userID);
					String password = lib1
							.getExcelData("users", i, 1, filePath);
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:password"))
							.sendKeys(password);
					Thread.sleep(5000);
					// String type=lib1.getExcelData("users",i,2,filePath);
					// driver.findElement(By.id("userDetailForm:userDetailTabView:userType_label")).click();
					// driver.findElement(By.xpath("//div[contains(@id,'userDetailForm:userDetailTabView:userType_panel')]//div[1]/ul[1]/li[text()='"+type+"']")).click();
					String fname = lib1.getExcelData("users", i, 3, filePath);
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:firstName"))
							.sendKeys(fname);
					String mname = lib1.getExcelData("users", i, 4, filePath);
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:middleName"))
							.sendKeys(mname);
					String lname = lib1.getExcelData("users", i, 5, filePath);
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:lastName"))
							.sendKeys(lname);
					String description = lib1.getExcelData("users", i, 6,
							filePath);
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:description"))
							.sendKeys(description);
					String phoneNumber = lib1.getExcelData("users", i, 7,
							filePath);
					int phoneNumberInt = new Double(phoneNumber).intValue();
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:phoneNumber"))
							.sendKeys("" + phoneNumberInt);
					String email = lib1.getExcelData("users", i, 8, filePath);
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:emailId"))
							.sendKeys(email);
					String fax = lib1.getExcelData("users", i, 9, filePath);
					int faxInt = new Double(fax).intValue();
					driver.findElement(
							By.id("userDetailForm:userDetailTabView:faxNumber"))
							.sendKeys("" + faxInt);
					/*
					 * String avGroup=lib1.getExcelData("users", i,12,filePath);
					 * if(avGroup!=null) { driver.findElement(By.xpath(
					 * "//table[contains(@id,'userDetailForm:userDetailTabView:userPickList')]/tbody/tr[1]/td[1]/ul/li[contains(text(),'"
					 * +avGroup+"')]")).click(); driver.findElement(By.xpath(
					 * "//table[contains(@id,'userDetailForm:userDetailTabView:userPickList')]/tbody/tr[1]/td[2]/button[1]"
					 * )).click(); } String avRole=lib1.getExcelData("users",
					 * i,13,filePath); if(avRole!=null) {
					 * driver.findElement(By.xpath(
					 * "//table[contains(@id,'userDetailForm:userDetailTabView:rolePickList')]/tbody/tr[1]/td[1]/ul/li[contains(text(),'"
					 * +avRole+"')]")).click(); driver.findElement(By.xpath(
					 * "//table[contains(@id,'userDetailForm:userDetailTabView:rolePickList')]/tbody/tr[1]/td[2]/button[1]"
					 * )).click(); }
					 */
					// driver.findElement(By.id("userDetailForm:saveAndCloseUser")).click();//save
					// & close
					driver.findElement(By.id("userDetailForm:saveUser"))
							.click();// save
					// driver.findElement(By.id("userDetailForm:cancle")).click();//cancel
					Thread.sleep(10000);
					ExcelLibrary libUserCount = new ExcelLibrary();
					for (int userCount = 0; userCount <= libUserCount.rowCount; userCount++) {
						String userTotal = libUserCount.getExcelData("users",
								userCount, 0, filePath);
					}
					for (int j = 0; j <= libUserCount.rowCount;) {
						for (int rowModule = 1; rowModule <= lib1.rowCount; rowModule++) {
							driver.findElement(By.linkText("Privileges"))
									.click();
							String appPrivileges = lib1.getExcelData(
									"userPrivileges", rowModule, i, filePath);
							if (appPrivileges.equals("HOME")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_0')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// dashboard
								Thread.sleep(5000);
							} else if (appPrivileges.equals("WORKSPACE")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_1')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// workspace
								Thread.sleep(5000);
							} else if (appPrivileges.equals("INBOX")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_2')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// inbox
								Thread.sleep(5000);
							} else if (appPrivileges.equals("PORTFOLIO")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_3')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// portfolio
								Thread.sleep(5000);
							} else if (appPrivileges.equals("ADMIN")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_4')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// admin
								Thread.sleep(5000);
							} else if (appPrivileges.equals("REPORTS")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_5')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// report
								Thread.sleep(5000);
							} else if (appPrivileges.equals("ANNOTATION")) {
								driver.findElement(
										By.xpath("//tr[contains(@id,'userDetailForm:userDetailTabView:privilegeTabview:formPrivilegeTree_node_6')]/td[2]/div[1]/div[1]/div[2]"))
										.click();// annotation
								Thread.sleep(5000);
							}
							// driver.findElement(By.id("userDetailForm:saveAndCloseUser")).click();//save
							// & close
							driver.findElement(By.id("userDetailForm:saveUser"))
									.click();// save
							// driver.findElement(By.id("userDetailForm:cancle")).click();//cancel
							Thread.sleep(5000);
						}
						j++;
						break;
					}
					System.out.println("user creation complete............"
							+ userID);
					Reporter.log("User created " + userID);
				}
			}// for close
		} // catch (Exception e) {e.printStackTrace();}
		catch (Throwable e) {
			takeScreenShot(e, "Admin_Create_User");
			e.printStackTrace();
			Assert.fail("Error in creation of user ");

		}
		finally{
			Reporter.log("This test case is to verify if creation of user");
		}
	}
}
