package com.ms.sapphire.workspace;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Assertions;
import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;
import com.ms.sapphire.utility.Save_Analysis;

public class Work_DEC_Annotation extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void dec_annotate() throws Exception{
		try{
			Save_Analysis wsa=new Save_Analysis();
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText("WORKSPACE")).click();
				driver.findElement(By.linkText("ANALYSIS")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis")).click();//create button clicked
				String analysisType=lib1.getExcelData("DEC-Annotation", i, 0, filePath);
				WebElement EA=driver.findElement(By.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"+analysisType+"')]"));
				act.moveToElement(EA).click();//analysis type clicked
				act.perform();
				Thread.sleep(5000);
				String drugLevel=lib1.getExcelData("DEC-Annotation", i, 1, filePath);
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:drugLevelId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:drugLevelId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();
				Thread.sleep(3000);
				String drugName=lib1.getExcelData("DEC-Annotation", i, 3, filePath);
				if(drugName!=null)
				{
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSADrug")).click();
					driver.switchTo().activeElement();
					String dictLevel=lib1.getExcelData("DEC-Annotation", i, 2, filePath);
					driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:searchLevel_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"+dictLevel+"')]")).click();
					Thread.sleep(3000);
					
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:srcTxtId")).sendKeys(drugName);
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+drugName+"')]]]//td[1]/div/div/div[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("productLookupForm:selectProduct")).click();//drug select 
				Thread.sleep(3000);
				}
				String drugGroup=lib1.getExcelData("DEC-Annotation", i, 4, filePath);
				if(drugGroup!=null)
				{
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSADrug")).click();
					driver.switchTo().activeElement();
					driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView')]//ul/li[2]/a[contains(text(),'Group')]")).click();
					driver.findElement(By.id("productLookupForm:tabView:productGroupLookupGrid:srcTxtId")).sendKeys(drugGroup);
					driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productGroupLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
					Thread.sleep(3000);
					driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+drugGroup+"')]]]]//td[1]/div/div/div[2]")).click();
					Thread.sleep(2000);
					driver.findElement(By.id("productLookupForm:selectProduct")).click();//drug group select 
					Thread.sleep(5000);
				}
				String eventLevel=lib1.getExcelData("DEC-Annotation", i, 5, filePath);
				if(eventLevel!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:eventLevelId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:eventLevelId_panel')]//div/ul/li[contains(text(),'"+eventLevel+"')]")).click();
				}
				String event=lib1.getExcelData("DEC-Annotation", i, 9, filePath);
				if(event!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSAEvent")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId")).sendKeys(event);
				driver.findElement(By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+event+"')]]]//td[1]/div/div/div[2]")).click();
				driver.findElement(By.id("eventLookupForm:selectEvent")).click();
				Thread.sleep(2000);
				}
				String alertType=lib1.getExcelData("DEC-Annotation", i, 6, filePath);
				if(alertType!=null && alertType.equals("Hierarchy"))
				{
					driver.findElement(By.xpath("//table[contains(@id,'analysisCriteriaForm:saCriteriaTab:hierarchy')]//tbody/tr/td[1]/div/div[2]")).click();
				}
				if(alertType!=null && alertType.equals("DDI"))
				{
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:isddi')]//div[2]")).click();
				}
				String fromDate=lib1.getExcelData("DEC-Annotation", i, 12,filePath);
				String fromYear=lib1.getExcelData("DEC-Annotation", i, 13,filePath);
				String toDate=lib1.getExcelData("DEC-Annotation", i, 14,filePath);
				String toYear=lib1.getExcelData("DEC-Annotation", i, 15,filePath);
				String period=lib1.getExcelData("DEC-Annotation", i, 16,filePath);
				String past=lib1.getExcelData("DEC-Annotation", i, 17,filePath);
				if(alertType!=null && alertType.equals("Trending"))
				{
				if (fromDate !=null && fromYear !=null && toDate !=null && toYear !=null && period !=null)
				{
					int fromYearInt=new Double(fromYear).intValue();
					int toYearInt=new Double(toYear).intValue();
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendStrtQtrId_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendStrtQtrId_panel')]//div/ul/li[contains(text(),'"+fromDate+"')]")).click();
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendStrtYrrId_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendStrtYrrId_panel')]//div/ul/li[contains(text(),'"+fromYearInt+"')]")).click();
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendEndQtrId_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendEndQtrId_panel')]//div/ul/li[contains(text(),'"+toDate+"')]")).click();
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendEndYrId_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendEndYrId_panel')]//div/ul/li[contains(text(),'"+toYearInt+"')]")).click();
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPeriodId_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendPeriodId_panel')]//div/ul/li[contains(text(),'"+period+"')]")).click();
				}
				else if(fromDate ==null && fromYear ==null && toDate ==null && toYear ==null && period !=null && past!=null)
				{
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPeriodId_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendPeriodId_panel')]//div/ul/li[contains(text(),'"+period+"')]")).click();
					int pastInt=new Double(past).intValue();
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPastPeriodId")).sendKeys(""+pastInt);
				}
				}
				String thresholdPlan=lib1.getExcelData("DEC-Annotation", i, 18, filePath);
				String filter=lib1.getExcelData("DEC-Annotation", i, 19, filePath);
				if(alertType!=null && alertType!="DDI" && thresholdPlan!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:algoSelectionId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:algoSelectionId_panel')]//div/ul/li[text()='"+thresholdPlan+"']")).click();
				Thread.sleep(3000);
				}
				else if(alertType!=null && alertType!="DDI" && thresholdPlan==null && filter !=null)
				{
					driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filter+"')]]]//td[1]/div/div[2]")).click();
				}
				wsa.analysisType=lib1.getExcelData("DEC-Annotation", i, 0, filePath);
				wsa.analysisName=lib1.getExcelData("DEC-Annotation", i, 20, filePath);
				wsa.route=lib1.getExcelData("DEC-Annotation", i, 21, filePath);
				wsa.status =lib1.getExcelData("DEC-Annotation", i, 22, filePath);
				wsa.priority=lib1.getExcelData("DEC-Annotation", i, 23, filePath);
				wsa.topicName=lib1.getExcelData("DEC-Annotation", i, 24, filePath);
				wsa.scheduler=lib1.getExcelData("DEC-Annotation", i, 25, filePath);
				wsa.remarks=lib1.getExcelData("DEC-Annotation", i, 26, filePath);
				wsa.shareUser=lib1.getExcelData("DEC-Annotation", i, 27, filePath);
				wsa.shareUserGroup=lib1.getExcelData("DEC-Annotation", i, 28, filePath);
				wsa.shareNotify=lib1.getExcelData("DEC-Annotation", i, 29, filePath);
				wsa.caseAnnotationType=lib1.getExcelData("DEC-Annotation", i,30, filePath);
				wsa.caseAnnotationScope=lib1.getExcelData("DEC-Annotation", i,31, filePath);
				wsa.caseAnnotationPriority=lib1.getExcelData("DEC-Annotation", i, 32, filePath);
				wsa.caseAnnotationRemarks=lib1.getExcelData("DEC-Annotation", i,33, filePath);
				wsa.caseAnnotationFilePath=lib1.getExcelData("DEC-Annotation", i,34, filePath);
				wsa.existingCaseSeries=lib1.getExcelData("DEC-Annotation", i,35, filePath);
				wsa.newCaseSeries=lib1.getExcelData("DEC-Annotation", i,36, filePath);
				wsa.newCaseSeriesDesc=lib1.getExcelData("DEC-Annotation", i,37, filePath);
				wsa.save_Analysis();
				String decAnnotationEvent=lib1.getExcelData("DEC-Annotation", i,38, filePath);
				String decAnnotationType=lib1.getExcelData("DEC-Annotation", i,39, filePath);
				String decScope=lib1.getExcelData("DEC-Annotation", i,40, filePath);
				String decPriority=lib1.getExcelData("DEC-Annotation", i,41, filePath);
				String decStatus=lib1.getExcelData("DEC-Annotation", i,42, filePath);
				String decDateDetect=lib1.getExcelData("DEC-Annotation", i,43, filePath);
				String decDateClosed=lib1.getExcelData("DEC-Annotation", i,44, filePath);
				String decMethod=lib1.getExcelData("DEC-Annotation", i,45, filePath);
				String decAction=lib1.getExcelData("DEC-Annotation", i,46, filePath);
				String decSource=lib1.getExcelData("DEC-Annotation", i,47, filePath);
				String decRemarks=lib1.getExcelData("DEC-Annotation", i,48, filePath);
				String decFile=lib1.getExcelData("DEC-Annotation", i,49, filePath);
				if(decAnnotationType!=null)
				{
				driver.findElement(By.xpath("//tr[td[7][div[contains(text(),'"+decAnnotationEvent+"')]]]//td[1]//div[2]")).click();
				driver.findElement(By.xpath("//form[contains(@id,'analysisDetailToolbarForm')]//button/span[contains(text(),'Annotate')]")).click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("appDECAnnotateForm:dec_listAnnotateType_label")).click();	
				driver.findElement(By.xpath("//div[contains(@id,'appDECAnnotateForm:dec_listAnnotateType_panel')]//li[contains(text(),'"+decAnnotationType+"')]")).click();	
				driver.findElement(By.id("appDECAnnotateForm:dec_listAnnotateScope_label")).click();	
				driver.findElement(By.xpath("//div[contains(@id,'appDECAnnotateForm:dec_listAnnotateScope_panel')]//li[contains(text(),'"+decScope+"')]")).click();	
				if(decPriority!=null)
				{
				driver.findElement(By.id("appDECAnnotateForm:priority_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'appDECAnnotateForm:priority_panel')]//li[contains(text(),'"+decPriority+"')]")).click();
				}
				if(decStatus!=null)
				{
				driver.findElement(By.id("appDECAnnotateForm:declistAnnotateStatus_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'appDECAnnotateForm:declistAnnotateStatus_panel')]//li[contains(text(),'"+decStatus+"')]")).click();
				}
				if(decDateDetect!=null)
				{
				driver.findElement(By.id("appDECAnnotateForm:dateDetectedId_input")).clear();
				driver.findElement(By.id("appDECAnnotateForm:dateDetectedId_input")).sendKeys(decDateDetect);
				}
				if(decDateClosed!=null)
				{
				driver.findElement(By.id("appDECAnnotateForm:dateCloseId_input")).clear();
				driver.findElement(By.id("appDECAnnotateForm:dateCloseId_input")).sendKeys(decDateClosed);
				}
				if(decMethod!=null)
				{
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+decMethod+"')]]]//td[1]//div[2]")).click();
				}
				if(decAction!=null)
				{
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+decAction+"')]]]//td[1]//div[2]")).click();
				}
				if(decSource!=null)
				{
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+decSource+"')]]]//td[1]//div[2]")).click();
				}
		/*		if(decRemarks!=null)
				{
				Thread.sleep(3000);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				 jse.executeScript("scroll(0, 250)");
				 driver.findElement(By.xpath("//table[contains(@id,'appListEditDECAnnotForm:dec_lstaddRemarkPanel')]//textarea")).click();
				driver.findElement(By.xpath("//table[contains(@id,'appListEditDECAnnotForm:dec_lstaddRemarkPanel')]//textarea")).sendKeys(decRemarks);
				}
				driver.findElement(By.xpath("//div[contains(@id,'appListEditDECAnnotForm:appListEditAnnotDtlDlg')]//div[3]/span/div[contains(@id,'appListEditDECAnnotForm:dec_lstfileUploadId')]")).click();
				Thread.sleep(3000);	
				setClipboardData(decFile);
				Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(5000);
			*/
				driver.findElement(By.id("appDECAnnotateForm:dec_lstappDECAnnotate")).click();
				String alertText=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div[2]/p")).getText();
				System.out.println(alertText);
				Assert.assertEquals(alertText, "Annotation saved successfully!");
				Thread.sleep(3000);
				}
				Thread.sleep(10000);
			}
		}//try
		catch (Throwable e) 
		{
		//	Assert.fail("Error in DEC Annotation ..");
			e.printStackTrace();
		}
	}
}
