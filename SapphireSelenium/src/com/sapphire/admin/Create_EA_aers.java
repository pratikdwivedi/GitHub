package com.sapphire.admin;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;

public class Create_EA_aers extends Config {

	@Test
	public void TestMethodCreateEAaers() throws Exception {
		try {
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			driver.findElement(
					By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis"))
					.click();// create button clicked
			WebElement EA = driver.findElement(By
					.id("analysisform:saCriteriaTab:analysisGrid:j_idt101"));
			act.moveToElement(EA).click();// FDA AERS clicked
			act.perform();
			Thread.sleep(5000);
			System.out.println("AERS clicked");
			Thread.sleep(5000);
			driver.findElement(
					By.id("analysisCriteriaForm:tabView:drugsetGrid:0:genTradeName"))
					.sendKeys("raptiva");
			System.out.println("drug name passed");
			driver.findElement(
					By.id("analysisCriteriaForm:groupTabView:singlePanelView:groupByStep:9"))
					.click();
			System.out.println("Group by selected");
			Thread.sleep(5000);
			driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
			System.out.println("Execute pressed");
			Thread.sleep(10000);
			driver.findElement(By.id("analysisDetailToolbarForm:save")).click();
			System.out.println("save clicked");
			driver.switchTo().activeElement();
			driver.findElement(
					By.id("saveAnalysisForm:saveTabPanel:rootAnalysisName"))
					.sendKeys("NewAutomateAnalysis");
			driver.findElement(By.id("saveAnalysisForm:saveAnalysis")).click();
			Thread.sleep(5000);
			String el = driver.findElement(
					By.id("analysisDetailToolbarForm:analysisName")).getText();
			System.out.println(el);
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			driver.navigate().refresh();
			System.out.println("EA AERS creation complete............");
			Reporter.log("EA AERS creation complete............");

			Thread.sleep(5000);
		} catch (Exception e) {
			takeScreenShot(e, "Create_EA_aers");
			e.printStackTrace();
			Assert.fail("Error in ea aers creation..");

		}
	}

}