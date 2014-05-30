package com.practise;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;
public class SeleniumClass{
	WebDriver driver;
	@Test
	public void script() throws Exception{
		String filePath = ModuleExcelSelection
				.getExcelFilePath(IModuleSelection.ADMIN);
		try{
			ExcelLibrary lib = new ExcelLibrary();
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/com/test/sapphire_en.properties"));
			 String ADMINs = prop.getProperty("admin");
		        String SYS = prop.getProperty("systemsettings");
				
			for (int i = 1; i <= lib.rowCount; i++) {
				
				System.setProperty("webdriver.chrome.driver",
						"jar files/chromedriver.exe");
				driver = new ChromeDriver();
				driver.get("http://192.168.1.20:8080/sapphire");
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By
						.id("loginUserId")));
				// driver.manage().timeouts().implicitlyWait(30,
				// TimeUnit.SECONDS);
				driver.manage().window().maximize();
				String un = lib.getExcelData("loginlogout", i, 0, filePath);
				String pw = lib.getExcelData("loginlogout", i, 1, filePath);
				WebElement element = driver.findElement(By.id("loginUserId"));
				element.sendKeys(un);
				driver.findElement(By.name("j_password")).sendKeys(pw);
				driver.findElement(By.name("submit")).click();
				// System.out.println(un);
				// System.out.println(pw);
				Thread.sleep(5000);
				System.out.println(driver.getTitle());
				Actions act = new Actions(driver);
				driver.findElement(By.linkText(ADMINs)).click();
				Thread.sleep(2000);
				
				 driver.findElement(By.xpath("//form[contains(@id,'adminForm')]//div/ul/li/a/span[contains(text(),'"+SYS+"')]")).click();
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
			}
		catch(Throwable e){
			e.printStackTrace();
			
		}
	}
}
