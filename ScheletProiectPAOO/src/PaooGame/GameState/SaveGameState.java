package PaooGame.GameState;


import PaooGame.entities.Player;
import PaooGame.utils.LoadSave;
import static PaooGame.Game.*;

import java.awt.*;

public class SaveGameState implements GameState {

    @Override
    public void renderRequest(Graphics g) {

    }

    @Override
    public void updateRequest() {
        LoadSave.SaveGame(Player.getInstance());
        SAVE_SELECTED = false;
    }
}
