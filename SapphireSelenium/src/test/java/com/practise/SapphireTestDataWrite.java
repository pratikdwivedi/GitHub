package com.practise;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.Config;
import com.ms.sapphire.utility.ExcelLibrary;

public class SapphireTestDataWrite extends Config{
	String writeFilePath="D:\\Work\\TestWrite.xls";
	

	@Test
	public void DataWrite() throws Exception{
	//	String filePath = ModuleExcelSelection.getExcelFilePath(IModuleSelection.ADMIN);
		try {
			ExcelLibrary lib1=new ExcelLibrary();
			
			for(int i=1;i<=lib1.rowCount;i++)
			{
			String	expectedtitle=driver.getTitle();
		driver.findElement(By.xpath("//div[contains(@id,'headerMenu')]//ul/li/a/span[contains(text(),'ADMIN')]")).click();
		if(expectedtitle.equalsIgnoreCase("SapPhiRe")){

		    System.out.println("PASS");
		    String status="PASS";
		    writeExcelData(status,rowCount,writeFilePath);
		    }
		else{
		    System.out.println("FAIL");
		    String status = "FAIL";
		    writeExcelData(status,rowCount,writeFilePath);
		    }
			
			}
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
}
