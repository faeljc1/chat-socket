package br.com.redes2.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerChat {
  List<PrintWriter> escritores = new ArrayList<PrintWriter>();
  StringBuilder usuarios = new StringBuilder();

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
    if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8") && texto.contains("online")) {
      if (usuarios.length() == 0) {
        usuarios.append(texto);
      } else {
        usuarios.append("*" + texto);
      }
    } else if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8") && texto.contains("offline")) {

    }
    for (PrintWriter w : escritores) {
      try {
        if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8") || texto.contains("9E1CB7FF-D266-430E-AEBF-BF13384B8935")) {
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

  public static void main(String[] args) {
    new ServerChat();
  }
}
