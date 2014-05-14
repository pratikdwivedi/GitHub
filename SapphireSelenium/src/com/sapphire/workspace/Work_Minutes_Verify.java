package com.sapphire.workspace;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Work_Minutes_Verify extends Config{

	@Test
	public void TestMethodMinutesVerify() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.WORKSPACE);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
				{
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText("PORTFOLIO")).click();
				Thread.sleep(3000);
				List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String verifyMinutesMaster=lib1.getExcelData("WF-minutes", i, 13, filePath);
				String minutesMaster=lib1.getExcelData("WF-minutes", i,8, filePath);
	            String minutesTopic=lib1.getExcelData("WF-minutes", i, 9, filePath);
	           if(minutesMaster ==null && minutesTopic ==null)
	           {
	        	   System.out.println("Minutes doesn't have master or topic.......");
	        	 break;  
	           }
				for( WebElement weMaster:listMaster)
	          	{
	        	  System.out.println(weMaster.getText());
	        	  if(weMaster.getText().contentEquals(verifyMinutesMaster))
	        	  	{
	        		  	String linkMaster = weMaster.getText(); 
						System.out.println("Clicked Link"+linkMaster);
						weMaster.click();
				        System.out.println("clicked");
				        break;
	        	  	}
	          	}
	            Thread.sleep(4000);
	            driver.findElement(By.linkText("Minutes")).click();
	            String minutes=lib1.getExcelData("WF-minutes", i, 0, filePath);
	            driver.findElement(By.xpath("//tbody[contains(@id,'minutesform:minutesGrid_data')]//tr/td[2]/div/a[contains(text(),'"+minutes+"')]")).click();	
	            driver.switchTo().activeElement();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//tbody[contains(@id,'minutesDetailDlgForm:docGrid_data')]//button[1]/span[1]")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.id("close")).click();
	            Thread.sleep(3000);
	            
	            String minutesPublishTeam=lib1.getExcelData("WF-minutes", i, 2, filePath);
	            if(minutesPublishTeam.equals("Y"))
	            {
	            driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'INBOX')]")).click();
	             if(minutesMaster!=null)
		           	{
	            		driver.findElement(By.xpath("//div[contains(@id,'itemform:itemGrid')]//span[2]/input")).sendKeys(minutesMaster);
		        	}
	            
	             else if(minutesMaster==null)
	             	{
	            	 	if(minutesTopic!=null)
	            	 	{
	            		driver.findElement(By.xpath("//div[contains(@id,'itemform:itemGrid')]//span[2]/input")).sendKeys(minutesTopic);
	            	 	}
	            	 	else if(minutesTopic==null)
	            	 	{
	            	 		break;
	            	 	}
	             	}
	         driver.findElement(By.xpath("//div[contains(@id,'itemform:itemGrid')]//span[2]/button[1]/span")).click();
	         Thread.sleep(3000);
	         driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr[1]/td[5]/div/a")).click();
	         Thread.sleep(3000);  
	         driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li/a[contains(text(),'Minutes')]")).click();
	         driver.findElement(By.xpath("//tbody[contains(@id,'tabView:minutesform:minutesGrid_data')]//tr/td[2]/div/a[contains(text(),'"+minutes+"')]")).click();
	         Thread.sleep(3000);
	         driver.switchTo().activeElement();
	         driver.findElement(By.xpath("//tbody[contains(@id,'minutesDetailDlgForm:docGrid_data')]//button[1]/span[1]")).click();
	         Thread.sleep(3000);
	         driver.findElement(By.id("close")).click();
	         Thread.sleep(3000);
	            }  
	            Thread.sleep(5000);
	            
				}//for
		}//try
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("Error in Verify minutes.. ");
		}
	}//method

	            
}
