/** 
* 
* @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
* @since 10.04.2025
* <p> 
*  .txt dosyalarını okuyarak gezegen, araç ve kişi verilerini ayrıştıran sınıftır.
* </p> 
*/ 


package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class FileRead {
	
    public ArrayList<Planet> planets;
    public ArrayList<Person> persons;
    public ArrayList<SpaceVehicle> vehicles;
    
	public FileRead() {
		planets = new ArrayList<Planet>();
		persons = new ArrayList<Person>();
		vehicles = new ArrayList<SpaceVehicle>();
	}
	
	public void readFile(String path) throws ParseException {
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String satir;
            
            boolean isThere = false;
            
            while ((satir = br.readLine()) != null) {

        		String[] infos = satir.split("#");
        		
        		//Okuduğumuz dosyanın ismine göre ekleme yapıyor. Böylece üç ekleme işlemini tek fonksiyonda hallediyoruz.
        		if(path.contains("Gezegenler")) {
        			
        			//Regex
        			String[] dateSplit = infos[2].split("\\.");
        			
        			//Gün, ay, yıl oluşturma
        			int year = Integer.parseInt(dateSplit[2]);
        			int month = Integer.parseInt(dateSplit[1]);
        			int day = Integer.parseInt(dateSplit[0]);
        			
        			LocalDate date = LocalDate.of(year, month, day);
        			
        			//Gezegen ekleme
        			planets.add(new Planet(infos[0], Integer.parseInt(infos[1]), date, 0));
        		}

        		
        		if(path.contains("Araclar")) {

        			Planet exodusPlanet = new Planet();
        			Planet arrivalPlanet = new Planet();
        			
        			for (Planet planet : planets) {

        				if(planet.getPlanetName().equals(infos[1])) {
        					exodusPlanet = planet;
        				}
        				
        				if(planet.getPlanetName().equals(infos[2])) {
        					arrivalPlanet = planet;
        				}
        				
					}
        			

        			String[] dateSplit = infos[3].split("\\.");
        			
        			int year = Integer.parseInt(dateSplit[2]);
        			int month = Integer.parseInt(dateSplit[1]);
        			int day = Integer.parseInt(dateSplit[0]);

        			LocalDate date = LocalDate.of(year, month, day);
        			
        			vehicles.add(new SpaceVehicle(infos[0], exodusPlanet, arrivalPlanet, date, Integer.parseInt(infos[4]), 0));
        		}
        		
        		if(path.contains("Kisiler")) {
        			
        			SpaceVehicle vehicle = new SpaceVehicle();
        			
        			for (SpaceVehicle spaceVehicle : vehicles) {

        				if(spaceVehicle.getVehicleName().equals(infos[3])) {
        					vehicle = spaceVehicle;
        					vehicle.increaseNumberOfPersons();
        				}
        				
					}
        			
        			persons.add(new Person(infos[0], Integer.parseInt(infos[1]), Integer.parseInt(infos[2]), vehicle));
        		}
        		
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	public void getPersons() {
		for(Person person : persons) {
			if(person == null)	break;
			System.out.println(person.getName());
		}
	}

	
	public void getVehicle() {
		for(SpaceVehicle vehicle : vehicles) {
			if(vehicle == null)	break;
			System.out.println(vehicle.getVehicleName() + " - " + vehicle.getNumberOfPersons());
		}
	}
	
	//Tüm eklemeler yapıldıktan sonra araçların nüfus toplamlarını bulundukları gezegen nüfusuna eşitliyoruz.
	public void nufusArtir() {
		
		for(Planet planet : planets) {
			for(SpaceVehicle vehicle : vehicles) {
				if(vehicle.getExodusPlanet().getPlanetName().equals(planet.getPlanetName())) {					
					planet.increaseNumberOfPersons(vehicle.getNumberOfPersons());
				}
			}
		}
		
		for(SpaceVehicle vehicle : vehicles) {
			Time time = new Time();
			
			time.arrivalDate(vehicle);
			
			vehicle.setArrivalTimeSum(time.sonucSaatGetir());
			vehicle.setArrivalDate(time.sonucTarihGetir());
		}
	}
	
	//Yazdırma komutu
	public void writePlanets(ArrayList<Planet> planets, ArrayList<SpaceVehicle> vehicles) {
		
		System.out.println("Gezegenler:");

		for(Planet planet : planets) {
			System.out.print("\t\t---" + planet.getPlanetName() + "---");
		}
		
		System.out.println();
		System.out.print("Tarih\t\t");
		
		for(Planet planet : planets) {
			System.out.print(planet.getDateInPlanet() + "\t");
		}
		
		System.out.println();
		System.out.print("Nüfus\t\t");
		
		for(Planet planet : planets) {
			System.out.print(planet.getNumberOfPersons() + "\t\t");
		}
		
		System.out.println("\n\n");
		System.out.println("Uzay Araçlari:");
		System.out.println("Araç Adı\tDurum\t\tÇıkış\tVarış\t\tHedefe Kalan Saat\tHedefe Varacağı Tarih");
		
		for(SpaceVehicle vehicle : vehicles) {
			if(vehicle.getStatus() == "Bekliyor")
				System.out.print(vehicle.getVehicleName() + "\t\t" + vehicle.getStatus());
			
			else
				System.out.print(vehicle.getVehicleName() + "\t\t" + vehicle.getStatus() + "\t");

			int kalanSaat = (int) ((vehicle.getArrivalTimeSum() > vehicle.getArrivalTime()) ? vehicle.getArrivalTime() : vehicle.getArrivalTimeSum());
			
			if(kalanSaat == -1)	kalanSaat = 0;
			
			System.out.print("\t" + 
					vehicle.getExodusPlanet().getPlanetName() + "\t" + vehicle.getArrivalPlanet().getPlanetName()
					+ "\t\t" + kalanSaat + "\t\t\t");
			
			if(vehicle.getArrivalDate() == null)
				System.out.println("---");
			else
				System.out.println(vehicle.getArrivalDate());
			
		}
	}
}
