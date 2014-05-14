package com.sapphire.dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import com.test.Config;
public class Dashboard_DragAndDrop extends Config{
	
	@Test
	public void TestMethodDragAndDrop() throws Exception{
		try{
		Thread.sleep(5000);
		WebElement analysis1=driver.findElement(By.xpath("//div[contains(@id,'tabView:dashBoard')]//div[3]/div/div[contains(@id,'tabView:analysis_header')]/span"));		
		WebElement analysis2=driver.findElement(By.xpath("//div[contains(@id,'tabView:dashBoard')]//div[1]/div[contains(@id,'tabView:docket')]"));		
		act.dragAndDrop(analysis1, analysis2);	
		
		}
		catch (Exception e) 
		{
			  takeScreenShot(e, "Dashboard_DragAndDrop");
			Assert.fail("Error in home drag & drop ..");
			e.printStackTrace();
		}
	}
}
