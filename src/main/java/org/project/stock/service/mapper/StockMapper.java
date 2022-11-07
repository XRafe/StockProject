package org.project.stock.service.mapper;

import org.project.stock.dto.stock.StockDto;
import org.project.stock.entity.Stock;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    public StockDto mapStockToStockDto(Stock stock) {
        return new StockDto(
                stock.getId(),
                stock.getName()
        );
    }

    public List<StockDto> mapStockToStockDto(Collection<Stock> stocks) {
        return stocks.stream()
                .map(this::mapStockToStockDto)
                .collect(Collectors.toList());
    }
}
