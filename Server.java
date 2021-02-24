import java.util.*;

public class Server {
  public ArrayList<Integer> infectedIds;

  public Server(){
    infectedIds = new ArrayList<Integer>();
  }
  public boolean addInfectedIds(ArrayList<Integer> ids){
    if (ids == null){
      return false;
    }
    for (int id : ids){
      infectedIds.add(id);
    }
    return true;
  }
  public ArrayList<Integer> getInfectedIds(){
    ArrayList<Integer> newInfectedIds = new ArrayList<Integer>();
    for (int id : infectedIds){
      newInfectedIds.add(id);
    }
    return newInfectedIds;
  }
}
