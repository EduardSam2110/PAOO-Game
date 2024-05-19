package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.GameState.*;
import PaooGame.GameWindow.MouseInput;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.*;

public class MenuControl {
    public static StartButton start = new StartButton(590,300);
    public static LoadButton load = new LoadButton(590,370);

    public static void updateRequest(Game game) {

        if (EXIT_PRESSED)
            game.setState(new ExitState());

        else if (RESET_PRESSED)
            game.setState(new ResetGameState());

        else if(GAME_FINISHED)
            game.setState(new GameFinishedState());

        else if (SAVE_SELECTED)
            game.setState(new SaveGameState());

        else if (Player.getInstance().died)
            game.setState(new GameOverState());

        else if (LOAD_SELECTED) {
            game.setState(new LoadGameState());
            LOAD_SELECTED = false;
            START_PRESSED = true;
        }

        else if (START_PRESSED)
            game.setState(new StartState());
        else
            game.setState(new PauseState());

//        System.out.println(MouseInfo.getPointerInfo().getLocation().x + "," + MouseInfo.getPointerInfo().getLocation().y);


        if(game.getState() instanceof PauseState)
        {
            start.action();

        }
    }

    public static void renderMenu(Graphics g, Game game){
        if(game.getState() instanceof PauseState)
        {
            start.draw(g, game);
            load.draw(g, game);
        }
    }
}
