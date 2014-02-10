package GUI;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class SplashScreen extends JWindow {
  public SplashScreen() {
    JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);
    URL imageurl = getClass().getResource("/res/splash.png");
    Image myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
    JLabel label = new JLabel(new ImageIcon(myPicture));
    JLabel copyrt = new JLabel ("Copyleft 2014, Arquitectura II", JLabel.CENTER);
    copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
    content.add(label, BorderLayout.CENTER);
    content.add(copyrt, BorderLayout.SOUTH);
    content.setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));
    setSize(new Dimension(400, 600));
    setLocationRelativeTo(null);
    setVisible(true);
    try { Thread.sleep(2000); } catch (Exception e) {}
    setVisible(false);
    VentanaPrincipal p = new VentanaPrincipal();
  }

  public static void main(String[] args) {
      SplashScreen splash = new SplashScreen();
  }
}