package com.Bento.Bento.application.counter;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name="test_table")
public class Counter {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public String getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getAge(){
        return this.age;
    }
}
