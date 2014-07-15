package com.ms.sapphire.workspace;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;
import com.ms.sapphire.utility.Save_Analysis;

public class Work_Create_Medline extends Config{
	
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
		@Test
		public void create_medline() throws Exception{
			try{
				Save_Analysis sa=new Save_Analysis();
				
				ExcelLibrary lib1=new ExcelLibrary();
				for(int i=1;i<=lib1.rowCount;i++)
				{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("WORKSPACE")).click();
			driver.findElement(By.linkText("ANALYSIS")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis")).click();//create button clicked
			String analysisType=lib1.getExcelData("WF-medline", i, 0, filePath);
			WebElement unstructured=driver.findElement(By.linkText(analysisType));
			act.moveToElement(unstructured).click();//analysis type clicked
			act.perform();
			Thread.sleep(5000);
			System.out.println("analysis clicked");
			Thread.sleep(5000);
			String citationCriteria=lib1.getExcelData("WF-medline", i, 1, filePath);
			driver.findElement(By.id("analysisCriteriaForm:litsetGrid:0:fieldId_label")).click();
			driver.findElement(By.xpath(" //div[contains(@id,'analysisCriteriaForm:litsetGrid:0:fieldId_panel')]//div/ul/li[contains(text(),'"+citationCriteria+"')]")).click();
			String name=lib1.getExcelData("WF-medline", i,2, filePath);
			driver.findElement(By.id("analysisCriteriaForm:litsetGrid:0:citationName")).sendKeys(name);
			sa.analysisType=lib1.getExcelData("WF-medline", i, 0, filePath);
			sa.analysisName=lib1.getExcelData("WF-medline", i, 7,filePath);
			sa.route=lib1.getExcelData("WF-medline", i,8,filePath);
			sa.status=lib1.getExcelData("WF-medline", i,9,filePath);
			sa.priority=lib1.getExcelData("WF-medline", i,10,filePath);
			sa.topicName=lib1.getExcelData("WF-medline", i, 11,filePath);
			sa.scheduler=lib1.getExcelData("WF-medline", i, 12, filePath);
			sa.remarks=lib1.getExcelData("WF-medline", i, 13, filePath);
			sa.shareUser=lib1.getExcelData("WF-medline", i, 14, filePath);
			sa.shareUserGroup=lib1.getExcelData("WF-medline", i, 15, filePath);
			sa.shareNotify=lib1.getExcelData("WF-medline", i, 16, filePath);
			sa.caseAnnotationType=lib1.getExcelData("WF-medline", i,17, filePath);
			sa.caseAnnotationScope=lib1.getExcelData("WF-medline", i,18, filePath);
			sa.caseAnnotationPriority=lib1.getExcelData("WF-medline", i, 19, filePath);
			sa.caseAnnotationRemarks=lib1.getExcelData("WF-medline", i,20, filePath);
			sa.caseAnnotationFilePath=lib1.getExcelData("WF-medline", i,21, filePath);
			sa.existingCaseSeries=lib1.getExcelData("WF-medline", i,22, filePath);
			sa.newCaseSeries=lib1.getExcelData("WF-medline", i,23, filePath);
			sa.newCaseSeriesDesc=lib1.getExcelData("WF-medline", i,24, filePath);
			sa.save_Analysis();
			Thread.sleep(3000);
				}//for
			}//try
			catch (Exception e) 
			{
			//	Assert.fail("Error in medline creation..");
				e.printStackTrace();
			}
			
		}//method	
			
}
