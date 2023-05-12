package com.example.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AuthorResponse {
    private Long id;
    private String name;
    private List<String> bookNames;
    private String zipcodeName;
}
