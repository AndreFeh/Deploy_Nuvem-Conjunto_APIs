package com.dio.cloudparking.controller;

import com.dio.cloudparking.controller.mapper.ParkingMapper;
import com.dio.cloudparking.dto.ParkingDTO;
import com.dio.cloudparking.dto.ParkingRequisicoesDTO;
import com.dio.cloudparking.entity.Parking;
import com.dio.cloudparking.service.ParkingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@Api("Parking Controller")
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
    @ApiOperation("Get All")
    public ResponseEntity<List<ParkingDTO>> findAll(){
//        return parkingService.findAll();
        List<Parking>parkingList = parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
//        return result; /*Para evitar aparecer campos nulos, na DTO, colocar a Annotation JSON...*/
        return ResponseEntity.ok(result);
    }

    @ApiOperation("Get ID")
    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO>findById(@PathVariable String id){
        Parking parkingId = parkingService.findById(id); // quando buscar um Id inexistente, fazer a tratativa de erro em Service
        ParkingDTO /*Retorna um ...*/ result = parkingMapper.convertForParkingDTO(parkingId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @ApiOperation("Post Parking")
    public ResponseEntity<ParkingDTO>create(@RequestBody ParkingRequisicoesDTO parkingRequisicoesDTO){ /*Consultar no Word, oq foi feito*/
//        Para fazer essa criação, quero converter um DTO em um Parking, para isso, ou fazer a classe Convert, ou...
        Parking parkingCreate = parkingMapper.parkingCreateDTO(parkingRequisicoesDTO); //Converter para DTO
        Parking parkingPost = parkingService.create(parkingCreate);
        ParkingDTO result = parkingMapper.convertForParkingDTO(parkingPost); //Converter para Parking
//        return ResponseEntity.ok(result);
//        return ResponseEntity.status(HttpStatus.OK).body(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


//    HAVIA COLOCADO COM @RequestParam, com esse Annotation ele pede esse parametro no BODY FORM-DATA
//    Nao tem por que deixar o Response Entity com parking DTO pois ele vai retornar um objeto deletado = obj vazio
//
//    public ResponseEntity/*<ParkingDTO>*/ deleteID(@PathVariable String id){
//        Parking parkingDelete = parkingService.deleteId(id);
//        ParkingDTO result = parkingMapper.convertForParkingDTO(parkingDelete);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
//    }

    //REFAZENDO
    @DeleteMapping("/{id}")
    @ApiOperation("Delete Parking")
    public ResponseEntity deleteID(@PathVariable String id){
        parkingService.deleteId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @ApiOperation("Put Parking")
    public ResponseEntity<ParkingDTO>update(@PathVariable String id, @RequestBody ParkingRequisicoesDTO parkingRequisicoesDTO){ /*Consultar no Word, oq foi feito*/
        Parking parkingUpdate = parkingMapper.parkingCreateDTO(parkingRequisicoesDTO); //Converter para DTO
        Parking parkingPut = parkingService.update(id, parkingUpdate);
        ParkingDTO result = parkingMapper.convertForParkingDTO(parkingPut); //Converter para Parking
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO>exit(@PathVariable String id){
        Parking parking = parkingService.exit(id);
        return ResponseEntity.ok(parkingMapper.convertForParkingDTO(parking));
    }
}
