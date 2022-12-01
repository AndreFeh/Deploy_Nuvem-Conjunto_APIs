package com.dio.cloudparking.service;

import com.dio.cloudparking.entity.Parking;
import com.dio.cloudparking.exception.ParkingNotFoundException;
import com.dio.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {

/*    private static final Map<String, Parking> parkingMap = new HashMap<>();

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
    }*/

    private String id;

    private final ParkingRepository parkingRepo;

    public ParkingService(ParkingRepository parkingRepo) {
        this.parkingRepo = parkingRepo;
    }

    //    GET ALL
    public List<Parking> findAll() {
        /*LEMBRANDO Arrays.asList -> adiciona manualmente os itens dentro do construtor, por isso Contrutor Vazio*/
//        return Arrays.asList().add();
//        return parkingMap.values().stream().collect(Collectors.toList()); MELHORANDO
//        return new ArrayList<>(parkingMap.values());
        return parkingRepo.findAll();
    }

    //    Convertendo a forma de aparecer o ID de 111-111-111 para 111 111 111
    private static String getUUID() {
        return UUID.randomUUID().toString()/*Convert p String*/.replace("-", " ");
    }

    //    GET ONE
    public Parking findById(String id) { /*Fazer tratativa de erros*/
//        return parkingMap.get(id);
/*        Parking parking = parkingMap.get(id);
        if (parking == null){
            throw new ParkingNotFoundException(id);
        }
        return parking;*/

        return parkingRepo.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id));
    }

    //    POST
    public Parking create(Parking parkingCreate) {
        /*Setar ID*/
        String uuid = getUUID();
        parkingCreate.setId(uuid); /*Seta e pega o ID veiculo, automatico*/
        parkingCreate.setEntryDate(LocalDateTime.now()); /*lança qual a data de entrada do veiculo, automatico*/
        parkingRepo.save(parkingCreate);
        return parkingCreate;
//        return  parkingRepo.save(parkingCreate);
    }

//    public Parking deleteId(String id) {
//        deleteId(id).setExitDate(LocalDateTime.now());
//        return parkingMap.remove(id);
//    }

    public void deleteId(String id){
        Parking parkingDelete = findById(id);
/*        parkingMap.remove(id);*/
        parkingRepo.deleteById(id);
    }

    public Parking update(String id, Parking parkingUpdate) {
//        Parking updateColor = findById(id);
//        updateColor.setColor(parkingUpdate.getColor());
//        parkingMap.replace(id, updateColor); /*Pegar o ID e redefinir o objeto Color para ele*/
//        return updateColor;

        Parking update = findById(id);
        update.setColor(parkingUpdate.getColor());
        update.setLicense(parkingUpdate.getLicense());
        update.setModel(parkingUpdate.getModel());
        update.setState(parkingUpdate.getState());
        parkingRepo.save(update);
        return parkingUpdate;
    }

    public ParkingRepository getParkingRepo() {
        return parkingRepo;
    }


    public Parking exit(String id) {
        return null;
    }
}
