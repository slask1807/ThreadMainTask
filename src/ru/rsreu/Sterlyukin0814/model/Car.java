package ru.rsreu.Sterlyukin0814.model;
import com.prutzkow.resourcebundledemo.Resourcer;

public class Car implements Runnable{
	
	private static final int WAITING_TIME = 100;
	private int id;
	private Parking parking;
	
	public int getId(){
		return id;
	}
	
	public Car(int id, Parking parking){
		this.id = id;
		this.parking = parking;
	}
	
	public void run(){
		ParkingPlace parkingPlace = null;
		try {
			System.out.printf(Resourcer.getString("car.searchingPlace"),
					this.id);
			parkingPlace = parking.tryReserve(this, WAITING_TIME);
			
			if(parkingPlace == null){
				return;
			}
		
			System.out.printf(Resourcer.getString("car.reservedPlace"),
					parkingPlace.getId(), this.id);

			parkingPlace.reserve();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (parkingPlace != null) {
				
				System.out.printf(Resourcer.getString("car.endReserve"),
						this.id, parkingPlace.getId());
				parking.endReserve(parkingPlace);
			}
		}
	}
}
