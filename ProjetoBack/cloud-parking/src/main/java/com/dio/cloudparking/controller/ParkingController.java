package com.dio.cloudparking.controller;

import com.dio.cloudparking.controller.mapper.ParkingMapper;
import com.dio.cloudparking.dto.ParkingDTO;
import com.dio.cloudparking.dto.ParkingRequisicoesDTO;
import com.dio.cloudparking.entity.Parking;
import com.dio.cloudparking.service.ParkingService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    /*TODO/-> SÓ LIST É UMA MÁ PRATICA, ALTERAR PARA ResponseEntity*/
//    public List<ParkingDTO> findAll(){ /*Como Agora Editado para ParkingDTO, reeditar o retorno*/
    public ResponseEntity<List<ParkingDTO>> findAll(){
//        return parkingService.findAll();
        List<Parking>parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
//        return result; /*Para evitar aparecer campos nulos, na DTO, colocar a Annotation JSON...*/
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO>findById(@PathVariable String id){
        Parking parkingId = parkingService.findById(id);
        ParkingDTO /*Retorna um ...*/ result = parkingMapper.convertForParkingDTO(parkingId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO>create(@RequestBody ParkingRequisicoesDTO parkingRequisicoesDTO){ /*Consultar no Word, oq foi feito*/
//        Para fazer essa criação, quero converter um DTO em um Parking, para isso, ou fazer a classe Convert, ou...
        Parking parkingCreate = parkingMapper.parkingCreateDTO(parkingRequisicoesDTO); //Converter para DTO
        Parking parkingPost = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.convertForParkingDTO(parkingPost); //Converter para Parking
//        return ResponseEntity.ok(result);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @DeleteMapping("/{id}")
//    HAVIA COLOCADO COM @RequestParam, com esse Annotation ele pede esse parametro no BODY FORM-DATA
    public ResponseEntity<ParkingDTO> deleteID(@PathVariable String id){
        Parking parkingDelete = parkingService.deleteId(id);
        ParkingDTO result = parkingMapper.convertForParkingDTO(parkingDelete);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ParkingDTO> putID(@PathVariable String id){
//        Parking parkingPut = parkingService.putId(id);
//        ParkingDTO result = parkingMapper.convertForParkingDTO(parkingPut);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
//    }
}
