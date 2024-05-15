package PaooGame.GameState;

import java.awt.*;

import static PaooGame.Game.START_PRESSED;
import static PaooGame.Graphics.Assets.game_over;

public class GameOverState implements GameState{

    @Override
    public void renderRequest(Graphics g) {
        g.drawImage(game_over, 0, 0, 1280, 720, null);
    }

    @Override
    public void updateRequest() {
    }
}
