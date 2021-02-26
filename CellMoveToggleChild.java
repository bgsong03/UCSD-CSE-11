import java.util.*;

public class CellMoveToggleChild extends CellMoveToggle {
  public static int numAlive = 0;
  
  public CellMoveToggleChild(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    numAlive += 1;
  }

  public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild){
    super(otherCellMoveToggleChild);
    numAlive += 1;
  }

  public boolean checkApoptosis(List<Cell> neighbors){
    if (neighbors.size() < 2 && neighbors.size() > 5 && numAlive < 10){
      return true;
    }
    return false;
  }

  @Override
  public void apoptosis(){
    super.apoptosis();
    numAlive -= 1;
  }
}
