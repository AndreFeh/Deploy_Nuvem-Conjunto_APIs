package com.dio.cloudparking.controller.mapper;

import com.dio.cloudparking.dto.ParkingDTO;
import com.dio.cloudparking.entity.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {//Responsavel por fazer conversão em vez de converter la dentro de cada classe

    public static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO parkingDTO(Parking parking){
    /* Declarando essa classe, ele pega os atributos de parking, compara de ParkingDTO, se forem iguais ou parecidos*/
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::parkingDTO).collect(Collectors.toList());
    }
}
