/**
 * 
 * @author Mahmut KARAMAN mahmut.karaman@ogr.sakarya.edu.tr
 * @since 10.04.2025
 * <p> 
 *  Uzay araçlarında taşınan kişilerin bilgilerini ve ömür takibini yapan sınıftır.
 * </p> 
 */

package classes;

public class Person {
	
	private String name;
	private int age;
	private int remainTime;
	private SpaceVehicle vehicle;
	private boolean isAlive;
	
	public Person(String name, int age, int remainTime, SpaceVehicle vehicle) {
		super();
		this.name = name;
		this.age = age;
		this.remainTime = remainTime;
		this.vehicle = vehicle;
		this.isAlive = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}

	public SpaceVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(SpaceVehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public void decreaseRemainTime() {
		remainTime--;
		
		if(remainTime == 0)	isAlive = false;
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}
}
