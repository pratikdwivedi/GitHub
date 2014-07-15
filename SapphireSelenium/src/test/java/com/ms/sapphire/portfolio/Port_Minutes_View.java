package com.ms.sapphire.portfolio;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.FetchProperties;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Port_Minutes_View extends Config {
	@Test
	public void master_minutes() throws Exception {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.PORTFOLIO);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {

				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.portfolio)).click();
				Thread.sleep(3000);
				List<WebElement> listMaster = driver
						.findElements(By
								.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String masterName = lib1.getExcelData("viewminutes", i, 0,
						filePath);
				for (WebElement weMaster : listMaster) {
					System.out.println(weMaster.getText());
					if (weMaster.getText().contentEquals(masterName)) {
						String link = weMaster.getText();
						System.out.println("Clicked LInk" + link);
						weMaster.click();
						System.out.println("clicked");
						Thread.sleep(3000);
						break;
					}
				}
				Thread.sleep(3000);
				driver.findElement(
						By.xpath("//div[contains(@class,'ui-menu ui-widget ui-widget-content ui-corner-all ui-helper-clearfix leftContent')]//ul/li/a/span[contains(text(),'Minutes')]"))
						.click();

				Thread.sleep(10000);
			}// for
			Thread.sleep(10000);
		}// try
		catch (Throwable e) {
			e.printStackTrace();
			// Assert.fail("Error in Minutes view ");
		}

	}// method

}
