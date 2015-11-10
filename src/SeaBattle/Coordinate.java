package SeaBattle;

import java.io.Serializable;


public class Coordinate implements Serializable {
    int x;
    int y;
    
    public Coordinate(){
    	
    }
    public Coordinate(int x, int y){
    	this.x = x;
    	this.y = y;
    	
    }
}