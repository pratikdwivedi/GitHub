package com.sapphire.portfolio;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
import com.test.Save_Analysis;

public class Port_Create_SA_cs extends Config{
	@Test
	public void TestMethodPortCreateSAcs() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		try {
			Save_Analysis sa=new Save_Analysis();
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText("PORTFOLIO")).click();
				Thread.sleep(5000);
				List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String masterName=lib1.getExcelData("sa-cs", i, 0, filePath);
	         
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
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[contains(@id,'leftNavPanel_content')]//form/div[1]/ul/li[4]/a/span")).click();
				driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[3]/a")).click();
				List<WebElement> listTopics=driver.findElements(By.xpath("//tbody[contains(@id,'tabView:masterTopictListForm:topicGrid_data')]//tr/td[2]/div/div/a"));	
	            String topicName=lib1.getExcelData("sa-cs", i, 1, filePath);
	            
	            for( WebElement weTopic:listTopics)
	          	{
	        	  System.out.println(weTopic.getText());
	        	  if(weTopic.getText().contentEquals(topicName))
	        	  	{
	        		  	String linkTopic = weTopic.getText(); 
						System.out.println("Clicked Link : "+linkTopic);
						weTopic.click();
				        System.out.println("clicked");
				        break;
	        	  	}
	          	}
	            Thread.sleep(3000);
			driver.findElement(By.linkText("Alerts")).click();
			driver.findElement(By.id("tabView:topicDetailForm:tabView:analysisGrid:startAnalysis")).click();//create button clicked
			String alertTypeSAcs=lib1.getExcelData("sa-cs", i, 2, filePath);
			WebElement weAlert=driver.findElement(By.xpath("//div[contains(@id,'tabView:topicDetailForm:tabView:analysisGrid:startAnalysisPanel')]//td[2]//label[contains(text(),'"+alertTypeSAcs+"')]"));
			act.moveToElement(weAlert).click();//FDA AERS clicked
			act.perform();
			Thread.sleep(3000);
			driver.findElement(By.id("analysisCriteriaForm:lookupUdr")).click();
			driver.switchTo().activeElement();
			String udrSAcs=lib1.getExcelData("sa-cs", i, 3, filePath);
			if(udrSAcs!=null)
			{
				driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span/input")).sendKeys(udrSAcs);
				driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-left mergeInHeader')]//span/button[1]/span")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+udrSAcs+"')]]]//td[1]/div/div/div[2]")).click();
				driver.findElement(By.id("udrLookupForm:selectUdr")).click();
			}
				
			Thread.sleep(3000);
				
			}//for close
		}//try close
		catch(Throwable e)
		{
			e.printStackTrace();
	//		Assert.fail("Error in creating topic Alerts for sa cs ");
		}
	}//method
}
