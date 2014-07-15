package com.practise;
/*package com.ms.sapphire.admin;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Config;

public class TestConfig extends Config{
	
	@Test
	public void testMethod() throws AWTException, InterruptedException
	{
		
		
		
		
		
		
		
		

		System.out.println("****************************");
		Thread.sleep(20000);
		driver.findElement(By.id("templateGroupDetailForm:descriptionId")).sendKeys("Automation testing template group for aers");
		//driver.findElement(By.id("templateGroupDetailForm:saveCloseTemplateGroupId")).click();//save&close
		driver.findElement(By.id("templateGroupDetailForm:cancle")).click();//cancel
		
		//create Template Group CS acme
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Template group")).click();
		driver.findElement(By.id("reportTab:templateGroupform:templateGroupGrid:createTemplateGroup")).click();
		driver.findElement(By.id("templateGroupDetailForm:groupnameid")).sendKeys("CSAcme-Template group");
		driver.findElement(By.id("templateGroupDetailForm:datasourceId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'templateGroupDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[3]")).click();
		driver.findElement(By.id("templateGroupDetailForm:descriptionId")).sendKeys("Automation testing template group for CS Acme");
		//driver.findElement(By.id("templateGroupDetailForm:saveCloseTemplateGroupId")).click();//save&close
		driver.findElement(By.id("templateGroupDetailForm:cancle")).click();//cancel
		
		
		//create Template Group Maude
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Template group")).click();
		driver.findElement(By.id("reportTab:templateGroupform:templateGroupGrid:createTemplateGroup")).click();
		driver.findElement(By.id("templateGroupDetailForm:groupnameid")).sendKeys("Maude-Template group");
		driver.findElement(By.id("templateGroupDetailForm:datasourceId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'templateGroupDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[4]")).click();
		driver.findElement(By.id("templateGroupDetailForm:descriptionId")).sendKeys("Automation testing template group for Maude");
		//driver.findElement(By.id("templateGroupDetailForm:saveCloseTemplateGroupId")).click();//save&close
		driver.findElement(By.id("templateGroupDetailForm:cancle")).click();//cancel
				
		
		//create Template Group Medline
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Template group")).click();
		driver.findElement(By.id("reportTab:templateGroupform:templateGroupGrid:createTemplateGroup")).click();
		driver.findElement(By.id("templateGroupDetailForm:groupnameid")).sendKeys("Medline-Template group");
		driver.findElement(By.id("templateGroupDetailForm:datasourceId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'templateGroupDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[5]")).click();
		driver.findElement(By.id("templateGroupDetailForm:descriptionId")).sendKeys("Automation testing template group for Medline");
		//driver.findElement(By.id("templateGroupDetailForm:saveCloseTemplateGroupId")).click();//save&close
		driver.findElement(By.id("templateGroupDetailForm:cancle")).click();//cancel
						
		//create Template Group Vaers
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Template group")).click();
		driver.findElement(By.id("reportTab:templateGroupform:templateGroupGrid:createTemplateGroup")).click();
		driver.findElement(By.id("templateGroupDetailForm:groupnameid")).sendKeys("VAERS-Template group");
		driver.findElement(By.id("templateGroupDetailForm:datasourceId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'templateGroupDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[6]")).click();//vaers
		driver.findElement(By.id("templateGroupDetailForm:descriptionId")).sendKeys("Automation testing template group for VAERS");
		//driver.findElement(By.id("templateGroupDetailForm:saveCloseTemplateGroupId")).click();//save&close
		driver.findElement(By.id("templateGroupDetailForm:cancle")).click();//cancel
				
		
		//Create Report Parameter aers text
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Report parameters")).click();
		driver.findElement(By.id("reportTab:reportParamform:reportParamGrid:createReportParam")).click();
		driver.findElement(By.id("reportParamDetailForm:nameid")).sendKeys("AERS-text");
		driver.findElement(By.id("reportParamDetailForm:reportParamDSId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamDSId_panel')]//div[1]/ul[1]/li[2]")).click();//aers
		driver.findElement(By.id("reportParamDetailForm:reportParamTypeId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamTypeId_panel')]//div[1]/ul[1]/li[2]")).click();//text
		//driver.findElement(By.id("reportParamDetailForm:saveAndCloseUser")).click();//save&Close
		driver.findElement(By.id("reportParamDetailForm:cancle"));//cancel
		
		
		//Create Report Parameter CSacme text
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Report parameters")).click();
		driver.findElement(By.id("reportTab:reportParamform:reportParamGrid:createReportParam")).click();
		driver.findElement(By.id("reportParamDetailForm:nameid")).sendKeys("CSAcme-text");
		driver.findElement(By.id("reportParamDetailForm:reportParamDSId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamDSId_panel')]//div[1]/ul[1]/li[3]")).click();//csacme
		driver.findElement(By.id("reportParamDetailForm:reportParamTypeId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamTypeId_panel')]//div[1]/ul[1]/li[2]")).click();//text
		//driver.findElement(By.id("reportParamDetailForm:saveAndCloseUser")).click();//save&Close
		driver.findElement(By.id("reportParamDetailForm:cancle"));//cancel
				
		
		//Create Report Parameter VAERS text
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.linkText("Report parameters")).click();
		driver.findElement(By.id("reportTab:reportParamform:reportParamGrid:createReportParam")).click();
		driver.findElement(By.id("reportParamDetailForm:nameid")).sendKeys("VAERS-text");
		driver.findElement(By.id("reportParamDetailForm:reportParamDSId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamDSId_panel')]//div[1]/ul[1]/li[6]")).click();//vaers
		driver.findElement(By.id("reportParamDetailForm:reportParamTypeId_label")).click();
		driver.findElement(By.xpath("//div[contains(@id,'reportParamDetailForm:reportParamTypeId_panel')]//div[1]/ul[1]/li[2]")).click();//text
		//driver.findElement(By.id("reportParamDetailForm:saveAndCloseUser")).click();//save&Close
		driver.findElement(By.id("reportParamDetailForm:cancle"));//cancel
				
		
		 //Create Template aers
		driver.findElement(By.linkText("ADMIN")).click();
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.id("reportTab:reportTemplateform:reportTemplateGrid:createReportTemplate")).click();
		driver.findElement(By.id("reportTemplateDetailForm:nameid")).sendKeys("Auto-aresWorkflowTemplate");
		//driver.findElement(By.id("reportTemplateDetailForm:datasourceId_input")).click();
		driver.findElement(By.id("reportTemplateDetailForm:datasourceId_label")).click();//datasource dropdown clicked
		driver.findElement(By.xpath("//div[contains(@id,'reportTemplateDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[2]")).click();//aers clicked
		/*Select sel=new Select(driver.findElement(By.id("reportTemplateDetailForm:datasourceId_input")));
		 sel.selectByVisibleText("AERS");
		WebElement selectedValue=sel.getFirstSelectedOption();
		System.out.println("seleted value : "+selectedValue.getText());
		driver.findElement(By.linkText("AERS")).click();
		*///here select by index is not working for selecting dropdown list
