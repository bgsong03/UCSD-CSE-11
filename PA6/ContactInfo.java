package PA6;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#6 Part 1. It is used to hold the
 * contact information between students.
 */

/**
 * This class contains a consctructor and 1 method that are used
 * to hold contact information.
 * 
 * Instance variables:
 * id - A random positive id given to the other student
 * distance - The distance between the two students
 * time - The time the contact between the two students occured
 * used - Stores whether the contact information has been used
 */
public class ContactInfo {
  public int id;
  public int distance;
  public int time;
  public boolean used;

  /**
   * The constructor initializes the contact information that will be used
   * by the students.
   * 
   * @param id - Value to set the instance variable id to
   * @param distance - Value to set the instance variable distance to
   * @param time - Value to set the instance variable time to
   */
  public ContactInfo(int id, int distance, int time){
    this.id = id;
    this.distance = distance;
    this.time = time;
    //used will always be initialized to false
    used = false;
  }

  /**
   * This method checks if the instance variables are valid.
   *
   * @return True or False depending on the validity
   */
  public boolean isValid(){
    //Checking for valid variables
    if (id >= 0 && distance >= 0 && time >= 0){
      return true;
    }
    return false;
  }
}