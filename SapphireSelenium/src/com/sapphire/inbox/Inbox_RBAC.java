package com.sapphire.inbox;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
public class Inbox_RBAC extends Inbox_logout{
	
	int j;int k;
	List list1;
	String exlTopic;
	static WebElement weWF;
	@Test
	public void InboxRbac() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.INBOX);
		try {
			ExcelLibrary inboxLib=new ExcelLibrary();
			for(int i=1;i<=inboxLib.rowCount;i++)
			{
				list1 = new ArrayList();
				String user=inboxLib.getExcelData("workflowrbac", i, 0,filePath);
				if(user!=null)
				{
				String pwd=inboxLib.getExcelData("workflowrbac", i, 1,filePath);
				String browser=inboxLib.getExcelData("browsers",1,0,"SapphireFiles\\AutomationTestData.xls");
				String url=inboxLib.getExcelData("browsers",1,1,"SapphireFiles\\AutomationTestData.xls");
				System.out.println("Start scenario");
			if(browser.equals("firefox"))
			{
				driver=new FirefoxDriver();
			}
			else if(browser.equals("ie"))
			{
				System.setProperty("webdriver.ie.driver","jar files/IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}
			else if(browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","jar files/chromedriver.exe");
					driver=new ChromeDriver();
			}
				driver.get(url);
				driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
				driver.manage().window().maximize();
				WebElement element=driver.findElement(By.id("loginUserId"));
				element.sendKeys(user);
				driver.findElement(By.name("j_password")).sendKeys(pwd);
				Thread.sleep(3000);
				driver.findElement(By.name("submit")).click();
				Assertions.assertText(driver.getTitle(), "Sapphire");
				driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'INBOX')]")).click();
				System.out.println(user);
				WebElement weUserName=driver.findElement(By.xpath("//div[contains(@class,'userNavBox')]//table/tbody/tr/td[2]/div/ul/li[1]/a/span[1]"));
				String uName=weUserName.getText();
				driver.findElement(By.id("itemform:itemGrid:activitiesList_label")).click();
				if(user.equalsIgnoreCase(uName))
				{
			//Workflow activity list		
				List<WebElement> wfActivity=driver.findElements(By.xpath("//div[contains(@id,'itemform:itemGrid:activitiesList_panel')]//ul/li"));
				list1 = new ArrayList();
				for(WebElement weWF:wfActivity)
					{
					String activity=weWF.getText();
					 list1.add(activity);
					System.out.println("WFActivity : "+weWF.getText());
					}
				System.out.println(list1);
				for(j=1;j<=inboxLib.rowCount;j++)
				{
					String exlWfAct=inboxLib.getExcelData(user,j, 2,filePath);
					if(!list1.contains(exlWfAct)){
						System.out.println("FAILED");
						break;
					}
					else{
						System.out.println("PASSED");
					}
					System.out.println("From excel : "+exlWfAct);
				}
				}
				//masterList				
			List<WebElement> masterList=driver.findElements(By.xpath("//ul[contains(@class,'ui-tree-container')]/li/span/span[3]/table/tbody/tr/td[1]"));
					for(WebElement weMaster:masterList)
					{
						System.out.println("Master from application : "+weMaster.getText());
						String master=weMaster.getText();
						 list1.add(master);
					}
					for(j=1;j<=inboxLib.rowCount;j++)
					{
						String exlMaster=inboxLib.getExcelData(user,j, 0,filePath);
						if(exlMaster!=null)
						{
							if(!list1.contains(exlMaster)){
							System.out.println("FAILED");
							break;
							}
							else{
							System.out.println("PASSED");
							}
							System.out.println("From excel : "+exlMaster);
						}
					}
			driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li[3]/a/span")).click();
			Thread.sleep(3000);
			for(k=1;k<inboxLib.rowCount;k++)
			{
			String selectAlert=inboxLib.getExcelData(user, k, 4, filePath);
			if(selectAlert!=null)
				{
				driver.findElement(By.id("itemform:itemGrid:gblSrc1")).clear();
				driver.findElement(By.id("itemform:itemGrid:gblSrc1")).sendKeys(selectAlert);
				driver.findElement(By.id("itemform:itemGrid:gblSrcBtn")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//tbody[contains(@id,'itemform:itemGrid_data')]//tr/td[5]/div/a[contains(text(),'"+selectAlert+"')]")).click();	
				Thread.sleep(3000);
			}
				Thread.sleep(2000);
			//source 
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[1]/a[contains(text(),'Source')]")).click();
			driver.findElement(By.xpath("//tbody[contains(@id,'tabView:alertCaseForm:alertCaseGrid_data')]//tr/td[4]/div/a")).click();	
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'caseDetailForm:caseDetailDlg')]//div[3]/span/button[2]/span")).click();
	//Report
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[2]/a[1]")).click();
	//Analysis
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[3]/a")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[contains(@class,'ui-toolbar-group-right')]//button[1]/span")).click();
			driver.findElement(By.xpath("//tbody[contains(@id,'caseListForm:caseGrid_data')]//tr/td[4]/div/a")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			driver.findElement(By.xpath("//div[contains(@id,'caseDetailForm:caseDetailDlg')]//div[3]/span/button[2]/span")).click();
			//annotation
			driver.findElement(By.xpath("//tbody[contains(@id,'caseListForm:caseGrid_data')]//tr/td/div/div/div[2]")).click();
			driver.findElement(By.xpath("//div[contains(@id,'caseListForm:caseGrid')]/table/thead/tr/th/div/div/span/button[4]/span")).click();//annotation button
			driver.switchTo().activeElement();
			String annotationAnalysisCase=inboxLib.getExcelData(user, k,5, filePath);
			if(annotationAnalysisCase!=null)
			{
			driver.findElement(By.id("appAnnotateForm:listAnnotateType_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:listAnnotateType_panel')]//div/ul/li[text()='"+annotationAnalysisCase+"']")).click();
			driver.findElement(By.id("appAnnotateForm:listAnnotateScope_label")).click();
			String annotationScope=inboxLib.getExcelData(user, k,6, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:listAnnotateScope_panel')]//div/ul/li[contains(text(),'"+annotationScope+"')]")).click();
			driver.findElement(By.id("appAnnotateForm:priority_label")).click();
			String annotationPriority=inboxLib.getExcelData(user, k,7, filePath);
			driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:priority_panel')]//div/ul/li[contains(text(),'"+annotationPriority+"')]")).click();
			String annotationRemarks=inboxLib.getExcelData(user, k, 8, filePath);
			driver.findElement(By.id("appAnnotateForm:listAnnotateRem")).sendKeys(annotationRemarks);
			String annotationFile=inboxLib.getExcelData(user, k,9,filePath);//annotation file upload
			driver.findElement(By.xpath("//div[contains(@id,'appAnnotateForm:lstfileUploadId')]//div[1]/label")).click();
			Thread.sleep(3000);	
			setClipboardData(annotationFile);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(5000);
			driver.findElement(By.id("appAnnotateForm:lstappAnnoate")).click();
			Thread.sleep(10000);
			}
			else
			{
			driver.findElement(By.id("appAnnotateForm:lstclose")).click();
			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(@class,'ui-layout-unit-header-icon ui-state-default ui-corner-all')]//span[contains(@class,'ui-icon ui-icon-triangle-1-s')]")).click();
			Thread.sleep(2000);
	//Drilldown
			String drilldownCriteria=inboxLib.getExcelData(user, k, 29, filePath);
			driver.findElement(By.xpath("//tr[td[2][div[text()='"+drilldownCriteria+"']]]//td[1]/div/div/div[2]")).click();
			String groupBy=inboxLib.getExcelData(user, k, 30, filePath);
			if(groupBy.equals("Step"))
			{
			//	driver.findElement(By.id("//div[contains(@id,'tabView:analysisDetailGroupForm:groupTabView')]//ul/li/a[contains(text(),'"+groupBy+"')]")).click();
				String filter=inboxLib.getExcelData(user, k, 31, filePath);
				driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+filter+"')]]]//td[1]/div/div[2]")).click();
			}
			driver.findElement(By.id("tabView:analysisDetailToolbarForm:expbut")).click();
			Thread.sleep(10000);
			driver.findElement(By.id("tabView:analysisDetailToolbarForm:save")).click();
			Thread.sleep(5000);
	//Evaluation
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[4]/a")).click();
			driver.findElement(By.id("tabView:evaluationForm:itemEvalGrid:createEvaluation")).click();
			Thread.sleep(3000);
			driver.switchTo().activeElement();
			String evalTitle=inboxLib.getExcelData(user, k, 14, filePath);
			if(evalTitle!=null)
			{
			driver.findElement(By.id("tabView:evaluationDetailForm:evalTabView:evalName")).sendKeys(evalTitle);
			String evalOutcome=inboxLib.getExcelData(user, k, 15, filePath);
			driver.findElement(By.id("tabView:evaluationDetailForm:evalTabView:evalStatus_label")).click();
			driver.findElement(By.xpath("//div[contains(@id,'tabView:evaluationDetailForm:evalTabView:evalStatus_panel')]//div/ul/li[contains(text(),'"+evalOutcome+"')]")).click();
			String evalDescription=inboxLib.getExcelData(user, k, 16, filePath);
			driver.findElement(By.id("tabView:evaluationDetailForm:evalTabView:evalDescription")).sendKeys(evalDescription);
			String evalCriteria=inboxLib.getExcelData(user, k, 17, filePath);
			driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+evalCriteria+"')]]]//td[1]/div")).click();
			String evalOptions=inboxLib.getExcelData(user, k, 18, filePath);
			driver.findElement(By.xpath("//tr[td[2][label[contains(text(),'"+evalOptions+"')]]]//td[1]/div")).click();
			String evalDoc=inboxLib.getExcelData(user, k, 19, filePath);
			if(evalDoc!=null)
				{
				driver.findElement(By.xpath("//div[contains(@id,'tabView:evaluationDetailForm:evalTabView')]//ul/li/a[contains(text(),'Documents')]")).click();
				driver.findElement(By.id("tabView:evaluationDetailForm:evalTabView:docGrid:addDoc")).click();
				driver.switchTo().activeElement();
				driver.findElement(By.id("tabView:supDocDetailForm:Title")).sendKeys(evalDoc);
				String evalTemplateType=inboxLib.getExcelData(user, k, 20, filePath);
				driver.findElement(By.id("tabView:supDocDetailForm:typeId_label")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[contains(@id,'tabView:supDocDetailForm:typeId_panel')]//div/ul/li[contains(text(),'"+evalTemplateType+"')]")).click();
				String evalDocDescription=inboxLib.getExcelData(user, k, 21, filePath);
				driver.findElement(By.id("tabView:supDocDetailForm:description")).sendKeys(evalDocDescription);
				String evalDocFile=inboxLib.getExcelData(user, k, 22, filePath);
				driver.findElement(By.xpath("//div[contains(@id,'tabView:supDocDetailForm:supDocDtlDlg')]//div[2]/div[contains(@id,'tabView:supDocDetailForm:uploadNewAtrId')]")).click();
				Thread.sleep(3000);	
				setClipboardData(evalDocFile);
				Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(3000);
				driver.findElement(By.id("tabView:supDocDetailForm:close")).click();//close
				Thread.sleep(3000);
				driver.findElement(By.id("tabView:evaluationDetailForm:saveEvaluation")).click();//Evaluation save
				Thread.sleep(3000);
				}
			}
	//Minutes
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[5]/a")).click();
	//Actions
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[6]/a")).click();
	//Documents
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[7]/a")).click();
			String supDocTitle=inboxLib.getExcelData(user, k, 23, filePath);
			if(supDocTitle!=null)
			{
				driver.findElement(By.id("tabView:itemDocumentForm:itemDocGrid:addDoc")).click();
				Thread.sleep(2000);
				driver.switchTo().activeElement();
				driver.findElement(By.id("tabView:itemSupDocDetailForm:Title")).sendKeys(supDocTitle);
				String supDocTemplateType=inboxLib.getExcelData(user, k, 24, filePath);
				driver.findElement(By.id("tabView:itemSupDocDetailForm:typeId_label")).click();
				driver.findElement(By.xpath(" //div[contains(@id,'tabView:itemSupDocDetailForm:typeId_panel')]//div/ul/li[contains(text(),'"+supDocTemplateType+"')]")).click();
				String supDocDescription=inboxLib.getExcelData(user, k, 25, filePath);
				driver.findElement(By.id("tabView:itemSupDocDetailForm:Description")).sendKeys(supDocDescription);
				String supDocFile=inboxLib.getExcelData(user, k, 26, filePath);
				driver.findElement(By.xpath("//div[contains(@id,'tabView:itemSupDocDetailForm:itemSupDocDtlDlg')]//div[2]/div[contains(@id,'tabView:itemSupDocDetailForm:uploadNewAtrId')]")).click();
				Thread.sleep(3000);	
				setClipboardData(supDocFile);
				Robot robot=new Robot();
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				Thread.sleep(2000);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				Thread.sleep(3000);
				driver.findElement(By.id("tabView:itemSupDocDetailForm:saveAnalysisDoc")).click();
				Thread.sleep(3000);
			}
	//Linked Records
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[8]/a")).click();
			String linkedRecord=inboxLib.getExcelData(user, k, 27, filePath);
			if(linkedRecord!=null)
			{
				driver.findElement(By.id("tabView:linkedRecordForm:itemLinkedRecordGrid:addLinkedRecord")).click();
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'itemLookupForm:linkedItemGrid')]//table/thead/tr/th/div/div/span/input")).sendKeys(linkedRecord);
				driver.findElement(By.xpath("//div[contains(@id,'itemLookupForm:linkedItemGrid')]//table/thead/tr/th/div/div/span/button[1]/span")).click();
				Thread.sleep(3000);
				driver.findElement(By.xpath("//tr[td[2][div[contains(text(),'"+linkedRecord+"')]]]//td[1]/div/div/div[2]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("itemLookupForm:addLinkedRecord")).click();
				String linkedRecordRemark=inboxLib.getExcelData(user, k, 28, filePath);
				Thread.sleep(3000);
				driver.switchTo().activeElement();
				driver.findElement(By.xpath("//div[contains(@id,'itemLookupRemForm:remarkDlg')]//div[2]/div/textarea[1]")).sendKeys(linkedRecordRemark);
				driver.findElement(By.xpath("//div[contains(@id,'itemLookupRemForm:remarkDlg')]//div[3]/span/button[1]/span")).click();
				Thread.sleep(3000);
			}
	//Activity Logs
			driver.findElement(By.xpath("//div[contains(@id,'tabView')]//ul/li[9]/a")).click();
			Thread.sleep(3000);
			}//if of alert check in test data file
			doLogout();
			System.out.println("Logout user ->"+user);
				}
				else
				{
					break;
				}
		}//for close
	}//try close
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("Error in Inbox RBAC");
		}
	}//method close	
	public static void setClipboardData(String string) {
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
}