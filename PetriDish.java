/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#7 Part 2. It is used to hold the information
 * of a petri dish and the cells growing on it.
 */

/**
 * This class contains 1 method and is used to contain and add information 
 * to the petri dish.
 * 
 * Instance variables:
 * dish - a 2D array representing the petri dish in the current iteration
 */
public class PetriDish {
  public Cell[][] dish;

  /**
   * This constructor initializes the information of the petridish. 
   * 
   * @param board - a 2D array of Strings representing what cells the 
   * dish should be filled with
   */
  public PetriDish(String[][] board) {
    dish = new Cell[board.length][board[0].length];
    //Looping through board
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        //If an element in board is null then it will also be null in dish
        if (board[i][j] == "null") {
          dish[i][j] = null;
        } 
        else {
          //Spliting up the information found in board
          String[] cellInfo = board[i][j].strip().split(" ");
          //The first part of the string contains the cell type
          String cellType = cellInfo[0];
          //The second part of the string contains the mass
          int mass = Integer.parseInt(cellInfo[1]);
          //Creating a new object depending on its cell type
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