package com.sapphire.admin;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;
import com.test.IModuleSelection;
import com.test.ModuleExcelSelection;

public class Admin_Delete_Workflow extends Config{
	@Test
	public void deleteWorkflow() throws Exception{
		String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.ADMIN_DELETE);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{
			Assertions.assertText(driver.getTitle(), "Sapphire");
		driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'ADMIN')]")).click();
		System.out.println("Admin clicked ");
		driver.findElement(By.linkText("Workflows")).click();
		String workflowDelete=lib1.getExcelData("deleteWorkflow",i, 0,filePath);
		driver.findElement(By.xpath("//tr[td[3][div[a[contains(text(),'"+workflowDelete+"')]]]]//td[1]//div[2]")).click();
		driver.findElement(By.id("workflowform:workflowGrid:deletewf")).click();
		Thread.sleep(3000);
		driver.switchTo().activeElement();
		driver.findElement(By.id("removeConfirmForm:confirmRemove")).click();
		driver.switchTo().activeElement();
		String alertText=driver.findElement(By.xpath("//div[contains(@class,'ui-growl-item-container ui-state-highlight ui-corner-all ui-helper-hidden ui-shadow')]//div/div[2]/p")).getText();
		System.out.println(alertText);
		if(alertText.equals("Cannot delete! one or more selected workflow is in use."))
		{
			Assert.assertEquals(alertText, "Cannot delete! one or more selected workflow is in use");
		}
		Thread.sleep(5000);
			}
		}//try
		catch(Throwable e)
		{
			takeScreenShot(e, "Admin_Delete_Workflow");
			Assert.fail("Error in deleteing workflow ");
			e.printStackTrace();
		}
	}
}
