package com.example.demo;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;

public class UserPdfGeneration {
	private List<User> user;
	public UserPdfGeneration(List<User> user)
	{
		this.user=user;
	}
	public void tableHeader(PdfPTable pdfpTable)
	{
		PdfPCell pdfpCell = new PdfPCell();
		pdfpCell.setBackgroundColor(Color.PINK);
		pdfpCell.setPadding(10);
		Font font=FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.BLACK);
		font.setSize(12);
		
		pdfpCell.setPhrase(new Phrase("Id",font));
		pdfpTable.addCell(pdfpCell);
		pdfpCell.setPhrase(new Phrase("Email",font));
		pdfpTable.addCell(pdfpCell);
		pdfpCell.setPhrase(new Phrase("Enabled",font));
		pdfpTable.addCell(pdfpCell);
		pdfpCell.setPhrase(new Phrase("Fullname",font));
		pdfpTable.addCell(pdfpCell);
		pdfpCell.setPhrase(new Phrase("Password",font));
		pdfpTable.addCell(pdfpCell);
		
	}
	
	public void tableofContent(PdfPTable pdfpTable)
	{
		
		for(User user:user)
		{
			pdfpTable.addCell(String.valueOf(user.getId()));
			pdfpTable.addCell(user.getEmail());
			pdfpTable.addCell(String.valueOf(user.getEnabled()));
			pdfpTable.addCell(user.getFullName());
			pdfpTable.addCell(user.getPassword());
			
		}
	    	
		
		
	}
	

	public void exportPdf(HttpServletResponse respose) throws DocumentException, IOException{
		
		Document doc=new Document(PageSize.A4);
		PdfWriter.getInstance(doc,respose.getOutputStream());
		doc.open();
		Font font=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.DARK_GRAY);
		font.setSize(18);
	    Paragraph paragraph=new Paragraph("ListOfEmployees",font);
		 paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		 doc.add(paragraph);
		 
		 PdfPTable table=new PdfPTable(5);
		 table.setWidthPercentage(100);
		  table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	        table.setSpacingBefore(10);
		 
		 tableHeader(table);
		 tableofContent(table);
		 
		 doc.add(table);
		 doc.close();
		 
		
		
		
		
	}

}
