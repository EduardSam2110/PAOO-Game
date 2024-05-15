package PaooGame.GameWindow;

import PaooGame.Game;
import PaooGame.entities.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    private Player player;

    public KeyboardInput(Player player) {
    this.player = player;
}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                player.setUp(true);
                player.setDown(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                player.setUp(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SHIFT:
                player.setSpeed(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ENTER:
                Game.START_PRESSED = true;
                break;
                //debugg
            case KeyEvent.VK_B:
                Game.DEBUG = !Game.DEBUG;
                break;
            case KeyEvent.VK_ESCAPE:
                Game.START_PRESSED =!Game.START_PRESSED;
                break;
            case KeyEvent.VK_E:
                Game.EXIT_PRESSED = true;
                break;
            case KeyEvent.VK_L:
                Game.LOAD_SELECTED = true;
                break;
            case KeyEvent.VK_R:
                Game.RESET_PRESSED = true;
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
            case KeyEvent.VK_S:
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SHIFT:
                player.setSpeed(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


}