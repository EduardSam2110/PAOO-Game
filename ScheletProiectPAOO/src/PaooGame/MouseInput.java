package PaooGame;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener, MouseInputListener {

    private Game gamepanel;
    public MouseInput(Game gamepanel){
          this.gamepanel = gamepanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        gamepanel.x = e.getXOnScreen();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
