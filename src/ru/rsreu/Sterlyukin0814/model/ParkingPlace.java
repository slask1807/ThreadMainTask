package ru.rsreu.Sterlyukin0814.model;
import java.util.Random;

public class ParkingPlace {

	private int id;
	private Car car;
	
	public int getId(){
		return id;
	}
	
	public Car getCar(){
		return car;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setCar(Car car){
		this.car = car;
	}
	
	public ParkingPlace(int id){
		this.id = id;
	}
	
	public void reserve(){
		try{
			Thread.sleep(new Random().nextInt(300));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
