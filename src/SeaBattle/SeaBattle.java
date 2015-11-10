package SeaBattle;

import java.util.Scanner;
import java.io.*;
import java.io.*;

public class SeaBattle{


    static Scanner scanner = new Scanner(System.in);
    static final int FOURDECK_COUNT = 1;
    static final int THREEDECK_COUNT = 2;
    static final int TWODECK_COUNT = 3;
    static final int ONEDECK_COUNT = 4;
    static final int COMMON_COUNT = FOURDECK_COUNT + THREEDECK_COUNT + TWODECK_COUNT + ONEDECK_COUNT;

    public static void main(String[] args) throws IOException {
    	int x;
    	x = Input.getTypeOfGame();
    	FileOutputStream fos = new FileOutputStream("temp.out");
    	ObjectOutputStream oos = new ObjectOutputStream(fos); 
    	switch (x){ 
    	case 1:
	        Player player = new Player(true);                                   
	        Player ai = new Player(false);                                      
	        player.createField(Input.genTypeChoice());
	        ai.createField(false);        
	        ai.username = "Bot";
	        for (; ; )                                                     
	        {
	            Coordinate coordinate = Input.inputShotCoordinate();
	            if (Field.inRange(coordinate.x, coordinate.y) && !player.getMap().getCell(coordinate.x, coordinate.y).wasShot) {   
	                if (player.playerShoot(coordinate.x, coordinate.y, ai)) {   
	                	UserInterface.winner(true, player.username);                          
	                    break;
	                }
	                if (ai.aiShoot(player)) {                                   
	                	UserInterface.winner(false, player.username);
	                    break;
	                }
	            } else
	            	UserInterface.wrongCoordinate();
	        }
    		break;
    	case 2: 
    		Player ai1 = new Player(true);                                   
	        Player ai2 = new Player(true); 
	        ai1.createField(true);
	        ai2.createField(true);
	        for(;;){
	        	if (ai1.aiShoot(ai2)){
	        		UserInterface.winner(true, ai1.username);                          
                    break;
	        	}
	        	if (ai2.aiShoot(ai1)){
	        		UserInterface.winner(true, ai2.username);                          
                    break;
	        	}
	        }
    		break;
    	}
    	
    }

}


