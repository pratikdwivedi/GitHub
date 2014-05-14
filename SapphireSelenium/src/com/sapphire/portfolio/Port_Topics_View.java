package com.sapphire.portfolio;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Topics_View extends Config {

	@Test
	public void TestMethodPortTopics() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText("PORTFOLIO")).click();
				Thread.sleep(3000);
				List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String masterName=lib1.getExcelData("viewtopics", i, 0, filePath);
	          for( WebElement weMaster:listMaster)
	          {
	        	  System.out.println(weMaster.getText());
	        	  if(weMaster.getText().contentEquals(masterName))
	        	  {
	        		  String link = weMaster.getText(); 
						 System.out.println("Clicked LInk"+link);
						 weMaster.click();
				        System.out.println("clicked");
				        break;
	        	  }
	        	 }
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//div[contains(@class,'ui-menu ui-widget ui-widget-content ui-corner-all ui-helper-clearfix leftContent')]//ul/li/a/span[contains(text(),'Topics')]")).click();
	            Thread.sleep(3000);
	            List<WebElement> listTopics=driver.findElements(By.xpath("//tbody[contains(@id,'masterTopictListForm:topicGrid_data')]//tr/td[2]/div/div/a"));	
	            String topicsName=lib1.getExcelData("viewtopics", i, 1, filePath);
	            
	            for( WebElement weTopics:listTopics)
	          	{
	        	  System.out.println(weTopics.getText());
	        	  if(weTopics.getText().contentEquals(topicsName))
	        	  	{
	        		  	String linkTopic = weTopics.getText(); 
						System.out.println("Clicked Link : "+linkTopic);
						weTopics.click();
						System.out.println("clicked");
						Thread.sleep(3000);
				        break;
	        	  	}
	          	}
	            String alertType=lib1.getExcelData("viewtopics", i, 2, filePath);
	           driver.findElement(By.xpath("//span[span[3][table[tbody[tr[td[contains(text(),'"+alertType+"')]]]]]]//span[1]")).click();
	           String analysisType=lib1.getExcelData("viewtopics", i, 3, filePath);
	           driver.findElement(By.xpath("//span[span[3][table[tbody[tr[td[contains(text(),'"+analysisType+"')]]]]]]//span[1]")).click();
	           Thread.sleep(2000);
	           String analysisName=lib1.getExcelData("viewtopics", i, 4, filePath);
	          driver.findElement(By.xpath("//tr/td/span[text()='"+analysisName+"']")).click();
	           
	          Thread.sleep(2000);
	          //Source
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Source')]")).click();
	           Thread.sleep(2000);
	          //Reports
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Reports')]")).click();
	           Thread.sleep(2000);
	          //Analysis 
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Analysis')]")).click();
	           Thread.sleep(2000);
	          //Evaluation 
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Evaluation')]")).click();
	           Thread.sleep(2000);
	          //Minutes 
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Minutes')]")).click();
	           Thread.sleep(2000);
	          //Action 
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Action')]")).click();
	           Thread.sleep(2000);
	          //Documents 
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Documents')]")).click();
	           Thread.sleep(2000);
	          //Linked Records 
	           driver.findElement(By.xpath("//ul/li/a[contains(text(),'Linked records')]")).click();
	           Thread.sleep(2000);
	            Thread.sleep(10000);  
	            
			}//for
			Thread.sleep(30000);
			Reporter.log("topic summary test completed");
		}//try
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("Error in topic view");
		}
		
		
	}//method
}
