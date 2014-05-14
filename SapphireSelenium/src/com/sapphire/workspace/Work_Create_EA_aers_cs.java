package com.sapphire.workspace;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
import com.test.Save_Analysis;
public class Work_Create_EA_aers_cs extends Config{
	int k;
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void TestMethodCreateEAaerscs() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			Save_Analysis sa=new Save_Analysis();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("ANALYSIS")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("analysisform:saCriteriaTab:analysisGrid:startAnalysis")).click();//create button clicked
		String analysisType=lib1.getExcelData("workspaceEA", i, 0, filePath);
		WebElement EA=driver.findElement(By.linkText(analysisType));
		act.moveToElement(EA).click();//analysis type clicked
		act.perform();
		Thread.sleep(5000);
		System.out.println("analysis clicked");
		Thread.sleep(5000);
		String drugLevel=lib1.getExcelData("workspaceEA", i, 1, filePath);
		driver.findElement(By.id("analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_label")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();			
		String drugName=lib1.getExcelData("workspaceEA", i, 2, filePath);
		driver.findElement(By.id("analysisCriteriaForm:tabView:drugsetGrid:0:genTradeName")).sendKeys(drugName);
		System.out.println("drug name passed");
		String analysisTab=lib1.getExcelData("workspaceEA", i, 10, filePath);
		if(analysisTab!=null && analysisTab.equals("Events"))
		{
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView')]//ul/li/a[contains(text(),'Events')]")).click();
			String eventLabel=lib1.getExcelData("workspaceEA", i, 11, filePath);
			Thread.sleep(2000);
			driver.findElement(By.id("analysisCriteriaForm:tabView:eventLevelId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:eventLevelId_panel')]//ul/li[contains(text(),'"+eventLabel+"')]")).click();
			String event=lib1.getExcelData("workspaceEA", i, 12, filePath);
		//	String eventGroup=lib1.getExcelData("workspaceEA", i, 31,filePath);
			if(eventLabel.equals("Standardized MedDRA Queries"))
			{
				String smqScope=lib1.getExcelData("workspaceEA", i, 13, filePath);
				driver.findElement(By.id("analysisCriteriaForm:tabView:scope_label")).click();
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:scope_panel')]//ul/li[contains(text(),'"+smqScope+"')]")).click();
				driver.findElement(By.id("analysisCriteriaForm:tabView:lookupSAEvent")).click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'eventLookupForm:smqLookupGrid')]//span/input[1]")).sendKeys(event);
				driver.findElement(By.xpath("//div[contains(@id,'eventLookupForm:smqLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+event+"')]]]//td[1]/div/div/div[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("eventLookupForm:selectEvent")).click();
				}
			else {
			driver.findElement(By.id("analysisCriteriaForm:tabView:lookupSAEvent")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("eventLookupForm:eventLookupTabView:EventLookupGrid:srcTxtId")).sendKeys(event);
			driver.findElement(By.xpath("//div[contains(@id,'eventLookupForm:eventLookupTabView:EventLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+event+"')]]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.id("eventLookupForm:selectEvent")).click();
			}
		}
		else if(analysisTab!=null && analysisTab.equals("Demography"))
		{
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView')]//ul/li/a[contains(text(),'Demography')]")).click();
			Thread.sleep(3000);
			String gender=lib1.getExcelData("workspaceEA", i, 14, filePath);
			if(gender!=null){
			driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+gender+"')]]]//td[1]/div/div[2]")).click();
			}
			String outcome=lib1.getExcelData("workspaceEA", i, 15, filePath);
			if(outcome!=null){				
			driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+outcome+"')]]]//td[1]/div/div[2]")).click();
			}
			String startAge=lib1.getExcelData("workspaceEA", i, 16, filePath);
			if(startAge!=null)
			{
			int startAgeInt=new Double(startAge).intValue();
			driver.findElement(By.id("analysisCriteriaForm:tabView:ageStart")).sendKeys(""+startAgeInt);
			String endAge=lib1.getExcelData("workspaceEA", i, 17, filePath);
			int endAgeInt=new Double(endAge).intValue();
			driver.findElement(By.id("analysisCriteriaForm:tabView:ageEnd")).sendKeys(""+endAgeInt);
			String unit=lib1.getExcelData("workspaceEA", i, 18, filePath);
			driver.findElement(By.id("analysisCriteriaForm:tabView:ageUnitId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:ageUnitId_panel')]//div/ul/li[contains(text(),'"+unit+"')]")).click();
			}
			String FDAdateFrom=lib1.getExcelData("workspaceEA", i, 19, filePath);
			if(FDAdateFrom!=null)
			{
			driver.findElement(By.id("analysisCriteriaForm:tabView:substartdate_input")).clear();
			driver.findElement(By.id("analysisCriteriaForm:tabView:substartdate_input")).sendKeys(FDAdateFrom);
			driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div[2]/button[2]")).click();
			String FDAdateTo=lib1.getExcelData("workspaceEA", i, 20, filePath);
			driver.findElement(By.id("analysisCriteriaForm:tabView:subenddate_input")).clear();
			driver.findElement(By.id("analysisCriteriaForm:tabView:subenddate_input")).sendKeys(FDAdateTo);
			driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div[2]/button[2]")).click();
			}
			String serious=lib1.getExcelData("workspaceEA", i, 21, filePath);
			if(serious!=null)
			{
			driver.findElement(By.id("analysisCriteriaForm:tabView:serious_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:serious_panel')]//div/ul/li[contains(text(),'"+serious+"')]")).click();
			}
			String country=lib1.getExcelData("workspaceEA", i, 22, filePath);
			if(country!=null){
			driver.findElement(By.xpath("//tr[td[2][label[text()='"+country+"']]]//td[1]/div/div[2]")).click();
			}
			String source=lib1.getExcelData("workspaceEA", i, 23, filePath);
			if(source!=null){
			driver.findElement(By.xpath("//tr[td[2][label[text()='"+source+"']]]//td[1]/div/div[2]")).click();
			}
		}
		String groupTab=lib1.getExcelData("workspaceEA", i, 8, filePath);
		if(groupTab.equals("Step"))
		{
			if(analysisType.equals("FDA AERS"))
			{
				Thread.sleep(2000);
				String groupByaers=lib1.getExcelData("workspaceEA", i, 9, filePath);	
				driver.findElement(By.xpath("//table[contains(@id,'analysisCriteriaForm:groupTabView:singlePanelView')]//tbody/tr/td[2]/label[contains(text(),'"+groupByaers+"')]")).click();
				System.out.println("Group by selected");
				Thread.sleep(2000);
			}
			else if(analysisType.equals("Company safety"))
			{
				Thread.sleep(2000);
				String groupByCS=lib1.getExcelData("workspaceEA", i, 9, filePath);	
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:groupTabView:singleTab')]//div/div//tr/td[2]/label[contains(text(),'"+groupByCS+"')]")).click();
				Thread.sleep(2000);
			}
		}
		else if(groupTab.equals("Parallel"))
		{
			driver.findElement(By.linkText("Parallel")).click();
			if(analysisType.equals("FDA AERS")) //Group in FDA aers
			{
				for(int j=1;j<=lib1.rowCount;j++)
				{
					String parallelGroupBy=lib1.getExcelData("parallel-nested", j, 0, filePath);
					Thread.sleep(2000);
					WebElement w=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+parallelGroupBy+"')]]]]//td[1]/div/div/div[2]"));
					w.click();	
				}
			}		
			else if(analysisType.equals("Company safety"))  //Group in CS 
			{
				for(int k=1;k<=lib1.rowCount;k++)
				{
					String CSparallelGroupBy=lib1.getExcelData("parallel-nested", k, 3, filePath);
					Thread.sleep(2000);
					WebElement wCS=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+CSparallelGroupBy+"')]]]]//td[1]/div/div/div[2]"));
					wCS.click();	
				}
			}
		}
		else if(groupTab.equals("Nested"))
		{
			driver.findElement(By.linkText("Nested")).click();
			if(analysisType.equals("FDA AERS")) //Group in FDA aers
			{
			for(k=1;k<=lib1.rowCount;k++)
				{
				String nestedGroupBy=lib1.getExcelData("parallel-nested",k, 1, filePath);
				if(nestedGroupBy!=null)
				{
				Thread.sleep(2000);
				Select sl=new Select(driver.findElement(By.id("analysisCriteriaForm:groupTabView:groupByNested")));
				sl.selectByVisibleText(nestedGroupBy);
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:groupTabView:nestedTab')]//button[1]/span")).click();	
				Thread.sleep(3000);
				}
				}
			System.out.println("Pivote selection start.....");
			String pivoteEA=lib1.getExcelData("parallel-nested",1, 2, filePath);
			if(pivoteEA!=null)
				{
				driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
				Thread.sleep(3000);
				driver.navigate().refresh();
				Thread.sleep(3000);
				WebElement wmt=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+pivoteEA+"')]]]]//td[1]/div/div/div[2][contains(@class,'ui-radiobutton-box ui-widget ui-corner-all ui-radiobutton-relative ui-state-default')]"));		
				Thread.sleep(3000);
				wmt.click();
				Thread.sleep(2000);
				System.out.println("Pivote selected : "+pivoteEA);
				}
			}
			else if(analysisType.equals("Company safety"))  //Group in CS 
			{
				for(k=1;k<=lib1.rowCount;k++)
				{
				String CSnestedGroupBy=lib1.getExcelData("parallel-nested",k, 4, filePath);	
					if(CSnestedGroupBy!=null)
					{
					Thread.sleep(2000);
					Select sl=new Select(driver.findElement(By.id("analysisCriteriaForm:groupTabView:groupByNested")));
					sl.selectByVisibleText(CSnestedGroupBy);
					driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:groupTabView:nestedTab')]//button[1]/span")).click();	
					Thread.sleep(3000);
					}
				}
				System.out.println("Pivote selection start.....");
				String pivoteCS=lib1.getExcelData("parallel-nested",1, 5, filePath);
				if(pivoteCS!=null)
				{
					driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
					Thread.sleep(3000);
					driver.navigate().refresh();
					Thread.sleep(3000);
					WebElement wmtCS=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+pivoteCS+"')]]]]//td[1]/div/div/div[2][contains(@class,'ui-radiobutton-box ui-widget ui-corner-all ui-radiobutton-relative ui-state-default')]"));		
					Thread.sleep(3000);
					wmtCS.click();
					Thread.sleep(2000);
					System.out.println("Pivote selected : "+pivoteCS);
				}
			}
			Thread.sleep(10000);	
		}
		Thread.sleep(3000);
		sa.analysisType=lib1.getExcelData("workspaceEA", i, 0, filePath);
		sa.analysisName=lib1.getExcelData("workspaceEA", i, 24,filePath);
		sa.route=lib1.getExcelData("workspaceEA", i, 25,filePath);
		sa.status=lib1.getExcelData("workspaceEA", i, 26,filePath);
		sa.priority=lib1.getExcelData("workspaceEA", i,27 ,filePath);
		sa.topicName=lib1.getExcelData("workspaceEA", i,28 ,filePath);
		sa.scheduler=lib1.getExcelData("workspaceEA", i,29 ,filePath);
		sa.remarks=lib1.getExcelData("workspaceEA", i, 30,filePath);
		sa.shareUser=lib1.getExcelData("workspaceEA", i, 32, filePath);
		sa.shareUserGroup=lib1.getExcelData("workspaceEA", i, 33, filePath);
		sa.shareNotify=lib1.getExcelData("workspaceEA", i, 34, filePath);
		sa.caseAnnotationType=lib1.getExcelData("workspaceEA", i, 35, filePath);
		sa.caseAnnotationScope=lib1.getExcelData("workspaceEA", i, 36, filePath);
		sa.caseAnnotationPriority=lib1.getExcelData("workspaceEA", i, 37, filePath);
		sa.caseAnnotationRemarks=lib1.getExcelData("workspaceEA", i, 38, filePath);
		sa.caseAnnotationFilePath=lib1.getExcelData("workspaceEA", i, 39, filePath);
		sa.existingCaseSeries=lib1.getExcelData("workspaceEA", i, 40, filePath);
		sa.newCaseSeries=lib1.getExcelData("workspaceEA", i, 41, filePath);
		sa.newCaseSeriesDesc=lib1.getExcelData("workspaceEA", i, 42, filePath);
		sa.save_Analysis();
		String el=driver.findElement(By.id("analysisDetailToolbarForm:analysisName")).getText();
		System.out.println(el);
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("ANALYSIS")).click();
		Thread.sleep(5000);
		driver.navigate().refresh();
		System.out.println("EA AERS creation complete............");
		Reporter.log("EA AERS creation complete............");
			Thread.sleep(5000);	
			}//for close
		}//try closes
		catch (Exception e) 
		{
			Assert.fail("Error in ea aers creation..");
			e.printStackTrace();
		}
	}
}
