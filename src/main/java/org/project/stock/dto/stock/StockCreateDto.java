package org.project.stock.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StockCreateDto {

    private final String name;

    public StockCreateDto(@JsonProperty("name") String name) {
        this.name = name;
    }
}
