package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_name", nullable = false)
    private Category category;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;

    @OneToMany(mappedBy = "product")
    private List<Order> orders;
}
