package com.dynamic.pdf.generation.demo.service;

import static org.mockito.Mockito.when;

import com.dynamic.pdf.generation.demo.entity.FileName;
import com.dynamic.pdf.generation.demo.entity.Request;
import com.dynamic.pdf.generation.demo.repository.FileNameRepository;
import com.dynamic.pdf.generation.demo.repository.RequestRepository;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PdfGeneratorService.class, Request.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PdfGeneratorServiceTest {
    @MockBean
    private FileNameRepository fileNameRepository;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private Request request;

    @MockBean
    private RequestRepository requestRepository;

    /**
     * Method under test: {@link PdfGeneratorService#generatePdf(Request)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGeneratePdf() throws Exception {

        // Arrange
        Request requestId = new Request();
        requestId.setBuyer("Buyer");
        requestId.setBuyerAddress("42 Main St");
        requestId.setBuyerGstin("Buyer Gstin");
        requestId.setId(ObjectId.get());
        requestId.setItems(new ArrayList<>());
        requestId.setSeller("Seller");
        requestId.setSellerAddress("42 Main St");
        requestId.setSellerGstin("Seller Gstin");

        FileName fileName = new FileName();
        fileName.setFileName("foo.txt");
        fileName.setId(ObjectId.get());
        fileName.setRequestId(requestId);
        when(fileNameRepository.save(Mockito.<FileName>any())).thenReturn(fileName);

        Request request2 = new Request();
        request2.setBuyer("Buyer");
        request2.setBuyerAddress("42 Main St");
        request2.setBuyerGstin("Buyer Gstin");
        request2.setId(ObjectId.get());
        request2.setItems(new ArrayList<>());
        request2.setSeller("Seller");
        request2.setSellerAddress("42 Main St");
        request2.setSellerGstin("Seller Gstin");
        when(requestRepository.save(Mockito.<Request>any())).thenReturn(request2);

        Request request3 = new Request();
        request3.setBuyer("Buyer");
        request3.setBuyerAddress("42 Main St");
        request3.setBuyerGstin("Buyer Gstin");
        request3.setId(null);
        request3.setItems(new ArrayList<>());
        request3.setSeller("Seller");
        request3.setSellerAddress("42 Main St");
        request3.setSellerGstin("Seller Gstin");

        // Act
        pdfGeneratorService.generatePdf(request3);
    }

    /**
     * Method under test: {@link PdfGeneratorService#generatePdf(Request)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGeneratePdf2() throws Exception {


        // Arrange
        Request requestId = new Request();
        requestId.setBuyer("Buyer");
        requestId.setBuyerAddress("42 Main St");
        requestId.setBuyerGstin("Buyer Gstin");
        requestId.setId(ObjectId.get());
        requestId.setItems(new ArrayList<>());
        requestId.setSeller("Seller");
        requestId.setSellerAddress("42 Main St");
        requestId.setSellerGstin("Seller Gstin");

        FileName fileName = new FileName();
        fileName.setFileName("foo.txt");
        fileName.setId(ObjectId.get());
        fileName.setRequestId(requestId);
        when(fileNameRepository.save(Mockito.<FileName>any())).thenReturn(fileName);

        Request request2 = new Request();
        request2.setBuyer("Buyer");
        request2.setBuyerAddress("42 Main St");
        request2.setBuyerGstin("Buyer Gstin");
        request2.setId(ObjectId.get());
        request2.setItems(new ArrayList<>());
        request2.setSeller("Seller");
        request2.setSellerAddress("42 Main St");
        request2.setSellerGstin("Seller Gstin");
        when(requestRepository.save(Mockito.<Request>any())).thenReturn(request2);

        Request request3 = new Request();
        request3.setBuyer("Buyer");
        request3.setBuyerAddress("42 Main St");
        request3.setBuyerGstin("Buyer Gstin");
        request3.setId(ObjectId.get());
        request3.setItems(new ArrayList<>());
        request3.setSeller("Seller");
        request3.setSellerAddress("42 Main St");
        request3.setSellerGstin("Seller Gstin");

        // Act
        pdfGeneratorService.generatePdf(request3);
    }

}
