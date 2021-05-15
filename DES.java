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

                System.out.print("File to read: ");
                String path = input.nextLine();
                System.out.print("File for encryption: ");
                String fileenc = input.nextLine();
                fileencrypt(desCipherEncrypter,path,fileenc);
               
     
            }
            catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException e ) {
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
     
     }

