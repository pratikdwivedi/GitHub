package com.practise;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FetchFileData {

	public static void main(String[] args) {
		String s;
		try {
			 FileReader fr=new FileReader("E://VewUser.txt");
			   BufferedReader br= new BufferedReader(fr);
		
			   while (br.ready()) { 
				  String text = br.readLine(); 
				   System.out.println(text); 
				 }

			    
			
		} catch (Throwable e) {
			
			e.printStackTrace();
		}
		
	}
}
