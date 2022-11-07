package org.project.stock.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.stock.StockCreateDto;
import org.project.stock.dto.stock.StockDto;
import org.project.stock.dto.stock.StockEditDto;
import org.project.stock.entity.Stock;
import org.project.stock.repository.StockRepository;
import org.project.stock.service.StockService;
import org.project.stock.service.mapper.StockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaStockService implements StockService {

    private final StockRepository stockRepository;
    private final StockMapper stockMapper;

    @Transactional
    @Override
    public StockDto createStock(StockCreateDto stockCreateDto) {
        Stock stock = new Stock(stockCreateDto.getName());

        stockRepository.saveAndFlush(stock);

        return stockMapper.mapStockToStockDto(stock);
    }

    @Transactional
    @Override
    public StockDto editStock(Integer id, StockEditDto stockEditDto) {
        Stock stock = stockRepository.findById(id).orElseThrow();

        stockRepository.saveAndFlush(stock);

        return stockMapper.mapStockToStockDto(stock);
    }

    @Override
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockRepository.findAll();
        return stockMapper.mapStockToStockDto(stocks);
    }
}
