import java.util.Scanner;

public class CovidMutation {
  public static void main(String[] args){
    String reverse = "";

    Scanner scan = new Scanner(System.in);
    System.out.println("");
    String original = scan.nextLine();
    System.out.println("");
    int k = scan.nextInt();

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
}