/*		
		Thread.sleep(2000);
		driver.findElement(By.id("reportTemplateDetailForm:reportGroupListId_label")).click();
		driver.findElement(By.xpath(" //div[contains(@id,'reportTemplateDetailForm:reportGroupListId_panel')]//div[1]/ul[1]/li[2]")).click();
		boolean beforeStatus =driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).isSelected();
		System.out.println(beforeStatus);
		Thread.sleep(2000);
		if(!driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).isSelected())
		{
			driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).click();
		}
		Thread.sleep(2000);
		boolean afterStatus=driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).isSelected();
		System.out.println(afterStatus);
		Thread.sleep(3000);
		driver.findElement(By.id("reportTemplateDetailForm:descriptionId")).sendKeys("Test Template aers workflow");
		driver.findElement(By.id("reportTemplateDetailForm:simpleid_input")).click();
		setClipboardData("D:\\SapphireRpt&XML\\AersWFinboxSeriousnessOutcome.rptdesign");
		
		//setClipboardData("D:\\SapphireRpt&XML\\ProductList\\ProductList.txt");
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		System.out.println("complete AErs template created");
		Thread.sleep(5000);
//		driver.findElement(By.id("reportTemplateDetailForm:saveAndCloseTemplate")).click();//save&close
		driver.findElement(By.id("reportTemplateDetailForm:cancle")).click();//cancel		
		Thread.sleep(5000);
		//Create template csAcme 
		driver.findElement(By.linkText("ADMIN")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Templates")).click();
		driver.findElement(By.id("reportTab:reportTemplateform:reportTemplateGrid:createReportTemplate")).click();
		driver.findElement(By.id("reportTemplateDetailForm:nameid")).sendKeys("Auto-CSacme-Workflow-Template");
		driver.findElement(By.id("reportTemplateDetailForm:datasourceId_label")).click();//datasource clicked
		driver.findElement(By.xpath("//div[contains(@id,'reportTemplateDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[3]")).click();//cs acme selected
		Thread.sleep(2000);
		driver.findElement(By.id("reportTemplateDetailForm:reportGroupListId_label")).click();
		driver.findElement(By.xpath(" //div[contains(@id,'reportTemplateDetailForm:reportGroupListId_panel')]//div[1]/ul[1]/li[2]")).click();
		boolean beforeStatusCSacme =driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).isSelected();
		System.out.println(beforeStatusCSacme);
		Thread.sleep(2000);
		if(!driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).isSelected())
		{
			driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).click();
		}
		Thread.sleep(2000);
		boolean afterStatusCSacme=driver.findElement(By.id("reportTemplateDetailForm:typesId:0")).isSelected();
		System.out.println(afterStatusCSacme);
		Thread.sleep(3000);
		driver.findElement(By.id("reportTemplateDetailForm:descriptionId")).sendKeys("Test Template CSacme Workflow");
		driver.findElement(By.id("reportTemplateDetailForm:simpleid_input")).click();
		setClipboardData("D:\\SapphireRpt&XML\\CSWFinbox.rptdesign");
		//Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		System.out.println("complete CSacme template created");
	
	Thread.sleep(5000);
		//	driver.findElement(By.id("reportTemplateDetailForm:saveAndCloseTemplate")).click();//save&close
	driver.findElement(By.id("reportTemplateDetailForm:cancle")).click();//cancel	
	Thread.sleep(5000);
	
	//Create template for aers Regulatory
	driver.findElement(By.linkText("ADMIN")).click();
	driver.findElement(By.linkText("Templates")).click();
	driver.findElement(By.id("reportTab:reportTemplateform:reportTemplateGrid:createReportTemplate")).click();
	driver.findElement(By.id("reportTemplateDetailForm:nameid")).sendKeys("Auto-ares-RegulatoryTemplate");
	//driver.findElement(By.id("reportTemplateDetailForm:datasourceId_input")).click();
	driver.findElement(By.id("reportTemplateDetailForm:datasourceId_label")).click();//datasource dropdown clicked
	driver.findElement(By.xpath("//div[contains(@id,'reportTemplateDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[2]")).click();//aers clicked	
	Thread.sleep(2000);
	driver.findElement(By.id("reportTemplateDetailForm:reportGroupListId_label")).click();
	driver.findElement(By.xpath(" //div[contains(@id,'reportTemplateDetailForm:reportGroupListId_panel')]//div[1]/ul[1]/li[2]")).click();
	boolean beforeStatusRegulatory =driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).isSelected();
	System.out.println(beforeStatusRegulatory);
	Thread.sleep(2000);
	if(!driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).isSelected())
	{
		driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).click();
	}
	Thread.sleep(2000);
	boolean afterStatusRegulatory=driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).isSelected();
	System.out.println(afterStatusRegulatory);
	Thread.sleep(3000);
	driver.findElement(By.id("reportTemplateDetailForm:descriptionId")).sendKeys("Test Template aers regulatory ");
	driver.findElement(By.id("reportTemplateDetailForm:simpleid_input")).click();
	setClipboardData("D:\\SapphireRpt&XML\\AERS-Regulatory.rptdesign");
	
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_V);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	
	System.out.println("complete Regulatory-AErs template created");
	Thread.sleep(5000);
//	driver.findElement(By.id("reportTemplateDetailForm:saveAndCloseTemplate")).click();//save&close
	driver.findElement(By.id("reportTemplateDetailForm:cancle")).click();//cancel		
	Thread.sleep(5000);
	//Create template csAcme Regulatory
			driver.findElement(By.linkText("ADMIN")).click();
			driver.findElement(By.linkText("Templates")).click();
			driver.findElement(By.id("reportTab:reportTemplateform:reportTemplateGrid:createReportTemplate")).click();
			driver.findElement(By.id("reportTemplateDetailForm:nameid")).sendKeys("Auto-CSacme-Regulatory-Template");
			driver.findElement(By.id("reportTemplateDetailForm:datasourceId_label")).click();//datasource clicked
			driver.findElement(By.xpath("//div[contains(@id,'reportTemplateDetailForm:datasourceId_panel')]//div[1]/ul[1]/li[3]")).click();//cs acme selected
			Thread.sleep(2000);
			driver.findElement(By.id("reportTemplateDetailForm:reportGroupListId_label")).click();
			driver.findElement(By.xpath(" //div[contains(@id,'reportTemplateDetailForm:reportGroupListId_panel')]//div[1]/ul[1]/li[2]")).click();
			boolean beforeStatusCSacmeRegulatory =driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).isSelected();
			System.out.println(beforeStatusCSacmeRegulatory);
			Thread.sleep(2000);
			if(!driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).isSelected())
			{
				driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).click();
			}
			Thread.sleep(2000);
			boolean afterStatusCSacmeRegulatory=driver.findElement(By.id("reportTemplateDetailForm:typesId:3")).isSelected();
			System.out.println(afterStatusCSacmeRegulatory);
			Thread.sleep(3000);
			driver.findElement(By.id("reportTemplateDetailForm:descriptionId")).sendKeys("Test Template CSacmeRegulatory");
			driver.findElement(By.id("reportTemplateDetailForm:simpleid_input")).click();
			setClipboardData("D:\\SapphireRpt&XML\\CS-Regulatory.rptdesign");
			//Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			System.out.println("complete CSacme Regulatorytemplate created");
		
		Thread.sleep(5000);
			//	driver.findElement(By.id("reportTemplateDetailForm:saveAndCloseTemplate")).click();//save&close
		driver.findElement(By.id("reportTemplateDetailForm:cancle")).click();//cancel
		Thread.sleep(5000);
	System.out.println("Finish template");

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
*/