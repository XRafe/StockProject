package org.project.stock.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_seq_generator",
            sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "product_id_seq_generator")
    private Integer id;

    private String article;

    private String name;

    private Long priceLastBuy;

    private Long priceLastSale;

    @Column(name = "stock_id")
    private Integer stockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id", insertable = false, updatable = false)
    private Stock stock;

    public Product(String article,
                   String name,
                   Long priceLastBuy,
                   Long priceLastSale) {
        this.article = article;
        this.name = name;
        this.priceLastBuy = priceLastBuy;
        this.priceLastSale = priceLastSale;
    }
}
