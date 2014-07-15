package com.ms.sapphire.etl;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;

import com.ms.sapphire.utility.DatabaseConnect;
public class AERS_ISRID {
	@Test
	public void aers_reaction_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_reactions where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_reactions for "+actualList+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if isr_id is null in aers_reactions table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test
	public void aers_drug_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_drugs where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_drugs for "+actualList+ " records");
			e.printStackTrace();

		} finally {
			Reporter.log("This test case is to verify if isr_id is null in aers_drugs table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test
	public void aers_demo_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_demo for "+actualList+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify isr_id is null in aers_demo table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test
	public void aers_therapy_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_drug_therapy where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_drug_therapy for "+actualList+ " records");
			e.printStackTrace();

		} finally {
			Reporter.log("This test case is to verify isr_id is null in aers_drug_therapy table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test
	public void aers_source_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_report_source where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_report_source for "+actualList+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify isr_id is null in aers_report_source table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test
	public void aers_indication_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_drug_indication where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_drug_indication for "+actualList+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify isr_id is null in aers_drug_indication table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test
	public void aers_outcome_isrid() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_outcome where isr_id is null or isr_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("isr_id is null in aers_outcome for "+actualList+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify isr_id is null in aers_outcome table ");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

}
