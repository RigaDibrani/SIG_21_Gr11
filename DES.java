import java.util.Scanner;
import java.security.spec.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.util.Base64;
public class DES {
    public static void main(String[] args){
    Scanner input=new Scanner(System.in);
    System.out.print("Shenoni plaintext: ");
    String plaintext=input.nextLine();
    String encryptedtext=Encrypted(plaintext);
    System.out.println("Encrypted text: "+encryptedtext);
    String decryptedtext=Decrypted(encryptedtext);
    System.out.println("Decrypted text: "+decryptedtext);
    input.close();
    }
    public static String Encrypted(String plaintext){
        return null;
    }
    public static String Decrypted(String text){
        return null;
    }
}
