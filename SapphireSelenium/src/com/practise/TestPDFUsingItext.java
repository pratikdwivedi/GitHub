package com.practise;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;




public class TestPDFUsingItext {
	 public static void main(String[] args) {

	        Document document = new Document();

	        try {
	            PdfWriter.getInstance(document,
	                new FileOutputStream("HelloWorld.pdf"));

	            document.open();
	            document.add(new Paragraph("A Hello World PDF document."));
	            document.close(); // no need to close PDFwriter?
System.out.println("DONE");
	        } catch (Throwable e) {
	            e.printStackTrace();
	        } 

	    }
}
