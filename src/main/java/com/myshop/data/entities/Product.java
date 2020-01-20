package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_name", nullable = false)
    private Category category;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private String description;
}
