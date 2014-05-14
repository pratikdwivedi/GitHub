package com.sapphire.admin;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
public class Admin_Create_Product extends Config{
	
@Test
public void TestMethodCreateProduct() throws Exception{
	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.ADMIN);	
	try{
		ExcelLibrary lib1=new ExcelLibrary();
		for(int i=1;i<=lib1.rowCount;i++)
		{
		Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.linkText("ADMIN")).click();
		System.out.println("Admin clicked ");
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.id("productform:productGrid:createProduct")).click();
		Thread.sleep(5000);
		System.out.println("create button clicked");
		String productName = lib1.getExcelData("product", i, 0,filePath);
		WebElement elementPname=driver.findElement(By.id("productDetailForm:prdTabView:itnid"));
		elementPname.sendKeys(productName);
		System.out.println(productName);
		String tArea=lib1.getExcelData("product", i, 1,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:therapeuticArea_label")).click();
		driver.findElement(By.xpath(" //div[contains(@id,'productDetailForm:prdTabView:therapeuticArea_panel')]//ul/li[contains(text(),'"+tArea+"')]")).click();
		String type=lib1.getExcelData("product", i, 2,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:productTypeId_label")).click();//type clicked
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:productTypeId_panel')]/div[1]/ul[1]/li[contains(text(),'"+type+"')]")).click();
		String organizationType =lib1.getExcelData("product",i,3,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:organizationTypeId_label")).click();//Organization Type clicked
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:organizationTypeId_panel')]/div[1]/ul[1]/li[contains(text(),'"+organizationType+"')]")).click();
		driver.findElement(By.id("productDetailForm:prdTabView:lookupWhoDCProd")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//div[contains(@id,'whoDcMedProductLookupForm:ProdLookupGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(productName);
		driver.findElement(By.xpath("//div[contains(@id,'whoDcMedProductLookupForm:ProdLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
		Thread.sleep(5000);
		String whoPName=productName.toUpperCase();
		driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+whoPName+"')]]]//td[1]/div/div/div[2]")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("whoDcMedProductLookupForm:selectWhoDcMedProductGen")).click();
	//	String whocode=lib1.getExcelData("product", i, 4,filePath);
	//	driver.findElement(By.id("productDetailForm:prdTabView:whoDrlCodeid")).sendKeys(whocode);
		Thread.sleep(6000);
		String birthDate=lib1.getExcelData("product", i, 5,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:ibdid_input")).sendKeys(birthDate);
		driver.findElement(By.xpath("//div[contains(@id,'ui-datepicker-div')]//div[2]/button[2]")).click();
		String formStrength=lib1.getExcelData("product", i, 6,filePath);
	//	double formStrengthInt=new Double(formStrength).doubleValue();
		driver.findElement(By.id("productDetailForm:prdTabView:formStrengthid")).sendKeys(formStrength);
		String formStrengthUnit=lib1.getExcelData("product", i, 7,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:formStrengthunitid")).sendKeys(formStrengthUnit);
		String phamaceuticalForm =lib1.getExcelData("product", i, 8,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:pharmFormId_label")).click();//phamaceuticalForm clicked
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:pharmFormId_panel')]/div[1]/ul[1]/li[text()='"+phamaceuticalForm+"']")).click();
		String mahSponsor =lib1.getExcelData("product",i,9,filePath); //mah/Sponsor
		driver.findElement(By.id("productDetailForm:prdTabView:mfrnameid")).sendKeys(mahSponsor);
		String mahId =lib1.getExcelData("product",i,10,filePath); //mah/Sponsor
		driver.findElement(By.id("productDetailForm:prdTabView:MFRCodeid")).sendKeys(mahId);
		String source =lib1.getExcelData("product",i,11,filePath); //Source
		driver.findElement(By.id("productDetailForm:prdTabView:sourceId_label")).click();//Source clicked
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:sourceId_panel')]/div[1]/ul[1]/li[contains(text(),'"+source+"')]")).click();
		String status=lib1.getExcelData("product", i, 12,filePath);//status
		driver.findElement(By.id("productDetailForm:prdTabView:productStatusId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:productStatusId_panel')]/div[1]/ul[1]/li[contains(text(),'"+status+"')]")).click();//status selected
		String route=lib1.getExcelData("product", i, 13,filePath);//route
		driver.findElement(By.id("productDetailForm:prdTabView:routeId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:routeId_panel')]/div[1]/ul[1]/li[contains(text(),'"+route+"')]")).click();//route selected
		String description=lib1.getExcelData("product", i, 14,filePath);
		driver.findElement(By.id("productDetailForm:prdTabView:prd_desc_id")).sendKeys(description);//Description
		System.out.println("Complete");
	//	driver.findElement(By.id("productDetailForm:saveAndCloseUser")).click();//save & close
		driver.findElement(By.id("productDetailForm:saveProduct")).click();//save
	//	driver.findElement(By.id("productDetailForm:cancle")).click();//cancel
		Thread.sleep(5000);
		//Indication
		String indication=lib1.getExcelData("product", i, 15,filePath);
		if(indication!=null)
		{
		driver.findElement(By.linkText("Indication")).click();//Indication Clicked
		//driver.findElement(By.id("productDetailForm:prdTabView:prodIndicationGrid:fileUploadBtn")).click();
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:prodIndicationGrid')]//table/thead/tr/th/div/div/span/div[1]")).click();
		Thread.sleep(3000);	
		setClipboardData(indication);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		System.out.println("Indication complete");
//		driver.findElement(By.id("productDetailForm:saveProduct")).click();//save
		Thread.sleep(5000);
		}
		//Labelling
		String labelling=lib1.getExcelData("product", i, 16,filePath);
		if(labelling!=null)
		{
		driver.findElement(By.linkText("Labelling")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[contains(@id,'productDetailForm:prdTabView:prodLabellingGrid')]//table/thead/tr/th/div/div/span/div[1]")).click();
		Thread.sleep(3000);
		setClipboardData(labelling);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(5000);
		System.out.println("Labelling Complete");
	//	driver.findElement(By.id("productDetailForm:saveAndCloseUser")).click();//save & close
		driver.findElement(By.id("productDetailForm:saveProduct")).click();//save
	//	driver.findElement(By.id("productDetailForm:cancle")).click();//cancel
		}
		Thread.sleep(10000);
		  System.out.println("/////////////////////////////////////");
       /*WebElement elementDes=driver.findElement(By.id("productDetailForm:prdTabView:whoDrlCodeid"));
		elementDes.sendKeys(whoDrlCode);
		System.out.println(whoDrlCode);
		Thread.sleep(10000);
		
	Select selectTherapeuticArea=new Select(driver.findElement(By.id("productDetailForm:prdTabView:therapeuticArea_input")));

	selectTherapeuticArea.selectByIndex(5);
	WebElement optionTherapeuticArea = selectTherapeuticArea.getFirstSelectedOption();
	System.out.println(optionTherapeuticArea.getText());
	Select selectType=new Select(driver.findElement(By.id("productDetailForm:prdTabView:productTypeId_input")));
	selectType.selectByIndex(5);
	WebElement optionType = selectType.getFirstSelectedOption();
	System.out.println(optionType.getText());
	driver.findElement(By.id("productDetailForm:prdTabView:lookupWhoDCProd")).click();
	Thread.sleep(3000);
	driver.switchTo().activeElement();
	Select selectGridValue=new Select(driver.findElement(By.xpath("//th[contains(@id,'whoDcMedProductLookupForm:ProdLookupGrid_paginator_top')]/select[1]")));
	selectGridValue.selectByIndex(2);
	WebElement gridValue=selectGridValue.getFirstSelectedOption();
	System.out.println("Index per page is : "+gridValue.getText());
	//driver.findElement(By.xpath(".//*[@id='whoDcMedProductLookupForm:ProdLookupGrid_paginator_top']/span[4]/span")).click();//pagination 
	Thread.sleep(3000);
	driver.findElement(By.xpath(".//*[@id='whoDcMedProductLookupForm:ProdLookupGrid_data']/tr[8]/td[1]/div/div/div[2]")).click();//drug select
	driver.findElement(By.id("whoDcMedProductLookupForm:selectWhoDcMedProductGen")).click();//click on select
	Select selectSource=new Select(driver.findElement(By.id("productDetailForm:prdTabView:sourceId_input")));//select source)
	selectSource.selectByIndex(2);
	WebElement sourceValue=selectSource.getFirstSelectedOption();
	System.out.println("Source value : "+sourceValue.getText());
	Select selectStatus=new Select(driver.findElement(By.id("productDetailForm:prdTabView:productStatusId_input")));//select status)
	selectStatus.selectByIndex(2);
	WebElement statusValue=selectStatus.getFirstSelectedOption();
	System.out.println("Source value : "+statusValue.getText());
	Select selectRoute=new Select(driver.findElement(By.id("productDetailForm:prdTabView:routeId_input")));//select route)
	selectRoute.selectByIndex(2);
	WebElement routeValue=selectRoute.getFirstSelectedOption();
	System.out.println("Source value : "+routeValue.getText());
	driver.findElement(By.id("productDetailForm:prdTabView:prd_desc_id")).sendKeys("Testing product by automation");
	
	
	
	
	//Authorization
	driver.findElement(By.linkText("Authorization")).click();
	driver.findElement(By.id("productDetailForm:prdTabView:prodAuthorizationGrid:createMedProductAuthorization")).click();
	Thread.sleep(2000);
	driver.findElement(By.id("authorizationDetailForm:productName")).sendKeys("Test authorization");
	//select country in popup
	Select select = new Select(driver.findElement(By.id("authorizationDetailForm:authCountry_input")));
	select.selectByIndex(2);
	WebElement selectedCountry = select.getFirstSelectedOption();
	System.out.println("Selected country :"+ selectedCountry.getText());
	//procedureId
	select = new Select(driver.findElement(By.id("authorizationDetailForm:procedureIdId_input")));
	select.selectByIndex(2);
	WebElement selectedProcedure = select.getFirstSelectedOption();
	System.out.println("Selected Procedure :"+ selectedProcedure.getText());
	//Product status
	select = new Select(driver.findElement(By.id("authorizationDetailForm:productStatId_input")));
	select.selectByIndex(2);
	WebElement selectedProductStatus = select.getFirstSelectedOption();
	System.out.println("Selected Procedure :"+ selectedProductStatus.getText());
	//Authorization date
	driver.findElement(By.id("authorizationDetailForm:authDate_input")).sendKeys("2013-Nov-07");
	//Withdrawn Date: 
	driver.findElement(By.id("authorizationDetailForm:withdrawnDate_input")).sendKeys("2013-Nov-18");
	//who code
	driver.findElement(By.id("authorizationDetailForm:lookupWhoDCProd")).click();
	//driver.findElement(By.xpath(".//*[@id='productDetailForm:saveAndCloseUser']")).click();*///save&close
	//driver.findElement(By.id("productDetailForm:saveProduct")).click();//save
       
     //driver.findElement(By.id("productDetailForm:cancle")).click();//cancel
       System.out.println("Product creation complete............"+productName);
       Reporter.log("Product created "+productName);
       Thread.sleep(20000);
		}//for close
	}
	catch(Exception e)
	{
		takeScreenShot(e, "Admin_Create_Product");
		Assert.fail("Error in creating product");
		e.printStackTrace();}
	}
public static void setClipboardData(String string) {
	   StringSelection stringSelection = new StringSelection(string);
	   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

}
