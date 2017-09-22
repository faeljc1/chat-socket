package br.com.chatsocket.actions;

import br.com.chatsocket.sockets.ReaderWriter;
import br.com.chatsocket.swing.AppClient;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ActionCloseWindow implements WindowListener {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  public void windowOpened(WindowEvent e) {
  }

  public void windowClosing(WindowEvent e) {
    readerWriter.write.println("06069539-50FE-422D-9BDC-336CD4C0F7F8|" + AppClient.txtNome.getText() + "|" + "offline");
    readerWriter.write.flush();
  }

  public void windowClosed(WindowEvent e) {
  }

  public void windowIconified(WindowEvent e) {
  }

  public void windowDeiconified(WindowEvent e) {
  }

  public void windowActivated(WindowEvent e) {
  }

  public void windowDeactivated(WindowEvent e) {
  }
}
