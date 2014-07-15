package com.ms.sapphire.DB_Function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ms.sapphire.utility.DatabaseConnect;

public class AersGenderStrata {
	int dbvalueInt;
	String dbvalue;
	public Statement stmt;
	public Connection con;

	@Test(enabled = true)
	public void aers_gender_strata() throws Exception {
		try {
			DatabaseConnect dbc = new DatabaseConnect();
			dbc.dbConnect();
			String procStoredProcedure = "{? = call get_gender_strata_code(?)}";
			CallableStatement cs = dbc.con.prepareCall(procStoredProcedure);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, "Male");
			cs.execute();
			String param = cs.getString(1);
			System.out.println("Gender Strata=" + param);
			dbc.con.close();
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail("FAIL", e);
		}
	}
}
