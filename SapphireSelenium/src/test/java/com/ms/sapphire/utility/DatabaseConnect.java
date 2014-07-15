package com.ms.sapphire.utility;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseConnect {
	public Statement stmt;
	public Connection con;
	String userNameP;
	String passwordP;
	String databaseClass;
	String databaseURl;

	public void dbConnect() {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/test/java/com/ms/sapphire/utility/sapphire_en.properties"));
			userNameP = prop.getProperty("dbuser");
			passwordP = prop.getProperty("dbpassword");
			databaseClass = prop.getProperty("dbClass");
			databaseURl = prop.getProperty("dbUrl");
			Class.forName(databaseClass);
			con = DriverManager
					.getConnection(databaseURl, userNameP, passwordP);
			stmt = con.createStatement();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
