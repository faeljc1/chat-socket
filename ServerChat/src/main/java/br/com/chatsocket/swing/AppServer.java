package br.com.chatsocket.swing;

import br.com.chatsocket.actions.ActionExcluirUsuario;
import br.com.chatsocket.server.ServerChat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AppServer extends JFrame {
  private static final long serialVersionUID = 1L;
  public static JLabel lblTotal;
  public static JButton btnExcluirUsuario;

  public static JTable tabela;
  public static JScrollPane barraRolagemTabela;
  public static JPanel painelImportacao;

  public static JLabel lblUsuarios;

  public static DefaultTableModel modelo = new DefaultTableModel();

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AppServer frame = new AppServer();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public AppServer() {
    setTitle("Keep Calm - Let's Talk / SERVIDOR");
    setResizable(false);
    setBounds(100, 100, 444, 467);
    setLocationRelativeTo(null);
    // this.setExtendedState(MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    painelImportacao = new JPanel();
    getContentPane().add(painelImportacao, BorderLayout.CENTER);
    painelImportacao.setLayout(null);

    criaJTable();
    barraRolagemTabela = new JScrollPane(tabela);
    barraRolagemTabela.setBounds(10, 68, 418, 298);
    painelImportacao.add(barraRolagemTabela);

    btnExcluirUsuario = new JButton("Excluir Usu\u00E1rio");
    btnExcluirUsuario.setBounds(10, 390, 107, 23);
    btnExcluirUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
    btnExcluirUsuario.addActionListener(new ActionExcluirUsuario());
    painelImportacao.add(btnExcluirUsuario);

    lblTotal = new JLabel("");
    lblTotal.setBounds(564, 411, 0, 0);
    painelImportacao.add(lblTotal);

    lblUsuarios = new JLabel("Usu\u00E1rios Logados");
    lblUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
    lblUsuarios.setAlignmentX(Component.CENTER_ALIGNMENT);
    lblUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
    lblUsuarios.setBounds(10, 11, 418, 46);
    painelImportacao.add(lblUsuarios);

    new Thread(new ExecutaServer()).start();
  }

  private void criaJTable() {
    modelo = new DefaultTableModel() {
      private static final long serialVersionUID = 1L;

      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };
    tabela = new JTable(modelo);
    modelo.addColumn("Usuário");
    modelo.addColumn("Endereço");
    tabela.getColumnModel().getColumn(0).setPreferredWidth(100);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
  }

  private class ExecutaServer implements Runnable {
    public ExecutaServer() {
    }

    public void run() {
      try {
        new ServerChat();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
