package com.ms.sapphire.score;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;
import com.ms.sapphire.utility.ExcelLibrary;
import com.ms.sapphire.utility.IModuleSelection;
import com.ms.sapphire.utility.ModuleExcelSelection;

public class CalculateScoreFormExcel {
	String drug_name;
	String event_name;
	DatabaseConnect dbc = new DatabaseConnect();
	String filePath = ModuleExcelSelection
			.getExcelFilePath(IModuleSelection.BOX_VALUES);
	
	@Test
	public void scoreTestExcel() {
		int dbvalueA = 0;
		int dbvalueAC = 0;
		int dbvalueAB = 0;
		int dbvalueABCD = 0;
		double excel_ExpA = 0 ;
		double excel_PRR_LB=0;
		double excel_PRR=0;
		double excel_PRR_UB=0;
		double dbStatvalueExpA = 0;
		double dbStatvaluePRRLB = 0;
		double dbStatvaluePRR = 0;
		double dbStatvaluePRRUB = 0;

		try {
			ExcelLibrary lib1 = new ExcelLibrary();

			for (int i = 1; i <= lib1.rowCount; i++) {
				drug_name = lib1.getExcelData("DBBoxValueDrugEvent", i, 0,
						"SapphireFiles\\ScoreTestData.xls");
				event_name = lib1.getExcelData("DBBoxValueDrugEvent", i, 1,
						"SapphireFiles\\ScoreTestData.xls");
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MMM/dd HH:mm:ss a");
				Calendar cal = Calendar.getInstance();
				System.out.println("Run Date : "
						+ dateFormat.format(cal.getTime()));
				System.out.println("Criteria : " + drug_name + " & "
						+ event_name);
				/*
				 * Fetch values from fact_demo_drug_reacs
				 */
				dbvalueA = scoreValueA(dbvalueA);
				dbvalueAC = scoreValueAC(dbvalueAC);
				dbvalueAB = scoreValueAB(dbvalueAB);
				dbvalueABCD = scoreValueABCD(dbvalueABCD);
				ExcelLibrary wd = new ExcelLibrary();
				/*
				 * Write data to excel to compute box values
				 */
				System.out.println("Writing to Excel...");
				wd.writeExcelEvaluation(dbvalueA, 10, 3, filePath);// A
				wd.writeExcelEvaluation(dbvalueAC, 12, 3, filePath);// A+C
				wd.writeExcelEvaluation(dbvalueAB, 10, 5, filePath);// A+B
				wd.writeExcelEvaluation(dbvalueABCD, 12, 5, filePath);// A+B+C+D
				/*
				 * Read computed box values from excel
				 */
				System.out.println("Reading from excel...");
				wd.readExcelEvaluation("Sheet1", "E11", filePath);// B
				wd.readExcelEvaluation("Sheet1", "E12", filePath);// D
				wd.readExcelEvaluation("Sheet1", "E13", filePath);// B+D
				wd.readExcelEvaluation("Sheet1", "F12", filePath);// C+D
				System.out.println("Calculating from excel...");
				// wd.evaluateAllExl("Sheet1", "E33",
				// filePath);//
				/*
				 * Read score values form excel
				 */
				excel_ExpA = wd.readExcelEvaluation("Sheet1", "E27", filePath);
				excel_PRR_LB = wd.readExcelEvaluation("Sheet1", "E35",
						filePath);
				excel_PRR = wd.readExcelEvaluation("Sheet1", "E33", filePath);
				excel_PRR_UB = wd.readExcelEvaluation("Sheet1", "E36",
						filePath);
				System.out.println("Exp A :" + excel_ExpA);
				System.out.println("PRR (-) : " + excel_PRR_LB);
				System.out.println("PRR : " + excel_PRR);
				System.out.println("PRR (+) : " + excel_PRR_UB);

				
				/*
				 * Compare calculated box values form excel to stat tables
				 */
				dbStatvalueExpA = scoreStatValueExpA(dbStatvalueExpA);
				dbStatvaluePRRLB = scoreStatValuePRRLB(dbStatvaluePRRLB);
				dbStatvaluePRR = scoreStatValuePRR(dbStatvaluePRR);
				dbStatvaluePRRUB = scoreStatValuePRRUB(dbStatvaluePRRUB);
		/*		Assert.assertEquals(actual, expected); */
				Assert.assertEquals(dbStatvalueExpA,excel_ExpA);
				Assert.assertEquals(dbStatvaluePRRLB,excel_PRR_LB);
				Assert.assertEquals(dbStatvaluePRR,excel_PRR);
				Assert.assertEquals(dbStatvaluePRRUB,excel_PRR_UB);

			}
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail("Error in scores ");
		} finally {
			try {
				Reporter.log("This test case is to verify score values form excel");
				Reporter.log("Expected ExpA: "+excel_ExpA  );
				Reporter.log("Actual ExpA: "+dbStatvalueExpA  );
				Reporter.log("Expected PRR_LB: "+excel_PRR_LB  );
				Reporter.log("Actual PRR_LB: "+dbStatvaluePRRLB  );
				Reporter.log("Expected PRR: "+excel_PRR  );
				Reporter.log("Actual PRR: "+dbStatvaluePRR  );
				Reporter.log("Expected PRR_UB: "+excel_PRR_UB  );
				Reporter.log("Actual PRR_UB: "+dbStatvaluePRRUB  );
					System.out.println("Connection is closed : "
						+ dbc.con.isClosed());
			} catch (Throwable e) {

				e.printStackTrace();
			}
		}
	}

