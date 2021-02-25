package PA3;

/**
 * Name: Bryce Ong
 * ID: A16287711
 * Email: bong@ucsd.edu
 * Sources used:  Zybooks chapter 5.4 (findGCD & findLCM)
 * 
 * This file is used for PA#3 Part 4.
 * It is used to encode and decode ciphers
 * using the Vernam Cipher algorithm.
 */

/**
 * This class contains 5 methods that, when used together,
 * allow for the encoding and decoding of ciphers.
 */
public class CipherEC {

  /**
   * This method finds the greatest common divisor of two values.
   *
   * @return GCD of aVal and bVal
   */
  public static int findGCD(int aVal, int bVal) {
    int numA;
    int numB;
    numA = aVal;
    numB = bVal;

    while (numA != numB) { // Euclid's algorithm
      if (numB > numA) {
        numB = numB - numA;
      } 
      else {
        numA = numA - numB;
      }
    }
    return numA;
  }

  /**
   * This method finds the lowest common multiple of two values.
   *
   * @return LCM of aVal and bVal
   */
  public static int findLCM(int aVal, int bVal){
    int lcmVal;
    lcmVal = Math.abs(aVal * bVal) / findGCD(aVal, bVal);
    return lcmVal;
  }

  /**
   * This method creates a long key using two short keys.
   *
   * @return Long key for Vernam Cipher
   */
  public static String computeVernamLongKey(String key1, String key2){
    //Find LCM for the two keys
    int lcmVal = findLCM(key1.length(), key2.length());
    //Repeats key1 until its length reaches the LCM
    while(key1.length() < lcmVal){
      key1 += key1;
    }
    //Repeats key2 until its length reaches the LCM
    while(key2.length() < lcmVal){
      key2 += key2;
    }
    //Creates long key using the two keys
    String longKey = Cipher.vigenereEncode(key1, key2);
    return longKey;
  }

  /**
   * This method encodes plaintext using the long key
   *
   * @return Encoded plaintext
   */
  public static String vernamEncode(String plaintext, String key1, String key2){
    String longKey = computeVernamLongKey(key1, key2);
    String encode = Cipher.vigenereEncode(plaintext, longKey);
    return encode;
  }

  /**
   * This method decodes ciphertext using the long key
   *
   * @return Decoded ciphertext
   */
  public static String vernamDecode(String ciphertext, String key1, String key2){
    String longKey = computeVernamLongKey(key1, key2);
    String decode = Cipher.vigenereDecode(ciphertext, longKey);
    return decode;
  }
}