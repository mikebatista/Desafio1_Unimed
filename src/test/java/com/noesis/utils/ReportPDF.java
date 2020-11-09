package com.noesis.utils;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ReportPDF {

	static Document document = new Document();
	static Font font = new Font(FontFamily.HELVETICA, 12, Font.BOLD);

	public static void openPDF() {

		try {
			String path = System.getProperty("user.dir") + "\\evidencias\\";
			String File = path+ "report.pdf";
			System.out.println(path+"aaaaa.fpd");
			PdfWriter.getInstance(document, new FileOutputStream(File));
			document.open();
			document.addAuthor("Michael Batista");
			document.addTitle("Teste automatizado com BDD");
			// document.addSubject("Descrição do projeto");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void addText(String text) throws Exception {
		document.add(new Paragraph(text, font));
	}

	public static void addImage(byte[] screanshot, String description) {
		try {
			Paragraph preface = new Paragraph(description, font);
			preface.setAlignment(Element.ALIGN_CENTER);
			Image img = Image.getInstance(screanshot);
			img.scaleToFit((float)(PageSize.A4.getWidth()*0.85), (float)(PageSize.A4.getHeight() *0.85));
			//img.scaleToFit((PageSize.A4.getWidth()),(PageSize.A4.getHeight()));
			
			PdfPTable table = new PdfPTable(1);
			
			
		    table.setWidthPercentage(100);
		 
		    PdfPCell cell1 = new PdfPCell();
		    cell1.setMinimumHeight(50);
		    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell1.addElement(preface);
		    table.addCell(cell1);
		    
		    PdfPCell cell2 = new PdfPCell();
		    cell2.setMinimumHeight(50);
		    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
		    cell2.addElement(img);
		    table.addCell(cell2);
		    
		    document.add(table);
		    document.add(new Paragraph(""));
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void save() {
		document.close();
	}
}