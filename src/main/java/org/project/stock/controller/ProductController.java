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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductService productService;

    @PostMapping("/entrance")
    public ResponseEntity<List<ProductDto>> entranceProduct(@Valid @RequestBody ProductDocumentEntranceDto productDocumentEntranceDto) {
        return new ResponseEntity<>(productService.entranceProduct(productDocumentEntranceDto),
                HttpStatus.OK);
    }

    @PutMapping("/replace/stock/{stockId}")
    public ResponseEntity<List<ProductDto>> replaceProduct(@Valid @PathVariable("stockId") Integer newStockId,
                                                           @Valid @RequestBody ProductDocumentReplaceDto productDocumentReplaceDto) {
        return new ResponseEntity<>(productService.replaceProduct(newStockId, productDocumentReplaceDto),
                HttpStatus.OK);
    }

    @PostMapping("/sale")
    public ResponseEntity<List<ProductDto>> saleProduct(@Valid @RequestBody ProductDocumentSaleDto productDocumentSaleDto) {
        return new ResponseEntity<>(productService.saleProduct(productDocumentSaleDto),
                HttpStatus.OK);
    }

    @PutMapping("{id}/edit")
    public ResponseEntity<ProductDto> editProduct(@Valid @PathVariable("id") Integer id,
                                                  @Valid @RequestBody ProductEditDto productEditDto) {
        return new ResponseEntity<>(productService.editProduct(id, productEditDto),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        return new ResponseEntity<>(productService.getAllProduct(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.deleteProduct(id),
                HttpStatus.OK);
    }
}
