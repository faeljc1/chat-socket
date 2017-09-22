package br.com.chatsocket.sockets;

import br.com.chatsocket.models.Users;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ReaderWriter {
  public static Scanner read;
  public static PrintWriter write;
  private static ReaderWriter uniqueInstance = new ReaderWriter();
  public static Map<String, Users> listUsers;

  private ReaderWriter() {
    listUsers = new HashMap<String, Users>();
  }

  public void setReaderWriter(InputStream inputStream, OutputStream outputStream) {
    read = new Scanner(inputStream);
    write = new PrintWriter(outputStream);
  }

  public static ReaderWriter getInstance() {
    return uniqueInstance;
  }
}
