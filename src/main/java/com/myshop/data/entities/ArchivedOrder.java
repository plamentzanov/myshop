package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "archived_orders")
@Getter
@Setter
@NoArgsConstructor
public class ArchivedOrder extends BaseEntity {

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "global_order_id", nullable = false)
    private GlobalOrder globalOrder;

    @Column(name = "is_completed", columnDefinition = "boolean default false")
    private boolean isCompleted;
}
