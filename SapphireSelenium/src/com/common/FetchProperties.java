package com.common;

import java.io.FileInputStream;
import java.util.Properties;

public class FetchProperties {
	public String home;
	public String workspace;
	public String inbox;
	public String portfolio;
	public String reports;
	public String tools;
	public String admin;

	public void fetchProp() {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/com/test/sapphire_en.properties"));
			home = prop.getProperty("home");
			workspace = prop.getProperty("workspace");
			inbox = prop.getProperty("inbox");
			portfolio = prop.getProperty("portfolio");
			reports = prop.getProperty("reports");
			tools = prop.getProperty("tools");
			admin = prop.getProperty("admin");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
