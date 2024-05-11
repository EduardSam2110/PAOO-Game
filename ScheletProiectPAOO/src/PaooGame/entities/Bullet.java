package PaooGame.entities;

import PaooGame.Game;
import PaooGame.Graphics.Assets;

import java.awt.*;

import static PaooGame.Game.player;
import static PaooGame.utils.Camera.xCamera;

public class Bullet extends Entity {
    private int x,y;
    private int startpoint;
    private float bulletspeed = 2.5f;
    private Enemy e;


    public Bullet(Enemy e) {
        super((int) e.getHitBox().x,(int) e.getHitBox().y, 16,16);
        this.e = e;
        x = (int) e.getHitBox().x;
        y = (int) e.getHitBox().y+75;
        initHitbox(x,y,8,8);
    }

    private void draw(Graphics g)
    {
        if(e.shooting)
            g.drawImage(Assets.bullet, (int) hitBox.x - xCamera, (int) hitBox.y,null);

        if(Game.DEBUG)
            drawHitbox(g);
    }

    private void movement()
    {
       if(e.shooting) {
            if (e.getMovingLeft()) {
                hitBox.x -= bulletspeed;
                if(hitBox.x < e.getHitBox().x - 128)
                    hitBox.x = e.getHitBox().x;
            } else if (e.getMovingRight()) {
                hitBox.x += bulletspeed;
                if(hitBox.x > e.getHitBox().x + 128)
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
        die_if_attack();
    }

    private void die_if_attack()
    {
        int playerX = (int) player.getHitBox().x;
        int playerY = (int) player.getHitBox().y;

        if((hitBox.y >= playerY) && (hitBox.y + hitBox.height <= playerY + player.getHitBox().height))
            if((hitBox.x >= playerX) && (hitBox.x + hitBox.width <= playerX + player.getHitBox().width))
                player.takeDamage();
    }
}

