package org.project.stock.dto.product;

import lombok.Getter;

@Getter
public class ProductSaleDto {

    private final String article;

    private final String name;

    private final Long priceSale;

    private final Integer count;

    public ProductSaleDto(String article, String name, Long priceSale, Integer count) {
        this.article = article;
        this.name = name;
        this.priceSale = priceSale;
        this.count = count;
    }
}
