package org.project.stock.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProductEditDto {

    private final String article;

    private final String name;

    private final Integer count;

    public ProductEditDto(@JsonProperty("article") String article,
                          @JsonProperty("name") String name,
                          @JsonProperty("count") Integer count) {
        this.article = article;
        this.name = name;
        this.count = count;
    }
}
