package org.project.stock.service.impl;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.product.*;
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
            Product product;

            if (!productRepository.existsByArticleAndStockId(productEntranceDto.getArticle(),
                    productDocumentEntranceDto.getStockId())) {
                product = new Product(
                        productEntranceDto.getArticle(),
                        productEntranceDto.getName(),
                        productEntranceDto.getCount(),
                        productEntranceDto.getPriceBuy(),
                        null,
                        productDocumentEntranceDto.getStockId()
                );

            } else {
                product = productRepository.findByArticleAndStockId(productEntranceDto.getArticle(),
                        productDocumentEntranceDto.getStockId()).orElseThrow();

                product.setCount(product.getCount() + productEntranceDto.getCount());
            }
            productRepository.save(product);

            productDtos.add(productMapper.mapProductToProductDto(product));
        }

        productRepository.flush();

        return productDtos;
    }

    @Transactional
    @Override
    public List<ProductDto> replaceProduct(Integer newStock, ProductDocumentReplaceDto productDocumentReplaceDto) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductReplaceDto productDto : productDocumentReplaceDto.getProducts()) {
            Product newProduct;
            Product oldProduct = productRepository.findByArticleAndStockId(productDto.getArticle(),
                    productDocumentReplaceDto.getOldStock()).orElseThrow();

            validateCountProduct(oldProduct.getCount(), productDto.getCount());

            if (!productRepository.existsByArticleAndStockId(productDto.getArticle(),
                    newStock)) {
                newProduct = new Product(
                        oldProduct.getArticle(),
                        oldProduct.getName(),
                        productDto.getCount(),
                        oldProduct.getPriceLastBuy(),
                        oldProduct.getPriceLastSale(),
                        newStock
                );

            } else {
                newProduct = productRepository.findByArticleAndStockId(productDto.getArticle(),
                        newStock).orElseThrow();
                newProduct.setCount(newProduct.getCount() + productDto.getCount());
            }

            oldProduct.setCount(oldProduct.getCount() - productDto.getCount());

            productRepository.save(newProduct);
            productRepository.save(oldProduct);

            productDtos.add(productMapper.mapProductToProductDto(newProduct));
        }

        productRepository.flush();

        return productDtos;
    }

    @Transactional
    @Override
    public List<ProductDto> saleProduct(ProductDocumentSaleDto productDocumentSaleDto) {
        List<ProductDto> productDtos = new ArrayList<>();

        for (ProductSaleDto productSaleDto : productDocumentSaleDto.getProducts()) {
            Product product = productRepository.findByArticleAndStockId(productSaleDto.getArticle(),
                    productDocumentSaleDto.getStockId()).orElseThrow();

            validateCountProduct(product.getCount(), productSaleDto.getCount());

            product.setCount(product.getCount() - productSaleDto.getCount());
            product.setPriceLastSale(productSaleDto.getPriceSale());

            productRepository.save(product);

            productDtos.add(productMapper.mapProductToProductDto(product));
        }

        productRepository.flush();

        return productDtos;
    }

    @Transactional
    @Override
    public ProductDto editProduct(Integer id, ProductEditDto productEditDto) {
        Product product = productRepository.findById(id)
                .orElseThrow();

        product.setArticle(productEditDto.getArticle());
        product.setName(productEditDto.getName());
        product.setCount(productEditDto.getCount());

        productRepository.saveAndFlush(product);

        return productMapper.mapProductToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        return productMapper.mapProductToProductDto(products);
    }

    @Transactional
    @Override
    public String deleteProduct(Integer id) {
        productRepository.deleteById(id);

        return "Склад номер: " + id + " успешно удалён";
    }

    private void validateCountProduct(Integer countProduct, Integer countPickUpProduct) {
        if (countProduct - countPickUpProduct < 0) {
            throw new ArithmeticException("Такого кол-ва товара нет на складе!");
        }
    }
}
