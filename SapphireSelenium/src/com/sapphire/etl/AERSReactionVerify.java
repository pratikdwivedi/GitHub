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

/**
 * Record count (distinct reaction_vbm) where pt_name is null (For now it cannot
 * the count cannot exceed three digits. Ideally pt_name should be not null for
 * which reaction manual cleaning has to be done)
 * 
 */
public class AERSReactionVerify {
//	PdfUtility pdfUtility = new PdfUtility();

	 TestPDFUsingItext pdfUtility=new TestPDFUsingItext();
	@Test(enabled = true)
	public void reactionVBM() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "select count(distinct reaction_vbm) from aers.aers_reactions where pt_name is null";
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
			pdfUtility.resultList.add("reactionVBM,Pass" + "Actual :"
					+ actualList + "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("reactionVBM,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSReactionVerify.pdf",
					pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	/*
	 * SELECT count(distinct isr_id) FROM aers.aers_reactions where pt_name is
	 * null and pt_code is not null
	 */

	@Test(enabled = true)
	public void reactionPtCodeNotNull() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(distinct isr_id) FROM aers.aers_reactions where pt_name is null and pt_code is not null";
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
			pdfUtility.resultList.add("reactionPtCodeNotNull,Pass" + "Actual :"
					+ actualList + "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("reactionPtCodeNotNull,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSReactionVerify.pdf",
					pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test(enabled = true)
	public void reactionPtNameNotNull() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(distinct isr_id) FROM aers.aers_reactions where pt_code is null and pt_name is not null";
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
			pdfUtility.resultList.add("reactionPtNameNotNull,Pass" + "Actual :"
					+ actualList + "Expected :" + expectedList);
		} catch (Throwable e) {
			pdfUtility.resultList.add("reactionPtNameNotNull,Fail" + "Actual :"
					+ actualList + "Expected :" + expectedList);
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			// write the test result pdf file with file name TestResult
			pdfUtility.WriteTestResultToPdfFile("AERSReactionVerify.pdf",
					pdfUtility.resultList);
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}
}
