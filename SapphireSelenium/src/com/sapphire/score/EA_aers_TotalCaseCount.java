package com.sapphire.score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class EA_aers_TotalCaseCount extends Config{
	ResultSet rst; Connection con; int columnsNumber ; ResultSetMetaData rsmd;ArrayList dbListvalue;ArrayList applicationListValue;
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.SCORE);
	String dbvalue;
	@Test
	public void EA_aers_TotalCaseCount() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				ScoreDBConnection dbc =new ScoreDBConnection();
				String drugName=lib1.getExcelData("EAScore", i, 3, filePath);
				String DBColumnName=lib1.getExcelData("EAScore", i, 6, filePath);
		 Class.forName(dbc.dbClass);
		  con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
		 Statement stmt = con.createStatement();
		 String query = "SELECT count(distinct isr_id) FROM aers.fact_demo_drugs_reacs where "+DBColumnName+"='"+drugName+"';";
		 System.out.println(query);
		 rst = stmt.executeQuery(query); 
		 rsmd = rst.getMetaData();
		 columnsNumber =rsmd.getColumnCount();
		 while (rst.next()) {
			
			 dbvalue =rst.getString(1);
			 System.out.println("Database : "+dbvalue); 
		  }
		 con.close();
				String analysisType=lib1.getExcelData("EAScore", i, 0, filePath);
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis")).click();//create button clicked
			WebElement EA=driver.findElement(By.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[1]//tr/td[1]/a/label[contains(text(),'"+analysisType+"')]"));
			act.moveToElement(EA).click();//analysis type clicked
			act.perform();
			Thread.sleep(5000);
			String drugLevel=lib1.getExcelData("EAScore", i, 1, filePath);
			driver.findElement(By.id("analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();
			Thread.sleep(3000);
		
				if(drugName!=null)
				{
				String dictLevel=lib1.getExcelData("EAScore", i, 2, filePath);
				driver.findElement(By.id("analysisCriteriaForm:tabView:drugsetGrid:0:lookupEADrug")).click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("productLookupEAForm:productLookupGrid:searchLevel_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'productLookupEAForm:productLookupGrid:searchLevel_panel')]//ul/li[contains(text(),'"+dictLevel+"')]")).click();
				driver.findElement(By.id("productLookupEAForm:productLookupGrid:srcTxtId")).sendKeys(drugName);
				driver.findElement(By.xpath("//div[contains(@id,'productLookupEAForm:productLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+drugName+"')]]]//td[1]//div[2]")).click();
				driver.findElement(By.id("productLookupEAForm:selectProduct")).click();
				Thread.sleep(2000);
				}
				String event=lib1.getExcelData("EAScore", i, 4, filePath);
				if(event!=null){
				
				}
				
				String GroupBy=lib1.getExcelData("EAScore", i, 5, filePath);
				driver.findElement(By.xpath("//table[contains(@id,'analysisCriteriaForm:groupTabView:singlePanelView')]//tbody/tr/td[2]/label[contains(text(),'"+GroupBy+"')]")).click();
				
				driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
				System.out.println("Execute pressed");
				Thread.sleep(10000);
				String caseCount=driver.findElement(By.xpath("//tr/td[4]/span[1]")).getText();
				int IntCaseCount=Integer.parseInt(caseCount);
			System.out.println("Application : "+IntCaseCount);	
			
		
			Assert.assertEquals(caseCount, dbvalue);
				
			  
				Thread.sleep(5000);
				
				
				}//for
	}//try
		catch(Throwable e) {
			  e.printStackTrace();
			  takeScreenShot(e, "EA_aers_TotalCaseCount");
			 }
		}
	
	
}
