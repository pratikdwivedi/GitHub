package com.ms.sapphire.etl;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;

public class AERS_RECORD_COUNTS {
	static List actualListDemoCaseCount;
	static List expectedListDemoCaseCount;
	static List actualListDrugCaseCount;
	static List expectedListDrugCaseCount;
	static List actualListReactionCaseCount;
	static List expectedListReactionCaseCount;
	static List actualListOutcomeCaseCount;
	static List expectedListOutcomeCaseCount;
	static List actualListSourceCaseCount;
	static List expectedListSourceCaseCount;
	static List actualListTherapyCaseCount;
	static List expectedListTherapyCaseCount;
	static List actualListIndicationCaseCount;
	static List expectedListIndicationCaseCount;

	/*
	 * This test case is to verify if all records are loaded from demographic
	 * ascii file to aers_demo table
	 */
	@Test
	public void aers_demo_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 1 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListDemoCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListDemoCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListDemoCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS ccq = new AERS_RECORD_COUNTS();
			ccq.aers_case_count_demo();
			Assert.assertEquals(actualListDemoCaseCount,
					expectedListDemoCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from demographic ascii file to aers_demo table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListDemoCaseCount);
			Reporter.log("Actual : " + actualListDemoCaseCount);
			actualListDemoCaseCount.clear();
			expectedListDemoCaseCount.clear();
		}
	}

	public void aers_case_count_demo() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListDemoCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_demo group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListDemoCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListDemoCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test case is to verify if all records are loaded from drug ascii
	 * file to aers_drugs table
	 */
	@Test(enabled = false)
	public void aers_drug_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 2 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListDrugCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListDrugCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListDrugCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS arc = new AERS_RECORD_COUNTS();
			arc.aers_case_count_drugs();
			Assert.assertEquals(actualListDrugCaseCount,
					expectedListDrugCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from drug ascii file to aers_drugs table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListDrugCaseCount);
			Reporter.log("Actual : " + actualListDrugCaseCount);
			actualListDrugCaseCount.clear();
			expectedListDrugCaseCount.clear();
		}
	}

	public void aers_case_count_drugs() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListDrugCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_drugs group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListDrugCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListDrugCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test case is to verify if all records are loaded from reaction ascii
	 * file to aers_reaction table
	 */
	@Test(enabled = false)
	public void aers_reaction_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 3 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListReactionCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListReactionCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListReactionCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS arc = new AERS_RECORD_COUNTS();
			arc.aers_case_count_reaction();
			Assert.assertEquals(actualListReactionCaseCount,
					expectedListReactionCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from reaction ascii file to aers_reaction table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListReactionCaseCount);
			Reporter.log("Actual : " + actualListReactionCaseCount);
			actualListReactionCaseCount.clear();
			expectedListReactionCaseCount.clear();
		}
	}

	public void aers_case_count_reaction() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListReactionCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_reactions group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListReactionCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListReactionCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test case is to verify if all records are loaded from outcome ascii
	 * file to aers_outcome table
	 */
	@Test
	public void aers_outcome_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 4 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListOutcomeCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListOutcomeCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListOutcomeCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS arc = new AERS_RECORD_COUNTS();
			arc.aers_case_count_outcome();
			Assert.assertEquals(actualListOutcomeCaseCount,
					expectedListOutcomeCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from outcome ascii file to aers_outcome table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListOutcomeCaseCount);
			Reporter.log("Actual : " + actualListOutcomeCaseCount);
			actualListOutcomeCaseCount.clear();
			expectedListOutcomeCaseCount.clear();
		}
	}

	public void aers_case_count_outcome() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListOutcomeCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_outcome group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListOutcomeCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListOutcomeCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test case is to verify if all records are loaded from source ascii
	 * file to aers_report_source table
	 */
	@Test
	public void aers_source_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 5 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListSourceCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListSourceCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListSourceCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS arc = new AERS_RECORD_COUNTS();
			arc.aers_case_count_source();
			Assert.assertEquals(actualListSourceCaseCount,
					expectedListSourceCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from source ascii file to aers_report_source table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListSourceCaseCount);
			Reporter.log("Actual : " + actualListSourceCaseCount);
			actualListSourceCaseCount.clear();
			expectedListSourceCaseCount.clear();
		}
	}

	public void aers_case_count_source() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListSourceCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_report_source group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListSourceCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListSourceCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test case is to verify if all records are loaded from therapy ascii
	 * file to aers_drug_therapy table
	 */
	@Test
	public void aers_therapy_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 6 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListTherapyCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListTherapyCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListTherapyCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS arc = new AERS_RECORD_COUNTS();
			arc.aers_case_count_therapy();
			Assert.assertEquals(actualListTherapyCaseCount,
					expectedListTherapyCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from therapy ascii file to aers_drug_therapy table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListTherapyCaseCount);
			Reporter.log("Actual : " + actualListTherapyCaseCount);
			actualListTherapyCaseCount.clear();
			expectedListTherapyCaseCount.clear();
		}
	}

	public void aers_case_count_therapy() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListTherapyCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_drug_therapy group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListTherapyCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListTherapyCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * This test case is to verify if all records are loaded from indication
	 * ascii file to aers_drug_indication table
	 */
	@Test
	public void aers_Indication_counts() throws Exception {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count FROM aers_stats where table_id = 7 group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);
			expectedListIndicationCaseCount = new ArrayList<Integer>();
			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				expectedListIndicationCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				expectedListIndicationCaseCount.add(dbvalueInt);
			}
			dbc.stmt.close();
			dbc.con.close();
			AERS_RECORD_COUNTS arc = new AERS_RECORD_COUNTS();
			arc.aers_case_count_indication();
			Assert.assertEquals(actualListIndicationCaseCount,
					expectedListIndicationCaseCount);
		} catch (Throwable e) {
			Assert.fail("Record counts do not match");
			e.printStackTrace();
		} finally {
			Reporter.log("This test case is to verify if all records are loaded from indication ascii file to aers_drug_indication table");
			Reporter.log(query);
			Reporter.log("Expected : " + expectedListIndicationCaseCount);
			Reporter.log("Actual : " + actualListIndicationCaseCount);
			actualListIndicationCaseCount.clear();
			expectedListIndicationCaseCount.clear();
		}
	}

	public void aers_case_count_indication() {
		String query = null;
		int dbvalueInt = 0;
		String dbvalue2 = null;
		String dbvalue = null;
		DatabaseConnect dbc = new DatabaseConnect();
		actualListIndicationCaseCount = new ArrayList<Integer>();
		try {
			dbc.dbConnect();
			query = "SELECT fda_rel_qtr,count(*) FROM aers_drug_indication group by fda_rel_qtr";
			ResultSet rst = dbc.stmt.executeQuery(query);

			while (rst.next()) {
				dbvalue2 = rst.getString(1);
				actualListIndicationCaseCount.add(dbvalue2);
				dbvalue = rst.getString(2);
				dbvalueInt = Integer.parseInt(dbvalue);
				actualListIndicationCaseCount.add(dbvalueInt);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
