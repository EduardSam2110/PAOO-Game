package PaooGame.GameState;

import java.awt.*;

import PaooGame.Game;
import PaooGame.Game.*;
import static PaooGame.Game.*;

public class MenuControl {
    public static void updateRequest(Game g) {
        if(START_PRESSED)
            g.setState(new StartState());
        else if(LOAD_SELECTED) {
            g.setState(new LoadGameState());
            LOAD_SELECTED = false;
        }
        else
            g.setState(new PauseState());
    }
}
