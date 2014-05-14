package com.sapphire.workspace;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Work_Delete_Minutes extends Config{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE_DELETE);
	@Test
	public void TestMethodDeleteMinutes() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("WORKSPACE")).click();
		driver.findElement(By.linkText("MINUTES")).click();
		String minuteName=lib1.getExcelData("deleteMinutes", i, 0, filePath);
		if(minuteName!=null)
		{
			driver.findElement(By.xpath("//td/div/a[contains(text(),'"+minuteName+"')]")).click();
			String minutesStatus=driver.findElement(By.xpath("//div[contains(@id,'minutesDetailForm:savepnl')]//button[4]")).getAttribute("aria-disabled");
			System.out.println(minutesStatus);
			driver.findElement(By.id("minutesDetailForm:cancle")).click();
			Thread.sleep(3000);
			if(minutesStatus.toString().equals("false"))
			{
		driver.findElement(By.xpath("//div[contains(@id,'minutesform:minutesGrid')]//input[contains(@type,'text')]")).sendKeys(minuteName);
		driver.findElement(By.xpath("//div[contains(@id,'minutesform:minutesGrid')]//button[1]/span[contains(text(),'Search')]")).click();
		Thread.sleep(3000);	
		driver.findElement(By.xpath("//tr[td[2][div[a[contains(text(),'"+minuteName+"')]]]]//td[1]//div[2]")).click();
		driver.findElement(By.id("minutesform:minutesGrid:removeMin")).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
		String msgMinutes=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p")).getText();
		System.out.println(msgMinutes);
		Assert.assertEquals(msgMinutes, "Selected record(s) are deleted successfully");
		Thread.sleep(3000);
			}
			else if(minutesStatus.toString().equals("true"))
			{
				driver.findElement(By.xpath("//div[contains(@id,'minutesform:minutesGrid')]//input[contains(@type,'text')]")).sendKeys(minuteName);
				driver.findElement(By.xpath("//div[contains(@id,'minutesform:minutesGrid')]//button[1]/span[contains(text(),'Search')]")).click();
				Thread.sleep(3000);	
				driver.findElement(By.xpath("//tr[td[2][div[a[contains(text(),'"+minuteName+"')]]]]//td[1]//div[2]")).click();
				driver.findElement(By.id("minutesform:minutesGrid:removeMin")).click();
				Thread.sleep(1000);
				String msgMinutes=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div/p")).getText();
				System.out.println(msgMinutes);
				Assert.assertEquals(msgMinutes, "Could not remove, some of selected Minutes are published");
				Thread.sleep(3000);
			}
		}//minute name if
			}//for
		}//try
		
		catch (Throwable e) 
		{
			takeScreenShot(e, "MinutesDelete");
			Assert.fail("Error in minutes delete..");
			e.printStackTrace();
		}
	}
}
