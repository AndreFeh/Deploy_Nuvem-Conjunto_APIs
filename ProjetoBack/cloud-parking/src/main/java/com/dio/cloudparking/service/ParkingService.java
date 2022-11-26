package com.dio.cloudparking.service;

import com.dio.cloudparking.entity.Parking;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id = getUUID();
        Parking parking = new Parking(id, "AAA-1112","BR", "Focus", "Preto");
        parkingMap.put(id, parking);
    }

    public List<Parking>findAll(){
        /*LEMBRANDO Arrays.asList -> adiciona manualmente os itens dentro do construtor, por isso Contrutor Vazio*/
//        return Arrays.asList().add();
//        return parkingMap.values().stream().collect(Collectors.toList()); MELHORANDO
        return new ArrayList<>(parkingMap.values());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString()/*Convert p String*/.replace("-"," ");
    }


}
