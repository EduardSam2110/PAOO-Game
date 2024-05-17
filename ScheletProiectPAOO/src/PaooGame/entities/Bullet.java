package PaooGame.entities;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

//import static PaooGame.Game.player;
import static PaooGame.Tiles.Tile.TILE_SIZE;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.CRAWLING;
import static PaooGame.utils.Constants.SIT_DOWN;

public class Bullet extends Entity {
    private int x,y;
    private float bulletspeed = 3.5f;
    private int shootingRange = 5 * TILE_SIZE;
    private SimpleEnemy e;


    public Bullet(SimpleEnemy e) {
        super((int) e.getHitBox().x,(int) e.getHitBox().y, 16,16);
        this.e = e;
        x = (int) e.getHitBox().x;
        y = (int) e.getHitBox().y+75;
        initHitbox(x,y,8,8);
    }

    private void draw(Graphics g)
    {
        if(e.shooting)
            g.drawImage(Assets.bullet, (int) hitBox.x - xCamera, (int) hitBox.y - 15,null);

        if(Game.DEBUG)
            drawHitbox(g);
    }

    private void movement()
    {
       if(e.shooting) {
            if (e.getMovingLeft()) {
                hitBox.x -= bulletspeed;
                if(hitBox.x < e.getHitBox().x - shootingRange)
                    hitBox.x = e.getHitBox().x;
            } else if (e.getMovingRight()) {
                hitBox.x += bulletspeed;
                if(hitBox.x > e.getHitBox().x + shootingRange)
                    hitBox.x = e.getHitBox().x;
            }
       }else{
           hitBox.x = e.getHitBox().x;
           hitBox.y = e.getHitBox().y;
       }
    }

    public void SHOOT(Graphics g)
    {
        draw(g);
        movement();
//        if(!e.died && e.shooting)
//            die_if_attack();
    }

    private void die_if_attack()
    {
        int playerX = (int) Player.getInstance().getHitBox().x;
        int playerY = (int) Player.getInstance().getHitBox().y;

        if((hitBox.y >= playerY) && (hitBox.y + hitBox.height <= playerY + Player.getInstance().getHitBox().height))
            if((hitBox.x >= playerX) && (hitBox.x + hitBox.width <= playerX + Player.getInstance().getHitBox().width))
                if(Player.getInstance().action != CRAWLING && Player.getInstance().action != SIT_DOWN)
                    Player.getInstance().takeDamage();
    }
}

