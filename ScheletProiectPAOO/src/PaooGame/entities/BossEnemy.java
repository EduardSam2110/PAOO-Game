package PaooGame.entities;

import PaooGame.Game;

import java.awt.*;

import static PaooGame.Graphics.Assets.*;
import static PaooGame.utils.Camera.xCamera;
import static PaooGame.utils.Constants.*;
import static PaooGame.utils.Constants.EnemyATTACK;

public class BossEnemy extends SimpleEnemy{
    public BossEnemy(int x, int y, int width, int height, boolean containsClippers) {
        super(x,y,width,height, containsClippers);
        initHitbox(x,y,80,28);
        super.yDrawOffset = 160;
        super.xDrawOffset = 70;
        movingRight = true;
        b = new Bullet(this);
        b.bulletspeed = 4f;
        current_animation = boss_animations_right[EnemyIDLE];
        current_animation_left = boss_animations_left;
        current_animation_right = boss_animations_right;
        health.lifeCount = 10;
    }

    public void render(Graphics g)
    {

        g.drawImage(current_animation[aniIndex], (int) (hitBox.x - xDrawOffset - xCamera),(int) (hitBox.y - yDrawOffset),width,height,null);

        b.SHOOT(g);


        //debugg
        if(Game.DEBUG)
            drawHitbox(g);
    }
}
