package PaooGame.entities;

import java.awt.*;
import static PaooGame.Graphics.Assets.health_bar;

// clasa modeleaza health bar-ul player-ului, counter fiind numarul de vieti actuale
// maxHealth fiind numarul de vieti totale
public class HealthBar {
    public float lifeCount = 3;

    public void render(Graphics g, int x, int y, int width, int height)
    {
        g.drawImage(health_bar[health_bar.length - (int) lifeCount - 1],x,y,width,height, null);
    }
}
