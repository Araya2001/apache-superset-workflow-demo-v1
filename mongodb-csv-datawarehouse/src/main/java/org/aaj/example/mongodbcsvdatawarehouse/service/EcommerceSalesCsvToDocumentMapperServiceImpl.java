package org.aaj.example.mongodbcsvdatawarehouse.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
@Log4j2
public class EcommerceSalesCsvToDocumentMapperServiceImpl implements EcommerceSalesCsvToDocumentMapperService {
    @Override
    public List<EcommerceSaleDocument> mapInitialObjectToResultObject(MultipartFile multipartFile) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()))) {
            return new CsvToBeanBuilder<EcommerceSaleDocument>(br)
                    .withType(EcommerceSaleDocument.class)
                    .build()
                    .parse();
        } catch (Exception e) {
            log.error(e);
        }
        return List.of();
    }
}
