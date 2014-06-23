package com.sapphire.admin;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class LoginLogout {
	WebDriver driver;
	String un;
	String pw;
	Actions act;

	@Test
	public void LoginLogoutMethod() {
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib = new ExcelLibrary();
			for (int i = 1; i <= lib.rowCount; i++) {
				System.out.println("S.No.->" + lib.rowCount);
				System.setProperty("webdriver.chrome.driver",
						"jar files/chromedriver.exe");
				driver = new ChromeDriver();
				driver.get("http://192.168.1.5:8080/sapphire");
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
				File srcfile = ((ChromeDriver) driver)
						.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(srcfile, new File("D:\\ScreenShots\\Admin\\"
						+ un + ".png"));
				act = new Actions(driver);
				driver.findElement(By.linkText("ADMIN")).click();
				Thread.sleep(2000);
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
