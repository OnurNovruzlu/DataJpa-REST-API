package com.example.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class BookResponse {
    private Long id;
    private String name;
    private List<String> authorNames;
    private String categoryName;
}
