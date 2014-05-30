package com.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.DatabaseConnect;

public class CaseCountInQtr {
	List actualList;
	List expectedList;
	@Test(priority = 1)
	public void AERSCaseCounts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers.aers_stats group by fda_rel_qtr";
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
	//		Assert.assertEquals(actualList, expectedList);
		} catch (Throwable e) {
			e.printStackTrace();
		//	Assert.fail(query, e);
		} /*finally {
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}*/
	}

	@Test(dependsOnMethods = "AERSCaseCounts")
	public void AERSCaseCountDemo() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers.aers_demo group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			actualList = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualList.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualList.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			rst.close();
			Assert.assertEquals(actualList, expectedList);			
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail(query, e);
		} finally {
			Reporter.log(query);
			Reporter.log("Expected : " + expectedList);
			Reporter.log("Actual : " + actualList);
		}
	}

}
