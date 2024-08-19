package org.aaj.example.mongodbcsvdatawarehouse.service;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import org.aaj.example.mongodbcsvdatawarehouse.model.EcommerceSaleDocument;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Service
@Log4j2
public class EcommerceSaleCsvToDocumentMapperServiceImpl implements EcommerceSaleCsvToDocumentMapperService {
    @Override
    public Flux<EcommerceSaleDocument> mapInitialObjectToResultObject(FilePart filePart) {

        var dataBufferFlux = filePart.content();

        var joinedDataBuffer = DataBufferUtils.join(dataBufferFlux);

        var inputStreamFromDataBuffer = joinedDataBuffer.map(DataBuffer::asInputStream);

        var bufferedReader = inputStreamFromDataBuffer.map(inputStream -> new BufferedReader(new InputStreamReader(inputStream)));

        var mappedCsvValuesToEcommerceSaleDocument = bufferedReader
                .flatMapIterable(br ->
                        new CsvToBeanBuilder<EcommerceSaleDocument>(br)
                                .withType(EcommerceSaleDocument.class)
                                .build()
                                .parse()
                );

        return mappedCsvValuesToEcommerceSaleDocument.doOnError(log::error);
    }
}
