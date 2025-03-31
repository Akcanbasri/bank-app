package com.banking.core.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sort {
    private String field;
    private String direction;

    public org.springframework.data.domain.Sort.Direction getDirection() {
        return direction.equalsIgnoreCase("DESC")
                ? org.springframework.data.domain.Sort.Direction.DESC
                : org.springframework.data.domain.Sort.Direction.ASC;
    }
}