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
    if (checkValidInputsLocations(worldSize, locations, movements)){
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
    if(checkValidInputsInfections(worldSize, locations, infections)){
      int[] numStudentsInfected = new int[infections.length];
      if(numStudentsInfected != null){
        for (int i = 0; i < locations.length; i++){
          if (infections[i] == 1){
            int numInfected = 0;
            for (int k = 0; k < locations.length; k++){
              if (locations[i] == locations[k] && infections[k] == 0){
                numInfected += 1;
                infections[k] = 2;
              }
            }
            for (int m = 0; m < infections.length;m++){
              if (locations[i] == locations[m] && infections[m] == 1){
                numStudentsInfected[m] += numInfected;
                infections[m] = 2;
              }
            }
          }
          
        }
        for (int s = 0; s < infections.length; s++){
          if (infections[s] == 2){
            infections[s] = 1;
          }
        }
        return numStudentsInfected;
      }
    }

    return null;
  }

  public static int[] countInfectionsByStudent(int days, int worldSize,
  int[] locations, int[] movements, int[]infections){
    if (checkValidInputsLocations(worldSize, locations, movements)){
      int[] numStudentsInfected = new int[infections.length];
      int[] tempArray = new int[infections.length];
      for (int i = 1; i <= days; i++){
        updateLocations(worldSize, locations, movements);
        tempArray =
        updateInfections(worldSize, locations, infections);
        for (int j = 0; j < tempArray.length; j++){
          numStudentsInfected[j] += tempArray[j];
        }
      }
      
      return numStudentsInfected;
    }
    return null;
  }

  public static int findRNaught(int[] infectionRecord){
    if (infectionRecord != null && infectionRecord.length > 0){
      int sum = 0;
      int average = 0;
      for (int i = 0; i < infectionRecord.length; i++){
        if (infectionRecord[i] < 0){
          return -1;
        }
        sum += infectionRecord[i];
      }
      average = (sum + infectionRecord.length - 1) / infectionRecord.length;
      return average;
    }

    return -1;
  }

  public static String findSuperSpreader(int[] infectionRecord,
  String[] names){
    if (infectionRecord != null && names != null &&
    infectionRecord.length > 0 && infectionRecord.length == names.length){
      int greatestInfections = 0;
      for (int i = 0; i < names.length; i++){
        if (infectionRecord[i] < 0){
          return null;
        }
        else if (infectionRecord[i] > greatestInfections){
          greatestInfections = infectionRecord[i];
        }
      }
      for (int j = 0; j < names.length; j++){
        if (infectionRecord[j] == greatestInfections){
          return names[j];
        }
      }
    }
    return null;
  }

  private static boolean checkValidInputsLocations(int worldSize,
  int[] locations, int[] movements){
    if (worldSize > 0 && movements != null && locations != null && 
    locations.length > 0 && (locations.length == movements.length)){
      for (int j = 0; j < locations.length; j++){
        if (locations[j] < 0 || locations[j] >= worldSize){
          return false;
        }
      }
      return true;
    }
    return false;
  }

  private static boolean checkValidInputsInfections(int worldSize, 
  int[] locations, int[] infections){
    if (worldSize > 0 && locations.length > 0 && infections != null 
    && locations != null && (locations.length == infections.length)){
      for (int j = 0; j < locations.length; j++){
        if (locations[j] < 0 || locations[j] >= worldSize ||
        !(infections[j] == 0 || infections[j] == 1)){
          return false;
        }
      }
      return true;
    }
    return false;
  }
}
