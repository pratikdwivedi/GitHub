package com.sapphire.admin;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_Delete_Role extends Config{
	@Test
	public void deleteUserRole() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.ADMIN_DELETE);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'ADMIN')]")).click();
		System.out.println("Admin clicked ");
		driver.findElement(By.linkText("Roles")).click();
		String userRoleDelete=lib1.getExcelData("userRoleDelete",i, 0,filePath);
		driver.findElement(By.xpath("//tr[td[2][div[a[text()='"+userRoleDelete+"']]]]//td[1]//div[2]")).click();
		driver.findElement(By.id("roleform:roleGrid:deleteRole")).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
		Thread.sleep(5000);
			}
		}//try
		catch(Throwable e)
		{
			takeScreenShot(e, "Admin_Delete_User_Role");
			Assert.fail("Error in deleteing user Role ");
			e.printStackTrace();
		}
	}
}
