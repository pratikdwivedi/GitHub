package com.ms.sapphire.score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

public class SA_aers_DrugEvent_Trending extends Config {
	DatabaseConnect dbc = new DatabaseConnect();
	private List appValue = null;
	private List dbListValue = null;
	ResultSet rst;
	Connection con;
	String filePath = ModuleExcelSelection
			.getExcelFilePath(IModuleSelection.SCORE);
	private String dbvalue;
	String drugName;
	String DBTableName;
	String event;
	String query;
	List algoList;
	@Test
	public void SADrugEvent_Algo() throws Exception {
		try {
			dbc.dbConnect();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				 drugName = lib1.getExcelData("DECCaseCount", i, 3,
						filePath);
				 event = lib1
						.getExcelData("DECCaseCount", i, 4, filePath);
				 DBTableName = lib1.getExcelData("DECCaseCount", i, 8,
						filePath);
				 query = "SELECT prr_lb FROM "
						+ DBTableName
						+ " where drug_name='"
						+ drugName
						+ "' and event_name ='"
						+ event
						+ "' and fda_period<=(SELECT * FROM stat_latest_quarter) and fda_period>=(SELECT quarter as Q FROM stat_latest_quarter) - 3 order by fda_period;";
				System.out.println(query);
				rst = dbc.stmt.executeQuery(query);
				dbListValue = new ArrayList();
				while (rst.next()) {
					dbvalue = rst.getString(1);
					
					dbListValue.add(dbvalue);
				
				}
				System.out.println("Database: " + dbListValue);
				driver.findElement(By.linkText("WORKSPACE")).click();
				driver.findElement(By.linkText("ANALYSIS")).click();
				Thread.sleep(5000);
				driver.findElement(
						By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis"))
						.click();// create button clicked
				String analysisType = lib1.getExcelData("DECCaseCount", i, 0,
						filePath);
				WebElement EA = driver
						.findElement(By
								.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"
										+ analysisType + "')]"));
				act.moveToElement(EA).click();// analysis type clicked
				act.perform();
				Thread.sleep(5000);
				String drugLevel = lib1.getExcelData("DECCaseCount", i, 1,
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
					String dictLevel = lib1.getExcelData("DECCaseCount", i, 2,
							filePath);
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
					/*Clicks on search button*/
					driver.findElement(
							By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
							.click();
					Thread.sleep(4000);
					/*select event from event popup*/
					driver.findElement(
							By.xpath("//tr[td[2][div[contains(text(),'"+event+"')]]]//td[1]/div/div/div[2]")).click();
					
					Thread.sleep(4000);
					driver.findElement(By.id("eventLookupForm:selectEvent"))
							.click();
					Thread.sleep(2000);
				}
				String filter = lib1.getExcelData("DECCaseCount", i, 5,
						filePath);
				algoList=new ArrayList();
				algoList.add(filter);
				driver.findElement(
						By.xpath("//tr[td[2][label[contains(text(),'" + filter
								+ "')]]]//td[1]/div/div[2]")).click();
				String trendPeriod = lib1.getExcelData("DECCaseCount", i, 6,
						filePath);
				driver.findElement(
						By.id("analysisCriteriaForm:saCriteriaTab:trendPeriodId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendPeriodId_panel')]//ul/li[contains(text(),'"
								+ trendPeriod + "')]")).click();
				String trendPast = lib1.getExcelData("DECCaseCount", i, 7,
						filePath);
				int trendPastInt = new Double(trendPast).intValue();
				driver.findElement(
						By.id("analysisCriteriaForm:saCriteriaTab:trendPastPeriodId"))
						.sendKeys("" + trendPastInt);
				driver.findElement(By.id("analysisCriteriaForm:expbut"))
						.click();
				System.out.println("Execute pressed");
				Thread.sleep(10000);
				List<WebElement> PRR = driver
						.findElements(By
								.xpath("//div[contains(@id,'analysisDetailForm:trendingTabView:trendGrid')][div[1][div[table[thead[tr[2][th[div[span[2]]]]]]]]]//div[2]//td/div[contains(text(),'.')]"));
				appValue = new ArrayList();
				for (WebElement we : PRR) {
					
					appValue.add(we.getText());
					
				}
				System.out.println("Application: " + appValue);
				// Assert.assertEquals(dbListValue.contains(appValue),appValue.contains(dbListValue));

				Assert.assertEquals(appValue, dbListValue);
				con.close();
				Thread.sleep(5000);
			}// for
		} catch (Throwable e) {
			e.printStackTrace();
			takeScreenShot(e, "SA_aers_DrugEvent_Trending");
			Assert.fail("SA aers trending score values are incorrect for "+drugName +" and " +event);
		} finally {
			Reporter.log("This test case is to verify SA aers trending score values in "+DBTableName+" table");		
			Reporter.log("Selected algorithms :"+algoList);
			Reporter.log("Expected :"+dbListValue);
			Reporter.log("Actual :"+appValue);
			Reporter.log(query);
			dbc.stmt.close();
			dbc.con.close();
			appValue.clear();
			dbListValue.clear();
		}
	}
}
