package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.GameState.*;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.*;

public class MenuControl {
    public static StartButton start = new StartButton(590,300);
    public static LoadButton load = new LoadButton(590,370);
    public static RestartButton restart = new RestartButton(590,370);
    public static ExitButton exit = new ExitButton(590,440);
    public static SaveButton save = new SaveButton(590,510);

    public static boolean MousePerm = false;

    public static void updateRequest(Game game) {
        MousePerm = false;

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
            if(InGamePause_PRESSED)
                game.setState(new InGamePauseState());
            else
                game.setState(new StartState());
        else
            game.setState(new StartMenuState());

//        System.out.println(MouseInfo.getPointerInfo().getLocation().x + "," + MouseInfo.getPointerInfo().getLocation().y);


        if(game.getState() instanceof StartMenuState)
        {
            MousePerm = true;
            start.action();
            load.action();
            exit.action();
        }

        if(game.getState() instanceof InGamePauseState)
        {
            MousePerm = true;
            start.action();
            save.action();
            exit.action();
            restart.action();
        }

        if(game.getState() instanceof GameFinishedState)
        {

        }
    }

    public static void renderMenu(Graphics g, Game game){
        if(game.getState() instanceof StartMenuState)
        {
            start.draw(g, game);
            load.draw(g, game);
            exit.draw(g, game);
        }

        if(game.getState() instanceof InGamePauseState)
        {
            start.draw(g,game);
            save.draw(g,game);
            exit.draw(g, game);
            restart.draw(g,game);
        }
    }
}
