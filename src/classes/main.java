/**
 * 
 * @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
 * @since 10.04.2025
 * <p> 
 *  Programın başlangıç noktası olup, dosyaları okuyup simülasyonu başlatan ana sınıftır.
 * </p> 
 */

package classes;

import java.io.IOException;
import java.text.ParseException;

public class main {
	
	 public static void temizle() throws IOException {
	        try {
	        	//Burada bekleme süresi var (ms üzerinden). 1 saniye için 1000 yazmak gerek (Ama büyük dosyalarda yavaş oluyor, tavsiyem dosya çok büyükse 1-10, değilse 10-100)
	            Thread.sleep(10);
	            
	            //Terminalde silme komutu
	            if (System.getProperty("os.name").contains("Windows"))
	                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	            else
	                System.out.print("\033[H\033[2J");
	            System.out.flush();
	            
	            
	            /*
	             * Yukarıdaki silme komutu eclipse terminalinde çalışmadığı için yazdım. Şu anda silebilirsin.
	             * 
	            for(int i = 0; i < 50; i++) {
	            	System.out.println();;
	            }*/
	            
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	
	public static void main(String[] args) throws ParseException, InterruptedException, IOException {
		
		//Döngüyü sonlandırma değişkeni
		boolean isOver = false;
		
		FileRead fileRead = new FileRead();
		
		fileRead.readFile("Gezegenler.txt");
		fileRead.readFile("Araclar.txt");
		fileRead.readFile("Kisiler.txt");
		
		fileRead.writePlanets(fileRead.planets, fileRead.vehicles);
		
		//Gezegendeki insan sayısını aktarır
		fileRead.nufusArtir();
		
		Simulation simulation = new Simulation(fileRead.persons, fileRead.vehicles, fileRead.planets);
		
		int toplamSure = 0;
		
		while(!isOver) {
			isOver = true;
			simulation.getSimulation();
			temizle();
			
			fileRead.writePlanets(simulation.getPlanets(), simulation.getVehicles());
			
			for(SpaceVehicle vehicle : simulation.getVehicles()) {
				
				if(vehicle.getArrivalTimeSum() != -1) {
					isOver = false;
					break;
				}
			}
		}
		
	}


}
