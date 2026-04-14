/**
 * 
 * @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
 * @since 10.04.2025
 * <p> 
 *  Uzay araçlarının hareket, durum ve yolcu bilgilerini yöneten sınıftır.
 * </p> 
 */


package classes;

import java.time.LocalDate;
import java.util.Date;

public class SpaceVehicle {
	
	private String vehicleName;
	private Planet exodusPlanet;
	private Planet arrivalPlanet;
	private LocalDate departureDate;
	private LocalDate arrivalDate;
	
	//Bu iki kavram arasındaki fark
	private int arrivalTime;		//Gezegenden çıktıktan sonra diğer gezegene kalan saat
	private long arrivalTimeSum;	//Program başladıktan itibaren diğer gezegene kalan saat
	
	private int numberOfPersons;
	private String status;
	
	public boolean okuduMu;

	public SpaceVehicle(String vehicleName, Planet exodusPlanet, Planet arrivalPlanet, LocalDate departureDate,
			int arrivalTime, int numberOfPersons) {
		super();
		this.vehicleName = vehicleName;
		this.exodusPlanet = exodusPlanet;
		this.arrivalPlanet = arrivalPlanet;
		this.departureDate = departureDate;
		this.arrivalTime = arrivalTime;
		this.numberOfPersons = numberOfPersons;
		this.status = "Bekliyor";
		this.arrivalTimeSum = 0;
		this.arrivalDate = null;
		this.okuduMu = false;
	}

	public SpaceVehicle() {
		
	}
	
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Planet getExodusPlanet() {
		return exodusPlanet;
	}
	public void setExodusPlanet(Planet exodusPlanet) {
		this.exodusPlanet = exodusPlanet;
	}
	public Planet getArrivalPlanet() {
		return arrivalPlanet;
	}
	public void setArrivalPlanet(Planet arrivalPlanet) {
		this.arrivalPlanet = arrivalPlanet;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	public int getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}
	
	public void increaseNumberOfPersons() {
		numberOfPersons++;
	}
	
	public void decreaseNumberOfPersons() {
		//Kişi sayısı azalt
		numberOfPersons--;
		
		//Eğer sıfırsa bil ki araç imha olmuştur
		if(numberOfPersons == 0)	setStatusDestruction();
	}

	public String getStatus() {
		return status;
	}

	public void setStatusWaiting() {
		this.status = "Bekliyor";
	}

	public void setStatusOnWay() {
		this.status = "Yolda";
	}

	public void setStatusFinished() {
		this.status = "Vardı";
	}

	public void setStatusDestruction() {
		this.status = "İmha";
	}

	public void setArrivalTimeSum(long arrivalTimeSum) {
		this.arrivalTimeSum = arrivalTimeSum;
	}
	
	public void decreaseArrivalTimeSum() {
		arrivalTimeSum--;
		
		//Eğer sıfırsa yolculuk bitmiştir
		if(arrivalTimeSum == 0)				setStatusFinished();
		
		//Eğer bu değişkenler eşitse araç kalkmaya hazır
		else if(arrivalTimeSum == arrivalTime) {
			setStatusOnWay();
		}
	}
	
	public long getArrivalTimeSum() {
		return arrivalTimeSum;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
}
