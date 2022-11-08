package org.project.stock.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ProductReplaceDto {

    @NotNull
    private final String article;

    @NotNull
    private final Integer count;

    public ProductReplaceDto(@JsonProperty("article") String article,
                             @JsonProperty("count") Integer count) {
        this.article = article;
        this.count = count;
    }
}
