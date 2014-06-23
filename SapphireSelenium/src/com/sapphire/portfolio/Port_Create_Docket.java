package com.sapphire.portfolio;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.common.FetchProperties;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Create_Docket extends Config {
	@Test
	public void TestMethodPortCreateDocket() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.PORTFOLIO);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Thread.sleep(10000);
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.portfolio)).click();
				Thread.sleep(3000);
				List<WebElement> listMaster = driver
						.findElements(By
								.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				/*
				 * Iterator itr1 = listMaster.iterator(); while(itr1.hasNext()){
				 * String masterLink =((WebElement) itr1.next()).getText();
				 * System.out.println(masterLink);
				 * D:\DemoDataArtifacts\Docket\Labelling doc - Azacitidine
				 * (Vidaza).pdf }//while close
				 */
				String masterName = lib1.getExcelData("docket", i, 0, filePath);
				for (WebElement we : listMaster) {
					System.out.println(we.getText());
					if (we.getText().contentEquals(masterName)) {
						String link = we.getText();
						System.out.println("Clicked LInk" + link);
						we.click();
						System.out.println("clicked");
						break;
					}
				}
				Thread.sleep(4000);
				driver.findElement(
						By.xpath(" //div[contains(@id,'leftNavPanel_content')]//ul/li/a/span[contains(text(),'Setup')]"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView')]//ul/li[2]/a"))
						.click();// Docket clicked
				driver.findElement(
						By.xpath("//div[contains(@id,'tabView:masterArtifactListForm:masterFileGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
						.click();
				driver.switchTo().activeElement();
				String docketName = lib1.getExcelData("docket", i, 1, filePath);
				driver.findElement(By.id("artifactDetailForm:tabView:name"))
						.sendKeys(docketName);
				String docketType = lib1.getExcelData("docket", i, 2, filePath);
				driver.findElement(
						By.id("artifactDetailForm:tabView:artifactType_label"))
						.click();
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//div[contains(@id,'artifactDetailForm:tabView:artifactType_panel')]//ul[1]/li[text()='"
								+ docketType + "']")).click();
				String department = lib1.getExcelData("docket", i, 3, filePath);
				driver.findElement(
						By.id("artifactDetailForm:tabView:artifactStatus_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'artifactDetailForm:tabView:artifactStatus_panel')]//ul[1]/li[text()='"
								+ department + "']")).click();
				String docketOwner = lib1
						.getExcelData("docket", i, 4, filePath);
				if (docketOwner != null) {
					driver.findElement(
							By.id("artifactDetailForm:tabView:lookupUser"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(
							By.xpath("//div[contains(@id,'userLookupForm:userLookupGrid')]//table[1]/thead[1]/tr[1]/th[1]/div[1]/div[1]/span[1]/input[1]"))
							.sendKeys(docketOwner);
					Thread.sleep(2000);
					driver.findElement(
							By.xpath("//div[contains(@id,'userLookupForm:userLookupGrid')]//table[1]/thead[1]/tr[1]/th[1]/div[1]/div[1]/span[1]/button[1]"))
							.click();
					WebElement searchedUser = driver
							.findElement(By
									.xpath("//tbody[contains(@id,'userLookupForm:userLookupGrid_data')]//tr[1]/td[2]/div[1]"));
					String getTextRecord = searchedUser.getText();
					// System.out.println("Get record of user is : "+getTextRecord);
					Thread.sleep(2000);
					driver.findElement(
							By.xpath("//tbody[contains(@id,'userLookupForm:userLookupGrid_data')]//tr[1]/td[1]/div/div/div[2]"))
							.click();
					Thread.sleep(3000);
					driver.findElement(By.id("userLookupForm:selectUser"))
							.click();
				}

				String extLink = lib1.getExcelData("docket", i, 5, filePath);
				if (extLink != null) {
					driver.findElement(
							By.id("artifactDetailForm:tabView:docLink"))
							.sendKeys(extLink);
				}
				String revDate = lib1.getExcelData("docket", i, 6, filePath);
				if (revDate != null) {
					driver.findElement(
							By.id("artifactDetailForm:tabView:revDate_input"))
							.clear();
					driver.findElement(
							By.id("artifactDetailForm:tabView:revDate_input"))
							.sendKeys(revDate);
					driver.findElement(
							By.xpath("//div[contains(@id,'ui-datepicker-div')]//div[3]/button[2]"))
							.click();
				}
				String desc = lib1.getExcelData("docket", i, 7, filePath);
				driver.findElement(
						By.id("artifactDetailForm:tabView:description"))
						.sendKeys(desc);
				String docketUpload = lib1.getExcelData("docket", i, 8,
						filePath);
				driver.findElement(
						By.xpath("//div[contains(@id,'artifactDetailForm:tabView:generalTab')]//div[1]/div[1]/div[1][contains(@id,'artifactDetailForm:tabView:uploadNewAtrId')]"))
						.click();
				Thread.sleep(3000);
				setClipboardData(docketUpload);
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
				driver.findElement(By.id("artifactDetailForm:saveArtifact"))
						.click();
				Thread.sleep(10000);
				System.out
						.println("//////////////////////////////////////////////////");
			}// for close
		}// try close
		catch (Throwable e) {
			e.printStackTrace();
			Assert.fail("Error in creating Docket ");
		}
	}
}
