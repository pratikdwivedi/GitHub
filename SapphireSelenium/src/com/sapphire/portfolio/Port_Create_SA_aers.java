package com.sapphire.portfolio;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.common.FetchProperties;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
import com.test.Save_Analysis;

public class Port_Create_SA_aers extends Config{
	
	@Test
	public void TestMethodPortCreateSAaers() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		FetchProperties fetchProp = new FetchProperties();
		try {
			fetchProp.fetchProp();
			Save_Analysis sa=new Save_Analysis();
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.linkText(fetchProp.portfolio)).click();
				Thread.sleep(5000);
				List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
				String masterName=lib1.getExcelData("sa-aers", i, 0, filePath);
	         
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
				driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[3]/a")).click();//topic and alerts clicked
				List<WebElement> listTopics=driver.findElements(By.xpath("//tbody[contains(@id,'tabView:masterTopictListForm:topicGrid_data')]//tr/td[2]/div/div/a"));	
	            String topicName=lib1.getExcelData("sa-aers", i, 1, filePath);
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
			String alertType=lib1.getExcelData("sa-aers", i, 2, filePath);
			WebElement weAlert=driver.findElement(By.xpath(" //div[contains(@id,'tabView:topicDetailForm:tabView:analysisGrid:startAnalysisPanel')]//table/tbody/tr/td[2]/div/div[2]/table/tbody/tr/td/a/label[contains(text(),'"+alertType+"')]"));
			act.moveToElement(weAlert).click();//FDA AERS clicked
			act.perform();
			Thread.sleep(3000);
			String drugLevel=lib1.getExcelData("sa-aers", i, 3, filePath);
			driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:drugLevelId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:drugLevelId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();
			String drugName=lib1.getExcelData("sa-aers", i, 5, filePath);
			driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:drugName")).sendKeys(drugName);
			driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:addSADrug")).click();
			//Thread.sleep(3000);
			String saAersTypeDDI=lib1.getExcelData("sa-aers", i, 6, filePath);
			for(int j=1;j<=lib1.rowCount;j++)
			{
				if(!saAersTypeDDI.equals("DDI"))
				{
				
				String filters=lib1.getExcelData("safilters", j, 0, filePath);
				WebElement w=driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filters+"')]]]//td[1]/div/div[2]"));
				w.click();
				String operator=lib1.getExcelData("safilters", j, 1, filePath);
			/*	if(operator!=null)
				{
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filters+"')]]]//td[3]/div/label")).click();
				WebElement weselect=driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filters+"')]]]//td[3]/div/div/select"));		
				Select sel = new Select(weselect);
				sel.selectByVisibleText(operator);
				String value=lib1.getExcelData("safilters", j, 2, filePath);
				driver.findElement(By.xpath(" //tr[td[2][label[contains(text(),'"+filters+"')]]]/td[4]/input")).sendKeys(value);
			
				
				}
			*/
				}//if closes
				Thread.sleep(5000);
			}	//for close
			String saAersTypeHierarchy=lib1.getExcelData("sa-aers", i, 6, filePath);
			
			if(saAersTypeHierarchy!=null && saAersTypeHierarchy.equals("Hierarchy"))
			{
				driver.findElement(By.xpath("//table[contains(@id,'analysisCriteriaForm:saCriteriaTab:hierarchy')]//tbody/tr/td[1]/div/div[2]")).click();
				
				
			}
			
			if(saAersTypeDDI!=null && saAersTypeDDI.equals("DDI"))
			{
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:isddi')]//div[2]")).click();
				
			}
			String saAersTypeTrending=lib1.getExcelData("sa-aers", i, 6, filePath);
			if(saAersTypeTrending!=null && saAersTypeTrending.equals("Trending"))
			{
				String period=lib1.getExcelData("sa-aers", i, 27, filePath);
				driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPeriodId_label")).click();
				Thread.sleep(2000);
			WebElement wePeriod=driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:saCriteriaTab:trendPeriodId_panel')]//div/ul/li[contains(text(),'"+period+"')]"));		
			wePeriod.click();	
			String past=lib1.getExcelData("sa-aers", i, 28, filePath);
			int pastInt=new Double(past).intValue();
			driver.findElement(By.id("analysisCriteriaForm:saCriteriaTab:trendPastPeriodId")).sendKeys(""+pastInt);
			Thread.sleep(5000);
			}
			sa.analysisName=lib1.getExcelData("sa-aers", i, 31, filePath);
			sa.status=lib1.getExcelData("sa-aers", i, 32, filePath);
			sa.priority=lib1.getExcelData("sa-aers", i, 33, filePath);
			sa.scheduler=lib1.getExcelData("sa-aers", i, 34, filePath);
			sa.remarks=lib1.getExcelData("sa-aers", i, 35, filePath);
			sa.route=lib1.getExcelData("sa-aers", i, 36, filePath);
			sa.save_Analysis();
			driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'INBOX')]")).click();
		    WebElement alertCheckSAaers=driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a"));
			String alertCheckSAaers2=alertCheckSAaers.getText();
			if(sa.analysisName.equals(alertCheckSAaers2))
			{
				System.out.println("Test passed");
				Reporter.log("Test Passed : Created analysis exists in INBOX");
			//	Assert.assertEquals(analysisName, alertCheckSAaers2);
			}
			else 
			{
				System.out.println("Test Failed");
				Reporter.log("Test Failed : Created analysis does not exists in INBOX");
			//	Assert.fail("Test Failed : Created analysis does not exists in INBOX");
			//	Assert.assertEquals(analysisName, alertCheckSAaers2);
				
			}
		         Thread.sleep(20000);
			}//for close 
		}//try close
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("Error in creating topic Alerts for sa aers ");
		}
		
	}//method close
}