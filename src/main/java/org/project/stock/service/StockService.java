package org.project.stock.service;

import org.project.stock.dto.stock.StockCreateDto;
import org.project.stock.dto.stock.StockDto;
import org.project.stock.dto.stock.StockEditDto;

import java.util.List;

public interface StockService {

    StockDto createStock(StockCreateDto stockCreateDto);

    StockDto editStock(Integer id, StockEditDto stockEditDto);

    List<StockDto> getAllStocks();

    String deleteStock(Integer id);
}
