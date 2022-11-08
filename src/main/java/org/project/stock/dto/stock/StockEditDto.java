package org.project.stock.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StockEditDto {

    @NotNull
    private final String name;

    public StockEditDto(@JsonProperty("name") String name) {
        this.name = name;
    }
}
