package com.dio.cloudparking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) /*So aparecer objetos nao nulos*/
public class ParkingDTO {
    private String id;
    private String license;
    private String state;
    private String model;
    private String color;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime entryDate;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime exitDate;

    private Double bill; // Usuario do Parking

}
