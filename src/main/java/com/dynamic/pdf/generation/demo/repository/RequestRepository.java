package com.dynamic.pdf.generation.demo.repository;

import com.dynamic.pdf.generation.demo.entity.Request;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface RequestRepository extends MongoRepository<Request, ObjectId> {

}