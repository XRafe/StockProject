package org.project.stock.dto.product.document;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.project.stock.dto.product.ProductReplaceDto;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class ProductDocumentReplaceDto {

    private final List<ProductReplaceDto> products;

    @NotNull
    private final Integer oldStock;

    public ProductDocumentReplaceDto(@JsonProperty("products") List<ProductReplaceDto> products,
                                     @JsonProperty("oldStock") Integer oldStock) {
        this.products = products;
        this.oldStock = oldStock;
    }
}
