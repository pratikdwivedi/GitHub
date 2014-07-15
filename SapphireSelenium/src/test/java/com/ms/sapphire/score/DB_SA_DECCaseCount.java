package com.ms.sapphire.score;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;

public class DB_SA_DECCaseCount {
	DatabaseConnect dbc = new DatabaseConnect();
	int j;
	ResultSet rstExpected;
	ResultSet rstActual;
	ResultSet rst;
	Connection con;
	int columnsNumber;
	ResultSetMetaData rsmd;
	List actResult;
	List expResult;
	@Test
	public void DB_SA_DECCaseCountCheck() throws Exception {
		try {
			dbc.dbConnect();
			/*
			 * 
			 * 
			 */
			String queryExpected = "SELECT (A) FROM stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=(SELECT * FROM aers.stat_latest_quarter);"
					+ "SELECT (B) FROM stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=(SELECT * FROM aers.stat_latest_quarter);"
					+ "SELECT (C) FROM stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=(SELECT * FROM aers.stat_latest_quarter);"
			// +"SELECT (D) FROM stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=(SELECT * FROM aers.stat_latest_quarter);"
			;
			 expResult = new ArrayList();
			String[] resultsExpected = queryExpected.split(";");
			for (int i = 0; i < resultsExpected.length; i++) {
				rstExpected = dbc.stmt.executeQuery(resultsExpected[i]);
				ResultSetMetaData rsmdExpected = rstExpected.getMetaData();
				while (rstExpected.next()) {
					System.out.println(rstExpected.getString(1));
					expResult.add(rstExpected.getString(1));
				}
			}
			System.out.println("***************");
			String queryActual = "SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia';"
					+ "SELECT count(distinct isr_id)-(SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia' ) FROM fact_demo_drugs_reacs where trade_name='RAPTIVA' ;"
					+ "SELECT count(distinct isr_id)-(SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia' ) FROM fact_demo_drugs_reacs where pt_name='Pyrexia' and trade_name is not null;"
			// +"select (count(distinct isr_id) - (SELECT count(distinct isr_id)-(SELECT count(distinct isr_id) FROM fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia' ) FROM fact_demo_drugs_reacs where trade_name='RAPTIVA' and trade_name is not null and pt_name is not null)) as count from fact_demo_drugs_reacs;"
			;

			actResult = new ArrayList();
			String[] resultsActual = queryActual.split(";");
			for (int i = 0; i < resultsActual.length; i++) {
				rstActual = dbc.stmt.executeQuery(resultsActual[i]);
				ResultSetMetaData rsmdActual = rstActual.getMetaData();
				while (rstActual.next()) {
					System.out.println(rstActual.getString(1));
					actResult.add(rstActual.getString(1));
				}
			}

			// System.out.println(rstExpected.getString(1).equals(rstActual.getString(1)));
			System.out.println(expResult.containsAll(actResult));
			Assert.assertEquals(actResult, expResult);
			Thread.sleep(5000);
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail("Error in SA DEC Case Count  ");
		} finally {
			Reporter.log("Expected [values from stat table] :"+expResult);
			Reporter.log("Actual [values from fact table] :"+actResult);
			dbc.stmt.close();
			dbc.con.close();
		}
	}
}
