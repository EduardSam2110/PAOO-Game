package PaooGame.GameState;

import PaooGame.utils.LoadSave;

import java.awt.*;

public class ExitState implements GameState {

    @Override
    public void renderRequest(Graphics g) {

    }

    @Override
    public void updateRequest() {
        System.out.println("Game will shutdown");
        LoadSave.CloseConnection();
        System.exit(0);
    }
}
