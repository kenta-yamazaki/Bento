package jp.co.esm.bento.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "rice")
public class Rice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "availability")
    private String availability;

    @Column(name = "price")
    private int price;
}
