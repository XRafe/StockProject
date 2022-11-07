package org.project.stock.service;

import org.project.stock.dto.product.ProductDto;
import org.project.stock.dto.product.ProductEditDto;
import org.project.stock.dto.product.document.ProductDocumentEntranceDto;
import org.project.stock.dto.product.document.ProductDocumentReplaceDto;
import org.project.stock.dto.product.document.ProductDocumentSaleDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> entranceProduct(ProductDocumentEntranceDto productDocumentEntranceDto);

    List<ProductDto> replaceProduct(ProductDocumentReplaceDto productDocumentReplaceDto);

    List<ProductDto> saleProduct(ProductDocumentSaleDto productDocumentSaleDto);

    ProductDto editProduct(ProductEditDto productEditDto);
}
