package org.project.stock.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StockCreateDto {

    @NotNull
    private final String name;

    public StockCreateDto(@JsonProperty("name") String name) {
        this.name = name;
    }
}
