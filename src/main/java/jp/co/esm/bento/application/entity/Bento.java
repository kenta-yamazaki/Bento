package jp.co.esm.bento.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "bento")
public class Bento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

}
