package PaooGame.GameState;

import java.awt.*;

import PaooGame.Game;
import PaooGame.Game.*;
import PaooGame.entities.HealthBar;

import static PaooGame.Game.*;

public class MenuControl {
    public static void updateRequest(Game g) {

        if(RESET_PRESSED)
        {
            START_PRESSED = true;
            RESET_PRESSED = false;
        }

        if(EXIT_PRESSED)
            g.setState(new ExitState());
        else if(HealthBar.health == 2) {
            g.setState(new GameOverState());
            HealthBar.health = 3;
        } else if(LOAD_SELECTED) {
            g.setState(new LoadGameState());
            LOAD_SELECTED = false;
            START_PRESSED = true;
        }
        else if(START_PRESSED) {
            g.setState(new StartState());
        }
//        else if(!START_PRESSED)
//            g.setState(new PauseState());
    }
}