	private double scoreStatValuePRRUB(double dbStatvaluePRRUB)
			throws SQLException {
		ResultSet rstActualPRRUB = null;

		dbc.dbConnect();
		String queryExpected_PRRUB = "SELECT prr_ub FROM stat_de_tn_pt where drug_name='"
				+ drug_name
				+ "' and event_name='"
				+ event_name
				+ "' and fda_period=(SELECT * FROM aers.stat_latest_quarter);";
		String[] resultsActualPRRUB = queryExpected_PRRUB.split(";");
		for (int i = 0; i < resultsActualPRRUB.length; i++) {
			rstActualPRRUB = dbc.stmt.executeQuery(resultsActualPRRUB[i]);
			rstActualPRRUB.getMetaData();
			while (rstActualPRRUB.next()) {
				System.out.println("Stat_prr_ub : "
						+ rstActualPRRUB.getString(1));
				dbStatvaluePRRUB = Double.parseDouble(rstActualPRRUB
						.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();

		return dbStatvaluePRRUB;
	}

	private double scoreStatValuePRR(double dbStatvaluePRR) throws SQLException {
		ResultSet rstActualPRR = null;

		dbc.dbConnect();
		String queryExpected_PRR = "SELECT prr FROM stat_de_tn_pt where drug_name='"
				+ drug_name
				+ "' and event_name='"
				+ event_name
				+ "' and fda_period=(SELECT * FROM stat_latest_quarter);";
		String[] resultsActualPRR = queryExpected_PRR.split(";");
		for (int i = 0; i < resultsActualPRR.length; i++) {
			rstActualPRR = dbc.stmt.executeQuery(resultsActualPRR[i]);
			rstActualPRR.getMetaData();
			while (rstActualPRR.next()) {
				System.out.println("Stat_prr : " + rstActualPRR.getString(1));
				dbStatvaluePRR = Double.parseDouble(rstActualPRR.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();

		return dbStatvaluePRR;
	}

	private double scoreStatValuePRRLB(double dbStatvaluePRRLB)
			throws SQLException {
		ResultSet rstActualPRRLB = null;

		dbc.dbConnect();
		String queryExpected_PRRLB = "SELECT prr_lb FROM stat_de_tn_pt where drug_name='"
				+ drug_name
				+ "' and event_name='"
				+ event_name
				+ "' and fda_period=(SELECT * FROM stat_latest_quarter);";
		String[] resultsActualPRRLB = queryExpected_PRRLB.split(";");
		for (int i = 0; i < resultsActualPRRLB.length; i++) {
			rstActualPRRLB = dbc.stmt.executeQuery(resultsActualPRRLB[i]);
			rstActualPRRLB.getMetaData();
			while (rstActualPRRLB.next()) {
				System.out.println("Stat_prr_lb : "
						+ rstActualPRRLB.getString(1));
				dbStatvaluePRRLB = Double.parseDouble(rstActualPRRLB
						.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();
		return dbStatvaluePRRLB;
	}

	private double scoreStatValueExpA(double dbStatvalueExpA)
			throws SQLException {
		ResultSet rstActualExpA = null;

		dbc.dbConnect();
		String queryExpected_ExpA = "SELECT exp_A FROM stat_de_tn_pt where drug_name='"
				+ drug_name
				+ "' and event_name='"
				+ event_name
				+ "' and fda_period=(SELECT * FROM stat_latest_quarter);";
		String[] resultsActualExpA = queryExpected_ExpA.split(";");
		for (int i = 0; i < resultsActualExpA.length; i++) {
			rstActualExpA = dbc.stmt.executeQuery(resultsActualExpA[i]);
			rstActualExpA.getMetaData();
			while (rstActualExpA.next()) {
				System.out.println("Stat_ExpA : " + rstActualExpA.getString(1));
				dbStatvalueExpA = Double
						.parseDouble(rstActualExpA.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();
		return dbStatvalueExpA;
	}

	private int scoreValueA(int dbvalueA) throws SQLException {
		ResultSet rstActualA = null;

		dbc.dbConnect();
		String queryExpected_A = "SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where trade_name='"
				+ drug_name + "' and pt_name='" + event_name + "';";
		String[] resultsActualA = queryExpected_A.split(";");
		for (int i = 0; i < resultsActualA.length; i++) {
			rstActualA = dbc.stmt.executeQuery(resultsActualA[i]);
			rstActualA.getMetaData();
			while (rstActualA.next()) {
				// System.out.println("A : " + rstActualA.getString(1));
				dbvalueA = Integer.parseInt(rstActualA.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();
		return dbvalueA;
	}

	private int scoreValueAC(int dbvalueAC) throws SQLException {

		ResultSet rstActualAC = null;
		dbc.dbConnect();
		String queryExpected_AC = "SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where pt_name='"
				+ event_name + "';";
		String[] resultsActualAC = queryExpected_AC.split(";");
		for (int i = 0; i < resultsActualAC.length; i++) {
			rstActualAC = dbc.stmt.executeQuery(resultsActualAC[i]);
			rstActualAC.getMetaData();
			while (rstActualAC.next()) {
				// System.out.println("A+C : " + rstActualAC.getString(1));
				dbvalueAC = Integer.parseInt(rstActualAC.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();
		return dbvalueAC;
	}

	private int scoreValueAB(int dbvalueAB) throws SQLException {
		ResultSet rstActualAB = null;
		dbc.dbConnect();
		String queryExpected_AB = "SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where trade_name='"
				+ drug_name + "';";
		String[] resultsActualAB = queryExpected_AB.split(";");
		for (int i = 0; i < resultsActualAB.length; i++) {
			rstActualAB = dbc.stmt.executeQuery(resultsActualAB[i]);
			rstActualAB.getMetaData();
			while (rstActualAB.next()) {
				// System.out.println("A+B : " + rstActualAB.getString(1));
				dbvalueAB = Integer.parseInt(rstActualAB.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();
		return dbvalueAB;
	}

	private int scoreValueABCD(int dbvalueABCD) throws SQLException {
		ResultSet rstActualABCD = null;
		dbc.dbConnect();
		String queryExpected_ABCD = "SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs;";
		String[] resultsActualABCD = queryExpected_ABCD.split(";");
		for (int i = 0; i < resultsActualABCD.length; i++) {
			rstActualABCD = dbc.stmt.executeQuery(resultsActualABCD[i]);
			while (rstActualABCD.next()) {
				// System.out.println("A+B+C+D : " +
				// rstActualABCD.getString(1));
				dbvalueABCD = Integer.parseInt(rstActualABCD.getString(1));
			}
		}

		dbc.stmt.close();
		dbc.con.close();
		return dbvalueABCD;
	}
}
