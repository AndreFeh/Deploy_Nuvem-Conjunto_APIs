package com.dio.cloudparking.controller;

import com.dio.cloudparking.controller.mapper.ParkingMapper;
import com.dio.cloudparking.dto.ParkingDTO;
import com.dio.cloudparking.entity.Parking;
import com.dio.cloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    /*PRIVATE É DEFAULT, NAO NECESSARIO DECLARAR*/
    private final ParkingService parkingService;
    final ParkingMapper parkingMapper;

    /*Construtor criado Automatico*/
    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    //    Listar todos os estacionados nas vagas
    @GetMapping
    public List<ParkingDTO> findAll(){ /*Como Agora Editado para ParkingDTO, reeditar o retorno*/
//        return parkingService.findAll();
        List<Parking>parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return result;
    }
}
