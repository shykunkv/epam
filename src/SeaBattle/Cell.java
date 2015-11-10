package SeaBattle;

import java.io.Serializable;


public class Cell implements Serializable {
    boolean occupied = false; 
    boolean wasShot = false; 
    Ship owner;

    public Cell() {
    }

    public Cell(boolean occupied, boolean wasShot) {
        this.occupied = occupied;
        this.wasShot = wasShot;
        owner = null;
    }

}
