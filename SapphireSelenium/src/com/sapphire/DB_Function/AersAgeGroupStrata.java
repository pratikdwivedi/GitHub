package com.sapphire.DB_Function;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Types;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.test.DatabaseConnect;

public class AersAgeGroupStrata {
	int dbvalueInt;
	String dbvalue;
	public Statement stmt;
	public Connection con;

	@Test(enabled = true)
	public void testAersGenderStrata() throws Exception {
		try {
			DatabaseConnect dbc = new DatabaseConnect();
			dbc.dbConnect();
			String procStoredProcedure = "{? = call get_age_strata_code(?)}";
			CallableStatement cs = dbc.con.prepareCall(procStoredProcedure);
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(2, "Adult");
			cs.execute();
			String param = cs.getString(1);
			System.out.println("param=" + param);
			dbc.con.close();
		} catch (Throwable e) {
			e.printStackTrace();
			Assert.fail("FAIL", e);
		}
	}

}
