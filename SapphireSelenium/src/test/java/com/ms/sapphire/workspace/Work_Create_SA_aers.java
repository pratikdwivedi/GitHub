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

public class Work_Create_SA_aers extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void create_sa_aers() throws Exception{
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
				String analysisType=lib1.getExcelData("work-sa-aers", i, 0, filePath);
				WebElement EA=driver.findElement(By.xpath("//div[contains(@id,'analysisform:saCriteriaTab:analysisGrid:startAnalysisPanel')]//td[2]//tr/td[1]/a/label[contains(text(),'"+analysisType+"')]"));
				act.moveToElement(EA).click();//analysis type clicked
				act.perform();
				Thread.sleep(5000);
				String drugLevel=lib1.getExcelData("work-sa-aers", i, 1, filePath);
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:drugLevelId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:drugLevelId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();
				Thread.sleep(3000);
				String drugName=lib1.getExcelData("work-sa-aers", i, 3, filePath);
				if(drugName!=null)
				{
					driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:lookupSADrug")).click();
					driver.switchTo().activeElement();
					String dictLevel=lib1.getExcelData("work-sa-aers", i, 2, filePath);
					driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:searchLevel_label")).click();
					driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"+dictLevel+"')]")).click();
					Thread.sleep(3000);
					
				driver.findElement(By.id("productLookupForm:tabView:productLookupGrid:srcTxtId")).sendKeys(drugName);
				driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:tabView:productLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(4000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+drugName.toUpperCase()+"')]]]//td[1]/div/div/div[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("productLookupForm:selectProduct")).click();//drug select 
				Thread.sleep(3000);
				}
				String drugGroup=lib1.getExcelData("work-sa-aers", i, 4, filePath);
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
				String eventLevel=lib1.getExcelData("work-sa-aers", i, 5, filePath);
				if(eventLevel!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:eventLevelId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:eventLevelId_panel')]//div/ul/li[contains(text(),'"+eventLevel+"')]")).click();
				}
				String event=lib1.getExcelData("work-sa-aers", i, 9, filePath);
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
				String alertType=lib1.getExcelData("work-sa-aers", i, 6, filePath);
				if(alertType.equals("Hierarchy"))
				{
					driver.findElement(By.xpath("//table[contains(@id,'analysisCriteriaForm:saCriteriaTab:hierarchy')]//tbody/tr/td[1]/div/div[2]")).click();
				}
				if(alertType.equals("DDI"))
				{
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:isddi')]//div[2]")).click();
				}
				String fromDate=lib1.getExcelData("work-sa-aers", i, 12,filePath);
				String fromYear=lib1.getExcelData("work-sa-aers", i, 13,filePath);
				String toDate=lib1.getExcelData("work-sa-aers", i, 14,filePath);
				String toYear=lib1.getExcelData("work-sa-aers", i, 15,filePath);
				String period=lib1.getExcelData("work-sa-aers", i, 16,filePath);
				String past=lib1.getExcelData("work-sa-aers", i, 17,filePath);
				if(alertType.equals("Trending"))
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
				String thresholdPlan=lib1.getExcelData("work-sa-aers", i, 18, filePath);
				String filter=lib1.getExcelData("work-sa-aers", i, 19, filePath);
				if(alertType!="DDI" && thresholdPlan!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:algoSelectionId_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:algoSelectionId_panel')]//div/ul/li[text()='"+thresholdPlan+"']")).click();
				Thread.sleep(3000);
				}
				else if(alertType!="DDI" && thresholdPlan==null && filter !=null)
				{
					driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filter+"')]]]//td[1]/div/div[2]")).click();
				}
				wsa.analysisType=lib1.getExcelData("work-sa-aers", i, 0, filePath);
				wsa.analysisName=lib1.getExcelData("work-sa-aers", i, 20, filePath);
				wsa.route=lib1.getExcelData("work-sa-aers", i, 21, filePath);
				wsa.status =lib1.getExcelData("work-sa-aers", i, 22, filePath);
				wsa.priority=lib1.getExcelData("work-sa-aers", i, 23, filePath);
				wsa.topicName=lib1.getExcelData("work-sa-aers", i, 24, filePath);
				wsa.scheduler=lib1.getExcelData("work-sa-aers", i, 25, filePath);
				wsa.remarks=lib1.getExcelData("work-sa-aers", i, 26, filePath);
				wsa.shareUser=lib1.getExcelData("work-sa-aers", i, 27, filePath);
				wsa.shareUserGroup=lib1.getExcelData("work-sa-aers", i, 28, filePath);
				wsa.shareNotify=lib1.getExcelData("work-sa-aers", i, 29, filePath);
				wsa.caseAnnotationType=lib1.getExcelData("work-sa-aers", i,30, filePath);
				wsa.caseAnnotationScope=lib1.getExcelData("work-sa-aers", i,31, filePath);
				wsa.caseAnnotationPriority=lib1.getExcelData("work-sa-aers", i, 32, filePath);
				wsa.caseAnnotationRemarks=lib1.getExcelData("work-sa-aers", i,33, filePath);
				wsa.caseAnnotationFilePath=lib1.getExcelData("work-sa-aers", i,34, filePath);
				wsa.existingCaseSeries=lib1.getExcelData("work-sa-aers", i,35, filePath);
				wsa.newCaseSeries=lib1.getExcelData("work-sa-aers", i,36, filePath);
				wsa.newCaseSeriesDesc=lib1.getExcelData("work-sa-aers", i,37, filePath);
	//			wsa.save_Analysis();
				Thread.sleep(10000);
			}
		}//try
		catch (Throwable e) 
		{
			e.printStackTrace();
			Assert.fail("Error in sa aers creation..");
			
		}
	}
}
