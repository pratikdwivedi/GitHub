package com.ms.sapphire.score;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.DatabaseConnect;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class EA_aers_TotalCaseCount extends Config {
	DatabaseConnect dbc = new DatabaseConnect();
	ResultSet rst;
	int columnsNumber;
	ResultSetMetaData rsmd;
	String filePath = ModuleExcelSelection
			.getExcelFilePath(IModuleSelection.SCORE);
	String dbvalue;
	String caseCountApp;
	String drugName;
	String DBColumnName;
	String query;
	@Test
	public void EA_aers_TotalCaseCount() throws Exception {
		try {
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {

				drugName = lib1.getExcelData("EAScore", i, 3, filePath);
				DBColumnName = lib1.getExcelData("EAScore", i, 6,
						filePath);
				dbc.dbConnect();
				query = "SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where "
						+ DBColumnName + "='" + drugName + "';";
				System.out.println(query);
				rst = dbc.stmt.executeQuery(query);
				rsmd = rst.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				while (rst.next()) {

					dbvalue = rst.getString(1);
					System.out.println("Database : " + dbvalue);
				}
				String analysisType = lib1.getExcelData("EAScore", i, 0,
						filePath);
				driver.findElement(By.linkText("WORKSPACE")).click();
				driver.findElement(By.linkText("ANALYSIS")).click();
				Thread.sleep(5000);
				driver.findElement(
						By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis"))
						.click();// create button clicked
				WebElement EA = driver
						.findElement(By
								.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[1]//tr/td[1]/a/label[contains(text(),'"
										+ analysisType + "')]"));
				act.moveToElement(EA).click();// analysis type clicked
				act.perform();
				Thread.sleep(5000);
				String drugLevel = lib1.getExcelData("EAScore", i, 1, filePath);
				driver.findElement(
						By.id("analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_panel')]//div/ul/li[contains(text(),'"
								+ drugLevel + "')]")).click();
				Thread.sleep(3000);

				if (drugName != null) {
					String dictLevel = lib1.getExcelData("EAScore", i, 2,
							filePath);
					driver.findElement(
							By.id("analysisCriteriaForm:tabView:drugsetGrid:0:lookupEADrug"))
							.click();
					Thread.sleep(2000);
					driver.switchTo().activeElement();
					driver.findElement(
							By.id("productLookupEAForm:productLookupGrid:searchLevel_label"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'productLookupEAForm:productLookupGrid:searchLevel_panel')]//ul/li[contains(text(),'"
									+ dictLevel + "')]")).click();
					driver.findElement(
							By.id("productLookupEAForm:productLookupGrid:srcTxtId"))
							.sendKeys(drugName);
					driver.findElement(
							By.xpath("//div[contains(@id,'productLookupEAForm:productLookupGrid')]//span/button[1]/span"))
							.click();
					Thread.sleep(2000);
					driver.findElement(
							By.xpath("//tr[td[2][div[contains(text(),'"
									+ drugName + "')]]]//td[1]//div[2]"))
							.click();
					driver.findElement(
							By.id("productLookupEAForm:selectProduct")).click();
					Thread.sleep(2000);
				}
				String event = lib1.getExcelData("EAScore", i, 4, filePath);
				if (event != null) {

				}

				String GroupBy = lib1.getExcelData("EAScore", i, 5, filePath);
				driver.findElement(
						By.xpath("//table[contains(@id,'analysisCriteriaForm:groupTabView:singlePanelView')]//tbody/tr/td[2]/label[contains(text(),'"
								+ GroupBy + "')]")).click();

				driver.findElement(By.id("analysisCriteriaForm:expbut"))
						.click();
				System.out.println("Execute pressed");
				Thread.sleep(10000);
				caseCountApp = driver.findElement(
						By.xpath("//tr/td[4]/span[1]")).getText();
				int IntCaseCount = Integer.parseInt(caseCountApp);
				System.out.println("Application : " + IntCaseCount);

				Assert.assertEquals(caseCountApp, dbvalue);
				Thread.sleep(5000);
			}// for
		}// try
		catch (Throwable e) {
			e.printStackTrace();
			takeScreenShot(e, "EA_aers_TotalCaseCount");
			Assert.fail("EA total case count is incorrect for "+ drugName );
		} finally {
			Reporter.log("This test case is to verify EA case count in application and case count in database fact_demo_drugs_reacs table");
			Reporter.log("Expected : "+dbvalue);
			Reporter.log("Actual : "+caseCountApp);
			Reporter.log(query);
			dbc.stmt.close();
			dbc.con.close();
		}
	}

}
