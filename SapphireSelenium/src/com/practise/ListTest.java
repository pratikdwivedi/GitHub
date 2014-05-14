package com.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class ListTest {

	public static void main(String arg[]) throws SQLException, ClassNotFoundException
	{
		ListTest lt=new ListTest();
		lt.method1();
	}

	public  void method1() {
		
		ArrayList a=new ArrayList();
		for(int i=1;i<=4;i++)
		{
			a.add(i);
		}
		
		a.add("Pratik");
		System.out.println(a.size());
		System.out.println(a);
		System.out.println("---------------------------------");
		ArrayList b=new ArrayList();
		for(int j=1;j<=4;j++)
		{
			b.add(j);
		}
		b.add("Pratik");
		System.out.println(b.size());
		System.out.println(b);
		System.out.println("---------------------------------");
		System.out.println(a.containsAll(b));
	}
	
	public void method2() throws SQLException, ClassNotFoundException
	{
		ArrayList dbListvalue = null;
		int columnsNumber ;
		ResultSetMetaData rsmd;
		String dbvalue;
		ResultSet rst; 
		Connection con;
		String dbUrl = "jdbc:mysql://localhost:3306/test"; 
		String username="root";
		String password="mysql";
		String dbClass = "com.mysql.jdbc.Driver";
		Class.forName(dbClass);
		  con = DriverManager.getConnection (dbUrl,username,password);
		 Statement stmt = con.createStatement();
		 String query = "SELECT * from db1";
		 System.out.println(query);
		 rst = stmt.executeQuery(query); 
		 rsmd = rst.getMetaData();
		 columnsNumber =rsmd.getColumnCount();
		 while (rst.next()) {
			 dbListvalue=new ArrayList();
			 for(int j = 1 ; j <= columnsNumber; j++){
			       dbListvalue.add(rst.getString(j));
			//       System.out.println("Database :"+rst.getString(j)); 
			      
			}
		//	 dbvalue =rst.getString(1);
		 System.out.println("Database : "+dbListvalue); 
		  }
		 con.close();
		
	}
	
}