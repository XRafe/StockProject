package org.project.stock.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProductEntranceDto {

    private final String article;

    private final String name;

    private final Integer count;

    private final Long priceBuy;

    public ProductEntranceDto(@JsonProperty("") String article,
                              @JsonProperty("") String name,
                              @JsonProperty("") Integer count,
                              @JsonProperty("") Long priceBuy) {
        this.article = article;
        this.name = name;
        this.count = count;
        this.priceBuy = priceBuy;
    }
}
