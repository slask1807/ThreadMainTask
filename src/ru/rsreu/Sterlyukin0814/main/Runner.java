package ru.rsreu.Sterlyukin0814.main;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.Sterlyukin0814.model.*;

public class Runner {

	private final static int NUMBER_OF_PARKING_PLACES = 2;
	private final static int NUMBER_OF_CARS = 3;

	public static void main(String[] args) {
		List<ParkingPlace> places = new ArrayList<ParkingPlace>();
		for (int i = 1; i <= NUMBER_OF_PARKING_PLACES; i++) {
			places.add(new ParkingPlace(i));
		}
		Parking parking = new Parking(places);
		for (int i = 1; i <= NUMBER_OF_CARS; i++) {
			Thread thread = new Thread(new Car(i, parking));
			thread.start();
		}
	}

}
