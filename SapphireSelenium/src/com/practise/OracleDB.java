package com.practise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

public class OracleDB {
	@Test
	public void oracleDBmethod() throws Exception{
		String dbvalue = null;
		ResultSet rst = null;
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@//127.0.0.1:1521/XE","hr","hr"); //My Db 
		//	Connection con = DriverManager.getConnection ("jdbc:oracle:thin:@//192.168.1.20:1521/XE","system","manager123"); // 
			Statement stmt = con.createStatement();
			
			System.out.println("Connected");
			/*String query = "select * from tab";
			rst =stmt.executeQuery(query);
					while (rst.next()) {
					 dbvalue = rst.getString(1);
						
					}*/
			String query="create table ms_user (record_id integer not null,  user_id varchar(20),  password varchar(100),  description varchar(255) ,  failed_access_count integer ,  activation_date timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  pwd_modification_date timestamp not null default '0000-00-00 00:00:00',  dn   varchar(500),  is_ad integer default 0,  is_admin integer,  is_super_admin integer,  is_account_locked integer ,  is_reset_password integer ,  is_account_expirable integer ,  is_password_expired integer ,  status integer,  person_id integer,  primary key (record_id),  unique key unq_user_id_1(user_id),  key idx_ms_user_1 (person_id)) ";
			rst =stmt.executeQuery(query);
		//	System.out.println(dbvalue);
		}
		catch(Throwable e)
		{
			e.printStackTrace();
			Assert.fail("FAIL");
		}
	}
}
