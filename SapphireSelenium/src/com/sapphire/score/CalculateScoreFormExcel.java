package com.sapphire.score;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.testng.annotations.Test;
import com.test.ExcelLibrary;
public class CalculateScoreFormExcel {
	@Test
	public void dataEntry()
	{
		try{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss a");
			Calendar cal = Calendar.getInstance();
			System.out.println("Run Date : "+dateFormat.format(cal.getTime()));
	ExcelLibrary wd=new ExcelLibrary();
	System.out.println("Writing to Excel...");
	wd.writeExcelEvaluation(114, 10, 3,"D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx");//A
	wd.writeExcelEvaluation(152138, 12, 3,"D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx");//A+C
	wd.writeExcelEvaluation(2587, 10, 5,"D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx");//A+B
	wd.writeExcelEvaluation(5726581, 12, 5,"D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx");//A+B+C+D
	System.out.println("Reading from excel...");
	System.out.println("B : "+wd.readExcelEvaluation("Sheet1", "E11", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("D : "+wd.readExcelEvaluation("Sheet1", "E12", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("B+D : "+wd.readExcelEvaluation("Sheet1", "E13", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("C+D : "+wd.readExcelEvaluation("Sheet1", "F12", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("Calculating from excel...");
	//	wd.evaluateAllExl("Sheet1", "E33", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx");//
	System.out.println("Exp A : "+wd.readExcelEvaluation("Sheet1", "E27", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("PRR (-) : "+wd.readExcelEvaluation("Sheet1", "E35", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("PRR : "+wd.readExcelEvaluation("Sheet1", "E33", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
	System.out.println("PRR (+) : "+wd.readExcelEvaluation("Sheet1", "E36", "D:\\Work\\Signal_Score_Algorithms_v1.0-Internal.xlsx"));//
		}
		catch(Throwable e)
		{e.printStackTrace();}
	}	
	
}
