package GUI;

import java.awt.Dimension;

public class VetanaExplicación extends javax.swing.JFrame {
    String tipoOP,OP;
    String Q, Q1,A,M,ultimoQ;
    String Res="NAN";
    private void initStr(String[] rowAt) {
        A = rowAt[1];
        Q = rowAt[2];
        Q1 = rowAt[3];
        M = rowAt[4];
        String l = rowAt[5];
        Res = rowAt[6];
        ultimoQ = Q.substring(Q.length()-1);
        System.out.println(l);
        if(l.equals(" desplazamiento"))
        {
            tipoOP="Desplazamiento";
            OP="";
            setTextDesp();
        }else if (l.equals(" A<~A+M"))
        {
            tipoOP="Sumar";
            OP="+";
            setText();
        }else if (l.equals(" A<~A-M"))
        {
            tipoOP="Restar";
            OP="-";
            setText();
        }
        else if (l.equals(" Inicial"))
        {
            tipoOP="Inicial";
            OP="init";
            setTextInit();
        }
    }
    VetanaExplicación(String[] rowAt) {
        initComponents();
        initStr(rowAt);
        setMinimumSize(new Dimension(400, 300));
        setTitle("Explicación operación "+tipoOP);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextPane1.setContentType("text/html"); // NOI18N
        jTextPane1.setText("");
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VetanaExplicación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VetanaExplicación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VetanaExplicación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VetanaExplicación.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String[] g = {"0","110100","101011","1","010111"," Inicial", "000000"};
                new VetanaExplicación(g).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
    
    private void setTextDesp() {
        String primeroA=A.substring(0,1);
        String sinAsin=A.substring(1,A.length()-1);
        String ultimoA= A.substring(A.length()-1);
        String AsinUltimo=A.substring(0,A.length()-2);
        String Qsin=Q.substring(0,Q.length()-1);
        String QsinUltimo=Q.substring(0,Q.length()-2); 
        String algo = "<table style=\"width: 100%;\" border=\"1\">";
        String tabla = String.format(
                "<center><h1> %s  </h1></center><br>"
                + "<p> Texto </p> %s"+
        "<tbody><tr>"+
         " <td style=\"height: 19px; text-align: center;\">A</td>"+
         " <td style=\"text-align: center;\">Q</td>"+
         " <td style=\"text-align: center;\">Q-1</td>"+
        "</tr>"+
        "<tr>"+
          "<td style=\"text-align: center; width: 538.667px;\"><FONT color=\"green\">%s</font>%s<FONT color=\"red\">%s</font></td>"+
          "<td style=\"text-align: center; width: 538.667px;\">%s<FONT color=\"blue\">%s</font></td>"+
          "<td style=\"text-align: center; width: 200px;\"> %s </td>"+
        "</tr>"+
        "<tr>"+
          "<td style=\"text-align: center;\"><FONT color=\"green\">%s</font><FONT color=\"green\">%s</font>%s</td>"+
          "<td style=\"text-align: center;\"><FONT color=\"red\">%s</font>%s</td>"+
          "<td style=\"text-align: center;\"><FONT color=\"blue\">%s</font></td>"+
        "</tr></tbody> </table>",
       tipoOP, algo,primeroA, sinAsin, ultimoA, Qsin, ultimoQ, Q1, primeroA,primeroA,AsinUltimo, ultimoA,QsinUltimo, ultimoQ);
        jTextPane1.setText(tabla);
    }
    private void setTextInit() {
        String s = String.format("<center><h1> %s</h1></center><br>", tipoOP);
        String cuerpo = "<div style=\"text-align: center;\">Dados los números %1 y %2, es necesario:<br>"+
      "<br>"+
      "1. Convertirlos a binario complemento a dos<br>"+
      "<br>"+
      "1 = bin(%1)<br>"+
      "%2 = bin(%2)<br>"+
      "<br>"+
      "2. Igualar ambos al mismo número de bits<br>"+
      "<br>"+
      "%1 = fin(%1)<br>"+
      "%2 = fin(%2)<br>"+
      "<br>"+
      "3. Inicializar a A en %A (Asignarle tantos ceros como dígitos tenga M)<br>"+
      "<br>"+
      "4. Inicializar Q1 en 0</div>";
        jTextPane1.setText(s);
    }
    private void setText() {
        
        String s = String.format("<center><h1> %s</h1></center><br>"
                + "<center><p>Ya que el último dígito de <b>Q</b> (%s) y de <b>Q-1</b> (%s) <br>"
                + "es <b>%s</b> y <b>%s</b> respectivamente,<br>"
                + "es necesario <b>%s</b> a <b>A</b> y a <b>M</b></p><br><br></center>", tipoOP, Q,Q1, ultimoQ,Q1,tipoOP);
        
        String q = String.format("<center>&nbsp;%s</center><center>%s%s</center><center>_____________</center><center>&nbsp;%s</center>",A,OP,M,Res);
        jTextPane1.setText(s+q);
    }
}
