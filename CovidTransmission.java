/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for the Covid Transmission PA.
 * It determines the risk level of infection through the number of 
 * minutes of contact between two people.
 */
import java.util.Scanner;

public class CovidTransmission{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.print("");
    int D1 = input.nextInt();
    int H1 = input.nextInt();
    int M1 = input.nextInt();
    int D2 = input.nextInt();
    int H2 = input.nextInt();
    int M2 = input.nextInt();
    int min = (((D2-D1)*1440)+((H2-H1)*60)+(M2-M1));

    if (D1<1 || D1>31 || D2<1 || D2>31){
      System.out.println("-1 -1");
    }
    else if (H1<0 || H1>23 || H2<0 || H2>23){
      System.out.println("-1 -1");
    }
    else if (M1<0 || M1>59 || M2<0 || M2>59){
      System.out.println("-1 -1");
    }
    else if (min<0){
      System.out.println("-1 -1");
    }
    else{
      if (min>=0 && min<=60){
        System.out.println(min + " low");
      }
      else if (min>60 && min<=180){
        System.out.println(min + " medium");
      }
      else if (min>180 && min<=360){
        System.out.println(min + " high");
      }
      else if (min>360){
        System.out.println(min + " HIGH");
      }
    }
  }
}