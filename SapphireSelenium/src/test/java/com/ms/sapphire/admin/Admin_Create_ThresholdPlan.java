package com.ms.sapphire.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.FetchProperties;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Admin_Create_ThresholdPlan extends Config {
	int i;
	String pname;
	String des;
	String nValues;
	String expectedValues;

	// public int rowCount1=1;
	@Test
	public void threshold_plan() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.admin)).click();
				driver.findElement(By.linkText("Threshold plan")).click();
				driver.findElement(
						By.id("tresHoldMatrixform:tresHoldMatrixGrid:createTreshHoldMatrix"))
						.click();// create clicked
				pname = lib1.getExcelData("thresholdplan", i, 0, filePath);
				des = lib1.getExcelData("thresholdplan", i, 1, filePath);
				WebElement elementPname = driver.findElement(By
						.id("tresHoldMatrixDetailForm:tresHoldMatrixName"));
				elementPname.sendKeys(pname);
				System.out.println(pname);
				WebElement elementDes = driver.findElement(By
						.id("tresHoldMatrixDetailForm:descriptionId"));
				elementDes.sendKeys(des);
				System.out.println(des);
				driver.findElement(
						By.id("tresHoldMatrixDetailForm:A_optr_label")).click();
				driver.findElement(
						By.xpath("//div[contains(@id,'tresHoldMatrixDetailForm:A_optr_panel')]//div[1]/ul[1]/li[4]"))
						.click();// dropdown select
				driver.findElement(By.id("tresHoldMatrixDetailForm:A_VAL_ID"))
						.sendKeys("3");
				driver.findElement(
						By.xpath("//table[contains(@id,'tresHoldMatrixDetailForm:algoPanelParent')]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[5]/td[1]/div[1]/div[2]"))
						.click();// prr marked
				Thread.sleep(2000);
				driver.findElement(
						By.id("tresHoldMatrixDetailForm:prr_optr_label"))
						.click();// dropdown clicked
				driver.findElement(
						By.xpath("//div[contains(@id,'tresHoldMatrixDetailForm:prr_optr')]//div[1]/ul[1]/li[3]"))
						.click();// dropdown select
				driver.findElement(By.id("tresHoldMatrixDetailForm:prr_VAL_ID"))
						.sendKeys("2");
				driver.findElement(
						By.xpath("//table[contains(@id,'tresHoldMatrixDetailForm:algoPanelParent')]/tbody[1]/tr[1]/td[2]/table[1]/tbody[1]/tr[4]/td[1]/div[1]/div[2]"))
						.click(); // prr marked
				driver.findElement(
						By.id("tresHoldMatrixDetailForm:chi_sqr_ABCD_optr_label"))
						.click();// dropdown clicked
				driver.findElement(
						By.xpath("//div[contains(@id,'tresHoldMatrixDetailForm:chi_sqr_ABCD_optr_panel')]//div[1]/ul[1]/li[3]"))
						.click();// dropdown select
				driver.findElement(
						By.id("tresHoldMatrixDetailForm:chi_sqr_ABCD_VAL_ID"))
						.sendKeys("4");
				System.out.println("Complete Threshold plan" + pname);
				Thread.sleep(5000);
				/*
				 * driver.findElement(By.id(
				 * "tresHoldMatrixform:tresHoldMatrixGrid:createTreshHoldMatrix"
				 * )).click();//create clicked driver.findElement(By.id(
				 * "tresHoldMatrixDetailForm:tresHoldMatrixName"
				 * )).sendKeys("TestThresholdPlan");
				 * nValues=lib1.getExcelData("thresholdplan", i, 2,filePath);
				 * expectedValues=lib1.getExcelData("thresholdplan", i,
				 * 3,filePath); System.out.println(nValues);
				 * System.out.println(expectedValues);
				 */
				// driver.findElement(By.id("tresHoldMatrixDetailForm:saveAndCloseUser")).click();//save
				// & close
				driver.findElement(
						By.id("tresHoldMatrixDetailForm:saveTreshHoldMatrix"))
						.click();// save
				// driver.findElement(By.id("tresHoldMatrixDetailForm:cancle")).click();//cancel
				System.out
						.println("Threshold Plan creation complete............"
								+ pname);
				Reporter.log("Threshold Plan creation complete..........."
						+ pname);

				Thread.sleep(5000);

			}
		} catch (Exception e) {
			takeScreenShot(e, "Admin_Create_ThresholdPlan");
			e.printStackTrace();
			Assert.fail("Error in creation of threshold plan..");

		}finally{
			Reporter.log("This test case is to verify creation of threshold plan");
		}
	}
}
