package org.project.stock.service.report;

import org.project.stock.dto.report.ReportProductDto;
import org.project.stock.dto.report.ReportProductRemainsDto;

import java.util.List;

public interface ProductReport {

    List<ReportProductDto> getProductsByName(String name);

    List<ReportProductRemainsDto> getRemainsProducts();
}
