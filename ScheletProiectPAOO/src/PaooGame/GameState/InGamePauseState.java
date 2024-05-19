package PaooGame.GameState;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.levelManager;

public class InGamePauseState implements GameState {

    @Override
    public void renderRequest(Graphics g) {
        levelManager.draw(g);
        Player.getInstance().render(g);
        g.drawImage(Assets.transparent,0,0,1280,720,null);
    }

    @Override
    public void updateRequest() {

    }
}
