package PaooGame.GameState;

import java.awt.*;

import PaooGame.Game;
import PaooGame.Game.*;
import PaooGame.entities.HealthBar;
import PaooGame.entities.Player;

import static PaooGame.Game.*;

public class MenuControl {
    public static void updateRequest(Game g) {

        if (EXIT_PRESSED)
            g.setState(new ExitState());

        else if (RESET_PRESSED)
            g.setState(new ResetGameState());

        else if(GAME_FINISHED)
            g.setState(new GameFinishedState());

        else if (SAVE_SELECTED)
            g.setState(new SaveGameState());

        else if (Player.getInstance().died)
            g.setState(new GameOverState());

        else if (LOAD_SELECTED) {
            g.setState(new LoadGameState());
            LOAD_SELECTED = false;
            START_PRESSED = true;
        }

        else if (START_PRESSED)
            g.setState(new StartState());
        else
            g.setState(new PauseState());
    }
}
