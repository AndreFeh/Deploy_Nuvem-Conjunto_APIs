package com.dio.cloudparking.service;

import com.dio.cloudparking.controller.mapper.ParkingMapper;
import com.dio.cloudparking.entity.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap<>();

    static {
        String id = getUUID();
        String id2 = getUUID();
        String id3 = getUUID();
        Parking parking = new Parking(id, "AAA-1112", "RS", "Focus", "Preto");
        Parking parking2 = new Parking(id2, "RGC-456", "PR", "Toro", "Vermelho");
        Parking parking3 = new Parking(id3, "BBF-956", "SC", "Fusion", "Azul");
        parkingMap.put(id, parking);
        parkingMap.put(id2, parking2);
        parkingMap.put(id3, parking3);
    }

    //    GET ALL
    public List<Parking> findAll() {
        /*LEMBRANDO Arrays.asList -> adiciona manualmente os itens dentro do construtor, por isso Contrutor Vazio*/
//        return Arrays.asList().add();
//        return parkingMap.values().stream().collect(Collectors.toList()); MELHORANDO
        return new ArrayList<>(parkingMap.values());
    }

    //    Convertendo a forma de aparecer o ID de 111-111-111 para 111 111 111
    private static String getUUID() {
        return UUID.randomUUID().toString()/*Convert p String*/.replace("-", " ");
    }

    //    GET ONE
    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    //    POST
    public Parking create(Parking parkingCreate) {
        /*Setar ID*/
        String uuid = getUUID();
        parkingCreate.setId(uuid); /*Seta e pega o ID veiculo, automatico*/
        parkingCreate.setEntryDate(LocalDateTime.now()); /*lança qual a data de entrada do veiculo, automatico*/
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }

    public Parking deleteId(String id) {
        return parkingMap.remove(id);
    }
}
