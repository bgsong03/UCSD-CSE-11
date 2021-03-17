/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#8 Part 2. It is used to hold the information
 * of CellMoveToggle type cells.
 */

import java.util.*;

/**
 * This class acts as a subclass to the CellMoveUp class.
 * It contains 2 consctructors and 2 methods that are used hold information 
 * regarding CellMoveToggle type cells.
 * 
 * Instance variables:
 * toggled - represents if the cell is currently "toggled" or not.
 */
public class CellMoveToggle extends CellMoveUp {
  public boolean toggled;

  /**
   * This constructor initializes the various information of the cell. 
   * It also checks if the values given are valid.
   * 
   * @param currRow - the row value of the cell
   * @param currCol - the column value of the cell
   * @param mass - the mass of the cell
   */
  public CellMoveToggle(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    toggled = true;
  }

  /**
   * This copy constructor copies the instance variables of another cell
   * and applies them to the current cell.
   * 
   * @param otherCellMoveToggle - the other cell which information will 
   * be copied from
   */
  public CellMoveToggle(CellMoveToggle otherCellMoveToggle){
    super(otherCellMoveToggle);
  }

  /**
   * This method creates the string representation of an object of this class.
   * 
   * @return String representation of current object
   */
  public String toString(){
    //If toggled is true string representation is "T"
    if (toggled == true){
      return "T";
    }
    //If toggled is not ture string representation is "t"
    return "t";
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
    if (neighbors.size() < 2 || neighbors.size() > 5){
      return true;
    }
    return false;
  }

  /**
   * This method defines how a CellMoveToggle type cell will move
   * in the petri dish.
   *
   * @return Array containing the new position of the cell
   */
  @Override
  public int[] getMove() {
    int[] newPosition = new int[2];
    //Move up once if toggled is true
    if (toggled == true){
      newPosition[0] = currRow - 1;
      newPosition[1] = currCol;
      toggled = false;
      return newPosition;
    }
    //Remain stationary if toggled is false
    newPosition[0] = currRow;
    newPosition[1] = currCol;
    toggled = true;
    return newPosition;
  }

  /**
   * This method creates a deep copy of a CellMoveToggle object.
   *
   * @return Deep copy of CellMoveToggle object
   */
  @Override
  public Cell newCellCopy() {
    CellMoveToggle newCell = new CellMoveToggle(currRow, currCol, mass);
    newCell.toggled = this.toggled;
    return newCell;
  }
}
