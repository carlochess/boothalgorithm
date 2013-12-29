package GUI;

import algoritmobooth.ImplAlgoritmo;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author yocasta
 */
public class VentPrin extends javax.swing.JFrame {
    DefaultListModel<String> listModel = new DefaultListModel();
    /** Creates new form VentPrin */
    public VentPrin() {
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CampoNumUno = new javax.swing.JTextField();
        CampoNumDos = new javax.swing.JTextField();
        botonResolver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ListaSolución = new javax.swing.JList();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmo de booth");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(0));

        jLabel1.setText("Número uno");

        jLabel2.setText("Número dos");

        CampoNumUno.setToolTipText("campo numero uno");
        CampoNumUno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoNumUnoActionPerformed(evt);
            }
        });

        CampoNumDos.setToolTipText("campo numero dos");
        CampoNumDos.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        CampoNumDos.setDragEnabled(true);
        CampoNumDos.setName(""); // NOI18N
        CampoNumDos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CampoNumDosMouseExited(evt);
            }
        });

        botonResolver.setText("Resolver");
        botonResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResolverActionPerformed(evt);
            }
        });

        ListaSolución.setModel(listModel);
        jScrollPane1.setViewportView(ListaSolución);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoNumDos, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                            .addComponent(CampoNumUno, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)))
                    .addComponent(botonResolver, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(CampoNumUno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(CampoNumDos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(botonResolver)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jMenu1.setText("Sugerencias");

        jMenuItem1.setText("Info");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuItem1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarVentana(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void CampoNumUnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoNumUnoActionPerformed

}//GEN-LAST:event_CampoNumUnoActionPerformed

private void botonResolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonResolverActionPerformed
    UpdateJList(null);
    ImplAlgoritmo p = new ImplAlgoritmo();
    if(esInvalido(CampoNumUno.getText()))
        return;
    if(esInvalido(CampoNumDos.getText()))
        return;
    int uno = Integer.parseInt(CampoNumUno.getText());
    int dos = Integer.parseInt(CampoNumDos.getText());
    UpdateJList(p.init(uno, dos));
}//GEN-LAST:event_botonResolverActionPerformed

private void CampoNumDosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CampoNumDosMouseExited
// TODO add your handling code here:
    botonResolver.setText(":D");
}//GEN-LAST:event_CampoNumDosMouseExited

private void mostrarVentana(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostrarVentana
// TODO add your handling code here:
    
}//GEN-LAST:event_mostrarVentana

private void jMenuItem1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MousePressed
// TODO add your handling code here:
    JOptionPane.showMessageDialog(null, "https://github.com/carlochess/");
}//GEN-LAST:event_jMenuItem1MousePressed
private void UpdateJList(ArrayList<String> g ){
    listModel = new DefaultListModel<String>();
    if(null != g){
        String k="";
        for(String p : g){
             listModel.addElement(p);
             k = p;
        }
        imprimirRes(k);
    }
    ListaSolución.setModel(listModel);     
    ListaSolución.setSelectedIndex(0);
}

public static boolean isNumeric(String str)  
{  
  try  
  {  
    double d = Double.parseDouble(str);  
  }  
  catch(NumberFormatException nfe)  
  {  
    return false;  
  }  
  return true;  
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CampoNumDos;
    private javax.swing.JTextField CampoNumUno;
    private javax.swing.JList ListaSolución;
    private javax.swing.JButton botonResolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private boolean esInvalido(String text) {
        return !isNumeric(text);
    }

    private void imprimirRes(String k) {
        int longitud = k.indexOf(" ");
        String numero = k.substring(0,longitud)+k.substring(longitud+7,longitud*2+7);
        JOptionPane.showMessageDialog(null, numero+"\n"+Integer.parseInt(numero, 2));
    }
}
