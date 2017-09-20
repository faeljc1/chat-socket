package br.com.redes2.actions;

import br.com.redes2.sockets.ReaderWriter;
import br.com.redes2.swing.AppClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionSend implements ActionListener {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  @SuppressWarnings("deprecation")
  public void actionPerformed(ActionEvent arg0) {
    readerWriter.write.println(AppClient.txtNome.getText() + " : " + AppClient.txtMensagem.getText());
    readerWriter.write.flush();
    AppClient.txtMensagem.setText("");
    AppClient.txtMensagem.requestFocus();
  }
}
