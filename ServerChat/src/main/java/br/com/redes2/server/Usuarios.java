package br.com.redes2.server;

import java.io.PrintWriter;

public class Usuarios {
  private String nome;
  private String status;
  private String mensagem;
  private String enderecoCliente;
  private PrintWriter escritor;

  public Usuarios() {
  }

  public Usuarios(String nome, String status, String mensagem, String enderecoCliente, PrintWriter escritor) {
    this.nome = nome;
    this.status = status;
    this.mensagem = mensagem;
    this.enderecoCliente = enderecoCliente;
    this.escritor = escritor;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public PrintWriter getEscritor() {
    return escritor;
  }

  public void setEscritor(PrintWriter escritor) {
    this.escritor = escritor;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public String getEnderecoCliente() {
    return enderecoCliente;
  }

  public void setEnderecoCliente(String enderecoCliente) {
    this.enderecoCliente = enderecoCliente;
  }
}
