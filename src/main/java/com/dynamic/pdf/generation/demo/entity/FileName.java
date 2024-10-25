package com.dynamic.pdf.generation.demo.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Document(collection = "FileName")
@Component
public class FileName {

    @Id
    private ObjectId id;

    @DBRef
   private Request requestId;

    private String fileName;
}
