package com.ms.sapphire.inbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Inbox_Config;
public class Inbox_InAction extends Inbox_Config{
	Actions actionInbox;
@Test
public void inbox_in_action() throws InterruptedException
{
	
	doLogInInbox();
	driver.findElement(By.linkText("INBOX")).click();
	Thread.sleep(3000);
	actionInbox =new Actions(driver);
	WebElement userLogout=driver.findElement(By.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
	actionInbox.moveToElement(userLogout).build().perform();
	Thread.sleep(3000);
	driver.findElement(By.linkText("Sign Out")).click();
	System.out.println("sign out successfully");
	driver.quit();
	System.out.println("browser closed");
	Thread.sleep(2000);
	System.out.println("complete");
	Thread.sleep(2000);
	doLogInInbox();
	}
}
