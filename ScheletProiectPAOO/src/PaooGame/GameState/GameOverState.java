package PaooGame.GameState;

import PaooGame.Game;
import PaooGame.Tiles.LevelManager;
import PaooGame.entities.Player;
import PaooGame.entities.Score;

import java.awt.*;
import java.util.Timer;

import static PaooGame.Game.*;
import static PaooGame.Graphics.Assets.game_over;

public class GameOverState implements GameState{

    @Override
    public void renderRequest(Graphics g) {
        g.drawImage(game_over, 0, 0, 1280, 720, null);
    }

    @Override
    public void updateRequest() {

        /*
        se asteapta 3 secunde si dupa se ia jocul de la inceput, din meniul de start
         */
        if(current_time - old_time > 3_000) {
            LevelManager.level = 1;
            levelManager.initALevel();
            Player.getInstance().resetAll();
            Score.resetAll();
            Game.START_PRESSED = false;
        }
    }
}
