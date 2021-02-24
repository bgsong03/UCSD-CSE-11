import java.util.*;

public class Student {
  public int id;
  public int location;
  public boolean covidPositive;
  public boolean inQuarantine;
  public ArrayList<Integer> usedIds;
  public ArrayList<ContactInfo> contactHistory;

  public Student(){

  }
  public boolean setLocation(int newLocation){
    return true;
  }
  public void updateId(){

  }
  public boolean addContactInfo(ContactInfo info){
    return true;
  }
  public boolean uploadAllUsedIds(Server server){
    return true;
  }
  public boolean testPositive(Server server){
    return true;
  }
  public ArrayList<ContactInfo> getRecentPositiveContacts(Server server,
  int fromTime){
    return contactHistory;
  }
  public int riskCheck(Server server, int fromTime, boolean quarantineChoice){
    return 1;
  }
}
