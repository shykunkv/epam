package SeaBattle;


import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;


public class ShipManipulator implements Serializable{
    static boolean isAutoGenerated = false;

    public static void createShips(Field field, boolean isAutoGenerated) {
        Ship[] ships = field.getShips();
        for (int i = 0; i < SeaBattle.COMMON_COUNT; i++)
            if (i < SeaBattle.FOURDECK_COUNT)
                ships[i] = shipGenerate(4, isAutoGenerated, field);
            else if (i < SeaBattle.FOURDECK_COUNT + SeaBattle.THREEDECK_COUNT)
                ships[i] = shipGenerate(3, isAutoGenerated, field);
            else if (i < SeaBattle.FOURDECK_COUNT + SeaBattle.THREEDECK_COUNT + SeaBattle.TWODECK_COUNT)
                ships[i] = shipGenerate(2, isAutoGenerated, field);
            else if (i < SeaBattle.COMMON_COUNT - 1)
                ships[i] = shipGenerate(1, isAutoGenerated, field);
            else
                ships[i] = shipGenerate(1, isAutoGenerated, field);
    }


    public static Ship shipGenerate(int strength, boolean isAuto, Field field) {

        if (isAuto)
            isAutoGenerated = isAuto;

        if (isAutoGenerated)
            return autoGenerate(strength, field);
        else
            return shipsPlace(strength, field);
    }


    public static boolean clearCheck(Field field, Ship ship) {
        boolean clear = true;
        if (!Field.inRange(ship.end.x, ship.end.y) || !Field.inRange(ship.beginning.x, ship.beginning.y))
            clear = false;
        else {
            for (int i = Math.min(ship.beginning.y, ship.end.y); i <= (Math.min(ship.beginning.y, ship.end.y) + Math.abs(ship.beginning.y - ship.end.y)); i++)
                for (int j = Math.min(ship.beginning.x, ship.end.x); j <= (Math.min(ship.beginning.x, ship.end.x) + Math.abs(ship.beginning.x - ship.end.x)); j++) {
                    if (!field.getCell(i, j).occupied && !field.getCell(i, j).wasShot && clear)
                        clear = true;
                    else
                        clear = false;
                }
        }
        return clear;
    }


    static void setShip(Field field, Ship ship) {
        for (int i = Math.min(ship.beginning.y, ship.end.y); i <= Math.max(ship.beginning.y, ship.end.y); i++)
            for (int j = Math.min(ship.beginning.x, ship.end.x); j <= Math.max(ship.beginning.x, ship.end.x); j++) {
                field.getCell(i, j).occupied = true;
                field.getCell(i, j).wasShot = false;
                for (int k = -1; k <= 1; k++)
                    for (int l = -1; l <= 1; l++) {
                        if (Field.inRange(i + k, j + l))
                            if (!field.getCell(i + k, j + l).occupied)
                                field.getCell(i + k, j + l).wasShot = true;
                    }
            }
    }


    static void setSunk(Field field, Ship ship) {
        for (int i = Math.min(ship.beginning.y, ship.end.y); i <= Math.max(ship.beginning.y, ship.end.y); i++) {
            for (int j = Math.min(ship.beginning.x, ship.end.x); j <= Math.max(ship.beginning.x, ship.end.x); j++)
                for (int k = -1; k <= 1; k++)
                    for (int l = -1; l <= 1; l++) {
                        if (Field.inRange(i + k, j + l))
                            field.getCell(i + k, j + l).wasShot = true;
                    }
        }
    }


