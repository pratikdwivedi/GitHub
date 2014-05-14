package com.sapphire.portfolio;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Port_Signal extends Config{
	@Test
	public void TestMethodPortSiganl() throws Exception{
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
			String masterName=lib1.getExcelData("Signals", i, 0, filePath);
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
            driver.findElement(By.xpath("//form[contains(@id,'portLeftPane')]//div[1]/ul/li/a/span[contains(text(),'Signals')]")).click();
            String annotation=lib1.getExcelData("Signals", i, 1, filePath);
            driver.findElement(By.id("masterSignalForm:signalGrid:annotationTypeId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'masterSignalForm:signalGrid:annotationTypeId_panel')]//ul/li[contains(text(),'"+annotation+"')]")).click();
			Thread.sleep(3000);
			String event=lib1.getExcelData("Signals", i, 2, filePath);
            if(event!=null)
            {
            	driver.findElement(By.xpath("//div[contains(@id,'masterSignalForm:signalGrid')]//div/span[2]/input")).sendKeys(event);
            }
            String fromMonth=lib1.getExcelData("Signals", i, 4, filePath);
            String fromYear=lib1.getExcelData("Signals", i, 5, filePath);
            String fromDate=lib1.getExcelData("Signals", i, 6, filePath);
            if(fromMonth!=null && fromYear!=null && fromDate!=null)
            {
            driver.findElement(By.id("masterSignalForm:signalGrid:startDateId")).click();	
            Select monthList=new Select(driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div/div/select[1]")));
            monthList.selectByVisibleText(fromMonth);
            int fromYearInt=Double.valueOf(fromYear).intValue();
            Select yearList=new Select(driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div/div/select[2]")));
       	 	yearList.selectByVisibleText(""+fromYearInt);
            int fromDateInt=Double.valueOf(fromDate).intValue();
        	driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//td/a[contains(text(),'"+fromDateInt+"')]")).click();
        	 }
            String toMonth=lib1.getExcelData("Signals", i, 7, filePath);
            String toYear=lib1.getExcelData("Signals", i, 8, filePath);
            String toDate=lib1.getExcelData("Signals", i, 9, filePath);
            if(toMonth!=null && toYear!=null && toDate!=null)
            {
            driver.findElement(By.id("masterSignalForm:signalGrid:endDateId")).click();	
            Select toMonthList=new Select(driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div/div/select[1]")));
            toMonthList.selectByVisibleText(toMonth);
            int toYearInt=Double.valueOf(toYear).intValue();
            Select toYearList=new Select(driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div/div/select[2]")));
            toYearList.selectByVisibleText(""+toYearInt);
            int toDateInt=Double.valueOf(toDate).intValue();
            driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//td/a[contains(text(),'"+toDateInt+"')]")).click();
        	  }
            driver.findElement(By.xpath("//div[contains(@id,'masterSignalForm:signalGrid')]//div/span[2]/button[1]")).click();
			List<WebElement> annotationList=driver.findElements(By.xpath("//tbody[contains(@id,'masterSignalForm:signalGrid_data')]//div"));
			for(WebElement we:annotationList)
			{
				String signalData=we.getText();
				System.out.println(signalData);
			}
			Thread.sleep(3000);
			}//for close
		}//try close
	catch(Throwable e)
	{
		e.printStackTrace();
	//	Assert.fail("Error in portfolio Signal ");
	}
	}
}
