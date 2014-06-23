package com.sapphire.score;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Config;
import com.test.DatabaseConnect;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class EA_Aggragates_FieldsScore extends Config {
	DatabaseConnect dbc = new DatabaseConnect();
	ResultSet rst;
	Connection con;
	int columnsNumber;
	ResultSetMetaData rsmd;
	String dbvalue;
	ArrayList applicationListValue;
	ArrayList dbListvalue;
	String filePath = ModuleExcelSelection
			.getExcelFilePath(IModuleSelection.SCORE);

	@Test
	public void EA_Aggregates_FieldsScore() throws Exception {
		try {
			ExcelLibrary lib1 = new ExcelLibrary();
			for (int i = 1; i <= lib1.rowCount; i++) {

				String drugName = lib1.getExcelData(
						"EA_Aggreagtes_FieldsScore", i, 3, filePath);
				String analysisType = lib1.getExcelData(
						"EA_Aggreagtes_FieldsScore", i, 0, filePath);
				String event = lib1.getExcelData("EA_Aggreagtes_FieldsScore",
						i, 5, filePath);
				dbc.dbConnect();
				String query = "select * from (select 'Death' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_death = 1 group by fddr.outc_death union all select 'Life Threatening' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_life_threat = 1 group by fddr.outc_life_threat union all select 'Hospitalization' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_hosp = 1 group by fddr.outc_hosp union all select 'Disability' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_disability = 1 group by fddr.outc_disability union all select 'Congenital Anomaly' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_cong_anomaly = 1 group by fddr.outc_cong_anomaly union all select 'Required Intervention' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_reqd_interv = 1 group by fddr.outc_reqd_interv union all select 'Other' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_other = 1 group by fddr.outc_other union all select 'None' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_none = 1 group by fddr.outc_none union all select 'Recovered' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_rec = 1 group by fddr.outc_rec union all select 'Treated with drug' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_rx = 1 group by fddr.outc_rec union all select 'Not available' outcome, count(distinct fddr.isr_id) cnt FROM aers.fact_demo_drugs_reacs fddr,aers.aers_drug_ings adi where fddr.isr_id = adi.isr_id and fddr.trade_name='"
						+ drugName
						+ "' and fddr.outc_death = '0' and fddr.outc_life_threat = '0' and fddr.outc_hosp = '0' and fddr.outc_disability = '0' and fddr.outc_cong_anomaly = '0' and fddr.outc_reqd_interv = '0' and fddr.outc_other = '0' and fddr.outc_none = '0' and fddr.outc_rec = '0' and fddr.outc_rx ='0' group by fddr.outc_rec) as result order by cnt desc; ";
				System.out.println(query);
				rst = dbc.stmt.executeQuery(query);
				rsmd = rst.getMetaData();
				columnsNumber = rsmd.getColumnCount();
				dbListvalue = new ArrayList();
				while (rst.next()) {

					for (int j = 1; j <= columnsNumber; j++) {
						dbListvalue.add(rst.getString(j));
						// System.out.println("Database :"+rst.getString(j));
					}
					// dbvalue =rst.getString(1);
				}
				System.out.println("Database : " + dbListvalue);
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
				String drugLevel = lib1.getExcelData(
						"EA_Aggreagtes_FieldsScore", i, 1, filePath);
				driver.findElement(
						By.id("analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_label"))
						.click();
				driver.findElement(
						By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_panel')]//div/ul/li[contains(text(),'"
								+ drugLevel + "')]")).click();
				Thread.sleep(3000);
				if (drugName != null) {
					String dictLevel = lib1.getExcelData(
							"EA_Aggreagtes_FieldsScore", i, 2, filePath);
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

				if (event != null) {
					driver.findElement(
							By.xpath("//ul/li/a[contains(@href,'#analysisCriteriaForm:tabView:eventTab')]"))
							.click();
					String eventLevel = lib1.getExcelData(
							"EA_Aggreagtes_FieldsScore", i, 4, filePath);
					Thread.sleep(3000);
					driver.findElement(
							By.id("analysisCriteriaForm:tabView:eventLevelId_label"))
							.click();
					driver.findElement(
							By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:eventLevelId_panel')]//ul/li[contains(text(),'"
									+ eventLevel + "')]")).click();
					driver.findElement(
							By.id("analysisCriteriaForm:tabView:lookupSAEvent"))
							.click();
					Thread.sleep(3000);
					driver.switchTo().activeElement();
					driver.findElement(
							By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId"))
							.sendKeys(event);
					driver.findElement(
							By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//button[1]/span"))
							.click();
					Thread.sleep(3000);
					driver.findElement(
							By.xpath("//tr[td[div[contains(text(),'" + event
									+ "')]]]//td[1]/div/div/div[2]")).click();
					driver.findElement(By.id("eventLookupForm:selectEvent"))
							.click();
					Thread.sleep(3000);
				}
				String GroupBy = lib1.getExcelData("EA_Aggreagtes_FieldsScore",
						i, 7, filePath);
				driver.findElement(
						By.xpath("//table[contains(@id,'analysisCriteriaForm:groupTabView:singlePanelView')]//tbody/tr/td[2]/label[contains(text(),'"
								+ GroupBy + "')]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("analysisCriteriaForm:expbut"))
						.click();
				System.out.println("Execute pressed");
				List<WebElement> result1 = driver
						.findElements(By
								.xpath("//tbody[contains(@id,'analysisDetailForm:analysisGrid_data')]//tr/td[2]/div"));
				List GroupList = new ArrayList();
				for (WebElement we : result1) {
					// System.out.println(we.getText());
					GroupList.add(we.getText());
				}

				List<WebElement> resultCounts = driver
						.findElements(By
								.xpath("//tbody[contains(@id,'analysisDetailForm:analysisGrid_data')]//tr/td[3]/div"));
				// List GroupCountList=new ArrayList();
				for (WebElement we2 : resultCounts) {
					// System.out.println(we2.getText());
					GroupList.add(we2.getText());
				}
				System.out.println("Application : " + GroupList);
				System.out.println(dbListvalue.equals(GroupList));
				Assert.assertEquals(GroupList, dbListvalue);
				Thread.sleep(2000);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			takeScreenShot(e, "EA_Aggreagtes_FieldsScore");
			Assert.fail("Error in EA fields score ");
		} finally {
			dbc.stmt.close();
			dbc.con.close();
		}
	}// method
}
