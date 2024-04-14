package entities;

import java.awt.*;

import static PaooGame.Graphics.Assets.game_over;
import static PaooGame.Graphics.Assets.health_bar;

public class HealthBar {
    public static int counter = 0;

    public static void render(Graphics g)
    {
        g.drawImage(health_bar[counter],1100,20,32*3,32, null);
    }
}
