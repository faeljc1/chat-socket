package br.com.chatsocket.actions;

import br.com.chatsocket.sockets.ClientChat;
import br.com.chatsocket.sockets.ReaderWriter;
import br.com.chatsocket.swing.AppClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;

public class ActionConect implements ActionListener {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  @SuppressWarnings("deprecation")
  public void actionPerformed(ActionEvent arg0) {
    ClientChat client = new ClientChat("localhost", 5000);
    try {
      AppClient.btnConetar.setEnabled(false);
      AppClient.txtNome.setEditable(false);
      AppClient.btnEnviar.setEnabled(true);
      AppClient.txtMensagem.setEditable(true);
      client.configurarRede();

      readerWriter.write.println("06069539-50FE-422D-9BDC-336CD4C0F7F8|" + AppClient.txtNome.getText() + "|" + "online");
      readerWriter.write.flush();
    } catch (ConnectException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Servidor desconectado!!!", "Erro",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
