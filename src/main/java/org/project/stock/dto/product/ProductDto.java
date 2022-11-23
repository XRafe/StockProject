package org.project.stock.dto.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductDto {

    private final String article;

    private final String name;

    private final Integer count;

    private final Long priceLastBuy;

    private final Long priceLastSale;

    private final Integer stockId;
}
