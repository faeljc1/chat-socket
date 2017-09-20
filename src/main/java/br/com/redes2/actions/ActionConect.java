package br.com.redes2.actions;

import br.com.redes2.sockets.ClientChat;
import br.com.redes2.sockets.ReaderWriter;
import br.com.redes2.swing.AppClient;

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
      client.configurarRede();
    } catch (ConnectException e) {
      e.printStackTrace();
      JOptionPane.showMessageDialog(null, "Servidor desconectado!!!", "Erro",
          JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
