package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.UserInterface.ButtonInterface;
import PaooGame.UserInterface.MenuControl;
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

//            if(Game.START_PRESSED == false)
//                if(x >= 545 && x <= 765)
//                    if(y >= 410 && y <= 490 )
//                        Game.START_PRESSED = true;


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(MousePerm){
            int x = 0, y = 0;

            if (e.getButton() == MouseEvent.BUTTON1) {
                x = e.getX();
                y = e.getY();
            }


            start.getClick(x, y);
            load.getClick(x, y);
            save.getClick(x, y);
            exit.getClick(x, y);
            restart.getClick(x, y);

        }

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
