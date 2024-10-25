package com.dynamic.pdf.generation.demo.service;

import com.dynamic.pdf.generation.demo.entity.FileName;
import com.dynamic.pdf.generation.demo.entity.Request;
import com.dynamic.pdf.generation.demo.repository.FileNameRepository;
import com.dynamic.pdf.generation.demo.repository.RequestRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;


@Service
public class PdfGeneratorService {

    @Autowired
    Request request;
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private FileNameRepository fileNameRepository;


   //Giving local path of the system
    private static final String PDF_STORAGE_DIR = "C:\\Users\\ADMIN\\Desktop\\pdfGeneration\\";


    public String generatePdf(Request request) throws Exception {
        requestRepository.save(request);

        FileName fileName=new FileName();
        // Use the hash as part of the PDF filename to identify it uniquely
        String pdfFileName= PDF_STORAGE_DIR +request.getId().toString()+".pdf";
        fileName.setFileName(pdfFileName);
        fileName.setRequestId(request);
        fileNameRepository.save(fileName);

        System.out.println(pdfFileName);




        // Create a new PDF document
        Document document = new Document();
        FileOutputStream fName=new FileOutputStream(pdfFileName);
        PdfWriter.getInstance(document, fName);

        document.open();

        // Create a table for the invoice header
        PdfPTable headerTable = new PdfPTable(2);
        headerTable.setWidthPercentage(100);

        // Add header information
        headerTable.addCell(new PdfPCell(new Phrase("\n \n      Seller: \n      "+request.getSeller()+"\n      "+request.getSellerAddress()+"\n      GSTIN: "+request.getSellerGstin() +"\n \n \n", FontFactory.getFont("Arial", 12, Font.BOLD))));
        headerTable.addCell(new PdfPCell(new Phrase("\n \n      Buyer: \n      "+request.getBuyer()+"\n      "+request.getBuyerAddress()+"\n      GSTIN: "+request.getBuyerGstin()+"\n \n \n", FontFactory.getFont("Arial", 12, Font.BOLD))));

        document.add(headerTable);

        PdfPTable itemTable = new PdfPTable(4);
        itemTable.setWidthPercentage(100);

        itemTable.setHeaderRows(1);
        PdfPCell[] headerCells = new PdfPCell[4];
        headerCells[0] = new PdfPCell(new Phrase("Item", FontFactory.getFont("Arial", 12, Font.BOLD)));
        headerCells[1] = new PdfPCell(new Phrase("Quantity", FontFactory.getFont("Arial", 12, Font.BOLD)));
        headerCells[2] = new PdfPCell(new Phrase("Rate", FontFactory.getFont("Arial", 12, Font.BOLD)));
        headerCells[3] = new PdfPCell(new Phrase("Amount", FontFactory.getFont("Arial", 12, Font.BOLD)));
        itemTable.addCell(headerCells[0]);
        itemTable.addCell(headerCells[1]);
        itemTable.addCell(headerCells[2]);
        itemTable.addCell(headerCells[3]);

        for (Request.Item item : request.getItems()) {
            itemTable.addCell(item.getName());
            itemTable.addCell(item.getQuantity());
            itemTable.addCell(item.getRate()+"");
            itemTable.addCell(item.getAmount()+"");
        }

        document.add(itemTable);
        document.close();


        return pdfFileName;  // Return the path of the generated PDF
    }




}
