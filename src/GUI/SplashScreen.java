package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SplashScreen extends JWindow {
  private int duration;
  public SplashScreen(int d) {
    duration = d;
  }

  public void showSplash() {
    JPanel content = (JPanel)getContentPane();
    content.setBackground(Color.white);

    // Set the window's bounds, centering the window
    int width = 450;
    int height =115;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width-width)/2;
    int y = (screen.height-height)/2;
    setBounds(x,y,width,height);
    String imagen = "../res/oreilly.gif";
    BufferedImage wPic=null;
        try {
            wPic = ImageIO.read(this.getClass().getResource(imagen));
        } catch (IOException ex) {
            
        }
    JLabel label = new JLabel(new ImageIcon(wPic));
    JLabel copyrt = new JLabel
      ("Copyleft 2014, Arquitectura II", JLabel.CENTER);
    copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
    content.add(label, BorderLayout.CENTER);
    content.add(copyrt, BorderLayout.SOUTH);
    Color oraRed = new Color(156, 20, 20,  255);
    content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

    setVisible(true);

    try { Thread.sleep(duration); } catch (Exception e) {}

    setVisible(false);
  }

  public void showSplashAndExit() {
    showSplash();
    VentPrin p = new VentPrin();
  }

  public static void main(String[] args) {
    SplashScreen splash = new SplashScreen(2000);
    splash.showSplashAndExit();
  }
}