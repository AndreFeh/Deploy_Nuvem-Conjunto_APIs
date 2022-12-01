package com.dio.cloudparking.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "tbl_parking")
public class Parking {
    @Id
//    @Column(name = "Id")
    private String id;
//    @Column(name = "Placa")
    private String license;
//    @Column(name = "Estado")
    private String state;
//    @Column(name = "Modelo")
    private String model;
//    @Column(name = "Cor")
    private String color;
//    @Column(name = "Data de Entrada")
    private LocalDateTime entryDate;
//    @Column(name = "Data de Saida")
    private LocalDateTime exitDate;
//    @Column(name = "Nome")
    private Double bill; // Usuario do Parking

    public Parking(String id, String license, String state, String model, String color) {
        this.id=id;
        this.state=state;
        this.license=license;
        this.model=model;
        this.color=color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Parking parking = (Parking) o;
        return id != null && Objects.equals(id, parking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
