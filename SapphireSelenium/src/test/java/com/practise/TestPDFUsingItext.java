package com.practise;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.PageSize;

public class TestPDFUsingItext {
	/*
	 * static String status = "PASS"; static String status1 = "Pratik";
	 */
	public static	List<String> resultList = new ArrayList<String>();
	public void WriteTestResultToPdfFile(String fileName,
			List<String> testCase) {

		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			addMetaData(document);
			document.add(new Paragraph(testCase.toString()));
			document.newPage();
			document.close();
			System.out.println("DONE");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void addMetaData(Document document) {
		document.addTitle("ARES Demo");
		// document.addHeader(status, status1);
		document.addSubject("Using iText");
		document.addKeywords("Java, PDF, iText");
		document.addAuthor("Pratik dwivedi");
		document.addCreator("Pratik dwivedi");
	}
}