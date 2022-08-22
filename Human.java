
public class Human {
private String name;

	public Human(String name) {
		this.name=name;
	
	}
	public static Human getChristina() {	
		String nameof="Christina";
		Human Christina = new Human(nameof);
		return Christina;
	}
	public void printName() {
		System.out.println(this.name);
	}
}
