package br.com.chatsocket.swing;

import br.com.chatsocket.actions.ActionCloseWindow;
import br.com.chatsocket.actions.ActionConect;
import br.com.chatsocket.actions.ActionEnter;
import br.com.chatsocket.actions.ActionSend;

import javax.swing.*;
import java.awt.*;

public class AppClient extends JFrame {
  private static final long serialVersionUID = 1L;

  public static JButton btnConetar;
  public static JButton btnEnviar;

  public static JLabel lblNome;
  public static JTextField txtNome;
  public static JTextField txtMensagem;
  public static JScrollPane barraRolagemTexto;
  public static JTextPane txtConversa;

  public static JPanel painelNome;
  public static JPanel panelConversa;
  public static JPanel panelContatos;

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          AppClient frame = new AppClient();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public AppClient() {
    setTitle("Keep Calm - Let's Talk");
    setResizable(false);
    setBounds(100, 100, 705, 590);
    setLocationRelativeTo(null);
    // this.setExtendedState(MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);

    painelNome = new JPanel();
    painelNome.setBounds(0, 0, 699, 30);
    getContentPane().add(painelNome);
    painelNome.setLayout(null);

    lblNome = new JLabel("Nome:");
    lblNome.setBounds(7, 10, 43, 14);
    painelNome.add(lblNome);

    txtNome = new JTextField();
    txtNome.setBounds(52, 7, 532, 20);
    txtNome.addActionListener(new ActionEnter("conectar"));
    painelNome.add(txtNome);
    txtNome.setColumns(10);

    btnConetar = new JButton("Conectar");
    btnConetar.setBounds(600, 6, 89, 20);
    btnConetar.addActionListener(new ActionConect());
    painelNome.add(btnConetar);

    panelConversa = new JPanel();
    panelConversa.setBounds(212, 32, 487, 529);
    getContentPane().add(panelConversa);
    panelConversa.setLayout(null);
    barraRolagemTexto = new JScrollPane();
    barraRolagemTexto.setBounds(13, 11, 464, 466);
    panelConversa.add(barraRolagemTexto);

    txtConversa = new JTextPane();
    barraRolagemTexto.setViewportView(txtConversa);
    txtConversa.setEditable(false);

    btnEnviar = new JButton("Enviar");
    btnEnviar.setBounds(388, 488, 89, 20);
    btnEnviar.addActionListener(new ActionSend());
    panelConversa.add(btnEnviar);
    btnEnviar.setEnabled(false);

    txtMensagem = new JTextField();
    txtMensagem.setBounds(13, 488, 365, 20);
    txtMensagem.addActionListener(new ActionEnter("enviar"));
    panelConversa.add(txtMensagem);
    txtMensagem.setColumns(10);
    txtMensagem.setEditable(false);

    panelContatos = new JPanel();
    panelContatos.setBounds(10, 32, 192, 529);
    getContentPane().add(panelContatos);
    panelContatos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    addWindowListener(new ActionCloseWindow());
  }
}
