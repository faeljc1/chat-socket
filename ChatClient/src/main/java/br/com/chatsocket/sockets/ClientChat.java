package br.com.chatsocket.sockets;


import br.com.chatsocket.models.Users;
import br.com.chatsocket.swing.AppClient;

import javax.swing.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ClientChat {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();
  private String ipAddress;
  private int port;
  private Map<String, Users> listUsers = new HashMap<String, Users>();

  private JLabel lblUsers;

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
        while ((texto = readerWriter.read.nextLine()) != null) {
          if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
            String[] params = texto.split("\\*");
            preencheUsers(params);
            preencheWindow();
          } else {
            AppClient.txtConversa.setText(AppClient.txtConversa.getText() + texto + "\n");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void preencheUsers(String[] users) {
    for (String user : users) {
      String[] params = user.split("\\|");
      Users u = new Users(params[1], params[2]);
      listUsers.put(params[1], u);
    }
  }

  private void preencheWindow() {
    AppClient.panelContatos.removeAll();
    for (String key : listUsers.keySet()) {
      Users u = listUsers.get(key);
      lblUsers = new JLabel("<html><body>Nome: " + u.getNome() +
          " Status: " + u.getStatus() + "</body></html>");
      AppClient.panelContatos.add(lblUsers);
      AppClient.panelContatos.doLayout();
      AppClient.panelContatos.repaint();
    }
  }
}