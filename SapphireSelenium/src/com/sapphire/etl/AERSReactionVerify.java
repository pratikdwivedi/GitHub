package com.sapphire.etl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.test.DatabaseConnect;

/**
 * Record count (distinct reaction_vbm) where pt_name is null 
 * (For now it cannot the count cannot exceed three digits. 
 * Ideally pt_name should be not null for which reaction manual cleaning has to be done)
 *
 */
public class AERSReactionVerify {

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
