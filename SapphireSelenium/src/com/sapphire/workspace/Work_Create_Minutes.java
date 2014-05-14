package com.sapphire.workspace;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Work_Create_Minutes extends Config {
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
	@Test
	public void TestMethodCreateMinutes() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("MINUTES")).click();
		driver.findElement(By.id("minutesform:minutesGrid:createMinutes")).click();
		Thread.sleep(3000);
		String minuteName=lib1.getExcelData("WF-minutes", i, 0, filePath);
		if(minuteName!=null)
		{
		driver.findElement(By.id("minutesDetailForm:nameid")).sendKeys(minuteName);
		String minuteID=lib1.getExcelData("WF-minutes", i, 1, filePath);
		driver.findElement(By.id("minutesDetailForm:meetingid")).sendKeys(minuteID);
	/*	String publishToTeam=lib1.getExcelData("WF-minutes", i, 2, filePath);
		if(publishToTeam.equals("Y"))
			{
			driver.findElement(By.xpath("//div[contains(@id,'minutesDetailForm:publishtoteam')]//div[2]")).click();
			}
	*/
		String minutesDesc=lib1.getExcelData("WF-minutes", i, 3, filePath);
		driver.findElement(By.id("minutesDetailForm:descriptionId")).sendKeys(minutesDesc);
		String minutesDocName=lib1.getExcelData("WF-minutes", i, 4, filePath);
		if(minutesDocName!=null)
		{
			driver.findElement(By.id("minutesDetailForm:docGrid:addDoc")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("supDocDetailForm:Title")).sendKeys(minutesDocName);
			String minutesDocTemplate=lib1.getExcelData("WF-minutes", i, 5, filePath);
			driver.findElement(By.id("supDocDetailForm:typeId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'supDocDetailForm:typeId_panel')]//li[contains(text(),'"+minutesDocTemplate+"')]")).click();		
			String minutesDocDesc=lib1.getExcelData("WF-minutes", i, 6, filePath);
			driver.findElement(By.id("supDocDetailForm:description")).sendKeys(minutesDocDesc);
			String minutesDocFile=lib1.getExcelData("WF-minutes", i, 7, filePath);	
			driver.findElement(By.xpath(" //div[contains(@id,'supDocDetailForm:supDocDtlDlg')]//div[3]/span/div[contains(@id,'supDocDetailForm:uploadNewAtrId')]")).click();
			Thread.sleep(3000);	
			setClipboardData(minutesDocFile);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			driver.findElement(By.id("supDocDetailForm:close")).click();	
			}
		String minutesMaster=lib1.getExcelData("WF-minutes", i,8, filePath);
		if(minutesMaster!=null)
		{
			driver.findElement(By.xpath("//div[contains(@id,'minutesDetailForm:masterGrid')]//button[1]/span")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'masterLookupForm:masterLookupGrid')]//span/input[1]")).sendKeys(minutesMaster);
			driver.findElement(By.xpath("//div[contains(@id,'masterLookupForm:masterLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(" //tr[td[2][div[contains(text(),'"+minutesMaster+"')]]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.id("masterLookupForm:selectMaster")).click();
			
		}
/*		String minutesTopic=lib1.getExcelData("WF-minutes", i, 9, filePath);
		if(minutesTopic!=null)
		{
			driver.findElement(By.xpath("//div[contains(@id,'minutesDetailForm:topicGrid')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicLookupGrid')]//span/input")).sendKeys(minutesTopic);
			driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(" //tr[td[2][div[text()='"+minutesTopic+"']]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.id("topicLookupForm:selectTopic")).click();
		}
*/		String minutesNotify=lib1.getExcelData("WF-minutes", i, 10, filePath);
		if(minutesNotify!=null)
		{
			driver.findElement(By.id("minutesDetailForm:notifyOptioniD_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'minutesDetailForm:notifyOptioniD_panel')]//li[contains(text(),'Notify')]")).click();
			String minutesNotifyUser=lib1.getExcelData("WF-minutes", i, 11, filePath);
			driver.findElement(By.id("minutesDetailForm:notifyUserLookupButton")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'minuteNotifyUserLookupForm:userLookupGrid')]//span/input")).sendKeys(minutesNotifyUser);
			driver.findElement(By.xpath("//div[contains(@id,'minuteNotifyUserLookupForm:userLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath(" //tr[td[2][div[contains(text(),'"+minutesNotifyUser+"')]]]//td[1]/div/div/div[2]")).click();
			driver.findElement(By.id("minuteNotifyUserLookupForm:selectUser")).click();
			
		}
		driver.findElement(By.id("minutesDetailForm:saveProduct")).click();
		Thread.sleep(3000);
		String minutesPublish=lib1.getExcelData("WF-minutes", i, 12, filePath);
		if(minutesPublish!=null && minutesPublish.equals("Y"))
			{
			driver.findElement(By.id("minutesDetailForm:publishId")).click();
			Thread.sleep(3000);	
			}
		}//minute name if
			}//for
		}//try
		
		catch (Exception e) 
		{
			e.printStackTrace();
			Assert.fail("Error in minutes creation..");
			
		}
	}
}
