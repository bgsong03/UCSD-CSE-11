package PA8;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#8 Part 2. It is used to hold the information
 * of CellDivide type cells.
 */

import java.util.*;

/**
 * This class acts as a subclass to the Cell class.
 * It contains 2 consctructors and 2 methods that are used hold information
 * regarding CellDivide type cells.
 * 
 * Instance variables:
 * direction - the direction the cell will divide into
 */
public class CellDivide extends Cell implements Divisible {
  public int direction;
  
  /**
   * This constructor initializes the various information of the cell. 
   * It also checks if the values given are valid.
   * 
   * @param currRow - the row value of the cell
   * @param currCol - the column value of the cell
   * @param mass - the mass of the cell
   */
  public CellDivide(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    direction = 1;
  }

  /**
   * This copy constructor copies the instance variables of another cell
   * and applies them to the current cell.
   * 
   * @param otherCellDivide - the other cell which information will 
   * be copied from
   */
  public CellDivide(CellDivide otherCellDivide){
    super(otherCellDivide);
    this.direction = otherCellDivide.direction;
  }

  /**
   * This method creates the string representation of an object of this class.
   * 
   * @return String representation of current object
   */
  public String toString(){
    return "+";
  }

  /**
   * This method checks if the cell has the conditions to undergo
   * apoptosis.
   *
   * @param neighbors - a list of the neighbors of the cell
   * @return True or False depending on the whether the conditions for 
   * apoptosis are met
   */
  public boolean checkApoptosis(List<Cell> neighbors){
    //Checking if conditions are met
    if (neighbors.size() == 3){
      return true;
    }
    return false;
  }

  /**
   * This method creates a deep copy of a CellDivide object.
   *
   * @return Deep copy of CellDivide object
   */
  public Cell newCellCopy() {
    CellDivide newCell = new CellDivide(currRow, currCol, mass);
    newCell.direction = this.direction;
    return newCell;
  }

  /**
   * This method  defines where a new cell that is divided from the
   * original calling cell will be placed.
   *
   * @return Array containing the position of the newly spawned cell
   */
  public int[] getDivision() {
    int[] newPosition = new int[2];
    //The behavior of the cell changes depending on the value of direction
    if (direction == 0){
      newPosition[0] = currRow + 1;
      newPosition[1] = currCol;
      direction = 1;
      return newPosition;
    }
    else if (direction == 1){
      newPosition[0] = currRow - 1;
      newPosition[1] = currCol;
      direction = 2;
      return newPosition;
    }
    else if (direction == 2){
      newPosition[0] = currRow;
      newPosition[1] = currCol - 1;
      direction = 3;
      return newPosition;
    }
    else{
      newPosition[0] = currRow;
      newPosition[1] = currCol + 1;
      direction = 0;
      return newPosition;
    }
  }
}
