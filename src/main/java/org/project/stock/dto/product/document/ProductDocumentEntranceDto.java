package org.project.stock.dto.product.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.project.stock.dto.product.ProductEntranceDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ProductDocumentEntranceDto {

    private final List<ProductEntranceDto> products;

    @NotNull
    private final Integer stockId;

    public ProductDocumentEntranceDto(@JsonProperty("products") List<ProductEntranceDto> products,
                                      @JsonProperty("stockId") Integer stockId) {
        this.products = products;
        this.stockId = stockId;
    }
}
