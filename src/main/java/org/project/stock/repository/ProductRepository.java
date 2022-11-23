package org.project.stock.repository;

import org.project.stock.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    boolean existsByArticleAndStockId(String article, Integer stockId);

    Optional<Product> findByArticleAndStockId(String article, Integer stockId);
}
