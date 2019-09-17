package ru.ncedu.lebedev.deliveryService.deliveryServiceDatabase.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomCoordinates {

    private static final double MAX_EAST_LATITUDE = 55.757854;
    private static final double MAX_WEST_LATITUDE = 55.761718;
    private static final double MAX_NORTH_LONGITUDE = 37.579534;
    private static final double MAX_SOUTH_LONGITUDE = 37.638586;

    public static Double getRandomLatitude() {
        return ThreadLocalRandom.current().nextDouble(MAX_EAST_LATITUDE, MAX_WEST_LATITUDE);
    }

    public static Double getRandomLongitude() {
        return ThreadLocalRandom.current().nextDouble(MAX_NORTH_LONGITUDE, MAX_SOUTH_LONGITUDE);
    }
}
