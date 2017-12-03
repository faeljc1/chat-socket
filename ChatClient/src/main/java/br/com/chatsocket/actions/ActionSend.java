package br.com.chatsocket.actions;

import br.com.chatsocket.criptografy.DES;
import br.com.chatsocket.sockets.ReaderWriter;
import br.com.chatsocket.swing.AppClient;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ActionSend implements ActionListener {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  @SuppressWarnings("deprecation")
  public void actionPerformed(ActionEvent arg0) {
    try {
      DES des = new DES();

      String mensagem = AppClient.txtMensagem.getText();
      mensagem = des.encrypt(mensagem, AppClient.chaveDes);
      if (mensagem.contains("mensagemprivada:")) {
        mensagem = getMensagemPrivada(mensagem);
      } else {
        mensagem = AppClient.txtNome.getText() + ":: " + mensagem;
      }

      readerWriter.write.println(mensagem);
      readerWriter.write.flush();
      AppClient.txtMensagem.setText("");
      AppClient.txtMensagem.requestFocus();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public String getMensagemPrivada(String mensagem) {
    String[] valores = mensagem.trim().split(":");
    mensagem = valores[1];
    valores = mensagem.split("}");
    String destinatario = valores[0];
    mensagem = valores[1].trim();
    return "41B900D9-10CA-425A-A8D4-4F7E7982AA1E|" + AppClient.txtNome.getText() + ": " + mensagem + "|" + destinatario;
  }
}
