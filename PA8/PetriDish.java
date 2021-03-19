package PA8;
import java.util.*;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#8 Part 3. It is used to hold the information
 * of a petri dish and the cells growing on it.
 */

/**
 * This class contains 1 method and is used to contain and add information 
 * to the petri dish.
 * 
 * Instance variables:
 * dish - a 2D array representing the petri dish in the current iteration
 * movables - a list of all the Movable cells in the petri dish
 * divisibles - a list of all the Divisible cells in the petri dish
 */
public class PetriDish {
  public Cell[][] dish;
  public List<Movable> movables;
  public List<Divisible> divisibles;

  /**
   * This constructor initializes the information of the petridish. 
   * 
   * @param board - a 2D array of Strings representing what cells the 
   * dish should be filled with
   */
  public PetriDish(String[][] board) {
    movables = new ArrayList<Movable>();
    divisibles = new ArrayList<Divisible>();
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

  /**
   * This method gets the neigbors of a cell in the petri dish.
   * 
   * @param row - row value of the cell
   * @param col - column value of the cell
   * @return A list containing all neigbors of the cell
   */
  public List<Cell> getNeighborsOf(int row, int col){
    //Return null if the parameters are out of bounds
    if (row < 0 || col < 0 || row >= dish.length || col >= dish[0].length){
      return null;
    }
    List<Cell> neighbors = new ArrayList<Cell>();
    //Looping through the neigbors starting from northwest
    for (int i = row - 1; i <= row + 1; i++){
      for (int j = col - 1; j <= col + 1; j++){
        int tempi = i;
        int tempj = j;
        //Wrapping for row value
        if (i < 0){
          i = dish.length - 1;
        }
        else if (i >= dish.length){
          i = 0;
        }
        //Wrapping for column value
        if (j < 0){
          j = dish[0].length - 1;
        }
        else if (j >= dish[0].length){
          j = 0;
        }
        //Add cell to list if it is not null and it is not the original cell
        if (dish[i][j] != null && (i != row || j != col)){
          neighbors.add(dish[i][j]);
        }
        i = tempi;
        j = tempj;
      }
    }
    return neighbors;
  }

  /**
   * This method moves all Movable cells based on their getMove() behavior.
   */
  public void move(){
    //List containing cells with equal mass that are contesting a position 
    List<Cell> equalMass = new ArrayList<Cell>();
    //List containing cells that will not be added until the end of division
    List<Cell> tempCells = new ArrayList<Cell>();
    //Add all Movable type cells to movables list
    for (int i = 0; i < dish.length; i++){
      for (int j = 0; j < dish[0].length; j++){
        if (dish[i][j] instanceof Movable){
          movables.add((Movable) dish[i][j]);
        }
      }
    }
    for (Movable currMoveCell : movables){
      Cell currCell = (Cell) currMoveCell;
      int[] position = currMoveCell.getMove();
      //Wrapping around the row value
      if (position[0] % dish.length == 0){
        position[0] = 0;
      }
      else if (position[0] < 0){
        position[0] = dish.length - Math.abs(position[0] % dish.length);
      }
      else if (position[0] >= dish.length){
        position[0] %= dish.length;
      }
      //Wrapping around the column value
      if (position[1] % dish[0].length == 0){
        position[1] = 0;
      }
      else if (position[1] < 0){
        position[1] = dish[0].length - Math.abs(position[1] % dish[0].length);
      }
      else if (position[1] >= dish[0].length){
        position[1] %= dish[0].length;
      }
      
      //Checking if the cell did not move at all
      if (position[0] == currCell.currRow && position[1] == currCell.currCol){
        continue;
      }
      /*Checking if the moving cell will share its position with another 
      Movable cell*/
      else if (dish[position[0]][position[1]] instanceof Movable){
        //Case if current moving cell has greater mass
        if (currCell.compareTo(dish[position[0]][position[1]]) > 0){
          //Checking if the cell exists in the original board
          if (!movables.contains((Movable) 
          dish[position[0]][position[1]])){
            dish[currCell.currRow][currCell.currCol] = null;
            dish[position[0]][position[1]].apoptosis();
            equalMass.remove(equalMass.indexOf
            ((dish[position[0]][position[1]])));
            dish[position[0]][position[1]] = currCell.newCellCopy();
            dish[position[0]][position[1]].currRow = position[0];
            dish[position[0]][position[1]].currCol = position[1];
          }
          else{
            dish[currCell.currRow][currCell.currCol] = null;
            currCell.currRow = position[0];
            currCell.currCol = position[1];
            tempCells.add(currCell);
          }
        }
        //Case if current moving cell has less mass
        else if (currCell.compareTo(dish[position[0]][position[1]]) < 0){
          dish[currCell.currRow][currCell.currCol] = null;
          currCell.apoptosis();
        }
        //Case if both moving cells have equal mass
        else if (currCell.compareTo(dish[position[0]][position[1]]) == 0){
          dish[currCell.currRow][currCell.currCol] = null;
          equalMass.add(dish[position[0]][position[1]]);
        }
      }
      /*Checking if the moving cell will share its position with another 
      non-Movable cell*/
      else if (dish[position[0]][position[1]] != null
      && !(dish[position[0]][position[1]] instanceof Movable)){
        dish[currCell.currRow][currCell.currCol] = null;
        dish[position[0]][position[1]].apoptosis();
        dish[position[0]][position[1]] = currCell.newCellCopy();
        dish[position[0]][position[1]].currRow = position[0];
        dish[position[0]][position[1]].currCol = position[1];
      }
      //Update dish if the moving cell is not sharing its position
      else{
        dish[currCell.currRow][currCell.currCol] = null;
        dish[position[0]][position[1]] = currCell.newCellCopy();
        dish[position[0]][position[1]].currRow = position[0];
        dish[position[0]][position[1]].currCol = position[1];
      }
    }
    //Removes cells if there are multiple largest masses in a position
    for (int m = 0; m < equalMass.size(); m++){
      dish[equalMass.get(m).currRow][equalMass.get(m).currCol] = null;
    }
    //Adds cells that were held during movement
    for (int j = 0; j < tempCells.size(); j++){
      dish[tempCells.get(j).currRow][tempCells.get(j).currCol] = 
      tempCells.get(j);
    }
    //Remove all current movables for next iteration
    movables.clear();
  }

  /**
   * This method divides all Divisable cells based on their
   * getDivision() behavior.
   */
  public void divide(){
    //List containing cells with equal mass that are contesting a position 
    List<Cell> equalMass = new ArrayList<Cell>();
    //List containing cells that will not be added until the end of division
    List<Cell> tempCells = new ArrayList<Cell>();
    //Add all Divisible type cells to divisibles list
    for (int i = 0; i < dish.length; i++){
      for (int j = 0; j < dish[0].length; j++){
        if (dish[i][j] instanceof Divisible){
          divisibles.add((Divisible) dish[i][j]);
        }
      }
    }
    for (Divisible currdivisibleCell : divisibles){
      int[] position = currdivisibleCell.getDivision();
      CellDivide currCell = (CellDivide) currdivisibleCell;
      //Wrapping around the row value
      if (position[0] % dish.length == 0){
        position[0] = 0;
      }
      else if (position[0] < 0){
        position[0] = dish.length - Math.abs(position[0] % dish.length);
      }
      else if (position[0] >= dish.length){
        position[0] %= dish.length;
      }
      //Wrapping around the column value
      if (position[1] % dish[0].length == 0){
        position[1] = 0;
      }
      else if (position[1] < 0){
        position[1] = dish[0].length - Math.abs(position[1] % dish[0].length);
      }
      else if (position[1] >= dish[0].length){
        position[1] %= dish[0].length;
      }
      //Checking if new position for divided cell is empty
      if (dish[position[0]][position[1]] == null){
        dish[position[0]][position[1]] = currCell.newCellCopy();
        dish[position[0]][position[1]].currRow = position[0];
        dish[position[0]][position[1]].currCol = position[1];
      }
      //Checking if new position for divided cell has another Divisible cell
      else if (dish[position[0]][position[1]] instanceof Divisible){
        //Case if current dividing cell has greater mass
        if (currCell.compareTo(dish[position[0]][position[1]]) > 0){
          //Checking if the cell exists in the original board
          if (!divisibles.contains((Divisible) 
          dish[position[0]][position[1]])){
            equalMass.remove(equalMass.indexOf
            ((dish[position[0]][position[1]])));
            dish[position[0]][position[1]] = currCell.newCellCopy();
            dish[position[0]][position[1]].currRow = position[0];
            dish[position[0]][position[1]].currCol = position[1];
          }
          else{
            CellDivide newCell = new CellDivide(currCell);
            newCell.currRow = position[0];
            newCell.currCol = position[1];
            tempCells.add(newCell);
          }
        }
        //Case if both divisible cells have equal mass
        else if (currCell.compareTo(dish[position[0]][position[1]]) == 0){
          equalMass.add(dish[position[0]][position[1]]);
        }
      }
    }
    //Removes cells if there are multiple largest masses in a position
    for (int m = 0; m < equalMass.size(); m++){
      dish[equalMass.get(m).currRow][equalMass.get(m).currCol] = null;
    }
    //Adds cells that were held during division
    for (int j = 0; j < tempCells.size(); j++){
      dish[tempCells.get(j).currRow][tempCells.get(j).currCol] = 
      tempCells.get(j);
    }
    //Remove all current divisibles for next iteration
    divisibles.clear();
  }

  /**
   * This method simultaneously initiates apoptosis for all eligible cells and
   * spawns new cells in eligible empty spaces.
   */
  public void update(){
    //Temporary array which contains a copy of the dish
    Cell[][] dishCopy = new Cell[dish.length][dish[0].length];
    for (int i = 0; i < dish.length; i++){
      for (int j = 0; j < dish[0].length; j++){
        //Checking if element is null
        if (dish[i][j] == null){
          //Checking if new cell is able to be spawned
          if (getNeighborsOf(i, j).size() == 2 || 
          getNeighborsOf(i, j).size() == 3){
            dishCopy[i][j] = getNeighborsOf(i, j).get(0).newCellCopy();
            dishCopy[i][j].currRow = i;
            dishCopy[i][j].currCol = j;
          }
        }
        //Checking if cell is eligible for apoptosis
        else if (dish[i][j] != null
        && dish[i][j].checkApoptosis(getNeighborsOf(i, j)) == false){
          dishCopy[i][j] = dish[i][j];
        }
        //Undergo Apoptosis if the checkApoptosis is true
        else if (dish[i][j] != null
        && dish[i][j].checkApoptosis(getNeighborsOf(i, j)) == true){
          dish[i][j].apoptosis();
        }
        
      }
    }
    dish = dishCopy;
  }

  /**
   * This method moves, divides, and then updates the cells.
   */
  public void iterate(){
    move();
    divide();
    update();
  }
}