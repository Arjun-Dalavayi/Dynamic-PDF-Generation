package com.dynamic.pdf.generation.demo.repository;

import com.dynamic.pdf.generation.demo.entity.FileName;
import com.dynamic.pdf.generation.demo.entity.Request;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FileNameRepository extends MongoRepository<FileName, ObjectId> {
List<FileName> findByRequestId(Request requestId);
}
