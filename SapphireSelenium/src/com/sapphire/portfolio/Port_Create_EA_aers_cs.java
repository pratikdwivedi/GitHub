package com.sapphire.portfolio;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
import com.test.Save_Analysis;

public class Port_Create_EA_aers_cs extends Config {
	int k;
	@Test
	public void TestMethodPortCreateTopicAlerts_EA_aers() throws Exception{
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
				String masterName=lib1.getExcelData("topicalerts", i, 0, filePath);
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
		        String topicName=lib1.getExcelData("topicalerts", i, 1, filePath);
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
				String alertType=lib1.getExcelData("topicalerts", i, 2, filePath);
				WebElement weAlert=driver.findElement(By.linkText(alertType));
				act.moveToElement(weAlert).click();//FDA AERS clicked
				act.perform();
				Thread.sleep(3000);
				String drugLevel=lib1.getExcelData("topicalerts", i, 5, filePath);
				driver.findElement(By.id("analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_label")).click();//drug level clicked
				driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:tabView:drugsetGrid:0:fieldId_panel')]//div/ul/li[contains(text(),'"+drugLevel+"')]")).click();//drug level select				
				Thread.sleep(3000);
				driver.findElement(By.id("analysisCriteriaForm:tabView:drugsetGrid:0:lookupEADrug")).click();
				String dictLevel=lib1.getExcelData("topicalerts", i, 6, filePath);
				driver.findElement(By.id("productLookupEAForm:productLookupGrid:searchLevel_label")).click();//dict level clicked
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[contains(@id,'productLookupEAForm:productLookupGrid:searchLevel_panel')]//div/ul/li[contains(text(),'"+dictLevel+"')]")).click();
				Thread.sleep(2000);
				String drugNamePort=lib1.getExcelData("topicalerts", i, 7, filePath);
				driver.findElement(By.id("productLookupEAForm:productLookupGrid:srcTxtId")).sendKeys(drugNamePort);//drug name passed
				driver.findElement(By.xpath("//div[contains(@id,'productLookupEAForm:productLookupGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();//search clicked
				Thread.sleep(2000);
				driver.findElement(By.xpath("//tbody[contains(@id,'productLookupEAForm:productLookupGrid_data')]//tr/td/div/div/div[2]")).click();
				Thread.sleep(2000);
				driver.findElement(By.id("productLookupEAForm:selectProduct")).click();
				Thread.sleep(3000);
				String groupTab=lib1.getExcelData("topicalerts", i, 13, filePath);
				driver.findElement(By.linkText(groupTab)).click();
				if(groupTab.equals("Step"))
				{
					if(alertType.equals("FDA AERS")) //Group in FDA aers
					{
						String groupByaers=lib1.getExcelData("topicalerts", i, 14, filePath);
						Thread.sleep(2000);
						driver.findElement(By.xpath("//table[contains(@id,'analysisCriteriaForm:groupTabView:singlePanelView')]//tbody/tr/td[2]/label[contains(text(),'"+groupByaers+"')]")).click();
						Thread.sleep(2000);
					}
					else if(alertType.equals("Company safety"))  //Group in CS 
					{
						String groupByCS=lib1.getExcelData("topicalerts", i, 14, filePath);
						Thread.sleep(2000);
						driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:groupTabView:singleTab')]//div/div//tr/td[2]/label[contains(text(),'"+groupByCS+"')]")).click();
						Thread.sleep(2000);
					}
				}
				else if(groupTab.equals("Parallel"))
				{
					driver.findElement(By.linkText("Parallel")).click();
					if(alertType.equals("FDA AERS")) //Group in FDA aers
					{
						
						for(int j=1;j<=lib1.rowCount;j++)
						{
							String parallelGroupBy=lib1.getExcelData("parallel-nested", j, 0, filePath);
							WebElement w=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+parallelGroupBy+"')]]]]//td[1]/div/div/div[2]"));
							w.click();	
						}
					}		
					else if(alertType.equals("Company safety"))  //Group in CS 
					{
						for(int k=1;k<=lib1.rowCount;k++)
						{
							String CSparallelGroupBy=lib1.getExcelData("parallel-nested", k, 3, filePath);
							WebElement wCS=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+CSparallelGroupBy+"')]]]]//td[1]/div/div/div[2]"));
							wCS.click();	
						}
					}
				}
				else if(groupTab.equals("Nested"))
				{
					driver.findElement(By.linkText("Nested")).click();
					if(alertType.equals("FDA AERS")) //Group in FDA aers
					{
					for(k=1;k<=lib1.rowCount;k++)
						{
						String nestedGroupBy=lib1.getExcelData("parallel-nested",k, 1, filePath);	
						Select sl=new Select(driver.findElement(By.id("analysisCriteriaForm:groupTabView:groupByNested")));
						sl.selectByVisibleText(nestedGroupBy);
						driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:groupTabView:nestedTab')]//button[1]/span")).click();	
						Thread.sleep(3000);
						}
					System.out.println("Pivote selection start.....");
					String pivoteEA=lib1.getExcelData("parallel-nested",1, 2, filePath);
					driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
					Thread.sleep(3000);
					driver.navigate().refresh();
					Thread.sleep(3000);
					WebElement wmt=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+pivoteEA+"')]]]]//td[1]/div/div/div[2][contains(@class,'ui-radiobutton-box ui-widget ui-corner-all ui-radiobutton-relative ui-state-default')]"));		
					Thread.sleep(3000);
					wmt.click();
					Thread.sleep(2000);
					System.out.println("Pivote selected : "+pivoteEA);
					}
					else if(alertType.equals("Company safety"))  //Group in CS 
					{
						for(k=1;k<=lib1.rowCount;k++)
						{
						String CSnestedGroupBy=lib1.getExcelData("parallel-nested",k, 4, filePath);	
						Select sl=new Select(driver.findElement(By.id("analysisCriteriaForm:groupTabView:groupByNested")));
						sl.selectByVisibleText(CSnestedGroupBy);
						driver.findElement(By.xpath("//div[contains(@id,'analysisCriteriaForm:groupTabView:nestedTab')]//button[1]/span")).click();	
						Thread.sleep(3000);
						}
						System.out.println("Pivote selection start.....");
						String pivoteCS=lib1.getExcelData("parallel-nested",1, 5, filePath);
						driver.findElement(By.id("analysisCriteriaForm:expbut")).click();
						Thread.sleep(3000);
						driver.navigate().refresh();
						Thread.sleep(3000);
						WebElement wmtCS=driver.findElement(By.xpath("//tr[td[2][div[label[contains(text(),'"+pivoteCS+"')]]]]//td[1]/div/div/div[2][contains(@class,'ui-radiobutton-box ui-widget ui-corner-all ui-radiobutton-relative ui-state-default')]"));		
						Thread.sleep(3000);
						wmtCS.click();
						Thread.sleep(2000);
						System.out.println("Pivote selected : "+pivoteCS);
					}
					Thread.sleep(10000);	
				}
				Thread.sleep(3000);
				sa.analysisName=lib1.getExcelData("topicalerts", i, 15, filePath);
				sa.status=lib1.getExcelData("topicalerts", i, 16, filePath);	
				sa.priority=lib1.getExcelData("topicalerts", i, 17, filePath);	
				sa.scheduler=lib1.getExcelData("topicalerts", i, 18, filePath);
				sa.route=lib1.getExcelData("topicalerts", i, 19, filePath);
				sa.remarks=lib1.getExcelData("topicalerts", i, 20, filePath);
				sa.save_Analysis();
				driver.findElement(By.linkText("INBOX")).click();
				WebElement alertCheck=driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a"));
				String alertCheck2=alertCheck.getText();
			//	Assertions.verifyEquals(analysisPortName, alertCheck2);
				if(sa.analysisName.equals(alertCheck2))
				{
					System.out.println("Test passed");
					Reporter.log("Test Passed : Created analysis exists in INBOX");
				}
				else 
				{
					System.out.println("Test Failed");
					Reporter.log("Test Failed : Created analysis does not exists in INBOX");
				}
				
				Thread.sleep(10000);
			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("Error in creating topic Alerts ");
		}
	}
}
