import javax.crypto.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
public class DES {


  //creating an instance of the Cipher class for encryption
  private static Cipher encrypt;
  //creating an instance of the Cipher class for decryption
  private static Cipher decrypt;
  //initializing vector
  private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
  public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
  IllegalBlockSizeException, InvalidKeyException, InvalidAlgorithmParameterException {
                //path of the file that we want to encrypt
                Scanner input=new Scanner(System.in);
                SecretKey scrtkey = KeyGenerator.getInstance("DES").generateKey();
                AlgorithmParameterSpec aps = new IvParameterSpec(initialization_vector);
                encrypt=Cipher.getInstance("DES/CBC/PKCS5Padding");
                decrypt= Cipher.getInstance("DES/CBC/PKCS5Padding");
                
              
                // Initialize the cipher for encryption and decryption
                encrypt.init(Cipher.ENCRYPT_MODE, scrtkey,aps);
                decrypt.init(Cipher.DECRYPT_MODE, scrtkey,aps);

                 //----------------------------------------TEXT/FILE ENCRYPTION/DECRYPTION----------------------------------------------//
                  System.out.print("Zgjedhni text ose file : ");
                  String fromuser=user.nextLine();
                  fromuser=fromuser.toLowerCase();
                  if(fromuser.equals("text")){
                      text();
                  }
                  else if(fromuser.equals("file")){
                      file();
                  }
                  else
                      System.out.print("\nGabim!!!");
              
               }
         public static void text() throws BadPaddingException, IllegalBlockSizeException {

                Scanner input=new Scanner(System.in);
                System.out.print("Text:");
                String plain=input.nextLine();
        
                String encrypted = encrypt(encrypt, plain);
                System.out.println("Encrypted text: " + encrypted);
        
                String decrypted = decrypt(decrypt, encrypted);
                System.out.println("Decrypted text: " + decrypted);
                input.close();
            
          }

          //----------------------------------------TEXT ENCRYPTION/DECRYPTION----------------------------------------------//
          public static String encrypt(Cipher cipher, String plainStr) throws BadPaddingException,IllegalBlockSizeException
          {
              byte[] plainBytes;
              byte[] encryptedBytes;
              plainBytes=plainStr.getBytes(StandardCharsets.UTF_8);
              encryptedBytes=cipher.doFinal(plainBytes);
              //return new String(encryptedBytes,StandardCharsets.ISO_8859_1);
              return Base64.getEncoder().encodeToString(encryptedBytes);
          }

          public static String decrypt(Cipher cipher,String encryptedStr) throws BadPaddingException,IllegalBlockSizeException
          {
              byte[] encryptedBytes=Base64.getDecoder().decode(encryptedStr);
              //byte[] encryptedBytes=encryptedStr.getBytes(StandardCharsets.ISO_8859_1);
              byte[] decryptedBytes=cipher.doFinal(encryptedBytes);
              return new String(decryptedBytes,StandardCharsets.UTF_8);
          }
           //----------------------------------------FILE ENCRYPTION/DECRYPTION----------------------------------------------//
          private static void encryption(InputStream input, OutputStream output)
          throws IOException
          {
              output = new CipherOutputStream(output, encrypt);
              //calling the writeBytes() method to write the encrypted bytes to the file
              writeBytes(input, output);
          }
          //method for decryption
          private static void decryption(InputStream input, OutputStream output)
                  throws IOException
          {
              input = new CipherInputStream(input, decrypt);
              //calling the writeBytes() method to write the decrypted bytes to the file
              writeBytes(input, output);
          }
            


            
     }

