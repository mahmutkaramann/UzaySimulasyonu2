/**
 * 
 * @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
 * @since 10.04.2025
 * <p> 
 *  Uzay araçları ve kişilerin simülasyon sürecini yöneten sınıftır.
 * </p> 
 */


package classes;

import java.util.ArrayList;
import java.util.Vector;

public class Simulation {

    private ArrayList<Person> persons;
    private ArrayList<SpaceVehicle> vehicles;
    private ArrayList<Planet> planets;
    
	public Simulation(ArrayList<Person> persons, ArrayList<SpaceVehicle> vehicles, ArrayList<Planet> planets) {
		super();
		this.persons = persons;
		this.vehicles = vehicles;
		this.planets = planets;
	}
	
	public ArrayList<Person> getPersons() {
		return persons;
	}
	
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	public ArrayList<SpaceVehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(ArrayList<SpaceVehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public ArrayList<Planet> getPlanets() {
		return planets;
	}

	public void setPlanets(ArrayList<Planet> planets) {
		this.planets = planets;
	}

	public void getSimulation() {
		
		for(Person person : persons) {
			
			//Kişi öldü mü
			if(!person.getIsAlive())	continue;
			
			//Kişinin zamanından bir saat çal
			person.decreaseRemainTime();
			
			//Died one person
			if(!person.getIsAlive()) {
				
				//Araçtan bir kişi eksilt.
				person.getVehicle().decreaseNumberOfPersons();
				
				//Eğer şu anda bekliyorsa beklediği gezegenden bir kişi eksilt
				if("Bekliyor".equals(person.getVehicle().getStatus()))
					person.getVehicle().getExodusPlanet().decreaseNumberOfPersons(1);
				
				//Eğer toplam süre varış süresinden fazlaysa şu anda bekliyordur. Bu yüzden yine kaldığımız gezegenden bir kişi eksilt
				if("İmha".equals(person.getVehicle().getStatus()) && person.getVehicle().getArrivalTimeSum() > person.getVehicle().getArrivalTime()) {
					person.getVehicle().getExodusPlanet().decreaseNumberOfPersons(1);
				}
				
				if("Vardı".equals(person.getVehicle().getStatus())) {
					person.getVehicle().getArrivalPlanet().decreaseNumberOfPersons(1);
				}
				
			}
			
		}
		
		for(SpaceVehicle vehicle : vehicles) {
			
			//Eğer tam olarak vardıysa vardığı gezegene araçtaki kişi sayısı kadar ekle
			if(vehicle.getArrivalTimeSum() == 0 && "Vardı".equals(vehicle.getStatus())) {
				vehicle.getArrivalPlanet().increaseNumberOfPersons(vehicle.getNumberOfPersons());
				vehicle.setArrivalTimeSum(-1);
			}
			
			//Eğer imha veya varmışsa varış tarihini null yap.
			if("İmha".equals(vehicle.getStatus()))	{
				vehicle.setArrivalTimeSum(-1);
				vehicle.setArrivalDate(null);				
				continue;
			}
			
			//Eğer araç vardıysa yolculuk bitmiştir
			if("Vardı".equals(vehicle.getStatus())){
				vehicle.setArrivalTimeSum(-1);		
				continue;
			}
			
			//Bunlar eşitse bil ki yolculuk başlamıştır. Bu yüzden çıkış gezegenindeki nüfus azalır.
			if(vehicle.getArrivalTime() == vehicle.getArrivalTimeSum()) {
				vehicle.setStatusOnWay();
				vehicle.getExodusPlanet().decreaseNumberOfPersons(vehicle.getNumberOfPersons());
				vehicle.okuduMu = true;
			}
			
			//Yolculuk bitti
			else if(vehicle.getArrivalTimeSum() == 0) {
				vehicle.setStatusFinished();
			}
			
			//Zamanı azalt
			vehicle.decreaseArrivalTimeSum();
		}
		
		for(Planet planet : planets) {
			
			if(planet == null)	break;
		
			planet.increaseHourCount();
			
		}
		
		
	}

}
