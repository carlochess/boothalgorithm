package GUI;

import algoritmobooth.ImplAlgoritmo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    //----------
    // Atributos
    //----------
    DefaultListModel<String> listModel;
    JScrollPane jScrollPane1;
    JTextField CampoNumUno,CampoNumDos, resultadoBin;
    JList ListaSolución;
    JLabel button, button2, resultadoDec;
    JButton botonResolver;
    JPanel mainPanel, panel, panel2, panel3, panel4;
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

    private void initListeners() {
        botonResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResolverActionPerformed(evt);
            }
        });
    }

    private void initGUI() {
        listModel = new DefaultListModel();
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //----------------------------------------
        panel = new JPanel();

        button = new JLabel("Numero Dos");
        panel.add(button);

        CampoNumUno = new JTextField();
        CampoNumUno.setPreferredSize(new Dimension(100, 20));
        panel.add(CampoNumUno);
        //----------------------------------------
        panel2 = new JPanel();

        button2 = new JLabel("Numero Uno");
        panel2.add(button2);

        CampoNumDos = new JTextField();
        CampoNumDos.setPreferredSize(new Dimension(100, 20));
        panel2.add(CampoNumDos);
        //----------------------------------------
        panel3 = new JPanel();

        botonResolver = new JButton("Calcular");
        panel3.add(botonResolver);
        //---------------------------------------- 

        ListaSolución = new JList();
        ListaSolución.setModel(listModel);
        jScrollPane1 = new JScrollPane(ListaSolución);
        jScrollPane1.setPreferredSize(new Dimension(500, 300));
        //----------------------------------------
        
        panel4 = new JPanel();
        //panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
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

    private void botonResolverActionPerformed(java.awt.event.ActionEvent evt) {
        UpdateJList(null);
        ImplAlgoritmo p = new ImplAlgoritmo();
        if (esInvalido(CampoNumUno.getText())) {
            return;
        }
        if (esInvalido(CampoNumDos.getText())) {
            return;
        }
        int uno = Integer.parseInt(CampoNumUno.getText());
        int dos = Integer.parseInt(CampoNumDos.getText());
        UpdateJList(p.init(uno, dos));
    }

    private void UpdateJList(ArrayList<String> g) {
        listModel = new DefaultListModel<String>();
        if (null != g) {
            String k = "";
            for (String p : g) {
                listModel.addElement(p);
                k = p;
            }
            imprimirRes(k);
        }
        ListaSolución.setModel(listModel);
        ListaSolución.setSelectedIndex(0);
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean esInvalido(String text) {
        return !isNumeric(text);
    }

    private void imprimirRes(String k) {
        int longitud = k.indexOf(" ");
        String numero = k.substring(0, longitud) + k.substring(longitud + 7, longitud * 2 + 7);
        int res = binToDec(numero);
        System.out.println(numero);
        resultadoBin.setText(numero);
        resultadoDec.setText("Resultado: "+res+" => ");
    }

    public static int binToDec(String bin) {
        int n = bin.length() - 1;
        int resultado = -1 * (int) Math.pow(2, n) * ((bin.charAt(0) == '1') ? 1 : 0);
        for (int i = 1, m = n - 1; i <= n; i++, m--) {
            resultado += ((bin.charAt(i) == '1') ? 1 : 0) * (int) Math.pow(2, m);
        }
        return resultado;
    }
}