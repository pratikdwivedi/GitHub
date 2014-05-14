package com.test;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import com.test.*;

import org.openqa.selenium.By;

public class Save_Analysis extends Config{
	String filePath;
	int i;
	public static String analysisType,analysisName,status,priority,route,topicName,scheduler,remarks,shareUser,shareUserGroup,shareNotify,caseAnnotationType,caseAnnotationScope,caseAnnotationPriority,caseAnnotationRemarks,caseAnnotationFilePath,existingCaseSeries,newCaseSeries,newCaseSeriesDesc;
	public void save_Analysis() throws InterruptedException, AWTException
	{
		ExcelLibrary lib1=new ExcelLibrary();
		driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
		System.out.println("Execute pressed");
		Thread.sleep(10000);
//Export
		driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left')]//span/button[1]/span[contains(text(),'Export')]")).click();
		driver.findElement(By.xpath("//body[contains(@class,'ui-layout-container')]//ul[1]/li[1]/a[text()='Excel']")).click();
		Thread.sleep(10000);
		driver.findElement(By.id("analysisDetailToolbarForm:save")).click();
		System.out.println("save clicked");
		driver.switchTo().activeElement();
		driver.findElement(By.id("saveAnalysisForm:saveTabPanel:rootAnalysisName")).sendKeys(analysisName);
		if(status!=null)
		{
		driver.findElement(By.id("saveAnalysisForm:saveTabPanel:statusDropdown_label")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@id,'saveAnalysisForm:saveTabPanel:statusDropdown_panel')]//div/ul/li[contains(text(),'"+status+"')]")).click();
		}
		if(priority!=null)
		{
		driver.findElement(By.id("saveAnalysisForm:saveTabPanel:priorityDropdown_label")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@id,'saveAnalysisForm:saveTabPanel:priorityDropdown_panel')]//div/ul/li[contains(text(),'"+priority+"')]")).click();
		}
		if(topicName!=null)
		{
		driver.findElement(By.id("saveAnalysisForm:saveTabPanel:lookupTopic")).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//div[contains(@id,'assignTopicForm:topicHeaderGridSave')]//span/input")).sendKeys(topicName);
		driver.findElement(By.xpath("//div[contains(@id,'assignTopicForm:topicHeaderGridSave')]//span/button[1]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+topicName+"')]]]//td[1]/div[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@id,'assignTopicForm:topicLookupDlg')]//div[3]/span/button[1]/span")).click();
		Thread.sleep(5000);
		}
		if(topicName!=null && route!=null && route.equals("Y"))
		{
			driver.findElement(By.xpath("//div[contains(@id,'saveAnalysisForm:saveTabPanel:route')]//div[2]")).click();
		}
		else{
			System.out.println("route is not selected");
		}
		if(scheduler!=null)
		{
			driver.findElement(By.id("saveAnalysisForm:saveTabPanel:lookupScheduler")).click();
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(scheduler);
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerGrid')]//table/tbody/tr/td/div/div/div[2]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerPanelDlg')]//div[3]/span/button[1]/span")).click();
		}
		if(remarks!=null)
		{
			driver.findElement(By.id("saveAnalysisForm:saveTabPanel:rootAnalysisRem")).sendKeys(remarks);
		}
		driver.findElement(By.id("saveAnalysisForm:saveAnalysis")).click();
	//	driver.findElement(By.id("saveAnalysisForm:close")).click();//close
		Thread.sleep(10000);
	//Sharing Analysis
		if(shareUser!=null)
		{
			driver.findElement(By.id("analysisDetailToolbarForm:share")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("sharedAccessForm:userGroupGrid:createNew")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView')]//ul/li/a[text()='User']")).click();
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userLookupGrid')]//span/input")).sendKeys(shareUser);
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+shareUser+"')]]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.id("userLookupForm:selectUser")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("sharedAccessForm:save")).click();
			Thread.sleep(10000);
		}
		if(shareUserGroup!=null)
		{
			driver.findElement(By.id("analysisDetailToolbarForm:share")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("sharedAccessForm:userGroupGrid:createNew")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView')]//ul/li/a[text()='User group']")).click();
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userGroupLookupGrid')]//span/input")).sendKeys(shareUserGroup);
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userGroupLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+shareUserGroup+"')]]]//td[1]/div/div/div[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("userLookupForm:selectUser")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("sharedAccessForm:save")).click();
		}
		if(shareNotify!=null && shareNotify.equals("Y"))
		{
			driver.findElement(By.id("analysisDetailToolbarForm:share")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'sharedAccessForm:userGroupGrid:notifyId')]//div[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("sharedAccessForm:save")).click();
			Thread.sleep(5000);
		}
	//show case annotation
		if(!analysisType.equals("Medline"))
		{
		
		if(caseAnnotationType!=null)
		{
			driver.findElement(By.id("analysisDetailToolbarForm:showCases")).click();
			Thread.sleep(4000);
		driver.findElement(By.xpath(" //tbody[contains(@id,'caseListForm:caseGrid_data')]//tr/td[1]//div[2]")).click();
		driver.findElement(By.xpath("//div[contains(@id,'caseListForm:caseGrid')]//span/button[4]/span")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("appAnnotateForm:listAnnotateType_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:listAnnotateType_panel')]//div/ul/li[text()='"+caseAnnotationType+"']")).click();
		if(caseAnnotationScope!=null)
		{
		driver.findElement(By.id("appAnnotateForm:listAnnotateScope_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:listAnnotateScope_panel')]//div/ul/li[contains(text(),'"+caseAnnotationScope+"')]")).click();
		}
		if(caseAnnotationPriority!=null)
		{
			driver.findElement(By.id("appAnnotateForm:priority_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:priority_panel')]//div/ul/li[contains(text(),'"+caseAnnotationPriority+"')]")).click();
		}
		if(caseAnnotationRemarks!=null)
		{
			driver.findElement(By.id("appAnnotateForm:listAnnotateRem")).sendKeys(caseAnnotationRemarks);
		}
		if(caseAnnotationFilePath!=null)
		{
			driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:appAnnoateDlg')]//div[contains(@id,'appAnnotateForm:lstfileUploadId')]")).click();
			Thread.sleep(3000);	
			setClipboardData(caseAnnotationFilePath);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
		}
		driver.findElement(By.id("appAnnotateForm:lstappAnnoate")).click();
		driver.findElement(By.xpath("//a[contains(@class,'ui-layout-unit-header-icon ui-state-default ui-corner-all')]//span[contains(@class,'ui-icon ui-icon-triangle-1-s')]")).click();
		Thread.sleep(2000);
		}//show case with annotation
	//case series
		if(existingCaseSeries!=null)
		{
		driver.findElement(By.id("analysisDetailToolbarForm:showCases")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(" //tbody[contains(@id,'caseListForm:caseGrid_data')]//tr/td[1]//div[2]")).click();
		driver.findElement(By.xpath("//div[contains(@id,'caseListForm:caseGrid')]//span/button[3]/span")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'Select a Case Series')]]]//td[1]/div/div[2]")).click();
		driver.findElement(By.id("saveCaseSeriesForm:selectedCaseSeriesId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'saveCaseSeriesForm:selectedCaseSeriesId_panel')]//div/ul/li[contains(text(),'"+existingCaseSeries+"')]")).click();
		driver.findElement(By.id("saveCaseSeriesForm:saveCaseSeries")).click();
		Thread.sleep(2000);
		}
		else if(existingCaseSeries==null && newCaseSeries!=null)
		{
			driver.findElement(By.id("analysisDetailToolbarForm:showCases")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(" //tbody[contains(@id,'caseListForm:caseGrid_data')]//tr/td[1]//div[2]")).click();
		driver.findElement(By.xpath("//div[contains(@id,'caseListForm:caseGrid')]//span/button[3]/span")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'Create a new Case Series')]]]//td[1]/div/div[2]")).click();
			driver.findElement(By.id("saveCaseSeriesForm:csNameId")).sendKeys(newCaseSeries);
			driver.findElement(By.id("saveCaseSeriesForm:csDescNameId")).sendKeys(newCaseSeriesDesc);
			driver.findElement(By.id("saveCaseSeriesForm:saveCaseSeries")).click();
			Thread.sleep(2000);
		}
	//	driver.findElement(By.xpath("//a[contains(@class,'ui-layout-unit-header-icon ui-state-default ui-corner-all')]//span[contains(@class,'ui-icon ui-icon-triangle-1-s')]")).click();
		Thread.sleep(2000);
	//cases export
		driver.findElement(By.id("analysisDetailToolbarForm:showCases")).click();
		Thread.sleep(8000);
		driver.findElement(By.xpath("//span[contains(@id,'caseListForm:caseGrid:expDataCtrlPnl')]//span/button[1]/span[2]")).click();
		driver.findElement(By.xpath("//ul[contains(@class,'ui-menu-list ui-helper-reset')]//li/a[contains(text(),'PDF')]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[contains(@class,'ui-layout-unit-header-icon ui-state-default ui-corner-all')]//span[contains(@class,'ui-icon ui-icon-triangle-1-s')]")).click();
		Thread.sleep(20000);
	}
	}	
}
