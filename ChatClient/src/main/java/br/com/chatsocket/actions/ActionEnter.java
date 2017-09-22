package br.com.chatsocket.actions;

import br.com.chatsocket.sockets.ReaderWriter;
import br.com.chatsocket.swing.AppClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionEnter implements ActionListener {
  private String tipo;
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  public ActionEnter(String tipo) {
    this.tipo = tipo;
  }

  @SuppressWarnings("deprecation")
  public void actionPerformed(ActionEvent arg0) {
    if (tipo.equals("enviar")) {
      AppClient.btnEnviar.doClick();
    } else if (tipo.equals("conectar")) {
      AppClient.btnConetar.doClick();
    }
  }
}
