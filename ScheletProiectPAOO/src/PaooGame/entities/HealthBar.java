package PaooGame.entities;

import java.awt.*;
import static PaooGame.Graphics.Assets.health_bar;

// clasa modeleaza health bar-ul player-ului, counter fiind numarul de vieti actuale
// maxHealth fiind numarul de vieti totale
public class HealthBar {
    public static int counter = 0;
    public static int maxLife = 3;

    public static void render(Graphics g)
    {
        g.drawImage(health_bar[counter],1100,20,32*3,32, null);
    }
}
