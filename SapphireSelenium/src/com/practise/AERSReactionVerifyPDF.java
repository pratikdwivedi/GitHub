package com.practise;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.DatabaseConnect;
import com.test.ExcelLibrary;
import com.test.PdfUtility;

public class AERSReactionVerifyPDF {
	String writeFilePath = "TestOutputFiles\\Testoutput.xls";
	ExcelLibrary exl = new ExcelLibrary();

	@Test(enabled = true)
	public void reactionCaseValidFrom() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		// create a resultList that will contain number of test cases
		List<String> resultList = new ArrayList<String>();
		// create an instance of PdfUtilityClass
		PdfUtility pdfUtility = new PdfUtility();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers.aers_reactions where isr_id is null or isr_id = ''";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue = rst.getString(1);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualList.add(dbvalueInt);
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.stmt.close();
			dbc.con.close();
			resultList.add("reactionCaseValidFrom,Pass" + "Actual :"
					+ actualList + "Actual :" + expectedList);
		} catch (Throwable e) {
			resultList.add("reactionCaseValidFrom,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("TestResult.pdf", resultList);
			// PDFCreator.doPDFCreate("TestResult.pdf", resultList);
			// exl.writeTestOutputExcelData("reactionCaseValidFrom","Fail",writeFilePath);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
		resultList.clear();
	}
}
