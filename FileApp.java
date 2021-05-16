package siguri;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class FileApp implements ActionListener {
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
    String inputMessage1;
    String inputMessage2;
    String inputMessage3;
    //initializing vector
    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
    InvalidKeyException, InvalidAlgorithmParameterException {

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

        label=new JLabel("File");
        label.setBounds(50,20,150,25);
        panel.add(label);

        usertext =new JTextField(20);
        usertext.setBounds(170,20,200,25);
        panel.add(usertext);


        encryptlabel=new JLabel("Encrypted file's path");
        encryptlabel.setBounds(50,60,150,25);
        panel.add(encryptlabel);

        ourencrypt =new JTextField(20);
        ourencrypt.setBounds(170,60,200,25);
        panel.add(ourencrypt);

        decryptlabel=new JLabel("Decrypted file's path");
        decryptlabel.setBounds(50,100,150,25);
        panel.add(decryptlabel);

        ourdecrypt =new JTextField(20);
        ourdecrypt.setBounds(170,100,200,25);
        panel.add(ourdecrypt);

        button=new JButton("Encrypt");
        button.setBounds(155,140,100,18);
        button.addActionListener(new FileApp());
        panel.add(button);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        inputMessage1=usertext.getText();
        inputMessage2=ourencrypt.getText();
        inputMessage3=ourdecrypt.getText();
        try {
            encryption(new FileInputStream(inputMessage1), new FileOutputStream(inputMessage2));
            decryption(new FileInputStream(inputMessage2), new FileOutputStream(inputMessage3));

        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
    
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
    //method for writting bytes to the files
    private static void writeBytes(InputStream input, OutputStream output)
            throws IOException
    {
        byte[] writeBuffer = new byte[512];
        int readBytes;
        while ((readBytes = input.read(writeBuffer)) >= 0)
        {
            output.write(writeBuffer, 0, readBytes);
        }

        //closing the output stream
        output.close();
        //closing the input stream
        input.close();
    }
}
