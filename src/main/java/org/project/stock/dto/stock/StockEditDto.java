package org.project.stock.dto.stock;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class StockEditDto {

    private final Integer id;

    private final String name;

    public StockEditDto(@JsonProperty("id") Integer id,
                        @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
