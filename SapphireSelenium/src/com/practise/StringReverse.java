package com.practise;

import java.util.ArrayList;
import java.util.List;

public class StringReverse {
	static char nameF;static char c1[];
	static char nameR;
	static String first;
	public static void main(String arg[]) 
	{
		 first="pratik";
		char [] array=first.toCharArray();
		for(int i=0;i<=first.length()-1;i++)
		{
			nameF=first.charAt(i);
			System.out.print(nameF);
		}
		System.out.println();
		for(int i=first.length()-1;i>=0;i--)
		{
			char nameR=first.charAt(i);
			System.out.print(nameR);
		}
	}
}
