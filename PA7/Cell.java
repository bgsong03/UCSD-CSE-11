package PA7;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#7 Part 1. It is used to hold the information
 * of the cells.
 */

import java.util.*;

/**
 * This abstract class acts as a superclass for multiple classes in PA#7.
 * It contains 2 consctructors and 8 methods that are used to add, 
 * update, and hold information regarding the cells.
 * 
 * Instance variables:
 * currRow - the row value of the cell
 * currCol - the column value of the cell
 * mass - the mass of the cell
 */
public abstract class Cell {
  public int currRow;
  public int currCol;
  public int mass;

  /**
   * This constructor initializes the various information of the cell. 
   * It also checks if the values given are valid.
   * 
   * @param currRow - the row value of the cell
   * @param currCol - the column value of the cell
   * @param mass - the mass of the cell
   */
  public Cell(int currRow, int currCol, int mass){
    this.currRow = currRow;
    this.currCol = currCol;
    this.mass = mass;
    //Checking if variables are valid
    if (currRow < 0){
      this.currRow = 0;
    }
    if (currCol < 0){
      this.currCol = 0;
    }
    if (mass < 0){
      this.mass = 0;
    }
  }

  /**
   * This copy constructor copies the instance variables of another cell
   * and applies them to the current cell.
   * 
   * @param otherCell - the other cell which information will be copied from
   */
  public Cell(Cell otherCell){
    this.currRow = otherCell.getCurrRow();
    this.currCol = otherCell.getCurrCol();
    this.mass = otherCell.getMass();
  }

  /**
   * This method simulates the apoptosis of a cell.
   */
  public void apoptosis(){
    this.currRow = -1;
    this.currCol = -1;
    this.mass = -1;
  }

  /**
   * This method gets the row value of the cell.
   * 
   * @return Row value of cell
   */
  public int getCurrRow(){
    return currRow;
  }

  /**
   * This method gets the column value of the cell.
   * 
   * @return Column value of cell
   */
  public int getCurrCol(){
    return currCol;
  }

  /**
   * This method gets the mass of the cell.
   * 
   * @return Mass of cell
   */
  public int getMass(){
    return mass;
  }

  /**
   * This method updates the row value of the cell.
   *
   * @param newRow - The new row value of the cell
   * @return True or False depending on the whether the method is successful
   */
  public boolean setCurrRow(int newRow){
    //Checking if parameters are valid
    if (newRow >= 0){
      currRow = newRow;
      return true;
    }
    return false;
  }

  /**
   * This method updates the column value of the cell.
   *
   * @param newRow - The new column value of the cell
   * @return True or False depending on the whether the method is successful
   */
  public boolean setCurrCol(int newCol){
    //Checking if parameters are valid
    if (newCol >= 0){
      currCol = newCol;
      return true;
    }
    return false;
  }

  /**
   * This method updates the mass of the cell.
   *
   * @param newRow - The new mass of the cell
   * @return True or False depending on the whether the method is successful
   */
  public boolean setMass(int newMass){
    //Checking if parameters are valid
    if (newMass >= 0){
      mass = newMass;
      return true;
    }
    return false;
  }

  /**
   * This abstract method is a template that each concrete subclass 
   * will implement with its own behavior.
   *
   * @param neighbors - a list of the neighbors of the cell
   */
  public abstract boolean checkApoptosis(List<Cell> neighbors);
}
