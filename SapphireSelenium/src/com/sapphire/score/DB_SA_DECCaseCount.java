package com.sapphire.score;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DB_SA_DECCaseCount {

	int j;
	ResultSet rstExpected;
	ResultSet rstActual;
	ResultSet rst;
	Connection con; 
	int columnsNumber ;
	ResultSetMetaData rsmd;
	@Test
	public void DB_SA_DECCaseCountCheck() throws Exception{
		try{
			ScoreDBConnection dbc =new ScoreDBConnection();
			Class.forName(dbc.dbClass);
			con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
			Statement stmt = con.createStatement();
			String queryExpected = "SELECT (A) FROM aers.stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=20124;"
					+"SELECT (B) FROM aers.stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=20124;"
					+"SELECT (C) FROM aers.stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=20124;"
			//		+"SELECT (D) FROM aers.stat_de_tn_pt where drug_name='Raptiva' and event_name='Pyrexia' and fda_period=20124;"
					;
			List expResult=new ArrayList(); 
			String[] resultsExpected=queryExpected.split(";");   
			for(int i=0;i<resultsExpected.length;i++)   
			{
				rstExpected = stmt.executeQuery(resultsExpected[i]);
				ResultSetMetaData rsmdExpected = rstExpected.getMetaData();
				while(rstExpected.next())
				{
					System.out.println(rstExpected.getString(1)); 
					expResult.add(rstExpected.getString(1));
				}
			}
			System.out.println("***************");
			String queryActual = "SELECT count(distinct isr_id) FROM aers.fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia';"
					+"SELECT count(distinct isr_id)-(SELECT count(distinct isr_id) FROM aers.fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia' ) FROM aers.fact_demo_drugs_reacs where trade_name='RAPTIVA' ;"
					+"SELECT count(distinct isr_id)-(SELECT count(distinct isr_id) FROM aers.fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia' ) FROM aers.fact_demo_drugs_reacs where pt_name='Pyrexia' and trade_name is not null;"
			//		+"select (count(distinct isr_id) - (SELECT count(distinct isr_id)-(SELECT count(distinct isr_id) FROM aers.fact_demo_drugs_reacs where trade_name='RAPTIVA' and pt_name='Pyrexia' ) FROM aers.fact_demo_drugs_reacs where trade_name='RAPTIVA' and trade_name is not null and pt_name is not null)) as count from aers.fact_demo_drugs_reacs;"
					;
			
			List actResult=new ArrayList(); 
			String[] resultsActual=queryActual.split(";");   
			for(int i=0;i<resultsActual.length;i++)   
			{
				rstActual = stmt.executeQuery(resultsActual[i]);
				ResultSetMetaData rsmdActual = rstActual.getMetaData();
			 	while(rstActual.next())
			 	{
		 		System.out.println(rstActual.getString(1)); 
		 		actResult.add(rstActual.getString(1));
			 	}
			}
			
			// System.out.println(rstExpected.getString(1).equals(rstActual.getString(1)));
			System.out.println(expResult.containsAll(actResult));
			Assert.assertEquals(actResult, expResult);
			Thread.sleep(5000);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
}
