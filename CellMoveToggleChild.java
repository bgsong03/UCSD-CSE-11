import java.util.*;

public class CellMoveToggleChild extends CellMoveToggle {
  public static int numAlive;
  
  public CellMoveToggleChild(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    numAlive++;
  }

  public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild){
    super(otherCellMoveToggleChild);
    numAlive++;
  }

  public boolean checkApoptosis(List<Cell> neighbors){
    if ((neighbors.size() < 2 || neighbors.size() > 5) && numAlive < 10){
      return true;
    }
    return false;
  }

  @Override
  public void apoptosis(){
    super.apoptosis();
    numAlive--;
  }
}
