package org.project.stock.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.product.ProductDto;
import org.project.stock.dto.product.ProductEditDto;
import org.project.stock.dto.product.ProductEntranceDto;
import org.project.stock.dto.product.ProductSaleDto;
import org.project.stock.dto.product.document.ProductDocumentEntranceDto;
import org.project.stock.dto.product.document.ProductDocumentReplaceDto;
import org.project.stock.dto.product.document.ProductDocumentSaleDto;
import org.project.stock.entity.Product;
import org.project.stock.repository.ProductRepository;
import org.project.stock.service.ProductService;
import org.project.stock.service.mapper.ProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaProductService implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public List<ProductDto> entranceProduct(ProductDocumentEntranceDto productDocumentEntranceDto) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductEntranceDto productEntranceDto : productDocumentEntranceDto.getProducts()) {
            Product product = new Product(
                    productEntranceDto.getArticle(),
                    productEntranceDto.getName(),
                    productEntranceDto.getCount(),
                    productEntranceDto.getPriceBuy(),
                    null,
                    productDocumentEntranceDto.getStockId()
            );

            productRepository.saveAndFlush(product);

            productDtos.add(productMapper.mapProductToProductDto(product));
        }

        return productDtos;
    }

    @Transactional
    @Override
    public List<ProductDto> replaceProduct(ProductDocumentReplaceDto productDocumentReplaceDto) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductDto productDto : productDocumentReplaceDto.getProducts()) {
            Product product = productRepository.findByName(productDto.getName())
                    .orElseThrow();
            product.setStockId(productDocumentReplaceDto.getNewStock());

            productRepository.saveAndFlush(product);

            productDtos.add(productMapper.mapProductToProductDto(product));
        }

        return productDtos;
    }

    @Transactional
    @Override
    public List<ProductDto> saleProduct(ProductDocumentSaleDto productDocumentSaleDto) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductSaleDto productSaleDto : productDocumentSaleDto.getProducts()) {
            Product product = productRepository.findByName(productSaleDto.getName())
                    .orElseThrow();

            product.setCount(product.getCount() - productSaleDto.getCount());
            product.setPriceLastSale(productSaleDto.getPriceSale());

            productRepository.saveAndFlush(product);

            productDtos.add(productMapper.mapProductToProductDto(product));
        }
        return productDtos;
    }

    @Transactional
    @Override
    public ProductDto editProduct(ProductEditDto productEditDto) {
        Product product = productRepository.findByName(productEditDto.getName())
                .orElseThrow();

        product.setArticle(productEditDto.getArticle());
        product.setName(productEditDto.getName());
        product.setCount(productEditDto.getCount());

        productRepository.saveAndFlush(product);

        return productMapper.mapProductToProductDto(product);
    }
}
