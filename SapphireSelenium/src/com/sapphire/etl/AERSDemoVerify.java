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

public class AERSDemoVerify {
	// create a resultList that will contain number of test cases
	// create an instance of PdfUtilityClass
//	PdfUtility pdfUtility = new PdfUtility();

	 TestPDFUsingItext pdfUtility=new TestPDFUsingItext();
	@Test(enabled = true)
	public void demoCaseValidFrom() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where valid_from is null ";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			expectedList.add(0);
			while (rst.next()) {
				dbvalue = rst.getString(1);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualList.add(dbvalueInt);
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.stmt.close();
			dbc.con.close();
			pdfUtility.resultList.add("demoCaseValidFrom,Pass" + "Actual :"
					+ actualList + "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseValidFrom,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCaseValidTo() throws Exception {
		String query = null;
		String dbvalue;
		int dbvalueInt = 0;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		DatabaseConnect dbc = new DatabaseConnect();

		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where valid_to is null";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			expectedList.add(0);
			while (rst.next()) {
				dbvalue = rst.getString(1);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualList.add(dbvalueInt);
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCaseValidTo,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseValidTo,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);

		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCASE_ID() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		String dbvalue;
		int dbvalueInt = 0;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where case_id is null";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			expectedList.add(0);
			while (rst.next()) {
				dbvalue = rst.getString(1);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualList.add(dbvalueInt);
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCASE_ID,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCASE_ID,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoFDA_date() throws Exception {
		String query = null;
		String dbvalue = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where fda_date is null";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			expectedList.add(0);
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(Integer.parseInt(dbvalue));
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoFDA_date,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoFDA_date,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf",pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCaseGenderCode() throws Exception {
		String query = null;
		List<String> expectedList = null;
		List<String> actualList = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT distinct gender_code FROM aers_demo";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<String>();
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(dbvalue);
			}
			expectedList = new ArrayList<String>();
			expectedList.add("F");
			expectedList.add("M");
			expectedList.add("UNK");
			expectedList.add("NS");
			expectedList.add("U");
			expectedList.add("''");
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCaseGenderCode,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseGenderCode,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCaseGender() throws Exception {
		String query = null;
		List<String> expectedList = null;
		List<String> actualList = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT distinct gender_dec FROM aers_demo";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<String>();
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(dbvalue);
			}
			expectedList = new ArrayList<String>();
			expectedList.add("FEMALE");
			expectedList.add("MALE");
			expectedList.add("Not Provided");
			expectedList.add("NOT SPECIFIED");
			expectedList.add("UNKNOWN");
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCaseGenderDEC,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseGenderDEC,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCaseSeriousness() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		String dbvalue = null;

		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where seriousness = 1 and ( outc_death= 0 and  outc_life_threat =0 and outc_hosp =0 and outc_disability=0 and outc_cong_anomaly=0  and outc_reqd_interv=0 and outc_other=0 and outc_none=0 and outc_rec=0 and outc_rx=0)";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			expectedList.add(0);
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(Integer.parseInt(dbvalue));
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCaseSeriousness,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseSeriousness,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCaseAgeCode() throws Exception {
		String query = null;
		List<String> actualList = null;
		List<String> expectedList = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT distinct age_code FROM aers_demo";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<String>();
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(dbvalue);
			}
			expectedList = new ArrayList<String>();
			expectedList.add("YR");
			expectedList.add("MON");
			expectedList.add("WK");
			expectedList.add("DY");
			expectedList.add("HR");
			expectedList.add("DEC");
			expectedList.add("MIN");
			expectedList.add("SEC");
			expectedList.add("''");
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCaseAgeCode,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseAgeCode,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demoCaseAgeGroup() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where std_age_group = 'Not Available' AND age != '' AND age_code != '' AND age not like ('%-%') AND age not like ('%.%') AND age != 0 AND age != 00";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			expectedList = new ArrayList<Integer>();
			expectedList.add(0);
			// In a loop
			while (rst.next()) {
				dbvalue = rst.getString(1);

				actualList.add(Integer.parseInt(dbvalue));
			}
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
			pdfUtility.resultList.add("demoCaseAgeGroup,Pass" + "Actual :" + actualList
					+ "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("demoCaseAgeGroup,Fail" + "Actual :" + actualList
					+ "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSDemoVerify.pdf", pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}
}
