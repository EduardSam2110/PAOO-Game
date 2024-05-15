package PaooGame.GameState;

import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.*;

public class ResetGameState implements GameState{

    @Override
    public void renderRequest(Graphics g) {

    }

    @Override
    public void updateRequest() {
        START_PRESSED = true;
        RESET_PRESSED = false;
        Player.getInstance().resetAll();
        levelManager.initALevel();
    }
}
