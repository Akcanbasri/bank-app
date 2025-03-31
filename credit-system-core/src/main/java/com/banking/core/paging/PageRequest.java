package com.banking.core.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequest {
    private int page = 0;
    private int size = 10;
    private Sort sort;

    public org.springframework.data.domain.PageRequest getPageable() {
        if (sort != null && sort.getField() != null && sort.getDirection() != null) {
            return org.springframework.data.domain.PageRequest.of(
                    page,
                    size,
                    org.springframework.data.domain.Sort.by(sort.getDirection(), sort.getField()));
        }
        return org.springframework.data.domain.PageRequest.of(page, size);
    }
}