/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used: None
 * 
 * This file is used for PA#3 Parts 1-3.
 * It is used to encode and decode ciphers
 * using the Vigenère Cipher algorithm
 */

/**
 * This class contains 6 methods that, when used together,
 * allow for the encoding and decoding of ciphers.
 */
public class Cipher {

  /**
   * This method checks if a character is lowercase.
   *
   * @return Boolean (True or False)
   */
  public static boolean isLowerCase(char letter){
    int asciival = letter;
    //Checks if character is within the ascii values for lowercase characters
    if (asciival >= 97 && asciival <= 122){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * This method checks if a string is lowercase.
   *
   * @return Boolean (True or False)
   */
  public static boolean isLowerCase(String str){
    //If the string is empty it is still valid
    if (str == ""){
      return true;
    }
    //If the string is null it is invalid
    else if (str == null){
      return false;
    }
    else{
      //Loops through string to check if all characters are lowercase 
      for (int i = 0; i < str.length(); i++){
        char c = str.charAt(i);
        if (!isLowerCase(c)){
          return false;
        }
      }
    }
    return true;
  }

  /**
   * This method encodes a plain text using a key,
   * both of which are characters.
   *
   * @return Encoded character plaintext
   */
  public static char caesarShiftEncode(char plaintext, char key){
    //Checks if plaintext and key are valid
    if (!isLowerCase(plaintext) || !isLowerCase(key)){
      return plaintext;
    }
    else{
      int asciiplain = plaintext;
      int asciikey = key;
      int encode = 
      //Formula to encode using Caesar shift
      ((asciiplain - 97) + (asciikey - 97)) % 26 + 97; 
      return (char) encode;
    }
  }

  /**
   * This method decodes a cipher text using a key,
   * both of which are characters.
   *
   * @return Decoded character ciphertext
   */
  public static char caesarShiftDecode(char ciphertext, char key){
    //Checks if ciphertext and key are valid
    if (!isLowerCase(ciphertext) || !isLowerCase(key)){
      return ciphertext;
    }
    else{
      int asciicipher = ciphertext;
      int asciikey = key;
      if (asciicipher > asciikey){
        int decode = 
        //Formula to decode when ascii value of ciphertext is greater than key
        Math.abs((asciicipher - 97) - (asciikey - 97)) % 26 + 97;
        return (char) decode;
      }
      else{
        int decode = 
        //Formula to decode when ascii value of key is greater than ciphertext
        (26 - Math.abs((asciicipher - 97) - (asciikey - 97))) % 26 + 97;
        return (char) decode;
      }
    }
  }

  /**
   * This method encodes a plain text using a key,
   * both of which are strings.
   *
   * @return Encoded string plaintext
   */
  public static String vigenereEncode(String plaintext, String key){
    //For Part 4 of PA3
    //Checks if key and plaintext are valid
    if (key == "" || !isLowerCase(plaintext) || !isLowerCase(key)){
      return null;
    }
    else{
      int counter = 0;
      //Create an empty string to add the encoded characters to
      String vigEncode = "";
      //Loop through plaintext
      for (int i = 0; i < plaintext.length(); i++){
        //Counter to check if the key needs to wrap around
        if (counter > key.length() - 1){
          counter = 0;
        }
        //Add encoded character to vigEncode
        vigEncode += 
        caesarShiftEncode(plaintext.charAt(i), key.charAt(counter));
        counter++;
    }
      return vigEncode;
    }
  }

  /**
   * This method decodes a cipher text using a key,
   * both of which are strings.
   *
   * @return Decoded string ciphertext
   */
  public static String vigenereDecode(String ciphertext, String key){
    //For Part 4 of PA3
    //Checks if key and ciphertext are valid
    if (key == "" || !isLowerCase(ciphertext) || !isLowerCase(key)){
      return null;
    }
    else{
      int counter = 0;
      //Create an empty string to add the decoded characters to
      String vigDecode = "";
      //Loop through ciphertext
      for (int i = 0; i < ciphertext.length(); i++){
        //Counter to check if the key needs to wrap around
        if (counter > key.length() - 1){
          counter = 0;
        }
        //Add decoded character to vigDecode
        vigDecode += 
        caesarShiftDecode(ciphertext.charAt(i), key.charAt(counter));
        counter++;
      }
      return vigDecode;
    }
  }
}