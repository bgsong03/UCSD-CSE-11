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
      ArrayList<String> infectedMobs = new ArrayList<String>();
      return infectedMobs;
    }
    return null;
  }
  public static void main(String args[]){
    String[][] groups = {
      {"Zombie", "Cow", "Creeper", "Guardian", null, "Slime"},
      {"Sheep", "Iron Golem", "Cow"},
      {"Sheep"},
      {"Cow", "Puf erfish", "Squid", "Sheep"}
    };
    ArrayList<String> test = new ArrayList<String>();
    test = getMobsToTest(groups, "Sheep");
    for (int i = 0; i < test.size();i++){ 		      
      System.out.println(test.get(i)); 		
    }   
  }
}
