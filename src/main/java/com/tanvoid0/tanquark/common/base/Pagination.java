package com.tanvoid0.tanquark.common.base;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.StringJoiner;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T> {
    private List<T> results;
    private Long totalItems;
    private int totalPages;
    private int page;
    private int size;
    private Integer previousPageNumber;
    private Integer nextPageNumber;

    public Pagination(final List<T> results, final Long totalItems, final int page, final int size) {
        this.results = results;
        this.totalItems = totalItems;
        this.page = page;
        this.size = size;
        this.totalPages = (int) Math.ceil(totalItems / (double) size);
        this.previousPageNumber = (page > 1) ? (page - 1) : null;
        this.nextPageNumber = page < totalPages - 1 ? page + 1 : null;
    }

    public static String getOrderBy(final List<String> orderBy) {
        if (orderBy == null || orderBy.isEmpty()) {
            return "order by id+asc";
        } else {
            StringJoiner stringJoiner = new StringJoiner(",");
            orderBy.forEach(stringJoiner::add);
            return "order by " + stringJoiner;
        }
    }
}
