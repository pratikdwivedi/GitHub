package com.practise;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class TestPDFUsingItext {
	static String status = "PASS";
	static String status1 = "Pratik";
	public static void main(String[] args) {
		
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(
					"HelloWorld.pdf"));

			document.open();
			addMetaData(document);

			
			
			document.add(new Paragraph("A Hello World PDF document."));
			document.newPage();
			document.close(); 
			System.out.println("DONE");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		


	}

	private static void addMetaData(Document document) {
	    document.addTitle("My first PDF");
	    document.addHeader(status, status1);
	    document.addSubject("Using iText");
	    document.addKeywords("Java, PDF, iText");
	    document.addAuthor("Lars Vogel");
	    document.addCreator("Lars Vogel");
	  }
}
