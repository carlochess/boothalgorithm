package GUI;

import algoritmobooth.ImplAlgoritmo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    //----------
    // Atributos
    //----------
    DefaultListModel<String> listModel;
    JScrollPane jScrollPane1;
    JTextField CampoNumUno,CampoNumDos, resultadoBin;
    JTable ListaSolución;
    JLabel button, button2, resultadoDec;
    JButton botonResolver;
    JPanel mainPanel, panel, panel2, panel3, panel4;
    Object[][] data;
    DefaultTableModel modelo;
    //----------
    // Métodos
    //----------

    public VentanaPrincipal() {
        initGUI();
        initListeners();
        //----------------------------------------
        add(mainPanel);
        pack();
        setSize(500, 400);
        setTitle("Algortimo de Booth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * Crea el único listener para el único botón :3
     */
    private void initListeners() {
        botonResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResolverActionPerformed(evt);
            }
        });
        
    }
    /**
     * Inicializa la GUI
     */
    private void initGUI() {
        listModel = new DefaultListModel();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //----------------------------------------
        panel = new JPanel();
        panel.setBorder(new TitledBorder("Numero Dos"));

        CampoNumUno = new JTextField(20);
        //CampoNumUno.setPreferredSize(new Dimension(100, 20));
        panel.add(CampoNumUno);
        //----------------------------------------
        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder("Numero Uno"));
        
        CampoNumDos = new JTextField(20);
        //CampoNumDos.setPreferredSize(new Dimension(100, 20));
        panel2.add(CampoNumDos);
        //----------------------------------------
        panel3 = new JPanel();

        botonResolver = new JButton("Calcular");
        panel3.add(botonResolver);
        //---------------------------------------- 
        String[] columna = new String[]{"N","A","Q","Q-1","M","OP"};
        modelo = new DefaultTableModel(new Object[0][0], columna);
        ListaSolución = new JTable(modelo);
        ListaSolución.setFillsViewportHeight(true);
        jScrollPane1 = new JScrollPane(ListaSolución);
        jScrollPane1.setPreferredSize(new Dimension(500, 300));
        //----------------------------------------
        
        panel4 = new JPanel();
        resultadoDec = new JLabel("Resultado: 0");
        resultadoBin = new JTextField();
        resultadoBin.setPreferredSize(new Dimension(250, 20));
        panel4.add(resultadoDec);
        panel4.add(resultadoBin);
        //------------------------------
        mainPanel.add(panel2);
        mainPanel.add(panel);
        mainPanel.add(panel3);
        mainPanel.add(jScrollPane1);
        mainPanel.add(panel4);
    }
    /**
     * Verifica si los datos ingresados en los JTextfield son válidos
     * @param evt 
     */
    private void botonResolverActionPerformed(java.awt.event.ActionEvent evt) {
        if (isNumeric(CampoNumUno.getText())) {
            return;
        }
        if (isNumeric(CampoNumDos.getText())) {
            return;
        }
        int uno = Integer.parseInt(CampoNumUno.getText());
        int dos = Integer.parseInt(CampoNumDos.getText());
        UpdateJTable(uno, dos);
    }
    /**
     * Método que actualiza el modelo de la tabla según la información
     * generada.
     * @param uno
     * @param dos 
     */
    private void UpdateJTable(int uno, int dos) {
        ImplAlgoritmo p = new ImplAlgoritmo();
        removerFilas(modelo);
        ArrayList<ArrayList<String>> g = p.init(uno, dos);
        if (g != null)
        {
            for (ArrayList<String> h : g)
                modelo.addRow(h.toArray());
            imprimirRes();
        }else{System.out.println("g sin data");}
    }
    /**
     * Método encargado  de verificar si una cadena puede convertirse
     * en un número.
     * @param str
     * @return 
     */
    public boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }
    /**
     * Convierte el resultado de binario a decimal y lo imprime en la
     * GUI
     */
    private void imprimirRes() {
        String a = (String) modelo.getValueAt(modelo.getRowCount()-1, 1);
        String b = (String) modelo.getValueAt(modelo.getRowCount()-1, 2);
        String numero = a + b;
        int res = binToDec(numero);
        System.out.println(numero);
        resultadoBin.setText(numero);
        resultadoDec.setText("Resultado: "+res+" => ");
    }
    /**
     * Convierte un número binario (complemento dos) en decimal
     * @param bin
     * @return 
     */
    public static int binToDec(String bin) {
        int n = bin.length() - 1;
        int resultado = -1 * (int) Math.pow(2, n) * ((bin.charAt(0) == '1') ? 1 : 0);
        for (int i = 1, m = n - 1; i <= n; i++, m--) {
            resultado += ((bin.charAt(i) == '1') ? 1 : 0) * (int) Math.pow(2, m);
        }
        return resultado;
    }
    /**
     * Limpia el modelo de la tabla
     * @param model 
     */
    private void removerFilas(DefaultTableModel model) {
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }
    public static void main(String ... args)
    {
        VentanaPrincipal p = new VentanaPrincipal();
    }
}
