public class PetriDish {
  public Cell[][] dish;

  public PetriDish(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == null) {
          dish[i][j] = null;
        } 
        else {
          String[] cellInfo = board[i][j].strip().split(" ");
          String cellType = cellInfo[0];
          int mass = Integer.parseInt(cellInfo[1]);
          switch(cellType){
            case "CellDivide":
              dish[i][j] = new CellDivide(i, j, mass);
              break;
            case "CellStationary":
              dish[i][j] = new CellStationary(i, j, mass);
              break;
            case "CellMoveUp":
              dish[i][j] = new CellMoveUp(i, j, mass);
              break;
            case "CellMoveToggle":
              dish[i][j] = new CellMoveToggle(i, j, mass);
              break;
            case "CellMoveDiagonal":
              dish[i][j] = new CellMoveDiagonal(i, j, mass);
              break;
            case "CellMoveToggleChild":
              dish[i][j] = new CellMoveToggleChild(i, j, mass);
              break;
          }
        }
      }
    }
  }
}
