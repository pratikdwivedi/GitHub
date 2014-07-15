package com.ms.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;
/**
 * Record count (distinct reaction_vbm) where pt_name is null (For now it cannot
 * the count cannot exceed three digits. Ideally pt_name should be not null for
 * which reaction manual cleaning has to be done)
 * 
 */

public class AERS_REACTIONS {
	@Test(enabled = true)
	public void reaction_VBM() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "select count(distinct reaction_vbm) from aers_reactions where pt_name is null";
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
			Assert.fail("pt_name is null in aers_reactions for "+actualList+ "records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if pt_name is null for cleaned drug in aers_reactions table");
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
	public void reaction_pt_code_notnull() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(distinct isr_id) FROM aers_reactions where pt_name is null and pt_code is not null";
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
			Assert.fail("pt_name is null and pt_code is not null in aers_reactions for "+actualList+ "records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify that pt_name is null and pt_code is not null in aers_reactions table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

	@Test(enabled = true)
	public void reaction_pt_name_notnull() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT count(distinct isr_id) FROM aers_reactions where pt_code is null and pt_name is not null";
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
			Assert.fail("pt_code is null and pt_name is not null in aers_reactions for "+actualList+ "records");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify that pt_code is null and pt_name is not null in aers_reactions table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}
}
