package com.sapphire.etl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.sapphire.score.ScoreDBConnection;
import com.test.Assertions;
import com.test.Config;
import com.test.ExcelLibrary;

public class FetchDatabase {
	String dbvalue;
	 int dbvalueInt;
	@Test(description=" First test")
	 public void dataForETL() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{	
			
				ScoreDBConnection dbc =new ScoreDBConnection();
				Class.forName(dbc.dbClass);
				 Connection con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				//String query = "select name from db1";
				 String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where trade_name='raptiva'";
				 System.out.println(query);
				 ResultSet rst = stmt.executeQuery(query); 
				//In a loop
				 while (rst.next()) {
					 dbvalue =rst.getString(1);
					 dbvalueInt = Integer.parseInt(dbvalue);
				  System.out.println(dbvalueInt);
				 }
				 
					 
				 con.close();
			
				
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			 
			 }
	}	
	
	@Test
	 public void dataForETL1() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{	
			
				ScoreDBConnection dbc =new ScoreDBConnection();
				Class.forName(dbc.dbClass);
				 Connection con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				//String query = "select name from db1";
				 String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where trade_name='raptiva'";
				 System.out.println(query);
				 ResultSet rst = stmt.executeQuery(query); 
				//In a loop
				 while (rst.next()) {
					 dbvalue =rst.getString(1);
					 dbvalueInt = Integer.parseInt(dbvalue);
				  System.out.println(dbvalueInt);
				 }
				 
					 
				 con.close();
			
				
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			 
			 }
	}
	@Test
	 public void dataForETL2() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{	
			
				ScoreDBConnection dbc =new ScoreDBConnection();
				Class.forName(dbc.dbClass);
				 Connection con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				//String query = "select name from db1";
				 String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where trade_name='raptiva'";
				 System.out.println(query);
				 ResultSet rst = stmt.executeQuery(query); 
				//In a loop
				 while (rst.next()) {
					 dbvalue =rst.getString(1);
					 dbvalueInt = Integer.parseInt(dbvalue);
				  System.out.println(dbvalueInt);
				 }
				 
					 
				 con.close();
			
				
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			 
			 }
	}
	@Test
	 public void dataForETL3() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{	
			
				ScoreDBConnection dbc =new ScoreDBConnection();
				Class.forName(dbc.dbClass);
				 Connection con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				//String query = "select name from db1";
				 String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where trade_name='raptiva'";
				 System.out.println(query);
				 ResultSet rst = stmt.executeQuery(query); 
				//In a loop
				 while (rst.next()) {
					 dbvalue =rst.getString(1);
					 dbvalueInt = Integer.parseInt(dbvalue);
				  System.out.println(dbvalueInt);
				 }
				 
					 
				 con.close();
			
				
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			 
			 }
	}
	@Test
	 public void dataForETL4() throws Exception{
		try{
			ExcelLibrary lib1=new ExcelLibrary();
			for(int i=1;i<=lib1.rowCount;i++)
			{	
			
				ScoreDBConnection dbc =new ScoreDBConnection();
				Class.forName(dbc.dbClass);
				 Connection con = DriverManager.getConnection (dbc.dbUrl,dbc.username,dbc.password);
				 Statement stmt = con.createStatement();
				//String query = "select name from db1";
				 String query = "select count(distinct isr_id) from aers.fact_demo_drugs_reacs where trade_name='raptiva'";
				 System.out.println(query);
				 ResultSet rst = stmt.executeQuery(query); 
				//In a loop
				 while (rst.next()) {
					 dbvalue =rst.getString(1);
					 dbvalueInt = Integer.parseInt(dbvalue);
				  System.out.println(dbvalueInt);
				 }
				 
					 
				 con.close();
			
				
			}//for
		}
		catch(Throwable e) {
			  e.printStackTrace();
			 
			 }
	}
}
