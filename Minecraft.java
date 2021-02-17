/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#5 Parts 1-2. It is used to complete
 * certain problems in the game world of Minecraft.
 */
import java.util.*;

/**
 * This class contains 2 methods that both deal with problems found
 * in the world of Minecraft
 */
public class Minecraft {
  /**
   * This method rotates a given floor plan 90 degrees clockwise.
   *
   * @param originalFloorPlan - A 2D array containing the original floor plan.
   * @return A 2D array containing the new, rotated floor plan.
   */
  public static int[][] rotateFloorPlan(int[][] originalFloorPlan){
    //Checks if the floor plan is null
    if (originalFloorPlan != null){
      final int column = originalFloorPlan.length;
      final int row = originalFloorPlan[0].length;
      //New 2D array for new floor plan
      int[][] newFloorPlan = new int[row][column];
      //Rotates original floor plan 90 degrees clockwise
      for (int i = 0; i < column; i++){
        for (int j = 0; j < row; j++){
          newFloorPlan[j][column - 1 - i] = originalFloorPlan[i][j];
        }
      }
      //Return new floor plan when method is finished
      return newFloorPlan;
    }
    //Return null if the floor plan is null
    return null;
  }

  /**
   * This method finds which Minecraft mobs need to be tested based on
   * their proximity with an infected mob during a party.
   *
   * @param groups - A 2D array containing the different groups of mobs that
   * were formed during the party.
   * @param infected - The infected mob.
   * @return An ArrayList that contains all the mobs that need to be tested.
   */
  public static ArrayList<String> getMobsToTest(String[][] groups,
  String infected){
    //Checks if either groups or infected is null
    if (groups != null && infected != null){
      //Create a temporary list that contains duplicates
      ArrayList<String> tempList = new ArrayList<String>();
      //Final array list with no duplicates
      ArrayList<String> MobsToTest = new ArrayList<String>();
      for (int row = 0; row < groups.length; row++){
        for (int column = 0; column < groups[row].length; column++){
          //Checks if a group contains an infected mob
          if (groups[row][column] == infected){
            /*Adds all mobs aside from the original infected mob to the 
            temp list*/
            for (int i = 0; i < groups[row].length; i++){
              //Checks if the string is null or is the original infected mob
              if (groups[row][i] != null && groups[row][i] != infected){
                tempList.add(groups[row][i]);
              }
            }
          }
        }
      }
      //Remove any duplicates
      for (String element : tempList) {
        if (!MobsToTest.contains(element)) { 
          MobsToTest.add(element); 
        } 
      }
      //Returns array list with mobs to test if valid
      return MobsToTest;
    }
    //Returns null if either parameter is null
    return null;
  }
}