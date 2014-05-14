package com.sapphire.portfolio;

import java.util.Iterator;
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

public class Port_Create_Master extends Config{
	@Test
	public void TestMethodPortCreateMaster() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.PORTFOLIO);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
			driver.findElement(By.linkText("MASTERS")).click();
			driver.findElement(By.id("masterform:masterGrid:createMaster")).click();
			String masterName=lib1.getExcelData("master",i,0,filePath);
			driver.findElement(By.id("tabView:masterDetailForm:masterName")).sendKeys(masterName);
			Thread.sleep(2000);
			System.out.println("*******");
			String typeProduct=lib1.getExcelData("master", i, 1,filePath);
			driver.findElement(By.id("tabView:masterDetailForm:masterType_label")).click();
			System.out.println(typeProduct);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:masterDetailForm:masterType_panel')]//div/ul/li[contains(text(),'"+typeProduct+"')]")).click();
			System.out.println("1");
			Thread.sleep(3000);
			String owner=lib1.getExcelData("master", i, 2,filePath);
			driver.findElement(By.id("tabView:masterDetailForm:lookupUser")).click();
			driver.switchTo().activeElement();
			Thread.sleep(3000);
			
			List lw=driver.findElements(By.xpath("//tbody[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid_data')]/tr/td[2]/div"));
			System.out.println(2);
			Iterator itr = lw.iterator();
            while(itr.hasNext()){
                String test =((WebElement) itr.next()).getText();
                System.out.println(test);
                if(test.equals(owner)){
                String xpath = driver.findElement(By.xpath("//tbody[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid_data')]/tr/td[2]/div")).getAttribute("xpath");
                System.out.println(xpath);
               // System.out.println(test);
                System.out.println("3");
                }
                
                else
                {
                	//System.out.println("User is not available............");
                //	driver.findElement(By.id("tabView:masterOwnerLookupForm:close")).click();
                	
                }
            }
			
			System.out.println("/////////user list printed");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(owner);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
			WebElement searchedUser=driver.findElement(By.xpath("//tbody[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid_data')]//tr[1]/td[2]/div[1]"));
				String getTextRecord=searchedUser.getText();
				System.out.println("Get record of user is : "+getTextRecord);
				Thread.sleep(2000);
				
			if(getTextRecord.equals("No records found."))
			{
				driver.findElement(By.id("tabView:masterOwnerLookupForm:close")).click();
				System.out.println("close clicked");
				Thread.sleep(1000);
			}
				
			else{
			WebElement searchedUser2=driver.findElement(By.xpath("//div[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid')]/table[1]/tbody[1]/tr[1]/td[2]/div[contains(text(),'"+owner+"')]"));
			String ownerName=searchedUser2.getText();
			System.out.println(ownerName);
			
			
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[contains(@id,'tabView:masterOwnerLookupForm:userLookupGrid')]//table[1]/tbody[1]/tr/td/div/div/div[2]")).click();
				driver.findElement(By.id("tabView:masterOwnerLookupForm:selectUser")).click();
				Thread.sleep(2000);
				System.out.println("user select");
				
			}//else close
			Thread.sleep(5000);
			String description=lib1.getExcelData("master", i, 3,filePath);
			driver.findElement(By.id("tabView:masterDetailForm:masterDescription")).sendKeys(description);
	//		driver.findElement(By.id("tabView:masterDetailForm:saveAndCloseMaster")).click();//save&close
			driver.findElement(By.id("tabView:masterDetailForm:saveMaster")).click();//save
	//		driver.findElement(By.id("tabView:masterDetailForm:masterCancle")).click();//cancel
			Thread.sleep(3000);
			//Add Products
			driver.findElement(By.xpath("//div[contains(@id,'tabView:masterDetailForm:masterProductGrid')]//span/button[1]/span")).click();
			driver.switchTo().activeElement();
			Thread.sleep(2000);
			String productLevel=lib1.getExcelData("master", i, 4,filePath);
			driver.findElement(By.id("tabView:productLookupForm:productLookupGrid:drugLevelId_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:productLookupForm:productLookupGrid:drugLevelId_panel')]/div[1]/ul[1]/li[contains(text(),'"+productLevel+"')]")).click();
			Thread.sleep(3000);
			String dictLevel=lib1.getExcelData("master", i, 7, filePath);//dictLevel
			driver.findElement(By.id("tabView:productLookupForm:productLookupGrid:searchLevel_label")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:productLookupForm:productLookupGrid:searchLevel_panel')]/div[1]/ul[1]/li[contains(text(),'"+dictLevel+"')]")).click();
			Thread.sleep(3000);
			String product=lib1.getExcelData("master", i, 5,filePath);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:productLookupForm:productLookupGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(product);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:productLookupForm:productLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
			Thread.sleep(3000);
	/*		WebElement searchedproduct=driver.findElement(By.xpath(".//*[@id='tabView:productLookupForm:tabView:productLookupGrid_data']/tr[1]/td[2]/div[1]"));
				String getProductRecord=searchedproduct.getText();
				System.out.println("Get product is : "+getProductRecord);
				Thread.sleep(2000);
				
			if(getProductRecord.equals("No records found."))
			{
				driver.findElement(By.id("tabView:productLookupForm:close")).click();//close
				System.out.println("close clicked");
				Thread.sleep(3000);
			}
				
		*/	
			WebElement searchedProduct2=driver.findElement(By.xpath("//tbody[contains(@id,'tabView:productLookupForm:productLookupGrid_data')]//tr[1]/td[2]/div[contains(text(),'"+product+"')]"));
			String searchedProductName=searchedProduct2.getText();
			System.out.println("Searched Product : "+searchedProductName);
			
			if(searchedProductName.equals(product))
				{
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+product+"')]]]//td[1]//div[2]")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("tabView:productLookupForm:selectProduct")).click();
				Thread.sleep(3000);
				System.out.println("Product select");
				}
			System.out.println(searchedProductName.equals(product));
			
			
	/*		//product group
			driver.findElement(By.xpath("//div[contains(@id,'tabView:masterDetailForm:masterProductGrid:j_idt122')]//div/span/button[1]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Group")).click();
			Thread.sleep(3000);
			
			String productGroup=lib1.getExcelData("master", i, 6,filePath);
			driver.findElement(By.id("tabView:productLookupForm:tabView:productGroupLookupGrid:srcTxtId")).sendKeys(productGroup);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@id,'tabView:productLookupForm:tabView:productGroupLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
			Thread.sleep(3000);
			
			WebElement searchedProductgroup=driver.findElement(By.xpath("//tbody[contains(@id,'tabView:productLookupForm:tabView:productGroupLookupGrid_data')]//tr[1]/td[1]/div"));
			String getProductRecordGroup=searchedProductgroup.getText();
			System.out.println("Get product Group is : "+getProductRecordGroup);
			Thread.sleep(2000);
			
		if(getProductRecordGroup.equals("No records found."))
		{
			driver.findElement(By.id("tabView:productLookupForm:close")).click();//close
			System.out.println("close clicked");
			Thread.sleep(3000);
			Reporter.log("Product Group Not found");
		}
			
		else{
		WebElement searchedProductGroup2=driver.findElement(By.xpath("//tbody[contains(@id,'tabView:productLookupForm:tabView:productGroupLookupGrid_data')]//tr[1]/td[2]/div[text()='"+productGroup+"']"));
		String searchedProductGroupName=searchedProductGroup2.getText();
		System.out.println("Searched Product : "+searchedProductGroupName);
		
		if(searchedProductGroupName.equals(productGroup))
			{
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:productLookupForm:tabView:productGroupLookupGrid_data')]//tr[1]/td[1]/div[1]/div[1]/div[2]")).click();
			Thread.sleep(5000);
			driver.findElement(By.id("tabView:productLookupForm:selectProduct")).click();
			Thread.sleep(3000);
			System.out.println("Product Group select");
			}
		System.out.println(searchedProductGroupName.equals(productGroup));
		}//else close
	
	*/		
		//Add members
		String member=lib1.getExcelData("master", i, 8,filePath);
			if(member!=null)
			{
				driver.findElement(By.id("tabView:masterDetailForm:userGroupGrid:createNew")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'tabView:userLookupForm:tabView:userLookupGrid')]//span/input")).sendKeys(member);
				driver.findElement(By.xpath("//div[contains(@id,'tabView:userLookupForm:tabView:userLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//tbody[contains(@id,'tabView:userLookupForm:tabView:userLookupGrid_data')]//tr[1]/td[1]/div/div/div[2]")).click();
				
				Thread.sleep(5000);
				driver.findElement(By.id("tabView:userLookupForm:selectUser")).click();
			}
			else
			{
				System.out.println("member is not available or null ...."); 
				
				
			}
			Thread.sleep(3000);
			String memberGroup=lib1.getExcelData("master", i, 9,filePath);
			if(memberGroup!=null)
			{
				driver.findElement(By.id("tabView:masterDetailForm:userGroupGrid:createNew")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.linkText("User group")).click();
				driver.findElement(By.xpath("//div[contains(@id,'tabView:userLookupForm:tabView:userGroupLookupGrid')]//span/input")).sendKeys(memberGroup);
				driver.findElement(By.xpath("//div[contains(@id,'tabView:userLookupForm:tabView:userGroupLookupGrid')]//span/button[1]/span")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//tbody[contains(@id,'tabView:userLookupForm:tabView:userGroupLookupGrid_data')]//tr[1]/td[1]/div/div/div[2]")).click();
				Thread.sleep(5000);
				driver.findElement(By.id("tabView:userLookupForm:selectUser")).click();
				
			
			}
		
			Thread.sleep(5000);
			driver.findElement(By.id("tabView:masterDetailForm:saveMaster")).click();
			Thread.sleep(5000);
				
			}//for close
			}
		catch(Throwable e1)
		{
			e1.printStackTrace();
			Assert.fail("Error in creating Master ");
		}
	}
}
