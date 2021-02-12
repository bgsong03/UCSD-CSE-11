import java.util.*;

public class Minecraft {
  public static int[][] rotateFloorPlan(int[][] originalFloorPlan){
    if (originalFloorPlan != null){
      int column = originalFloorPlan.length;
      int row = originalFloorPlan[0].length;
      int [][] newFloorPlan = new int [row][column];
      for (int i = 0; i < column; i++){
        for (int j = 0; j < row; j++){
          newFloorPlan[j][column - 1 - i] = originalFloorPlan[i][j];
        }
      }
      return newFloorPlan;
    }
    return null;
  }

  public static ArrayList<String> getMobsToTest(String[][] groups,
  String infected){
    if (groups != null && infected != null){
      ArrayList<String> newList = new ArrayList<String>();
      for (int row = 0; row < groups.length; row++){
        for (int column = 0; column < groups[row].length; column++){
          if (groups[row][column] == infected){
            for (int i = 0; i < groups[row].length; i++){
              if (groups[row][i] != null && groups[row][i] != infected){
                newList.add(groups[row][i]);
              }
            }
          }
        }
      }
      ArrayList<String> infectedMobs = new ArrayList<String>();
      //Remove any duplicates
      for (String element : newList) {
        if (!infectedMobs.contains(element)) { 
          infectedMobs.add(element); 
        } 
      } 
      return infectedMobs;
    }
    return null;
  }
  public static void main(String args[]){
    String[][] groups = {
      {"Zombie", "Cow", "Creeper", "Guardian", null, "Slime"},
      {"Sheep", "Iron Golem", "Cow"},
      {"Sheep"},
      {"Cow", "Pufferfish", "Squid", "Sheep"}
    };
    ArrayList<String> test = new ArrayList<String>();
    test = getMobsToTest(groups, "Sheep");
    for (int i = 0; i < test.size();i++){ 		      
      System.out.println(test.get(i)); 		
    }   
  }
}
