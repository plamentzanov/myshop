package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "global_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalOrder extends BaseEntity {

    @ManyToMany
    @JoinTable(
            name = "global_orders_orders",
            joinColumns = @JoinColumn(name = "global_order_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> orders;
}
