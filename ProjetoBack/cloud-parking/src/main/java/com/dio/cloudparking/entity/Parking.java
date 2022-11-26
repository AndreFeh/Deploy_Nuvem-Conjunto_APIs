package com.dio.cloudparking.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Parking {
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill; // Usuario do Parking

    public Parking(String id, String license, String state, String model, String color) {
        this.id=id;
        this.state=state;
        this.license=license;
        this.model=model;
        this.color=color;
    }
    public Parking(){}
}
