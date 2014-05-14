package com.sapphire.workspace;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
import com.test.Save_Analysis;

public class Work_Create_External extends Config{
	
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
		@Test
		public void TestMethodCreateMedline() throws Exception{
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
			String analysisType=lib1.getExcelData("WF-external", i, 0, filePath);
			WebElement unstructured=driver.findElement(By.linkText(analysisType));
			act.moveToElement(unstructured).click();//analysis type clicked
			act.perform();
			Thread.sleep(5000);
			System.out.println("analysis clicked");
			Thread.sleep(5000);
			String analysisName=lib1.getExcelData("WF-external", i, 1, filePath);
			driver.findElement(By.id("analysisDetailForm:rootAnalysisName")).sendKeys(analysisName);
			String type=lib1.getExcelData("WF-external", i,2, filePath);
			driver.findElement(By.id("analysisDetailForm:unstructType_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisDetailForm:unstructType_panel')]//div/ul/li[contains(text(),'"+type+"')]")).click();
			String topic=lib1.getExcelData("WF-external", i,3, filePath);
			driver.findElement(By.id("analysisDetailForm:lookupTopic")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'assignTopicForm:topicHeaderGridSave')]//span/input")).sendKeys(topic);
			driver.findElement(By.xpath("//div[contains(@id,'assignTopicForm:topicHeaderGridSave')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+topic+"')]]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'assignTopicForm:topicLookupDlg')]//div[3]/span/button[1]/span")).click();
			String priority=lib1.getExcelData("WF-external", i,4, filePath);
			driver.findElement(By.id("analysisDetailForm:priorityDropdown_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisDetailForm:priorityDropdown_panel')]//div/ul/li[contains(text(),'"+priority+"')]")).click();
			String status=lib1.getExcelData("WF-external", i,5, filePath);
			driver.findElement(By.id("analysisDetailForm:statusDropdown_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisDetailForm:statusDropdown_panel')]//div/ul/li[contains(text(),'"+status+"')]")).click();
			String remarks=lib1.getExcelData("WF-external", i,6, filePath);
			driver.findElement(By.id("analysisDetailForm:rootAnalysisRem")).sendKeys(remarks);
			//Documents
			String docName=lib1.getExcelData("WF-external", i,7, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'analysisDetailForm:analysisUnstructDocGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("unstructAnalysisUnsaveDocForm:Title")).sendKeys(docName);
			String docType=lib1.getExcelData("WF-external", i,8, filePath);
			driver.findElement(By.id("unstructAnalysisUnsaveDocForm:typeId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'unstructAnalysisUnsaveDocForm:typeId_panel')]//div/ul/li[text()='"+docType+"']")).click();
			String docRemarks=lib1.getExcelData("WF-external", i,9, filePath);
			driver.findElement(By.id("unstructAnalysisUnsaveDocForm:description")).sendKeys(docRemarks);
			String docFile=lib1.getExcelData("WF-external", i,10, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'unstructAnalysisUnsaveDocForm:unstructAnalysisUnsaveDocDlg')]//div[3]//div[contains(@id,'unstructAnalysisUnsaveDocForm:uploadNewAtrId')]")).click();
			Thread.sleep(3000);	
			setClipboardData(docFile);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			driver.findElement(By.id("unstructAnalysisUnsaveDocForm:addAnalysisDoc")).click();
			String member=lib1.getExcelData("WF-external", i,11, filePath);
			Thread.sleep(2000);
			driver.findElement(By.id("analysisDetailForm:sharedMemGrid:createNew")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userLookupGrid')]//span/input")).sendKeys(member);
			driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'john')]]]//td[1]/div/div")).click();
			driver.findElement(By.id("userLookupForm:selectUser")).click();
			String memberGroup=lib1.getExcelData("WF-external", i,12, filePath);
			if(memberGroup!=null)
			{
				Thread.sleep(2000);
				driver.findElement(By.id("analysisDetailForm:sharedMemGrid:createNew")).click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView')]//ul/li[2]/a")).click();
				driver.findElement(By.xpath("//div[contains(@id,'userLookupForm:tabView:userGroupLookupGrid')]//span/input")).sendKeys(memberGroup);
				driver.findElement(By.xpath(" //div[contains(@id,'userLookupForm:tabView:userGroupLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+memberGroup+"')]]]//td[1]/div/div")).click();
				driver.findElement(By.id("userLookupForm:selectUser")).click();
			}
			
			driver.findElement(By.id("analysisDetailForm:saveUnstructAnalysis")).click();
			Thread.sleep(5000);
			String route=lib1.getExcelData("WF-external", i,13, filePath);
			if(route.equals("Y"))
			{
				driver.findElement(By.id("analysisDetailForm:routeBtn")).click();
				Thread.sleep(5000);
			}
		}//for
			}//try
			catch (Exception e) 
			{
				Assert.fail("Error in external creation..");
				e.printStackTrace();
			}
			
		}//method	
			
}
