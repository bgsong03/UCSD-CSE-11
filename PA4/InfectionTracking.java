package PA4;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#4 Parts 1-4.
 * It is used to map out the transmission pattern of COVID
 * among UCSD students.
 */

import java.io.*;
import java.util.*;

/**
 * This class contains 6 public methods and 2 private helper methods.
 * When used togther, they can track the infection of COVID among
 * UCSD students.
 */
public class InfectionTracking {

  /**
   * This method populates the 4 arrays in the parameters using a read file.
   *
   * @param pathToFile - The path to the file being read.
   * @param names - An array containing the names of each student.
   * @param locations - An array containing the locations of each student.
   * @param movements - An array containing the movements of each student.
   * @param infections - An array containing the COVID state of each student
   * (infected or not infected).
   * @return Largest location value of students + 1
   */
  public static int populateArrays(String pathToFile, String[] names,
  int[] locations, int[] movements,
  int[] infections) throws IOException{
    int arrayCounter = 0;
    int largestLocation = 0;
    //Load and scan file
    Scanner scan = new Scanner(new File(pathToFile));
    //Loads contents of file into arrays until there is no next line
    while (scan.hasNextLine()){
      String[] values = scan.nextLine().split(",");
      names[arrayCounter] = values[0];
      locations[arrayCounter] = Integer.parseInt(values[1]);
      movements[arrayCounter] = Integer.parseInt(values[2]);
      infections[arrayCounter] = Integer.parseInt(values[3]);
      arrayCounter += 1;
    }
    //Finds largest location value
    for (int i = 0; i < locations.length; i++) {
      if (locations[i] > largestLocation) {
        largestLocation = locations[i];
      }
    }
    if (largestLocation >= 0){
      return largestLocation + 1;
    }
    //If the location value is less than 0 it is invalid
    else{
      return -1;
    }
  }

  /**
   * This method updates the locations of students based on their movements.
   *
   * @param worldSize - The size of the 1D world being used by the students.
   * @param locations - An array containing the locations of each student.
   * @param movements - An array containing the movements of each student.
   */
  public static void updateLocations(int worldSize, int[] locations,
  int[] movements) {
    //Checking for valid inputs
    if (checkValidInputsLocations(worldSize, locations, movements)){
      for (int i = 0; i < locations.length; i++) {
        int newLocation = locations[i] + movements[i];
        //New location if locations + movements is greater or equal to zero
        if (newLocation >= 0){
          newLocation %= worldSize;
        }
        //New location if locations + movements is less than zero
        else{
          newLocation = worldSize - Math.abs(newLocation % worldSize);
        }
        locations[i] = newLocation;
      }
      return;
    }
    //Return and change nothing if there are invalid inputs
    return;
  }

