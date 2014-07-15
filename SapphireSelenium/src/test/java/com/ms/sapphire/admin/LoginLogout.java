package com.ms.sapphire.admin;

import java.io.File;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;
@Listeners({ ATUReportsListener.class, ConfigurationListener.class,
	MethodListener.class })
public class LoginLogout {
	WebDriver driver;
	String un;
	String pw;
	Actions act;
	String testUrl;
	String testBrowser;
	@Test
	public void login_logout() {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib = new ExcelLibrary();
			for (int i = 1; i <= lib.rowCount; i++) {
				System.out.println("S.No.->" + lib.rowCount);
				testBrowser = lib.getExcelData("browsers", 1, 0,
						"SapphireFiles\\AutomationTestData.xls");
				testUrl = lib.getExcelData("browsers", 1, 1,
						"SapphireFiles\\AutomationTestData.xls");
				if (testUrl == null) {
					Properties prop = new Properties();
					prop.load(new FileInputStream(
							"src/test/java/com/ms/sapphire/utility/sapphire_en.properties"));
					testUrl = prop.getProperty("testURL");
					testBrowser= prop.getProperty("testBrowser");
				}
				System.out.println("Start scenario on :" + testUrl);
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
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.id("loginUserId")));
				// driver.manage().timeouts().implicitlyWait(30,
				// TimeUnit.SECONDS);
				driver.manage().window().maximize();
				un = lib.getExcelData("loginlogout", i, 0, filePath);
				pw = lib.getExcelData("loginlogout", i, 1, filePath);
				WebElement element = driver.findElement(By.id("loginUserId"));
				element.sendKeys(un);
				driver.findElement(By.name("j_password")).sendKeys(pw);
				driver.findElement(By.name("submit")).click();
				// System.out.println(un);
				// System.out.println(pw);
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				File srcfile = null;
				if (testBrowser.equals("firefox")) {
					 srcfile = ((FirefoxDriver) driver)
							.getScreenshotAs(OutputType.FILE);
				} else if (testBrowser.equals("ie")) {
					 srcfile = ((InternetExplorerDriver) driver)
							.getScreenshotAs(OutputType.FILE);
				} else if (testBrowser.equals("chrome")) {
					 srcfile = ((ChromeDriver) driver)
							.getScreenshotAs(OutputType.FILE);
				}
				FileUtils.copyFile(srcfile, new File("ScreenShotsOfErrors\\"
						+ un + ".png"));
				act = new Actions(driver);
				driver.findElement(By.linkText("ADMIN")).click();
				Thread.sleep(2000);

				/*
				 * This code is for turn off E-signature & audit remarks
				 */

				/*
				 * driver.findElement(By.linkText("System settings")).click();
				 * String wd=driver.findElement(By.xpath(
				 * "//div[contains(@id,'settingsDetailForm:parameterTabView:enableAudit')]//div/span[contains(text(),'ON')]"
				 * )).getText(); driver.findElement(By.xpath(
				 * "//div[contains(@id,'settingsDetailForm:parameterTabView:enableAudit')]//div/span[contains(text(),'OFF')]"
				 * )).click(); driver.findElement(By.xpath(
				 * "//div[contains(@id,'settingsDetailForm:parameterTabView:enableEsig')]//div/span[contains(text(),'OFF')]"
				 * )).click();
				 * driver.findElement(By.id("settingsDetailForm:saveSettings"
				 * )).click(); Thread.sleep(2000);
				 * driver.switchTo().activeElement();
				 * driver.findElement(By.xpath
				 * ("//form[contains(@id,'auditRemarkForm')]//button[1]/span"
				 * )).click(); Thread.sleep(2000);
				 * driver.switchTo().activeElement();
				 * driver.findElement(By.id("reAuthenticationForm:password"
				 * )).sendKeys("sap@ms20120213#"); driver.findElement(By.xpath(
				 * "//form[contains(@id,'reAuthenticationForm')]//button[1]/span"
				 * )).click();
				 */

				/*
				 * Select test data date
				 */

				driver.findElement(By.linkText("System settings")).click();
				Select dropdown = new Select(
						driver.findElement(By
								.id("settingsDetailForm:parameterTabView:defaultdateformat")));
				dropdown.selectByVisibleText("MMM d, yyyy");
				driver.findElement(By.id("settingsDetailForm:saveSettings"))
						.click();
				Thread.sleep(2000);
				WebElement user = driver
						.findElement(By
								.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
				act.moveToElement(user).build().perform();
				Thread.sleep(2000);
				driver.findElement(By.linkText("Sign Out")).click();

				Reporter.log("Login Logout successfully with user............."
						+ un);

				driver.quit();
			}
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail("Error in login logout..");
		}
	}

}
