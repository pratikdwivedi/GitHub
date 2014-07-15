package com.ms.sapphire.utility;
import java.util.List;

import org.testng.*;
public class Assertions {
		public static void assertText(String expected,String actual)
		{
			Assert.assertEquals(actual, expected);
			Reporter.log("Expected text "+expected+" matches actual test "+actual);
		}
		public void assertTitle(String expected,String actual)
		{
			//Assert.assertEquals(actual, expected);
		//	Reporter.log("Expected title "+expected+"matches actual title "+actual);
			
		}
		public static void verifyEquals(String expected,String actual)
		{
			Assert.assertEquals(actual, expected);
			Reporter.log("Expected text "+expected+" matches actual test "+actual);
		}
		public static void verifyEqualsInt(int actual, int expected) {
			
			Assert.assertEquals(actual, expected);
			Reporter.log("Expected text "+expected+" matches actual test "+actual);
		}
		public static void verifyEqualsIntString(int actual, String expected) {
			Assert.assertEquals(actual, expected);
			Reporter.log("Expected text "+expected+" matches actual test "+actual);
			
		}
		public static void assertEqualsList(List actual, List expected) {
			Assert.assertEquals(actual, expected);
			Reporter.log("Expected text "+expected+" matches actual test "+actual);
			
		}
		public static void assertEqualsList(List actual, String expected) {
			Assert.assertEquals(actual, expected);
			Reporter.log("Expected text "+expected+" matches actual test "+actual);
			
			
		}
		
	}

