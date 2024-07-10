package com.example.market_api.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageObject<T> {

    private Integer page;

    private Integer size;

    private Integer totalPages;

    private Long totalSize;

    private Long date;

    private List<T> data;

    private T details;


}