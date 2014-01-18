package GUI;

import algoritmobooth.Explicacion;
import java.awt.Dimension;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;


public class VentExplicacion  extends JDialog{

    public VentExplicacion(String[] rowAt) {
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextPane panelTexto = new JTextPane();
        panelTexto.setContentType("text/html");
        panelTexto.setText("");
        jScrollPane1.setViewportView(panelTexto);
                
        Explicacion e = new Explicacion();
        panelTexto.setText(e.initStr(rowAt));
        add(jScrollPane1);
        setTitle("Operación");
        setSize(new Dimension(600,450));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*
    public static void main(String[] args) {
        String[] g = {"0","110100","101011","1","010111"," Inicial", "000000"};
        new VentExplicacion(g);
    }*/
}
