package br.com.chatsocket.criptografy;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSA {
  private static final String UNICODE_FORMAT  = "UTF8";

  public String convertKeyToString(PublicKey chave) {
    return Base64.getEncoder().encodeToString(chave.getEncoded());
  }

  public PublicKey convertStringToKey(String chaveString) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeySpecException {
    byte[] data = Base64.getDecoder().decode((chaveString.getBytes(UNICODE_FORMAT)));
    X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
    KeyFactory fact = KeyFactory.getInstance("RSA");
    return fact.generatePublic(spec);
  }

  public String encryptText(String msg, PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
    Cipher desCipher1 = Cipher.getInstance("RSA");
    desCipher1.init(Cipher.ENCRYPT_MODE, key);
    byte[] encValue = desCipher1.doFinal(msg.getBytes(UNICODE_FORMAT));
    String encryptedValue = new BASE64Encoder().encode(encValue);
    return encryptedValue;
  }

  public String decryptText(String msg, PrivateKey key) throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException {
    Cipher desCipher1 = Cipher.getInstance("RSA");
    desCipher1.init(Cipher.DECRYPT_MODE, key);
    byte[] decordedValue = new BASE64Decoder().decodeBuffer(msg);
    byte[] decValue = desCipher1.doFinal(decordedValue);
    String decryptedValue = new String(decValue);
    return decryptedValue;
  }
}
