package br.com.chatsocket.models;

import javax.crypto.SecretKey;

public class Users {
  private String nome;
  private String status;
  private Integer idStatus;
  private SecretKey chave;

  public Users() {
  }

  public Users(String nome, String status, SecretKey chave) {
    this.nome = nome;
    this.status = status;
    this.chave = chave;
    switch (status) {
      case "online":
        this.idStatus = 1;
        break;
      case "ocupado":
        this.idStatus = 2;
        break;
      case "ausente":
        this.idStatus = 3;
        break;
      case "offline":
        this.idStatus = 4;
        break;
      default:
        this.idStatus = 1;
    }
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

  public Integer getIdStatus() {
    return idStatus;
  }

  public void setIdStatus(Integer idStatus) {
    this.idStatus = idStatus;
  }

  public SecretKey getChave() {
    return chave;
  }

  public void setChave(SecretKey chave) {
    this.chave = chave;
  }
}
