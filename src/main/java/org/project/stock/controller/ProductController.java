package org.project.stock.controller;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.product.ProductDto;
import org.project.stock.dto.product.ProductEditDto;
import org.project.stock.dto.product.document.ProductDocumentEntranceDto;
import org.project.stock.dto.product.document.ProductDocumentReplaceDto;
import org.project.stock.dto.product.document.ProductDocumentSaleDto;
import org.project.stock.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/entrance")
    public ResponseEntity<List<ProductDto>> entranceProduct(ProductDocumentEntranceDto productDocumentEntranceDto) {
        return new ResponseEntity<>(productService.entranceProduct(productDocumentEntranceDto),
                HttpStatus.OK);
    }

    @PutMapping("/replace/stock/{stockId}")
    public ResponseEntity<List<ProductDto>> replaceProduct(@PathVariable("stockId") Integer stockId,
                                                           ProductDocumentReplaceDto productDocumentReplaceDto) {
        return new ResponseEntity<>(productService.replaceProduct(productDocumentReplaceDto),
                HttpStatus.OK);
    }

    @PostMapping("/sale")
    public ResponseEntity<List<ProductDto>> saleProduct(ProductDocumentSaleDto productDocumentSaleDto) {
        return new ResponseEntity<>(productService.saleProduct(productDocumentSaleDto),
                HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<ProductDto> editProduct(ProductEditDto productEditDto) {
        return new ResponseEntity<>(productService.editProduct(productEditDto),
                HttpStatus.OK);
    }
}
