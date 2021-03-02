import java.util.*;

public class CellMoveToggle extends CellMoveUp {
  public boolean toggled;

  public CellMoveToggle(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    toggled = true;
  }

  public CellMoveToggle(CellMoveToggle otherCellMoveToggle){
    super(otherCellMoveToggle);
  }

  public String toString(){
    if (toggled == true){
      return "T";
    }
    return "t";
  }

  public boolean checkApoptosis(List<Cell> neighbors){
    if (neighbors.size() < 2 || neighbors.size() > 5){
      return true;
    }
    return false;
  }
}
