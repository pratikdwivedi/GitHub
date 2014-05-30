package com.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.DatabaseConnect;

public class AERSReactionVerify {

	@Test(enabled = true)
	public void reactionCaseValidFrom() throws Exception {
		String query = null;
		List<Integer> actualList = null;
		List<Integer> expectedList = null;
		int dbvalueInt = 0;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
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
