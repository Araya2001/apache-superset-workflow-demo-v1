package org.aaj.example.mongodbcsvdatawarehouse.service;

public interface ObjectMapperService<InitialObject, ResultObject> {
    ResultObject mapInitialObjectToResultObject(InitialObject initialObject);
}
