package PaooGame.GameState;

import PaooGame.entities.HealthBar;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.levelManager;

public class StartState implements GameState {

    @Override
    public void renderRequest(Graphics g) {

        levelManager.draw(g);
        Player.getInstance().render(g);
    }

    @Override
    public void updateRequest() {
        Player.getInstance().update();
        levelManager.update(Player.getInstance());
    }
}
