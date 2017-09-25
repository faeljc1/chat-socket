package br.com.chatsocket.models;

import br.com.chatsocket.swing.AppServer;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ListaTabela {
  public static Map<String, Usuarios> listaUsuarios;
  private static ListaTabela uniqueInstance = new ListaTabela();

  private ListaTabela() {
    listaUsuarios = new HashMap<String, Usuarios>();
  }

  public static ListaTabela getInstance() {
    return uniqueInstance;
  }

  public void pupoluarTabela() {
    AppServer.modelo.setNumRows(0);
    for (String key : listaUsuarios.keySet()) {
      Usuarios u = listaUsuarios.get(key);
      AppServer.modelo.addRow(new Object[]{u.getNome(), u.getEnderecoCliente()});
    }
  }

  public String removeItemTabela() {
    int selRow = AppServer.tabela.getSelectedRow();
    if (selRow >= 0) {
      Object value = AppServer.tabela.getValueAt(selRow, 0);
      String nome = String.valueOf(value);
      for (String key : listaUsuarios.keySet()) {
        Usuarios u = listaUsuarios.get(key);
        PrintWriter w = u.getEscritor();
        if (u.getNome().equals(nome)) {
          w.println("E55A3492-5037-4807-B3EE-4D1686A4FB63");
          w.flush();
        }
      }
      listaUsuarios.remove(nome);
      AppServer.modelo.removeRow(selRow);
      return String.valueOf(value);
    }
    return null;
  }
}
