package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.GameState.*;
import PaooGame.GameWindow.MouseInput;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.*;

public class MenuControl {
    private static StartButton start = new StartButton(590,300);

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

//        System.out.println(MouseInfo.getPointerInfo().getLocation().x + "," + MouseInfo.getPointerInfo().getLocation().y);


    }

    public static void renderMenu(Graphics g, Game game){
        if(game.getState() instanceof PauseState)
        {
            start.draw(g, game);
        }
    }
}
