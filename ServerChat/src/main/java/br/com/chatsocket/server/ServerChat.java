package br.com.chatsocket.server;

import br.com.chatsocket.criptografy.DES;
import br.com.chatsocket.models.ListaTabela;
import br.com.chatsocket.models.Usuarios;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

public class ServerChat {
  private StringBuilder usuariosString = new StringBuilder();
  private ListaTabela listaTabela = ListaTabela.getInstance();

  public ServerChat() {
    ServerSocket server;
    try {
      server = new ServerSocket(5000);
      while (true) {
        Socket socket = server.accept();
        new Thread(new EscutaCliente(socket)).start();
      }
    } catch (IOException e) {
    }
  }

  private class EscutaCliente implements Runnable {
    Scanner leitor;
    PrintWriter escritor;
    String enderecoCliente;
    DES des;

    public EscutaCliente(Socket socket) {
      try {
        enderecoCliente = socket.getInetAddress().toString() + ":" + socket.getPort();
        enderecoCliente = enderecoCliente.replace("/", "");
        leitor = new Scanner(socket.getInputStream());
        escritor = new PrintWriter(socket.getOutputStream());
        des = new DES();
      } catch (Exception e) {
      }
    }

    public void run() {
      try {
        String texto;
        while ((texto = leitor.nextLine()) != null) {
          addUsuarios(texto);
          encaminharParaTodos(texto);
        }
      } catch (Exception e) {
      }
    }

    private void addUsuarios(String texto) {
      if (texto.contains("B614BE0B-68BA-42B1-A591-277BF0FE54A2")) {
        String[] valores = texto.split("\\|");
        String nome = valores[1];
        String status = valores[2];
        String chaveString = valores[3];
        SecretKey key = des.convertStringToKey(chaveString);
        listaTabela.listaUsuarios.put(nome, new Usuarios(nome, status, texto, enderecoCliente, escritor, key));
      }
    }
  }

  private void updateUsuarios(String texto) {
    if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
      String[] valores = texto.split("\\|");
      String nome = valores[1];
      String status = valores[2];
      Usuarios u = listaTabela.listaUsuarios.get(nome);
      u.setStatus(status);
      u.setMensagem(texto);
    }
  }

  private void encaminharParaTodos(String texto) {
    listaTabela.pupoluarTabela();
    if (texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
      updateUsuarios(texto);
    }
    preencheStringUsuarios();
    for (String key : listaTabela.listaUsuarios.keySet()) {
      Usuarios u = listaTabela.listaUsuarios.get(key);
      PrintWriter w = u.getEscritor();
      try {
        if (texto.contains("B614BE0B-68BA-42B1-A591-277BF0FE54A2") || texto.contains("06069539-50FE-422D-9BDC-336CD4C0F7F8")) {
          w.println(usuariosString.toString());
        } else if (texto.contains("41B900D9-10CA-425A-A8D4-4F7E7982AA1E")) {
          String valores[] = texto.split("\\|");
          String remet = getRemetente(valores);
          String dest = getDestinatario(valores);
          if (u.getNome().equals(remet) || u.getNome().equals(dest)) {
            String mensagem = getMensagemPrivada(valores);
            w.println(mensagem);
          }
        } else {
            w.println(texto);
        }
        w.flush();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    System.out.println(usuariosString.toString());
  }

  private void preencheStringUsuarios() {
    usuariosString = new StringBuilder();
    for (String key : listaTabela.listaUsuarios.keySet()) {
      Usuarios u = listaTabela.listaUsuarios.get(key);
      if (usuariosString.length() == 0) {
        usuariosString.append(u.getMensagem());
      } else {
        usuariosString.append("*" + u.getMensagem());
      }
    }
  }

  private String getDestinatario(String[] valores) {
    return valores[2];
  }

  private String getRemetente(String[] valores) {
    String texto = valores[1];
    valores = texto.split(":");
    return valores[0];
  }

  private String getMensagemPrivada(String[] valores) {
    return "MENSAGEM PRIVADA - " + valores[1];
  }
}
