package GUI;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class SplashScreen extends JWindow {
  private int duration;
  
  public SplashScreen(int d) {
    duration = d;
  }

  public void showSplash() {
    JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);

    int width = 450;
    int height =115;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2;
    setBounds(x,y,width,height);
    //
    URL imageurl = getClass().getResource("/res/splash.gif");
    Image myPicture = Toolkit.getDefaultToolkit().getImage(imageurl);
    JLabel label = new JLabel(new ImageIcon(myPicture));
    JLabel copyrt = new JLabel
      ("Copyleft 2014, Arquitectura II", JLabel.CENTER);
    copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
    content.add(label, BorderLayout.CENTER);
    content.add(copyrt, BorderLayout.SOUTH);
    content.setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));

    setVisible(true);

    try { Thread.sleep(duration); } catch (Exception e) {}

    setVisible(false);
  }

  public void showSplashAndExit() {
    showSplash();
    VentanaPrincipal p = new VentanaPrincipal();
  }

  public static void main(String[] args) {
    SplashScreen splash = new SplashScreen(2000);
    splash.showSplashAndExit();
  }
}