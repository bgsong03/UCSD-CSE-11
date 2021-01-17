import java.util.Scanner;

public class CovidGenomeAnalysis {
  public static void main(String[] args){
    String new_seq = "";
    int counter = 0;
    Scanner scan = new Scanner(System.in);
    System.out.print("");
    String seq = scan.nextLine();

    for (int i = 0; i < seq.length(); i++){
      if (seq.charAt(i) == 'A'){
        new_seq += 'T';
        counter += 1;
      }
      else if (seq.charAt(i) == 'T'){
        new_seq += 'A';
      }
      else if (seq.charAt(i) == 'G'){
        new_seq += 'C';
      }
      else if (seq.charAt(i) == 'C'){
        new_seq += 'G';
      }
    }
    System.out.println(counter + " " + new_seq);
  }
}
