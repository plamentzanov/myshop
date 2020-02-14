package com.myshop.data.entities;

import com.myshop.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "global_orders")
@Getter
@Setter
@NoArgsConstructor
public class GlobalOrder extends BaseEntity {

    @OneToMany(mappedBy = "globalOrder")
    private List<ArchivedOrder> orders;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "order_date")
    private Date orderDate;
}
