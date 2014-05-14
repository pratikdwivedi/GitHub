package com.sapphire.portfolio;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
public class Port_Create_Dossier extends Config{
	@Test
	public void TestMethodPortCreateDossier() throws Exception{
		String getAction = null;
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
				Thread.sleep(10000);
			driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li[4]/a/span")).click();//portfolio clicked
			Thread.sleep(3000);
			List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
			String masterName=lib1.getExcelData("dossier", i, 0, filePath);
	          for( WebElement we:listMaster)
	          {
	        	  System.out.println(we.getText());
	        	  if(we.getText().contentEquals(masterName))
	        	  {
	        		  String link = we.getText(); 
						 System.out.println("Clicked LInk"+link);
						 we.click();
				        System.out.println("clicked");
				        break;
	        	  }
	        	}
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//form[contains(@id,'portLeftPane')]//div/ul/li[3]/a/span")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.id("dossierform:dossierGrid:createDossier")).click();
	            String dossierName=lib1.getExcelData("dossier", i, 1, filePath);
	            driver.findElement(By.id("dossierDetailForm:dossierName")).sendKeys(dossierName);
	            String desc=lib1.getExcelData("dossier", i, 2, filePath);
	            driver.findElement(By.id("dossierDetailForm:dossierDescription")).sendKeys(desc);
	            String startRange=lib1.getExcelData("dossier", i, 3, filePath);
	            driver.findElement(By.id("dossierDetailForm:startRange_input")).clear();
	            driver.findElement(By.id("dossierDetailForm:startRange_input")).sendKeys(startRange);
	            String endRange=lib1.getExcelData("dossier", i, 4, filePath);
	            driver.findElement(By.id("dossierDetailForm:rangeEnd_input")).clear();
	            driver.findElement(By.id("dossierDetailForm:rangeEnd_input")).sendKeys(endRange);
	  //add product       
	            String product=lib1.getExcelData("dossier", i, 5, filePath);
	            driver.findElement(By.id("dossierDetailForm:addMasterProduct")).click();
	            driver.switchTo().activeElement();
	            driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:productGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(product);
	            driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:productGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//tbody[tr[td[2][div[contains(text(),'Azacitidine')]]]]//tr/td[1]/div/div/div[2]")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:productLookupDlg')]//div[3]/span/button[1]/span")).click();//add product
	   //       driver.findElement(By.xpath("//div[contains(@id,'productLookupForm:productLookupDlg')]//div[3]/span/button[2]/span")).click();//close
	  //add docket          
	            String docketArtifact=lib1.getExcelData("dossier", i, 6, filePath);
	            driver.findElement(By.id("dossierDetailForm:addArtifact")).click();
	            driver.findElement(By.xpath("//div[contains(@id,'artifactLookupForm:artifactGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(docketArtifact);
	            driver.findElement(By.xpath("//div[contains(@id,'artifactLookupForm:artifactGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//tbody[tr[td[2][div[contains(text(),'"+docketArtifact+"')]]]]//tr/td[1]/div/div/div[2]")).click();
	            driver.findElement(By.xpath("//div[contains(@id,'artifactLookupForm:artifactLookupDlg')]//div[3]/span/button[1]/span")).click();//add
	            Thread.sleep(2000);
	  //        driver.findElement(By.xpath("//div[contains(@id,'artifactLookupForm:artifactLookupDlg')]//div[3]/span/button[2]/span")).click();//close
	  //add minutes 
	             String minutes=lib1.getExcelData("dossier", i, 7, filePath);
	            if(minutes!=null){
	             driver.findElement(By.id("dossierDetailForm:addMinutes")).click();
	            driver.switchTo().activeElement();
	            driver.findElement(By.xpath("//div[contains(@id,'minutesLookupForm:minutesGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(minutes);
	            driver.findElement(By.xpath("//div[contains(@id,'minutesLookupForm:minutesGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//tbody[contains(@id,'minutesLookupForm:minutesGrid_data')]//tr/td/div/div/div[2]")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.xpath("//div[contains(@id,'minutesLookupForm:minutesLookupDlg')]//div[3]/span/button[1]/span")).click();//add 
	          //  driver.findElement(By.xpath("//div[contains(@id,'minutesLookupForm:minutesLookupDlg')]//div[3]/span/button[2]/span")).click();//close
	            Thread.sleep(2000);
	            }
	  //add topic 
	            String topicName=lib1.getExcelData("dossier", i, 8, filePath);
	            driver.findElement(By.id("dossierDetailForm:selectTopicBtnId")).click();
	            Thread.sleep(2000);
	            driver.switchTo().activeElement();
	            driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicLookupGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(topicName);
	            driver.findElement(By.xpath("//div[contains(@id,'topicLookupForm:topicLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//tbody[contains(@id,'topicLookupForm:topicLookupGrid_data')]//tr/td/div/div/div[2]")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.id("topicLookupForm:selectTopic")).click();//select toipc
	            Thread.sleep(2000);
	   //         driver.findElement(By.xpath("//div[contains(@id,'dossierDetailForm:allTopicsId')]//div[2]")).click();//select all topics
	          /*  WebElement addedTopic=driver.findElement(By.xpath("//tbody[contains(@class,'ui-treetable-data ui-widget-content')]//tr/td/div[contains(text(),'"+topicName+"')]"));
	            String getTopic=addedTopic.getText();
	            if(topicName.equals(getTopic))
	            {
	            	driver.findElement(By.xpath("//tbody[contains(@class,'ui-treetable-data ui-widget-content')]//tr/td[2]/div/div/div[2]")).click();
	            }
	           
	            Thread.sleep(3000);
	            */ 
	   //add actions
	            String actionName=lib1.getExcelData("dossier", i, 9, filePath);
	            if(actionName!=null)
	            {	
	            driver.findElement(By.id("dossierDetailForm:selectPlanBtnId")).click();
	            Thread.sleep(3000);
	            driver.switchTo().activeElement();
	            driver.findElement(By.xpath("//div[contains(@id,'planLookupForm:topicLookupGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(actionName);
	            driver.findElement(By.xpath("//div[contains(@id,'planLookupForm:topicLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//tbody[contains(@id,'planLookupForm:topicLookupGrid_data')]//tr/td/div/div/div[2]")).click();
	            Thread.sleep(2000);
	            driver.findElement(By.id("planLookupForm:selectPlan")).click();//select action
	            Thread.sleep(3000);
	   //         driver.findElement(By.xpath("//div[contains(@id,'dossierDetailForm:allPlansId')]//div[2]")).click();
	            
	            WebElement addedAction=driver.findElement(By.xpath("//tbody[contains(@class,'ui-treetable-data ui-widget-content')]//tr/td/div[contains(text(),'"+actionName+"')]"));
	            getAction=addedAction.getText();
	            }
	            WebElement addedTopic=driver.findElement(By.xpath("//tbody[contains(@class,'ui-treetable-data ui-widget-content')]//tr/td/div[contains(text(),'"+topicName+"')]"));
	            
	            String getTopic=addedTopic.getText();
	            
	            if(topicName.equals(getTopic) || actionName.equals(getAction))
	            {
	            List<WebElement> listTopicAction=driver.findElements(By.xpath("//tbody[contains(@class,'ui-treetable-data ui-widget-content')]//tr/td[2]/div/div/div[2]"));
	            for( WebElement we:listTopicAction)
	            	{
	          	  	         	  	
	  				we.click();
	  				break;
	            	}
	            }
	            Thread.sleep(5000);
	            driver.findElement(By.id("dossierDetailForm:saveDossier")).click();//save
	            Thread.sleep(6000);
	            driver.findElement(By.id("dossierDetailForm:prevReportBtnId")).click();//preview
	            Thread.sleep(3000);
	            driver.findElement(By.xpath("//div[contains(@id,'displayReportForm:dossierPnlGroup')]//div/div/button[1]/span")).click();//download
	            Thread.sleep(2000);
	            driver.switchTo().activeElement();
	            Thread.sleep(2000);
	            robot.keyPress(KeyEvent.VK_ENTER);
	   			robot.keyRelease(KeyEvent.VK_ENTER);
	            driver.findElement(By.id("displayReportForm:saveAndCloseDossier")).click();
	          Thread.sleep(10000);
	          }
			Thread.sleep(5000);
			}//for close
			catch(Throwable e1)
		{
			e1.printStackTrace();
			Assert.fail("Error in creating Dossier ");
		}
	}
}
