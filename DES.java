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
        public static void main(String[] args) {
            try {
                Scanner input = new Scanner(System.in);
                KeyGenerator desKeyGenerator = KeyGenerator.getInstance("DES");
                Cipher desCipherEncrypter = Cipher.getInstance("DES");
     
                SecretKey key = desKeyGenerator.generateKey();
                desCipherEncrypter.init(Cipher.ENCRYPT_MODE, key);
                desCipherDecrypter.init(Cipher.DECRYPT_MODE, key);
                
                System.out.print("Enter plaintextin: ");
                String message = input.nextLine();
                String encrypted = encrypt(desCipherEncrypter, message);
                System.out.println("Encrypted text: " + encrypted);
                String decrypted = decrypt(desCipherDecrypter, encrypted);
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
     

        public static String encrypt(Cipher cipher, String plainStr) throws BadPaddingException,IllegalBlockSizeException
        {
            byte[] plainBytes;
            byte[] encryptedBytes;
            plainBytes=plainStr.getBytes(StandardCharsets.ISO_8859_1);
            encryptedBytes=cipher.doFinal(plainBytes);
            return Base64.getEncoder().encodeToString(encryptedBytes);
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
            
            public static String decrypt(Cipher cipher,String encryptedStr) throws BadPaddingException,IllegalBlockSizeException
    {
            byte[] encryptedBytes=Base64.getMimeDecoder().decode(encryptedStr);
            byte[] decryptedBytes=cipher.doFinal(encryptedBytes);
            return new String(decryptedBytes,StandardCharsets.UTF_8);
    }

            
     }

