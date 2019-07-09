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
    @Column(name = "id")
    private int id;

    @Column(name = "order_date")
    private Date order_date;

    @Column(name = "name")
    private String name;

    @Column(name = "bento_id")
    private int bento_id;

    @Column(name = "rice_id")
    private int rice_id;

    @Column(name = "arrival")
    private Date arrival;
}
