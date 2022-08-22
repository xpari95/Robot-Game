import java.util.Random;

public class Temp {
	public static void main(String[] args) {
		System.out.println(SmallRobot.getMyInventorsName());

		SmallRobot oneSmallRobot = new SmallRobot("Karol");

		System.out.println(oneSmallRobot.getName());

		SmallRobot[] smallRobots = new SmallRobot[3];
		for(int i = 0; i<3; i++){
			smallRobots[i] = new SmallRobot("Christina" + (char) i);
		}

		for(SmallRobot robot: smallRobots) {
			System.out.println(robot.getName());
		}

		String[] towns = {"Limassol", "Nicosia", "Paphos"};
		for (String town : towns) {
			System.out.println("Greatest one is " + town);
		}

		for (String town : towns) {
			System.out.println("Greatest one is " + town);
		}
	}

}
