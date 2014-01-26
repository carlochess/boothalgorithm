package GUI;

import algoritmobooth.ImplAlgoritmo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.LayerUI;

public class VentanaConvertidor extends JDialog {

    JPanel mainPanel, panel, panel2, panel3;
    JFormattedTextField decimal,binario;
    JButton binTodec, decTobin;
    
    public VentanaConvertidor() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //----------------------------------------
        panel = new JPanel();
        panel.setBorder(new TitledBorder("Decimal"));
        
        LayerUI<JFormattedTextField> layerUI = new ValidationLayerUI();
        
        decimal = new JFormattedTextField(){
            @Override
            public boolean isEditValid() {
                return isNumeric(this.getText());
            }
        };
        decimal.setColumns(16);
        decimal.setFocusLostBehavior(JFormattedTextField.PERSIST);
        panel.add(new JLayer<>(decimal, layerUI));
        decimal.setDragEnabled(true);
        //----------------------------------------
        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder("Binario"));

        binario = new JFormattedTextField(){
            @Override
            public boolean isEditValid() {
                return esvalida(this.getText());
            }
        };
        binario.setColumns(16);
        binario.setFocusLostBehavior(JFormattedTextField.PERSIST);
        panel2.add(new JLayer<>(binario, layerUI));
        binario.setDragEnabled(true);

        panel3 = new JPanel(new FlowLayout());
        binTodec = new JButton("Binario a Decimal");
        decTobin = new JButton("Decimal a Binario");
        panel3.add(decTobin);
        panel3.add(binTodec);
        panel.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);

        mainPanel.add(panel);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        add(mainPanel);
        initListener();
        pack();
        setTitle("Convertidor");
        setSize(new Dimension(400, 200));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*
    public static void main(String[] args) {
        new VentanaConvertidor();
    }*/

    private void initListener() {
        final ImplAlgoritmo a = new ImplAlgoritmo();
        decTobin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String cadenaDecimal = decimal.getText();
                //System.out.println("Intentando convertir a " + cadenaDecimal);
                if (isNumeric(cadenaDecimal)) {
                    binario.setText(a.decABinS(Integer.parseInt(cadenaDecimal)));
                } else {
                    JOptionPane.showMessageDialog(null, "Número inválido");
                }
            }
        });
        binTodec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String cadenaBinaria = binario.getText();
                if (esvalida(cadenaBinaria)) {
                    decimal.setText(Integer.toString(a.binToDec(cadenaBinaria)));
                } else {
                    JOptionPane.showMessageDialog(null, "Cadena inválida");
                }
            }
        });
    }

    private boolean esvalida(String cadena) {
        cadena = cadena.trim();
        if(cadena.isEmpty() || cadena.length() >31) return false;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != '1' && cadena.charAt(i) != '0') {
                //System.out.println(i + ": " + cadena.charAt(i));
                return false;
            }
        }
        return true;
    }

    /**
     * Método encargado de verificar si una cadena puede convertirse en un
     * número.
     *
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
