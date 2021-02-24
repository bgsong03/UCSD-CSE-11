import java.util.*;

public class Student {
  public int id;
  public int location;
  public boolean covidPositive;
  public boolean inQuarantine;
  public ArrayList<Integer> usedIds;
  public ArrayList<ContactInfo> contactHistory;

  public Student(){
    id = -1;
    location = -1;
    covidPositive = false;
    inQuarantine = false;
    usedIds = new ArrayList<Integer>();
    contactHistory = new ArrayList<ContactInfo>();
  }

  public boolean setLocation(int newLocation){
    if (newLocation >= 0 && inQuarantine == false){
      location = newLocation;
      return true;
    }
    return false;
  }

  public void updateId(){
    Random random = new Random();
    id = random.nextInt(Integer.MAX_VALUE);
    usedIds.add(id);
  }

  public boolean addContactInfo(ContactInfo info){
    if (info != null && info.isValid()){
      contactHistory.add(info);
      return true;
    }
    return false;
  }

  public boolean uploadAllUsedIds(Server server){
    if (server != null){
      server.addInfectedIds(this.usedIds);
      return true;
    }
    return false;
  }

  public boolean testPositive(Server server){
    covidPositive = true;
    inQuarantine = true;
    return uploadAllUsedIds(server);
  }

  public ArrayList<ContactInfo> getRecentPositiveContacts(Server server,
  int fromTime){
    if (server != null && fromTime >= 0 && server.getInfectedIds() != null){
      ArrayList<ContactInfo> subList = new ArrayList<ContactInfo>();
      for (ContactInfo temp : contactHistory){
        if (temp.used == false && temp.time >= fromTime && 
        server.getInfectedIds().contains(temp.id)){
          subList.add(temp);
        }
      }
      return subList;
    }
    return null;
  }

  public int riskCheck(Server server, int fromTime, boolean quarantineChoice){
    if (getRecentPositiveContacts(server, fromTime) == null){
      return -1;
    }
    
    return 0;
  }
}
