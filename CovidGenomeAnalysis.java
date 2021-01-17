/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#2.
 * It contains the class CovidGenomeAnalysis
 * which is used for Part 1 of the PA.
 */
import java.util.Scanner;

/**
 * This class asks for a genome sequence.
 * It will then output a new sequence, that is the 
 * opposite of the inputted sequence, and the number of Ts
 * in the new sequence.
 */
public class CovidGenomeAnalysis {
  public static void main(String[] args){
    String new_seq = "";
    int counter = 0;

    // Ask user to input a sequence
    Scanner scan = new Scanner(System.in);
    System.out.println("");
    String sequence = scan.nextLine();

    // Loops through the entire sequence and creates a new opposite sequence
    for (int i = 0; i < sequence.length(); i++){
      if (sequence.charAt(i) == 'A'){
        new_seq += 'T';
        counter += 1;
      }
      else if (sequence.charAt(i) == 'T'){
        new_seq += 'A';
      }
      else if (sequence.charAt(i) == 'G'){
        new_seq += 'C';
      }
      else if (sequence.charAt(i) == 'C'){
        new_seq += 'G';
      }
    }
    System.out.println(counter + " " + new_seq);
  }
}