package com.practise;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.pdfbox.pdmodel.PDPage;
import org.jsoup.Jsoup;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ConvertHTMLToPDF 
{
    public static void main( String[] args )
    {
    	try{
      // step 1
    	Document document = new Document(PageSize.A4_LANDSCAPE);
    	/*FileInputStream htmlString=new FileInputStream("test-output/index.html");
    	org.jsoup.nodes.Document doc = Jsoup.parse(htmlString.toString());
       */ // step 2
    	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("G:/PDFs/TestResult.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,new FileInputStream("test-output/emailable-report.html"));	
        
        //step 5
         document.close();

        System.out.println( "PDF Created!" );
    }
    	catch(Throwable e)
    	{e.printStackTrace();}
    }
}