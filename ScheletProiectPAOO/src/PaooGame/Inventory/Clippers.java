package PaooGame.Inventory;

import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.entities.Entity;
import PaooGame.entities.Player;
import PaooGame.entities.SimpleEnemy;

import java.awt.*;
import java.util.ArrayList;

import static PaooGame.Game.levelManager;
import static PaooGame.Tiles.LevelManager.gateXpos;
import static PaooGame.Tiles.LevelManager.gateYpos;
import static PaooGame.utils.Camera.xCamera;

public class Clippers extends ItemAbstractClass {

    protected SimpleEnemy e;

    private int drawingOffset = 10;

    private Clippers(SimpleEnemy e) {
        initHitbox(e.getHitBox().x, e.getHitBox().y + e.getHitBox().height, 8,8);
        this.e = e;
    }


    @Override
    public void update()
    {
        this.hitBox.x = (int) e.hitBox.x + drawingOffset;
        this.hitBox.y = (int) e.hitBox.y + drawingOffset;
    }

    @Override
    public void render(Graphics g)
    {
        if(e.died){
            if(!used) {
                if (!collected) {
                    g.drawImage(Assets.clipperstxt, (int) hitBox.x - xCamera, (int) hitBox.y, 16, 16, null);
                } else
                    g.drawImage(Assets.clipperstxt, 1080, 20, 32, 32, null);
            }
        }

       if(Game.DEBUG)
           drawHitbox(g);
    }

    @Override
    public void pickItem(Player p){
        if(e.died){
            if (hitBox.x > p.hitBox.x && hitBox.x + hitBox.width < p.hitBox.x + p.hitBox.width)
                if (hitBox.y > p.hitBox.y && hitBox.y + hitBox.height < p.hitBox.y + p.hitBox.height)
                    collected = true;
        }
    };

    @Override
    public void useItem(Player p){
        if(collected)
        {
            if((p.getHitBox().x > gateYpos * 32) && (p.getHitBox().y > gateXpos * 32))
            {
                used = true;
                levelManager.passLevel(p);
            }
        }
    };

    public static Clippers setTarget(ArrayList<Entity> arr) // seteaza care este inamicul care are variabila contiansClippers pe true
    {
        for(Entity e : arr)
            if(e instanceof SimpleEnemy)
                if(((SimpleEnemy) e).continsClippers)
                    return new Clippers((SimpleEnemy) e);
        return null;
    }
}
