import java.io.*;
import java.util.*;

public class InfectionTracking {
  public static int populateArrays(String pathToFile, String[] names,
  int[] locations, int[] movements,
  int[] infections) throws IOException{
    int arrayCounter = 0;
    int largestLocation = 0;
    Scanner scan = new Scanner(new File(pathToFile));
    while (scan.hasNextLine()){
      String[] values = scan.nextLine().split(",");
      names[arrayCounter] = values[0];
      locations[arrayCounter] = Integer.parseInt(values[1]);
      movements[arrayCounter] = Integer.parseInt(values[2]);
      infections[arrayCounter] = Integer.parseInt(values[3]);
      arrayCounter += 1;
    }
    for (int i = 0; i < locations.length; i++) {
      if (locations[i] > largestLocation) {
        largestLocation = locations[i];
      }
    }
    if (largestLocation >= 0){
      return largestLocation + 1;
    }
    else{
      return -1;
    }
  }

  public static void updateLocations(int worldSize, int[] locations,
  int[] movements) {
    if (worldSize > 0 && movements != null && locations != null && 
    locations.length > 0 && (locations.length == movements.length)){
      for (int j = 0; j < locations.length; j++){
        if (locations[j] < 0 || locations[j] >= worldSize){
          return;
        }
      }
      for (int i = 0; i < locations.length; i++) {
        int newLocation = locations[i] + movements[i];
        if (newLocation >= 0){
          newLocation %= worldSize;
        }
        else{
          newLocation = worldSize - Math.abs(newLocation % worldSize);
        }
        locations[i] = newLocation;
      }
      return;
    }
    return;
  }

  public static int[] updateInfections(int worldSize, int[] locations,
  int[] infections) {
    if (worldSize > 0 && locations.length > 0 && infections != null 
    && locations != null && (locations.length == infections.length)){
      int[] numStudentsInfected = new int[infections.length];
      for (int j = 0; j < locations.length; j++){
        if (locations[j] < 0 || locations[j] >= worldSize ||
        !(infections[j] == 0 || infections[j] == 1)){
          return null;
        }
      }
      for (int i = 0; i < locations.length; i++){
        if (infections[i] == 0){
          int numInfected = 0;
          for (int k = 0; k < locations.length; k++){
            if (locations[i] == locations[k] && infections[k] != 0){
              numInfected += 1;
              infections[i] = 1;
              numStudentsInfected[k] = numInfected;
            }
          }
        }
      }
      return numStudentsInfected;
    }
    return null;
  }

  public static void main(String args[]) {
    String[] names = new String[5];
    int[] locations = new int[5];
    int[] movements = new int[5];
    int[] infections = new int[5];
    String path = "C:/Users/bryce/Desktop/students.csv";
    try {
      System.out.println(populateArrays(path, names, locations, movements, infections));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    int[] test = new int[5];
    test = updateInfections(5, locations, infections);
    for (int i = 0; i < infections.length; i++){
      System.out.print(test[i] + ",");
    }
    for (int i = 0; i < infections.length; i++){
      System.out.print(infections[i] + ",");
    }
  }
}
