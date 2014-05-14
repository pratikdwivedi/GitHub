package com.test;


import com.test.*;

import org.openqa.selenium.By;

public class Work_Save_Report extends Config{
	String filePath;
	int i;
	public static String scheduleCheck,reportScheduler,reportGroup,reportName,reportDesc,reportNotification,reportUserNotify,reportShareNotify,reportShareUser,reportShareUserGroup;
	
	public void save_Report() throws InterruptedException
	{
		ExcelLibrary lib1=new ExcelLibrary();
		if(scheduleCheck!=null && scheduleCheck.equals("Y"))
			{
			driver.findElement(By.id("reportfieldsform:ScheduleId")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.id("saveReportForm:assignedScheduler")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerGrid')]//span/input")).sendKeys(reportScheduler);		
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerGrid')]//span/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+reportScheduler+"')]]]//td[1]//div[2]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'assignSchedulerForm:schedulerPanel')]//div[3]/span/button[1]/span")).click();
			}
		else {
			driver.findElement(By.id("reportfieldsform:SaveId")).click();
			}
		Thread.sleep(5000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("saveReportForm:reportGroupListId_label")).click();
		driver.findElement(By.xpath("//div/ul/li[contains(text(),'"+reportGroup+"')]")).click();
	
		driver.findElement(By.id("saveReportForm:reportNameId")).sendKeys(reportName);
		driver.findElement(By.id("saveReportForm:reportDescId")).sendKeys(reportDesc);
		if(reportNotification!=null)
		{
		driver.findElement(By.id("saveReportForm:notifyOptioniD_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'saveReportForm:notifyOptioniD_panel')]//div/ul/li[text()='"+reportNotification+"']")).click();
		driver.findElement(By.id("saveReportForm:notifyUserLookupButton")).click();
		Thread.sleep(2000);
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//div[contains(@id,'reportNotifyUserForm:userLookupGrid')]//span/input")).sendKeys(reportUserNotify);	
		driver.findElement(By.xpath("//div[contains(@id,'reportNotifyUserForm:userLookupGrid')]//span/button[1]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+reportUserNotify+"')]]]//td[1]//div[2]")).click();
		driver.findElement(By.id("reportNotifyUserForm:selectNotifyUser")).click();
		}
		if(reportShareNotify!=null && reportShareNotify.equals("Y"))
		{
			driver.findElement(By.xpath("//div[contains(@id,'saveReportForm:userGroupGrid:notifyId')]//div[2]")).click();
			if(reportShareUser!=null)
			{
			driver.findElement(By.id("saveReportForm:userGroupGrid:createNew")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'rep_userLookupForm:tabView:userLookupGrid')]//span/input")).sendKeys(reportShareUser);
			driver.findElement(By.xpath("//div[contains(@id,'rep_userLookupForm:tabView:userLookupGrid')]//span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'john')]]]//td[1]//div[2]")).click();
			driver.findElement(By.id("rep_userLookupForm:selectUser")).click();
			Thread.sleep(2000);
			}
			if(reportShareUserGroup!=null)
			{
				driver.findElement(By.id("saveReportForm:userGroupGrid:createNew")).click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'rep_userLookupForm:tabView')]//ul/li/a[contains(text(),'User group')]")).click();
				driver.findElement(By.xpath("//div[contains(@id,'rep_userLookupForm:tabView:userGroupLookupGrid')]//span/input")).sendKeys(reportShareUserGroup);
				driver.findElement(By.xpath("//div[contains(@id,'rep_userLookupForm:tabView:userGroupLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'safety user')]]]//td[1]//div[2]")).click();
				driver.findElement(By.id("rep_userLookupForm:selectUser")).click();
			}
		}
			driver.findElement(By.id("saveReportForm:saveReport")).click();//save
		driver.findElement(By.id("saveReportForm:close")).click();
		Thread.sleep(5000);
	}
}
