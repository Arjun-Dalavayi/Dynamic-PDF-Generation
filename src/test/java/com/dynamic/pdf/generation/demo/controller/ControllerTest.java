package com.dynamic.pdf.generation.demo.controller;

import static org.mockito.Mockito.when;

import com.dynamic.pdf.generation.demo.entity.Request;
import com.dynamic.pdf.generation.demo.service.PdfGeneratorService;
import com.dynamic.pdf.generation.demo.service.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {Controller.class, Request.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ControllerTest {
    @Autowired
    private Controller controller;

    @MockBean
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private Request request;

    @MockBean
    private RequestService requestService;

    @Test
    void testGenerateInvoice() throws Exception {

        when(pdfGeneratorService.generatePdf(Mockito.<Request>any())).thenReturn("Generate Pdf");
        when(requestService.checkDb(Mockito.<Request>any())).thenReturn("Check Db");

        Request request2 = new Request();
        request2.setBuyer("Buyer");
        request2.setBuyerAddress("42 Main St");
        request2.setBuyerGstin("Buyer Gstin");
        request2.setId(ObjectId.get());
        request2.setItems(new ArrayList<>());
        request2.setSeller("Seller");
        request2.setSellerAddress("42 Main St");
        request2.setSellerGstin("Seller Gstin");
        String content = (new ObjectMapper()).writeValueAsString(request2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/invoices/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("PDF generated at: Check Db"));
    }


    @Test
    void testGenerateInvoice2() throws Exception {
        // Arrange
        when(pdfGeneratorService.generatePdf(Mockito.<Request>any())).thenReturn("Generate Pdf");
        when(requestService.checkDb(Mockito.<Request>any())).thenReturn(null);

        Request request2 = new Request();
        request2.setBuyer("Buyer");
        request2.setBuyerAddress("42 Main St");
        request2.setBuyerGstin("Buyer Gstin");
        request2.setId(ObjectId.get());
        request2.setItems(new ArrayList<>());
        request2.setSeller("Seller");
        request2.setSellerAddress("42 Main St");
        request2.setSellerGstin("Seller Gstin");
        String content = (new ObjectMapper()).writeValueAsString(request2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/invoices/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("PDF generated at: Generate Pdf"));
    }


    @Test
    void testGenerateInvoice3() throws Exception {

        when(pdfGeneratorService.generatePdf(Mockito.<Request>any())).thenReturn("Generate Pdf");
        when(requestService.checkDb(Mockito.<Request>any())).thenReturn("Check Db");

        Request request2 = new Request();
        request2.setBuyer("Buyer");
        request2.setBuyerAddress("42 Main St");
        request2.setBuyerGstin("Buyer Gstin");
        request2.setId(null);
        request2.setItems(new ArrayList<>());
        request2.setSeller("Seller");
        request2.setSellerAddress("42 Main St");
        request2.setSellerGstin("Seller Gstin");
        String content = (new ObjectMapper()).writeValueAsString(request2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/invoices/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);


        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("PDF generated at: Check Db"));
    }
}
