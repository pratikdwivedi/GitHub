package com.sapphire.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_TestUserPrivileges {
	WebDriver driver;
	Actions act;
	String exlTopic;
	static WebElement weWF;

	@Test
	public void userPrivilegesTest() {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib = new ExcelLibrary();
			for (int userCount = 1; userCount <= lib.rowCount; userCount++) {
				String user = lib.getExcelData("users", userCount, 0, filePath);
				if (user != null) {
					String pwd = lib.getExcelData("users", userCount, 1,
							filePath);
					System.out.println("S.No.->" + lib.rowCount);
					String browser = lib.getExcelData("browsers", 1, 0,
							"SapphireFiles\\AutomationTestData.xls");
					String url = lib.getExcelData("browsers", 1, 1,
							"SapphireFiles\\AutomationTestData.xls");
					if (browser.equals("firefox")) {
						driver = new FirefoxDriver();
					} else if (browser.equals("ie")) {
						System.setProperty("webdriver.ie.driver",
								"jar files/IEDriverServer.exe");
						driver = new InternetExplorerDriver();
					} else if (browser.equals("chrome")) {
						System.setProperty("webdriver.chrome.driver",
								"jar files/chromedriver.exe");
						driver = new ChromeDriver();
					}
					driver.get(url);
					WebDriverWait wait = new WebDriverWait(driver, 60);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By
							.id("loginUserId")));
					driver.manage().window().maximize();
					WebElement element = driver.findElement(By
							.id("loginUserId"));
					element.sendKeys(user);
					driver.findElement(By.name("j_password")).sendKeys(pwd);
					driver.findElement(By.name("submit")).click();
					Thread.sleep(5000);
					System.out.println(driver.getTitle());
					File srcfile = ((ChromeDriver) driver)
							.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(srcfile, new File(
							"D:\\ScreenShots\\Admin\\" + user + ".png"));
					act = new Actions(driver);
					driver.findElement(By.linkText("WORKSPACE")).click();
					List<WebElement> menu = driver
							.findElements(By
									.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[1]"));
					List listHeaderItems = new ArrayList();
					for (WebElement weWF : menu) {
						String menuItemsName = weWF.getText();
						listHeaderItems.add(menuItemsName);
						listHeaderItems.remove(null);
						System.out.println("Menu Items : " + weWF.getText());
					}
					List listUserPri = new ArrayList();
					for (int i = 1; i <= lib.rowCount; i++) {
						String userPrivilegesAdmin = lib.getExcelData(
								"userPrivileges", i, userCount, filePath);
						listUserPri.add(userPrivilegesAdmin);
					}
					System.out.println(listUserPri);
					System.out
							.println(listHeaderItems.containsAll(listUserPri));
					Thread.sleep(2000);
					WebElement userLogOut = driver
							.findElement(By
									.xpath("//div[@class='ui-menu ui-menubar ui-widget ui-widget-content ui-corner-all ui-helper-clearfix userNav']//span[2][contains(@class,'ui-icon ui-icon-triangle-1-s')]"));
					act.moveToElement(userLogOut).build().perform();
					Thread.sleep(2000);
					driver.findElement(By.linkText("Sign Out")).click();
					System.out
							.println("Login Logout successfully with user ............"
									+ user);
					Reporter.log("Login Logout successfully with user............."
							+ user);
					driver.close();
					driver.quit();
				}
			}
		} catch (Throwable e) {
			
			e.printStackTrace();
			Assert.fail("Error in privileges test..");

		}
	}

}
