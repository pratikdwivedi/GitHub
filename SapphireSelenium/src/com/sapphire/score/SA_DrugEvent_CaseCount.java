package com.sapphire.score;

import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.DatabaseConnect;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class SA_DrugEvent_CaseCount extends Config {
	DatabaseConnect dbc = new DatabaseConnect();
	String filePath = ModuleExcelSelection
			.getExcelFilePath(IModuleSelection.SCORE);
	private String dbvalue;
	private int dbvalueInt;

	@Test
	public void SADrugEvent_CaseCount() throws Exception {
		try {
			dbc.dbConnect();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				String drugName = lib1.getExcelData("SADECCaseCount", i, 3,
						filePath);
				String event = lib1.getExcelData("SADECCaseCount", i, 4,
						filePath);
				String DBColumnName = lib1.getExcelData("SADECCaseCount", i, 8,
						filePath);
				String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where "
						+ DBColumnName
						+ "='"
						+ drugName
						+ "' and pt_name='"
						+ event + "';";
				System.out.println(query);
				ResultSet rst = dbc.stmt.executeQuery(query);
				// In a loop
				while (rst.next()) {
					dbvalue = rst.getString(1);
					dbvalueInt = Integer.parseInt(dbvalue);
					System.out.println("Database : " + dbvalueInt);
				}
				driver.findElement(By.linkText("WORKSPACE")).click();
				driver.findElement(By.linkText("ANALYSIS")).click();
				Thread.sleep(5000);
				driver.findElement(
						By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis"))
						.click();// create button clicked
				String analysisType = lib1.getExcelData("SADECCaseCount", i, 0,
						filePath);
				WebElement EA = driver
						.findElement(By
								.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"
										+ analysisType + "')]"));
				act.moveToElement(EA).click();// analysis type clicked
				act.perform();
				Thread.sleep(5000);
				String drugLevel = lib1.getExcelData("SADECCaseCount", i, 1,
						filePath);
				driver.findElement(
						By.id("analysisCriteriaForm:saCriteriaTab:drugLevelId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:drugLevelId_panel')]//div/ul/li[contains(text(),'"
								+ drugLevel + "')]")).click();
				Thread.sleep(3000);

				if (drugName != null) {
					driver.findElement(
							By.id("analysisCriteriaForm:saCriteriaTab:lookupSADrug"))
							.click();
					driver.switchTo().activeElement();
					String dictLevel = lib1.getExcelData("SADECCaseCount", i,
							2, filePath);
					driver.findElement(
							By.id("productLookupForm:tabView:productLookupGrid:searchLevel_label"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"
									+ dictLevel + "')]")).click();
					Thread.sleep(3000);
					driver.findElement(
							By.id("productLookupForm:tabView:productLookupGrid:srcTxtId"))
							.sendKeys(drugName);
					driver.findElement(
							By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
							.click();
					Thread.sleep(4000);
					driver.findElement(
							By.xpath("//tr[td[2][div[contains(text(),'"
									+ drugName.toUpperCase()
									+ "')]]]//td[1]/div/div/div[2]")).click();
					Thread.sleep(3000);
					driver.findElement(By.id("productLookupForm:selectProduct"))
							.click();// drug select
					Thread.sleep(3000);
				}

				if (event != null) {
					driver.findElement(
							By.id("analysisCriteriaForm:saCriteriaTab:lookupSAEvent"))
							.click();
					driver.switchTo().activeElement();
					driver.findElement(
							By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId"))
							.sendKeys(event);
					driver.findElement(
							By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
							.click();
					Thread.sleep(3000);
					driver.findElement(
							By.xpath("//tr[td[2][div[1][text()='" + event
									+ "']]]//td[1]/div/div")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("eventLookupForm:selectEvent"))
							.click();
					Thread.sleep(2000);
				}
				String filter = lib1.getExcelData("SADECCaseCount", i, 5,
						filePath);
				driver.findElement(
						By.xpath("//tr[td[2][label[contains(text(),'" + filter
								+ "')]]]//td[1]/div/div[2]")).click();
				driver.findElement(By.id("analysisCriteriaForm:expbut"))
						.click();
				System.out.println("Execute pressed");
				Thread.sleep(5000);
				driver.findElement(By.id("analysisDetailToolbarForm:showCases"))
						.click();
				Thread.sleep(8000);
				String caseCount = driver
						.findElement(
								By.xpath("//div[contains(@id,'caseListForm:caseGrid')]//div[1]/div[1]/div[1]"))
						.getText();
				int IntCaseCount = Integer.parseInt(caseCount.split(" ")[0]);
				System.out.println("Application : " + IntCaseCount);
				Assertions.verifyEqualsInt(IntCaseCount, dbvalueInt);
				Thread.sleep(5000);
			}// for
		} catch (Throwable e) {
			e.printStackTrace();
			takeScreenShot(e, "SADrugEvent_CaseCount");
			Assert.fail("Error in DEC case count ");
		} finally {
			dbc.stmt.close();
			dbc.con.close();
		}
	}
}
