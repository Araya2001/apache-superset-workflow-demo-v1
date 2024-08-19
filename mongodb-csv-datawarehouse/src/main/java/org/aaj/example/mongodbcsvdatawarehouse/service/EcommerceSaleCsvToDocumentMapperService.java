package org.aaj.example.mongodbcsvdatawarehouse.service;

import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EcommerceSaleCsvToDocumentMapperService extends ObjectMapperService<MultipartFile, List<EcommerceSaleDocument>> {
}
