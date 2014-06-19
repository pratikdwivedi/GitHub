package com.sapphire.score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class SA_aers_DrugEvent_Trending extends Config{
	 private  List appValue = null;
	private  List dbListValue = null;
	ResultSet rst; Connection con;
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.SCORE);
	private String dbvalue;
	@Test
	public void SADrugEvent_Algo() throws Exception{
		try{
			ScoreDBConnection dbc =new ScoreDBConnection();
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				String drugName=lib1.getExcelData("DECCaseCount", i, 3, filePath);
				String event=lib1.getExcelData("DECCaseCount", i, 4, filePath);
				String DBTableName=lib1.getExcelData("DECCaseCount", i,8, filePath);
				Class.forName(dbc.dbClass);
				  con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				 String query = "SELECT prr_lb FROM "+DBTableName+" where drug_name='"+drugName+"' and event_name ='"+event+"' and fda_period<=(SELECT * FROM aers.stat_latest_quarter) and fda_period>=(SELECT quarter as Q FROM aers.stat_latest_quarter) - 3 order by fda_period;";
				 System.out.println(query);
				 rst = stmt.executeQuery(query); 
				 while (rst.next()) {
					  dbvalue =rst.getString(1);
					  dbListValue=new ArrayList();
					  dbListValue.add(dbvalue);
					  System.out.println("Database: "+dbListValue); 
				  }
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis")).click();//create button clicked
			String analysisType=lib1.getExcelData("DECCaseCount", i, 0, filePath);
			WebElement EA=driver.findElement(By.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"+analysisType+"')]"));
			act.moveToElement(EA).click();//analysis type clicked
			act.perform();
			Thread.sleep(5000);
			String drugLevel=lib1.getExcelData("DECCaseCount", i, 1, filePath);
			driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:drugLevelId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:drugLevelId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();
			Thread.sleep(3000);
			
				if(drugName!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSADrug")).click();
				driver.switchTo().activeElement();
				String dictLevel=lib1.getExcelData("DECCaseCount", i, 2, filePath);
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:searchLevel_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"+dictLevel+"')]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:srcTxtId")).sendKeys(drugName);
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+drugName.toUpperCase()+"')]]]//td[1]/div/div/div[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("productLookupForm:selectProduct")).click();//drug select 
				Thread.sleep(3000);
				}
			
				if(event!=null){
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSAEvent")).click();
					driver.switchTo().activeElement();
					driver.findElement(By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId")).sendKeys(event);
					driver.findElement(By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//tr[td[2][div[1][text()='"+event+"']]]//td[1]/div/div")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("eventLookupForm:selectEvent")).click();
					Thread.sleep(2000);
					}
				String filter=lib1.getExcelData("DECCaseCount", i, 5, filePath);
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filter+"')]]]//td[1]/div/div[2]")).click();	
				String trendPeriod=lib1.getExcelData("DECCaseCount", i, 6, filePath);
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPeriodId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendPeriodId_panel')]//ul/li[contains(text(),'"+trendPeriod+"')]")).click();
				String trendPast=lib1.getExcelData("DECCaseCount", i, 7, filePath);
				int trendPastInt=new Double(trendPast).intValue();
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPastPeriodId")).sendKeys(""+trendPastInt);
				driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
				System.out.println("Execute pressed");
				Thread.sleep(10000);
				List<WebElement> PRR=driver.findElements(By.xpath("//div[contains(@id,'analysisDetailForm:trendingTabView:trendGrid')][div[1][div[table[thead[tr[2][th[div[span[2]]]]]]]]]//div[2]//td/div[contains(text(),'.')]"));
				 for( WebElement we:PRR)
		          {
					appValue=new ArrayList();
					 appValue.add(we.getText());
					 System.out.println("Application: "+appValue);
					}
				 
			//	 Assert.assertEquals(dbListValue.contains(appValue),appValue.contains(dbListValue));
		          
				 Assertions.assertEqualsList(appValue, dbListValue);
				 con.close();
				Thread.sleep(5000);
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			  takeScreenShot(e, "SA_aers_DrugEvent_Trending");
			  Assert.fail("Error in scores values ");
			 }
		}
}
