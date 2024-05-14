package PaooGame.GameState;
import PaooGame.entities.Player;
import PaooGame.utils.LoadSave;
import static PaooGame.Game.*;
import java.awt.*;

public class LoadGameState implements GameState {

    @Override
    public void renderRequest(Graphics g) {

    }

    @Override
    public void updateRequest() {
        LoadSave.LoadGame(Player.getInstance());
    }
}
