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
@AllArgsConstructor
@NoArgsConstructor
public class BentoOrder {

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
