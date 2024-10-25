package com.dynamic.pdf.generation.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter
@Setter
@Component
@Document(collection = "SaveInfo")
public class Request {
    @Id
    ObjectId id;
    private String seller;
    private String sellerGstin;
    private String sellerAddress;
    private String buyer;
    private String buyerGstin;
    private String buyerAddress;
    private ArrayList<Item> items;

    @Getter
    @Setter
    public static class Item {
        private String name;
        private String quantity;
        private double rate;
        private double amount;
    }


    // Getters and Setters
}






