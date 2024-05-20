package PaooGame.UserInterface;

import PaooGame.Game;
import PaooGame.GameState.*;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.Game.*;
/*
Clasa se ocupa cu gestionarea starilor jocului si cu meniurile

Modul de functionare este asemanator cu o masina de stari finite gestionata prin variabile booleene
, adaugand faptul ca unele comenzi au o prioritate mai mare
ca altele (spre exemplu ExitGame este prioritar lui StartGame)

Totodata aici se actualizeaza si butoanele meniului, atunci cand campul
state din clasa Game se afla in starile StartMenuState si InGamePauseState
 */
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

        else if (SAVE_SELECTED)
            game.setState(new SaveGameState());

        else if(GAME_FINISHED)
            game.setState(new GameFinishedState());

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
