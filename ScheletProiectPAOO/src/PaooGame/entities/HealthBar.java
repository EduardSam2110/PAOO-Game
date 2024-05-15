package PaooGame.entities;

import java.awt.*;
import static PaooGame.Graphics.Assets.health_bar;

// clasa modeleaza health bar-ul player-ului, counter fiind numarul de vieti actuale
// maxHealth fiind numarul de vieti totale
public class HealthBar {
    public float lifeCount = 3;

    public void render(Graphics g)
    {
        g.drawImage(health_bar[health_bar.length - (int) lifeCount - 1],50,20,32*3,32, null);
    }
}
