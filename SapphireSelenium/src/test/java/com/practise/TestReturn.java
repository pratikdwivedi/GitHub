package com.practise;

public class TestReturn {
	 public static String Firstname;
	public static void main(String arg[])
	{
		TestReturn tr=new TestReturn();
		tr.setname("A");
		tr.returNname();
		tr.setname("B");
		tr.tellname();
		
}
	 public void  setname(String name)
		{
		 Firstname = name;
		    System.out.println(name); 
		}
		public String returNname()
		{
		    return Firstname;
		}
		public void tellname()
		{
		    System.out.printf("your new firstName is %s",returNname());
		}
}