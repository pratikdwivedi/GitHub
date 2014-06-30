package com.practise;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ConvertHTMLToPDF 
{
    public static void main( String[] args )
    {
    	List<String> lines = null ;
    	try{
  
    	Document document = new Document(PageSize.A4_LANDSCAPE);
    	FileInputStream htmlString=new FileInputStream("test-output/emailable-report.html");
    	lines = IOUtils.readLines(htmlString);
    	String st=lines.toString().replace(",","");
    	org.jsoup.nodes.Document doc = Jsoup.parse(st);
    	PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test-output/emailable-report.html")));
        out.println(doc);
        out.close();
    	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("G:/PDFs/TestResult.pdf"));

        document.open();
   
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream("test-output/emailable-report.html"));	
        
         document.close();

        System.out.println( "PDF Created!" );
    }
    	catch(Throwable e)
    	{e.printStackTrace();}
    	finally{
    		lines.clear();
    	}
    }
}