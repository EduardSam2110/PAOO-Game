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
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}

/*
public class KeyboardInput implements KeyListener {
    private boolean[] keys;

    public KeyboardInput() {
        keys = new boolean[256];
    }

    public boolean isKeyDown(int keyCode) {
        if (keyCode >= 0) {
            return keys[keyCode];
        }
        return false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() >= 0) {
            keys[e.getKeyCode()] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() >= 0) {
            keys[e.getKeyCode()] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
 */