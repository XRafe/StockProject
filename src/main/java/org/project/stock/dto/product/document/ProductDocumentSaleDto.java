package org.project.stock.dto.product.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.project.stock.dto.product.ProductSaleDto;

import java.util.List;

@Getter
public class ProductDocumentSaleDto {

    private final List<ProductSaleDto> products;

    private final Integer stockId;

    public ProductDocumentSaleDto(@JsonProperty("products") List<ProductSaleDto> products,
                                  @JsonProperty("stockId") Integer stockId) {
        this.products = products;
        this.stockId = stockId;
    }
}
