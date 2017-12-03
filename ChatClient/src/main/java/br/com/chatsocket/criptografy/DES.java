package br.com.chatsocket.criptografy;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class DES {
  private static final String UNICODE_FORMAT  = "UTF8";

  public String convertKeyToString(SecretKey chave) {
    return Base64.getEncoder().encodeToString(chave.getEncoded());
  }

  public SecretKey convertStringToKey(String chaveString) {
    byte[] decodedKey = Base64.getDecoder().decode(chaveString);
    return new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
  }

  /*public byte[] encrypt(byte[] text, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher desCipher1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher1.init(Cipher.ENCRYPT_MODE, key);
    return desCipher1.doFinal(text);
  }

  public byte[] decrypt(byte[] text, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    Cipher desCipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher2.init(Cipher.DECRYPT_MODE, key);
    return desCipher2.doFinal(text);
  }*/

  public String encrypt(String text, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException {
    Cipher desCipher1 = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher1.init(Cipher.ENCRYPT_MODE, key);
    byte[] encValue = desCipher1.doFinal(text.getBytes(UNICODE_FORMAT));
    String encryptedValue = new BASE64Encoder().encode(encValue);
    return encryptedValue;
  }

  public String decrypt(String text, SecretKey key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {
    Cipher desCipher2 = Cipher.getInstance("DES/ECB/PKCS5Padding");
    desCipher2.init(Cipher.DECRYPT_MODE, key);
    byte[] decordedValue = new BASE64Decoder().decodeBuffer(text);
    byte[] decValue = desCipher2.doFinal(decordedValue);//////////LINE 50
    String decryptedValue = new String(decValue);
    return decryptedValue;
  }
}