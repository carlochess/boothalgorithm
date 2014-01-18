package GUI;

import algoritmobooth.ImplAlgoritmo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.LayerUI;
        
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    //----------
    // Atributos
    //----------
    DefaultListModel<String> listModel;
    JScrollPane jScrollPane1;
    JFormattedTextField CampoNumUno, CampoNumDos;
    JTextField resultadoBin;
    JTable ListaSolución;
    JLabel button, button2, resultadoDec;
    JButton botonResolver, botonAbajo, botonArriba;
    JPanel mainPanel, panel, panel2, panel3, panel4,panel5,panel6;
    Object[][] data;
    DefaultTableModel modelo;
    JMenuItem mi1, mi2;
    JRadioButton pasoApaso, yoResolver, normal;
    ImplAlgoritmo p;
    int posicion, opcion;// 0 normal, 1 pap, 2 res
    ArrayList<ArrayList<String>> g;
    JMenuItem mi3;  
    JComboBox listaDeOperaciones;
    String[] columna;
    //----------
    // Métodos
    //----------
    public VentanaPrincipal() {
        initGUI();
        initMenu();
        initListeners();
        add(mainPanel);
        pack();
        setMinimumSize(new Dimension(400, 500));
        setTitle("Algortimo de Booth");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void mistery() {
        remove(mainPanel);
        LayerUI<JPanel> layerUI = new SpotlightLayerUI();
        JLayer<JPanel> jlayer = new JLayer<>(mainPanel, layerUI);
        add(jlayer);
        repaint();
    }

    /**
     * Crea el único listener para el único botón :3
     */
    private void initListeners() {
        botonResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResolverActionPerformed();
            }
        });
        botonArriba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (g != null){// && calcularMaspasos) {
                        int longitudMaxima = g.size()-2;
                        if (posicion >1)
                        {
                            modelo.removeRow(posicion-1);
                            posicion--;
                            if(posicion == longitudMaxima+1) BorrarRes();
                        }
                    }
            }
        });
        // agregar
        botonAbajo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (g != null){// && calcularMaspasos) {
                    if(opcion == 2)
                    {
                        agregarLíneaYoRes();
                    }
                    else if (opcion == 1)
                    {
                        agregarLíneaPaP();
                    }  
               }
            }
        });
        ListaSolución.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(opcion != 2)
                {
                    int row = ListaSolución.rowAtPoint(e.getPoint());
                    int column = ListaSolución.columnAtPoint(e.getPoint());
                    if (row >= 0 && column==5) {
                        //System.out.println("Click en "+ row +" "+column );
                        new VentExplicacion(getRowAt(row));
                    }
                }
            }
            public String[] getRowAt(int row) {
                String[] result = new String[7];
                int fila = (row==0)? 0:row-1;
                for (int i = 0; i < 5; i++) {
                    result[i] = g.get(fila).get(i);
                }
                result[5] = (row==0)? "Inicial":g.get(fila+1).get(5); // operación actual
                result[6] = g.get(fila+1).get(1); // Resultado
                return result;
            }
        });
        mi1.addActionListener(new ActionListener() {
            public static final String creditos = "Carlos\nhttps://github.com/carlochess/boothalgorithm/";
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JLabel label = new JLabel();
                JEditorPane ep = new JEditorPane("text/txt", creditos);
                ep.setEditable(false);
                ep.setBackground(label.getBackground());
                JOptionPane.showMessageDialog(null, ep);
            }
        });
        mi2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        mi3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new VentanaConvertidor();
           }
        });
    }
    private void agregarLíneaYoRes() {
        int longitudMaxima = g.size()-1;
        if (posicion <= longitudMaxima)
        {
            ArrayList<String> m = g.get(posicion-1);
            for (int i=0; i< m.size(); i++) {
                String resp = (String)ListaSolución.getModel().getValueAt(posicion-1, i);
                if(!m.get(i).equals(resp))
                {
                    JOptionPane.showMessageDialog(this, "Lo siento,\nhay un error en "+columna[i]);
                    return;
                }
            }
            String nueva = g.get(posicion).get(0);
            posicion++;
            modelo.addRow(new String[]{nueva,"","","","",""});
            JOptionPane.showMessageDialog(this, "Muy bien, continua");
            //System.out.println("Muy bien, continua");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Genial, has terminado");
            botonAbajo.setEnabled(false);
            imprimirRes();
        }
    }
    private void agregarLíneaPaP() {
        int longitudMaxima = g.size()-1;
        if (posicion <= longitudMaxima)
        {
            modelo.addRow(g.get(posicion).toArray());
            posicion++;
            if (posicion == longitudMaxima+1)imprimirRes();
        }
    }

    /**
     * Inicializa la GUI
     */
    private void initGUI() {
        
        listModel = new DefaultListModel();
        mainPanel = new JPanel();
        LayerUI<JFormattedTextField> layerUI = new ValidationLayerUI();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //----------------------------------------
        columna = new String[]{"N", "A", "Q", "Q-1", "M", "OP"};
        //----------------------------------------
        listaDeOperaciones = new JComboBox();
        listaDeOperaciones.addItem("Inicial");
        listaDeOperaciones.addItem("A<~A+M");
        listaDeOperaciones.addItem("A<~A-M");
        listaDeOperaciones.addItem("desplazamiento");
        //----------------------------------------
        panel = new JPanel();
        panel.setBorder(new TitledBorder("Numero Dos"));

        CampoNumUno = new JFormattedTextField(){
            @Override
            public boolean isEditValid() {
                return !isNumeric(this.getText());
            }
        };
        CampoNumUno.setColumns(16);
        CampoNumUno.setFocusLostBehavior(JFormattedTextField.PERSIST);
        panel.add(new JLayer<>(CampoNumUno, layerUI));
        CampoNumUno.setDragEnabled(true);
        //----------------------------------------
        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder("Numero Uno"));

        CampoNumDos = new JFormattedTextField(){
            @Override
            public boolean isEditValid() {
                return !isNumeric(this.getText());
            }
        };
        CampoNumDos.setColumns(16);
        CampoNumDos.setFocusLostBehavior(JFormattedTextField.PERSIST);
        panel2.add(new JLayer<>(CampoNumDos, layerUI));
        
        CampoNumDos.setDragEnabled(true);
        panel.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        //----------------------------------------
        panel3 = new JPanel();
        botonResolver = new JButton("Calcular");
        botonResolver.setTransferHandler(new TransferHandler("text"));
        yoResolver = new JRadioButton("Yo resuelvo", false);
        pasoApaso = new JRadioButton("Paso a Paso", false);
        normal =  new JRadioButton("Normal", true);
        ButtonGroup group = new ButtonGroup();
        group.add(normal);
        group.add(pasoApaso);
        group.add(yoResolver);
        panel3.setBackground(Color.WHITE);
        panel3.add(normal);
        panel3.add(botonResolver);
        panel3.add(pasoApaso);
        panel3.add(yoResolver);
        //---------------------------------------- 
        panel5 = new JPanel();
        panel5.setBorder(new TitledBorder("Tabla"));    
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.X_AXIS));
        
            modelo = new DefaultTableModel(new Object[0][0], columna);
            ListaSolución = new JTable(modelo);
            ListaSolución.setBackground(Color.WHITE);
            ListaSolución.setSelectionBackground(Color.ORANGE);
            ListaSolución.getColumn("N").setPreferredWidth(20);
            ListaSolución.getColumn("Q-1").setPreferredWidth(20);
            ListaSolución.getColumn("OP").setPreferredWidth(150);
            ListaSolución.setFillsViewportHeight(true);
            jScrollPane1 = new JScrollPane(ListaSolución);
            jScrollPane1.setPreferredSize(new Dimension(500, 300));
            
            panel6 = new JPanel();
            panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
            botonArriba = new JButton("/\\");
            botonAbajo = new JButton("\\/");
            botonArriba.setBackground(Color.WHITE);
            botonAbajo.setBackground(Color.WHITE);
            panel6.add(botonArriba);
            panel6.add(botonAbajo);
        panel5.setBackground(Color.WHITE);
        panel5.add(Box.createRigidArea(new Dimension(30,0)));
        panel5.add(jScrollPane1);
        panel5.add(panel6);
        //----------------------------------------

        panel4 = new JPanel();
        resultadoDec = new JLabel("Resultado: 0");
        resultadoBin = new JTextField();
        resultadoBin.setPreferredSize(new Dimension(250, 20));
        panel4.setBackground(Color.WHITE);
        panel4.add(resultadoDec);
        panel4.add(resultadoBin);
        //------------------------------
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(panel2);
        mainPanel.add(panel);
        mainPanel.add(panel3);
        mainPanel.add(panel5);
        mainPanel.add(panel4);
    }

    /**
     * Verifica si los datos ingresados en los JTextfield son válidos
     * @param evt 
     */
    private void botonResolverActionPerformed() {
        if (isNumeric(CampoNumUno.getText())) {
            return;
        }
        if (isNumeric(CampoNumDos.getText())) {
            return;
        }
        int uno = Integer.parseInt(CampoNumUno.getText());
        int dos = Integer.parseInt(CampoNumDos.getText());
        determinarOpcion();
        UpdateJTable(uno, dos);
    }

    /**
     * Método que actualiza el modelo de la tabla según la información
     * generada.
     * @param uno
     * @param dos 
     */
    private void UpdateJTable(int uno, int dos) {
        p = new ImplAlgoritmo();
        removerFilas();
        BorrarRes();
        g = p.init(uno, dos);
        posicion = 1;
        botonAbajo.setEnabled(true);
        botonArriba.setEnabled(true);
        removerOpcionesOP();
        switch(opcion) {
            case 0: // normal
            {
                for (ArrayList<String> h : g) {
                    modelo.addRow(h.toArray());
                }
                imprimirRes();
                botonAbajo.setEnabled(false);
                botonArriba.setEnabled(false);
                break;
            }
            case 1: // PAP
            {
                modelo.addRow(g.get(0).toArray());
                break;
            }
            case 2: // YoRes
            {
                botonArriba.setEnabled(false);
                añadirOpcionesOP();
                //modelo.addRow(g.get(0).toArray());
                modelo.addRow(new String[]{"0","","","","","",""});
                break;
            }
        }
    }
    public void añadirOpcionesOP() {
        ListaSolución.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(listaDeOperaciones));
    }
    public void removerOpcionesOP() {
        ListaSolución.getColumnModel().getColumn(5).setCellEditor(null);
    }
    //----------------------------------------------------------
    /**
     * Método encargado  de verificar si una cadena puede convertirse
     * en un número.
     * @param str
     * @return 
     */
    public boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
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
        String a = (String) modelo.getValueAt(modelo.getRowCount() - 1, 1);
        String b = (String) modelo.getValueAt(modelo.getRowCount() - 1, 2);
        String numero = a + b;
        int res = p.binToDec(numero);
        resultadoBin.setText(numero);
        resultadoDec.setText("Resultado: " + res + " => ");
        if (res == 7) {
            mistery();
        }
    }
    
    private void BorrarRes() {
        resultadoBin.setText(" ");
        resultadoDec.setText(" ");
    }
    /**
     * Limpia el modelo de la tabla
     * @param model 
     */
    private void removerFilas() {
        DefaultTableModel dm = (DefaultTableModel)ListaSolución.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
    }
    
    private void initMenu() {
        JMenuBar mb = new JMenuBar();
        setJMenuBar(mb);
        JMenu menu1 = new JMenu("Menu");
        mb.add(menu1);
        mi1 = new JMenuItem("Creditos");
        menu1.add(mi1);
        mi2 = new JMenuItem("Salir");
        menu1.add(mi2);
        JMenu menu2 = new JMenu("Herramientas");
        mb.add(menu2);
        mi3 = new JMenuItem("Convertidor");
        menu2.add(mi3);
    }
    
    public static void main(String... args) {
        VentanaPrincipal p = new VentanaPrincipal();
    }

    private void determinarOpcion() {
        if(normal.isSelected()){
            opcion = 0; 
        }else if(yoResolver.isSelected()){
            opcion = 2;
        }
        else { opcion = 1;}
    }
}