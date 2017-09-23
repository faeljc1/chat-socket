package br.com.redes2.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServerChat {
  private List<PrintWriter> escritores = new ArrayList<PrintWriter>();
  private StringBuilder usuarios = new StringBuilder();
  private Map<String, String> usres = new HashMap<String, String>();

  public ServerChat() {
    ServerSocket server;
    try {
      server = new ServerSocket(5000);
      while (true) {
        Socket socket = server.accept();
        new Thread(new EscutaCliente(socket)).start();
        escritores.add(new PrintWriter(socket.getOutputStream()));
      }
    } catch (IOException e) {
    }
  }

  private void encaminharParaTodos(String texto) {
    if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
      String[] msg = texto.split("\\|");
      usres.put(msg[1], texto);
    }
    preencheStringUsers();
    for (PrintWriter w : escritores) {
      try {
        if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
          w.println(usuarios.toString());
        } else {
          w.println(texto);
        }
        w.flush();
      } catch (Exception e) {
      }
    }
    System.out.println(usuarios.toString());
  }

  private class EscutaCliente implements Runnable {

    Scanner leitor;

    public EscutaCliente(Socket socket) {
      try {
        leitor = new Scanner(socket.getInputStream());
      } catch (Exception e) {
      }
    }

    public void run() {
      try {
        String texto;
        while ((texto = leitor.nextLine()) != null) {
          // System.out.println(texto);
          encaminharParaTodos(texto);
        }
      } catch (Exception e) {
      }
    }
  }

  private void preencheStringUsers() {
    usuarios = new StringBuilder();
    for (String key : usres.keySet()) {
      if (usuarios.length() == 0) {
        usuarios.append(usres.get(key));
      }else {
        usuarios.append("*" + usres.get(key));
      }
    }
  }

  public static void main(String[] args) {
    new ServerChat();
  }
}
