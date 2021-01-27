public class Cipher {

  public static boolean isLowerCase(char letter){
    int asciival = letter;
    if (asciival >= 97 && asciival <= 122){
      return true;
    }
    else{
      return false;
    }
  }


  public static boolean isLowerCase(String str){
    if (str == ""){
      return true;
    }
    else if (str == null){
      return false;
    }
    else{
      for (int i = 0; i < str.length(); i++){
        char c = str.charAt(i);
        if (!isLowerCase(c)){
          return false;
        }
      }
    }
    return true;
  }


  public static char caesarShiftEncode(char plaintext, char key){
    if (!isLowerCase(plaintext) || !isLowerCase(key)){
      return plaintext;
    }
    else{
      int asciiplain = plaintext;
      int asciikey = key;
      int encode = ((asciiplain - 97) + (asciikey - 97)) % 26 + 97;
      return (char) encode;
    }
  }


  public static char caesarShiftDecode(char ciphertext, char key){
    if (!isLowerCase(ciphertext) || !isLowerCase(key)){
      return ciphertext;
    }
    else{
      int asciicipher = ciphertext;
      int asciikey = key;
      if (asciicipher > asciikey){
        int decode = Math.abs((asciicipher - 97) - (asciikey - 97)) % 26 + 97;
        return (char) decode;
      }
      else{
        int decode = (26 - Math.abs((asciicipher - 97) - (asciikey - 97))) % 26 + 97;
        return (char) decode;
      }
    }
  }


  public static String vigenereEncode(String plaintext, String key){
    int counter = 0;
    String vigEncode = "";
    for (int i = 0; i < plaintext.length(); i++){
      if (counter > key.length() - 1){
        counter = 0;
      }
      vigEncode += caesarShiftEncode(plaintext.charAt(i), key.charAt(counter));
      counter++;
    }
    return vigEncode;
  }


  public static String vigenereDecode(String ciphertext, String key){
    int counter = 0;
    String vigDecode = "";
    for (int i = 0; i < ciphertext.length(); i++){
      if (counter > key.length() - 1){
        counter = 0;
      }
      vigDecode += caesarShiftDecode(ciphertext.charAt(i), key.charAt(counter));
      counter++;
    }
    return vigDecode;
  }
}