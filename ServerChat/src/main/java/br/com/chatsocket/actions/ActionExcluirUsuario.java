package br.com.chatsocket.actions;

import br.com.chatsocket.models.ListaTabela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionExcluirUsuario implements ActionListener {
  private ListaTabela listaTabela = ListaTabela.getInstance();
  public void actionPerformed(ActionEvent e) {
    String nome = listaTabela.removeItemTabela();
    System.out.println(nome);
  }
}
