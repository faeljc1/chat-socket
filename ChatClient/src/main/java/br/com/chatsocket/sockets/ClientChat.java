package br.com.chatsocket.sockets;

import br.com.chatsocket.actions.ActionClickLabel;
import br.com.chatsocket.actions.ActionCloseWindow;
import br.com.chatsocket.models.Users;
import br.com.chatsocket.swing.AppClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.net.Socket;
import java.util.*;

public class ClientChat {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();
  private String ipAddress;
  private int port;
  private Map<String, Users> listUsers = new HashMap<>();

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
          if (texto.contains("B614BE0B-68BA-42B1-A591-277BF0FE54A2") || texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
            String[] params = texto.split("\\*");
            preencheUsers(params);
            preencheWindow();
          } else if (texto.contains("E55A3492-5037-4807-B3EE-4D1686A4FB63")) {
            readerWriter.write.println("06069539-50FE-422D-9BDC-336CD4C0F7F8|" + AppClient.txtNome.getText() + "|" + "offline");
            readerWriter.write.flush();
            System.exit(0);
          } else {
            AppClient.txtConversa.setText(AppClient.txtConversa.getText() + texto + "\n");
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void preencheWindow() {
    java.util.List<Users> listaValores = new ArrayList<>(listUsers.values());
    AppClient.panelContatos.removeAll();

    Collections.sort(listaValores, Comparator.comparing(Users::getIdStatus));
    for (Users u : listaValores) {
      lblUsers = new JLabel("<html><body>Nome: " + u.getNome() +
          " Status: " + u.getStatus() + "</body></html>");
      setCores(u.getStatus());

      lblUsers.addMouseListener(new ActionClickLabel());

      AppClient.panelContatos.add(lblUsers);
      AppClient.panelContatos.doLayout();
      AppClient.panelContatos.repaint();
    }
  }

  private void preencheUsers(String[] users) {
    for (String user : users) {
      String[] params = user.split("\\|");
      Users u = new Users(params[1], params[2]);
      listUsers.put(params[1], u);
    }
  }

  private void setCores(String status) {
    if (status.equals("online")) {
      lblUsers.setForeground(new Color(0, 150, 0));
    } else if (status.equals("ocupado")) {
      lblUsers.setForeground(new Color(200, 0, 0));
    } else if (status.equals("ausente")) {
      lblUsers.setForeground(new Color(200, 150, 0));
    } else if (status.equals("offline")) {
      lblUsers.setForeground(new Color(90, 90, 90));
    }
  }
}