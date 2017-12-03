package br.com.chatsocket.swing;

import br.com.chatsocket.criptografy.DES;
import br.com.chatsocket.criptografy.DES2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class JEncrytion {
  public static void main(String[] argv) {

    try {

      KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
      SecretKey myDesKey = keygenerator.generateKey();

      String chaveString = Base64.getEncoder().encodeToString(myDesKey.getEncoded());

      System.out.println(chaveString);

      byte[] decodedKey = Base64.getDecoder().decode(chaveString);

      SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");

      DES2 des = new DES2();

      //sensitive information
      String text = "No body can see me";

      // Encrypt the text
      String textEncrypted = des.encrypt(text, myDesKey);

      // Decrypt the text
      String textDecrypted = des.decrypt(textEncrypted, originalKey);

      System.out.println(textDecrypted);

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /*public static byte[] encrypt(byte[] text, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher desCipher1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher1.init(Cipher.ENCRYPT_MODE, key);
    return desCipher1.doFinal(text);
  }

  public static byte[] decrypt(byte[] text, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher desCipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher2.init(Cipher.DECRYPT_MODE, key);
    return desCipher2.doFinal(text);
  }*/
}