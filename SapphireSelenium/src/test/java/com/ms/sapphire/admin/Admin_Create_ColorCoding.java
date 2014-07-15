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

public class Admin_Create_ColorCoding extends Config {
	@Test
	public void create_color_coding() throws Exception {
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
				driver.findElement(By.linkText("Color coding")).click();
				String algo = lib1.getExcelData("colorCoding", i, 0, filePath);
				if (algo != null) {
					driver.findElement(
							By.xpath("//div[contains(@id,'statAlgoListForm:algoParamTable')]//button[1]/span"))
							.click();
					Thread.sleep(2000);
					driver.switchTo().activeElement();
					String lowerOperator = lib1.getExcelData("colorCoding", i,
							1, filePath);
					driver.findElement(
							By.xpath("//td[span[contains(text(),'Lower operator')]]//div/label[1]"))
							.click();
					driver.findElement(
							By.xpath("//div/div/ul/li[contains(text(),'"
									+ lowerOperator + "')]")).click();
					String lowerLimit = lib1.getExcelData("colorCoding", i, 2,
							filePath);
					driver.findElement(
							By.xpath("//td[span[contains(text(),'Lower limit')]]//input"))
							.sendKeys(lowerLimit);
					String upperOperator = lib1.getExcelData("colorCoding", i,
							3, filePath);
					driver.findElement(
							By.xpath("//td[span[contains(text(),'Upper operator')]]//div/label[1]"))
							.click();
					driver.findElement(
							By.xpath("//div/div/ul/li[contains(text(),'"
									+ upperOperator + "')]")).click();
					String upperLimit = lib1.getExcelData("colorCoding", i, 4,
							filePath);
					driver.findElement(
							By.xpath("//td[span[contains(text(),'Upper limit')]]//input"))
							.sendKeys(upperLimit);

					driver.findElement(
							By.xpath(" //span[contains(@id,'paramForm:bgColorPick')]/button[1]/span"))
							.click();
					Thread.sleep(2000);
					driver.switchTo().activeElement();
					driver.findElement(
							By.xpath("//div[contains(@class,'ui-colorpicker_color')]//div[1]/div"))
							.click();
					driver.findElement(
							By.xpath(" //span[contains(@id,'paramForm:bgColorPick')]/button[1]/span"))
							.click();
					Thread.sleep(3000);
					driver.findElement(
							By.xpath("//span[contains(@id,'paramForm:fgColorPick')]/button[1]/span"))
							.click();
					Thread.sleep(2000);
					driver.switchTo().activeElement();
					// driver.findElement(By.xpath("//div[contains(@class,'ui-colorpicker_color')]//div[1]/div")).click();
					// driver.findElement(By.xpath("//span[contains(@id,'paramForm:fgColorPick_button')]/button[1]")).click();
					driver.findElement(
							By.xpath("//span/button[1]/span[contains(text(),'Save')]"))
							.click();
				}

				Thread.sleep(5000);
			}// for
		}// try
		catch (Throwable e) {
			takeScreenShot(e, "Admin_Create_ColorCoding");
			e.printStackTrace();
			Assert.fail("Error in creating color coding ");

		}
		finally{
			Reporter.log("This test case is to verify creation of color coding");
		}
	}
}
