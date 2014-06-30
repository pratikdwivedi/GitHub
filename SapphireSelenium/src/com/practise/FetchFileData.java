package com.practise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FetchFileData {

	public static void main(String[] args) {
		String s;
		String text = null;
	
		try {
			 FileReader fr=new FileReader("E://VewUser.txt");
			   BufferedReader br= new BufferedReader(fr);
		
			   while (br.ready()) { 
				   text = br.readLine(); 
				   System.out.println(text); 
				 
				 }

			
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
	}
}
