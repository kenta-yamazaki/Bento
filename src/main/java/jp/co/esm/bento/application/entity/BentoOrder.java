package jp.co.esm.bento.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "bento_order")
public class BentoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int order_id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "order_name")
    private String order_name;

    @Column(name = "order_menu")
    private int order_menu;

    @Column(name = "order_rice")
    private int order_rice;

    @Column(name = "order_arrival")
    private Date order_arrival;
}
