package org.project.stock.service.report.mapper;

import org.project.stock.dto.report.ReportProductDto;
import org.project.stock.entity.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReportMapper {

    public ReportProductDto mapProductToReportProductDto(Product product) {
        return new ReportProductDto(
                product.getArticle(),
                product.getName(),
                product.getPriceLastBuy(),
                product.getPriceLastSale()
        );
    }

    public List<ReportProductDto> mapProductToReportProductDto(Collection<Product> products) {
        return products.stream()
                .map(this::mapProductToReportProductDto)
                .collect(Collectors.toList());
    }
}
