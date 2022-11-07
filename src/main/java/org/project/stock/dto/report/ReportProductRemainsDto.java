package org.project.stock.dto.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReportProductRemainsDto {

    private final String article;

    private final String name;

    private final Integer count;
}
