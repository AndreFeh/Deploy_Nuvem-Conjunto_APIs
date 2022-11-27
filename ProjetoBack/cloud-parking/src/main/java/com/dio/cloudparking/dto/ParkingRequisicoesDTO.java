package com.dio.cloudparking.dto;

import lombok.Data;

@Data
public class ParkingRequisicoesDTO {/*UNICA FUNÇÃO DELA É DAR POST COM OS ATRIBUTOS DENTRO DESSA CLASSE*/
    private String license;
    private String state;
    private String model;
    private String color;
}
