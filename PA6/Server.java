/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#6 Part 2. It is used to as a server which
 * contains the IDs of infected students.
 */

import java.util.*;

/**
 * This class contains a consctructor and 2 methods that are used
 * to add and hold the IDs of infected students.
 * 
 * Instance variables:
 * infectedIds - An array list that contains the IDs of infected students
 */
public class Server {
  public ArrayList<Integer> infectedIds;

  /**
   * The constructor initializes a new (empty) infectedIds array list.
   */
  public Server(){
    infectedIds = new ArrayList<Integer>();
  }

  /**
   * This method adds IDs of infected students to the infectedIds array list.
   *
   * @param ids - An array list containing the IDs of infected students
   * @return True or False depending on the whether the method is successful
   */
  public boolean addInfectedIds(ArrayList<Integer> ids){
    //If ids is null return false
    if (ids == null){
      return false;
    }
    for (int id : ids){
      infectedIds.add(id);
    }
    return true;
  }

  /**
   * This method returns a deep copy of the infectedIds array list.
   *
   * @return Deep copy of infectedIds array list
   */
  public ArrayList<Integer> getInfectedIds(){
    ArrayList<Integer> newInfectedIds = new ArrayList<Integer>();
    //Add all elements in infectedIds to new array list
    for (int id : infectedIds){
      newInfectedIds.add(id);
    }
    return newInfectedIds;
  }
}