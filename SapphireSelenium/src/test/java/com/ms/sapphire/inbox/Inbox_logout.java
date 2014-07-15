package com.ms.sapphire.inbox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Inbox_logout {
	public static WebDriver driver;
	public static void doLogout() throws InterruptedException
	{
		
		Actions action=new Actions(driver);
		WebElement userLogout = driver.findElement(By.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
		action.moveToElement(userLogout).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		driver.quit();
	}
}
