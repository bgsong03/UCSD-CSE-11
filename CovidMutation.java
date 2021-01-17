/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#2.
 * It contains the class CovidMutation
 * which is used for Part 2 of the PA
 */
import java.util.Scanner;

/**
 * This class asks for a genome sequence and an integer k. 
 * It then reverses k-sized chunks of the sequence.
 */
public class CovidMutation {
  public static void main(String[] args){
    String reverse = "";
    int k = 0;

    // Ask user to input a sequence and an integer k
    Scanner scan = new Scanner(System.in);
    System.out.println("");
    String original = scan.nextLine();
    System.out.println("");
    k = scan.nextInt();
    
    // Only able to continue if k is greater or equal to 1
    if (k >= 1){
      // Reverses the sequence in k-sized chunks
      for (int i = 0; i < original.length(); i += k){
        for (int j = i + k - 1; j >= i; j--){
          if (j > original.length() - 1){
            for (int m = original.length() -1; m >= i; m--){
              reverse += original.charAt(m);
            }
            break;
          }
          reverse += original.charAt(j);
        }
      }
      System.out.println(reverse);
    }
    // Print original sequence if k is negative or 0
    else{
      System.out.println(original);
    }
  }
}