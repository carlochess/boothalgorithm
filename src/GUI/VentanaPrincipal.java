package GUI;

import algoritmobooth.ImplAlgoritmo;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
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
    JTextField CampoNumUno, CampoNumDos, resultadoBin;
    JTable ListaSolución;
    JLabel button, button2, resultadoDec;
    JButton botonResolver, i, j;
    JPanel mainPanel, panel, panel2, panel3, panel4;
    Object[][] data;
    DefaultTableModel modelo;
    JMenuItem mi1, mi2;
    JCheckBox checkbox;
    ImplAlgoritmo p;
    boolean sel=false, calcularMaspasos= false;
    int posicion;
    ArrayList<ArrayList<String>> g;
    //----------
    // Métodos
    //----------
    private JPanel panel5;
    private JPanel panel6;

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
        j.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                    if (g != null && calcularMaspasos) {
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
        i.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (g != null && calcularMaspasos) {
                    int longitudMaxima = g.size()-1;
                        if (posicion <= longitudMaxima)
                        {
                            modelo.addRow(g.get(posicion).toArray());
                            posicion++;
                            if (posicion == longitudMaxima+1)imprimirRes();
                        }
                    } 
            }
        });
        ListaSolución.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = ListaSolución.rowAtPoint(e.getPoint());
                int column = ListaSolución.columnAtPoint(e.getPoint());
                if (row >= 0 && column==5) {
                    System.out.println(row);
                    VetanaExplicación a = new VetanaExplicación(getRowAt(row));
                }
            }
            public String[] getRowAt(int row) {
                String[] result = new String[7];
                row = (row==0)? 1:row-1;
                for (int i = 0; i < 5; i++) {
                    result[i] = (String)ListaSolución.getModel().getValueAt(row, i);
                }
                result[5] = (String)ListaSolución.getModel().getValueAt(row+1, 5);
                result[6] = (String)ListaSolución.getModel().getValueAt(row+1, 1);
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
        
        checkbox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
                if (event.getItemSelectable() == checkbox) {
                    sel = checkbox.isSelected();
                    i.setEnabled(sel);
                    j.setEnabled(sel);
                }
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

        CampoNumUno = new JTextField("6", 20);
        //CampoNumUno.setPreferredSize(new Dimension(100, 20));
        panel.add(CampoNumUno);
        CampoNumUno.setDragEnabled(true);
        //----------------------------------------
        panel2 = new JPanel();
        panel2.setBorder(new TitledBorder("Numero Uno"));

        CampoNumDos = new JTextField("5", 20);
        //CampoNumDos.setPreferredSize(new Dimension(100, 20));
        panel2.add(CampoNumDos);
        CampoNumDos.setDragEnabled(true);
        panel.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        //----------------------------------------
        panel3 = new JPanel();
        botonResolver = new JButton("Calcular");
        botonResolver.setTransferHandler(new TransferHandler("text"));
        checkbox = new JCheckBox("Paso a Paso", false);
        checkbox.setFocusable(false);
        panel3.setBackground(Color.WHITE);
        panel3.add(botonResolver);
        panel3.add(checkbox);
        //---------------------------------------- 
        panel5 = new JPanel();
            
            String[] columna = new String[]{"N", "A", "Q", "Q-1", "M", "OP"};
            modelo = new DefaultTableModel(new Object[0][0], columna);
            ListaSolución = new JTable(modelo);
            ListaSolución.getColumn("N").setPreferredWidth(20);
            ListaSolución.getColumn("Q-1").setPreferredWidth(20);
            ListaSolución.getColumn("OP").setPreferredWidth(150);
            ListaSolución.setFillsViewportHeight(true);
            jScrollPane1 = new JScrollPane(ListaSolución);
            jScrollPane1.setPreferredSize(new Dimension(500, 300));
            
            panel6 = new JPanel();
            panel6.setLayout(new BoxLayout(panel6, BoxLayout.Y_AXIS));
            j = new JButton("/\\");
            i = new JButton("\\/");
            j.setBackground(Color.WHITE);
            i.setBackground(Color.WHITE);
            i.setEnabled(false);
            j.setEnabled(false);
            panel6.add(j);
            panel6.add(i);
        panel5.setBackground(Color.WHITE);
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
        calcularMaspasos = checkbox.isSelected();
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
        removerFilas(modelo);
        BorrarRes();
        g = p.init(uno, dos);
        posicion = 1;
        if (g != null) {
            if(!sel)
            {
                for (ArrayList<String> h : g) {
                    modelo.addRow(h.toArray());
                }
                imprimirRes();
            }else{
                modelo.addRow(g.get(0).toArray());
            }
        } else {
            System.out.println("g sin data");
        }
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
    private void removerFilas(DefaultTableModel model) {
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }
    }

    public static void main(String... args) {
        VentanaPrincipal p = new VentanaPrincipal();
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
    }
}

class SpotlightLayerUI extends LayerUI<JPanel> {

    private boolean mActive;
    private int mX, mY;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(
                AWTEvent.MOUSE_EVENT_MASK
                | AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    @Override
    public void uninstallUI(JComponent c) {
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Paint the view.
        super.paint(g2, c);

        if (mActive) {
            // Create a radial gradient, transparent in the middle.
            java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(mX, mY);
            float radius = 72;
            float[] dist = {0.0f, 1.0f};
            Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK};
            RadialGradientPaint p =
                    new RadialGradientPaint(center, radius, dist, colors);
            g2.setPaint(p);
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, .6f));
            g2.fillRect(0, 0, c.getWidth(), c.getHeight());
        }

        g2.dispose();
    }

    @Override
    protected void processMouseEvent(MouseEvent e, JLayer l) {
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            mActive = true;
        }
        if (e.getID() == MouseEvent.MOUSE_EXITED) {
            mActive = false;
        }
        l.repaint();
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
        Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
        mX = p.x;
        mY = p.y;
        l.repaint();
    }
}