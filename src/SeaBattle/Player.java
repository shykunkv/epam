package SeaBattle;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.util.Random;
import java.io.*;

public class Player implements Serializable{

    static Scanner scanner = new Scanner(System.in);
    private boolean isHuman;                            
    static int enemyDeadShipsCount = 0;                          
    boolean nearEnemyShip = false;        
    int lastX, lastY, firstX, firstY;                  
    String username;                                    
    private Field field = new Field();                 
    private Field map = new Field();  	 
    Ship.Direction lastDirection;

    public Field getField() {
        return field;
    }

    public Field getMap() {
        return map;
    }

    public Player(boolean isHuman) {
        this.isHuman = isHuman;
        if (isHuman){
            username = Input.usernameEnter();
        }
    }

    public void createField(boolean isAuto) {
        if (isHuman) {
            this.field = new Field();
            ShipManipulator.createShips(field, isAuto);
            UserInterface.shipsReady(this.username);
            field.setFieldReady();                     
            UserInterface.showMap(field);
        } else {
        	ShipManipulator.createShips(field, true);
        }
    }
    

    public boolean playerShoot(int x, int y, Player ai)  {
    	//Serial(this.field);
        Ship[] ships = ai.getField().getShips();
        int deadCount = 0;
        map.shootCell(x, y);


        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                map.getCell(x, y).occupied = true;
                UserInterface.gotHim();
                if (ship.isDead) {
                    deadCount++;
                    ShipManipulator.setSunk(map, ship);
                }
            }
        }

        System.out.println("\nOur map:");       
        UserInterface.showMap(map);

        if (deadCount == SeaBattle.COMMON_COUNT)
            return true;
        else
            return false;
    }
    

    public boolean aiShoot(Player player) {
        Ship[] ships = player.getField().getShips();
        Random random = new Random();
        int deadCount = 0;
        int x, y;
        Ship.Direction direction = null;

        if (nearEnemyShip) {
            for (; ; ) {
                x = lastX;
                y = lastY;
                if (lastDirection != null)
                    direction = lastDirection;
                else
                    direction = Ship.Direction.values()[random.nextInt(4)];

                switch (direction) {
                    case Right:
                        x += 1;
                        break;
                    case Left:
                        x -= 1;
                        break;
                    case Down:
                        y += 1;
                        break;
                    case Up:
                        y -= 1;
                        break;
                }

                if ((!Field.inRange(x, y) && lastDirection != null) || (Field.inRange(x, y) && lastDirection != null && player.getField().getCell(x, y).wasShot)) {
                    x = firstX;
                    y = firstY;
                    switch (lastDirection) {
                        case Up:
                            direction = Ship.Direction.Down;
                            y += 1;
                            break;
                        case Down:
                            direction = Ship.Direction.Up;
                            y -= 1;
                            break;
                        case Left:
                            direction = Ship.Direction.Right;
                            x += 1;
                            break;
                        case Right:
                            direction = Ship.Direction.Left;
                            x -= 1;
                            break;
                    }
                }


                if (Field.inRange(x, y))
                    if (!player.getField().getCell(x, y).wasShot)
                        break;
            }
        } else

            do {
                x = random.nextInt(10);
                y = random.nextInt(10);

            } while (player.getField().getCell(x, y).wasShot);

        player.getField().shootCell(x, y);

        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                nearEnemyShip = true;
                lastX = x;
                lastY = y;

                lastDirection = direction;
                if (lastDirection == null) {
                    firstX = x;
                    firstY = y;
                }

                if (ship.isDead) {
                    ShipManipulator.setSunk(player.getField(), ship);
                    nearEnemyShip = false;
                }
            }
            if (ship.isDead) {
                deadCount++;
       	 }
        }
        System.out.println("\nField report, shoot by "+this.username+" in "+x+"-"+y+":");      
        UserInterface.showMap(player.getField());
        if (deadCount == SeaBattle.COMMON_COUNT)
            return true;
        else
            return false;
    }
}