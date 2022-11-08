package org.project.stock.service.report.impl;

import lombok.RequiredArgsConstructor;
import org.project.stock.dto.report.ReportProductDto;
import org.project.stock.dto.report.ReportProductRemainsDto;
import org.project.stock.entity.Product;
import org.project.stock.service.report.ProductReport;
import org.project.stock.service.report.mapper.ReportMapper;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductReportImpl implements ProductReport {

    private final EntityManager em;
    private final ReportMapper reportMapper;

    @Override
    public List<ReportProductDto> getProductsByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);

        cq.select(root).where(cb.like(root.get("name"), name + "%"));

        Query query = em.createQuery(cq);

        return reportMapper.mapProductToReportProductDto(query.getResultList());
    }

    @Override
    public List<ReportProductRemainsDto> getRemainsProducts() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<ReportProductRemainsDto> cq = cb.createQuery(ReportProductRemainsDto.class);
        Root<Product> root = cq.from(Product.class);

        cq.multiselect(root.get("article"), root.get("name"), cb.sum(root.get("count")))
                .groupBy(root.get("article"), root.get("name"));

        Query query = em.createQuery(cq);

        return query.getResultList();
    }
}
