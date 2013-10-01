package br.com.sani.util;  
  
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
  
/** 
 * @author Ronald Tetsuo Miura 
 */  
public class Drag extends MouseAdapter implements MouseMotionListener {  
  
    /** */  
    int baseX = -1;  
  
    /** */  
    int baseY = -1;  
  
    /** 
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent) 
     */  
    public void mouseDragged(MouseEvent e) {  
    	
    	if ((this.baseX != -1) && (this.baseY != -1)) {  
            Component c = e.getComponent();  
            int x = c.getX() + e.getX() - this.baseX;  
            int y = c.getY() + e.getY() - this.baseY;  
            Container parent = c.getParent();  
            Dimension pSize = parent.getSize();  
            Rectangle cSize = c.getBounds();  
      
            x = Math.max(x, 0);  
            x = Math.min(x, pSize.width - cSize.width);  
            y = Math.max(y, 0);  
            y = Math.min(y, pSize.height - cSize.height);  
      
            c.setLocation(x, y);  
            c.getParent().repaint();  
        }  
    }  
  
    /** 
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent) 
     */  
    public void mouseMoved(MouseEvent e) {  
        //  
    }  
  
    /** 
     * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent) 
     */  
    public void mousePressed(MouseEvent e) {  
        Component c = e.getComponent();  
        Container parent = c.getParent();  
        Component[] all = parent.getComponents();  
        for (int i = 0; i < all.length; i++) {  
            parent.remove(all[i]);  
        }  
        parent.add(c);  
        for (int i = 0; i < all.length; i++) {  
            if (all[i] != c) {  
                parent.add(all[i]);  
            }  
        }  
        this.baseX = e.getX();  
        this.baseY = e.getY();
    }  
  
    /** 
     * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent) 
     */  
    public void mouseReleased(MouseEvent e) {  
        this.baseX = -1;  
        this.baseY = -1;  
    }  
  
    /** 
     * @param args - 
     */  
    public static void main(String[] args) {  
  
        Random rand = new Random();  
  
        JFrame f = new JFrame("Teste");  
        f.setSize(640, 480);  
        Dimension windowSize = f.getSize();  
  
        Container contentPane = f.getContentPane();  
        contentPane.setLayout(null);  
  
        JComponent[] comp = new JComponent[10];  
        for (int i = 0; i < comp.length; i++) {  
            comp[i] = new JButton("Fly");  
            comp[i].setLocation(rand.nextInt(windowSize.width), rand.nextInt(windowSize.height));  
            comp[i].setSize(comp[i].getPreferredSize());  
  
            Drag mouseController = new Drag();  
            comp[i].addMouseListener(mouseController);  
            comp[i].addMouseMotionListener(mouseController);  
  
            contentPane.add(comp[i]);  
        }  
  
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        f.setVisible(true);  
  
        f.getGlassPane().setVisible(true);  
    }  
  
}  