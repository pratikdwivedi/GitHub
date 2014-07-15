package com.ms.sapphire.utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Config extends ExcelLibrary {
	public static WebDriver driver;
	public Actions act;
	protected int rowCount = 1;
	int i;
	protected String un;
	protected String pw;
	public Robot robot;
	public static String testUrl;
	public static	String testBrowser;
	@BeforeClass
	public void doLogIn() {
		try {
			Thread.sleep(5000);
			ExcelLibrary lib = new ExcelLibrary();
			testBrowser = lib.getExcelData("browsers", 1, 0,
					"SapphireFiles\\AutomationTestData.xls");
			testUrl = lib.getExcelData("browsers", 1, 1,
					"SapphireFiles\\AutomationTestData.xls");
			if (testUrl==null || testBrowser==null) {
				Properties prop = new Properties();
				prop.load(new FileInputStream(
						"src/test/java/com/ms/sapphire/utility/sapphire_en.properties"));
				testUrl = prop.getProperty("testURL");
				testBrowser= prop.getProperty("testBrowser");
			}

			System.out.println("Start scenario");
			if (testBrowser.equals("firefox")) {
				driver = new FirefoxDriver();
			} else if (testBrowser.equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						"jar files/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else if (testBrowser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						"jar files/chromedriver.exe");
				driver = new ChromeDriver();
			}
			driver.get(testUrl);
			// driver=new FirefoxDriver();
			// driver.get("http://192.168.1.8:9090/sapphire");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			ExcelLibrary lib2 = new ExcelLibrary();
			un = lib2.getExcelData("loginlogout", 1, 0,
					"SapphireFiles\\AutomationTestData.xls");
			pw = lib2.getExcelData("loginlogout", 1, 1,
					"SapphireFiles\\AutomationTestData.xls");
			WebElement element = driver.findElement(By.id("loginUserId"));
			element.sendKeys(un);
			driver.findElement(By.name("j_password")).sendKeys(pw);
			driver.findElement(By.name("submit")).click();
			Thread.sleep(5000);
			System.out.println(driver.getTitle());
			System.out.println("Login Successful");
			act = new Actions(driver);
			robot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void doLogOut() throws InterruptedException {
		Thread.sleep(2000);
		WebElement user = driver
				.findElement(By
						.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
		act.moveToElement(user).build().perform();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("sign out successfully");
		driver.quit();
		System.out.println("browser closed");
		Thread.sleep(2000);
		System.out.println("complete");
		Thread.sleep(2000);
	}

	public static void setClipboardData(String string) {
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard()
				.setContents(stringSelection, null);
	}

	public void takeScreenShot(Throwable e, String fileName) {
		File screenShot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot, new File("ScreenShotsOfErrors\\"
					+ fileName + ".png"));
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage(), ioe);
		}
		try {
			throw e;
		} catch (Throwable e1) {

			e1.printStackTrace();
		}
	}
}