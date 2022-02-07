package project;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Background_image extends JPanel
{
    private Image img;
  public Background_image(String img) 
  {
    this(new ImageIcon(img).getImage());
  }
  public Background_image(Image img) 
  {
    this.img = img;
    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
    setPreferredSize(size);
    setMinimumSize(size);
    setMaximumSize(size);
    setSize(size);
    setLayout(null);
  }

    Background_image(ImageIcon imageIcon)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  //@Override
  public void paintComponent(Graphics g, int x, int y) 
  {
    g.drawImage(img, x, y, null);
  }
}
