package br.com.chatsocket.actions;

import br.com.chatsocket.swing.AppClient;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ActionClickLabel implements MouseListener {
  @Override
  public void mouseClicked(MouseEvent e) {
    AppClient.txtMensagem.setText("");
    JLabel l = (JLabel) e.getSource(); // here
    String texto = l.getText();
    texto = texto.replace("<html><body>", "");
    texto = texto.replace("</body></html>", "");
    String[] params = texto.split(" ");
    String nome = params[1];
    if (!params[3].equals("offline") && !nome.equals(AppClient.txtNome.getText())) {
      AppClient.txtMensagem.setText("{mensagemprivada:" + nome + "} ");
      AppClient.txtMensagem.requestFocus();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
