package siguri;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

import java.util.Base64;
public class Main implements ActionListener {
    private static JLabel label;
    private static JTextField usertext;
    private static JLabel encryptlabel;
    private static JTextField ourencrypt;
    private static JLabel decryptlabel;
    private static JTextField ourdecrypt;
    private static JButton button;
    private static Cipher encrypt;

    //creating an instance of the Cipher class for decryption
    private static Cipher decrypt;
    String inputMessage;
    //initializing vector
    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {

        SecretKey scrtkey = KeyGenerator.getInstance("DES").generateKey();
        AlgorithmParameterSpec aps = new IvParameterSpec(initialization_vector);
        encrypt=Cipher.getInstance("DES/CBC/PKCS5Padding");
        decrypt= Cipher.getInstance("DES/CBC/PKCS5Padding");
        encrypt.init(Cipher.ENCRYPT_MODE, scrtkey,aps);
        decrypt.init(Cipher.DECRYPT_MODE, scrtkey,aps);



        JPanel panel=new JPanel();
        JFrame frame=new JFrame();
        frame.setSize(450,270);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        label=new JLabel("Plaintext");
        label.setBounds(50,20,150,25);
        panel.add(label);

        usertext =new JTextField(20);
        usertext.setBounds(170,20,200,25);
        panel.add(usertext);


        encryptlabel=new JLabel("Encrypted text");
        encryptlabel.setBounds(50,60,150,25);
        panel.add(encryptlabel);

        ourencrypt =new JTextField(20);
        ourencrypt.setBounds(170,60,200,25);
        panel.add(ourencrypt);

        decryptlabel=new JLabel("Decrypted text");
        decryptlabel.setBounds(50,100,150,25);
        panel.add(decryptlabel);

        ourdecrypt =new JTextField(20);
        ourdecrypt.setBounds(170,100,200,25);
        panel.add(ourdecrypt);

        button=new JButton("Encrypt");
        button.setBounds(155,140,100,18);
        button.addActionListener(new Main());
        panel.add(button);

      


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        inputMessage=usertext.getText();
        String encrypted = null;
        String decrypted = null;

        try {
            encrypted = encrypt(encrypt, inputMessage);
            System.out.println("Encrypted text: " + encrypted);
            ourencrypt.setText(encrypted);
            decrypted = decrypt(decrypt, encrypted);
            System.out.println("Decrypted text: " + decrypted);
            ourdecrypt.setText(decrypted);
            
        } catch (BadPaddingException | IllegalBlockSizeException badPaddingException) {
            badPaddingException.printStackTrace();
        }
       
      

    }

    public static String encrypt(Cipher cipher, String plainStr) throws BadPaddingException,IllegalBlockSizeException
    {
        byte[] plainBytes;
        byte[] encryptedBytes;
        plainBytes=plainStr.getBytes(StandardCharsets.UTF_8);
        encryptedBytes=cipher.doFinal(plainBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(Cipher cipher,String encryptedStr) throws BadPaddingException,IllegalBlockSizeException
    {
        byte[] encryptedBytes=Base64.getDecoder().decode(encryptedStr);
        byte[] decryptedBytes=cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes,StandardCharsets.UTF_8);
    }
}