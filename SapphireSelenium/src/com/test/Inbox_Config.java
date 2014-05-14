package com.test;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Inbox_Config extends ExcelLibrary{
	public WebDriver driver;
	
	protected int rowCount=1;
	
	protected String un;
	protected String pw;
	public Robot robot;
	WebElement userLogout;
	public void doLogInInbox() {
		try{
			ExcelLibrary inboxLib=new ExcelLibrary();
			ExcelLibrary inboxLib2=new ExcelLibrary();
			for(int i=1;i<=inboxLib.rowCount;i++)
			{
				String browser=inboxLib.getExcelData("browsers",1,0,"SapphireFiles\\AutomationTestData.xls");
				String url=inboxLib.getExcelData("browsers",1,1,"SapphireFiles\\AutomationTestData.xls");
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
			for(int userNo=1;userNo<=inboxLib2.rowCount;userNo++)
			{
			un=inboxLib.getExcelData("workflowrbac", userNo, 0,"SapphireFiles\\InboxTestData.xls");
			pw=inboxLib.getExcelData("workflowrbac", userNo, 1,"SapphireFiles\\InboxTestData.xls");
			}
			WebElement element=driver.findElement(By.id("loginUserId"));
			element.sendKeys(un);
			driver.findElement(By.name("j_password")).sendKeys(pw);
			driver.findElement(By.name("submit")).click();
			Thread.sleep(3000);
			break;
			}
		}
		catch(Throwable e)
		{
		e.printStackTrace();	
		}
		
		
		}
	/*
	 * @AfterClass
	public void doLogoutInbox() throws InterruptedException 
	{
	userLogout=driver.findElement(By.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
	act.moveToElement(userLogout).build().perform();
	Thread.sleep(3000);
	driver.findElement(By.linkText("Sign Out")).click();
	System.out.println("sign out successfully");
	driver.quit();
	System.out.println("browser closed");
	Thread.sleep(2000);
	System.out.println("complete");
	Thread.sleep(2000);
	}
	*/
	
	}
