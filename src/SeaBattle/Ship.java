
package SeaBattle;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Ship implements Serializable{

    public Coordinate beginning = new Coordinate();     
    public Coordinate end = new Coordinate();           
    int strength;                                
    public boolean isDead = false;                      
    enum Direction {Up, Right, Down, Left, Undefined}; 

    
    public boolean isHit(int x, int y) {
        boolean hitMarker = false;
        for (int i = Math.min(beginning.x, end.x); i <= Math.max(beginning.x, end.x); i++)
            for (int j = Math.min(beginning.y, end.y); j <= Math.max(beginning.y, end.y); j++) {
                if (j == x && i == y) {
                    strength -= 1;
                    hitMarker = true;
                    if (strength == 0) {
                        isDead = true;
                        UserInterface.shipSunk();
                    }
                }
            }
        return hitMarker;
    }
}



