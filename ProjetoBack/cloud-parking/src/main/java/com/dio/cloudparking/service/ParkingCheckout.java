package com.dio.cloudparking.service;

import com.dio.cloudparking.entity.Parking;

import java.time.LocalDateTime;

public class ParkingCheckout {
    public static Double getBill(Parking parking){
        return getBill(parking.getEntryDate(),parking.getExitDate());

    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        return null;
    }
}
