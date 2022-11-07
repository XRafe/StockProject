package org.project.stock.dto.product.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.project.stock.dto.product.ProductDto;

import java.util.List;

@Getter
public class ProductDocumentReplaceDto {

    private final List<ProductDto> products;

    private final Integer oldStock;
    private final Integer newStock;

    public ProductDocumentReplaceDto(@JsonProperty("products") List<ProductDto> products,
                                     @JsonProperty("oldStock") Integer oldStock,
                                     @JsonProperty("newStock") Integer newStock) {
        this.products = products;
        this.oldStock = oldStock;
        this.newStock = newStock;
    }
}
