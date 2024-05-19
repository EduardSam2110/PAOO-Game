package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.UserInterface.ButtonInterface;
import PaooGame.entities.Player;

import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static PaooGame.UserInterface.MenuControl.*;

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
//            if(Game.START_PRESSED == false)
//                if(x >= 545 && x <= 765)
//                    if(y >= 410 && y <= 490 )
//                        Game.START_PRESSED = true;

            start.getClick(x,y);
            load.getClick(x,y);
            save.getClick(x,y);
            exit.getClick(x,y);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1)
//            player.setAttacking(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1)
//            player.setAttacking(false);
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
