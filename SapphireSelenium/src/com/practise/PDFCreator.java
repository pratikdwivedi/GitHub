package com.practise;

import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;

public class PDFCreator {
	public static void doPDFCreate(String fileName, Object document) {
		Document pdf = new Document();
		if (!pdf.isOpen()) {
			pdf.open();
		}

		pdf.setPageSize(PageSize.A4.rotate());
		HeaderFooter footer = new HeaderFooter(new Phrase("Page "), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		pdf.setFooter(footer);

		Font font = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
		try {

			Phrase titlePhrase = new Phrase("1", font);
			titlePhrase.add("\n");
			Paragraph paragraph = new Paragraph();
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.add(titlePhrase);
			// paragraph.add(document);
			font = new Font(Font.TIMES_ROMAN, 14, Font.NORMAL);

			pdf.close();

		} catch (Throwable e) {

			e.printStackTrace();
		}

	}

	private static void addPDFTitle(Document pdf, Font font,
			final String msgCode, String titleText) throws Exception {
		Phrase titlePhrase;
		Paragraph paragraph;
		titlePhrase = new Phrase("Message" + ": " + titleText, font);
		paragraph = new Paragraph();
		// paragraph.setIndentationRight(RIGHT_INDENT);
		titlePhrase.add("\n");
		titlePhrase.add("\n");
		paragraph.add(titlePhrase);
		pdf.add(paragraph);
	}
}