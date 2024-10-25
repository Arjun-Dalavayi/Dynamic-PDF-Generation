package com.dynamic.pdf.generation.demo.controller;

import com.dynamic.pdf.generation.demo.entity.Request;
import com.dynamic.pdf.generation.demo.service.PdfGeneratorService;
import com.dynamic.pdf.generation.demo.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private RequestService requestService;

    // Endpoint to generate or download and return existing PDF
    @PostMapping("/generate")
    public ResponseEntity<String> generateInvoice(@RequestBody Request request) {
        String pdfPathFirst = null;
        try {
            pdfPathFirst = requestService.checkDb(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (pdfPathFirst != null) {
            return new ResponseEntity<>("PDF generated at: " + pdfPathFirst, HttpStatus.OK);
        }

        try {
            String pdfPath = pdfGeneratorService.generatePdf(request);
            return new ResponseEntity<>("PDF generated at: " + pdfPath, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating PDF: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //The request body of the API call looks something like this:
  /*  {
        "seller": "XYZ Pvt. Ltd.",
            "sellerGstin": "29AABBCCDD121ZD",
            "sellerAddress": "New Delhi, India",
            "buyer": "Vedant Computers",
            "buyerGstin": "29AABBCCDD131ZD",
            "buyerAddress": "New Delhi, India",
            "items": [
        {
            "name": "Product 1",
                "quantity": "12 Nos",
                "rate": 123.00,
                "amount": 1476.00
        }

   ]
    }*/
}
