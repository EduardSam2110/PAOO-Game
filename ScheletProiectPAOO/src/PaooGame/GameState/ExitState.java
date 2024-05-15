package PaooGame.GameState;

import java.awt.*;

public class ExitState implements GameState {

    @Override
    public void renderRequest(Graphics g) {

    }

    @Override
    public void updateRequest() {
        System.out.println("Game will shutdown");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {}
        System.exit(0);
    }
}