  /**
   * This method updates the infection state of students based on their 
   * location and also tracks how many other students they have infected.
   *
   * @param worldSize - The size of the 1D world being used by the students.
   * @param locations - An array containing the locations of each student.
   * @param infections - An array containing the COVID state ofeach student
   * (infected or not infected).
   * @return An array containing the number of infections caused by 
   * each student.
   */
  public static int[] updateInfections(int worldSize, int[] locations,
  int[] infections){
    //Checking for valid inputs
    if(checkValidInputsInfections(worldSize, locations, infections)){
      //New array that tracks the infections of each student
      int[] numStudentsInfected = new int[infections.length];
      for (int i = 0; i < locations.length; i++){
        if (infections[i] == 1){
          int numInfected = 0;
          for (int k = 0; k < locations.length; k++){
            /*Checking if an infected student shares a location with a
            non-infected student*/
            if (locations[i] == locations[k] && infections[k] == 0){
              numInfected += 1;
              infections[k] = 2;
            }
          }
          //Adding up the number of infections made by each student
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
    //Return null if there are invalid inputs
    return null;
  }

  /**
   * This method simulates the spread of COVID given a number of days
   * and the students' locations, movements, and infection states.
   *
   * @param days - The number of days the simulation will run.
   * @param worldSize - The size of the 1D world being used by the students.
   * @param locations - An array containing the locations of each student.
   * @param movements - An array containing the movements of each student.
   * @param infections - An array containing the COVID state of each student
   * (infected or not infected).
   * @return An array containing the number of infections caused by each 
   * student over the course of the given days.
   */
  public static int[] countInfectionsByStudent(int days, int worldSize,
  int[] locations, int[] movements, int[]infections){
    //Checking for valid inputs
    if (checkValidInputsLocations(worldSize, locations, movements)){
      //Array for total number of infections
      int[] numStudentsInfected = new int[infections.length];
      //Temporary array for infections per day
      int[] tempArray = new int[infections.length];
      for (int i = 1; i <= days; i++){
        updateLocations(worldSize, locations, movements);
        tempArray =
        updateInfections(worldSize, locations, infections);
        //Adds all the infections for the day to the first array
        for (int j = 0; j < tempArray.length; j++){
          numStudentsInfected[j] += tempArray[j];
        }
      }
      return numStudentsInfected;
    }
    //Return null if there are invalid inputs
    return null;
  }

  /**
   * This method finds the average number of students who will contract
   * COVID from another students who is infected.
   *
   * @param infectionRecord - An array containing the number of infections
   * caused by each student.
   * @return Average number of infections made by each student
   */
  public static int findRNaught(int[] infectionRecord){
    //Checking for valid inputs
    if (infectionRecord != null && infectionRecord.length > 0){
      int sum = 0;
      //Finding sum of all infections
      for (int i = 0; i < infectionRecord.length; i++){
        //Return -1 if there are invalid elements
        if (infectionRecord[i] < 0){
          return -1;
        }
        sum += infectionRecord[i];
      }
      //Average number of infections made by each student
      int average = 
      (sum + infectionRecord.length - 1) / infectionRecord.length;
      return average;
    }
    //Return -1 if there are invalid inputs
    return -1;
  }

  /**
   * This method finds the student who is causing the most infections.
   *
   * @param infectionRecord - An array containing the number of infections
   * caused by each student.
   * @param names - An array containing the names of each student.
   * @return Name of student who caused the most amount of infections.
   */
  public static String findSuperSpreader(int[] infectionRecord,
  String[] names){
    //Checking for valid inputs
    if (infectionRecord != null && names != null &&
    infectionRecord.length > 0 && (infectionRecord.length == names.length)){
      int greatestInfections = 0;
      //Find largest amount of infections
      for (int i = 0; i < names.length; i++){
        //Return null if there are invalid elements
        if (infectionRecord[i] < 0){
          return null;
        }
        else if (infectionRecord[i] > greatestInfections){
          greatestInfections = infectionRecord[i];
        }
      }
      //Find first student with the largest amount of infections
      for (int j = 0; j < names.length; j++){
        if (infectionRecord[j] == greatestInfections){
          return names[j];
        }
      }
    }
    //Return null if there are invalid inputs
    return null;
  }

  /**
   * This method is a private helper method to check for valid
   * inputs in the updateLocations method
   *
   * @param worldSize - The size of the 1D world being used by the students.
   * @param locations - An array containing the locations of each student.
   * @param movements - An array containing the movements of each student.
   * @return Boolean for valid or invalid input (True or False)
   */
  private static boolean checkValidInputsLocations(int worldSize,
  int[] locations, int[] movements){
    //Checking for valid inputs
    if (worldSize > 0 && movements != null && locations != null && 
    locations.length > 0 && (locations.length == movements.length)){
      for (int j = 0; j < locations.length; j++){
        if (locations[j] < 0 || locations[j] >= worldSize){
          //Return false if there are invalid elements
          return false;
        }
      }
      return true;
    }
    //Return false if there are invalid inputs
    return false;
  }

  /**
   * This method is a private helper method to check for valid
   * inputs in the updateInfections method
   *
   * @param worldSize - The size of the 1D world being used by the students.
   * @param locations - An array containing the locations of each student.
   * @param infections - An array containing the COVID state of each student
   * (infected or not infected).
   * @return Boolean for valid or invalid input (True or False)
   */
  private static boolean checkValidInputsInfections(int worldSize, 
  int[] locations, int[] infections){
    //Checking for valid inputs
    if (worldSize > 0 && locations != null && infections != null
    && locations.length > 0  && (locations.length == infections.length)){
      for (int j = 0; j < locations.length; j++){
        if (locations[j] < 0 || locations[j] >= worldSize ||
        !(infections[j] == 0 || infections[j] == 1)){
          //Return false if there are invalid elements
          return false;
        }
      }
      return true;
    }
    //Return false if there are invalid inputs
    return false;
  }
}