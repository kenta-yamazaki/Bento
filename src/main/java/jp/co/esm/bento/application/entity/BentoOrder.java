package jp.co.esm.bento.application.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;

import javax.validation.constraints.NotEmpty;



@Entity
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "bento_order")
@Data
public class BentoOrder {

    public BentoOrder(){
        super();
    }

    public BentoOrder(Integer id,String name,Integer bentoId,Integer riceId,String arrivalDate){
        super();
        this.id = id;
        this.name = name;
        this.bentoId = bentoId;
        this.riceId = riceId;
        this.arrivalDate = arrivalDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    @NotEmpty(message = "名前を入力してください。")
    private String name;

    @Column(name = "bento_id")
    private Integer bentoId;

    @Column(name = "rice_id")
    private Integer riceId;

    @Column(name = "arrival_date")
    private String arrivalDate;


//       public Integer getId(){
//        return this.id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public Integer getBento_id() {
//        return this.bentoId;
//    }
//
//    public Integer getRice_id() {
//        return this.riceId;
//    }
//
//    public String getArrival_date() {
//        return this.arrivalDate;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setBento_id(Integer bento_id) {
//        this.bento_id = bento_id;
//    }
//
//    public void setRice_id(Integer rice_id) {
//        this.rice_id = rice_id;
//    }
//
//    public void setArrival_date(String arrival_date) {
//        this.arrival_date = arrival_date;
//    }
}
