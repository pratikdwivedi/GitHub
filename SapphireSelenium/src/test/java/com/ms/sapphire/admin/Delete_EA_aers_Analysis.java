package com.ms.sapphire.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Config;

public class Delete_EA_aers_Analysis extends Config {
	@Test
	public void delete_selected_ea_analysis() throws Exception {
		try {
			act = new Actions(driver);
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			Boolean status1 = driver
					.findElement(
							By.xpath(".//*[@id='analysisform:saCriteriaTab:analysisGrid_data']/tr[1]/td[1]/div/div/div[2]"))
					.isSelected();
			System.out.println(status1);
			driver.findElement(
					By.xpath(".//*[@id='analysisform:saCriteriaTab:analysisGrid_data']/tr[1]/td[1]/div/div/div[2]"))
					.click();
			// driver.findElement(By.xpath("//tr[td[5][div[1][a[contains(Text(),'NewAutomateAnalysis')]]]]//div/div[2][contains(@class,'ui-widget-content ui-datatable-even')]")).click();
			System.out.println("analysis marked");
			driver.findElement(
					By.id("analysisform:saCriteriaTab:analysisGrid:deleteAnalysis"))
					.click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			System.out.println("Popup takes control");
			driver.findElement(By.id("removeConfirmForm:confirmRemove"))
					.click();
			Thread.sleep(3000);
			driver.navigate().refresh();
			System.out.println("EA AERS delete successfully............");
			Reporter.log("EA AERS delete successfully............");

		} catch (InterruptedException e) {
			takeScreenShot(e, "Delete_EA_aers_Analysis");
			e.printStackTrace();
			Assert.fail("Error in deleting ea aers..");

		}
	}
}
