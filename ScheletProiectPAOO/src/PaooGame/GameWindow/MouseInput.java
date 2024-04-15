package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.entities.Player;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener, MouseInputListener {

    private Player player;

    public MouseInput(Player player) {
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
            int x = e.getX();
            int y = e.getY();
            if(x >= 545 && x <= 765)
                if(y >= 410 && y <= 490 )
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
