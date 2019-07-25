package jp.co.esm.bento.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "bento_order")
@Data
public class BentoOrder {

    public BentoOrder(){
    }
    public BentoOrder(int id,String name,Integer bento_id,Integer rice_id,String arrival_date){
        this.id = id;
        this.name = name;
        this.bento_id = bento_id;
        this.rice_id = rice_id;
        this.arrival_date = arrival_date;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "名前を入力してください。")
    private String name;

    @Column(name = "bento_id")
    private Integer bento_id;

    @Column(name = "rice_id")
    private Integer rice_id;

    @Column(name = "arrival_date")
    private String arrival_date;
}
