package GUI;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

class ErrorLayerUI extends LayerUI<JTable> implements ActionListener {
  private boolean mIsRunning;
  private boolean mIsFadingOut;
  private Timer mTimer;
  private int var = 1;
  private Rectangle h;
  private int mFadeCount;
  private int mFadeLimit = 15;
  JTable tablaSolucion;
    private int celdaX;
    private int celdaY;
  @Override
  public void paint (Graphics g, JComponent c) {
    super.paint (g, c);
    if (!mIsRunning) {
      return;
    }
    Graphics2D g2 = (Graphics2D)g.create();
    g2.setColor(Color.orange);
    float fade = (float)mFadeCount / (float)mFadeLimit;
    Composite urComposite = g2.getComposite();
    g2.setComposite(AlphaComposite.getInstance(
        AlphaComposite.SRC_OVER, .5f * fade));
    g2.fillRect(h.x, h.y,h.width,h.height);
    g2.setComposite(urComposite);
    g2.setColor(Color.red);
    g2.setFont(new Font("Serif", Font.BOLD, 20)); 
    g2.drawString("¡Mal!", c.getWidth()/2, c.getHeight()/2);
    g2.dispose();
  }

  public void actionPerformed(ActionEvent e) {
    h = tablaSolucion.getCellRect(celdaX, celdaY, true);
    if (mIsRunning) {
      firePropertyChange("tick", 0, 1);
      if (mIsFadingOut) {
        mFadeCount=1;
        if (--mFadeCount == 0) {
          mIsRunning = false;
          mTimer.stop();
        }
      }
      else if(mFadeCount== mFadeLimit && var==1)
      {
          var = -1;
      }
      else if(mFadeCount == 0 && var==-1)
      {
          var = 1;
      }
      mFadeCount += var;
    }
  }

  public void start(JTable tablaSolucion, int x, int y) {
    if (mIsRunning) {
      return;
    }
    this.tablaSolucion = tablaSolucion;
        this.celdaX = x;
        this.celdaY = y;
    mIsRunning = true;
    mIsFadingOut = false;
    mFadeCount = 0;
    int fps = 24;
    int tick = 1000 / fps;
    mTimer = new Timer(tick, this);
    mTimer.start();
  }

  public void stop() {
    mIsFadingOut = true;
  }

  @Override
  public void applyPropertyChange(PropertyChangeEvent pce, JLayer l) {
    if ("tick".equals(pce.getPropertyName())) {
      l.repaint();
    }
  }
}