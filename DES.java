import javax.crypto.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner
public class Main {
        public static void main(String[] args) {
            try {
                Scanner input = new Scanner(System.in);
                KeyGenerator desKeyGenerator = KeyGenerator.getInstance("DES");
                Cipher desCipherEncrypter = Cipher.getInstance("DES");
     
                SecretKey key = desKeyGenerator.generateKey();
                desCipherEncrypter.init(Cipher.ENCRYPT_MODE, key);
                desCipherDecrypter.init(Cipher.DECRYPT_MODE, key);
     
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
     
     
     
     }

