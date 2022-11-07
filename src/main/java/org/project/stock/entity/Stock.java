package org.project.stock.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Stock {

    @Id
    @SequenceGenerator(name = "stock_id_seq_generator",
            sequenceName = "stock_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "stock_id_seq_generator")
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "stock")
    private Set<Product> products;

    public Stock(String name) {
        this.name = name;
    }
}
