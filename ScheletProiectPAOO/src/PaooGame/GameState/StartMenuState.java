package PaooGame.GameState;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.Graphics.Assets.start_game;

public class StartMenuState implements GameState {

    @Override
    public void renderRequest(Graphics g) {
        g.drawImage(start_game,0,0,1280,720,null);
    }

    @Override
    public void updateRequest() {
    }
}
