package org.project.stock.controller;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.stock.StockCreateDto;
import org.project.stock.dto.stock.StockDto;
import org.project.stock.dto.stock.StockEditDto;
import org.project.stock.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping("/create")
    public ResponseEntity<StockDto> createStock(StockCreateDto stockCreateDto) {
        return new ResponseEntity<>(stockService.createStock(stockCreateDto),
                HttpStatus.OK);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<StockDto> editStock(Integer id, StockEditDto stockEditDto) {
        return new ResponseEntity<>(stockService.editStock(id, stockEditDto),
                HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StockDto>> getAllStocks() {
        return new ResponseEntity<>(stockService.getAllStocks(),
                HttpStatus.OK);
    }
}
