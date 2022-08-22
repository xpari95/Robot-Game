/**
 * Author: Christina Pari
 * Written: 03/12/2021
 * <p>
 * This program initializes a game where "snakes" are initialized at a random position within a grid and informs the user and visualises
 * the movements of the snakes and whether the snakes collided, escaped or where trapped.
 **/
//

import java.util.Random;
import java.util.Arrays;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class RandomRobots {

    public static void main(String[] args) {
        int nRobots = Integer.parseInt(args[0]);
        int gridSize = Integer.parseInt(args[1]);
        StdDraw.setXscale(0.0, gridSize);
        StdDraw.setYscale(0.0, gridSize);
        StdDraw.clear();
        StdDraw.setPenColor(Color.GRAY.brighter());
        StdDraw.setPenRadius(0.25 / gridSize);

        for (int i = 0; i <= gridSize; i++)  //Creation of the square grid via lines
            StdDraw.line(i, 0, i, gridSize);
        for (int j = 0; j <= gridSize; j++)
            StdDraw.line(0, j, gridSize, j);

        Robot[] robotsArr = new Robot[nRobots];  //This array contains all the robots which are going to participate
        for (int i = 0; i < nRobots; i++) {          //It creates the name R0,R1.... and it saves it into the array i created before and it puts 
            String name = "R" + Integer.toString(i);  //them on the grid in random spots difference from each other
            int x_init = (int) (Math.random() * gridSize);
            int y_init = (int) (Math.random() * gridSize);
            robotsArr[i] = new Robot(name, x_init, y_init, gridSize);
        }

        System.out.println("ROBOTS AT INITIAL STATE");
        System.out.println();
        for (int i = 0; i < nRobots; i++) {
            System.out.println(robotsArr[i].toString()); //I call the method to toString to give me back the output i created inside of it
            //for the beginning and i it escaped
        }


        System.out.println("ROBOTS AT FINAL STATE");
        System.out.println();

        for (Robot robot : robotsArr) {
            while (robot.move())            //I call again the toString method for the second case of the output i created and
                for (Robot anotherRobot : robotsArr) {
                    robot.collides(anotherRobot);//check if two robots has collided
                }
            System.out.println(robot.toString());
            System.out.println();
         }
    }

    private void drawMove(Robot robot) {
        // draw the last move of the robot on the board
        StdDraw.setPenColor(robot.getColor());
        StdDraw.setPenRadius(0.25 / robot.getSize());
        int[][] path = robot.getPath();
        int[] currentPosition = robot.getPos();
        int[] previousPosition = path[path.length - 1];
        StdDraw.line(previousPosition[0], previousPosition[1], currentPosition[0], currentPosition[1]);

        StdDraw.setPenRadius(0.25 / robot.getSize());
        if (robot.hasEscaped()) {
            StdDraw.setPenColor(Color.GREEN);
            StdDraw.point(currentPosition[0], currentPosition[0]);
        } else if (robot.isTrapped()) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(currentPosition[0], currentPosition[0]);
        } else if (!robot.isAlive()) {
            StdDraw.setPenColor(Color.RED);
            StdDraw.point(currentPosition[0], currentPosition[0]);
        }
    }

    private void drawStartingDot(Robot robot) {
        int [][] path = robot.getPath();
        StdDraw.setPenColor(Color.YELLOW);      //It draws a small yellow dot at the starting psoition of each robot
        StdDraw.setPenRadius(0.25/robot.getSize());
        StdDraw.point(path[0][0],path[0][1]);
    }
}


//		for (int i = 0; i < nRobots; i++) {
//			System.out.println(" Robot robot1 is positioned at " + Arrays.toString(robot1.getPos()));  
//			System.out.println("The robot is alive");
//			System.out.println("Robot robot2 is positioned at " + Arrays.toString(robot2.getPos()));
//			System.out.println("The robot is alive");
//			robot1.move();
//			if (robot2.collides(robot1)) {
//				System.out.println("Oh no, they collide!");
//				int[] step = robot1.getPos();
//
//				StdDraw.setPenColor(StdDraw.BLACK);
//				StdDraw.filledCircle(step[0], step[1], .5);
//				break;
//
//			}
//			
//			robot2.move();
//			if (robot1.collides(robot2)) {
//				System.out.println("Oh no, they collide!");
//				int[] step = robot2.getPos();
//
//				StdDraw.setPenColor(StdDraw.BLACK);
//				StdDraw.filledCircle(step[0], step[1], .5);
//				break;
//			}
//
//			int[] step = robot1.getPos();
//			StdDraw.setPenColor(StdDraw.BLUE);
//			StdDraw.filledCircle(step[0], step[1], .5);
//			int[] step2 = robot2.getPos();
//			StdDraw.setPenColor(StdDraw.RED);
//			StdDraw.filledCircle(step2[0], step2[1], .5);
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}
//
//}
