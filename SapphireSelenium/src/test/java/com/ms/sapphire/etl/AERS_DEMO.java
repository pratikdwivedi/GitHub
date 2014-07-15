package com.ms.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;

public class AERS_DEMO {
	@Test(enabled = true)
	public void demo_valid_from() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where valid_from is null or valid_from = '' ";
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
			Assert.fail("valid_from is null in aers_demo for " + actualList
					+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if valid_from is null in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_valid_to() throws Exception {
		String query = null;
		String dbvalue;
		int dbvalueInt = 0;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		DatabaseConnect dbc = new DatabaseConnect();

		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where valid_to is null or valid_to = ''";
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
		} catch (Throwable e) {
			Assert.fail("valid_to is null in aers_demo for " + actualList
					+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if valid_to is null in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_case_id() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		String dbvalue;
		int dbvalueInt = 0;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where case_id is null or case_id = ''";
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
		} catch (Throwable e) {
			Assert.fail("case_id is null in aers_demo for " + actualList
					+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if case_id is null in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_fda_date() throws Exception {
		String query = null;
		String dbvalue = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_demo where fda_date is null or fda_date = ''";
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
		} catch (Throwable e) {
			Assert.fail("fda_date is null in aers_demo for " + actualList
					+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if fda_date is null in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_gender_code() throws Exception {
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
		} catch (Throwable e) {
			Assert.fail("Actual values of gender_code does not match with Expected values in aers_demo table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if gender_code is having the expected values in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_gender_decode() throws Exception {
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
		} catch (Throwable e) {
			Assert.fail("Actual values of gender_dec does not match with Expected values in aers_demo table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if gender_dec is having the expected values in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_seriousness() throws Exception {
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
		} catch (Throwable e) {
			Assert.fail("seriousness criteria is incorect for " + actualList
					+ " records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify records where seriousness is 1 and all the outcomes is 0 in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_age_Code() throws Exception {
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
		} catch (Throwable e) {
			Assert.fail("Actual values of age_code does not match with Expected values in aers_demo table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if age_code is having the expected values in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}

	@Test(enabled = true)
	public void demo_age_group() throws Exception {
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
		} catch (Throwable e) {
			Assert.fail("std_age_group is null or incorrectly populated for "
					+ actualList + "  records in aers_demo table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if std_age_group is correctly populated for all the records where age and age unit is available in aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
			expectedList.clear();
			actualList.clear();
		}
	}
}
