package com.ms.sapphire.dashboard;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class Dashboard_LinkNavigation extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.DASHBOARD);
	@Test
	public void dashboard_navigation() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
	//Case Frequency	
			String setCaseProduct=lib1.getExcelData("DashboardNavigation", i, 0, filePath);
			driver.findElement(By.id("tabView:formcasefrequency:drug_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formcasefrequency:drug_panel')]//div/ul/li[contains(text(),'"+setCaseProduct+"')]")).click();
			String setCaseTrend=lib1.getExcelData("DashboardNavigation", i, 1, filePath);
			driver.findElement(By.id("tabView:formcasefrequency:frequency_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formcasefrequency:frequency_panel')]//div/ul/li[contains(text(),'"+setCaseTrend+"')]")).click();
			String setCaseFrequency=lib1.getExcelData("DashboardNavigation", i, 2, filePath);
			int intCaseFrequency=new Double(setCaseFrequency).intValue();
			driver.findElement(By.id("tabView:formcasefrequency:frequencyValue")).clear();
			driver.findElement(By.id("tabView:formcasefrequency:frequencyValue")).sendKeys(""+intCaseFrequency);
			driver.findElement(By.id("tabView:formcasefrequency:casefrequency_loadbtn")).click();
			Thread.sleep(5000);
	//Signal Trending
			String trendProduct=lib1.getExcelData("DashboardNavigation", i, 3, filePath);
			driver.findElement(By.id("tabView:formsignaltrending:drug_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formsignaltrending:drug_panel')]//div/ul/li[contains(text(),'"+trendProduct+"')]")).click();
			String setSignalAlgo=lib1.getExcelData("DashboardNavigation", i, 4, filePath);
			driver.findElement(By.id("tabView:formsignaltrending:algo_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formsignaltrending:algo_panel')]//div/ul/li[text()='"+setSignalAlgo+"']")).click();
			String setSignalTrend=lib1.getExcelData("DashboardNavigation", i, 5, filePath);
			driver.findElement(By.id("tabView:formsignaltrending:frequency_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formsignaltrending:frequency_panel')]//div/ul/li[contains(text(),'"+setSignalTrend+"')]")).click();
			String setSignalTrendPeriod=lib1.getExcelData("DashboardNavigation", i, 6, filePath);
			int intSetSignalTrendPeriod=new Double(setSignalTrendPeriod).intValue();
			driver.findElement(By.id("tabView:formsignaltrending:frequencyValue")).clear();
			driver.findElement(By.id("tabView:formsignaltrending:frequencyValue")).sendKeys(""+intSetSignalTrendPeriod);
			driver.findElement(By.id("tabView:formsignaltrending:signaltrending_loadbtn")).click();
			Thread.sleep(5000);

	//NewPT's
			String setNewPTproduct=lib1.getExcelData("DashboardNavigation", i, 7, filePath);
			driver.findElement(By.id("tabView:formptfrequency:drug_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formptfrequency:drug_panel')]//div/ul/li[contains(text(),'"+setNewPTproduct+"')]")).click();
			String setNewPtTrend=lib1.getExcelData("DashboardNavigation", i, 8, filePath);
			driver.findElement(By.id("tabView:formptfrequency:frequency_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:formptfrequency:frequency_panel')]//div/ul/li[text()='"+setNewPtTrend+"']")).click();
			String setNewPtTrendperiod=lib1.getExcelData("DashboardNavigation", i, 9, filePath);
			int intsetNewPtTrendperiod=new Double(setNewPtTrendperiod).intValue();
			driver.findElement(By.id("tabView:formptfrequency:frequencyValue")).clear();
			driver.findElement(By.id("tabView:formptfrequency:frequencyValue")).sendKeys(""+intsetNewPtTrendperiod);
			driver.findElement(By.id("tabView:formptfrequency:ptfrequency_loadbtn")).click();
			Thread.sleep(5000);
	//Topic
			String navigateTopic=lib1.getExcelData("DashboardNavigation", i, 10, filePath);
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:formtopicsummarry:topicsummarry_component_data')]//tr/td/div/a[contains(text(),'"+navigateTopic+"')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("tabView:analysisDetailToolbarForm:showCases")).click();
			driver.findElement(By.xpath("//tbody[contains(@id,'caseListForm:caseGrid_data')]//tr/td[4]/div/a")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@id,'caseDetailForm:caseDetailDlg')]//div[3]/span/button[2]/span")).click();
			driver.findElement(By.xpath("//a[contains(@class,'ui-layout-unit-header-icon ui-state-default ui-corner-all')]//span[contains(@class,'ui-icon ui-icon-triangle-1-s')]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'HOME')]")).click();
	//Docket download
			String downloadDocket=lib1.getExcelData("DashboardNavigation", i, 12, filePath);
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:formdocket:docket_component_data')]//tr/td[1]/div/a[contains(text(),'"+downloadDocket+"')]")).click();
	//Navigate Analysis
			String workspaceAnalysis=lib1.getExcelData("DashboardNavigation", i, 13, filePath);
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:formanalysis:analysis_component_data')]//tr/td[1]/div/a[contains(text(),'"+workspaceAnalysis+"')]")).click();
			String wsAnalysisName=driver.findElement(By.xpath("//form[contains(@id,'analysisDetailToolbarForm')]//td[2]/span[contains(text(),'"+workspaceAnalysis+"')]")).getText();
			Thread.sleep(3000);
			Assertions.assertText(workspaceAnalysis, wsAnalysisName);
			driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'HOME')]")).click();
	//Navigate Inbox
			String navigateInbox=lib1.getExcelData("DashboardNavigation", i, 14, filePath);
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:formworkflowitem:workflowitem_component_data')]//tr/td[1]/div/a[contains(text(),'EA-aersStepOutcome (Azacitidine)')]")).click();			
			String inboxAlert=driver.findElement(By.xpath("//form[contains(@id,'itemDetailForm')]//td[2]/span[contains(text(),'"+navigateInbox+"')]")).getText();
			Assertions.assertText(inboxAlert, navigateInbox);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'HOME')]")).click();
	//Download report	
			String downloadReport=lib1.getExcelData("DashboardNavigation", i, 15, filePath);
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:formworkspacerport:workspacerport_component_data')]//tr/td[1]/div/a[contains(text(),'"+downloadReport+"')]")).click();
			Thread.sleep(5000);
			}
		}
		
		catch (Exception e) 
		{
			takeScreenShot(e, "Dashboard_LinkNavigation");
			e.printStackTrace();
			Assert.fail("Error in dashboard link navigation ..");
		}
	}	
}
	
