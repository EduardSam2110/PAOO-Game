package PaooGame;

import PaooGame.GameWindow.GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Constants.Directions.*;

public class KeyboardInput implements KeyListener {

    private Game gamepanel;
    public KeyboardInput(Game gamepanel) {
        this.gamepanel = gamepanel;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                gamepanel.getPlayer().setUp(true);
                break;
            case KeyEvent.VK_A:
                gamepanel.getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamepanel.getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamepanel.getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SHIFT:
                gamepanel.getPlayer().setSpeed(true);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                gamepanel.getPlayer().setUp(false);
                break;
            case KeyEvent.VK_A:
                gamepanel.getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamepanel.getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamepanel.getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SHIFT:
                gamepanel.getPlayer().setSpeed(false);
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}