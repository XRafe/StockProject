package org.project.stock.dto.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReportProductDto {

    private final String article;

    private final String name;

    private final Long priceLastBuy;

    private final Long priceLastSale;
}
