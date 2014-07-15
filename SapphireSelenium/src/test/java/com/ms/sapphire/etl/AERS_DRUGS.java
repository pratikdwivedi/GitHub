package com.ms.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;

public class AERS_DRUGS {
	@Test(enabled = true)
	public void drug_role_code() throws Exception {
		String query = null;
		List<String> actualList = null;
		List<String> expectedList = null;
		String dbvalue;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT distinct role_code FROM aers_drugs";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<String>();
			expectedList = new ArrayList<String>();
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(dbvalue);
			}
			expectedList.add("C");
			expectedList.add("I");
			expectedList.add("O");
			expectedList.add("PS");
			expectedList.add("S");
			expectedList.add("SS");
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
		} catch (Throwable e) {
			Assert.fail("Actual values of role_code does not match with Expected values in aers_drugs table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if role_code is having the expected values in aers_drugs table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test(enabled = true)
	public void drug_role_decode() throws Exception {
		String query = null;
		List<String> actualList = null;
		List<String> expectedList = null;
		String dbvalue;
		DatabaseConnect dbc = new DatabaseConnect();
		try {

			dbc.dbConnect();
			query = "SELECT distinct role_dec FROM aers.aers_drugs";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<String>();
			expectedList = new ArrayList<String>();
			while (rst.next()) {
				dbvalue = rst.getString(1);
				actualList.add(dbvalue);
			}
			expectedList.add("CONCOMITANT");
			expectedList.add("SECONDARY SUSPECT");
			expectedList.add("PRIMARY SUSPECT");
			expectedList.add("INTERACTING");
			Assert.assertEquals(actualList, expectedList);
			dbc.con.close();
			dbc.stmt.close();
		} catch (Throwable e) {
			Assert.fail("Actual values of role_dec does not match with Expected values in aers_drugs table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if role_dec is having the expected values in aers_drugs table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test(enabled = true)
	public void drug_cleaned_gn() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		String dbvalue;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_drugs where drug_cleaned is not null and generic_name is null or generic_name=''";
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
			Assert.fail("generic_name is null for "+actualList+" drugs in aers_drugs table");
			e.printStackTrace();		
		} finally {
			Reporter.log("This test case is to verify if generic_name is null for cleaned drug in aers_drugs table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test(enabled = true)
	public void drug_cleaned_null() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		String dbvalue;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(*) FROM aers_drugs where drug_cleaned is null";
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
			Assert.fail("drug_cleaned is null for "+actualList+" drugs in aers_drugs table");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if drug_cleaned is null in aers_drugs table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

}
