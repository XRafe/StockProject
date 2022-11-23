package org.project.stock.service.mapper;

import org.project.stock.dto.product.ProductDto;
import org.project.stock.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDto mapProductToProductDto(Product product) {
        return new ProductDto(
                product.getArticle(),
                product.getName(),
                product.getCount(),
                product.getPriceLastBuy(),
                product.getPriceLastSale(),
                product.getStockId()
        );
    }

    public List<ProductDto> mapProductToProductDto(Collection<Product> products) {
        return products.stream()
                .map(this::mapProductToProductDto)
                .collect(Collectors.toList());
    }
}
