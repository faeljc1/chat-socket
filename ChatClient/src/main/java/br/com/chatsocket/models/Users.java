package br.com.chatsocket.models;

public class Users {
  private String nome;
  private String status;
  private Integer idStatus;

  public Users() {
  }

  public Users(String nome, String status) {
    this.nome = nome;
    this.status = status;
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
}
