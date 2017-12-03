package br.com.chatsocket.actions;

import br.com.chatsocket.criptografy.DES;
import br.com.chatsocket.sockets.ClientChat;
import br.com.chatsocket.sockets.ReaderWriter;
import br.com.chatsocket.swing.AppClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.util.Base64;

public class ActionConect implements ActionListener {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  @SuppressWarnings("deprecation")
  public void actionPerformed(ActionEvent arg0) {
    if (!AppClient.txtNome.getText().equals("")) {
      ClientChat client = new ClientChat("localhost", 5000);
      try {
        DES des = new DES();
        AppClient.btnConetar.setEnabled(false);
        AppClient.txtNome.setEditable(false);
        AppClient.btnEnviar.setEnabled(true);
        AppClient.txtMensagem.setEditable(true);
        client.configurarRede();

        String status = AppClient.cboxStatus.getSelectedItem().toString().toLowerCase();
        System.out.println(status);

        String chaveString = des.convertKeyToString(AppClient.chaveDes);

        readerWriter.write.println("B614BE0B-68BA-42B1-A591-277BF0FE54A2|" + AppClient.txtNome.getText() + "|" + status + "|" + chaveString);
        readerWriter.write.flush();
      } catch (ConnectException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Servidor desconectado!!!", "Erro",
            JOptionPane.INFORMATION_MESSAGE);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Preencha o nome!", "Erro",
          JOptionPane.INFORMATION_MESSAGE);
      AppClient.txtNome.requestFocus();
    }
  }
}
