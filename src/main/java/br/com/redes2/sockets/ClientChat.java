package br.com.redes2.sockets;

import br.com.redes2.swing.AppClient;

import javax.swing.*;
import java.net.Socket;

public class ClientChat {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();
  private String ipAddress;
  private int port;

  public ClientChat(String ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }

  public void configurarRede() throws Exception {
    Socket socket = new Socket(ipAddress, port);
    readerWriter.setReaderWriter(socket.getInputStream(), socket.getOutputStream());
    new Thread(new ClientChat.EscutaServidor()).start();
  }

  private class EscutaServidor implements Runnable {

    public void run() {
      try {
        String texto;
        AppClient.panelContatos.add(new JLabel(AppClient.txtNome.getText()));
        AppClient.panelContatos.repaint();
        AppClient.panelContatos.doLayout();
        while ((texto = readerWriter.read.nextLine()) != null) {
          AppClient.txtConversa.setText(AppClient.txtConversa.getText() + texto + "\n");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}