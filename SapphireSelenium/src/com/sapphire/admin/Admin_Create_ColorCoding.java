package com.sapphire.admin;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_Create_ColorCoding extends Config{
	@Test
	public void TestMethodCreateUser() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'ADMIN')]")).click();
		System.out.println("Admin clicked ");
		driver.findElement(By.linkText("Color coding")).click();
		String algo=lib1.getExcelData("colorCoding", i, 0,filePath);
		if(algo!=null)
		{
			driver.findElement(By.xpath("//div[contains(@id,'statAlgoListForm:algoParamTable')]//button[1]/span")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			String lowerOperator=lib1.getExcelData("colorCoding", i, 1,filePath);
			driver.findElement(By.xpath("//td[span[contains(text(),'Lower operator')]]//div/label[1]")).click();
			driver.findElement(By.xpath("//div/div/ul/li[contains(text(),'"+lowerOperator+"')]")).click();
			String lowerLimit=lib1.getExcelData("colorCoding", i, 2,filePath);
			driver.findElement(By.xpath("//td[span[contains(text(),'Lower limit')]]//input")).sendKeys(lowerLimit);
			String upperOperator=lib1.getExcelData("colorCoding", i, 3,filePath);
			driver.findElement(By.xpath("//td[span[contains(text(),'Upper operator')]]//div/label[1]")).click();
			driver.findElement(By.xpath("//div/div/ul/li[contains(text(),'"+upperOperator+"')]")).click();
			String upperLimit=lib1.getExcelData("colorCoding", i, 4,filePath);
			driver.findElement(By.xpath("//td[span[contains(text(),'Upper limit')]]//input")).sendKeys(upperLimit);

			driver.findElement(By.xpath(" //span[contains(@id,'paramForm:bgColorPick')]/button[1]/span")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@class,'ui-colorpicker_color')]//div[1]/div")).click();
			driver.findElement(By.xpath(" //span[contains(@id,'paramForm:bgColorPick')]/button[1]/span")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[contains(@id,'paramForm:fgColorPick')]/button[1]/span")).click();
			Thread.sleep(2000);
			driver.switchTo().activeElement();
	//		driver.findElement(By.xpath("//div[contains(@class,'ui-colorpicker_color')]//div[1]/div")).click();
	//		driver.findElement(By.xpath("//span[contains(@id,'paramForm:fgColorPick_button')]/button[1]")).click();
			driver.findElement(By.xpath("//span/button[1]/span[contains(text(),'Save')]")).click();	
		}
		
		Thread.sleep(5000);
			}//for 
			}//try
			catch(Throwable e)
			{
				takeScreenShot(e, "Admin_Create_ColorCoding");
			//	Assert.fail("Error in creating user ");
				e.printStackTrace();
			}
		}
}
