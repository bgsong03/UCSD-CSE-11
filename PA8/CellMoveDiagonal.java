package PA8;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#8 Part 2. It is used to hold the information
 * of CellMoveDiagonal type cells.
 */

import java.util.*;

/**
 * This class acts as a subclass to the CellMoveUp class.
 * It contains 2 consctructors and 2 methods that are used hold information 
 * regarding CellMoveDiagonal type cells.
 * 
 * Instance variables:
 * orientedRight - represents if the cell is currently oriented to the 
 * right or not
 * diagonalMoves - the number of diagonal moves this cell has made
 */
public class CellMoveDiagonal extends CellMoveUp {
  public boolean orientedRight;
  public int diagonalMoves;

  /**
   * This constructor initializes the various information of the cell. 
   * It also checks if the values given are valid.
   * 
   * @param currRow - the row value of the cell
   * @param currCol - the column value of the cell
   * @param mass - the mass of the cell
   */
  public CellMoveDiagonal(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    orientedRight = true;
    diagonalMoves = 0;
  }

  /**
   * This copy constructor copies the instance variables of another cell
   * and applies them to the current cell.
   * 
   * @param otherCellDiagonal - the other cell which information will 
   * be copied from
   */
  public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal){
    super(otherCellMoveDiagonal);
    this.orientedRight = otherCellMoveDiagonal.orientedRight;
    this.diagonalMoves = otherCellMoveDiagonal.diagonalMoves;
  }

  /**
   * This method creates the string representation of an object of this class.
   * 
   * @return String representation of current object
   */
  public String toString(){
    if (orientedRight == true){
      return "/";
    }
    return "\\";
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
    if (neighbors.size() > 3){
      return true;
    }
    return false;
  }

  /**
   * This method defines how a CellMoveDiagonal type cell will move
   * in the petri dish.
   *
   * @return Array containing the new position of the cell
   */
  @Override
  public int[] getMove() {
    int[] newPosition = new int[2];
    //Move diagonally right if orientedRight is true
    if (orientedRight == true){
      newPosition[0] = currRow - 1;
      newPosition[1] = currCol + 1;
      diagonalMoves++;
      //If diagonalMoves is a multiple of 4 the orientation is flipped
      if (diagonalMoves % 4 == 0){
        orientedRight = false;
      }
      return newPosition;
    }
    //Move diagonally left if orientedRight is false
    newPosition[0] = currRow - 1;
    newPosition[1] = currCol - 1;
    diagonalMoves++;
    //If diagonalMoves is a multiple of 4 the orientation is flipped
    if (diagonalMoves % 4 == 0){
      orientedRight = true;
    }
    return newPosition;
  }

  /**
   * This method creates a deep copy of a CellMoveDiagonal object.
   *
   * @return Deep copy of CellMoveDiagonal object
   */
  @Override
  public Cell newCellCopy() {
    CellMoveDiagonal newCell = new CellMoveDiagonal(currRow, currCol, mass);
    newCell.orientedRight = this.orientedRight;
    newCell.diagonalMoves = this.diagonalMoves;
    return newCell;
  }
}
