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
public class Replace {

    @Id
    @SequenceGenerator(name = "replace_id_seq_generator",
            sequenceName = "replace_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "replace_id_seq_generator")
    private Integer id;

    private Integer stockFrom;

    private Integer stockTo;

    private Set<Product> products;
}
