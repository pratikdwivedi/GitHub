package com.sapphire.score;
import java.sql.*;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
public class SA_Total_CaseCount extends Config{
	 String dbvalue;
	 int dbvalueInt;
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.SCORE);
	
	@Test
	 public void SATotalCaseCount() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{	
				String drugName=lib1.getExcelData("SAStaticCaseCount", i, 3, filePath);
				ScoreDBConnection dbc =new ScoreDBConnection();
				Class.forName(dbc.dbClass);
				 Connection con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				//String query = "select name from db1";
				 String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where trade_name='raptiva'";
				 System.out.println(query);
				 ResultSet rst = stmt.executeQuery(query); 
				//In a loop
				 while (rst.next()) {
					 dbvalue =rst.getString(1);
					 dbvalueInt = Integer.parseInt(dbvalue);
				  System.out.println(dbvalueInt);
				 }
				 con.close();
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis")).click();//create button clicked
			String analysisType=lib1.getExcelData("SAStaticCaseCount", i, 0, filePath);
			WebElement EA=driver.findElement(By.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"+analysisType+"')]"));
			act.moveToElement(EA).click();//analysis type clicked
			act.perform();
			Thread.sleep(5000);
			String drugLevel=lib1.getExcelData("SAStaticCaseCount", i, 1, filePath);
			driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:drugLevelId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:drugLevelId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();
			Thread.sleep(3000);
		
				if(drugName!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSADrug")).click();
				driver.switchTo().activeElement();
				String dictLevel=lib1.getExcelData("SAStaticCaseCount", i, 2, filePath);
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:searchLevel_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"+dictLevel+"')]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:srcTxtId")).sendKeys(drugName);
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//tbody[tr[td[2][div[contains(text(),'"+drugName+"')]]]]//tr/td[1]/div/div/div[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("productLookupForm:selectProduct")).click();//drug select 
				Thread.sleep(3000);
				}
				String filter=lib1.getExcelData("SAStaticCaseCount", i, 4, filePath);
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filter+"')]]]//td[1]/div/div[2]")).click();	
				driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
				System.out.println("Execute pressed");
				Thread.sleep(5000);
				driver.findElement(By.id("analysisDetailToolbarForm:showCases")).click();
				Thread.sleep(8000);
				String caseCount=driver.findElement(By.xpath("//div[contains(@id,'caseListForm:caseGrid')]//div[1]/div[1]/div[1]")).getText();
				int IntCaseCount=Integer.parseInt(caseCount.split(" ")[0]);
				System.out.println(IntCaseCount);
				Assertions.verifyEqualsInt(IntCaseCount,dbvalueInt);
				Thread.sleep(5000);
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			  takeScreenShot(e, "SA_Total_CaseCount");
			 }
	}		
}
