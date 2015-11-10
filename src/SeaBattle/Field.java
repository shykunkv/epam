
package SeaBattle;

import java.io.*;


public class Field implements Serializable{

    private static final int VERTICAL_FIELD_SIZE = 10;
    private static final int HORIZONTAL_FIELD_SIZE = 10;

    private Cell[][] matrix = new Cell[VERTICAL_FIELD_SIZE][HORIZONTAL_FIELD_SIZE];
    private Ship[] ships;                       

    public Field() {
        for (int i = 0; i < VERTICAL_FIELD_SIZE; i++)
            for (int j = 0; j < HORIZONTAL_FIELD_SIZE; j++)
                matrix[i][j] = new Cell();
        ships = new Ship[SeaBattle.COMMON_COUNT];

    }
    
    public Ship[] getShips() {
        return ships;
    }

    public Cell getCell(int x, int y) {
        return matrix[y][x];
    }

    public void shootCell(int x, int y) {
        matrix[y][x].wasShot = true;
    }


    static boolean inRange(int x, int y) {
        if (x < 0 || y < 0 || x >= HORIZONTAL_FIELD_SIZE || y >= VERTICAL_FIELD_SIZE)
            return false;
        else
            return true;
    }

    public void setFieldReady() {
        for (int i = 0; i < VERTICAL_FIELD_SIZE; i++)
            for (int j = 0; j < HORIZONTAL_FIELD_SIZE; j++)
                matrix[i][j].wasShot = false;
    }


}
