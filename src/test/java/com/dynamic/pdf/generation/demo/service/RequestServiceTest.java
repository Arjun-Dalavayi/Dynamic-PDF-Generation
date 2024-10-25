package com.dynamic.pdf.generation.demo.service;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dynamic.pdf.generation.demo.entity.Request;
import com.dynamic.pdf.generation.demo.repository.FileNameRepository;
import com.dynamic.pdf.generation.demo.repository.RequestRepository;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RequestService.class, Request.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RequestServiceTest {
    @MockBean
    private FileNameRepository fileNameRepository;

    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private PdfGeneratorService pdfGeneratorService;

    @Autowired
    private Request request;

    @MockBean
    private RequestRepository requestRepository;

    @Autowired
    private RequestService requestService;


    @Test
    void testCheckDb() throws Exception {
        // Arrange
        when(mongoTemplate.find(Mockito.<Query>any(), Mockito.<Class<Request>>any())).thenReturn(new ArrayList<>());

        Request request2 = new Request();
        request2.setBuyer("Buyer");
        request2.setBuyerAddress("42 Main St");
        request2.setBuyerGstin("Buyer Gstin");
        request2.setId(ObjectId.get());
        request2.setItems(new ArrayList<>());
        request2.setSeller("Seller");
        request2.setSellerAddress("42 Main St");
        request2.setSellerGstin("Seller Gstin");

        // Act
        String actualCheckDbResult = requestService.checkDb(request2);

        // Assert
        verify(mongoTemplate).find(isA(Query.class), isA(Class.class));
        assertNull(actualCheckDbResult);
    }

}
