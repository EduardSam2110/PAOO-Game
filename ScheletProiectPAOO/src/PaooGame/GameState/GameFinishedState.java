package PaooGame.GameState;

import PaooGame.Game;
import PaooGame.Tiles.LevelManager;
import PaooGame.entities.Score;

import java.awt.*;

import static PaooGame.Game.*;

public class GameFinishedState implements GameState {
    @Override
    public void renderRequest(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Game Finished!\n Your score: " + (int) Score.finalScore, 500,500);
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
