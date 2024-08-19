package org.aaj.example.mongodbcsvdatawarehouse.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ModelResponse<Model> {
    private Model model;
    private String message;
    private String status;
}
