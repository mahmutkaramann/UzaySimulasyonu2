/**
 * 
 * @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
 * @since 10.04.2025
 * <p> 
 *  Gezegenlerin tarih, saat ve nüfus bilgilerini tutan sınıftır.
 * </p> 
 */


package classes;

import java.time.LocalDate;
import java.util.Date;

public class Planet {

	private String planetName;
	private int dayWithHours;
	private int hourCount;
	private LocalDate dateInPlanet;
	private int numberOfPersons;
	
	public Planet(String planetName, int dayWithHours, LocalDate dateInPlanet, int numberOfPersons) {
		super();
		this.planetName = planetName;
		this.dayWithHours = dayWithHours;
		this.dateInPlanet = dateInPlanet;
		this.numberOfPersons = numberOfPersons;
		this.hourCount = 0;
	}
	public Planet() {
		
	}
	
	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public int getDayWithHours() {
		return dayWithHours;
	}

	public void setDayWithHours(int dayWithHours) {
		this.dayWithHours = dayWithHours;
	}

	public LocalDate getDateInPlanet() {
		return dateInPlanet;
	}

	public void setDateInPlanet(LocalDate dateInPlanet) {
		this.dateInPlanet = dateInPlanet;
	}
	
	public int getNumberOfPersons() {
		return numberOfPersons;
	}
	
	public void setNumberOfPersons(int numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}
	
	public void increaseNumberOfPersons(int count) {
		numberOfPersons += count;
	}
	
	public void decreaseNumberOfPersons(int count) {
		numberOfPersons -= count;
	}
	
	public void increaseHourCount() {
		hourCount++;
		
		if(hourCount == dayWithHours) {
			hourCount = 0;
			dateInPlanet = dateInPlanet.plusDays(1);
		}
		
	}
}
