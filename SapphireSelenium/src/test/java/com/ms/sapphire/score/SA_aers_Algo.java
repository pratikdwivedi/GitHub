package com.ms.sapphire.score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

public class SA_aers_Algo extends Config {
	DatabaseConnect dbc = new DatabaseConnect();
	int j;
	ResultSet rst;
	Connection con;
	int columnsNumber;
	ResultSetMetaData rsmd;
	ArrayList dbListvalue;
	ArrayList applicationListValue;
	String filePath = ModuleExcelSelection
			.getExcelFilePath(IModuleSelection.SCORE);
	private String dbvalue;
	String drugName;
	String event;
	String query;
	String DBTableName;
	List algoList;
	@Test
	public void SA_aers_Algo() throws Exception {
		try {
			dbc.dbConnect();
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {
				String analysisType = lib1.getExcelData("DECCaseCount", i, 0,
						filePath);
				if (analysisType != null) {
					drugName = lib1.getExcelData("DECCaseCount", i, 3,
							filePath);
					event = lib1.getExcelData("DECCaseCount", i, 4,
							filePath);
					DBTableName = lib1.getExcelData("DECCaseCount", i,
							8, filePath);
					if (drugName != null && event != null
							&& DBTableName != null) {
						query = "SELECT exp_A,rr,prr_lb,prr_ub,ebgm_lb,ebgm_ub,ror_lb,ror_ub,bcpnn_lb,bcpnn_ub,chi_sqr_ABCD,chi_sqr_yates_ABCD,yulesq_lb,yulesq_ub FROM "
								+ DBTableName
								+ " where drug_name='"
								+ drugName
								+ "' and event_name ='"
								+ event
								+ "' and fda_period=(select quarter from aers.stat_latest_quarter);";
						System.out.println(query);
						rst = dbc.stmt.executeQuery(query);
						rsmd = rst.getMetaData();
						columnsNumber = rsmd.getColumnCount();
						while (rst.next()) {
							dbListvalue = new ArrayList();
							for (j = 1; j <= columnsNumber; j++) {
								dbListvalue.add(rst.getString(j));
								// System.out.println("Database :"+rst.getString(j));
							}
							System.out.println("Database : " + dbListvalue);
							System.out.println("Database values size : "
									+ dbListvalue.size());
						}

						if (analysisType != null) {
							driver.findElement(By.linkText("WORKSPACE"))
									.click();
							driver.findElement(By.linkText("ANALYSIS")).click();
							Thread.sleep(5000);
							driver.findElement(
									By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis"))
									.click();// create button clicked
							WebElement EA = driver
									.findElement(By
											.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"
													+ analysisType + "')]"));
							act.moveToElement(EA).click();// analysis type
															// clicked
							act.perform();
							Thread.sleep(5000);
							String drugLevel = lib1.getExcelData(
									"DECCaseCount", i, 1, filePath);
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
								String dictLevel = lib1.getExcelData(
										"DECCaseCount", i, 2, filePath);
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
												+ "')]]]//td[1]/div/div/div[2]"))
										.click();
								Thread.sleep(3000);
								driver.findElement(
										By.id("productLookupForm:selectProduct"))
										.click();// drug select
								Thread.sleep(3000);
							}
							if (event != null) {
								driver.findElement(
										By.id("analysisCriteriaForm:saCriteriaTab:lookupSAEvent"))
										.click();
								driver.switchTo().activeElement();
								/*open event lookup*/
								driver.findElement(
										By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId"))
										.sendKeys(event); 
								/* clicks on search button*/
								driver.findElement(
										By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span"))
										.click();
								Thread.sleep(5000);
								driver.findElement(
										By.xpath("//tr[td[2][div[contains(text(),'"+event+"')]]]//td[1]/div/div/div[2]")).click();
								Thread.sleep(4000);
								driver.findElement(
										By.id("eventLookupForm:selectEvent"))
										.click();
								Thread.sleep(2000);
							}
							algoList=new ArrayList();
							for (int j = 1; j <= lib1.rowCount; j++) {
								String filter = lib1.getExcelData(
										"SAScoreAlgo", j, 0, filePath);
								algoList.add(filter);
								if (filter != null) {
									driver.findElement(
											By.xpath("//tr[td[2][label[contains(text(),'"
													+ filter
													+ "')]]]//td[1]/div/div[2]"))
											.click();
								}
							}
							driver.findElement(
									By.id("analysisCriteriaForm:expbut"))
									.click();
							System.out.println("Execute pressed");
							Thread.sleep(10000);
							List<WebElement> algoValue = driver
									.findElements(By
											.xpath("//tbody[contains(@id,'analysisDetailForm:staticTabView:stat_analysisGrid_data')]//tr/td/div[contains(text(),'.')]"));
							applicationListValue = new ArrayList();
							for (WebElement we : algoValue) {
								// System.out.println("Application: "+we.getText());
								applicationListValue.add(we.getText());
							}
							System.out.println("Application : "
									+ applicationListValue);
							System.out.println("Application values size : "
									+ applicationListValue.size());

							System.out.println(applicationListValue
									.containsAll(dbListvalue)
									+ " --- "
									+ dbListvalue
											.containsAll(applicationListValue));
							Assert.assertEquals(applicationListValue
									.containsAll(dbListvalue), dbListvalue
									.containsAll(applicationListValue));

							/*
							 * for(int k=0;k<=dbListvalue.size();k++) { for(int
							 * k2=0;k<=applicationListValue.size();k++) {
							 * applicationListValue.get(k2).equals (
							 * dbListvalue.get(k) ); } } applicationListValue
							 * .equals ( dbListvalue );// which is true, if they
							 * contains the same elements in the same order new
							 * HashSet(applicationListValue) . equals (new
							 * HashSet(dbListvalue)); System.out.println(new
							 * HashSet(applicationListValue) . equals (new
							 * HashSet(dbListvalue))); // if the order does not
							 * matter // ListComparator<String> compare = new
							 * ListComparator<String>(); //
							 * System.out.println(compare
							 * .compare(applicationListValue, dbListvalue));
							 */
							Thread.sleep(5000);
						} 
						con.close();
					}// if
					else {
						System.out
								.println("Some important test data is missing....");
						Reporter.log("Some important test data is missing....");
					}
				}
			}// for
		}// try
		catch (Throwable e) {
			Assert.fail("SA aers score values are incorrect for "+drugName +" and " +event);
			takeScreenShot(e, "SA_aers_Algo");
			e.printStackTrace();
		}

		finally {
			Reporter.log("This test case is to verify SA aers score values in "+DBTableName+" table");
			Reporter.log("Selected algorithms  :"+algoList);
			Reporter.log("Expected :"+dbListvalue);
			Reporter.log("Actual :"+applicationListValue);
			Reporter.log(query);
			dbc.stmt.close();
			dbc.con.close();
		}
	}

}
