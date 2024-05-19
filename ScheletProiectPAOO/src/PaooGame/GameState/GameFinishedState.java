package PaooGame.GameState;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.LevelManager;
import PaooGame.entities.Score;

import java.awt.*;

import static PaooGame.Game.*;

public class GameFinishedState implements GameState {
    @Override
    public void renderRequest(Graphics g) {
        g.drawImage(Assets.gamefinished,0,0,1280,720,null);
        Score.drawIfWon(g);
    }

    @Override
    public void updateRequest() {
            if(current_time - old_time > 3_000) {
                LevelManager.level = 1;
                levelManager.initALevel();
                Score.resetAll();
                Game.START_PRESSED = false;
                GAME_FINISHED = false;
            }
//        System.out.println(current_time + "    " + old_time);
    }
}
