package org.project.stock.entity.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.stock.entity.Product;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entrance {

    @Id
    @SequenceGenerator(name = "entrance_id_seq_generator",
            sequenceName = "entrance_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "entrance_id_seq_generator")
    private Integer id;

    private Integer stockId;

    private Set<Product> products;
}
