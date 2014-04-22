package com.seleniumScripts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;
public class SignUp {

	WebDriver driver;
	String un;
	String pw;
	Actions act;
	@Test(description = "Login Logout Decription")
	public void LoginLogoutMethod(){
		try {
			
				System.setProperty("webdriver.chrome.driver","lib/chromedriver.exe");
				driver=new ChromeDriver();
				driver.get("http://192.168.1.59:8080/MavenWeb/SignUp.jsp");
				WebDriverWait wait = new WebDriverWait(driver,60);
				Thread.sleep(5000);
				driver.manage().window().maximize();
				driver.findElement(By.id("username")).sendKeys("Pratik");
				driver.findElement(By.id("mobno")).sendKeys("1234567890");
				driver.findElement(By.id("email")).sendKeys("Pratik@medsight.com");
				driver.findElement(By.id("submit")).click();
				Thread.sleep(8000);
				System.out.println("Login Logout successfully with user ............"+ un);
				Reporter.log("Login Logout successfully with user............."+ un);
				driver.close();
				driver.quit();
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
}
