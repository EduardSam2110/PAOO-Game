package PaooGame.Inventory;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Tiles.LevelManager;
import PaooGame.entities.Player;

import java.awt.*;

import static PaooGame.utils.Camera.xCamera;

/*
itemul superputere
ajuta la a a da damage isntant inamicilor simpli si dublu pentru Boss
la ultimul nivel ajuta si printr o super saritura
 */
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
        if(collected && LevelManager.level == 3)
            Player.getInstance().setJumpSpeed(-8);
        else
            Player.getInstance().setJumpSpeed(-4);
    }

    @Override
    public void render(Graphics g)
    {
        if (!collected) {
            g.drawImage(Assets.superPaw, (int) hitBox.x - xCamera, (int) hitBox.y, 16, 16, null);
        } else
            g.drawImage(Assets.superPaw, 1032, 16, 40, 40, null);

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
        if(!collected){
            setState(false);
            hitBox.x = x;
            hitBox.y = y;
        }
    }

    public void setState(boolean state)
    {
        collected = used = state;
    }


}
