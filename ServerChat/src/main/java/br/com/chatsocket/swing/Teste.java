package br.com.chatsocket.swing;

import br.com.chatsocket.criptografy.RSA;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Teste {
  public static void main(String[] args) {
    KeyPairGenerator keyGen;
    KeyPair pair;
    PrivateKey privateKey;
    PublicKey publicKey;

    try {
      keyGen = KeyPairGenerator.getInstance("RSA");
      keyGen.initialize(2048);

      pair = keyGen.generateKeyPair();
      privateKey = pair.getPrivate();
      publicKey = pair.getPublic();

      String texto = "Kekeo é bonita";
      System.out.println("Original: " + texto);

      RSA rsa = new RSA();
      String cifrado = rsa.encryptText(texto, publicKey);
      System.out.println("Cifrado: " + cifrado);

      String normal = rsa.decryptText(cifrado, privateKey);
      System.out.println("Normal: " + normal);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
