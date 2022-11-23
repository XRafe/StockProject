package org.project.stock.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductEntranceDto {

    @NotNull
    private final String article;

    @NotNull
    private final String name;

    @NotNull
    private final Integer count;

    @NotNull
    private final Long priceBuy;

    public ProductEntranceDto(@JsonProperty("article") String article,
                              @JsonProperty("name") String name,
                              @JsonProperty("count") Integer count,
                              @JsonProperty("priceBuy") Long priceBuy) {
        this.article = article;
        this.name = name;
        this.count = count;
        this.priceBuy = priceBuy;
    }
}
