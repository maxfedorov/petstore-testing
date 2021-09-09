package com.github.maxfedorov.petstore.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private int id;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
}