    private static Ship autoGenerate1(int strength, Field field){
    	  Ship ship = new Ship();
          ship.strength = strength; 
          Random rand = new Random();
          Ship.Direction direction;
          boolean directionSet;
          do{
              directionSet = true;
        	  if (strength != 1){
        		  ship.beginning.x = rand.nextInt(10);
                  ship.beginning.y = rand.nextInt(4);
        	  }
        	  else{
        		  ship.beginning.x = rand.nextInt(10);
                  ship.beginning.y = rand.nextInt(10);
        	  }
        	  direction = Ship.Direction.Down;
              directionSet = true;
              switch (direction) {
                  case Up:
                      ship.end.x = ship.beginning.x;
                      ship.end.y = ship.beginning.y - (strength - 1);
                      if (!clearCheck(field, ship)) {
                          directionSet = false;
                      } else {
                          setShip(field, ship);
                      }
                      break;
                  case Right:
                      ship.end.x = ship.beginning.x + (strength - 1);
                      ship.end.y = ship.beginning.y;
                      if (!clearCheck(field, ship)) {
                          directionSet = false;
                      } else {
                          setShip(field, ship);
                      }
                      break;
                  case Down:
                      ship.end.x = ship.beginning.x;
                      ship.end.y = ship.beginning.y + (strength - 1);
                      if (!clearCheck(field, ship)) {
                          directionSet = false;
                      } else {
                          setShip(field, ship);
                      }
                      break;
                  case Left:
                      ship.end.x = ship.beginning.x - (strength - 1);
                      ship.end.y = ship.beginning.y;
                      if (!clearCheck(field, ship)) {
                          directionSet = false;
                      } else {
                          setShip(field, ship);
                      }
                      break;
                  default:
              }

        	  
          }while(!directionSet);
          return ship;
    }
    
    
    private static Ship autoGenerate(int strength, Field field) {
        Ship ship = new Ship();
        ship.strength = strength; 
        Random rand = new Random();
        Ship.Direction direction;
        boolean directionSet;
        do {
            ship.beginning.x = rand.nextInt(10);
            ship.beginning.y = rand.nextInt(10);
            direction = Ship.Direction.values()[rand.nextInt(4)];
            directionSet = true;
            switch (direction) {
                case Up:
                    ship.end.x = ship.beginning.x;
                    ship.end.y = ship.beginning.y - (strength - 1);
                    if (!clearCheck(field, ship)) {
                        directionSet = false;
                    } else {
                        setShip(field, ship);
                    }
                    break;
                case Right:
                    ship.end.x = ship.beginning.x + (strength - 1);
                    ship.end.y = ship.beginning.y;
                    if (!clearCheck(field, ship)) {
                        directionSet = false;
                    } else {
                        setShip(field, ship);
                    }
                    break;
                case Down:
                    ship.end.x = ship.beginning.x;
                    ship.end.y = ship.beginning.y + (strength - 1);
                    if (!clearCheck(field, ship)) {
                        directionSet = false;
                    } else {
                        setShip(field, ship);
                    }
                    break;
                case Left:
                    ship.end.x = ship.beginning.x - (strength - 1);
                    ship.end.y = ship.beginning.y;
                    if (!clearCheck(field, ship)) {
                        directionSet = false;
                    } else {
                        setShip(field, ship);
                    }
                    break;
                default:
            }
        } while (!directionSet);
        return ship;
    }
    

    private static Ship shipsPlace (int strength, Field field) {
        Ship ship = new Ship();
        ship.strength = strength;
        boolean shipSet;
        Scanner scanner = new Scanner(System.in);
        do {
            shipSet = true;
            Coordinate nose = Input.inputNoseCoordinate(strength);
            ship.beginning.x = nose.x;
            ship.beginning.y = nose.y;

            switch (Input.inputShipDirection()) {
                case Up:
                    ship.end.x = ship.beginning.x;
                    ship.end.y = ship.beginning.y - (strength - 1);
                    if (!clearCheck(field, ship)) {
                        shipSet = false;
                        UserInterface.wrongPosition();
                    } else {
                        setShip(field, ship);
                    }
                    break;
                case Right:
                    ship.end.x = ship.beginning.x + (strength - 1);
                    ship.end.y = ship.beginning.y;
                    if (!clearCheck(field, ship)) {
                        shipSet = false;
                        UserInterface.wrongPosition();
                    } else {
                        setShip(field, ship);
                    }
                    break;
                case Down:
                    ship.end.x = ship.beginning.x;
                    ship.end.y = ship.beginning.y + (strength - 1);
                    if (!clearCheck(field, ship)) {
                        shipSet = false;
                        UserInterface.wrongPosition();
                    } else {
                        setShip(field, ship);
                    }
                    break;
                case Left:
                    ship.end.x = ship.beginning.x - (strength - 1);
                    ship.end.y = ship.beginning.y;
                    if (!clearCheck(field, ship)) {
                        shipSet = false;
                        UserInterface.wrongPosition();
                    } else {
                        setShip(field, ship);
                    }
                    break;

            }

        } while (!shipSet);

        UserInterface.showMap(field);
        return ship;
    }


}
