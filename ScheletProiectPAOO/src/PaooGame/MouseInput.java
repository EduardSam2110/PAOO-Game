package PaooGame;

import entities.Player;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener, MouseInputListener {

    private Player player;

    public MouseInput(Player player) {
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + " " + e.getY());
        if(e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
            if(e.getXOnScreen() > 500 && e.getYOnScreen() < 700)
                Game.START_PRESSED = true;
        }
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
