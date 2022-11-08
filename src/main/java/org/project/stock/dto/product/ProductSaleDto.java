package org.project.stock.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductSaleDto {

    @NotNull
    private final String article;

    @NotNull
    private final String name;

    @NotNull
    private final Long priceSale;

    @NotNull
    private final Integer count;

    public ProductSaleDto(@JsonProperty("article") String article,
                          @JsonProperty("name") String name,
                          @JsonProperty("priceSale") Long priceSale,
                          @JsonProperty("count") Integer count) {
        this.article = article;
        this.name = name;
        this.priceSale = priceSale;
        this.count = count;
    }
}
