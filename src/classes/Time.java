/**
 * 
 * @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
 * @since 10.04.2025
 * <p> 
 *  Uzay araçlarının varış zamanlarını ve yolculuk süresini hesaplayan sınıftır.
 * </p> 
 */


package classes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Time {
	
	private LocalDate sonucTarih;
	private long sonucSaat;
	
	public Time() {
		sonucTarih = null;
		sonucSaat = 0;
	}
	
	public void arrivalDate(SpaceVehicle vehicle) {
		
		long mesafeSuresi = vehicle.getArrivalTime();
		LocalDate varisTarih = vehicle.getArrivalPlanet().getDateInPlanet();
		LocalDate cikisTarih = vehicle.getExodusPlanet().getDateInPlanet();
		long varisGunSaat = vehicle.getArrivalPlanet().getDayWithHours();
		long cikisGunSaat = vehicle.getExodusPlanet().getDayWithHours();
		LocalDate hareketTarih = vehicle.getDepartureDate();
		
		//Çıkış gezegeninin tarihi ile hareket tarihi arasındaki gün farkı
		long gunFarki = ChronoUnit.DAYS.between(hareketTarih, cikisTarih);
		
		if(gunFarki < 0)	gunFarki = -1 * gunFarki;
		
		//Bu süre boyunca ne kadar saat geçti?
		long toplamSaat = gunFarki * cikisGunSaat;
		
		//Bu geçen toplam saat, varış gezegeninde ne kadar gün etti?
		long varisGun = toplamSaat / varisGunSaat;
		//Güne çeviremediğimiz artık saatler (Örn. 1 gün 24 saat olsun. Toplam geçen saat 100 ise varış gezegeninde 4 gün 4 saat geçmiş olur
		long varisGunKalan = toplamSaat % varisGunSaat;
		
		//Kalktığında varış gezegenindeki gün
		LocalDate varisKalkis = varisTarih.plusDays(varisGun);
		
		//Yolculuk başladı ve bu yolculuğun bitince varış gezegeninde olacak tarih
		long yolculukGun = mesafeSuresi / varisGunSaat;
		long yolculukGunKalan = mesafeSuresi % varisGunSaat;
		
		//Eğer artık saatlerimiz toplanıp bir gün saatinden büyükse onu da ekliyoruz.
		yolculukGun += (yolculukGunKalan + varisGunKalan > varisGunSaat) ? 1 : 0;
		
		sonucTarih = varisKalkis.plusDays(yolculukGun);
		
		long saatFarki = ChronoUnit.DAYS.between(varisTarih, sonucTarih);
		sonucSaat = toplamSaat + mesafeSuresi;
	}
	
	public long sonucSaatGetir() {
		return sonucSaat;
	}
	
	public LocalDate sonucTarihGetir() {
		return sonucTarih;
	}
	
}
