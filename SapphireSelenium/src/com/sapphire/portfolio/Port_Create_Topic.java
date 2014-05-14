package com.sapphire.portfolio;

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

public class Port_Create_Topic extends Config{
	@Test
	public void TestMethodPortCreateTopic() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("MASTERS")).click();
			Thread.sleep(3000);
			List<WebElement> listMaster=driver.findElements(By.xpath("//tbody[contains(@id,'masterform:masterGrid_data')]//tr/td[2]/div/div/a"));
			/*Iterator itr1 = listMaster.iterator();
            while(itr1.hasNext()){
                String masterLink =((WebElement) itr1.next()).getText();
                System.out.println(masterLink);	
            }//while close
          */
			String masterName=lib1.getExcelData("topic", i, 0, filePath);
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
            driver.findElement(By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li/a/span[contains(text(),'Setup')]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[3]/a")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:masterTopictListForm:topicGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
			driver.switchTo().activeElement();
			String topicName=lib1.getExcelData("topic", i, 1, filePath); 
			driver.findElement(By.id("tabView:topicDetailForm:tabView:name")).sendKeys(topicName);
			String type=lib1.getExcelData("topic", i, 2, filePath);
			driver.findElement(By.id("tabView:topicDetailForm:tabView:topicType_label")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:topicDetailForm:tabView:topicType_panel')]//ul[1]/li[text()='"+type+"']")).click();
			Thread.sleep(2000);
			String notify=lib1.getExcelData("topic", i, 9, filePath);
			if(notify!=null && notify.equals("Y"))
			{
				driver.findElement(By.xpath("//div[contains(@id,'tabView:topicDetailForm:tabView:notifyid')]//div[2]")).click();
			}
			String status=lib1.getExcelData("topic", i, 3, filePath); 
			driver.findElement(By.id("tabView:topicDetailForm:tabView:topicStatus_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:topicDetailForm:tabView:topicStatus_panel')]//ul[1]/li[text()='"+status+"']")).click();
			String priority=lib1.getExcelData("topic", i, 4, filePath); 
			driver.findElement(By.id("tabView:topicDetailForm:tabView:priority_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:topicDetailForm:tabView:priority_panel')]//ul[1]/li[text()='"+priority+"']")).click();
			String workflow=lib1.getExcelData("topic", i, 5, filePath);
			//driver.findElement(By.id("tabView:topicDetailForm:tabView:workflowSel")).sendKeys(workflow);
			driver.findElement(By.id("tabView:topicDetailForm:tabView:lookupWF")).click();
			driver.switchTo().activeElement();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//div[contains(@id,'tabView:assignWFForm:wfHeaderGridSave')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/input")).sendKeys(workflow);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:assignWFForm:wfHeaderGridSave')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/button[1]/span")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:assignWFForm:wfHeaderGridSave_data')]//tr[1]/td[1]/div[1]/div[1]/div[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:assignWFForm:wfLookupDlg')]/div[3]/span/button[1]/span")).click();// select
			
			String description=lib1.getExcelData("topic", i, 6, filePath);
			driver.findElement(By.id("tabView:topicDetailForm:tabView:description")).sendKeys(description);
			String user=lib1.getExcelData("topic", i, 7, filePath);
			if(user!=null)
			{
			driver.findElement(By.id("tabView:topicDetailForm:tabView:privTabView:topicUserGroupGrid:createNewTopPrv")).click();
			driver.switchTo().activeElement();
			driver.findElement(By.linkText("User"));
			driver.findElement(By.xpath("//div[contains(@id,'tabView:topicUserLookupForm:tabView:userLookupGrid')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/input")).sendKeys(user);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:topicUserLookupForm:tabView:userLookupGrid')]/table[1]/thead[1]/tr[1]/th[1]/div[1]/div/span/button[1]/span")).click();//search clicked
			Thread.sleep(3000);
			WebElement topicUserRecords=driver.findElement(By.xpath("//tbody[contains(@id,'tabView:topicUserLookupForm:tabView:userLookupGrid_data')]//tr/td/div"));
			String getTextRecord=topicUserRecords.getText();
			if(getTextRecord.equals("No records found."))
			{
				
				driver.findElement(By.id("tabView:topicUserLookupForm:close")).click();
				System.out.println("User not found");
			}	
			else{
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:topicUserLookupForm:tabView:userLookupGrid_data')]//tr[1]/td[1]/div[1]/div[1]/div[2]")).click();		
			Thread.sleep(3000);
			driver.findElement(By.id("tabView:topicUserLookupForm:selectUser")).click();
			}
			}
	
			driver.findElement(By.id("tabView:topicDetailForm:saveTopic")).click();
			Thread.sleep(3000);
			}//for close
		}//try close
	catch(Throwable e)
	{
		e.printStackTrace();
		Assert.fail("Error in creating topic ");
	}
	}
}

