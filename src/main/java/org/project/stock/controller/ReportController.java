package org.project.stock.controller;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.report.ReportProductDto;
import org.project.stock.dto.report.ReportProductRemainsDto;
import org.project.stock.service.report.ProductReport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final ProductReport productReport;

    @PostMapping("/{name}")
    public ResponseEntity<List<ReportProductDto>> getProductsByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(productReport.getProductsByName(name),
                HttpStatus.OK);
    }

    @GetMapping("/remains")
    public ResponseEntity<List<ReportProductRemainsDto>> getRemainsProducts() {
        return new ResponseEntity<>(productReport.getRemainsProducts(),
                HttpStatus.OK);
    }
}
