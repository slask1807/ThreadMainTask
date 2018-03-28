package ru.rsreu.Sterlyukin0814.model;
import java.util.ArrayList;
import java.util.List;

import com.prutzkow.resourcebundledemo.Resourcer;

public class Parking {
	private final static int MAX_ATTEMPTS_COUNT = 2;
	private int count = 0;
	
	List<ParkingPlace> places = new ArrayList<ParkingPlace>();

	public Parking(List<ParkingPlace> places) {
		this.places.addAll(places);
	}

	public Parking() {
	}

	public synchronized ParkingPlace tryReserve(Car car, int waitingTime) {
		for (ParkingPlace place : places) {
			if (searchFreeParkingPlace(place, car)) {
				return place;
			}
		}

		try {
			count++;
			if (checkAttemptsCount(count, waitingTime, car)) {
				return null;
			}else{
				return tryReserve(car, waitingTime);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return null;
	}

	public synchronized void endReserve(ParkingPlace place) {
		place.setCar(null);
		places.add(place);
		notify();
	}

	private synchronized boolean checkAttemptsCount(int attemptsCount,
			int waitingTime, Car car) throws InterruptedException {
		if (count == MAX_ATTEMPTS_COUNT) {
			System.out.printf(Resourcer.getString("car.left"),
					car.getId());

			return true;

		} else {
			System.out.printf(Resourcer.getString("car.waiting"),
					car.getId());
			wait(waitingTime);
		}
		return false;

	}

	private boolean searchFreeParkingPlace(ParkingPlace place, Car car) {
		if (place.getCar() == null) {
			place.setCar(car);
			places.remove(place);
			return true;
		}
		return false;
	}
}
