/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#7 Part 1. It is used to hold the information
 * of CellStationary type cells.
 */

import java.util.*;

/**
 * This class acts as a subclass to the Cell class.
 * It contains 2 consctructors and 2 methods that are used hold information 
 * regarding CellStationary type cells.
 */
public class CellStationary extends Cell {

  /**
   * This constructor initializes the various information of the cell. 
   * It also checks if the values given are valid.
   * 
   * @param currRow - the row value of the cell
   * @param currCol - the column value of the cell
   * @param mass - the mass of the cell
   */
  public CellStationary(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
  }

  /**
   * This copy constructor copies the instance variables of another cell
   * and applies them to the current cell.
   * 
   * @param otherCellStationary - the other cell which information will
   * be copied from
   */
  public CellStationary(CellStationary otherCellStationary){
    super(otherCellStationary);
  }

  /**
   * This method creates the string representation of an object of this class.
   * 
   * @return String representation of current object
   */
  public String toString(){
    return ".";
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
    if (neighbors.size() <= 7 && neighbors.size() >= 3){
      return true;
    }
    return false;
  }
}
