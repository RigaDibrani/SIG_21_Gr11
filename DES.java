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
                
                //----------------------------------------TEXT ENCRYPTION/DECRYPTION----------------------------------------------//

                // Initialize the cipher for encryption and decryption
                encrypt.init(Cipher.ENCRYPT_MODE, scrtkey,aps);
                decrypt.init(Cipher.DECRYPT_MODE, scrtkey,aps);

                System.out.print("Text:");
                String plain=input.nextLine();

                String encrypted = encrypt(encrypt, plain);
                System.out.println("Encrypted text: " + encrypted);

                String decrypted = decrypt(decrypt, encrypted);
                System.out.println("Decrypted text: " + decrypted);

                //file
                System.out.print("File to read: ");
                String path = input.nextLine();
                System.out.print("File for encryption: ");
                String fileenc = input.nextLine();
                fileencrypt(desCipherEncrypter,path,fileenc);
                System.out.print("File for decryption: ");
                String filedec = input.nextLine();
                filedecrypt(desCipherDecrypter, fileenc,filedec );
               
     
            }
                catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
              } catch (InvalidKeyException invalidKeyException) {
                invalidKeyException.printStackTrace();
              } catch (NoSuchPaddingException noSuchPaddingException) {
                noSuchPaddingException.printStackTrace();
              } catch (BadPaddingException badPaddingException) {
                badPaddingException.printStackTrace();
              } catch (IllegalBlockSizeException illegalBlockSizeException) {
                illegalBlockSizeException.printStackTrace();
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              } catch (Exception e) {
                e.printStackTrace();
              }
     
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
     
        public static void fileencrypt(Cipher cipher,String path,String encryptedfile) throws FileNotFoundException,Exception{
            Scanner inputi=new Scanner(System.in);
            Scanner finput = null;
            File file = new File(path);
            if (!file.exists()) throw new FileNotFoundException();
            if (!file.canRead()) throw new Exception("Cannot read " + path);
            if (!file.canWrite()) throw new Exception("Cannot write " + path);
            finput = new Scanner(file);
            FileWriter write =new FileWriter(encryptedfile);
            while (finput.hasNext()) {
                String str = finput.nextLine();
                String encrypt = encrypt(cipher, str);
                write.write(encrypt);
                write.write("\n");
            }
            System.out.println("Encrypted text file");
            write.close();
        }
     
            public static void filedecrypt(Cipher cipher,String filetodecrypt,String dencryptedfile) throws FileNotFoundException,Exception{
            Scanner dinput=null;
            FileWriter write1 =new FileWriter(dencryptedfile);
            File filefordecrypt=new File(filetodecrypt);
            dinput=new Scanner(filetodecrypt);
            while (dinput.hasNext()) {
            String str = dinput.nextLine();
            String decrypt = decrypt(cipher, str);
            write1.write(decrypt + " ");
        }
            write1.close();
            System.out.println("Decrypted text");
    }
            


            
     }

