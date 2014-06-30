package com.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.practise.TestPDFUsingItext;
import com.test.DatabaseConnect;
import com.test.PdfUtility;

public class CaseCountInQtr {
	List actualList;
	List expectedList;
//	PdfUtility pdfUtility = new PdfUtility();

	TestPDFUsingItext pdfUtility=new TestPDFUsingItext();
	@Test(priority = 1)
	public void AERSCaseCounts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedList = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedList.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedList.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			rst.close();
			 Assert.assertEquals(actualList, expectedList);
			pdfUtility.resultList.add("AERSCaseCounts,Pass" + "Actual :"
					+ actualList + "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("AERSCaseCounts,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			 Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("CaseCountInQtr.pdf",
					pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

//	@Test(dependsOnMethods="AERSCaseCounts")
	@Test
	public void AERSCaseCountDemo() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_demo group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualList.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualList.add(dbvalueInt);
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.stmt.close();
			dbc.con.close();
			rst.close();
			pdfUtility.resultList.add("AERSCaseCountDemo,Pass" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			Assert.assertEquals(actualList, expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("AERSCaseCountDemo,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("CaseCountInQtr.pdf",
					pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

}
