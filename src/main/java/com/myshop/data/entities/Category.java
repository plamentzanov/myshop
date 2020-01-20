package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity {

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
