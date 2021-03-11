/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#7 Part 1. It is used to hold the information
 * of CellMoveToggleChild type cells.
 */

import java.util.*;

/**
 * This class acts as a subclass to the CellMoveToggle class.
 * It contains 2 consctructors and 2 methods that are used hold information 
 * regarding CellMoveToggleChild type cells.
 * 
 * Instance variables:
 * numAlive - number of CellMoveToggleChild type cells that are currently 
 * alive
 */
public class CellMoveToggleChild extends CellMoveToggle {
  public static int numAlive;
  
  /**
   * This constructor initializes the various information of the cell. 
   * It also checks if the values given are valid.
   * 
   * @param currRow - the row value of the cell
   * @param currCol - the column value of the cell
   * @param mass - the mass of the cell
   */
  public CellMoveToggleChild(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    //Increment numAlive everytime new cell is created
    numAlive++;
  }

  /**
   * This copy constructor copies the instance variables of another cell
   * and applies them to the current cell.
   * 
   * @param otherCellMoveToggleChild - the other cell which information will
   * be copied from
   */
  public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild){
    super(otherCellMoveToggleChild);
    //Increment numAlive everytime new cell is created
    numAlive++;
  }

  /**
   * This method checks if the cell has the conditions to undergo
   * apoptosis.
   *
   * @param neighbors - a list of the neighbors of the cell
   * @return True or False depending on the whether the conditions for 
   * apoptosis are met
   */
  @Override
  public boolean checkApoptosis(List<Cell> neighbors){
    //Checking if conditions are met
    if ((neighbors.size() < 2 || neighbors.size() > 5) && numAlive < 10){
      return true;
    }
    return false;
  }

  /**
   * This method overrides the apoptosis method of Cell class.
   * It simulates the apoptosis of a CellMoveToggleChild type cell.
   */
  @Override
  public void apoptosis(){
    super.apoptosis();
    //Decrement numAlive everytime cell undergoes apoptosis
    numAlive--;
  }

  @Override
  public Cell newCellCopy() {
    CellMoveToggleChild newCell = new 
    CellMoveToggleChild(currRow, currCol, mass);
    newCell.toggled = this.toggled;
    return newCell;
  }
}
