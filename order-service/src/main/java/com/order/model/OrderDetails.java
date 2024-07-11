package com.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "fk_OrderId",referencedColumnName = "order_id")
    private List<OrderLineItems> orderLineItems;
}
