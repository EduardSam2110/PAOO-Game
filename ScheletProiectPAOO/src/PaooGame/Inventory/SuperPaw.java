package PaooGame.Inventory;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.utils.Camera.xCamera;

public class SuperPaw extends ItemAbstractClass {

    public SuperPaw(float x, float y)
    {
        initHitbox(x,y,8,8);
        this.x = (int) x;
        this.y = (int) y;
    }

    @Override
    public void update()
    {

    }

    @Override
    public void render(Graphics g)
    {
        if (!collected) {
            g.drawImage(Assets.superPaw, (int) hitBox.x - xCamera, (int) hitBox.y, 16, 16, null);
        } else
            g.drawImage(Assets.superPaw, 670, 20, 32, 32, null);

        if(Game.DEBUG)
            drawHitbox(g);
    }

    @Override
    public void pickItem(Player p){
        if (hitBox.x > p.hitBox.x && hitBox.x + hitBox.width < p.hitBox.x + p.hitBox.width)
            if (hitBox.y > p.hitBox.y && hitBox.y + hitBox.height < p.hitBox.y + p.hitBox.height) {
                collected = used = true;
            }
    };

    @Override
    public void useItem(Player p){};

    public void setNewPos(int x, int y)
    {
        resetState();
        hitBox.x = x;
        hitBox.y = y;
    }

    public void resetState()
    {
        collected = false;
        used = false;
    }

}
