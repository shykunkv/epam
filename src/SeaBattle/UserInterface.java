
package SeaBattle;

import java.io.*;

public class UserInterface {
	

    static void showMap(Field field) {
        System.out.println("  |0 1 2 3 4 5 6 7 8 9\n--+-------------------");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 10; j++) {
                if (field.getCell(j, i).occupied) {
                    if (field.getCell(j, i).wasShot)
                        System.out.print("X ");
                    else
                        System.out.print("O ");
                } else if (field.getCell(j, i).wasShot)
                    System.out.print("* ");
                else
                    System.out.print("~ ");
            }
            System.out.println();
        }
    }

    static void winner(boolean win, String username) {
        if (win)
            System.out.print("You win " + username + "!");
        else
            System.out.print("Sorry, you loose, " + username + " ");
    }

    static void wrongCoordinate(){
        System.out.println("Would you kindly set valid coordinates?");
    }

    static void gotHim(){
        System.out.println("You injured him!");
    }

    static void shipsReady(String Name){
        System.out.println("Your ships are ready, "+Name);
    }

    static void wrongPosition(){
        System.out.println("Please, repeat enter");
    }

    static void shipSunk(){
        System.out.println("The ship is dead");
    }


}