import java.util.*;

public abstract class Cell {
  public int currRow;
  public int currCol;
  public int mass;

  public Cell(int currRow, int currCol, int mass){
    this.currRow = currRow;
    this.currCol = currCol;
    this.mass = mass;
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

  public Cell(Cell otherCell){
    this.currRow = otherCell.currRow;
    this.currCol = otherCell.currCol;
    this.mass = otherCell.mass;
  }

  public void apoptosis(){
    currRow = -1;
    currCol = -1;
    mass = -1;
    return;
  }

  public int getCurrRow(){
    return currRow;
  }

  public int getCurrCol(){
    return currCol;
  }

  public int getMass(){
    return mass;
  }

  public boolean setCurrRow(int newRow){
    if (newRow >= 0){
      currRow = newRow;
      return true;
    }
    return false;
  }

  public boolean setCurrCol(int newCol){
    if (newCol >= 0){
      currCol = newCol;
      return true;
    }
    return false;
  }

  public boolean setMass(int newMass){
    if (newMass >= 0){
      mass = newMass;
      return true;
    }
    return false;
  }

  public abstract boolean checkApoptosis(List<Cell> neighbors);
}
