package com.github.maxfedorov.petstore.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    private int code;
    private String type;
    private String message;
}
