package SeaBattle;
import java.util.Scanner;

public class Input {

    static Scanner scanner = new Scanner(System.in);

    static int getTypeOfGame(){
    	int ch=0;
    	do{
    		
        	System.out.print("1-Human vs Computer\n2-Computer vs computer\n");
        	ch = scanner.nextInt();

    	}while(ch != 1 && ch != 2);
    	return ch;
    }
    
    static boolean genTypeChoice() {
        int choice = 0;
        boolean isAuto;
        do {
            System.out.println("How place your ship ?\n1-Auto;\n2-Yourself");
            try {
                choice = scanner.nextInt();
            } catch (Exception ex) {
                System.out.println("Invalid choice, please repeat");
                scanner = new Scanner(System.in);
            }
        } while (choice != 1 && choice != 2);
        if (choice == 1)
            isAuto = true;
        else if (choice == 2)
            isAuto = false;
        else
            isAuto = false;
        return isAuto;
    }

    static Coordinate inputNoseCoordinate(int strength){
        Coordinate coordinate = new Coordinate();
        try {
            System.out.println("Please, enter X coordinate of the beginning of your " + strength + "-deck ship: ");
            coordinate.x = scanner.nextInt();
            System.out.println("Please, enter Y coordinate of the beginning of your " + strength + "-deck ship: ");
            coordinate.y = scanner.nextInt();
        } catch (Exception ex) {
            scanner = new Scanner(System.in);
        }
        return coordinate;
    }

    static Ship.Direction inputShipDirection(){
        Ship.Direction direction = Ship.Direction.Undefined;
        do {
            try {
                System.out.println("Please, define the direction of the ship:\nUp, Down, Left or Right");
                direction = Ship.Direction.valueOf(scanner.next());
            } catch (Exception ex) {
                scanner = new Scanner(System.in);
            }
        } while (direction== Ship.Direction.Undefined);

        return direction;
    }

    static Coordinate inputShotCoordinate() {
        Coordinate coordinate = new Coordinate();
        try {
            System.out.print("\nEnter X coordinate of shot: ");
            coordinate.x = scanner.nextInt();
            System.out.print("\nEnter Y coordinate of  shot: ");
            coordinate.y = scanner.nextInt();
        } catch (java.util.InputMismatchException ex) {                   
            coordinate.x = 10;
            coordinate.y = 10;
            scanner = new Scanner(System.in);
        }
        return coordinate;
    }

    static String usernameEnter() {
        System.out.print("Please, enter your name: ");
        String username = scanner.next();
        System.out.print("\nWelcome, " + username + "!\n\n");
        return username;
    }
    
}