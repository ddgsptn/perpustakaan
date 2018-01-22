/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dang.aplikasi.perpustakaan.component;

import dang.aplikasi.perpustakaan.utility.ImageUtility;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ddgsptn
 */
public class DangLabel extends JLabel{
    private Icon iconReflec;

  public DangLabel() {
    setIconReflec(null);
  }
    
    public Icon getIconReflec()  {
    return this.iconReflec;
  }

  public final void setIconReflec(Icon iconReflec)throws IllegalArgumentException {
    if (iconReflec == null) {
      firePropertyChange("iconReflec", getIconReflec(), iconReflec);
      this.iconReflec = iconReflec;
      setIcon(null);
    } else {
      if (!(iconReflec instanceof ImageIcon)) {
        throw new IllegalArgumentException();
      }
      firePropertyChange("iconReflec", getIconReflec(), iconReflec);
      this.iconReflec = iconReflec;
      BufferedImage image = ImageUtility.convertToBufferedImageReflection(((ImageIcon)iconReflec).getImage());
      setIcon(new ImageIcon(image));
    }
  }
}
