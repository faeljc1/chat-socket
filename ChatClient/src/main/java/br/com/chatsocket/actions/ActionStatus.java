package br.com.chatsocket.actions;

import br.com.chatsocket.sockets.ReaderWriter;
import br.com.chatsocket.swing.AppClient;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ActionStatus implements ItemListener {
  private ReaderWriter readerWriter = ReaderWriter.getInstance();

  public void itemStateChanged(ItemEvent e) {
    if (!AppClient.btnConetar.isEnabled()) {
      if (e.getStateChange() == ItemEvent.SELECTED) {
        String status = AppClient.cboxStatus.getSelectedItem().toString().toLowerCase();
        System.out.println(status);
        readerWriter.write.println("06069539-50FE-422D-9BDC-336CD4C0F7F8|" + AppClient.txtNome.getText() + "|" + status);
        readerWriter.write.flush();
      }
    }
  }
}
