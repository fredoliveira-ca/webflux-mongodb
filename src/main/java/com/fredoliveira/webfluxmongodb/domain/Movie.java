package com.fredoliveira.webfluxmongodb.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@RequiredArgsConstructor
public class Movie {

    private String id;

    @NonNull
    private String title;
}
