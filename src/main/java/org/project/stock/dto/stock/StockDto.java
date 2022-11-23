package org.project.stock.dto.stock;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StockDto {

    private final Integer id;

    private final String name;
}
