/**
 * Author: Christina Pari
 * Written: 03/12/2021
 * <p>
 * This program initializes a game where "snakes" are initialized at a random position within a grid and informs the user and visualises
 * the movements of the snakes and whether the snakes collided, escaped or where trapped.
 **/
//

import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Robot {
    private String name; // its name
    private int Gsize; // the size of the grid where it is located 
    private int xpos; // the x-coordinate of its current position 
    private int ypos; // the y coordinate of its current position 
    private boolean alive; // is it alive; intitially, every robot is alive
    private boolean escaped; // has it escaped, i.e. are the coordinates somewhere outside the grid?;
    private boolean trapped; // is it trapped?
gg
    private int[][] path; // the distance it covered, so the sequence of the coordinates of the position it has passed by
    // - he starting point is givent on the first row of the path and the current position is given in the row plen-1 of the path
    private int plen; //  the length of its trail up to now
    private Color color; // its color, which is produced randomly but has to be in line with the white (WHITE)


    // Ο μοναδικός κατασκευαστής λαμβάνειτο όνομα του ρομπότ καιτις
    // συντεταγμένες της αρχικής του θέσης, καθώς καιτο μέγεθος του
    // πλέγματος και αρχικοποιεί ανάλογα τα σχετικά πεδία του ρομπότ.
    // Μόνο ζωντανά ρομπότ κατασκευάζονται.
    public Robot(String name, int x, int y, int gridSize) {
        this.name = name;
        Gsize = gridSize;
        alive = true;
        plen++;
        path = new int[gridSize * gridSize + 1][2];
        path[0][0] = x;
        path[0][1] = y;
        plen++;
        xpos = x;
        ypos = y;
        color = randomcolor();
        Color white = StdDraw.WHITE;
        while (!compatible(color, white) && !compatible(color, StdDraw.GRAY)) //I check if the color is compatible to white
            color = randomcolor();
    }

    public Color getColor() {
        return color;
    }

    // Είναι ζωντανό; Ορίζεται ήδη
    public boolean isAlive() {
        return alive;
    }

    // Έχει δραπετεύσει; Ορίζεται ήδη
    public boolean hasEscaped() {
        return escaped;
    }

    // Είναι παγιδευμένο; Ορίζεται ήδη
    public boolean isTrapped() {
        return trapped;
    }

    // Το ρομπότ καταστρέφεται. Ορίζεται ήδη.
    public void destroyed() {
        alive = false;
    }

    // Επιστρέφειτην τρέχουσα θέση του ρομπότ.
    public int[] getPos() {
        return new int[]{xpos, ypos};
    }

    // Επιστρέφειτην αρχική θέση του ρομπότ.
    public int[] getStart() {
        return new int[]{path[0][0], path[0][1]};
    }

    public int[][] getPath() {
        return this.path;
    }

    public int getSize() {
        return Gsize;
    }

    // Έχει συγκρουσθεί με το ρομπότ r;
    public boolean collides(Robot otherRobot) {
        int[] otherPos = otherRobot.getPos();
        for (int[] position : path) {
            if (otherPos[0] == position[0] && otherPos[1] == position[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean isViableStep(int[] nowPos) {
        boolean isOutsideTheBox = nowPos[0] > this.Gsize || nowPos[0] < 0 || nowPos[1] > this.Gsize || nowPos[1] < 0;
        if (isOutsideTheBox) {
            return false;
        }
        boolean beenThereBeforeForCurrentStep = false;
        for (int[] position : path) {
            if ((nowPos[0] == position[0] && nowPos[1] == position[1])) {
                beenThereBeforeForCurrentStep = true;
                break;
            }
        }
        return !beenThereBeforeForCurrentStep;
    }

    // Εάν και εφόσον μπορεί, δηλαδή είναι ζωντανό, δεν έχει δραπετεύσει
    // ή δεν είναι παγιδευμένο, μετακινείται με τυχαίο τρόπο κατά
    // ένα βήμα σε σημείο από το οποίο δεν έχει ξαναπεράσει. Η μέθοδος
    // τροποποιεί ανάλογα τα επηρεαζόμενα πεδία και επιστρέφει true εάν
    // η μετακίνηση σε κάποιο νέο σημείο ήταν εφικτή, διαφορετικά
    // επιστρέφει false. Επιπρόσθετα ζωγραφίζει στο πλέγμα την κίνησή του με το χρώμα του,
    // αφήνοντας έτσι το ίχνος της πορείας του πάνω στο πλέγμα (αν στη συνέχεια κάποιο άλλο
    // ρομπότ περάσει από τα ίδια σημεία θα φανεί το δικό του ίχνος με το δικό του χρώμα).
    public boolean move() {
        if (!alive || escaped || trapped) {
            System.out.println("Trapped!");
            return false;
        }

        int[][] possibleSteps = {{xpos + 1, ypos}, {xpos - 1, ypos}, {xpos, ypos + 1}, {xpos, ypos - 1}};
        ArrayList<int[]> acceptedSteps = new ArrayList<int[]>();

        for (int possibleStepIdx = 0; possibleStepIdx <= 3; possibleStepIdx++) {
            int[] currentStep = possibleSteps[possibleStepIdx];
            if (isViableStep(currentStep)) {
                acceptedSteps.add(currentStep);
            }
        }

        if (acceptedSteps.size() == 0) {
            trapped = true;
            return false;
        }

        int randomStepIdx = (new Random()).nextInt(acceptedSteps.size()); //
        int[] nextStep = acceptedSteps.get(randomStepIdx);

        this.xpos = nextStep[0];
        this.ypos = nextStep[1];

        this.path = Arrays.copyOf(this.path, this.path.length + 1);
        this.path[this.path.length - 1] = nextStep;

        return true;
    }

    // Η παρουσίαση του ρομπότ. Η λειτουργία της μεθόδου διαφαίνεται στο
    // ακόλουθο παράδειγμα χρήσης του προγράμματος RandomRobotsτο οποίο
    // είναι client της Robot.
    public String toString() {
        String currentposition = "Robot" + name + "is positioned at (" + path[0][0] + "," + path[0][1] + ")" + "\n";
        if (plen == 1) {
            if (isAlive()) {
                currentposition += "The robot is alive ";
                if (hasEscaped())
                    currentposition += "and has escaped";
                else if (isTrapped())
                    currentposition += "and is trapped";
            } else
                currentposition += "The robot is dead";
            currentposition += "\n";
        } else {
            if (isAlive()) {
                currentposition += "The robot is alive ";
                if (hasEscaped())
                    currentposition += "and has escaped" + "\n";
                else if (isTrapped())
                    currentposition += "and is trapped \n";


            } else
                currentposition += "The robot is dead \n";
            currentposition += "It has moved as follows:" + "\n";
            for (int i = 0; i < plen - 1; i++) {
                currentposition += "\t - From position (" + path[i][0] + "," + path[i][1] + ")"
                        + " to position " + "(" + path[i + 1][0] + "," + path[i + 1][1] + ")\n";

            }

        }

        return currentposition;
    }


    private static double lum(Color c) {
        int r = c.getRed();
        int g = c.getGreen();
        int b = c.getBlue();
        return .299 * r + .587 * g + .114 * b;
    }

    private static boolean compatible(Color a, Color b) {
        return Math.abs(lum(a) - lum(b)) >= 128.0;
    }

    //This method return back a random color
    private static Color randomcolor() {

        int r = (int) (Math.random() * 256.0);
        int g = (int) (Math.random() * 256.0);
        int b = (int) (Math.random() * 256.0);
        return new Color(r, g, b);

    }
}
