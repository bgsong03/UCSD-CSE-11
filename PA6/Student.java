package PA6;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#6 Part 3. It is used to hold the information
 * of the students.
 */

import java.util.*;

/**
 * This class contains a consctructor and 7 methods that are used
 * to add, update, and hold information regarding the students.
 * 
 * Instance variables:
 * id - A random positive id given to the student
 * location - The current location of the student
 * covidPositive - Indicator for if the student has tested positive
 * inQuarantine - Indicator for if the student is in quarantine
 * usedIds - An array list containing all IDs the student has used
 * contactHistory - An array list containing the contact information that
 * have been sent to the student
 */
public class Student {
  public int id;
  public int location;
  public boolean covidPositive;
  public boolean inQuarantine;
  public ArrayList<Integer> usedIds;
  public ArrayList<ContactInfo> contactHistory;

  /**
   * The constructor initializes the various information of the student.
   */
  public Student(){
    id = -1;
    location = -1;
    covidPositive = false;
    inQuarantine = false;
    usedIds = new ArrayList<Integer>();
    contactHistory = new ArrayList<ContactInfo>();
  }

  /**
   * This method updates the location of the student to their new location
   *
   * @param newLocation - The new location of the student
   * @return True or False depending on the whether the method is successful
   */
  public boolean setLocation(int newLocation){
    //Checking for valid inputs
    if (newLocation >= 0 && inQuarantine == false){
      location = newLocation;
      return true;
    }
    return false;
  }

  /**
   * This method updates the student's ID to a new random positive integer.
   */
  public void updateId(){
    //Create random integer from 0 to the maximum integer value
    Random random = new Random();
    id = random.nextInt(Integer.MAX_VALUE);
    usedIds.add(id);
  }

  /**
   * This method adds the contact information made between students to
   * their contact history.
   *
   * @param info - The contact information between the two students
   * @return True or False depending on the whether the method is successful
   */
  public boolean addContactInfo(ContactInfo info){
    //Checking for valid inputs
    if (info != null && info.isValid()){
      contactHistory.add(info);
      return true;
    }
    return false;
  }

  /**
   * This method uploads all IDs the student has used to the server
   *
   * @param server - The server which will be holding the information
   * @return True or False depending on the whether the method is successful
   */
  public boolean uploadAllUsedIds(Server server){
    if (server != null){
      return server.addInfectedIds(this.usedIds);
    }
    return false;
  }

  /**
   * This method updates the status of the student if they are Covid positive.
   * Afterwards, it adds all IDs the student has used to the server.
   *
   * @param server - The server which will be holding the information
   * @return True or False depending on the whether the method is successful
   */
  public boolean testPositive(Server server){
    covidPositive = true;
    inQuarantine = true;
    return uploadAllUsedIds(server);
  }

  /**
   * This method gets the recent Covid-positive contacts the student has made.
   *
   * @param server - The server which will be holding the information
   * @param fromTime - The starting time from which to check the contacts
   * @return A sub list of contactHistory that contains the recent 
   * Covid-positive contacts the student has made
   */
  public ArrayList<ContactInfo> getRecentPositiveContacts(Server server,
  int fromTime){
    //Checking for valid inputs
    if (server != null && server.getInfectedIds() != null && fromTime >= 0){
      ArrayList<ContactInfo> subList = new ArrayList<ContactInfo>();
      for (ContactInfo temp : contactHistory){
        //If the conditions are met, add contact information to sub list
        if (temp.used == false && temp.time >= fromTime && 
        server.getInfectedIds().contains(temp.id)){
          subList.add(temp);
        }
      }
      return subList;
    }
    //Return null if inputs are invalid
    return null;
  }

  /**
   * This method assesses the student's risk of having COVID-19 and notifies
   * other students.
   *
   * @param server - The server which will be holding the information
   * @param fromTime - The starting time from which to check the contacts
   * @param quarantineChoice - The choice of the student to quarantine or not
   * @return 1 or 0 depending on the infection status of the student
   */
  public int riskCheck(Server server, int fromTime, boolean quarantineChoice){
    //Boolean for if the student is infected or not
    boolean isInfected = false;
    //Return -1 if inputs are invalid
    if (getRecentPositiveContacts(server, fromTime) == null){
      return -1;
    }
    //Checking for 3 or more infected contacts 
    if (getRecentPositiveContacts(server, fromTime).size() >= 3){
      isInfected = true;
      //Make all IDs used if conditions are met
      for (ContactInfo contact : getRecentPositiveContacts(server, fromTime)){
        contact.used = true;
      }
      //Quarantine if student is infected and choice is true
      if (isInfected == true && quarantineChoice == true){
        inQuarantine = true;
      }
      return 1;
    }
    else{
      for (ContactInfo contact : getRecentPositiveContacts(server, fromTime)){
        /*Checking if there is a distance of less than or equal to 1 between 
        the student and the infected contact*/
        if (contact.distance <= 1){
          contact.used = true;
          isInfected = true;
        }
      }
      //Quarantine if student is infected and choice is true
      if (isInfected == true && quarantineChoice == true){
        inQuarantine = true;
      }
      //Return 1 if there is risk of infection
      if (isInfected == true){
        return 1;
      }
    }
    //Return 0 is there is no risk of infection
    return 0;
  }
}

