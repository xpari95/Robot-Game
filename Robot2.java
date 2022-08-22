public class Robot2 {
     final static int RIGHTBOUNDARY = 26;
     final static int LEFTBOUNDARY = 0;
     final static int TOPBOUNDARY = 26;
     final static int BOTTOMBOUNDARY = 0;
private static char[][] grid;
     private int x;
     private int y;
     private char payload;
     public Robot2() {
         x = 0;
         y = 0;
         payload = ' ';
     }
public Robot2(int x, int y, char payload) {
         this.x = x;
         this.y = y;
         this.payload = payload;
     }
public int getX() {
         return x;
     }
public void setX(int x) {
         this.x = x;
     }
public int getY() {
         return y;
     }
public void setY(int y) {
         this.y = y;
     }
public char getPayload() {
         return payload;
     }
public void setPayload(char payload) {
         this.payload = payload;
     }
 
     public void print() {
         System.out.println("The robot is on grid ("+x+","+y+") with payload: \'" + payload + "\'");
     }
 
     public boolean pickup(int lx, int ly) {
         if (lx != x || ly != y ) {
             System.out.println("Robot not at given location.");
             return false;
         }
 
         if (payload != ' ') {
             System.out.println("Robot already has a payload.");
             return false;
         }
 
         if (grid[lx][ly] == ' ') {
             System.out.println("Location has no payload.");
             return false;
         }
 
         payload = grid[lx][ly];
         grid[lx][ly] = ' ';
         return true;
     }
 
     public boolean dropOff(int lx, int ly) {
         if (lx != x || ly != y ) {
             System.out.println("Robot not at given location.");
             return false;
         }
 
         if (payload == ' ') {
             System.out.println("Robot has no payload.");
             return false;
         }
 
         grid[lx][ly] = payload;
         payload = ' ';
         return true;
     }
 
     public void moveRight() {
         if (x < RIGHTBOUNDARY - 1)
             x++;
         else
             System.out.println("Right boundary reached");
     }
 
     public void moveLeft() {
         if (x > LEFTBOUNDARY)
             x--;
         else
             System.out.println("Left boundary reached");
     }
 
     public void moveUp() {
         if (y < TOPBOUNDARY - 1)
             y++;
         else
             System.out.println("Top boundary reached");
     }
 
     public void moveDown() {
         if (y > BOTTOMBOUNDARY)
             y--;
         else
             System.out.println("Bottom boundary reached");
     }
 
     public boolean moveTo(int lx, int ly) {
         if (lx >= LEFTBOUNDARY && lx < RIGHTBOUNDARY  && y >= BOTTOMBOUNDARY && y < TOPBOUNDARY) {
             int dx = lx - x;
             int dy = ly - y;
 
             if (dx > 0)
                 for (int i = 0; i < dx; i++)
                     moveRight();
             else
                 for (int i = 0; i > dx; i--)
                     moveLeft();
 
             if (dy > 0)
                 for (int i = 0; i < dy; i++)
                     moveUp();
             else
                 for (int i = 0; i > dy; i--)
                     moveDown();
 
             return true;
         }
         return false;
     }
 
     public static void print2D() {
         for(int j = TOPBOUNDARY-1; j >= BOTTOMBOUNDARY; j--) {
             for (int i = LEFTBOUNDARY; i < RIGHTBOUNDARY; i++)
                 System.out.print(grid[j][i] + "|");
             System.out.println();
         }
     }
 
     public static void main(String[] args) {
         grid = new char[26][26];
         for(int j = TOPBOUNDARY-1; j >= BOTTOMBOUNDARY; j--)
             for (int i = LEFTBOUNDARY; i < RIGHTBOUNDARY; i++)
                 grid[j][i] = ' ';
 
         grid[10][8] = 'B';
         grid[22][4] = 'C';
 
         print2D();
         System.out.println("");
         Robot2 R1 = new Robot2();
         R1.print();
         Robot2 R2 = new Robot2();
         R2.print();
         System.out.println("");
         R1.moveTo(10, 8);
         R1.pickup(10, 8);
         R1.moveTo(20, 20);
         R1.dropOff(20, 20);
         print2D();
         System.out.println("");
         R2.moveTo(22, 4);
         R2.pickup(22, 4);
         R2.moveTo(0, 0);
         R2.dropOff(0, 0);
         print2D();
         System.out.println("");
         clear();
         print2D();
     }
 
     public static void clear(){
         for (int i = LEFTBOUNDARY; i < RIGHTBOUNDARY; i++)
             for(int j = TOPBOUNDARY-1; j >= BOTTOMBOUNDARY; j--)
                 if (grid[i][j] != ' '){
                     Robot2 r = new Robot2(i, j, ' ');
                     r.pickup(i, j);
                     r = null;
                 }
 
     }
 }